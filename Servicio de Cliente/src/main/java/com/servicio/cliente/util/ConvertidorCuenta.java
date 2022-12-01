package com.servicio.cliente.util;

import com.servicio.cliente.dto.CuentaDTO;
import com.servicio.cliente.entidad.Cuenta;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertidorCuenta {

    @Autowired
    private ModelMapper modelMapper;

    public CuentaDTO convertirCuentaACuentaDTO(Cuenta cuenta) {
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    public Cuenta convertirCuentaDTOaEntidad(CuentaDTO cuenta) {
        return modelMapper.map(cuenta, Cuenta.class);
    }

    public Cuenta map(Cuenta cuentaAactualizar, Cuenta cuenta) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(cuentaAactualizar, cuenta);
        return cuenta;
    }
}
