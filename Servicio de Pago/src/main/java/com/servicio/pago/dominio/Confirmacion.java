package com.servicio.pago.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Confirmacion {
    private String idOrden;
    private String idCuenta;
    private String idTransaccion;
    private String estadoTransaccion;
    private String codigoAuthTransaccion;
    private Date fechaTransaccion;
}
