<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${rol == 'Cliente' }">
		<title>WEB PRIVADA CLIENTE</title>
	</c:when>
	<c:when test="${rol == 'Consultor' }">
		<title>WEB PRIVADA CONSULTOR</title>
	</c:when>
	<c:otherwise>
		<title>WEB PRIVADA ADMIN</title>
	</c:otherwise>
</c:choose>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div class="nav">
		<button class="btn">
			<a
				href="${pageContext.request.contextPath}/vistas/portalAdministrador.jsp">Salir</a>
		</button>
	</div>
	<div class="header">
		<h1>Gestión de Consultas</h1>
	</div>
			<c:choose>
				<c:when test="${rol == 'Cliente' }">
					<c:forEach var="consulta" items="${consultasClientes}">
							<div class="consulta-item" data-id="${consulta.idConsulta}" onclick="seleccionarItem(this)">
								<p class="nombre">${consulta.titulo}</p>
								<span class="estado ${consulta.estadoActual}"> </span>
							</div>
					</c:forEach>
				</c:when>
				<c:when test="${rol == 'Consultor' }">
					<c:forEach var="consulta" items="${consultasConsultor}">
						<div class="consulta-item" data-id="${consulta.idConsulta}" onclick="seleccionarItem(this)">
								<p class="nombre">${consulta.titulo}</p>
								<span class="estado ${consulta.estadoActual}"> </span>
							</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="consulta" items="${consultasAdmin}">
						<div class="consulta-item" data-id="${consulta.idConsulta}" onclick="seleccionarItem(this)">
								<p class="nombre">${consulta.titulo}</p>
								<span class="estado ${consulta.estadoActual}"> </span>
							</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
		<div class="acciones">
			<button class="btn-principal" onclick="consultarServicio()">VER
				DETALLES</button>
		</div>
		<script src="${pageContext.request.contextPath}/js/consultas.js"></script>


</body>
</html>