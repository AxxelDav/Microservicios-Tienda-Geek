package com.servicio.ordenes.dto.servicioCliente;

import com.servicio.ordenes.dto.servicioEnvio.DireccionDTO;
import com.servicio.ordenes.util.EstadoDeCuenta;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CuentaDTO {
    private Long id;
    private DireccionDTO direccion;
    private ClienteDTO cliente;
    private TarjetaCreditoDTO tarjetaCredito;
    private EstadoDeCuenta estado;
}
