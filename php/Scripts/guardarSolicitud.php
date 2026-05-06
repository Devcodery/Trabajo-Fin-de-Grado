<?php
include 'db.php';

$titulo = $_POST['titulo'] ?? '';
$tipoDeServicio = $_POST['tipoDeServicio'] ?? '';
$descripcion = $_POST['descripcion'] ?? '';

$queryidServicio = "SELECT id FROM servicio WHERE nombre = '$tipoDeServicio'";
$idServicio = (int)pg_query($conn, $queryidServicio);



if (!$idServicio) {
    die("Error al obtener el ID del servicio: " . pg_last_error($conn));
}


$query = "INSERT INTO consulta (titulo, id_servicio, descripcion, estado) VALUES ('$titulo', $idServicio, '$descripcion', 'pendiente')";

if(pg_query($conn, $query)) {
     pg_close($conn);
    header("Location: ../formularios/formularioServicio.php?mensaje=exito");
    exit();
} else {
    echo "Error al guardar el servicio: " . pg_last_error($conn);
    header("Location: ../formularios/formularioServicio.php?mensaje=error");
    exit();
}
?>