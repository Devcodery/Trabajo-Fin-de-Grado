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

    <div class="tarjeta servicio-card">
        
        <div class="servicio-header">
            <h1 class="servicio-header-titulo">
                <c:choose>
                    <c:when test="${rol == 'admin'}">WEB PRIVADA ADMIN</c:when>
                    <c:otherwise>WEB PUBLICA</c:otherwise>
                </c:choose>
            </h1>
        </div>

        <div class="servicio-body">
            
            <h2 class="servicio-titulo">
                <c:out value="${servicio.nombre}"></c:out>
            </h2>

            <div class="servicio-grid">
                
                <div class="servicio-col">
                    <div class="servicio-grupo">
                        <span class="servicio-label">Categoría</span>
                        <span class="servicio-valor"><c:out value="${servicio.categoria}"></c:out></span>
                    </div>
                    
                    <div class="servicio-grupo">
                        <span class="servicio-label">Sede</span>
                        <span class="servicio-valor"><c:out value="${servicio.sede}"></c:out></span>
                    </div>

                    <div class="servicio-grupo">
                        <span class="servicio-label">Tecnologías implicadas</span>
                        <div class="servicio-caja servicio-caja-md">
                            <c:out value="${servicio.tecnologiasImplicadas}"></c:out>
                        </div>
                    </div>

                    <div class="servicio-grupo">
                        <span class="servicio-label">Beneficios</span>
                        <div class="servicio-caja servicio-caja-md">
                            <c:out value="${servicio.beneficios}"></c:out>
                        </div>
                    </div>
                    
                    <div class="servicio-meta">
                        <strong>Fecha:</strong> <c:out value="${servicio.fechaCreacion}"></c:out> <br>
                        <strong>Estado:</strong> <c:out value="${servicio.estado ? 'Activo' : 'Inactivo'}"></c:out>
                    </div>
                </div>

                <div class="servicio-col">
                    <div class="servicio-grupo">
                        <span class="servicio-label">Descripción</span>
                        <div class="servicio-caja servicio-caja-lg">
                            <c:out value="${servicio.descripcion}"></c:out>
                        </div>
                    </div>

                    <div class="servicio-grupo">
                        <span class="servicio-label">Objetivos</span>
                        <div class="servicio-caja servicio-caja-md">
                            <c:out value="${servicio.objetivos}"></c:out>
                        </div>
                    </div>

                    <div class="servicio-grupo">
                        <span class="servicio-label">Alcance</span>
                        <div class="servicio-caja servicio-caja-md">
                            <c:out value="${servicio.alcance}"></c:out>
                        </div>
                    </div>
                </div>
                
            </div>

            <div class="servicio-pie">
                <a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=listarServicios&funcion=servicio" class="btn-secondary servicio-btn-cerrar">Cerrar</a>
            </div>

        </div>
    </div>

</body>
</html>