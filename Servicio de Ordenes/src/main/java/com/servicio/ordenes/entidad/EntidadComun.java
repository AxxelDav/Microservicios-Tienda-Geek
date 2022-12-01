package com.servicio.ordenes.entidad;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class EntidadComun implements Serializable {

    @Column(name = "FECHA_CREADA")
    @CreatedDate
    private Date fechaCreada;

    @Column(name = "FECHA_ULTIMA_ACTUALIZACION")
    @LastModifiedDate
    private Date fechaUltimaActualizacion;

}
