package com.servicio.cliente.repositorio;

import com.servicio.cliente.entidad.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {
}
