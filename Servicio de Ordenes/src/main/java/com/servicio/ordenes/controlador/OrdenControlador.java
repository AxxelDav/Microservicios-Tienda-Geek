package com.servicio.ordenes.controlador;

import com.servicio.ordenes.dto.servicioOrden.RespuestaOrden;
import com.servicio.ordenes.dto.servicioOrden.SolicitudOrden;
import com.servicio.ordenes.entidad.Orden;
import com.servicio.ordenes.exception.ExcepcionPagoNoAceptado;
import com.servicio.ordenes.servicio.OrdenServicio;
import com.servicio.ordenes.util.TranformarEntidadAdto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
public class OrdenControlador {

    @Autowired
    private OrdenServicio ordenServicio;

    @Autowired
    private TranformarEntidadAdto tranformador;

    @ApiOperation(value = "Devuelve todos los pedidos existentes", notes = "Esta operación devuelve todos los pedidos almacenados")
    @GetMapping(value="orden")
    public ResponseEntity<List<RespuestaOrden>> findAll(){
        List<Orden> ordenes = ordenServicio.findAllOrdenes();
        return new ResponseEntity<>(tranformador.transformarEntidadAdto(ordenes), HttpStatus.OK);
    }


    @ApiOperation(value = "Devuelve una orden basado en un Id", notes = "Esta operación retorna una orden basado en un Id")
    @GetMapping(value="orden/{idOrden}")
    public ResponseEntity<RespuestaOrden> findById(@PathVariable String idOrden){
        Orden orden = ordenServicio.findOrderById(idOrden);
        return new ResponseEntity<>(tranformador.transformarEntidadAdto(orden),HttpStatus.OK);
    }

    @ApiOperation(value = "Devuelve una orden basado en un Id", notes = "Esta operación retorna una orden basado en un Id")
    @GetMapping(value="orden/generado/{idOrden}")
    public ResponseEntity<RespuestaOrden> findByGeneratedId(@PathVariable long idOrden){
        Orden orden = ordenServicio.findById(idOrden);
        return new ResponseEntity<>(tranformador.transformarEntidadAdto(orden),HttpStatus.OK);
    }

    @ApiOperation(value = "Devuelve ordenes basado en un IdCuenta", notes = "Esta operación retorna ordenes basado en un IdCuenta")
    @GetMapping(value="orden/cuenta/{idCuenta}")
    public ResponseEntity<List<RespuestaOrden>> findOrdenesByIdCuenta(@PathVariable String idCuenta){
        List<Orden> ordenes = ordenServicio.findOrdersByAccountId(idCuenta);
        return new ResponseEntity<>(tranformador.transformarEntidadAdto(ordenes),HttpStatus.OK);
    }

    @ApiOperation(value = "Crea una orden", notes = "Esta operación crea una nueva orden")
    @PostMapping(value = "orden/crear")
    public ResponseEntity<RespuestaOrden> crearOrden(@RequestBody SolicitudOrden payload) throws ExcepcionPagoNoAceptado {
        Orden orden = ordenServicio.creacionDeOrden(payload);
        return new ResponseEntity<>(tranformador.transformarEntidadAdto(orden),HttpStatus.CREATED);
    }
}
