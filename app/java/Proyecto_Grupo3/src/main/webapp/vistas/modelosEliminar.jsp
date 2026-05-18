<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="modal-alerta-activo" class="fondo-modal">
    <div class="tarjeta tarjeta-alerta">
        <h2>Acción no permitida</h2>
        <p>
            No puedes borrar un servicio que está <strong>activo</strong>. Por favor, desactívalo primero.
        </p>
        <div class="form-acciones">
            <button type="button" class="btn-secondary" onclick="cerrarModalAlerta()">Entendido</button>
        </div>
    </div>
</div>

<div id="modal-confirmacion" class="fondo-modal">
    <div class="tarjeta tarjeta-alerta">
        <h2>¿Eliminar servicio?</h2>
        <p>
            ¿Estás seguro de que deseas eliminar este servicio permanentemente? Esta acción no se puede deshacer.
        </p>
        <div class="form-acciones">
            <button type="button" class="btn-secondary" onclick="cerrarModalConfirmacion()">Cancelar</button>
            <button type="button" class="btn-eliminar" onclick="confirmarEliminarServicio()">Sí, eliminar</button>
        </div>
    </div>
</div>