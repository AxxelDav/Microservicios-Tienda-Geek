package com.servicio.ordenes.dto.servicioInventario;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "Esta Clase Representa un articulo incluido en una orden")
public class LineaDeArticulos {
    @ApiModelProperty(notes = "UPC (Codigo de Producto Universal), 12 digitos de largo", example = "123451234123", required = true, position = 0)
    private String upc;
    @ApiModelProperty(notes = "Cantidad", example = "5", required = true, position = 1)
    private Integer cantidad;
    @ApiModelProperty(notes = "Precio", example = "10.00", required = true, position = 2)
    private Double precio;
}
