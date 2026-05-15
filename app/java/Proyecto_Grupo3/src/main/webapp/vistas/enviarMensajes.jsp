<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="fondo-modal-detalle" class="fondo-modal">
    <div id="tarjeta-consultor" class="tarjeta" role="dialog" aria-labelledby="titulo-modal">
        <div class="mensaje">
            <h1 id="titulo-modal">ENVIAR MENSAJE</h1>
            
            <form class="mensaje-form" action="${pageContext.request.contextPath}/MensajeControlador?opcion=enviarMensaje" method="post">

				<input type="hidden" name="idUsuario" value="${idUsuario}">
				<input type="hidden" name="idConsultor" value="${consultor.idConsultor}">
				
                <div class="form-group">
                    <label for="asunto" class="Asunto">Asunto:</label>
                    <input type="text" id="asunto" name="asunto" placeholder="Asunto" required />
                </div>
                
                <div class="form-group">
                    <label for="cuerpoMensaje" class="Mensaje">Mensaje:</label>
                    <textarea id="cuerpoMensaje" name="cuerpoMensaje" placeholder="Escribe tu mensaje aquí..." required></textarea>
                </div>
                
                <div class="form-acciones">
                    <button type="submit">Enviar</button>
                    <button type="button" id="btn-cancelar">Cancelar</button>
                </div>
                
            </form>
        </div>
    </div>
</div>