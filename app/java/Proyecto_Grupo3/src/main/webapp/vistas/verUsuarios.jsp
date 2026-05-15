<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WEB PRIVADA ADMIN</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
    <div class="nav">
        <button class="btn">
            <a href="${pageContext.request.contextPath}/UsuarioControlador?opcion=gestion&rolPagina=<c:out value="${ rolPagina }"></c:out>">Volver</a>
        </button>
    </div>

    <div class="container-carrusel">
        <c:choose>
            <c:when test="${rol == 'cliente'}">
                <h2>Ver Clientes</h2>
            </c:when>
            <c:otherwise>
                <h2>Ver Consultores</h2>
            </c:otherwise>
        </c:choose>
        
        
        <div class="lista-servicios" id="listaServicios">
            <c:forEach var="user" items="${usuarios}">
                <div class="servicio-item" 
                     data-id="${user.idUsuario}" 
                     onclick="seleccionarItem(this)">
                    <p class="nombre">${user.nombre}</p>
                    <p class="idUsuario">${user.idUsuario}</p>
                </div>
            </c:forEach>
            
            <c:if test="${empty usuarios}">
                <p>No se encontraron usuarios en la base de datos.</p>
            </c:if>
        </div>

        <div class="acciones">
			<button class="btn-principal" onmouseover="ejecutarAccion('servicio')">VER DETALLES</button>
		</div>
    </div>

    <script src="${pageContext.request.contextPath}/js/"></script>
</body>
</html>