let consultaSeleccionadoId = null;
let rol = null;

function seleccionarItem(elemento) {
    // 1. Limpiar selecciones previas (clase CSS que pone el fondo gris/verde)
    const items = document.querySelectorAll('.consulta-item');
    items.forEach(item => item.classList.remove('seleccionado'));

    // 2. Marcar el elemento actual
    elemento.classList.add('seleccionado');

    // 3. Guardar el ID del atributo data-id
    consultaSeleccionadoId = elemento.getAttribute('data-id');
    rol = elemento.getAttribute('data-rol');
    console.log("Consulta seleccionada ID:", consultaSeleccionadoId);
    console.log("Rol:", rol);
}

function consultarConsulta() {
    if (!consultaSeleccionadoId) {
        alert("Por favor, selecciona una consulta de la lista primero.");
        return;
    }

   	let url = `/ConsultaControlador?opcion=verConsulta&idConsulta=${consultaSeleccionadoId}`;
	window.location.href = window.location.origin + url;
    }

   