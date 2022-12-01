package com.servicio.ordenes.client;

import com.servicio.ordenes.config.OrdenServicioConfig;
import com.servicio.ordenes.dto.servicioInventario.LineaDeArticulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InventarioServicioClient {

    private RestTemplate restTemplate;

    @Autowired
    private OrdenServicioConfig servicioConfig;

    public InventarioServicioClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public void actualizaInventario(List<LineaDeArticulos> solicitudArticulos) {
        ResponseEntity<String> respuesta = restTemplate.postForEntity(servicioConfig.getUrlServicioInventario(), solicitudArticulos, String.class);
    }
}
