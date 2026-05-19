<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <c:choose>
            <c:when test="${rol == 'consultor'}">
                <title>WEB PRIVADA CONSULTOR</title>
            </c:when>
            <c:when test="${rol == 'cliente'}">
                <title>WEB PRIVADA CLIENTE</title>
            </c:when>
        </c:choose>

        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/style/style.css">
    </head>
    <body>
    <div class="nav">
        <button class="btn">
        	<a href="${pageContext.request.contextPath}/ConsultaControlador?opcion=verConsulta&idConsulta=${sessionScope.idConsulta}">Volver</a>	
        </button>
    </div>
        <div class="title">
            <h1>MENSAJES</h1>
        </div>
        <div class="lista-Mensajes" id="listaMensajes">
            <c:forEach var="mensaje" items="${mensajes}">
                <div class="servicio-item"
                    data-id="${mensaje.idMensaje}"
                    data-descripcion="${mensaje.contenido}"
                    data-asunto="${mensaje.asunto}"
                    onclick="seleccionarItem(this)">
                    <p class="nombre">${mensaje.asunto}</p>
                </div>
            </c:forEach>

            <c:if test="${empty mensajes}">
                <div class="estado-vacio">
                    <div class="icono-vacio"></div>
                    <h3>No hay mensajes</h3>
                    <p>Aún no has recibido ningún mensaje.</p>
                </div>
            </c:if>
        </div>

        <div class="acciones">
            <button id="btn-abrir-detalle">Enviar Mensaje</button>
            <button id="btn-abrir-mensaje">Ver Detalles</button>
            <button id="btn-borrar-mensaje">Borrar</button>
        </div>

        <jsp:include page="enviarMensajes.jsp"></jsp:include>
        <jsp:include page="verMensajes.jsp"></jsp:include>
        <jsp:include page="modelosEliminar.jsp"></jsp:include>

        <script src="${pageContext.request.contextPath}/JavaScript/mensajeModal.js"></script>
        <script src="${pageContext.request.contextPath}/JavaScript/eliminar.js"></script>
    </body>
</html>