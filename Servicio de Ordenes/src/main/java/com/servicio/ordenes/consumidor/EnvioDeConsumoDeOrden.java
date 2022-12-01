package com.servicio.ordenes.consumidor;

import com.servicio.ordenes.dto.servicioOrden.RespuestaEnvioDeOrden;
import com.servicio.ordenes.servicio.OrdenServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnvioDeConsumoDeOrden {

    @Autowired
    private OrdenServicio ordenServicio;

    @RabbitListener(queues = "ENVIO_SALIENTE_ORDEN")
    public void recibo(final RespuestaEnvioDeOrden in) {
        log.debug(" [x] Informacion de Envio recibida: {}'", in);
        ordenServicio.actualizaEnvioDeOrden(in);
    }
}