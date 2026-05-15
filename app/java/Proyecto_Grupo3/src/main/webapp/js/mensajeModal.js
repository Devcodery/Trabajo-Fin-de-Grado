document.addEventListener("DOMContentLoaded", function() {
    const btnAbrir = document.getElementById('btn-abrir-detalle');
    const btnCancelar = document.getElementById('btn-cancelar');
    const fondoModal = document.getElementById('fondo-modal-detalle');

    if (btnAbrir && fondoModal) {
        btnAbrir.addEventListener('click', function(e) {
            e.preventDefault();
            fondoModal.style.display = 'flex'; 
        });
    }

    if (btnCancelar && fondoModal) {
        btnCancelar.addEventListener('click', function(e) {
            e.preventDefault();
            fondoModal.style.display = 'none';
        });
    }
});