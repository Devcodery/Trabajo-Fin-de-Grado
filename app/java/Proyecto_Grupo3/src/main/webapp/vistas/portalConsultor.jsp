<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WEB PRIVADA CONSULTOR</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>

	<div class="nav">
		<button class="btn">
			<a href="${pageContext.request.contextPath}/ConsultorControlador?opcion=logout">Salir</a>
		</button>
	</div>
	<h1 class="titulo">Portal del Consultor</h1>

	<div class="layout">
		<button class="btn-consulta">
			<a href="${pageContext.request.contextPath}/ConsultorControlador?opcion=verConsultas">Mis Consultas</a>
		</button>
	</div>
</body>
</html>