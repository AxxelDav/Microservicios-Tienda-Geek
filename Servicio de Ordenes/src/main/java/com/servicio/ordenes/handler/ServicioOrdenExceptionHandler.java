package com.servicio.ordenes.handler;

import com.servicio.ordenes.exception.ExcepcionCuentaNoEncontrada;
import com.servicio.ordenes.exception.ExcepcionPagoNoAceptado;
import com.servicio.ordenes.exception.ExcepcionRespuestaDeServicioOrden;
import com.servicio.ordenes.exception.ExcepcionSolicitudDeOrdenIncorrecta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class ServicioOrdenExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerAllExceptions(Exception exception, WebRequest request) {
        ExcepcionRespuestaDeServicioOrden respuesta = new ExcepcionRespuestaDeServicioOrden(exception.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(respuesta, respuesta.getEstado());
    }

    @ExceptionHandler(ExcepcionSolicitudDeOrdenIncorrecta.class)
    public ResponseEntity<Object> handleIncorrectRequest(ExcepcionSolicitudDeOrdenIncorrecta exception, WebRequest request) {
        ExcepcionRespuestaDeServicioOrden respuesta = new ExcepcionRespuestaDeServicioOrden(exception.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(respuesta, respuesta.getEstado());
    }

    @ExceptionHandler(ExcepcionCuentaNoEncontrada.class)
    public ResponseEntity<Object> handleResourceNotFound(ExcepcionCuentaNoEncontrada exception, WebRequest request) {
        ExcepcionRespuestaDeServicioOrden respuesta = new ExcepcionRespuestaDeServicioOrden(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(respuesta, respuesta.getEstado());
    }

    @ExceptionHandler(ExcepcionPagoNoAceptado.class)
    public ResponseEntity<Object> handlePaymentNotAcceptedResourceNotFound(ExcepcionPagoNoAceptado exception, WebRequest request) {
        ExcepcionRespuestaDeServicioOrden respuesta = new ExcepcionRespuestaDeServicioOrden(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(respuesta, respuesta.getEstado());
    }
}
