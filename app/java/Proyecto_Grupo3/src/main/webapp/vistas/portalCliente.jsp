<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portal Cliente</title>
<link rel="stylesheet" type="text/css" href="../style/style.css">
</head>
<body>
	<div class="nav">
        <button class="btn">Volver</button>
    </div>
    <h1 class="titulo">Portal del Cliente</h1>
    
    <div class="layout">
		<button class="btn-consulta"></button>
		<a class="btn-consulta" href="http://localhost:6644/formularios/formularioConsulta.php?idUsuario=<c:out value="${id}"></c:out>"> Nueva Consulta</a>
	</div>
	
	<div class="layout">
		<button class="btn-consulta" href="/">Mis Consultas</button>
	</div>
</body>
</html>