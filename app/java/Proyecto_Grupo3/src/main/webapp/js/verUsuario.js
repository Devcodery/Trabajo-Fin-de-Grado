let usuarioSeleccionadoId = null;
let consultaSeleccionadaId = null;

document.addEventListener("DOMContentLoaded", function() {
    const btnAbrir = document.getElementById('btn-abrir-detalle');
    const btnCerrar = document.getElementById('btn-cerrar');
    const fondoModal = document.getElementById('fondo-modal-detalle').style.display = 'flex';
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