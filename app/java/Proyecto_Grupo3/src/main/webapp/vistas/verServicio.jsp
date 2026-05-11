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
        	<a href="${pageContext.request.contextPath}/ServicioControlador?opcion=listarServicios">Salir</a>	
        </button>
    </div>
	
    <table>
        <thead>
            <tr>
                <th>Nombre</th>            
                <th>Descripción</th>           
                <th>Tecnologías Implicadas</th>           
                <th>Objetivos</th>            
                <th>Alcance</th>            
                <th>Beneficios</th>            
                <th>Categoría</th>            
                <th>Sede</th>           
                <th>Fecha de Creación</th>            
                <th>Estado</th>
            </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${servicio.nombre}"></c:out></td>
            <td><c:out value="${servicio.descripcion}"></c:out></td>
            <td><c:out value="${servicio.tecnologiasImplicadas}"></c:out></td>
            <td><c:out value="${servicio.objetivos}"></c:out></td>
            <td><c:out value="${servicio.alcance}"></c:out></td>
            <td><c:out value="${servicio.beneficios}"></c:out></td>
            <td><c:out value="${servicio.categoria}"></c:out></td>
            <td><c:out value="${servicio.sede}"></c:out></td>
            <td><c:out value="${servicio.fechaCreacion}"></c:out></td>
            <td><c:out value="${servicio.estado}"></c:out></td>
        </tr>
        </tbody>
    </table>
</body>
</html>