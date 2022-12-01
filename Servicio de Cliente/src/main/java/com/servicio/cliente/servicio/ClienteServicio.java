package com.servicio.cliente.servicio;

import com.servicio.cliente.entidad.Cuenta;
import com.servicio.cliente.excepcion.ExcepcionCuentaNoEncontrada;
import com.servicio.cliente.repositorio.CuentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    public Cuenta findCuentaById(Long id) {
        Optional<Cuenta> cuenta = cuentaRepositorio.findById(id);
        return cuenta.orElseThrow(() -> new ExcepcionCuentaNoEncontrada(id.toString()));
    }

    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    public void deleteCuenta(Cuenta cuenta) {
        cuentaRepositorio.delete(cuenta);
    }
}
