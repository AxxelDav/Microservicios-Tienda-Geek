package com.servicio.envio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EnvioDTO {

    private String idOrden;

    private String nombreReceptor;

    private String emailReceptor;

    private String idSeguimiento;

    private String estado;

    private String proveedor;

    private double precio;

    private Date fechaEnvio;

    private Date fechaEntrega;

    private DireccionDTO direccion;
}
