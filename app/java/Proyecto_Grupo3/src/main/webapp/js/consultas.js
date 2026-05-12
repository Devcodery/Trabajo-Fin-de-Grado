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

function consultarServicio() {
    if (!servicioSeleccionadoId) {
        alert("Por favor, selecciona un servicio de la lista primero.");
        return;
    }

   	let url = `/ConsultaControlador?opcion=verServicio&idServicio=${servicioSeleccionadoId}`;
	window.location.href = window.location.origin + url;
    }

   