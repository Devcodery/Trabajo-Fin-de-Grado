<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WEB PRIVADA ADMIN</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div class="nav">
		<button class="btn">
			<a href="${pageContext.request.contextPath}/ConsultaControlador?opcion=gestionConsultasAdmin">Volver</a>	
		</button>
    </div>

    <div class="title">
        <h1>Detalle de Consulta</h1>
        <h2><c:out value="${consulta.titulo}"/></h2>
    </div>
	
    <section class="info-consulta">
        <p><strong>Fecha de consulta:</strong> <c:out value="${consulta.fechaCreacion}"/></p>
        <p><strong>Tipo de consulta:</strong> <c:out value="${consulta.titulo}"/></p>
        <p><strong>Mensaje:</strong> <c:out value="${consulta.descripcion}"/></p>
    </section>
    
    <form action="${pageContext.request.contextPath}/ConsultaControlador" method="post" class="form-admin">
        <input type="hidden" name="opcion" value="elegirConsultor">
        <input type="hidden" name="idConsulta" value="${consulta.idConsulta}">
        
        <div class="field-group">
            <label for="idConsultor">Asignar Consultor:</label>
            <select name="idConsultor" id="idConsultor" onchange="this.form.submit()">
                <c:forEach var="consultor" items="${consultores}">
                    <option value="${consultor.idUsuario}" 
                        ${consultor.idUsuario == consulta.consultor.idUsuario ? 'selected' : ''}>
                        ${consultor.nombre}
                    </option>
                </c:forEach>
            </select>
        </div>
    </form>

    
        <div class="actions-row">
            <button type="button" id="btn-abrir-detalle" class="btn-secondary">Ver cliente</button>
            
            <form action="${pageContext.request.contextPath}/ConsultaControlador" method="post" class="form-admin">
                <input type="hidden" name="opcion" value="actualizarEstado">
                <input type="hidden" name="idConsulta" value="${consulta.idConsulta}">
                
                <div class="status-group">
                    <label for="estado">Estado:</label>
                    <select name="estado" id="estado" onchange="this.form.submit()">
                        <option value="pendiente" ${consulta.estadoActual == 'pendiente' ? 'selected' : ''}>Pendiente</option>
                        <option value="en_progreso" ${consulta.estadoActual == 'en_progreso' ? 'selected' : ''}>En proceso</option>
                        <option value="resuelta" ${consulta.estadoActual == 'resuelta' ? 'selected' : ''}>Resuelta</option>
                    </select>
                </div>
            </form>
        </div>
    <jsp:include page="tarjetaUsuario.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/js/verUsuario.js"></script>
</body>
</html>