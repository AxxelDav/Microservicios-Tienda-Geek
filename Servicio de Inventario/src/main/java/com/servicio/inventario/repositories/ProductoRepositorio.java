package com.servicio.inventario.repositories;

import com.servicio.inventario.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    public Optional<Producto> findBySku(String sku);
}
