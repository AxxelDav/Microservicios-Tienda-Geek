package com.servicio.cliente.dto;

import com.servicio.cliente.util.EstadoDeCuenta;
import lombok.Data;

@Data
public class CuentaDTO {
    private Long id;
    private DireccionDTO direccion;
    private ClienteDTO cliente;
    private TarjetaCreditoDTO tarjetaCredito;
    private EstadoDeCuenta estado;
}
