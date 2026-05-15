<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="fondo-modal-ver" class="fondo-modal"> <div id="tarjeta-ver" class="tarjeta" role="dialog" aria-labelledby="titulo-ver">
        <div class="mensaje">
            <h1 id="titulo-ver">DETALLE MENSAJE</h1>            
            
            <div class="detalle-contenido">
                <p><strong>Asunto:</strong> <span id="ver-asunto-texto"></span></p>
                <p><strong>Mensaje:</strong> <span id="ver-descripcion-texto"></span></p>
            </div>
            
            <div class="table-acciones">
                <button type="button" id="btn-cerrar-ver">Cerrar</button> </div>
        </div>
    </div>
</div>