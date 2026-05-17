<?php
$idUsuario = "";
$usuarioNombre = "";

if($_SERVER['HTTP_X_USER_ID'] && $_SERVER['HTTP_X_USERNAME'] !== "") {
    $idUsuario = intval($_SERVER['HTTP_X_USER_ID']);
    $usuarioNombre = $_SERVER['HTTP_X_USERNAME'];
} else {
    echo "Error: Usuario no autenticado.";
    exit();
}
?>