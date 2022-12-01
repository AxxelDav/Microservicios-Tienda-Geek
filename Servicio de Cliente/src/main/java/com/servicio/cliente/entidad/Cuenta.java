package com.servicio.cliente.entidad;

import com.servicio.cliente.util.EstadoDeCuenta;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "CUENTA")
@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIRECCION_ID", referencedColumnName = "ID")
    private Direccion direccion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TARJETA_CREDITO_ID", referencedColumnName = "ID")
    private TarjetaCredito tarjetaCredito;

    @Column(name = "ESTADO")
    @Enumerated(value = EnumType.STRING)
    private EstadoDeCuenta estado;
}