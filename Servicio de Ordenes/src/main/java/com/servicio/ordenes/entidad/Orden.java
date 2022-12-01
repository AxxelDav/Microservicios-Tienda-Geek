package com.servicio.ordenes.entidad;

import com.servicio.ordenes.util.EstadoDeOrden;
import com.servicio.ordenes.util.EstadoDePagoDeOrden;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "ORDENES")
@Entity
public class Orden extends EntidadComun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_ORDEN")
    private String idOrden;

    @Column(name = "ESTADO_ORDEN")
    private EstadoDeOrden estado;

    @Column(name = "ID_CUENTA")
    private String idCuenta;

    @Column(name = "MONTO_TOTAL")
    private Double montoTotal;

    @Column(name = "IMPUESTO_TOTAL")
    private Double impuestoTotal;

    @Column(name = "MONTO_TOTAL_IMPUESTO")
    private Double montoTotalDeImpuesto;

    @Column(name = "ESTADO_PAGO")
    @Enumerated(value = EnumType.STRING)
    private EstadoDePagoDeOrden estadoDePago;

    @Column(name = "FECHA_TRANSACCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransaccion;

    //ESTABLEZCO LA RELACION ENTRE LAS DOS ENTIDADES, DONDE LA ENTIDAD PADRE ES LA ORDEN. ES RECOMENDABLE QUE LA RELACION SEA BIDIRECCIONAL
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "orden" )
    private List<DetalleDeOrden> detalles;

}
