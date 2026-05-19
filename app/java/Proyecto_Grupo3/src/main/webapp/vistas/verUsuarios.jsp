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
            <c:forEach var="usuario" items="${usuarios}">

                <c:if test="${rolPagina != 'cliente'}">
                    <c:set var="sedeNombre" value="" />
                    <c:forEach var="s" items="${sedes}">
                        <c:if test="${s.idSede == usuario.sede}">
                            <c:set var="sedeNombre" value="${s.nombre}" />
                        </c:if>
                    </c:forEach>

                    <c:set var="dptoNombre" value="" />
                    <c:forEach var="d" items="${departamentos}">
                        <c:if test="${d.idDepartamento == usuario.departamento}">
                            <c:set var="dptoNombre" value="${d.nombre}" />
                        </c:if>
                    </c:forEach>
                </c:if>
                
                <div class="servicio-item" 
                     data-id="${usuario.idUsuario}" 
                     data-nombre="${usuario.nombre}"
                     data-apellidos="${usuario.apellidos}"
                     data-correo="${usuario.correo}"
                     <c:if test="${rolPagina != 'cliente'}">
                        data-departamento="${dptoNombre}"
                        data-sede="${sedeNombre}"
                     </c:if>
                     data-direccion="${usuario.direccion}"
                     onclick="abrirModalUsuario(this)">
                     
                    <p class="nombre">${usuario.nombre}</p>
                    <p class="idUsuario">${usuario.idUsuario}</p>
                </div>
            </c:forEach>
            
            <c:if test="${empty usuarios}">
                <p>No se encontraron usuarios en la base de datos.</p>
            </c:if>
        </div>
    </div>

    <jsp:include page="tarjetaUsuario.jsp"></jsp:include>

    <script src="${pageContext.request.contextPath}/JavaScript/verUsuario.js"></script>
</body>
</html>