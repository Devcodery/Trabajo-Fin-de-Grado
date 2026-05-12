<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
    <c:when test="${rol == 'admin'}">
        <title>WEB PRIVADA ADMIN</title>
    </c:when>
    <c:otherwise>
        <title>WEB PUBLICA</title>
    </c:otherwise>
</c:choose>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div class="nav">
        <button class="btn">
        	<a href="${pageContext.request.contextPath}/ConsultaControlador?opcion=gestionConsultas${rol}">Volver</a>	
        </button>
    </div>
	
    <table>
        <thead>
            <tr>
                <th>Nombre</th>            
               <th>Descripción</th>
                <th>Fecha de Creación</th>
                <th>Estado Actual</th>
            </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${consulta.titulo}"></c:out></td>
            <td><c:out value="${consulta.descripcion}"></c:out></td>
            <td><c:out value="${consulta.fechaCreacion}"></c:out></td>
            <td><c:out value="${consulta.estadoActual}"></c:out></td>
        </tr>
        </tbody>
    </table>
</body>
</html>