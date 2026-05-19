<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="fondo-modal-detalle" class="fondo-modal">
    <div id="tarjeta-consultor" class="tarjeta">
        <c:choose>
            <c:when test="${rol == 'cliente'}"><h2>Detalle Consultor</h2></c:when>
            <c:when test="${rol == 'consultor'}"><h2>Detalle Cliente</h2></c:when>
            <c:when test="${rol == 'admin'}"><h2>Detalle Usuario</h2></c:when>
        </c:choose>
        
        <div id="mensaje-sin-usuario" class="estado-vacio <c:if test="${not empty usuario}">oculto</c:if>">
            <div class="icono-vacio">👤</div>
            <h3>Aún no asignado</h3>
            <p>No hay información de usuario disponible para mostrar.</p>
        </div>

        <div id="contenido-datos-usuario" class="contenido-datos <c:if test="${empty usuario}">oculto</c:if>">
            <div class="columna">
                <p id="p-nombre"><strong>Nombre:</strong> <span id="modal-nombre"><c:out value="${usuario.nombre}" /></span></p>
                <p id="p-apellidos"><strong>Apellidos:</strong> <span id="modal-apellidos"><c:out value="${usuario.apellidos}" /></span></p>
                <p id="p-correo"><strong>Correo:</strong> <span id="modal-correo"><c:out value="${usuario.correo}" /></span></p>
            </div>
            <div class="columna">
                <p id="p-direccion">
                    <strong>Dirección:</strong> <span id="modal-direccion"><c:out value="${usuario.direccion}" /></span>
                </p>
                
                <c:if test="${not empty dptoNombre}">
                    <p id="p-departamento">
                        <strong>Departamento:</strong> <span id="modal-departamento"><c:out value="${dptoNombre}" /></span>
                    </p>
                </c:if>
                
                <c:if test="${not empty sedeNombre}">
                     <p id="p-sede">
                        <strong>Sede:</strong> <span id="modal-sede"><c:out value="${sedeNombre}" /></span>
                    </p>
                </c:if>
            </div>
        </div>
        <button id="btn-cerrar">Cerrar</button>
    </div>
</div>