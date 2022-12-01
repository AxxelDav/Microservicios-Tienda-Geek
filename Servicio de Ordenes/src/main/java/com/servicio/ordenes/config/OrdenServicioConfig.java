package com.servicio.ordenes.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Getter
@EnableJpaAuditing
@Configuration
@PropertySource({"classpath:application.properties"})
public class OrdenServicioConfig {

    @Value("${serviciocliente.url}")
    private String urlServicioCliente;

    @Value("${serviciopago.url}")
    private String urlServicioPago;

    @Value("${servicioinventario.url}")
    private String urlServicioInventario;

    @Qualifier(value = "saliente")
    @Bean
    public Queue envioEntranteDeOrden() {
        return new Queue("ORDEN_DE_ENVIO_ENTRANTE", false, false, false);
    }

    @Bean
    public Jackson2JsonMessageConverter convertir() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
