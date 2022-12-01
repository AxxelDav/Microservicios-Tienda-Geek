package com.servicio.ordenes.client;

import com.servicio.ordenes.config.OrdenServicioConfig;
import com.servicio.ordenes.dto.servicioPago.Confirmacion;
import com.servicio.ordenes.dto.servicioPago.SolicitudDePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PagoServicioClient {

    private final RestTemplate restTemplate;

    @Autowired
    private OrdenServicioConfig servicioConfig;

    public PagoServicioClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Confirmacion autorizador(SolicitudDePago solicitudDePago) {
        Confirmacion confirmacion = restTemplate.postForObject(servicioConfig.getUrlServicioPago(), solicitudDePago, Confirmacion.class);
        return confirmacion;
    }
}
