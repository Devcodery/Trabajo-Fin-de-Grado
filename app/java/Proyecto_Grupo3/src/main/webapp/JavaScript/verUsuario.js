let usuarioSeleccionadoId = null;

document.addEventListener("DOMContentLoaded", function() {
    const btnAbrir = document.getElementById('btn-abrir-detalle');
    const btnCerrar = document.getElementById('btn-cerrar');
    const fondoModal = document.getElementById('fondo-modal-detalle');

    if (fondoModal && fondoModal.getAttribute('data-mostrar') === 'true') {
        fondoModal.style.display = 'flex'; 
    }

    if (btnAbrir && fondoModal) {
        btnAbrir.addEventListener('click', function() {
            fondoModal.style.display = 'flex'; 
        });
    }

    if (btnCerrar && fondoModal) {
        btnCerrar.addEventListener('click', function() {
            fondoModal.style.display = 'none';
        });
    }
});

function seleccionarItem(elemento) {
    const items = document.querySelectorAll('.servicio-item');
    items.forEach(item => item.classList.remove('seleccionado'));
    
    elemento.classList.add('seleccionado');
    usuarioSeleccionadoId = elemento.getAttribute('data-id');
}

function ejecutarAccion(funcion, rolPagina) {
    if (!usuarioSeleccionadoId) {
        alert("Por favor, selecciona un usuario de la lista primero.");
        return;
    }

    if (funcion === 'ver_detalle') {
        window.location.href = `/UsuarioControlador?opcion=verusuarios&rolPagina=${rolPagina}&idUsuario=${usuarioSeleccionadoId}`;
    }
}