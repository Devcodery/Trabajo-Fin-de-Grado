<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="fondo-modal-detalle" class="fondo-modal">
    <div id="tarjeta-consultor" class="tarjeta">
        <c:choose>
            <c:when test="${rol == 'cliente'}"><h2>Detalle Consultor</h2></c:when>
            <c:when test="${rol == 'consultor'}"><h2>Detalle Cliente</h2></c:when>
            <c:when test="${rol == 'admin'}"><h2>Detalle Usuario</h2></c:when>
        </c:choose>
        
        <c:choose>
            <c:when test="${empty usuario}">
                <div id="mensaje-sin-usuario" class="estado-vacio oculto">
                    <div class="icono-vacio">👤</div>
                    <h3>Aún no asignado</h3>
                    <p>No hay información de usuario disponible para mostrar en esta consulta.</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="contenido-datos">
                    <div class="columna">
                        <p id="p-nombre">Nombre: <span id="modal-nombre"><c:out value="${usuario.nombre}" /></span></p>
                        <p id="p-apellidos">Apellidos: <span id="modal-apellidos"><c:out value="${usuario.apellidos}" /></span></p>
                        <p id="p-correo">Correo: <span id="modal-correo"><c:out value="${usuario.correo}" /></span></p>
                    </div>
                    <div class="columna">
                        <p id="p-departamento" <c:if test="${empty usuario.departamento}">style="display:none;"</c:if>>
                            Departamento: <span id="modal-departamento"><c:out value="${usuario.departamento}" /></span>
                        </p>
                        
                        <p id="p-sede" <c:if test="${empty usuario.sede}">style="display:none;"</c:if>>
                            Sede: <span id="modal-sede"><c:out value="${usuario.sede}" /></span>
                        </p>
                        
                        <p id="p-direccion" <c:if test="${empty usuario.direccion}">style="display:none;"</c:if>>
                            Direccion: <span id="modal-direccion"><c:out value="${usuario.direccion}" /></span>
                        </p>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <button id="btn-cerrar">Cerrar</button>
    </div>
</div>