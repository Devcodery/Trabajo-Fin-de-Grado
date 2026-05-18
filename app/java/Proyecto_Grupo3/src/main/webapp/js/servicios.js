let servicioSeleccionadoId = null;


function seleccionarItem(elemento) {
    // 1. Limpiar selecciones previas (clase CSS que pone el fondo gris/verde)
    const items = document.querySelectorAll('.servicio-item');
    items.forEach(item => item.classList.remove('seleccionado'));

    // 2. Marcar el elemento actual
    elemento.classList.add('seleccionado');

    // 3. Guardar el ID del atributo data-id
    servicioSeleccionadoId = elemento.getAttribute('data-id');
    rol = elemento.getAttribute('data-rol');
    console.log("Servicio seleccionado ID:", servicioSeleccionadoId);
    console.log("Rol:", rol);
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
            url = `/GestionServicioControlador?opcion=desactivarServicio&idServicio=${servicioSeleccionadoId}`;
            break;
        case 'activar':
            url = `/GestionServicioControlador?opcion=activarServicio&idServicio=${servicioSeleccionadoId}`;
            break;
        default:
            url = "/GestionServicioControlador?opcion=listarServicios";
    }

    window.location.href = window.location.origin + url;
}


document.getElementById('btn-abrir-filtros').addEventListener('click', function() {
    const modal = document.getElementById('modal-filtros');
    modal.classList.toggle('oculto');
});


function toggleFiltro(idElemento, isChecked) {
    const elemento = document.getElementById(idElemento);
    if (elemento) {
        elemento.disabled = !isChecked;
    }
}

function toggleFechas(isChecked) {
    document.getElementById('fechaInicio').disabled = !isChecked;
    document.getElementById('fechaFin').disabled = !isChecked;
}