package com.servicio.envio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudEnvioDeOrden implements Serializable {

    @JsonProperty("idOrden")
    private String idOrden;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("emailReceptor")
    private String emailReceptor;

    @JsonProperty("direccionEnvio")
    private DireccionDTO direccionEnvio;
}
