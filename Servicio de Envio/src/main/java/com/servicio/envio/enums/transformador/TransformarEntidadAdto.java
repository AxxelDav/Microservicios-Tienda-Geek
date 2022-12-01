package com.servicio.envio.enums.transformador;

import com.servicio.envio.dto.DireccionDTO;
import com.servicio.envio.dto.EnvioDTO;
import com.servicio.envio.entidad.Direccion;
import com.servicio.envio.entidad.Envio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransformarEntidadAdto {

    @Autowired
    private ModelMapper modelMapper;

    public EnvioDTO transformarEntidadAdto(Envio envio) {
        return modelMapper.map(envio, EnvioDTO.class);
    }

    public List<EnvioDTO> transformarEntidadAdto(List<Envio> envios) {
        return envios.stream()
                .map(envio -> modelMapper.map(envio, EnvioDTO.class))
                .collect(Collectors.toList());
    }

    public Direccion transformarDtoAentidad(DireccionDTO direccion) {
        return modelMapper.map(direccion, Direccion.class);
    }

    public DireccionDTO transformarAdto(Direccion direccion) {
        return modelMapper.map(direccion, DireccionDTO.class);
    }
}
