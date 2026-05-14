CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    passwd VARCHAR(255) NOT NULL,
    rol VARCHAR(50),
    direccion VARCHAR(255),
    departamento INT,
    sede INT
);

CREATE OR REPLACE FUNCTION registrar_usuario(
    _nombre VARCHAR,
    _apellidos VARCHAR,
    _correo VARCHAR,
    _passwd VARCHAR,
    _rol VARCHAR,
    _direccion VARCHAR,
    _departamento INT,
    _sede INT
)
RETURNS BOOLEAN
AS $$
BEGIN
    INSERT INTO usuario(nombre, apellidos, correo, passwd, rol, direccion, departamento, sede)
    VALUES (_nombre, _apellidos, _correo, crypt(_passwd, gen_salt('bf')), _rol, _direccion, _departamento, _sede);
    RETURN TRUE;
EXCEPTION
    WHEN unique_violation THEN
        RETURN FALSE;
    WHEN others THEN
        RETURN FALSE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION login_usuario(
    _login VARCHAR,
    _passwd VARCHAR
)
RETURNS BOOLEAN
AS $$
DECLARE
    v_passwd_almacenada VARCHAR(255);
BEGIN
    SELECT passwd INTO v_passwd_almacenada
    FROM usuario
    WHERE correo = _login OR nombre = _login;

    IF NOT FOUND THEN
        RETURN FALSE;
    END IF;

    IF crypt(_passwd, v_passwd_almacenada) = v_passwd_almacenada THEN
        RETURN TRUE;
    END IF;

    RETURN FALSE;
END;
$$ LANGUAGE plpgsql;
