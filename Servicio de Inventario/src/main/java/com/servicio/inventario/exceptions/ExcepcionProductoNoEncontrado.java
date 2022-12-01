package com.servicio.inventario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExcepcionProductoNoEncontrado extends RuntimeException {

    public ExcepcionProductoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
