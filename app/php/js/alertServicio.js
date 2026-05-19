document.addEventListener('DOMContentLoaded', function() {
    
    const formulario = document.querySelector('form');  // Selecciona el formulario
    const divAlertas = document.getElementById('mensajeAlerta');  // Donde van los mensajes
    
    // ============================================
    // FUNCIÓN PARA MOSTRAR ALERTAS
    // ============================================
    function mostrarAlerta(mensaje, tipo) {
        // Limpiar alertas anteriores
        divAlertas.innerHTML = '';
        
        // Crear el elemento de la alerta
        const alerta = document.createElement('div');
        alerta.textContent = mensaje;
        alerta.style.padding = '12px';
        alerta.style.borderRadius = '5px';
        alerta.style.marginBottom = '10px';
        alerta.style.textAlign = 'center';
        
        // Cambiar colores según el tipo
        if (tipo === 'error') {
            alerta.style.backgroundColor = '#f8d7da';  // Rojo claro
            alerta.style.color = '#721c24';            // Rojo oscuro
            alerta.style.border = '1px solid #f5c6cb';
        } else {
            alerta.style.backgroundColor = '#d4edda';  // Verde claro
            alerta.style.color = '#155724';            // Verde oscuro
            alerta.style.border = '1px solid #c3e6cb';
        }
        
        // Agregar la alerta al div
        divAlertas.appendChild(alerta);
        
        // Ocultar la alerta después de 4 segundos
        setTimeout(function() {
            alerta.style.display = 'none';
        }, 4000);
    }
    
    // ============================================
    // FUNCIÓN PARA VALIDAR (REVISAR) EL FORMULARIO
    // ============================================
    function validarFormulario() {
        
        // Obtener los valores que escribió el usuario
        const nombre = document.getElementById('nombre').value.trim();
        const categoria = document.getElementById('categoria').value.trim();
        const sede = document.getElementById('id_sede').value;
        const estado = document.getElementById('estado').value;
        const descripcion = document.getElementById('descripcion').value.trim();
        const beneficios = document.getElementById('beneficios').value.trim();
        const tecnologias_implicadas = document.getElementById('tecnologias_implicadas').value.trim();
        const alcance = document.getElementById('alcance').value.trim();
        const objetivos = document.getElementById('objetivos').value.trim();
        
        if (nombre === '') {
            mostrarAlerta('Error: El nombre es obligatorio', 'error');
            return false;
        } else if (categoria === '') {
            mostrarAlerta('Error: la categoria es obligatoria', 'error');
            return false;
        } else if (sede === '' || isNaN(sede)) {
            mostrarAlerta('Error: Debes seleccionar una sede', 'error');
            return false;
        } else if (estado === '') {
            mostrarAlerta('Error: Debes seleccionar un estado', 'error');
            return false;
        } else if (descripcion === '') {
            mostrarAlerta('Error: La descripción es obligatoria', 'error');
            return false;
        } else if (beneficios === '') {
            mostrarAlerta('Error: Los beneficios son obligatorios', 'error');
            return false;
        } else if (tecnologias_implicadas === '') {
            mostrarAlerta('Error: Las tecnologías implicadas son obligatorias', 'error');
            return false;
        } else if (alcance === '') {
            mostrarAlerta('Error: El alcance es obligatorio', 'error');
            return false;
        } else if (objetivos === '') {
            mostrarAlerta('Error: Los objetivos son obligatorios', 'error');
            return false;
        }
        
        // Si todo está bien, devolver true
        return true;
    }

    function enviarFormulario(evento) {
        // Evitar que el formulario se envíe automáticamente
        evento.preventDefault();
        const tipoServicio = formulario.getAttribute('id');
        console.log(tipoServicio);
        // Validar el formulario
        if (validarFormulario() === false) {
            return;  // Si hay errores, no enviamos
        }
        
        // Si llegamos aquí, todo está correcto
        if (tipoServicio === 'formModificarServicio') {
            mostrarAlerta('Modificando Servicio...', 'exito');
        } else if (tipoServicio === 'formInsertarServicio') {
            mostrarAlerta('Insertando Servicio...', 'exito');
        }
        // Enviar el formulario
        setTimeout(function() {
            formulario.submit();
        }, 2000);
    }
    
    // ============================================
    // ACTIVAR EL FORMULARIO
    // ============================================
    // Escuchar cuando el usuario haga clic en "Enviar"
    formulario.addEventListener('submit', enviarFormulario);
});