<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WEB PRIVADA CLIENTE</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<c:set var="idUsuario" value="${sessionScope.idUsuario}" />
	<div class="nav">
        <button class="btn">
        	<a href="/logout">Salir</a>	
        </button>
    </div>
    <h1 class="titulo">Portal del Cliente</h1>
    
    <div class="layout">
		<button class="btn-consulta">
			<a class="btn-consulta" href="http://consultoriatech.php.es/formularios/formularioConsulta.php?idUsuario=<c:out value="${idUsuario}"></c:out>"> Nueva Consulta</a>
		</button>
		
	</div>
	
	<div class="layout">
		<button class="btn-consulta" href="/">
			<a href="${pageContext.request.contextPath}/ClienteControlador?opcion=verConsultas">Ver Consultas</a>
		</button>
	</div>
</body>
</html>