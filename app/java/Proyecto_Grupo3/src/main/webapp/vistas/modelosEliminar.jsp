<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="modal-alerta-activo" class="fondo-modal">
    <div class="tarjeta tarjeta-alerta">
        <h2>Acción no permitida</h2>
        <p>
            <c:choose>
                <c:when test="${param.origen == 'servicio'}">
                    No puedes borrar un servicio que está <strong>activo</strong>. Por favor, desactívalo primero.
                </c:when>
            </c:choose>
        </p>
        <div class="form-acciones">
            <button type="button" class="btn-secondary" onclick="cerrarModalAlerta()">Entendido</button>
        </div>
    </div>
</div>

<div id="modal-confirmacion" class="fondo-modal">
    <div class="tarjeta tarjeta-alerta">
        <c:choose>
            <c:when test="${param.origen == 'servicio'}">
                <h2>¿Eliminar servicio?</h2>
                <p>
                    ¿Estás seguro de que deseas eliminar este servicio permanentemente? Esta acción no se puede deshacer.
                </p>
            </c:when>
            <c:when test="${param.origen == 'mensaje'}">
                <h2>¿Eliminar mensaje?</h2>
                <p>
                    ¿Estás seguro de que deseas eliminar este mensaje permanentemente? Esta acción no se puede deshacer.
                </p>
            </c:when>
        </c:choose>
        <div class="form-acciones">
            <button type="button" class="btn-secondary" onclick="cerrarModalConfirmacion()">Cancelar</button>
            <button type="button" class="btn-eliminar" onclick="confirmarEliminar()">Sí, eliminar</button>
        </div>
    </div>
</div>