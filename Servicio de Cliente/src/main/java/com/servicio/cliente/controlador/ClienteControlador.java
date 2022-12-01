package com.servicio.cliente.controlador;

import com.servicio.cliente.dto.CuentaDTO;
import com.servicio.cliente.entidad.Cuenta;
import com.servicio.cliente.servicio.ClienteServicio;
import com.servicio.cliente.util.ConvertidorCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ConvertidorCuenta convertidorCuenta;

    @GetMapping(value = "cuenta/{id}")
    public ResponseEntity<CuentaDTO> findCuentaById(@PathVariable("id") Long id) {
        Cuenta cuenta = clienteServicio.findCuentaById(id);
        return new ResponseEntity<>(convertidorCuenta.convertirCuentaACuentaDTO(cuenta), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "cuenta")
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody CuentaDTO cuenta) {
        Cuenta nuevaCuenta = convertidorCuenta.convertirCuentaDTOaEntidad(cuenta);
        nuevaCuenta = clienteServicio.saveCuenta(nuevaCuenta);
        return new ResponseEntity<>(convertidorCuenta.convertirCuentaACuentaDTO(nuevaCuenta), HttpStatus.CREATED);
    }

    @PutMapping(value = "cuenta/{id}")
    public ResponseEntity<CuentaDTO> modificarCuenta(@PathVariable("id") Long id, @RequestBody CuentaDTO cuenta) {
        Cuenta cuentaAactualizar = clienteServicio.findCuentaById(id);
        Cuenta cuentaActualizada = convertidorCuenta.map(convertidorCuenta.convertirCuentaDTOaEntidad(cuenta), cuentaAactualizar);
        cuentaActualizada = clienteServicio.saveCuenta(cuentaActualizada);
        return new ResponseEntity<>(convertidorCuenta.convertirCuentaACuentaDTO(cuentaActualizada), HttpStatus.OK);
    }

    @DeleteMapping(value = "cuenta/{id}")
    public ResponseEntity<CuentaDTO> eliminarCuenta(@PathVariable("id") Long id) {
        Cuenta cuenta = clienteServicio.findCuentaById(id);
        clienteServicio.deleteCuenta(cuenta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
