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
        	<a href="${pageContext.request.contextPath}/vistas/portalAdministrador.jsp">Salir</a>	
        </button>
    </div>
	<div class="header">
		<h1>Gestión de Servicios</h1>
	</div>
    <div class="layout">
   		<table>
	        <tr>
	            <button class="btn-consulta">
	            	<a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=listarServicios&funcion=verServicios">SERVICIOS</a>
	            </button>
	        </tr>
	        <tr>

			    <button class="btn-consulta">
			    	<a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=crearServicio">Crear Servicio</a>
			    </button>
	        </tr>
	        <tr>
	            <button class="btn-consulta">
                    <a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=listarServicios&funcion=modificarServicios">Modificar Servicio</a>
                </button>
	        </tr>
	        <tr>
	            <button class="btn-consulta">
                    <a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=listarServicios&funcion=desactivarServicios">Desactivar Servicio</a>
                </button>
	        </tr>
	        <tr>
	            <button class="btn-consulta">
                    <a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=listarServicios&funcion=eliminarServicios">Eliminar Servicio</a>
                </button>
	        </tr>
        </table>
	</div>
    

</body>
</html>