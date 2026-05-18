let usuarioSeleccionadoId = null;
let consultaSeleccionadaId = null;

document.addEventListener("DOMContentLoaded", function() {
    const btnAbrir = document.getElementById('btn-abrir-detalle');
    const btnCerrar = document.getElementById('btn-cerrar');
    const fondoModal = document.getElementById('fondo-modal-detalle');

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

    if (typeof abrirModalAuto !== 'undefined' && abrirModalAuto && fondoModal) {
        fondoModal.style.display = 'flex';
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
        // Recargamos la misma página de la lista, pero pasándole el ID que queremos ver en el modal
        window.location.href = `/UsuarioControlador?opcion=verusuarios&rolPagina=${rolPagina}&idUsuario=${usuarioSeleccionadoId}`;
    }
}

// 3. Control del Modal
// document.addEventListener("DOMContentLoaded", function() {
//     const btnCerrar = document.getElementById('btn-cerrar');
//     const fondoModal = document.getElementById('fondo-modal-detalle');

//     if (btnCerrar && fondoModal) {
//         btnCerrar.addEventListener('click', function() {
//             fondoModal.style.display = 'none';
//         });
//     }

//     // NUEVO: Si Java nos manda la señal de que hay datos, auto-abrimos el modal
//     if (typeof abrirModalAuto !== 'undefined' && abrirModalAuto && fondoModal) {
//         fondoModal.style.display = 'flex';
//     }
// });