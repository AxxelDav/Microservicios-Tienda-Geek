package com.servicio.ordenes.util;

import com.servicio.ordenes.dto.servicioOrden.SolicitudOrden;
import com.servicio.ordenes.exception.ExcepcionSolicitudDeOrdenIncorrecta;

public class ValidaOrden {

    public static boolean validarOrden(SolicitudOrden orden) {
        if(orden.getArticulos() == null || orden.getArticulos().isEmpty()){
            throw new ExcepcionSolicitudDeOrdenIncorrecta(EnumMensajesExcepciones.SOLICITUD_INCORRECTA_ARTICULOS_VACIOS_EN_ORDEN.getValue());
        }
        return true;
    }
}
