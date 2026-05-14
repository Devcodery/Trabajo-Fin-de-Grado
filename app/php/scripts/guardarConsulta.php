<?php
include 'db.php';

$idUsuario = $_POST['id'] ?? '';
$titulo = $_POST['titulo'] ?? '';
$tipoDeServicio = $_POST['tipoDeServicio'] ?? '';
$descripcion = $_POST['descripcion'] ?? '';

$queryidServicio = "SELECT id_servicio FROM servicio WHERE nombre = '$tipoDeServicio'";
$resultadoQuery = pg_query($conn, $queryidServicio);

$idServicio = 0;
if ($resultadoQuery && pg_num_rows($resultadoQuery) > 0) {
    $idServicio = (int) pg_fetch_result($resultadoQuery, 0, 0);
}

if (!$idServicio || $idServicio === 0) {
    die("Error: No se encontró el ID del servicio.");
}
$query = "INSERT INTO consulta (titulo, id_servicio, id_cliente, descripcion, estado) VALUES ('$titulo', $idServicio, $idUsuario, '$descripcion', 'pendiente')";


$jsonDataUsuario = 'http://consultoriatech.ercilla.es/usuario/' . $idUsuario;
$ch = curl_init($jsonDataUsuario);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$respuesta = curl_exec($ch);

$correo = '';
if(curl_errno($ch)){
    echo 'Error en cURL: ' . curl_error($ch);
} else {
    $datos = json_decode($respuesta, true);
    if(isset($datos['correo'])) {
        $correo = $datos['correo'];
        curl_close($ch)
    } else {
        echo "Error: No se encontró el correo en la respuesta.";
    }
}

$jsonDataConsulta = json_encode([
    'titulo' => $titulo,
    'id_servicio' => $idServicio,
    'id_cliente' => $idUsuario,
    'descripcion' => $descripcion,
    'estado' => 'pendiente'
]);

$webhook = 'http://consultoriatech.ercilla.es/';

$ch = curl_init($webhook);
curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonDataConsulta);
$respuesta = curl_exec($ch);
if(curl_errno($ch)){
    echo 'Error en cURL: ' . curl_error($ch);
} else {
    echo "Consulta enviada al webhook correctamente.";
    curl_close($ch);
}
if(pg_query($conn, $query)) {
     pg_close($conn);
    header("Location: ../formularios/formularioConsulta.php?mensaje=exito&idUsuario=$idUsuario");
    exit();
} else {
    echo "Error al guardar el servicio: " . pg_last_error($conn);
    header("Location: ../formularios/formularioConsulta.php?mensaje=error&idUsuario=$idUsuario");
    exit();
}

_
?>