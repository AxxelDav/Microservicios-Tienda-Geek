package com.servicio.ordenes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExcepcionPagoNoAceptado extends Exception {

    public ExcepcionPagoNoAceptado(String mensaje) {
        super(mensaje);
    }
}
