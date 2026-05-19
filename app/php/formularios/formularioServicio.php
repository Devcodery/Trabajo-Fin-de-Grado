<?php
$url = 'http://info.empresa.dam.es:8055/items/sedes';
$_GET = file_get_contents($url);
$data = json_decode($_GET, true);
$sedes = [];
foreach ($data['data'] as $sede) {
    $sedes[] = ['nombre' => $sede['nombre'],
                'id' => $sede['id']
                ];
}

?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadir Nuevo Servicio</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

    <div class="form-container">
            <div id="mensajeAlerta"></div>  

        <header>
            <h1>Registrar Nuevo Servicio</h1>
            <p class="subtitle">Completa los detalles técnicos del servicio para el catálogo.</p>
        </header>

        <form id="formInsertarServicio" action="/scripts/guardarServicio.php" method="post" class="form-grid">
            <input type="hidden" name="id_sede" value="<?php echo htmlspecialchars($sede['id']); ?>">
            <div class="form-group">
                <label for="nombre">Nombre del servicio:</label>
                <input type="text" id="nombre" name="nombre" placeholder="Ej: Automatización con IA">
            </div>

            <div class="form-group">
                <label for="categoria">Categoría:</label>
                <input type="text" id="categoria" name="categoria" placeholder="Ej: Ciberseguridad">
            </div>

            <div class="form-group">
                <label for="sede">Sede:</label>
                <select id="sede" name="sede">
                    <option value="" disabled selected>Seleccione una sede</option>
                    <?php foreach ($sedes as $sede): ?>
                        <option value="<?php echo htmlspecialchars($sede['nombre']); ?>"><?php echo htmlspecialchars($sede['nombre']); ?></option>
                    <?php endforeach; ?>
                </select>
            </div>

            <div class="form-group">
                <label for="estado">Estado:</label>
                <select id="estado" name="estado">
                    <option value="" disabled selected>Seleccione un estado</option>
                    <option value="Activo">Activo</option>
                    <option value="Inactivo">Inactivo</option>
                </select>
            </div>

            <div class="form-group full-width">
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" placeholder="Describe brevemente el servicio..."></textarea>
            </div>

            <div class="form-group full-width">
                <label for="beneficios">Beneficios:</label>
                <textarea id="beneficios" name="beneficios" placeholder="¿Cualés van a ser los beneficios de los clientes?"></textarea>
            </div>

            <div class="form-group full-width">
                <label for="tecnologias_implicadas">Tecnologías implicadas:</label>
                <textarea id="tecnologias_implicadas" name="tecnologias_implicadas" placeholder="Java, Python, PHP..." ></textarea>
            </div>

            <div class="form-group">
                <label for="alcance">Alcance:</label>
                <textarea id="alcance" name="alcance" placeholder="Límites del proyecto..."></textarea>
            </div>

            <div class="form-group">
                <label for="objetivos">Objetivos:</label>
                <textarea id="objetivos" name="objetivos" placeholder="Metas principales..."></textarea>
            </div>
            
            <div class="form-actions">
                <a href="/GestionServicioControlador?opcion=gestionServicios" class="btn-exit">Volver al inicio</a>
                <input type="submit" value="Guardar Servicio">
            </div>

        </form>
    </div>

    <script src="/js/alertServicio.js"></script>
</body>
</html>