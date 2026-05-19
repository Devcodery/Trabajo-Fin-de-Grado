let consultaSeleccionadoId = null;
let rol = null;

function seleccionarItem(elemento) {
    const items = document.querySelectorAll('.consulta-item');
    items.forEach(item => item.classList.remove('seleccionado'));

    elemento.classList.add('seleccionado');

    consultaSeleccionadoId = elemento.getAttribute('data-id');
    rol = elemento.getAttribute('data-rol');
}

function consultarConsulta() {
    if (!consultaSeleccionadoId) {
        alert("Por favor, selecciona una consulta de la lista primero.");
        return;
    }

    let url = "";

    if (rol === 'admin') {
        url = `/ConsultaControlador?opcion=verConsultaAdmin&idConsulta=${consultaSeleccionadoId}`;
    } else {
        url = `/ConsultaControlador?opcion=verConsulta&idConsulta=${consultaSeleccionadoId}`;
    }	
	window.location.href = window.location.origin + url;
    }

const btnAbrirFiltros = document.getElementById('btn-abrir-filtros');
if (btnAbrirFiltros) {
    btnAbrirFiltros.addEventListener('click', function() {
        const modal = document.getElementById('modal-filtros');
        if (modal) {
            modal.classList.toggle('oculto');
        }
    });
}

function toggleFiltro(idElemento, isChecked) {
    const elemento = document.getElementById(idElemento);
    if (elemento) {
        elemento.disabled = !isChecked;
    }
}

function toggleFechas(isChecked) {
    const fechaInicio = document.getElementById('fechaInicio');
    const fechaFin = document.getElementById('fechaFin');
    
    if (fechaInicio) fechaInicio.disabled = !isChecked;
    if (fechaFin) fechaFin.disabled = !isChecked;
}