<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WEB PRIVADA ADMIN</title>
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
	<table>
		<c:choose>
			<c:when test="${funcion == 'verServicio'}">
				<c:forEach var="servicio" items="${servicios}">
					<tr>
						<td><c:out value="${servicio.nombre}"></c:out></td>
						<c:if test="${servicio.estado == 'false'}">
							<td>Inactivo</td>
						</c:if>
						<c:if test="${servicio.estado == 'true'}">
							<td>Activo</td>
						</c:if>
						<td><a
							href="${pageContext.request.contextPath}/ServicioControlador?opcion=verServicio&idServicio=<c:out value="${servicio.idServicio}"></c:out>">VER</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${funcion == 'modificarServicio'}">
				<c:forEach var="servicio" items="${servicios}">
					<tr>
						<td><c:out value="${servicio.nombre}"></c:out></td>
						<c:if test="${servicio.estado == 'false'}">
							<td>Inactivo</td>
						</c:if>
						<c:if test="${servicio.estado == 'true'}">
							<td>Activo</td>
						</c:if>
						<td><a
							href="${pageContext.request.contextPath}/ServicioControlador?opcion=modificarServicioPHP&idServicio=<c:out value="${servicio.idServicio}"></c:out>">MODIFICAR</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${funcion == 'listarServicioInactivos'}">
				<c:forEach var="servicio" items="${serviciosInactivos}">
					<tr>
						<td><c:out value="${servicio.nombre}"></c:out></td>
						<c:if test="${servicio.estado == 'false'}">
							<td>Inactivo</td>
						</c:if>
						<td><a
							href="${pageContext.request.contextPath}/ServicioControlador?opcion=eliminarServicio&idServicio=<c:out value="${servicio.idServicio}"></c:out>">ELIMINAR</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${funcion == 'listarServicioActivos'}">
				<c:forEach var="servicio" items="${serviciosActivos}">
					<tr>
						<td><c:out value="${servicio.nombre}"></c:out></td>
						<c:if test="${servicio.estado == 'true'}">
							<td>Activo</td>
						</c:if>
						<td><a
							href="${pageContext.request.contextPath}/ServicioControlador?opcion=desactivarServicio&idServicio=<c:out value="${servicio.idServicio}"></c:out>">DESACTIVAR</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</body>
</html>