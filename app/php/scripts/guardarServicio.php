<?php
include 'db.php';

$nombre = $_POST['nombre'] ?? '';
$categoria = $_POST['categoria'] ?? '';
$sede = $_POST['sede'] ?? '';
$beneficios = $_POST['beneficios'] ?? '';
$tecnologias_implicadas = $_POST['tecnologias_implicadas'] ?? '';
$alcance = $_POST['alcance'] ?? '';
$objetivo = $_POST['objetivo'] ?? '';
$descripcion = $_POST['descripcion'] ?? '';
$estado = $_POST['estado'] ?? '';

if($estado == 'Activo') {
    $estado = 'true';
} else {
    $estado = 'false';
}

$query = "INSERT INTO servicio (nombre, descripcion, categoria, sede, beneficios, tecnologias_implicadas, alcance, objetivos, estado)
 VALUES ('$nombre', '$descripcion', '$categoria', '$sede', '$beneficios', '$tecnologias_implicadas', '$alcance', '$objetivo', $estado);";

if(pg_query($conn, $query)) {
     pg_close($conn);
    header("Location: /formularios/formularioServicio.php?mensaje=exito");
    exit();
} else {
    echo "Error al guardar el servicio: " . pg_last_error($conn);
    header("Location: /formularios/formularioServicio.php?mensaje=error");
    exit();
}



?>