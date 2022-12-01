package com.servicio.ordenes.dto.servicioPago;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalleDePagoDTO {
    private String numeroTarjeta;
    private String nombreTarjeta;
    private String codigoTarjeta;
    private String mesExpiracion;
    private String añoExpiracion;
    private String metodo;
}
