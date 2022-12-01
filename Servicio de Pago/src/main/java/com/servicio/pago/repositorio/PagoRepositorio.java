package com.servicio.pago.repositorio;

import com.servicio.pago.entidad.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago, Long> {
    public List<Pago> findByIdOrden(String orderId);
    public List<Pago> findByIdTransaccion(String idTransaccion);
    public List<Pago> findByIdCuenta(String idCuenta);
}
