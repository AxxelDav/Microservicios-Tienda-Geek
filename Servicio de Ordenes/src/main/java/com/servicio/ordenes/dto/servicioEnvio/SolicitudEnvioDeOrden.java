package com.servicio.ordenes.dto.servicioEnvio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.servicio.ordenes.dto.servicioEnvio.DireccionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudEnvioDeOrden implements Serializable {

    @JsonProperty("idOrden")
    private String idOrden;

    @JsonProperty("nombre")
    private String nombre; //nombre de quien va la orden

    @JsonProperty("emailReceptor")
    private String emailReceptor; //cual seria el correo que va a recibir el mensaje

    @JsonProperty("direccionDeEnvio")
    private DireccionDTO direccionDeEnvio; //la direccion hacia donde va a ir el mensaje
}
