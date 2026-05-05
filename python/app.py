from datetime import timedelta
from functools import wraps
from urllib.parse import urlparse
import os

import psycopg2
from dotenv import load_dotenv
from flask import Flask, render_template, request, redirect, url_for, session

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
            return cur.fetchone()[0]


def registrar_usuario(nombre, correo, passwd):
    """Registra un usuario normal usando la función SQL registrar_usuario."""
    with get_conn() as conn:
        with conn.cursor() as cur:
            cur.execute(
                "SELECT registrar_usuario(%s, %s, %s);",
                (nombre, correo, passwd),
            )
            return cur.fetchone()[0]


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


@app.route("/form", methods=["GET"])
def form():
    """Formulario sencillo de registro."""
    return render_template("formulario.html")


@app.route("/registro", methods=["POST"])
def registro():
    nombre = request.form.get("nombre")
    correo = request.form.get("email")
    passwd = request.form.get("passwd")

    if registrar_usuario(nombre, correo, passwd):
        return redirect(url_for("login"))

    return "No se pudo registrar el usuario. Revisa si ya existe.", 400


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

            if next_url_segura(next_url):
                return redirect(next_url)
            return redirect(url_for("index"))

        # Usuarios normales registrados en PostgreSQL.
        if login_usuario(user, passwd):
            session.permanent = True
            session["login"] = True
            session["user"] = user
            session["roles"] = ["cliente"]

            if next_url_segura(next_url):
                return redirect(next_url)
            return redirect(url_for("index"))

        error = "Usuario o contraseña incorrectos"

    next_url = request.args.get("next", "/")
    return render_template("login.html", error=error, next_url=next_url)


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
    <p><a href="/form">Registrar usuario</a></p>
    <p><a href="/logout">Cerrar sesión</a></p>
    """


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8888)
