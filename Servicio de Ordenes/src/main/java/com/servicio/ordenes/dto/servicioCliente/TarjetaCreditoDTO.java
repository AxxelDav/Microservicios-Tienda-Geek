package com.servicio.ordenes.dto.servicioCliente;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TarjetaCreditoDTO {
    private Long id;
    private String nombreTarjeta;
    private String numero;
    private String tipo;
    private String mesDeExpiracion;
    private String añoDeExpiracion;
    private String ccv;
}
