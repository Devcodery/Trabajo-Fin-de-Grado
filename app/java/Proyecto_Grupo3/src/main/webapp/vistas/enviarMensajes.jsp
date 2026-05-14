<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div class="nav">
		<c:choose>
			<c:when test="${rol == 'cliente' }">
				<button class="btn">
					<a href="${pageContext.request.contextPath}/ClienteControlador?opcion=logueado">Salir</a>
				</button>
			</c:when>
			<c:when test="${rol == 'consultor' }">
				<button class="btn">
					<a href="${pageContext.request.contextPath}/ConsultorControlador?opcion=logueado">Salir</a>
				</button>
			</c:when>
			<c:when test="${rol == 'admin'}">
				<button class="btn">
					<a href="${pageContext.request.contextPath}/AdministradorControlador?opcion=logueado">Salir</a>
				</button>
			</c:when>
		</c:choose>
	</div>
	<div class="header">
		<h1>MENSAJES</h1>
	</div>
	<div class="asunto-mensaje">
		<p class="asunto"></p>
	</div>
	<div class="mensaje">
		<textarea rows="100" cols="100" class="descripcion"></textarea>
	</div>
	
</body>
</html>