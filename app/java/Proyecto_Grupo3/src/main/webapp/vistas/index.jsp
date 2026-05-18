<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>WEB PUBLICA</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>

    <div class="nav">
        <button class="btn">Regresar</button>
        <a href="/login" class="btn">
		    Login
		</a>
    </div>

    <h1 class="titulo">Departamento de Consultoría Tecnológica</h1>

    <div class="layout">
        

            <span class="label">Información General</span>
            <div class="info-content">
                Tenemos como objetivo principal ofrecer servicios de análisis, asesoramiento y mejoras de sistemas
                tecnológicos e clientes externos. Este departamento permite gestionar solicitudes de consultoría,
                así como publicar información sobre los servicios ofrecidos por la empresa.
                <br>
                Para ello tenemos una serie de servicios que pueden en nuestra zona pública.
            </div>



            <span class="label">Servicios</span>
            <div class="scroll-box height-servicios">
                <ul>
                    <c:forEach var="servicio" items="${servicios}">
                        <li>
                            <c:out value="${servicio.nombre}"></c:out><br> 
                        </li>
                        <div class="servicio-item" 
                            data-id="${servicio.idServicio}" 
                            onclick="window.location.href=
                            '/ServicioControlador?opcion=verServicio&idServicio=${servicio.idServicio}'">
                            <p class="nombre">${servicio.nombre}</p>
                        </div>
                    </c:forEach>
                    
                    <c:if test="${empty servicios}">
                        <li>No hay servicios disponibles actualmente.</li>
                    </c:if>
                </ul>
            </div>
    </div>

</body>
</html>