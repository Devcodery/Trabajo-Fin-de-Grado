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
        	<a>Salir</a>	
        </button>
    </div>
    <div class="header">
		<h1>Portal del Administrador</h1>
	</div>
    <div class="layout">
        <table>
            <tr>
                <button class="btn">
                	<a href="${pageContext.request.contextPath}/GestionServicioControlador?opcion=gestionServicios">Gestión de Servicios</a>	
                </button>
            </tr>
            <tr>
                <button class="btn">
                	<a href="${pageContext.request.contextPath}/ConsultaControlador?opcion=gestionConsultasAdmin">Gestión de Consultas</a>	
                </button>
            </tr>
            <tr>
                <button class="btn">
                	<a href="${pageContext.request.contextPath}/UsuarioControlador?opcion=gestion&rol=consultor">Consultores</a>	
                </button>
            </tr>
            <tr>
                <button class="btn">
                	<a href="${pageContext.request.contextPath}/UsuarioControlador?opcion=gestion&rol=cliente">Gestión de Clientes</a>	
                </button>
            </tr>
            
        </table>
    </div>

</body>
</html>