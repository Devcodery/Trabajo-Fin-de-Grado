<?php
include 'db.php';
    
$id = $_POST['id'];
$nombre = $_POST['nombre'];
$categoria = $_POST['categoria'];
$sede = $_POST['sede'];
$descripcion = $_POST['descripcion'];
$beneficios = $_POST['beneficios'];
$tecnologias_implicadas = $_POST['tecnologias_implicadas'];
$alcance = $_POST['alcance'];
$objetivos = $_POST['objetivos'];
$estado = $_POST['estado'];

    
if($estado == 'Activo') {
    $estado = 'true';
} else {
    $estado = 'false';
}

$sql = "UPDATE servicio 
        SET nombre='$nombre', categoria='$categoria', sede='$sede', descripcion='$descripcion', beneficios='$beneficios', tecnologias_implicadas='$tecnologias_implicadas', alcance='$alcance', objetivos='$objetivos', estado='$estado' 
        WHERE id_servicio=$id";

    if (pg_query($conn, $sql)) {
        header("Location: ../formularios/modificarServicio.php?idServicio=$id&mensaje=exito");
    } else {
        header("Location: ../formularios/modificarServicio.php?idServicio=$id&mensaje=error");
    }
    pg_close($conn);
?>




