package com.servicio.ordenes.dto.servicioOrden;

import lombok.Data;

@Data
public class RespuestaDetalleDeOrden {

    private Long id;
    private Integer cantidad;
    private Double precio;
    private Double impuesto;
    private String upc;
    private Double montoTotal;
}
