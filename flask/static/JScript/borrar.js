let usuarioSeleccionadoId = null;

function seleccionarUsuario(elemento) {
    const items = document.querySelectorAll('.servicio-item');
    items.forEach(item => item.classList.remove('seleccionado'));

    elemento.classList.add('seleccionado');

    usuarioSeleccionadoId = elemento.getAttribute('data-id');
}

function intentarBorrar() {
    if (!usuarioSeleccionadoId) {
        alert("Por favor, selecciona un usuario de la lista primero.");
        return;
    }

    document.getElementById('input-id-usuario').value = usuarioSeleccionadoId;

    document.getElementById('modal-confirmacion').classList.add('activo');
}

function cerrarModalConfirmacion() {
    document.getElementById('modal-confirmacion').classList.remove('activo');
}