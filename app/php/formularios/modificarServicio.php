<?php
include '../scripts/db.php';

$idServicio = "";

if(isset($_GET['idServicio'])){
    $idServicio = intval($_GET['idServicio']);
}

$queryServicios = "SELECT * 
                    FROM servicio
                    WHERE id_servicio = $idServicio;";

$resultServicios = pg_query($conn, $queryServicios);

if (!$resultServicios) {
    die("Error al obtener los Servicios: " . pg_last_error($conn));
}
$servicios = [];
while ($row = pg_fetch_assoc($resultServicios)) {
    $servicios[] = $row;
}
pg_close($conn);

$url = 'http://info.empresa.dam.es:8055/items/sedes';
$_GET = file_get_contents($url);
$data = json_decode($_GET, true);
$sedes = [];
foreach ($data['data'] as $sede) {
    $sedes[] = ['nombre' => $sede['nombre']];
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Servicio</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="form-container">
        <header>
            <h1>Formulario de Servicio</h1>
        </header>  
        <div id="mensajeAlerta"></div>  
        <form id="formModificarServicio" action="/scripts/actualizarServicio.php" method="post">
            <input type="hidden" name="id" value="<?php echo isset($servicios[0]['id_servicio']) ? $servicios[0]['id_servicio'] : ''; ?>">
            <div class="form-group">
                <label for="nombre">Nombre del servicio:</label>
                <input type="text" id="nombre" name="nombre" value="<?php echo isset($servicios[0]['nombre']) ? $servicios[0]['nombre'] : ''; ?>">
            </div>
            <div class="form-group">
                <label for="categoria">Categoría:</label>
                <input type="text" id="categoria" name="categoria" value="<?= isset($servicios[0]['categoria']) ? $servicios[0]['categoria'] : ''?>">
            </div>

            <div class="form-group">
                <label for="sede">Sede:</label>
                <select type="text" id="id_sede" name="id_sede" value="<?= isset($servicios[0]['id_sede']) ? $servicios[0]['id_sede'] : ''?>">
                    <option value="">Seleccione una sede</option>
                    <?php foreach ($sedes as $sede): ?>
                        <?php if (isset($servicios[0]['id_sede']) && $servicios[0]['id_sede'] === $sede['id']): ?>
                            <option value="<?= $sede['id'] ?>" selected><?= $sede['nombre'] ?></option>
                        <?php else: ?>
                            <option value="<?= $sede['id'] ?>"><?= $sede['nombre'] ?></option>
                        <?php endif; ?>
                    <?php endforeach; ?>
                </select>
            </div>

            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion"><?= isset($servicios[0]['descripcion']) ? $servicios[0]['descripcion'] : ''?></textarea>
            </div>

            <div class="form-group">
                <label for="beneficios">Beneficios:</label>
                <textarea id="beneficios" name="beneficios"><?= isset($servicios[0]['beneficios']) ? $servicios[0]['beneficios'] : ''?></textarea>
            </div>

            <div class="form-group">
                <label for="tecnologias_implicadas">Tecnologías implicadas:</label>
                <textarea id="tecnologias_implicadas" name="tecnologias_implicadas"><?= isset($servicios[0]['tecnologias_implicadas']) ? $servicios[0]['tecnologias_implicadas'] : ''?></textarea>
            </div>

            <div class="form-group">
                <label for="alcance">Alcance:</label>
                <textarea id="alcance" name="alcance"><?= isset($servicios[0]['alcance']) ? $servicios[0]['alcance'] : ''?></textarea>
            </div>

            <div class="form-group">
                <label for="objetivos">Objetivos:</label>
                <textarea id="objetivos" name="objetivos"><?= isset($servicios[0]['objetivos']) ? $servicios[0]['objetivos'] : ''?></textarea>
            </div>

            <div class="form-group">
                <label for="estado">Estado:</label>
                <select id="estado" name="estado"s>
                    <?php if (isset($servicios[0]['estado']) && $servicios[0]['estado'] === 't'): ?>
                        <option value="Activo" selected>Activo</option>
                        <option value="Inactivo">Inactivo</option>
                    <?php else: ?>
                        <option value="Activo">Activo</option>
                        <option value="Inactivo" selected>Inactivo</option>
                    <?php endif; ?>
                </select>
            </div>
            
            <div class="form-actions">
                <input type="submit" value="Enviar">
                <a href="/GestionServicioControlador?opcion=listarServicios&funcion=modificar" class="btn-exit">Volver</a>
            </div>
            <script src="/js/alertServicio.js"></script>
        </form>
    </div>
</body>
</html>