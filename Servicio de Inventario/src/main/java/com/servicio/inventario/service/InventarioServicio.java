package com.servicio.inventario.service;

import com.servicio.inventario.entities.Producto;
import com.servicio.inventario.exceptions.ExcepcionProductoNoEncontrado;
import com.servicio.inventario.repositories.ProductoRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InventarioServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> devuelveTodosLosProductos() {
        return productoRepositorio.findAll();
    }

    public Producto devuelveProductoById(Long idProducto) {
        Optional<Producto> producto = productoRepositorio.findById(idProducto);
        return producto.orElseThrow(() -> new ExcepcionProductoNoEncontrado(idProducto.toString()));
    }

    public Producto devuelveProductoBySku(String sku) {
        Optional<Producto> producto = productoRepositorio.findBySku(sku);
        return producto.orElseThrow(() -> new ExcepcionProductoNoEncontrado(sku));
    }

    public Producto saveProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public Producto updateProducto(Producto productoActualizado) {
        Optional<Producto> productoEncontrado = productoRepositorio.findById(productoActualizado.getId());
        Producto productoActual = productoEncontrado.orElseThrow(() ->
                            new ExcepcionProductoNoEncontrado(productoActualizado.getId().toString()));
       BeanUtils.copyProperties(productoActualizado, productoActual);
        return productoRepositorio.save(productoActual);
    }

    public void deleteProducto(Long idProducto){
        if (!productoRepositorio.existsById(idProducto)){
            log.error("Id Producto: {id} no encontrado. ", idProducto);
            throw new ExcepcionProductoNoEncontrado(idProducto.toString());
        }
        productoRepositorio.deleteById(idProducto);
        log.info("Producto eliminado");
    }
}
