package com.servicio.pago.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PAGO")
@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_ORDEN")
    private String idOrden;

    @Column(name = "ID_CUENTA")
    private String idCuenta;

    @Column(name = "ID_TRANSACCION")
    private String idTransaccion;

    @Column(name = "ESTADO_TRANSACCION")
    private String estadoTransaccion;

    @Column(name = "CODIGO_AUTH")
    private String codigoAuth;

    @Column(name = "METODO")
    private String metodo;

    @Column(name = "MONEDA")
    private String moneda;

    @Column(name = "NUMERO_TARJETA")
    private String numeroTarjeta;

    @Column(name = "MONTO_TOTAL")
    private Double montoTotal;

    @Column(name = "FECHA_TRANSACCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransaccion;
}
