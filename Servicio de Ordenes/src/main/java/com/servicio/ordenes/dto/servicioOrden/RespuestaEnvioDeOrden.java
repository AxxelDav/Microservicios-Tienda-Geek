package com.servicio.ordenes.dto.servicioOrden;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.servicio.ordenes.dto.servicioEnvio.DireccionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespuestaEnvioDeOrden implements Serializable {

    //Defino las propiedades que voy a recibir en el mensaje
    @JsonProperty("idOrden")
    private String idOrden;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("emailReceptor")
    private String emailReceptor;

    @JsonProperty("idSeguimiento")
    private String idSeguimiento;

    @JsonProperty("EstadoDeEnvio")
    private String EstadoDeEnvio;

    @JsonProperty("direccion")
    private DireccionDTO direccion;

    @JsonProperty("fechaDeEnvio")
    private Date fechaDeEnvio; //fecha de envio

    @JsonProperty("fechaDeEntrega")
    private Date fechaDeEntrega; //fecha de recepcion
}