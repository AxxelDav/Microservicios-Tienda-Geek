package com.servicio.inventario.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "Esta Clase representa un artículo incluido en el Inventario")
public class LineaDeArticulos {

    @ApiModelProperty(notes = "UPC (Codigo Universal de Producto), Largo 12 digitos", example = "13423532433", required = true, position = 0)
    private String upc;

    @ApiModelProperty(notes = "cantidad", example = "5", required = true, position = 1)
    private int cantidad;

    @ApiModelProperty(notes = "precio", example = "10.00", required = true, position = 2)
    private double precio;
}