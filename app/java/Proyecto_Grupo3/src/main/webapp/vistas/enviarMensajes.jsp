<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="fondo-modal-enviar" class="fondo-modal"> <div id="tarjeta-enviar" class="tarjeta" role="dialog" aria-labelledby="titulo-enviar">
        <div class="mensaje">
            <h1 id="titulo-enviar">ENVIAR MENSAJE</h1>
            
            <form class="mensaje-form" action="${pageContext.request.contextPath}/MensajeControlador" method="post">
                <input type="hidden" name="opcion" value="enviarMensaje"> 
                <input type="hidden" name="idConsulta" value="${consulta.idConsulta}">
                
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
                    <button type="button" id="btn-cancelar-enviar">Cancelar</button> </div>
            </form>
        </div>
    </div>
</div>