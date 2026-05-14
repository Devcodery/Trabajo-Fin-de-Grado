from datetime import timedelta
from functools import wraps
from urllib.parse import urlparse
import os
import psycopg2
from dotenv import load_dotenv
from flask import Flask, render_template, request, redirect, url_for, session, jsonify

import requests

load_dotenv()

USUARIO = os.getenv("USUARIO")
PASSWD = os.getenv("PASSWD")
SECRET = os.getenv("SECRET")

app = Flask(__name__)
app.secret_key = SECRET
app.permanent_session_lifetime = timedelta(minutes=30)

# Configuración mínima de cookies de sesión.
app.config["SESSION_COOKIE_HTTPONLY"] = True
app.config["SESSION_COOKIE_SAMESITE"] = "Lax"
# Cuando se use HTTPS real detrás del proxy, se puede activar:
# app.config["SESSION_COOKIE_SECURE"] = True

def obtener_sedes():
    try:
        respuesta = requests.get("http://info.empresa.dam.es:8055/items/sedes", timeout=4)
        respuesta.raise_for_status()
        return respuesta.json().get("data", [])
    except requests.RequestException:
        return [
            {"id": 1, "nombre": "Sede Central"},
            {"id": 2, "nombre": "Sede Norte"},
            {"id": 3, "nombre": "Sede Sur"},
        ]


def obtener_departamentos():
    try:
        respuesta = requests.get("http://info.empresa.dam.es:8055/items/departamentos", timeout=4)
        respuesta.raise_for_status()
        return respuesta.json().get("data", [])
    except requests.RequestException:
        return [
            {"id": 1, "nombre": "Sistemas"},
            {"id": 2, "nombre": "Desarrollo"},
            {"id": 3, "nombre": "Redes"},
        ]

def get_conn():
    """Crea una conexión a PostgreSQL usando variables de entorno."""
    return psycopg2.connect(
        host=os.getenv("DB_HOST", "db-users"),
        port=os.getenv("DB_PORT", "5432"),
        dbname=os.getenv("POSTGRES_DB"),
        user=os.getenv("POSTGRES_USER"),
        password=os.getenv("POSTGRES_PASSWORD"),
    )


def login_usuario(user, passwd):
    """Comprueba un usuario normal usando la función SQL login_usuario."""
    with get_conn() as conn:
        with conn.cursor() as cur:
            cur.execute("SELECT login_usuario(%s, %s);", (user, passwd))
            resultado = cur.fetchone()
            
            if resultado is None:
                return False, None 
            
            login_correcto = resultado[0]
            
            if login_correcto:
                cur.execute("select * from usuario where nombre = %s or correo = %s;",(user, user))
                userComplety = cur.fetchall()[0]
                
                return login_correcto, userComplety
            else:
                return login_correcto, None
                


def registrar_usuario(nombre, correo, passwd, apellidos, rol, direccion, departamento, sede):
    """Registra un usuario normal usando la función SQL registrar_usuario."""
    with get_conn() as conn:
        with conn.cursor() as cur:
            cur.execute(
                "SELECT registrar_usuario(%s, %s, %s, %s, %s, %s, %s, %s);",
                (nombre, apellidos, correo, passwd, rol, direccion, departamento, sede),
            )
            resultado = cur.fetchone()
            
            if resultado is None:
                return False
            
            return resultado[0]

def login_requerido(f):
    """Decorador para proteger rutas propias de Flask."""
    @wraps(f)
    def wrapper(*args, **kwargs):
        if not session.get("login"):
            return redirect(url_for("login"))
        return f(*args, **kwargs)

    return wrapper


def next_url_segura(next_url):
    """
    Evita redirecciones abiertas.
    Permitimos URLs relativas como /vistas/carrito.jsp.
    """
    if not next_url:
        return False

    parsed = urlparse(next_url)
    return parsed.netloc == ""

def obtener_usuario(empleado_id):
    with get_conn() as conn:
        with conn.cursor() as cur:
            cur.execute(
                "SELECT * "
                + " FROM usuario WHERE id_usuario = %s;",
                (empleado_id,),
            )
            fila = cur.fetchone()

    if not fila:
        return jsonify({"error": "No existe el usuario"}), 404

    return jsonify({
        "id_usuario": fila[0],
        "nombre": fila[1],
        "apellidos": fila[2],
        "correo": fila[3],
        "rol": fila[5],
        "direccion": fila[6],
        "id_dpto": fila[7],
        "id_sede": fila[8],
    })

