<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:if test="${if rol == 'cliente'}">
	<title>WEB PRIVADA CLIENTE</title>
</c:if>
<c:if test="${if rol == 'consultor'}">
	<title>WEB PRIVADA CONSULTOR</title>	
</c:if>
<title>WEB PRIVADA CLIENTE</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div class="nav">
        <button class="btn">
        	<a>Salir</a>	
        </button>
    </div>
    
    <c:if test="${if rol == 'cliente'}">
    	 <h1 class="titulo">Solicitudes presentadas</h1>
    </c:if>
   	
   	<c:if test="${if rol == 'consultor'}">
    	 <h1 class="titulo">Solicitudes</h1>
    </c:if>
    
    <c:if test="${if rol == 'cliente'}">
    	<div class="layout">
		<button class="btn-consulta">
			<a class="btn-consulta" href="/formularios/formularioConsulta.php?idUsuario=<c:out value="${id}"></c:out>"> Nueva Consulta</a>
		</button>
		
		</div>
    </c:if>    
	
	<div class="layout">
		<button class="btn-consulta" href="${pageContext.request.contextPath}/">Ver</button>
	</div>
</body>
</html>