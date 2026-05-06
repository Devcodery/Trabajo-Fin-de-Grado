<?php
include '../Scripts/db.php';

$queryServicios = "SELECT nombre FROM servicio where estado = true";
$resultServicios = pg_query($conn, $queryServicios);
if (!$resultServicios) {
    die("Error al obtener los servicios: " . pg_last_error($conn));
}
$servicios = [];
while ($row = pg_fetch_assoc($resultServicios)) {
    $servicios[] = $row['nombre'];
}
pg_close($conn);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/styles.css">
    <title>Solicitud</title>
</head>
<body>
    <div id="mensajeAlerta"></div> 
    <div class="form-container">
        <header>
            <h1>Registrar Nueva Solicitud</h1>
            <p class="subtitle">Completa los detalles técnicos de la solicitud.</p>
        </header>
        <form action="../Scripts/guardarSolicitud.php" method="post">
            <div class="form-group">
                <label for="titulo">Título:</label>
                <input type="text" id="titulo" name="titulo" required>
            </div>

            <div class="form-group">
                <label for="tipoDeServicio">Tipo de servicio:</label>
                <select id="tipoDeServicio" name="tipoDeServicio" required>
                    <option value="">Seleccione un tipo de servicio</option>
                    <?php foreach ($servicios as $servicio): ?>
                        <option value="<?php echo htmlspecialchars($servicio); ?>"><?php echo htmlspecialchars($servicio); ?></option>
                    <?php endforeach; ?>
                </select>
            </div>

            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required></textarea>
            </div>

            <div class="form-actions">
                <a href="index" class="btn-exit">Volver al inicio</a>
                <input type="submit" value="Guardar Solicitud">
            </div>
        </form>
    </div>
    <script src="../js/alertSolicitud.js"></script>
</body>
</html>