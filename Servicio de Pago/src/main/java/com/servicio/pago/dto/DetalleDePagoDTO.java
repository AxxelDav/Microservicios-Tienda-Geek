package com.servicio.pago.dto;

import lombok.Data;

@Data
public class DetalleDePagoDTO {

    private String numeroTarjeta;

    private String codigoTarjeta;

    private String mesExpiracion;

    private String anioExpiracion;

    private String metodo;
}
