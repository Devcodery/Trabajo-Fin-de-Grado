<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">
<title>WEB PRIVADA ADMIN</title>
</head>
<body>
    <div class="nav">
        <button class="btn">
        	<a>Volver</a>	
        </button>
    </div>
    <div class="header">
		<h1>Portal del Administrador</h1>
	</div>
    <div class="layout">
        <table>
            <tr>
                <c:choose>
                    <c:when test="${rol = 'cliente'}">
                        <button class="btn">
                            <a href="/registro/<c:out value="${ rol }"></c:out>">Crear Clientes</a>	
                        </button>
                    </c:when>
                    <c:otherwise>
                         <button class="btn">
                            <a href="/registro/<c:out value="${ rol }"></c:out>">Crear Consultor</a>	
                        </button>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr>
            <c:choose>
                <c:when test="${rol = 'cliente'}">
                    <button class="btn">
                        <a href="${pageContext.request.contextPath}/UsuarioControlador?opcion=verusuarios&rol=<c:out value="${ rol }"></c:out>">Ver Clientes</a>	
                    </button>
                </c:when>
                <c:otherwise>
                    <button class="btn">
                        <a href="${pageContext.request.contextPath}/UsuarioControlador?opcion=verusuarios&rol=<c:out value="${ rol }"></c:out>">Ver Consultor</a>	
                    </button>
                </c:otherwise>
            </c:choose>
            </tr>  
        </table>
    </div>

</body>
</html>