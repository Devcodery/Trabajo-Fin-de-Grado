<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=gestionServicios">Volver</a>
        </button>
    </div>

    <div class="container-carrusel">
        <h2>Selecciona un Servicio</h2>
        
        <div class="lista-servicios" id="listaServicios">
            <c:forEach var="s" items="${servicios}">
                <div class="servicio-item" 
                     data-id="${s.idServicio}" 
                     onclick="seleccionarItem(this)">
                    <p class="nombre">${s.nombre}</p>
                    <span class="estado ${s.estado ? 'activo' : 'inactivo'}">
                        ${s.estado ? 'Activo' : 'Inactivo'}
                    </span>
                </div>
            </c:forEach>
            
            <c:if test="${empty servicios}">
                <p>No se encontraron servicios con esos filtros.</p>
            </c:if>
        </div>

        <div class="acciones">
			<c:choose>
				<c:when test="${funcion == 'servicio'}">
					<button class="btn-principal" onclick="ejecutarAccion('servicio')">VER DETALLES</button>
				</c:when>
				
				<c:when test="${funcion == 'modificar'}">
					<button class="btn-principal" onclick="ejecutarAccion('modificar')">IR A MODIFICAR</button>
				</c:when>

				<c:when test="${funcion == 'desactivar'}">
					<button class="btn-activar" onclick="ejecutarAccion('activar')">ACTIVAR</button>
					<button class="btn-desactivar" onclick="ejecutarAccion('desactivar')">DESACTIVAR</button>
				</c:when>

				<c:when test="${funcion == 'eliminar'}">
					<button class="btn-eliminar" onclick="ejecutarAccion('eliminar')">BORRAR SERVICIO</button>
				</c:when>
			</c:choose>
		</div>
    </div>

    <script src="${pageContext.request.contextPath}/js/servicios.js"></script>
</body>
</html>