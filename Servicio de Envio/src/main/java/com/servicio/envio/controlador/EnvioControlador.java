package com.servicio.envio.controlador;

import com.servicio.envio.dto.EnvioDTO;
import com.servicio.envio.entidad.Envio;
import com.servicio.envio.enums.transformador.TransformarEntidadAdto;
import com.servicio.envio.servicio.EnvioServicio;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class EnvioControlador {

    @Autowired
    private EnvioServicio envioServicio;

    @Autowired
    private TransformarEntidadAdto transformarEntidadAdto;

    @ApiOperation(value = "Devuelve un Envio basado en ID de seguimiento", notes = "Ninguno")
    @GetMapping(value = "/envio/idSeguimiento/{idSeguimiento}")
    public ResponseEntity<EnvioDTO> devuelveEnvioByIdSeguimiento(@PathVariable(value = "idSeguimiento") String idSeguimiento) {
        Envio envio = envioServicio.devuelveEnvioByIdSeguimiento(idSeguimiento);
        return new ResponseEntity<>(transformarEntidadAdto.transformarEntidadAdto(envio), HttpStatus.OK);
    }

    @ApiOperation(value = "Devuelve una lista de envios basado en Estado", notes = "Ninguno")
    @GetMapping(value = "/envio/estado/{estado}")
    public ResponseEntity<List<EnvioDTO>> devuelveEnviosByEstado(@PathVariable(value = "estado") String status) {
        List<Envio> envios = envioServicio.devuelveEnvioByEstado(status);
        return new ResponseEntity<>(transformarEntidadAdto.transformarEntidadAdto(envios), HttpStatus.OK);
    }

    @ApiOperation(value = "Devuelve todos los envios", notes = "Ninguno")
    @GetMapping(value = "/envios")
    public ResponseEntity<List<EnvioDTO>> devuelveTodosLosEnvios() {
        List<Envio> envios = envioServicio.devuelveTodosLosEnvios();
        return new ResponseEntity<>(transformarEntidadAdto.transformarEntidadAdto(envios), HttpStatus.OK);
    }
}
