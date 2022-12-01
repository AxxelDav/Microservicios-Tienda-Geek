package com.servicio.ordenes.dto.servicioPago;

import com.servicio.ordenes.dto.servicioPago.DetalleDePagoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitudDePago {

    private String idOrden;
    private String moneda;
    private String idCuenta;
    private Double monto;
    DetalleDePagoDTO pago;
}
