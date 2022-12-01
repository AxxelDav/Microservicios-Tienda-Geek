package com.servicio.ordenes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExcepcionCuentaNoEncontrada extends RuntimeException {

    public ExcepcionCuentaNoEncontrada(String mensaje) {
        super(mensaje);
    }
}
