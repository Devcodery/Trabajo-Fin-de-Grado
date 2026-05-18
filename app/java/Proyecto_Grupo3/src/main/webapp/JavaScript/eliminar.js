function puedeEliminarServicio(estado) {
    if (estado === 'activo') {
        abrirModalAlerta();
        return false;
    }
    return true;
}

function abrirModalAlerta() {
    document.getElementById('modal-alerta-activo').classList.add('activo');
}

function cerrarModalAlerta() {
    document.getElementById('modal-alerta-activo').classList.remove('activo');
}

function abrirModalConfirmacion() {
    document.getElementById('modal-confirmacion').classList.add('activo');
}

function cerrarModalConfirmacion() {
    document.getElementById('modal-confirmacion').classList.remove('activo');
}