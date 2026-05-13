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
	<c:otherwise>
		<title>WEB PRIVADA ADMIN</title>
	</c:otherwise>
</c:choose>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div class="nav">
	<c:choose>
	<c:when test="${rol == 'cliente' }">
		<button class="btn">
			<a href="${pageContext.request.contextPath}/ClienteControlador?opcion=python">Salir</a>
		</button>
	</c:when>
	<c:when test="${rol == 'consultor' }">
		<button class="btn">
			<a href="${pageContext.request.contextPath}/vistas/portalConsultor.jsp">Salir</a>
		</button>
	</c:when>
	<c:otherwise>
		<button class="btn">
			<a href="${pageContext.request.contextPath}/vistas/portalAdministrador.jsp">Salir</a>
		</button>
	</c:otherwise>
</c:choose>
	</div>
	<div class="header">
		<h1>Gestión de Consultas</h1>
	</div>
		<div class="container-carrusel">
		<div class="lista-consultas" id="listaConsultas">
			<c:choose>
				<c:when test="${rol == 'cliente' }">
					<c:forEach var="consulta" items="${consultasClientes}">
						<c:if test="${empty consultasClientes}"></c:if>
							<div class="consulta-item" 
                     data-id="${consulta.idConsulta}" 
					 data-rol="${rol}"
                     onclick="seleccionarItem(this)">
                    <p class="nombre">${consulta.titulo}</p>
                    <span class="estado ${consulta.estadoActual}">${consulta.estadoActual}</span>
                </div>
					</c:forEach>
				</c:when>
				<c:when test="${rol == 'consultor' }">
					<c:forEach var="consulta" items="${consultasConsultor}">
					<c:if test="${empty consultasConsultor}"></c:if>
						<div class="consulta-item" 
                     data-id="${consulta.idConsulta}" 
					 data-rol="${rol}"
                     onclick="seleccionarItem(this)">
                    <p class="nombre">${consulta.titulo}</p>
                    <span class="estado ${consulta.estadoActual}">${consulta.estadoActual}</span>
                </div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="consulta" items="${consultasAdmin}">
					<c:if test="${empty consultasAdmin}"></c:if>
						<div class="consulta-item" 
                     data-id="${consulta.idConsulta}" 
					 data-rol="${rol}"
                     onclick="seleccionarItem(this)">
                    <p class="nombre">${consulta.titulo}</p>
                    <span class="estado ${consulta.estadoActual}">${consulta.estadoActual}</span>
                </div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</div>
		<div class="acciones">
			<button class="btn-principal" onclick="consultarConsulta()">VER DETALLES</button>
		</div>
		</div>
		<script src="${pageContext.request.contextPath}/js/consultas.js"></script>
</body>
</html>