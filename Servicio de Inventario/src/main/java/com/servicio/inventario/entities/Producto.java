package com.servicio.inventario.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.*;

import javax.persistence.*;

import java.util.Date;

@ApiModel(description = "Informacion del Producto")
@JsonPropertyOrder({ "nombre", "descripcion", "sku",  "tamaño", "color",
                    "imagen","upc", "precioUnitario", "unidadesEnStock", "fechaCreada" })
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCTO")
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "NOMBRE")
    private String nombre;

    @NotEmpty
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @NotEmpty
    @Column(name = "SKU")
    private String sku;

    @NotEmpty
    @Column(name = "TAMAÑO")
    private String tamaño;

    @NotEmpty
    @Column(name = "COLOR")
    private String color;

    @Column(name = "IMAGEN")
    private String imagen;

    @NotEmpty
    @Column(name = "UPC")
    private String upc;

    @Min(0)
    @Column(name = "PRECIO_UNITARIO")
    private double precioUnitario;

    @Min(0)
    @Column(name = "UNIDADES_EN_STOCK")
    private Integer unidadesEnStock;

    @ApiModelProperty(hidden = true)
    @Column(name = "FECHA_CREADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreada;
}
