package com.servicio.ordenes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExcepcionSolicitudDeOrdenIncorrecta extends RuntimeException {

    public ExcepcionSolicitudDeOrdenIncorrecta(String mensaje) {
        super(mensaje);
    }
}
