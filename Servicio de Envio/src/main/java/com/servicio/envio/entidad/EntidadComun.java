package com.servicio.envio.entidad;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class EntidadComun {

    @Column(name = "FECHA_CREACION")
    @CreatedDate
    private Date fechaCreacion;

    @Column(name = "FECHA_ULTIMA_ACTUALIZACION")
    @LastModifiedDate
    private Date ultimaActualizacion;
}
