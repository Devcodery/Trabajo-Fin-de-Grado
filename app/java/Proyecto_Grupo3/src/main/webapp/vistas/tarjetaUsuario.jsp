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
                <div class="estado-vacio">
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
                        <c:choose>
                            <c:when test="${empty dptoNombre || empty sedeNombre}">
                                <p id="p-direccion">
                                    Direccion: <span id="modal-direccion"><c:out value="${usuario.direccion}" /></span>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <p id="p-departamento">
                                    Departamento: <span id="modal-departamento"><c:out value="${dptoNombre}" /></span>
                                </p>
                                
                                <p id="p-sede">
                                    Sede: <span id="modal-sede"><c:out value="${sedeNombre}" /></span>
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <button id="btn-cerrar">Cerrar</button>
    </div>
</div>