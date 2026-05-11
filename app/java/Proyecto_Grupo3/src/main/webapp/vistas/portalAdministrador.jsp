<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">
<title>WEB PRIVADA ADMIN</title>
</head>
<body>
    <div class="nav">
        <button class="btn">
        	<a>Volver</a>	
        </button>
    </div>
    <div class="header">
		<h1>Portal del Administrador</h1>
	</div>
    <div class="layout">
        <table>
            <tr>
                <button class="btn">
                	<a href="${pageContext.request.contextPath}/ServicioControlador?opcion=gestionServicios">Gestión de Servicios</a>	
                </button>
            </tr>
            <tr>
                <button class="btn">
                	<a href="${pageContext.request.contextPath}/ConsultasControlador?opcion=gestionConsultas">Gestión de Consultas</a>	
                </button>
            </tr>
            <tr>
                <button class="btn">
                	<a href="${pageContext.request.contextPath}/ConsultoresControlador?opcion=gestionConsultores">Consultores</a>	
                </button>
            </tr>
            <tr>
                <button class="btn">
                	<a href="${pageContext.request.contextPath}/ClientesControlador?opcion=gestionClientes">Gestión de Clientes</a>	
                </button>
            </tr>
            
        </table>
    </div>

</body>
</html>