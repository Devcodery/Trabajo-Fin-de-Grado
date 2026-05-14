<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id="fondo-modal-detalle" class="fondo-modal">
    <div id="tarjeta-consultor" class="tarjeta">
		<div class="mensaje">
		<h1>ENVIAR MENSAJE</h1>
			<form class="mensaje-form">
				<label class="Asunto">Asunto:</label>
				<input type="text" placeholder="Asunto" />
				<br><br>
				<label class="Mensaje">Mensaje:</label>
				<textarea placeholder="Escribe tu mensaje aquí..."></textarea>
				<br><br>
				<button type="submit">Enviar</button>
				<button id="btn-cancelar">Cancelar</button>
			</form>
		</div>
	</div>
</div>