package com.servicio.ordenes.dto.servicioCliente;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}
