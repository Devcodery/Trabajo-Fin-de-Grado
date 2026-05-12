let servicioSeleccionadoId = null;


function seleccionarItem(elemento) {
    // 1. Limpiar selecciones previas (clase CSS que pone el fondo gris/verde)
    const items = document.querySelectorAll('.servicio-item');
    items.forEach(item => item.classList.remove('seleccionado'));

    // 2. Marcar el elemento actual
    elemento.classList.add('seleccionado');

    // 3. Guardar el ID del atributo data-id
    servicioSeleccionadoId = elemento.getAttribute('data-id');
    
    console.log("Servicio seleccionado ID:", servicioSeleccionadoId);
}

function ejecutarAccion(funcion) {
    if (!servicioSeleccionadoId) {
        alert("Por favor, selecciona un servicio de la lista primero.");
        return;
    }

    let url = "";

    switch (funcion) {
        case 'servicio':
            url = `/ServicioControlador?opcion=verServicio&idServicio=${servicioSeleccionadoId}`;
            break;
        case 'modificar':
            url = `/GestionServicioControlador?opcion=listarServicios&funcion=modificarServicios&modificar=true&idServicio=${servicioSeleccionadoId}`;
            break;
        case 'eliminar':
            if(confirm("¿Estás seguro de que deseas eliminar este servicio permanentemente?")) {
                url = `/GestionServicioControlador?opcion=eliminarServicio&idServicio=${servicioSeleccionadoId}`;
            } else return;
            break;
        case 'desactivar':
            url = `/ServicioControlador?opcion=desactivarServicio&idServicio=${servicioSeleccionadoId}`;
            break;
        case 'activar':
            url = `/ServicioControlador?opcion=activarServicio&idServicio=${servicioSeleccionadoId}`;
            break;
        default:
            url = "/ServicioControlador?opcion=listarServicios";
    }

    window.location.href = window.location.origin + url;
}