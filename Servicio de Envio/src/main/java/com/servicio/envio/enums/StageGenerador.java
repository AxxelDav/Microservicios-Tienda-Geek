package com.servicio.envio.enums;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StageGenerador {

    private Map<String, String> stageMap = new HashMap<>();

    public StageGenerador() {
        stageMap.put(EstadoDeEnvioDeOrdenEnum.ESPERANDO_POR_CORREO.name(), EstadoDeEnvioDeOrdenEnum.ENVIADO.name());
        stageMap.put(EstadoDeEnvioDeOrdenEnum.ENVIADO.name(), EstadoDeEnvioDeOrdenEnum.LLEGADA_A_INSTALACION.name());
        stageMap.put(EstadoDeEnvioDeOrdenEnum.LLEGADA_A_INSTALACION.name(), EstadoDeEnvioDeOrdenEnum.FUERA_DE_ENTREGA.name());
        stageMap.put(EstadoDeEnvioDeOrdenEnum.FUERA_DE_ENTREGA.name(), EstadoDeEnvioDeOrdenEnum.ENTREGADO.name());
    }

    public String getSiguienteStage(String currentStage) {
        return  stageMap.getOrDefault(currentStage, EstadoDeEnvioDeOrdenEnum.ENVIADO.name());
    }
}
