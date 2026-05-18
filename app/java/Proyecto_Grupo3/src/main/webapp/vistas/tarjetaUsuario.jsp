<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="fondo-modal-detalle" class="fondo-modal">
    <div id="tarjeta-consultor" class="tarjeta">
        <c:choose>
            <c:when test="${rol == 'cliente'}"><h2>Detalle Consultor</h2></c:when>
            <c:when test="${rol == 'consultor'}"><h2>Detalle Cliente</h2></c:when>
            <c:when test="${rol == 'admin'}"><h2>Detalle Usuario</h2></c:when>
        </c:choose>
        
        <div class="contenido-datos">
            <c:choose>
                <c:when test="${rol == 'cliente'}">
                    <c:if test="${not empty usuario}">
                         <div class="columna">
                        <p>Nombre: <c:out value="${usuario.nombre}" /></p>
                        <p>Apellidos: <c:out value="${usuario.apellidos}" /></p>
                        <p>Correo: <c:out value="${usuario.correo}" /></p>
                    </div>
                    <div class="columna">
                        <p>Departamento: <c:out value="${usuario.departamento}" /></p>
                        <p>Sede: <c:out value="${usuario.sede}" /></p>
                    </div>
                    </c:if>
                   <c:if test="${empty usuario}">
                        <p>Esta consulta no tiene un consultor asignado</p>
                    </c:if>
                </c:when>

                <c:when test="${rol == 'consultor'}">
                    <div class="columna">
                        <p>Nombre: <c:out value="${usuario.nombre}" /></p>
                        <p>Apellidos: <c:out value="${usuario.apellidos}" /></p>
                    </div>
                    <div class="columna">
                        <p>Correo: <c:out value="${usuario.correo}" /></p>
                        <p>Direccion: <c:out value="${usuario.direccion}" /></p>
                    </div>
                </c:when>
                <c:when test="${rol == 'admin'}">
                    <c:choose>
                        <c:when test="${not empty consultor}">
                            <div class="columna">
                                <p>Nombre: <c:out value="${consultor.nombre}" /></p>
                                <p>Apellidos: <c:out value="${consultor.apellidos}" /></p>
                                <p>Correo: <c:out value="${consultor.correo}" /></p>
                            </div>
                            <div class="columna">
                                <p>Departamento: <c:out value="${consultor.departamento}" /></p>
                                <p>Sede: <c:out value="${consultor.sede}" /></p>
                            </div>
                        </c:when>
                        <c:when test="${not empty cliente}">
                            <div class="columna">
                                <p>Nombre: <c:out value="${cliente.nombre}" /></p>
                                <p>Apellidos: <c:out value="${cliente.apellidos}" /></p>
                            </div>
                            <div class="columna">
                                <p>Correo: <c:out value="${cliente.correo}" /></p>
                                <p>Direccion: <c:out value="${cliente.direccion}" /></p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p>Información no disponible</p>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <p>Información no disponible</p>
                </c:otherwise>
            </c:choose>
        </div>
        <button id="btn-cerrar">Cerrar</button>
    </div>
</div>