package com.servicio.envio.consumidor;

import com.servicio.envio.dto.SolicitudEnvioDeOrden;
import com.servicio.envio.servicio.EnvioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnvioDeConsumoDeOrden {

    @Autowired
    private EnvioServicio shipmentService;

    @RabbitListener(queues = "ENVIO_ORDEN_ENTRANTE")
    public void recibe(final SolicitudEnvioDeOrden in) {
        log.debug(" [x] recibido '" + in.getIdOrden() + "'");
        shipmentService.crearEnvio(in);
    }
}
