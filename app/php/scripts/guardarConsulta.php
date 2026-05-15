<?php
include 'db.php';

$usuarioNombre = $_POST['usuarioNombre'] ?? '';
$idUsuario = $_POST['idUsuario'] ?? 0;
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

if(pg_query($conn, $query)) {
     pg_close($conn);
} else {
    echo "Error al guardar el servicio: " . pg_last_error($conn);
    header("Location: ../formularios/formularioConsulta.php?mensaje=error&idUsuario=$idUsuario");
    exit();
}

// $_GET = file_get_contents($url);
// $opciones = [
//     'http' => [
//         'method' => 'GET',
//         'header' => "Cookie: " . ($_SERVER['HTTP_COOKIE'])
//     ]
// ];
// $respuestaUsuario = file_get_contents($url, false, stream_context_create($opciones));
// $data = json_decode($respuestaUsuario, true);

$jsonDataConsulta = json_encode([
    'titulo' => $titulo,
    'tipo_de_Servicio' => $tipoDeServicio,
    'nombre' => $usuarioNombre,
    'descripcion' => $descripcion,
    'estado' => 'pendiente',
    'correo' => $data['correo']
]);

$webhook = 'http://n8n-app:5678/webhook-test/correo';

$ch = curl_init($webhook);
curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonDataConsulta);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json')); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$respuesta = curl_exec($ch);
if(curl_errno($ch)){
    echo 'Error en cURL: ' . curl_error($ch);
} else {
    echo "Consulta enviada al webhook correctamente.";
}
header("Location: /formularios/formularioConsulta.php?mensaje=exito&idUsuario=$idUsuario");
exit();
?>