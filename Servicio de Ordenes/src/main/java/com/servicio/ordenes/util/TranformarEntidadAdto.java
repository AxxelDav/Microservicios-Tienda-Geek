package com.servicio.ordenes.util;

import com.servicio.ordenes.dto.servicioOrden.RespuestaOrden;
import com.servicio.ordenes.entidad.Orden;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TranformarEntidadAdto {

    @Autowired
    private ModelMapper modelMapper;

    public RespuestaOrden transformarEntidadAdto(Orden orden) {
        return modelMapper.map(orden, RespuestaOrden.class);
    }

    public List<RespuestaOrden> transformarEntidadAdto(List<Orden> ordenes) {
        return ordenes
                .stream()
                .map(orden -> transformarEntidadAdto(orden))
                .collect(Collectors.toList());
    }
}
