package com.servicio.pago.servicio;

import com.servicio.pago.dominio.Confirmacion;
import com.servicio.pago.dominio.ConfirmacionDePago;
import com.servicio.pago.dto.SolicitudDePago;
import com.servicio.pago.entidad.Pago;
import com.servicio.pago.repositorio.PagoRepositorio;
import com.servicio.pago.util.Autorizador;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PagoServicio {
    @Autowired
    private PagoRepositorio pagoRepositorio;

    @Autowired
    private Autorizador autorizador;

    public Confirmacion authoriza(SolicitudDePago solicitud) {
        log.info("Autorizando el Pago: {}", solicitud.toString());

        ConfirmacionDePago confirmacionDePago = autorizador.procesoDePago(solicitud.getPago());
        String transactionId = UUID.randomUUID().toString();

        Pago pago = Pago.builder()
                            .idTransaccion(transactionId)
                            .idOrden(solicitud.getIdOrden())
                            .estadoTransaccion(confirmacionDePago.getEstado())
                            .codigoAuth(confirmacionDePago.getCodigoAuth())
                            .idCuenta(solicitud.getIdCuenta())
                            .moneda(solicitud.getMoneda())
                            .numeroTarjeta(solicitud.getPago().getNumeroTarjeta())
                            .montoTotal(solicitud.getMonto())
                            .metodo(solicitud.getPago().getMetodo())
                            .fechaTransaccion(new Date())
                            .build();

        Pago resultadoDePago = pagoRepositorio.save(pago);

        return new Confirmacion(resultadoDePago.getIdOrden(), resultadoDePago.getIdCuenta(),
                resultadoDePago.getIdTransaccion(), resultadoDePago.getEstadoTransaccion(),
                resultadoDePago.getCodigoAuth(), resultadoDePago.getFechaTransaccion());
    }

    public List<Pago> findPagosByIdTransaccion(String idTransaccion) {
        return pagoRepositorio.findByIdTransaccion(idTransaccion);
    }

    public List<Pago> findPagosByIdCuenta(String idCuenta) {
        return pagoRepositorio.findByIdCuenta(idCuenta);
    }

    public List<Pago> findPagosByIdOrden(String idOrden) {
        return pagoRepositorio.findByIdOrden(idOrden);
    }
}
