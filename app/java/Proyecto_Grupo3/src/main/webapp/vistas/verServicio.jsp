<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${rol == 'admin'}">WEB PRIVADA ADMIN</c:when>
            <c:otherwise>WEB PUBLICA</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>

    <div class="nav">
        <c:choose>
            <c:when test="${rol == 'admin'}">
                <button class="btn">
                    <a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=listarServicios&funcion=servicio">Volver</a>
                </button>
            </c:when>
            <c:otherwise>
                <button class="btn">
                    <a href="${pageContext.request.contextPath}/IndexControlador">Volver</a>
                </button>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="title">
        <h1>
            <c:choose>
                <c:when test="${rol == 'admin'}">Detalle del Servicio</c:when>
                <c:otherwise>Información del Servicio</c:otherwise>
            </c:choose>
        </h1>
        <h2><c:out value="${servicio.nombre}"></c:out></h2>
    </div>

    <div class="status-section center-text">
        <p>Estado del servicio: 
            <span class="status-text ${servicio.estado ? 'activo' : 'inactivo'}">
                <c:out value="${servicio.estado ? 'Activo' : 'Inactivo'}"></c:out>
            </span>
        </p>
    </div>

    <section class="info-servicio">
        
        <div class="servicio-grid">
            <div class="servicio-columna">
                <div class="servicio-campo">
                    <span class="servicio-label">Categoría:</span>
                    <span class="servicio-texto"><c:out value="${servicio.categoria}"></c:out></span>
                </div>
                
                <div class="servicio-campo">
                    <span class="servicio-label">Sede:</span>
                    <span class="servicio-texto"><c:out value="${sede.nombre}"></c:out></span>
                </div>

                <div class="servicio-campo">
                    <span class="servicio-label">Tecnologías implicadas:</span>
                    <div class="servicio-caja">
                        <c:out value="${servicio.tecnologiasImplicadas}"></c:out>
                    </div>
                </div>

                <div class="servicio-campo">
                    <span class="servicio-label">Beneficios:</span>
                    <div class="servicio-caja">
                        <c:out value="${servicio.beneficios}"></c:out>
                    </div>
                </div>
                
                <div class="servicio-campo servicio-campo-final">
                    <span class="servicio-label">Fecha de Creación:</span>
                    <span class="servicio-texto"><c:out value="${servicio.fechaCreacion}"></c:out></span>
                </div>
            </div>

            <div class="servicio-columna">
                <div class="servicio-campo">
                    <span class="servicio-label">Descripción:</span>
                    <div class="servicio-caja servicio-caja-grande">
                        <c:out value="${servicio.descripcion}"></c:out>
                    </div>
                </div>

                <div class="servicio-campo">
                    <span class="servicio-label">Objetivos:</span>
                    <div class="servicio-caja">
                        <c:out value="${servicio.objetivos}"></c:out>
                    </div>
                </div>

                <div class="servicio-campo">
                    <span class="servicio-label">Alcance:</span>
                    <div class="servicio-caja">
                        <c:out value="${servicio.alcance}"></c:out>
                    </div>
                </div>
            </div>
        </div>

    </section>

</body>
</html>