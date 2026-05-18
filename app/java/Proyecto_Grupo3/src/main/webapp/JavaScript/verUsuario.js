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

function abrirModalUsuario(elemento) {
    const nombre = elemento.getAttribute('data-nombre') || '';
    const apellidos = elemento.getAttribute('data-apellidos') || '';
    const correo = elemento.getAttribute('data-correo') || '';
    const departamento = elemento.getAttribute('data-departamento') || '';
    const sede = elemento.getAttribute('data-sede') || '';
    const direccion = elemento.getAttribute('data-direccion') || '';

    document.getElementById('modal-nombre').textContent = nombre;
    document.getElementById('modal-apellidos').textContent = apellidos;
    document.getElementById('modal-correo').textContent = correo;
    

    const pDepto = document.getElementById('p-departamento');
    if (departamento) {
        document.getElementById('modal-departamento').textContent = departamento;
        pDepto.style.display = 'block';
    } else {
        pDepto.style.display = 'none';
    }

    const pSede = document.getElementById('p-sede');
    if (sede) {
        document.getElementById('modal-sede').textContent = sede;
        pSede.style.display = 'block';
    } else {
        pSede.style.display = 'none';
    }

    const pDir = document.getElementById('p-direccion');
    if (direccion) {
        document.getElementById('modal-direccion').textContent = direccion;
        pDir.style.display = 'block';
    } else {
        pDir.style.display = 'none';
    }

    const fondoModal = document.getElementById('fondo-modal-detalle');
    if (fondoModal) {
        fondoModal.style.display = 'flex';
    }
}