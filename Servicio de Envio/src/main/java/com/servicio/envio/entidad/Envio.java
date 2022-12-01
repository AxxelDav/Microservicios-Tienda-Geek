package com.servicio.envio.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Envio extends EntidadComun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_ORDEN")
    private String idOrden;

    @Column(name = "ID_SEGUIMIENTO")
    private String idSeguimiento;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "PROVEEDOR")
    private String proveedor;

    @Column(name = "PRECIO")
    private double precio;

    @Column(name = "FECHA_ENVIO")
    private Date fechaEnvio;

    @Column(name = "FECHA_ENTREGA")
    private Date fechaEntrega;

    @Column(name = "NOMBRE_RECEPTOR")
    private String nombreReceptor;

    @Column(name = "EMAIL_RECEPTOR")
    private String emailReceptor;

    @ManyToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private Direccion direccion;
}
