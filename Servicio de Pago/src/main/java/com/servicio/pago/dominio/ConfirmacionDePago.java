package com.servicio.pago.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfirmacionDePago {
    private String codigoAuth;
    private String estado;
}
