package com.servicio.ordenes.client;

import com.servicio.ordenes.config.OrdenServicioConfig;
import com.servicio.ordenes.dto.servicioCliente.ClienteDTO;
import com.servicio.ordenes.dto.servicioCliente.CuentaDTO;
import com.servicio.ordenes.dto.servicioEnvio.DireccionDTO;
import com.servicio.ordenes.dto.servicioCliente.TarjetaCreditoDTO;
import com.servicio.ordenes.util.EstadoDeCuenta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Slf4j
@Component
public class ClienteServicioClient {

    private final RestTemplate restTemplate;

    @Autowired
    private OrdenServicioConfig servicioConfig;

    public ClienteServicioClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Optional<CuentaDTO> findCuenta(String idCuenta) {
        Optional<CuentaDTO> resultado = Optional.empty();
        try {
            resultado =  Optional.ofNullable(restTemplate.getForObject(servicioConfig.getUrlServicioCliente() + "/{id}", CuentaDTO.class, idCuenta));
        }
        catch (HttpClientErrorException ex){
            if(ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return resultado;
    }

    public CuentaDTO creacionDeCuenta(CuentaDTO cuenta) {
        return restTemplate.postForObject(servicioConfig.getUrlServicioCliente(), cuenta, CuentaDTO.class);
    }


    public CuentaDTO creacionCuerpoDeCuenta(CuentaDTO cuenta) {
        ResponseEntity<CuentaDTO> respuestaCuenta = restTemplate.postForEntity(servicioConfig.getUrlServicioCliente(), cuenta, CuentaDTO.class);
        log.info("Response:" + respuestaCuenta.getHeaders());
        return respuestaCuenta.getBody();
    }


    public void actualizaCuenta(CuentaDTO cuenta) {
        restTemplate.put(servicioConfig.getUrlServicioCliente() + "/{id}", cuenta, cuenta.getId());
    }


    public void eliminaCuenta(CuentaDTO cuenta) {
        restTemplate.delete(servicioConfig.getUrlServicioCliente() + "/{id}", cuenta.getId());
    }
}
