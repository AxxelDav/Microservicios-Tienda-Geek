package com.servicio.cliente.dto;

import lombok.Data;

@Data
public class TarjetaCreditoDTO {
    private Long id;
    private String nameOnCard;
    private String number;
    private String type;
    private String expirationMonth;
    private String expirationYear;
    private String ccv;
}
