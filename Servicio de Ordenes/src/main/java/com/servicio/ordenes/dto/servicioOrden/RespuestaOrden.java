package com.servicio.ordenes.dto.servicioOrden;

import com.servicio.ordenes.dto.servicioOrden.RespuestaDetalleDeOrden;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RespuestaOrden {
    private String idOrden;
    private String estado;
    private String idCuenta;
    private Double montoTotal;
    private Double impuestoTotal;
    private Double montoTotalDeImpuesto;
    private Date fechaTransaccion;

    List<RespuestaDetalleDeOrden> detalles;
}
