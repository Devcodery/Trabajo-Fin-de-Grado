<?php

$host = getenv("DB_HOST");
$user = getenv("DB_USER");
$password = getenv("DB_PASSWORD");
$db = getenv("DB_NAME");
$puerto = getenv("DB_PORT");

$conn = pg_connect("host=$host port=$puerto dbname=$db user=$user password=$password");

if (!$conn) {
    die("Error de conexion: " . pg_last_error());
}

?>