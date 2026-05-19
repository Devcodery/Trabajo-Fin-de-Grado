let itemSeleccionado = null;

function seleccionarItem(elemento) {
    const items = document.querySelectorAll('.servicio-item'); 
    items.forEach(item => item.classList.remove('seleccionado'));

    elemento.classList.add('seleccionado');
    

    itemSeleccionado = elemento;
    console.log("Mensaje seleccionado ID:", elemento.getAttribute('data-id'));
}

document.addEventListener("DOMContentLoaded", function() {
    const btnAbrirEnviar = document.getElementById('btn-abrir-detalle');
    const btnCancelarEnviar = document.getElementById('btn-cancelar-enviar');
    const fondoModalEnviar = document.getElementById('fondo-modal-enviar');
    
    if (btnAbrirEnviar && fondoModalEnviar) {
        btnAbrirEnviar.addEventListener('click', function(e) {
            e.preventDefault();
            fondoModalEnviar.style.display = 'flex'; 
        });
    }

    if (btnCancelarEnviar && fondoModalEnviar) {
        btnCancelarEnviar.addEventListener('click', function(e) {
            e.preventDefault();
            fondoModalEnviar.style.display = 'none';
        });
    }
    const btnAbrirVer = document.getElementById('btn-abrir-mensaje');
    const btnCerrarVer = document.getElementById('btn-cerrar-ver');
    const fondoModalVer = document.getElementById('fondo-modal-ver');
    
    const spanAsunto = document.getElementById('ver-asunto-texto');
    const spanDescripcion = document.getElementById('ver-descripcion-texto');
    
    if (btnAbrirVer && fondoModalVer) {
        btnAbrirVer.addEventListener('click', function(e) {
            e.preventDefault();
            
            if (!itemSeleccionado) {
                alert("Por favor, selecciona un mensaje de la lista primero.");
                return;
            }
            
            const asunto = itemSeleccionado.getAttribute('data-asunto');
            const descripcion = itemSeleccionado.getAttribute('data-descripcion');
            
            if (spanAsunto) spanAsunto.textContent = asunto;
            if (spanDescripcion) spanDescripcion.textContent = descripcion;

            fondoModalVer.style.display = 'flex';  
        });
    }

    if (btnCerrarVer && fondoModalVer) {
        btnCerrarVer.addEventListener('click', function(e) {
            e.preventDefault();
            fondoModalVer.style.display = 'none';
        });	
    }
    const btnBorrarMensaje = document.getElementById('btn-borrar-mensaje');

    if (btnBorrarMensaje) {
        btnBorrarMensaje.addEventListener('click', function(e) {
            e.preventDefault();
            if (!itemSeleccionado) {
                alert("Por favor, selecciona un mensaje de la lista primero.");
                return;
            }
            abrirModalConfirmacion();
        });
    }
});

function confirmarEliminar() {
    const idMensaje = itemSeleccionado.getAttribute('data-id');
    const url = `/MensajeControlador?opcion=borrarMensaje&idMensaje=${idMensaje}`;
    window.location.href = window.location.origin + url;
}