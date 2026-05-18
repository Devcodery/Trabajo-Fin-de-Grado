let servicioSeleccionadoId = null;
let servicioSeleccionadoEstado = null;

function seleccionarItem(elemento) {
    const items = document.querySelectorAll('.servicio-item');
    items.forEach(item => item.classList.remove('seleccionado'));

    elemento.classList.add('seleccionado');

    servicioSeleccionadoId = elemento.getAttribute('data-id');
    rol = elemento.getAttribute('data-rol');
    servicioSeleccionadoEstado = elemento.getAttribute('data-estado');
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
            if (!puedeEliminarServicio(servicioSeleccionadoEstado)) {
                return; 
            }

            abrirModalConfirmacion();
            return;
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

function confirmarEliminarServicio() {
    const url = `/GestionServicioControlador?opcion=eliminarServicio&idServicio=${servicioSeleccionadoId}`;
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