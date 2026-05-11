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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<div class="nav">
        <button class="btn">
        	<a>Salir</a>	
        </button>
    </div>
   <div class="layout">
		<table>
	       <c:choose>
				<c:when test="${if rol == 'cliente'}">
					<tr>
			            <c:out value="${consulta.nombre}"></c:out>
			        </tr>
		   </c:choose>
		</table>
   </div>
</body>
</html>