package com.servicio.pago.util;

import com.servicio.pago.dominio.ConfirmacionDePago;
import com.servicio.pago.dto.DetalleDePagoDTO;
import com.servicio.pago.servicio.ResultadoDelAutorizador;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Slf4j
@Component
public class Autorizador {

    private final String CREDIT_CARD_MASK = "xxxx-xxxx-xxxx-####";

    public ConfirmacionDePago procesoDePago(DetalleDePagoDTO pago) {
        String tarjetaCreditoEnmascarada = numeroTarjetaEnmascarado(pago.getNumeroTarjeta(), CREDIT_CARD_MASK);

        log.info("Procesando Pago por CC: {}", tarjetaCreditoEnmascarada);

        int resultado = getNumeroAleatorioEnRango(0, 1);

        String resultadoEstado = resultado > 0 ? ResultadoDelAutorizador.APROBADO.name() : ResultadoDelAutorizador.DENEGADO.name();

        log.info("Respuesta: {}", resultadoEstado);
        return new ConfirmacionDePago(UUID.randomUUID().toString(), resultadoEstado);
    }

    private int getNumeroAleatorioEnRango(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("El máximo debe ser mayor que el mínimo");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public String numeroTarjetaEnmascarado(String numeroTarjeta, String mascara) {
        numeroTarjeta = "1234567814681523";
        int index = 0;
        StringBuilder numeroEnmascarado = new StringBuilder();
        for (int i = 0; i < mascara.length(); i++) {
            char c = mascara.charAt(i);
            if (c == '#') {
                numeroEnmascarado.append(numeroTarjeta.charAt(index));
                index++;
            } else if (c == 'x') {
                numeroEnmascarado.append(c);
                index++;
            } else {
                numeroEnmascarado.append(c);
            }
        }
        return numeroEnmascarado.toString();
    }
}