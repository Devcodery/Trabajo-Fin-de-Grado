# Departamento de Consultoría Tecnológica

## Descripción del Proyecto

Aplicación web diseñada para el Departamento de Consultoría Tecnológica. Su objetivo principal es ofrecer un catálogo público de servicios (automatización, ciberseguridad, análisis de datos, etc.) y gestionar un área privada donde los clientes pueden solicitar servicios y los consultores/administradores pueden gestionar dichas peticiones, asignaciones y mensajería interna.

El sistema utiliza una arquitectura basada en microservicios y está orquestada con Docker Compose.

## Tecnologías Utilizadas

* **Backend Principal (Java):** Java 21, Tomcat 9, Servlets, JSP y Maven.
* **Backend Secundario (PHP):** PHP 8.2 (con driver de PostgreSQL) para la gestión de formularios.
* **API de Autenticación (Flask):** Python 3.12, Flask, psycopg2. Gestiona el registro y login (con verificación de sesión para proxy inverso).
* **Base de Datos:** PostgreSQL 16 (dos instancias separadas: una para datos de negocio y otra para usuarios).
* **Automatización:** n8n para flujos de trabajo (ej. notificaciones por correo).
* **Despliegue:** Docker y Docker Compose.

## Requisitos Previos

* **Docker y Docker Compose** instalados en el sistema.
* **Java 21 y Maven** (necesarios para compilar el proyecto Java y generar el `.war`).
* Disponer de los puertos `6644`, `7788`, `8001`, `8383`, `9335`, `9336` y `5678` libres en el host.

## Variables de Entorno

El proyecto requiere dos archivos `.env` distintos.

**1. Archivo `/app/.env`** (Base de datos principal, Java y PHP):

```env
POSTGRES_DB=consultoriatecnologia
POSTGRES_USER=postgres
POSTGRES_PASSWORD=1234
DB_HOST=db
DB_PORT=5432
DB_NAME=consultoriatecnologia
DB_USER=postgres
DB_PASSWORD=1234
```

**2. Archivo `/flask/.env`** (Autenticación y BD de usuarios):

```env
# Usuario administrador local de Flask
USUARIO=admin
PASSWD=admin123
SECRET=misecreto

# PostgreSQL (db-users)
POSTGRES_DB=authdb
POSTGRES_USER=postgres
POSTGRES_PASSWORD=1234
DB_HOST=db-users
DB_PORT=5432
```

## Pasos para el Despliegue

El despliegue se realiza mediante una combinación de comandos por terminal (para el ciclo de vida de los contenedores) y Portainer (para la gestión de redes y conectividad entre servicios).

1. **Crear la red externa:** El archivo de `docker-compose.yml` de la aplicación principal espera que exista una red externa llamada `npm` (pensada para Nginx Proxy Manager).

   ```bash
   docker network create npm
   ```
2. **Compilar el proyecto Java:** Es obligatorio generar el paquete `.war` antes de levantar los servicios.

   ```bash
   cd app/java/Proyecto_Grupo3
   mvn clean package
   cd ../../../
   ```

   *Esto generará el archivo `target/ROOT.war` que Tomcat montará como volumen.*
3. **Levantar el servicio de Autenticación (Flask):**

   ```bash
   cd flask
   docker-compose up -d
   cd ..
   ```
4. **Levantar los servicios principales (Java, PHP, BD, n8n):**

   ```bash
   cd app
   docker-compose up -d
   ```
5. **Conexión de microservicios con Portainer:**
   Dado que los servicios están en stacks de Docker Compose diferentes, es necesario utilizar **Portainer** para conectarlos a la misma red:

   * Accede a Portainer y localiza el contenedor `flask-flaskapp-1`.
   * En la sección de **Connected Networks**, une el contenedor a la red `npm` o a la red por defecto del stack principal (ej. `app_default`).
   * Esto permitirá que el backend de Java resuelva el nombre de host `flask-flaskapp-1`.

## Puertos Utilizados

El proyecto maneja dos conjuntos de puertos dependiendo de la fase de despliegue:

### 1. Puertos de prueba (Locales sin Nginx)

Estos puertos están mapeados hacia el host en los archivos `docker-compose.yml` para facilitar la verificación inicial de los servicios:

* **7788:** Tomcat (Aplicación Java principal).
* **8001:** Tomcat (Puerto de depuración JPDA).
* **6644:** Apache/PHP (Formularios e inserción de datos).
* **8383:** Flask (API de Autenticación y gestión de usuarios).
* **9335:** PostgreSQL principal (Base de datos del negocio).
* **9336:** PostgreSQL usuarios (Base de datos de Flask).
* **5678:** n8n (Orquestación y automatización).

### 2. Puertos internos (Configuración Nginx)

Cuando se utiliza el proxy inverso para producción, Nginx se comunica con los contenedores a través de sus puertos internos:

* **8080:** Tomcat (Servicio Java).
* **80:** Apache (Servicio PHP).
* **8888:** Flask (API de Autenticación).
* **5678:** n8n (Automatización).
* **5432:** PostgreSQL (Bases de datos).

## Rutas y URL de Acceso

La aplicación está unificada bajo un único dominio gracias al uso de un proxy inverso (`Nginx`). A continuación se detalla el mapeo de rutas hacia los microservicios internos, sus puertos y los roles con permiso de acceso:

### 1. Backend Principal (Java) - `app-tomcat-1:8080`

Gestiona la lógica de negocio principal y las vistas dinámicas.

