package com.servicio.pago.controlador;

import com.servicio.pago.dominio.Confirmacion;
import com.servicio.pago.dto.SolicitudDePago;
import com.servicio.pago.dto.RespuestaDePago;
import com.servicio.pago.entidad.Pago;
import com.servicio.pago.servicio.PagoServicio;
import com.servicio.pago.util.TransformadorTransferenciaPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PagoControlador {

    @Autowired
    private PagoServicio pagoServicio;

    @Autowired
    private TransformadorTransferenciaPago transformador;

    @PostMapping(value = "/autorizador")
    public ResponseEntity<Confirmacion> autorizador(@RequestBody SolicitudDePago solicitudDePago){
        return new ResponseEntity<>(pagoServicio.authoriza(solicitudDePago), HttpStatus.OK);
    }

    @GetMapping(value = "/transaccion/{IdTransaccion}")
    public ResponseEntity<List<RespuestaDePago>> findPagosByIdTransaccion(@PathVariable("IdTransaccion") String IdTransaccion) {
        List<Pago> pagos = pagoServicio.findPagosByIdTransaccion(IdTransaccion);
        return new ResponseEntity<>(transformador.transformaListaDePagoAdto(pagos), HttpStatus.OK);
    }

    @GetMapping(value = "/orden/{idOrden}")
    public ResponseEntity<List<RespuestaDePago>> findPagosByIdOrden(@PathVariable("idOrden") String idOrden) {
        List<Pago> pagos = pagoServicio.findPagosByIdOrden(idOrden);
        return new ResponseEntity<>(transformador.transformaListaDePagoAdto(pagos), HttpStatus.OK);
    }

    @GetMapping(value = "/cuenta/{idCuenta}")
    public ResponseEntity<List<RespuestaDePago>> findPagosByIdCuenta(@PathVariable("idCuenta") String idCuenta) {
        List<Pago> pagos = pagoServicio.findPagosByIdCuenta(idCuenta);
        return new ResponseEntity<>(transformador.transformaListaDePagoAdto(pagos), HttpStatus.OK);
    }
}
