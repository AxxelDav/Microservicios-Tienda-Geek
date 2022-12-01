package com.servicio.ordenes.repositorio;

import com.servicio.ordenes.entidad.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioDeOrden extends JpaRepository<Orden, Long> {

    public List<Orden> findOrdensByIdCuenta(String idCuenta);

    public Orden findOrdenByIdOrden(String idOrden);
}
