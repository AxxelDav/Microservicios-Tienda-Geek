package com.servicio.ordenes.dto.servicioPago;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Confirmacion {
    private String idOrden;
    private String idCuenta;
    private String idTransaccion;
    private String EstadoDeTransaccion;
    private String codigoAutenticacionDeTransaccion;
    private Date fechaTransaccion;
}

