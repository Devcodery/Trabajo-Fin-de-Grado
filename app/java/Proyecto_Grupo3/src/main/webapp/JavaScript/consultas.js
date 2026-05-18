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

    url = "";

    if (rol === 'admin') {
        let url = `/ConsultaControlador?opcion=verConsultaAdmin&idConsulta=${consultaSeleccionadoId}`;
    } else {
        let url = `/ConsultaControlador?opcion=verConsulta&idConsulta=${consultaSeleccionadoId}`;
    }	
	window.location.href = window.location.origin + url;
    }

   