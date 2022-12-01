package com.servicio.envio.servicio;

import com.servicio.envio.dto.ProveedorSelector;
import com.servicio.envio.dto.SolicitudEnvioDeOrden;
import com.servicio.envio.entidad.Direccion;
import com.servicio.envio.entidad.Envio;
import com.servicio.envio.enums.EstadoDeEnvioDeOrdenEnum;
import com.servicio.envio.enums.transformador.TransformarEntidadAdto;
import com.servicio.envio.repositorio.RepositorioDeEnvio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioServicio {
    @Autowired
    private ProveedorSelector proveedorSelector;

    @Autowired
    private RepositorioDeEnvio repositorioDeEnvio;

    @Autowired
    private TransformarEntidadAdto transformador;

    public Envio crearEnvio(SolicitudEnvioDeOrden envioDeOrden) {

        Direccion direccion = transformador.transformarDtoAentidad(envioDeOrden.getDireccionEnvio());

        Envio nuevoEnvio = Envio.builder().proveedor(proveedorSelector.selector())
                                           .idOrden(envioDeOrden.getIdOrden())
                                           .nombreReceptor(envioDeOrden.getNombre())
                                           .emailReceptor(envioDeOrden.getEmailReceptor())
                                           .direccion(direccion)
                                           .idSeguimiento(proveedorSelector.generadorIdSeguimiento())
                                           .precio(100.00)
                                           .estado(EstadoDeEnvioDeOrdenEnum.ESPERANDO_POR_CORREO.name())
                                           .build();

        return repositorioDeEnvio.save(nuevoEnvio);
    }

    public List<Envio> devuelveOrdenesNoEntregadas() {
        return repositorioDeEnvio.findByEstadoIsNotContaining(EstadoDeEnvioDeOrdenEnum.ENTREGADO.name());
    }

    public void actualizacionDeEstadoDeEnvio(long shipmentId, String status) {
        repositorioDeEnvio.updateStatus(shipmentId, status);
    }

    public void actualizaEnvio(Envio envio) {
        repositorioDeEnvio.save(envio);
    }

    public Envio devuelveEnvioByIdSeguimiento(String idSeguimiento) {
        return repositorioDeEnvio.findByIdSeguimiento(idSeguimiento);
    }

    public List<Envio> devuelveEnvioByEstado(String status) {
        return repositorioDeEnvio.findByEstado(status);
    }

    public List<Envio> devuelveTodosLosEnvios() {
        return repositorioDeEnvio.findAll();
    }
}