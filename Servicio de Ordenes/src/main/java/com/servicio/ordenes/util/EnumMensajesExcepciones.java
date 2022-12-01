package com.servicio.ordenes.util;

public enum EnumMensajesExcepciones {

    CUENTA_NO_ENCONTRADA("Cuenta No encontrada"),
    SOLICITUD_INCORRECTA_ARTICULOS_VACIOS_EN_ORDEN("No se permite Articulos vacios en una Orden");

    private final String valor;

    EnumMensajesExcepciones(String mensaje) {
        this.valor = mensaje;
    }

    public String getValue() {
        return this.valor;
    }
}
