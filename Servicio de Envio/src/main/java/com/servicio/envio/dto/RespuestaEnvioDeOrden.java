package com.servicio.envio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespuestaEnvioDeOrden {

    @JsonProperty("idOrden")
    private String idOrden;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("emailReceptor")
    private String emailReceptor;

    @JsonProperty("idSeguimiento")
    private String idSeguimiento;

    @JsonProperty("estadoEnvio")
    private String estadoEnvio;

    @JsonProperty("direccion")
    private DireccionDTO direccion;

    @JsonProperty("fechaEnvio")
    private Date fechaEnvio;

    @JsonProperty("fechaEntrega")
    private Date fechaEntrega;
}
