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
	<c:set var="rol" value="${sessionScope.rol}" />
	<div class="nav">
		<c:choose>
			<c:when test="${rol == 'cliente' }">
				<button class="btn">
					<a href="${pageContext.request.contextPath}/ClienteControlador?opcion=logueado">Volver</a>
				</button>
			</c:when>
			<c:when test="${rol == 'consultor' }">
				<button class="btn">
					<a href="${pageContext.request.contextPath}/ConsultorControlador?opcion=logueado">Volver</a>
				</button>
			</c:when>
			<c:when test="${rol == 'admin'}">
				<button class="btn">
					<a href="${pageContext.request.contextPath}/AdministradorControlador?opcion=logueado">Volver</a>
				</button>
			</c:when>
		</c:choose>
	</div>
	<div class="header">
		<h1>Gestión de Consultas</h1>
	</div>
	<div class="container-carrusel">
	
	<c:if test="${rol == 'admin'}">
		<div class="barra-herramientas">
			<button id="btn-abrir-filtros" class="btn-filtrar">
				Filtrar
			</button>
		</div>

		<div id="modal-filtros" class="modal-filtros oculto">
			<form action="${pageContext.request.contextPath}/ConsultaControlador" method="GET">
				<input type="hidden" name="opcion" value="filtrarConsultas">

				<div class="fila-filtro">
					<input type="checkbox" id="chkCliente" onchange="toggleFiltro('idCliente', this.checked)">
					<label for="chkCliente">Cliente</label>
					<select name="idCliente" id="idCliente" disabled>
						<c:forEach var="cliente" items="${listaClientes}">
							<option value="${cliente.idUsuario}">${cliente.nombre}</option>
						</c:forEach>
					</select>
				</div>

				<div class="fila-filtro">
					<input type="checkbox" id="chkConsultor" onchange="toggleFiltro('idConsultor', this.checked)">
					<label for="chkConsultor">Consultor</label>
					<select name="idConsultor" id="idConsultor" disabled>
						<c:forEach var="consultor" items="${listaConsultores}">
							<option value="${consultor.idUsuario}">${consultor.nombre}</option>
					</c:forEach>
					</select>
				</div>

				<div class="fila-filtro">
					<input type="checkbox" id="chkEstado" onchange="toggleFiltro('estado', this.checked)">
					<label for="chkEstado">Estado</label>
					<select name="estado" id="estado" disabled>
						<option value="pendiente">Pendiente</option>
						<option value="enprogreso">En progreso</option>
						<option value="finalizada">Finalizada</option>
					</select>
				</div>

				<div class="fila-filtro">
					<input type="checkbox" id="chkServicio" onchange="toggleFiltro('idServicio', this.checked)">
					<label for="chkServicio">Tipo de Servicio</label>
					<select name="idServicio" id="idServicio" disabled>
						<c:forEach var="servicio" items="${listaServicios}">
							<option value="${servicio.idServicio}">${servicio.nombre}</option>
						</c:forEach>
					</select>
				</div>

				<div class="fila-filtro">
					<input type="checkbox" id="chkFechas" onchange="toggleFechas(this.checked)">
					<label for="chkFechas">Rango de fechas</label>
					<input type="date" name="fechaInicio" id="fechaInicio" disabled>
					<input type="date" name="fechaFin" id="fechaFin" disabled>
				</div>

				<div class="acciones-filtro">
					<button type="submit" class="btn-aplicar">Aplicar Filtros</button>
				</div>
			</form>
		</div>
	</c:if>
	<div class="container-carrusel">
		<div class="lista-consultas" id="listaConsultas">
			<c:if test="${empty listaConsultas}">
				<p>No hay consultas para mostrar.</p>
			</c:if>
			<c:forEach var="consulta" items="${listaConsultas}">
				<div class="consulta-item" data-id="${consulta.idConsulta}"
					data-rol="${rol}" onclick="seleccionarItem(this)">
					<p class="nombre">${consulta.titulo}</p>
					<span class="estado ${consulta.estadoActual}">${consulta.estadoActual}</span>
				</div>
			</c:forEach>

		</div>
		<div class="acciones">
			<button class="btn-principal" onclick="consultarConsulta()">VER DETALLES</button>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/JavaScript/consultas.js"></script>
</body>
</html>