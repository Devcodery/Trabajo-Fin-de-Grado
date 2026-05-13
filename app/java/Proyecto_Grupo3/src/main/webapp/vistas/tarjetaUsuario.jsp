<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <c:choose>
        <c:when test="${rol == 'cliente' }">
            <title>WEB PRIVADA CLIENTE</title>
        </c:when>
        <c:when test="${rol == 'consultor' }">
            <title>WEB PRIVADA CONSULTOR</title>
        </c:when>
        <c:when test="${rol == 'admin'}">
            <title>WEB PRIVADA ADMIN</title>
        </c:when>
    </c:choose>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
    <div class="nav">
        <button class="btn">
            <a href="${pageContext.request.contextPath}/UsuarioControlador?opcion=gestion&rol=${rol}">Volver</a>
        </button>
    </div>   
    <div id="fondo-modal-detalle" class="fondo-modal">
    <div id="tarjeta-consultor" class="tarjeta">
        <c:choose>
            <c:when test="${rol == 'cliente'}"><h2>Detalle Consultor</h2></c:when>
            <c:when test="${rol == 'consultor'}"><h2>Detalle Cliente</h2></c:when>
        </c:choose>
        
        <div class="contenido-datos">
            <c:choose>
                <c:when test="${rol == 'cliente'}">
                    <div class="columna">
                        <p>Nombre: <c:out value="${consultor.nombre}" /></p>
                        <p>Apellidos: <c:out value="${consultor.apellidos}" /></p>
                        <p>Correo: <c:out value="${consultor.correo}" /></p>
                    </div>
                    <div class="columna">
                        <p>Departamento: <c:out value="${consultor.departamento}" /></p>
                        <p>Sede: <c:out value="${consultor.sede}" /></p>
                    </div>
                </c:when>

                <c:when test="${rol == 'consultor'}">
                    <div class="columna">
                        <p>Nombre: <c:out value="${cliente.nombre}" /></p>
                        <p>Apellidos: <c:out value="${cliente.apellidos}" /></p>
                    </div>
                    <div class="columna">
                        <p>Correo: <c:out value="${cliente.correo}" /></p>
                        <p>Direccion: <c:out value="${cliente.direccion}" /></p>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <button id="btn-cerrar">Cerrar</button>
    </div>
    </div>
</body>
</html>