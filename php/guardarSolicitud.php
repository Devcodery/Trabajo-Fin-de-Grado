<?php
include 'db.php';

$titulo = $_POST['titulo'] ?? '';
$tipoDeServicio = $_POST['tipoDeServicio'] ?? '';
$descripcion = $_POST['descripcion'] ?? '';

$idServicio = "SELECT id FROM servicios WHERE nombre = '$tipoDeServicio'";

$query = "INSERT INTO solicitudes (titulo, idServicio, descripcion, estado) VALUES ('$titulo', $idServicio, '$descripcion', 'pendiente')";

if(pg_query($conn, $query)) {
    echo "Solicitud guardada correctamente.";
} else {
    echo "Error al guardar la solicitud: " . pg_last_error($conn);
}
?>