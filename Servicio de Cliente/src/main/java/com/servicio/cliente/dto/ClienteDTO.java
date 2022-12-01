package com.servicio.cliente.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}
