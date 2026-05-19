<?php
include 'db.php';

$usuarioNombre = $_POST['usuarioNombre'] ?? '';
$idUsuario = $_POST['idUsuario'] ?? 0;
$titulo = $_POST['titulo'] ?? '';
$tipoDeServicio = $_POST['tipoDeServicio'] ?? '';
$descripcion = $_POST['descripcion'] ?? '';
$idiomaCorreo = $_POST['idioma'] ?? 'es';

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
    header("Location: ../formularios/formularioConsulta.php?mensaje=error");
    exit();
}

$url = 'http://flask-flaskapp-1:8888/usuario/' . $idUsuario;


$cookieSesion = isset($_COOKIE['session']) ? 'session=' . $_COOKIE['session'] : '';

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

if ($cookieSesion) {
    curl_setopt($ch, CURLOPT_COOKIE, $cookieSesion); 
}
$respuestaJson = curl_exec($ch);
curl_close($ch);
$data = json_decode($respuestaJson, true);
$correoUsuario = $data['correo'] ?? 'Correo no encontrado';

$jsonDataConsulta = json_encode([
    'titulo' => $titulo,
    'tipo_de_Servicio' => $tipoDeServicio,
    'nombre' => $usuarioNombre,
    'descripcion' => $descripcion,
    'estado' => 'pendiente',
    'correo' => $correoUsuario,
    'idioma' => $idiomaCorreo
]);

$webhook = 'http://n8n-app:5678/webhook/correo';

$ch = curl_init($webhook);
curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonDataConsulta);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json')); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$respuesta = curl_exec($ch);
if(curl_errno($ch)){
    echo 'Error en cURL: ' . curl_error($ch);
}
header("Location: /formularios/formularioConsulta.php?mensaje=exito");
exit();
?>