| Ruta                            | Rol de Acceso             | Descripción                                 |
| :------------------------------ | :------------------------ | :------------------------------------------- |
| `/IndexControlador`           | Cualquiera                | Página de inicio y acceso público.         |
| `/ServicioControlador`        | Cualquiera                | Listado y visualización de servicios.       |
| `/style`                      | Cualquiera                | Estilos CSS globales de la aplicación Java. |
| `/ConsultaControlador`        | Admin, Consultor, Cliente | Gestión de consultas/tickets.               |
| `/AutenticadorControlador`    | Admin, Consultor, Cliente | Lógica de validación de sesión.           |
| `/vistas`                     | Admin, Consultor, Cliente | Acceso a directorios de vistas JSP.          |
| `/JavaScript`                 | Admin, Consultor, Cliente | Scripts JS del backend Java.                 |
| `/MensajeControlador`         | Consultor, Cliente        | Sistema de mensajería interna.              |
| `/ClienteControlador`         | Cliente                   | Panel y acciones exclusivas del cliente.     |
| `/ConsultorControlador`       | Consultor                 | Panel y acciones exclusivas del consultor.   |
| `/AdministradorControlador`   | Admin                     | Panel de administración principal.          |
| `/GestionServicioControlador` | Admin                     | Configuración y gestión de servicios.      |
| `/UsuarioControlador`         | Admin                     | Gestión administrativa de perfiles.         |

### 2. Microservicio de Autenticación (Python/Flask) - `flask-flaskapp-1:8888`

Encargado de la seguridad, registro y gestión de identidades en la base de datos de usuarios.

| Ruta          | Rol de Acceso             | Descripción                                             |
| :------------ | :------------------------ | :------------------------------------------------------- |
| `/login`    | Cualquiera                | Endpoint de inicio de sesión.                           |
| `/logout`   | Cualquiera                | Cierre de sesión y limpieza de tokens.                  |
| `/usuario`  | Admin, Consultor, Cliente | Información del perfil del usuario actual.              |
| `/registro` | Admin                     | Creación de nuevos usuarios (solo personal autorizado). |
| `/usuarios` | Admin                     | Listado completo de usuarios del sistema.                |
| `/borrar`   | Admin                     | Eliminación de registros de usuario.                    |
| `/static`   | Cualquiera                | Archivos estáticos internos (Acceso restringido).       |

### 3. Servidor Web y Recursos (PHP/Apache) - `app-web-1:80`

Aloja los recursos front-end estáticos y los formularios de inserción rápida.

| Ruta                                    | Rol de Acceso             | Descripción                                     |
| :-------------------------------------- | :------------------------ | :----------------------------------------------- |
| `/css`, `/js`, `/scripts`         | Admin, Consultor, Cliente | Recursos estáticos (estilos y lógica cliente). |
| `/formularios/formularioConsulta.php` | Cliente                   | Formulario específico para nuevas solicitudes.  |
| `/formularios`                        | Admin                     | Acceso a la gestión de archivos de formularios. |

## Usuarios de Prueba

Para ingresar al sistema por primera vez y administrar la plataforma, utiliza las credenciales definidas en `flask/.env`:

* **Rol:** Administrador
* **Usuario:** `admin`
* **Contraseña:** `admin123`

Desde el panel de administración podrás crear usuarios con roles de `cliente` y `consultor`.

## Carga de la Base de Datos

No es necesario ejecutar scripts manualmente para inicializar las bases de datos.

* Al levantar los contenedores, las imágenes de Postgres ejecutarán automáticamente los archivos `init.sql` alojados en `app/database/init.sql` y `flask/db/init.sql`.
* Estos scripts generan las tablas necesarias (`servicio`, `consulta`, `mensajes`, `usuario`) y cargan datos de prueba por defecto (servicios activos).

## Explicación Básica de los Servicios

* **web (PHP/Apache):** Se encarga de procesar los formularios de "Añadir/Modificar Servicio" y "Nueva Solicitud (Consulta)". Conecta directamente a la BD principal e invoca webhooks de n8n para enviar correos.
* **tomcat (Java):** Contiene toda la lógica de gestión (DAO, Servlets y Controladores). Genera las vistas JSP para clientes, consultores y administradores.
* **flaskapp (Python):** Gestiona la validación de usuarios (criptografía y contraseñas), creación de sesiones y provee endpoints internos JSON (ej: `/usuarios`) consumidos por el backend de Java.
* **db / db-users (PostgreSQL):** Almacenan de manera independiente el modelo de negocio (servicios, mensajería) y las identidades (usuarios, roles, contraseñas hasheadas).
* **n8n:** Integrado para capturar peticiones (webhooks) desde PHP y procesar el envío automatizado de correos electrónicos.

## Posibles Problemas Conocidos Durante el Despliegue

1. **Error 404 al acceder a Java:** Si visitas `http://localhost:7788/` y da un error 404, significa que Tomcat no encontró la aplicación. Verifica que has ejecutado `mvn clean package` en la carpeta Java y que el archivo `ROOT.war` existe en `app/java/Proyecto_Grupo3/target/`.
3. **Comunicación interna entre Java y Flask:** El backend de Java hace peticiones HTTP a `http://flask-flaskapp-1:8888`. Dado que Flask se levanta en un stack independiente, el contenedor de Java no podrá alcanzarlo inicialmente. **Solución:** Utiliza Portainer para añadir manualmente el contenedor `flask-flaskapp-1` a la red de la aplicación principal o a la red `npm`.
4. **Dependencia de API Externa:** Algunos formularios y vistas de Java dependen de llamadas a `http://info.empresa.dam.es:8055` para obtener los listados de sedes y departamentos. Si este servidor no está operativo o la red local bloquea la conexión, estos menús desplegables podrían fallar o aparecer vacíos.local bloquea la conexión, estos menús desplegables podrían fallar o aparecer vacíos.
