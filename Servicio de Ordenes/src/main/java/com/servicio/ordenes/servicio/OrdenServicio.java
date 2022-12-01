package com.servicio.ordenes.servicio;

import com.servicio.ordenes.client.ClienteServicioClient;
import com.servicio.ordenes.client.InventarioServicioClient;
import com.servicio.ordenes.dto.servicioPago.Confirmacion;
import com.servicio.ordenes.dto.servicioCliente.CuentaDTO;
import com.servicio.ordenes.dto.servicioOrden.RespuestaEnvioDeOrden;
import com.servicio.ordenes.dto.servicioOrden.SolicitudOrden;
import com.servicio.ordenes.entidad.DetalleDeOrden;
import com.servicio.ordenes.entidad.Orden;
import com.servicio.ordenes.exception.ExcepcionCuentaNoEncontrada;
import com.servicio.ordenes.exception.ExcepcionOrdenNoEncontrada;
import com.servicio.ordenes.exception.ExcepcionPagoNoAceptado;
import com.servicio.ordenes.email.MailDeOrdenServicio;
import com.servicio.ordenes.productor.EnvioDeProductorDeOrden;
import com.servicio.ordenes.repositorio.RepositorioDeOrden;
import com.servicio.ordenes.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrdenServicio {

    @Autowired
    private RepositorioDeOrden repositorioDeOrden;

    @Autowired
    private ProcesoDePagoServicio servicioDePago;

    @Autowired
    private ClienteServicioClient clienteClient;

    @Autowired
    private InventarioServicioClient inventarioClient;

    @Autowired
    private EnvioDeProductorDeOrden mensajeDeProductorDeEnvio;

    @Autowired
    private MailDeOrdenServicio mailService;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Orden creacionDeOrden(SolicitudOrden solicitudOrden) throws ExcepcionPagoNoAceptado {

        ValidaOrden.validarOrden(solicitudOrden);

        CuentaDTO cuenta = clienteClient.findCuenta(solicitudOrden.getIdCuenta())
                .orElseThrow(() -> new ExcepcionCuentaNoEncontrada(EnumMensajesExcepciones.CUENTA_NO_ENCONTRADA.getValue()));

        Orden nuevaOrden = initOrder(solicitudOrden);

        Confirmacion confirmacion = servicioDePago.procesoDePago(nuevaOrden, cuenta);

        log.info("Confirmacion del Pago: {}", confirmacion);

        String estadoDePago = confirmacion.getEstadoDeTransaccion();
        nuevaOrden.setEstadoDePago(EstadoDePagoDeOrden.valueOf(estadoDePago));

        if (estadoDePago.equals(EstadoDePagoDeOrden.DENEGADA.name())) {
            nuevaOrden.setEstado(EstadoDeOrden.NO_ASISTIDO);
            repositorioDeOrden.save(nuevaOrden);
            throw new ExcepcionPagoNoAceptado("El pago agregado a la cuenta no fue aceptado, por favor verifíquelo.");
        }

        log.info("Updating Inventory: {}", solicitudOrden.getArticulos());
        inventarioClient.actualizaInventario(solicitudOrden.getArticulos());

        log.info("Enviando solicitud al Servicio de Envío.");
        mensajeDeProductorDeEnvio.envio(nuevaOrden.getIdOrden(), cuenta);

        return repositorioDeOrden.save(nuevaOrden);
    }

    private Orden initOrder(SolicitudOrden solicitudOrden) {
        Orden ordenObj = new Orden();
        //Empiezo a popular los datos
        ordenObj.setIdOrden(UUID.randomUUID().toString());
        ordenObj.setIdCuenta(ordenObj.getIdCuenta());
        ordenObj.setEstado(EstadoDeOrden.PENDIENTE);

        List<DetalleDeOrden> detallesDeOrden = solicitudOrden.getArticulos().stream()
                                                                            .map(articulo -> DetalleDeOrden.builder()
                                                                                    .precio(articulo.getPrecio())
                                                                                    .cantidad(articulo.getCantidad())
                                                                                    .upc(articulo.getUpc())
                                                                                    .impuesto((articulo.getPrecio() * articulo.getCantidad()) * Constantes.IMPORTE_IMPUESTO)
                                                                                    .montoTotal((articulo.getPrecio() * articulo.getCantidad()))
                                                                                    .orden(ordenObj).build())
                                                                            .collect(Collectors.toList());

        ordenObj.setDetalles(detallesDeOrden);
        ordenObj.setMontoTotal(detallesDeOrden.stream().mapToDouble(DetalleDeOrden::getMontoTotal).sum());
        ordenObj.setImpuestoTotal(ordenObj.getMontoTotal() * Constantes.IMPORTE_IMPUESTO);
        ordenObj.setImpuestoTotal(ordenObj.getMontoTotal() + ordenObj.getImpuestoTotal());
        ordenObj.setFechaTransaccion(new Date());

        return ordenObj;
    }

    @Cacheable(value = "ordenes")
    public List<Orden> findAllOrdenes(){
        return repositorioDeOrden.findAll();
    }

    public Orden findOrderById(String idOrden){
        Optional<Orden> orden = Optional.ofNullable(repositorioDeOrden.findOrdenByIdOrden(idOrden));
        return orden
                .orElseThrow(() -> new ExcepcionOrdenNoEncontrada("Orden NO encontrada"));
    }

    public Orden findById(Long id){
        return repositorioDeOrden.findById(id)
                .orElseThrow(() -> new ExcepcionOrdenNoEncontrada("Orden NO encontrada"));
    }

    @Cacheable(value = "CuentaOrdenes", key = "#idCuenta")
    public List<Orden> findOrdersByAccountId(String idCuenta) {
        Optional<List<Orden>> ordenes = Optional.ofNullable(repositorioDeOrden.findOrdensByIdCuenta(idCuenta));
        return ordenes
                .orElseThrow(() -> new ExcepcionOrdenNoEncontrada("Las Ordenes NO han sido encontradas"));
    }

    public void actualizaEnvioDeOrden(RespuestaEnvioDeOrden respuesta) {
        try {
            Orden orden = findOrderById(respuesta.getIdOrden());
            orden.setEstado(EstadoDeOrden.valueOf(respuesta.getEstadoDeEnvio()));
            repositorioDeOrden.save(orden);
            mailService.sendEmail(orden, respuesta);
        }
        catch(ExcepcionOrdenNoEncontrada ordenNoEncontrada) {
            log.info("La siguiente Orden No ha sido encontrada: {} con el idSeguimiento: {}", respuesta.getIdOrden(), respuesta.getIdSeguimiento());
        }
        catch(Exception e) {
            log.info("Ha ocurrido un error al enviar el mail: " + e.getMessage());
        }
    }
}
