<?php
if (!empty($_COOKIE)) {
    echo "<h3>Cookies recibidas:</h3>";
    foreach ($_COOKIE as $cookie => $valor) {
        echo htmlspecialchars($cookie) . " = " . htmlspecialchars($valor) . "<br>";
    }
} else {
    echo "No se recibieron cookies.<br>";
}

$headers = getallheaders();
if ($headers) {
    echo "<h3>Cabeceras HTTP recibidas:</h3>";
    foreach ($headers as $clave => $valor) {
        echo htmlspecialchars($clave) . ": " . htmlspecialchars($valor) . "<br>";
    }
} else {
    echo "No se pudieron obtener cabeceras.<br>";
}
?>