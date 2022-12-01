package com.servicio.pago.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RespuestaDePago {

    private String idOrden;

    private String idCuenta;

    private String idTransaccion;

    private String estadoTransaccion;

    private String codigoAuth;

    private String metodo;

    private String moneda;

    private String numeroTarjeta;

    private Double montoTotal;

    private Date fechaTransaccion;
}
