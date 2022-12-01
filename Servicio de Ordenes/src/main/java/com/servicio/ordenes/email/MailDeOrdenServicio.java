package com.servicio.ordenes.email;

import com.servicio.ordenes.dto.servicioOrden.RespuestaEnvioDeOrden;
import com.servicio.ordenes.entidad.Orden;
import com.servicio.ordenes.util.OrdenUtilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailDeOrdenServicio {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration freemarkerConfig;

    public void sendEmail(Orden orden, RespuestaEnvioDeOrden respuesta) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        Map<String, Object> model = new HashMap();
        model.put("usuario", respuesta.getNombre());
        model.put("irOrden", orden.getIdOrden());
        model.put("estadoDeOrden", orden.getEstado().name());
        model.put("totalDeOrden", OrdenUtilidad.FormatoDecimal(orden.getMontoTotalDeImpuesto()));
        model.put("idSeguimientoDeOrden", respuesta.getIdSeguimiento());

        String fechaDeEnvio = respuesta.getFechaDeEnvio() != null ? String.valueOf(respuesta.getFechaDeEnvio()) : "En Proceso.";
        String fechaDeEntrega = respuesta.getFechaDeEntrega() != null ? String.valueOf(respuesta.getFechaDeEntrega()) : "En Transito.";

        model.put("fechaDeEnvioDeOrden", fechaDeEnvio);
        model.put("fechaDeEntregaoDeRDEN", fechaDeEntrega);
        model.put("direccionDeOrden", OrdenUtilidad.ObtenerDireccionCompleta(respuesta.getDireccion()));

        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");


        Template t = freemarkerConfig.getTemplate("email.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setTo(respuesta.getEmailReceptor());
        helper.setText(text, true);
        helper.setSubject("Tu Orden GeekShirt.com #" + orden.getIdOrden());

        sender.send(message);
    }
}
