package com.servicio.envio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {

    @JsonProperty("calle")
    private String calle;

    @JsonProperty("ciudad")
    private String ciudad;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("pais")
    private String pais;

    @JsonProperty("codigoPostal")
    private String codigoPostal;
}