@app.route("/registro/<string:rol>", methods=["GET", "POST"])
def registro(rol):
    
    back_url = request.args.get('next') or "/"
    
    if request.method == "POST":
        nombre = request.form.get("nombre")
        apellidos = request.form.get("apellidos")
        correo = request.form.get("email")
        passwd = request.form.get("passwd")
        rol = request.form.get("rol")
        direccion = request.form.get("direccion")
        departamento = request.form.get("departamento")
        sede = request.form.get("sede")

        if registrar_usuario(nombre, correo, passwd, apellidos, rol, direccion, departamento, sede):
            return redirect(url_for("login"))

        return render_template(
            "formulario.html",
            error="No se pudo registrar el empleado. Revisa si ya existe.",
            departamentos=obtener_departamentos(),
            sedes=obtener_sedes(),
            back_url=back_url
        ), 400

    return render_template(
        "formulario.html",
        departamentos=obtener_departamentos(),
        sedes=obtener_sedes(),
        error="",
        rol=rol,
        back_url=back_url
    )

@app.route("/usuarios", methods=["GET"])
@login_requerido
def usuarios():

    if "admin" not in session.get("roles", []):
        return "Sin permisos", 403

    with get_conn() as conn:
        with conn.cursor() as cur:

            cur.execute("SELECT * FROM usuario order by id_usuario;")

            filas = cur.fetchall()
    
    usuarios = []
    for fila in filas:
        usuarios.append({
            "id_usuario": fila[0],
            "nombre": fila[1],
            "apellidos": fila[2],
            "correo": fila[3],
            "rol": fila[5],
            "direccion": fila[6],
            "id_dpto": fila[7],
            "id_sede": fila[8],
        })
    return jsonify(usuarios)

@app.route("/usuario/<int:id_usuario>", methods=["GET"])
@login_requerido
def usuario(id_usuario):
    # if "admin" not in session.get("roles", []):
    #     return "Sin permisos", 403
    return obtener_usuario(id_usuario)
    
@app.route("/login", methods=["GET", "POST"])
def login():
    error = ""

    if request.method == "POST":
        user = request.form.get("user")
        passwd = request.form.get("passwd")
        next_url = request.form.get("next") or "/"

        # Usuario administrador definido en .env.
        if user == USUARIO and passwd == PASSWD:
            session.permanent = True
            session["login"] = True
            session["user"] = user
            session["roles"] = ["admin"]

            return redirect("/Proyecto_Grupo3/AdministradorControlador?opcion=logueado&rol=admin")

        # Usuarios normales registrados en PostgreSQL.
        login_correcto, userComplety  = login_usuario(user, passwd)
        
        if login_correcto: 
            session.permanent = True
            session["login"] = True
            session["user"] = user
            
            if userComplety is None:
                error = "No se extrajo los datos del usuario."
                return render_template("login.html", error=error, next_url=next_url)
            
            session["roles"] = userComplety[5] 
            
            if session["roles"] == 'cliente':
                return redirect(f"/Proyecto_Grupo3/ClienteControlador?idUsuario={userComplety[0]}&rol={userComplety[5]}&opcion=logueado")
            elif session["roles"] == 'consultor':
                return redirect(f"/Proyecto_Grupo3/ConsultorControlador?idUsuario={userComplety[0]}&rol={userComplety[5]}&opcion=logueado")

        error = "Usuario o contraseña incorrectos"

    return render_template("login.html", error=error)


@app.route("/logout")
def logout():
    session.clear()
    return redirect(url_for("login"))


@app.route("/auth/verify")
def auth_verify():
    """
    Endpoint preparado para la siguiente fase con Nginx Proxy Manager.

    Respuestas:
    - 204: permitido.
    - 401: no está logueado.
    - 403: está logueado, pero no tiene el rol requerido.
    """
    print("========== AUTH VERIFY LLAMADO ==========", flush=True)

    if not session.get("login"):
        print("AUTH: no login", flush=True)
        return "", 401

    roles_usuario = session.get("roles", [])

    if isinstance(roles_usuario, str):
        roles_usuario = [roles_usuario]

    roles_requeridos = request.headers.get("X-Required-Roles", "").strip()

    print("AUTH usuario:", session.get("user"), flush=True)
    print("AUTH roles_usuario:", roles_usuario, flush=True)
    print("AUTH roles_requeridos:", roles_requeridos, flush=True)
    print("AUTH uri:", request.headers.get("X-Original-URI"), flush=True)

    if not roles_requeridos:
        print("AUTH permitido: no se exige rol concreto", flush=True)
        return "", 204

    lista_roles_requeridos = [
        rol.strip()
        for rol in roles_requeridos.split(",")
        if rol.strip()
    ]

    for rol in lista_roles_requeridos:
        if rol in roles_usuario:
            print("AUTH permitido por rol:", rol, flush=True)
            return "", 204

    print("AUTH denegado", flush=True)
    return "", 403


@app.route("/")
@login_requerido
def index():
    
    return f"""
    <h1>Login correcto</h1>
    <p>Usuario: {session.get("user")}</p>
    <p>Roles: {session.get("roles")}</p>
    
    <p><a href="/usuarios">Registrar usuario</a></p>
    <p><a href="/logout">Cerrar sesión</a></p>
    """


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8888)
