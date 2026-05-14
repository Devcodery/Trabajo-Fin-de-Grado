<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
    <c:when test="${rol == 'consultor'}">
        <title>WEB PRIVADA CONSULTOR</title>
    </c:when>
    <c:when test="${rol == 'cliente'}">
        <title>WEB PRIVADA CLIENTE</title>
    </c:when>
</c:choose>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>	
    <div class="title">
        <h1>MENSAJES</h1>
    </div>
    <button id="btn-abrir-detalle">Enviar Mensaje</button>
     <jsp:include page="enviarMensajes.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/js/mensajeModal.js"></script>
</body>
</html>