package com.servicio.cliente.dto;

import lombok.Data;

@Data
public class DireccionDTO {
    private Long id;
    private String calle;
    private String ciudad;
    private String estado;
    private String pais;
    private String codigoPostal;
}
