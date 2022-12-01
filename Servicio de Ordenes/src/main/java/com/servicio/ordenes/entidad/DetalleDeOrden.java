package com.servicio.ordenes.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DETALLES_DE_ORDEN")
@Entity
public class DetalleDeOrden extends EntidadComun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "IMPUESTO")
    private Double impuesto;

    @Column(name = "UPC")
    private String upc; //CODIGO UNIVERSAL DE PRODUCTO

    @Column(name = "MONTO_TOTAL")
    private Double montoTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    private Orden orden;

}
