package com.servicio.ordenes.util;

import com.servicio.ordenes.dto.servicioEnvio.DireccionDTO;

import java.text.DecimalFormat;

public class OrdenUtilidad {

    public static String ObtenerDireccionCompleta(DireccionDTO direccion) {
        if (direccion == null) {
            return "";
        }
        return direccion.getCalle() + ", " + direccion.getCiudad() + ", "
                + direccion.getEstado() + " " + direccion.getCodigoPostal();
    }


    public static String FormatoDecimal(double numero) {
        DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
        return dFormat.format(numero);
    }
}
