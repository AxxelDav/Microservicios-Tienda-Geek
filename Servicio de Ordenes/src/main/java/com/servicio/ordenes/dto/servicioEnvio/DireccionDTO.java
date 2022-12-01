package com.servicio.ordenes.dto.servicioEnvio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DireccionDTO {
    private Long id;
    private String calle;
    private String ciudad;
    private String estado;
    private String pais;
    private String codigoPostal;
}
