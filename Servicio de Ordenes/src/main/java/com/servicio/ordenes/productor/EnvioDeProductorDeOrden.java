package com.servicio.ordenes.productor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servicio.ordenes.dto.servicioCliente.ClienteDTO;
import com.servicio.ordenes.dto.servicioCliente.CuentaDTO;
import com.servicio.ordenes.dto.servicioEnvio.SolicitudEnvioDeOrden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnvioDeProductorDeOrden {

    @Autowired
    private RabbitTemplate template;

    @Qualifier(value = "saliente")
    private Queue queue;

    public void envio(String idOrden, CuentaDTO cuenta) {
        SolicitudEnvioDeOrden solicitudDeEnvio = new SolicitudEnvioDeOrden();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClienteDTO cliente = cuenta.getCliente();
            String receptorDeEnvio = cliente.getApellido() + ", " + cliente.getNombre();

            solicitudDeEnvio.setIdOrden(idOrden);
            solicitudDeEnvio.setNombre(receptorDeEnvio);
            solicitudDeEnvio.setEmailReceptor(cliente.getEmail());
            solicitudDeEnvio.setDireccionDeEnvio(cuenta.getDireccion());


            Message jsonMessage = MessageBuilder.withBody(objectMapper.writeValueAsString(solicitudDeEnvio).getBytes())
                                                .andProperties(MessagePropertiesBuilder.newInstance().setContentType("application/json")
                                                .build()).build();

            this.template.convertAndSend("ENVIO_ENTRANTE_DE_ORDEN", jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.debug(" [x] Enviado '" + solicitudDeEnvio.toString() + "'");
    }
}
