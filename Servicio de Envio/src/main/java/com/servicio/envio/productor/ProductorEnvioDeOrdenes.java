package com.servicio.envio.productor;

import com.servicio.envio.dto.RespuestaEnvioDeOrden;
import com.servicio.envio.entidad.Envio;
import com.servicio.envio.enums.EstadoDeEnvioDeOrdenEnum;
import com.servicio.envio.enums.StageGenerador;
import com.servicio.envio.enums.UtileriaFecha;
import com.servicio.envio.enums.transformador.TransformarEntidadAdto;
import com.servicio.envio.servicio.EnvioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class ProductorEnvioDeOrdenes {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private StageGenerador stageGenerador;

    @Autowired
    private EnvioServicio envioServicio;

    @Autowired
    private TransformarEntidadAdto tranformador;

    @Autowired
    @Qualifier(value = "saliente")
    private Queue queue;

    @Scheduled(fixedDelay = 30000, initialDelay = 500)
    public void envio() {
        List<Envio> envioDeOrdenes = envioServicio.devuelveOrdenesNoEntregadas();

        for (Envio envio : envioDeOrdenes) {
            String nuevoStage = stageGenerador.getSiguienteStage(envio.getEstado());
            envio.setEstado(nuevoStage);

            if (nuevoStage.equals(EstadoDeEnvioDeOrdenEnum.ENVIADO.name())) {
                envio.setFechaEnvio(new Date());
            }
            else if (nuevoStage.equals(EstadoDeEnvioDeOrdenEnum.ENTREGADO.name())) {
                envio.setFechaEntrega(UtileriaFecha.addDays(envio.getFechaEnvio(), 1));
            }

            envioServicio.actualizaEnvio(envio);

            RespuestaEnvioDeOrden respuestaDeEnvio = new RespuestaEnvioDeOrden(envio.getIdOrden(),
                                                                               envio.getNombreReceptor(),
                                                                               envio.getEmailReceptor(),
                                                                               envio.getIdSeguimiento(),
                                                                               nuevoStage,
                                                                               tranformador.transformarAdto(envio.getDireccion()),
                                                                               envio.getFechaEnvio(),
                                                                               envio.getFechaEntrega());

            this.template.convertAndSend(queue.getName(), respuestaDeEnvio);
            log.info(" [x] Enviado: {}", respuestaDeEnvio);
        }
    }
}
