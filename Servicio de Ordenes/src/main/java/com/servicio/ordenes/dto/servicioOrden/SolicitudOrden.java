package com.servicio.ordenes.dto.servicioOrden;

import com.servicio.ordenes.dto.servicioInventario.LineaDeArticulos;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SolicitudOrden {
    @NotBlank
    @NotNull
    @ApiModelProperty(notes = "ID de Cuenta", example = "98124341123", required = true)
    private String idCuenta;

    @ApiModelProperty(notes = "Articulos incluidos en una Orden", required = true)
    private List<LineaDeArticulos> articulos;
}
