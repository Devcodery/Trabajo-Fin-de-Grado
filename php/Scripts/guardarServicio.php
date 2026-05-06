<?php
include 'db.php';

$nombre = $_POST['nombre'] ?? '';
$categoria = $_POST['categoria'] ?? '';
$sede = $_POST['sede'] ?? '';
$beneficios = $_POST['beneficios'] ?? '';
$tecnologias_aplicadas = $_POST['tecnologias_aplicadas'] ?? '';
$alcance = $_POST['alcance'] ?? '';
$objetivo = $_POST['objetivo'] ?? '';
$descripcion = $_POST['descripcion'] ?? '';
$estado = $_POST['estado'] ?? '';

if($estado == 'Activo') {
    $estado = 'true';
} else {
    $estado = 'false';
}

$query = "INSERT INTO servicios (nombre, descripcion, categoria, sede, beneficios, tecnologias_aplicadas, alcance, objetivo, estado)
 VALUES ('$nombre', '$descripcion', '$categoria', '$sede', '$beneficios', '$tecnologias_aplicadas', '$alcance', '$objetivo', $estado);";

if(pg_query($conn, $query)) {
    echo "Servicio guardado correctamente.";
} else {
    echo "Error al guardar el servicio: " . pg_last_error($conn);
}

pg_close($conn);

?>