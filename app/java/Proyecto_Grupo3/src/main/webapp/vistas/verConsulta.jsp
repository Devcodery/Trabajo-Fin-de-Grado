<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
    <c:when test="${rol == 'consultor'}">
        <title>WEB PRIVADA CONSULTOR</title>
    </c:when>
    <c:when test="${rol == 'cliente'}">
        <title>WEB PRIVADA CLIENTE</title>
    </c:when>
</c:choose>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div class="nav">
		<c:choose>
		    <c:when test="${rol == 'cliente'}">
		        <button class="btn">
		        	<a href="${pageContext.request.contextPath}/ConsultaControlador?opcion=gestionConsultasCliente">Volver</a>	
		        </button>
		    </c:when>
		    <c:when test="${rol == 'consultor'}">
		        <button class="btn">
		        	<a href="${pageContext.request.contextPath}/ConsultaControlador?opcion=gestionConsultasConsultor">Volver</a>	
		        </button>
		    </c:when>
	    </c:choose>
    </div>
	
    <div class="title">
        <h1>Detalle de Consulta</h1>
        <h2><c:out value="${consulta.titulo}"/></h2>
    </div>

    <div class="status-section center-text">
        <c:choose>
            <c:when test="${rol == 'cliente'}">
                <p>Estado de consulta: <span class="status-text"><c:out value="${consulta.estadoActual}"/></span></p>
            </c:when>
            
            <c:when test="${rol == 'consultor'}">
                <form action="${pageContext.request.contextPath}/ConsultaControlador" method="post" class="form-inline">
                    <input type="hidden" name="opcion" value="actualizarEstado">
                    <input type="hidden" name="idConsulta" value="${consulta.idConsulta}">
                    
                    <label for="estado">Estado:</label>
                    <select name="estado" id="estado" onchange="this.form.submit()">
                        <option value="pendiente" ${consulta.estadoActual == 'pendiente' ? 'selected' : ''}>Pendiente</option>
                        <option value="en_progreso" ${consulta.estadoActual == 'en_progreso' ? 'selected' : ''}>En proceso</option>
                        <option value="resuelta" ${consulta.estadoActual == 'resuelta' ? 'selected' : ''}>Resuelta</option>
                    </select>
                </form>
            </c:when>
        </c:choose>
    </div>

    <section class="info-consulta">
        <p><strong>Fecha de consulta:</strong> <c:out value="${consulta.fechaCreacion}"/></p>
        <p><strong>Tipo de consulta:</strong> <c:out value="${consulta.titulo}"/></p>
        <p><strong>Mensaje:</strong> <c:out value="${consulta.descripcion}"/></p>
    </section>


    <a href="${pageContext.request.contextPath}/ConsultaControlador?opcion=listarMensajes">Ver Mensajes</a>

    <c:choose>
        <c:when test="${ rol == 'cliente'}">
            <button id="btn-abrir-detalle">Ver Consultor</button>
        </c:when>
        <c:when test="${ rol == 'consultor'}">
            <button id="btn-abrir-detalle">Ver Cliente</button>
        </c:when>
    </c:choose>
    
    <jsp:include page="tarjetaUsuario.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/js/verUsuario.js"></script>
</body>
</html>