package com.servicio.inventario.controllers;

import com.servicio.inventario.dto.LineaDeArticulos;
import com.servicio.inventario.entities.Producto;
import com.servicio.inventario.service.InventarioServicio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@Api
@RestController
public class InventarioControlador {

    @Autowired
    private InventarioServicio inventarioServicio;

    @ApiOperation(value = "Devuelve todos los productos existentes en el Inventario", notes = "Ninguno")
    @GetMapping(value = "/productos")
    public ResponseEntity<List<Producto>> devuelveTodosLosProductos(){
        return new ResponseEntity<>(inventarioServicio.devuelveTodosLosProductos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Guardar un nuevo producto en el inventario", notes = "Un nuevo producto tiene que ser populado")
    @PostMapping(value = "/producto")
    public ResponseEntity<Void> saveProducto(@RequestBody Producto producto){
        Producto productObj = inventarioServicio.saveProducto(producto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(productObj.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza el Inventario basado en linea de artículos", notes = "Actualiza inventario en base a linea de artículos")
    @PostMapping(value = "/inventario")
    public ResponseEntity<String> updateInventario(@RequestBody List<LineaDeArticulos> articulos){
        log.info("Actualizando inventario, los siguientes productos son: {}", articulos);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("Linea de Articulos actualizado.", headers, HttpStatus.OK);
    }

    @ApiOperation(value = "Actualiza un producto en el inventario", notes = "Un nuevo producto tiene que ser populado")
    @PutMapping(value = "/producto")
    public ResponseEntity updateProducto(@RequestBody Producto productoActualizado){
        return new ResponseEntity(inventarioServicio.updateProducto(productoActualizado), HttpStatus.OK);
    }

    @ApiOperation(value = "Devuelve un producto del catálogo por ID de producto", notes = "El ID del producto debe agregarse como parte de la URL")
    @GetMapping(value = "/producto/{idProducto}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long idProducto) throws Exception {
        return new ResponseEntity<>(inventarioServicio.devuelveProductoById(idProducto), HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina un producto del catálogo por ID de product", notes = "El ID del producto debe agregarse como parte de la URL")
    @DeleteMapping(value = "/producto/{idProducto}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long idProducto){
        inventarioServicio.deleteProducto(idProducto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Devuelve un producto del catálogo por sku de producto", notes = "El sku del producto debe agregarse como parte de la URL")
    @GetMapping(value = "/producto/sku/{sku}")
    public ResponseEntity<Producto> devuelveProductoBySku(@PathVariable String sku) throws Exception {
        return new ResponseEntity<>(inventarioServicio.devuelveProductoBySku(sku), HttpStatus.OK);
    }
}
