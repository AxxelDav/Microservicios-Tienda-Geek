package com.servicio.envio.config;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class EnvioConfig {

    @Qualifier(value = "entrante")
    @Bean
    public Queue inboundShipmentOrder() {
        return new Queue("ORDEN_ENVIO_ENTRANTE", false, false, false);
    }

    @Qualifier(value = "saliente")
    @Bean
    public Queue outboundShipmentOrder() {
        return new Queue("ORDEN_ENVIO_SALIENTE");
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
