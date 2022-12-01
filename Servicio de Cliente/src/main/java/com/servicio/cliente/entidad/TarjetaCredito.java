package com.servicio.cliente.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "TARJETA_CREDITO")
@Entity
public class TarjetaCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE_TARJETA")
    private String nombreTarjeta;

    @Column(name = "NUMERO_TARJETA")
    private String numero;

    @Column(name = "TIPO_TARJETA")
    private String tipoTarjeta;

    @Column(name = "MES_EXP")
    private String mesExpiracion;

    @Column(name = "AÑO_EXP")
    private String añoExpiracion;

    @Column(name = "CCV")
    private String ccv;
}
