package com.servicio.ordenes.servicio;

import com.servicio.ordenes.client.PagoServicioClient;
import com.servicio.ordenes.dto.servicioCliente.CuentaDTO;
import com.servicio.ordenes.dto.servicioCliente.TarjetaCreditoDTO;
import com.servicio.ordenes.dto.servicioPago.Confirmacion;
import com.servicio.ordenes.dto.servicioPago.DetalleDePagoDTO;
import com.servicio.ordenes.dto.servicioPago.SolicitudDePago;
import com.servicio.ordenes.entidad.Orden;
import com.servicio.ordenes.util.TipoMoneda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcesoDePagoServicio {

    @Autowired
    private PagoServicioClient pagoClient;

    public Confirmacion procesoDePago(Orden orden, CuentaDTO cuenta) {
        DetalleDePagoDTO detalleDePagoDTO = crearDetalleDePagoDTO(cuenta);
        SolicitudDePago solicitudDePago = crearSolicitudDePago(detalleDePagoDTO, orden);
        return pagoClient.autorizador(solicitudDePago);
    }


    private SolicitudDePago crearSolicitudDePago(DetalleDePagoDTO detalleDePagoDTO, Orden orden) {
        return SolicitudDePago.builder()
                .pago(detalleDePagoDTO)
                .idCuenta(orden.getIdCuenta())
                .monto(orden.getMontoTotal())
                .idOrden(orden.getIdOrden())
                .moneda(TipoMoneda.USD.name())
                .build();
    }


    private DetalleDePagoDTO crearDetalleDePagoDTO(CuentaDTO cuenta) {
        TarjetaCreditoDTO detalleTarjeta = cuenta.getTarjetaCredito();
        return DetalleDePagoDTO.builder().nombreTarjeta(detalleTarjeta.getNumero())
                .codigoTarjeta(detalleTarjeta.getCcv())
                .mesExpiracion(detalleTarjeta.getMesDeExpiracion())
                .añoExpiracion(detalleTarjeta.getAñoDeExpiracion())
                .nombreTarjeta(detalleTarjeta.getNombreTarjeta())
                .metodo(detalleTarjeta.getTipo())
                .build();
    }
}
