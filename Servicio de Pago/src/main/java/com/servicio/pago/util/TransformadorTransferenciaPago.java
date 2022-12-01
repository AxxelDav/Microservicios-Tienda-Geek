package com.servicio.pago.util;

import com.servicio.pago.dto.RespuestaDePago;
import com.servicio.pago.entidad.Pago;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransformadorTransferenciaPago {
    @Autowired
    private ModelMapper modelMapper;

    public List<RespuestaDePago> transformaListaDePagoAdto(List<Pago> pagos) {

        if (pagos == null || pagos.isEmpty()) {
            return new ArrayList<>();
        }

        return pagos.stream()
                    .map(pago -> transformaPagoAdto(pago))
                    .collect(Collectors.toList());
    }

    public RespuestaDePago transformaPagoAdto(Pago pago) {
        if (pago == null) {
            return new RespuestaDePago();
        }
        return modelMapper.map(pago, RespuestaDePago.class);
    }
}
