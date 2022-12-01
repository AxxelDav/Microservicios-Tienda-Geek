package com.servicio.envio.repositorio;

import com.servicio.envio.entidad.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepositorioDeEnvio extends JpaRepository<Envio, Long> {

    public Envio findByIdSeguimiento(String idSeguimiento);

    public List<Envio> findByEstado(String estado);

    public List<Envio> findByEstadoIsNotContaining(String estado);

    @Modifying
    @Transactional
    @Query("UPDATE Envio envio SET envio.estado = :estado WHERE envio.id = :idEnvio ")
    int updateStatus(@Param("idEnvio") long idEnvio, @Param("estado") String estado);
}
