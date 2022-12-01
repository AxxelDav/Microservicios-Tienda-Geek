package com.servicio.pago.dto;

import lombok.Data;

@Data
public class SolicitudDePago {
    private String idOrden;
    private String moneda;
    private String idCuenta;
    private double monto;
    DetalleDePagoDTO pago;
}
