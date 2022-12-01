package com.servicio.envio.enums;

import java.util.Calendar;
import java.util.Date;

public class UtileriaFecha {

    public static Date addDays(Date aDate, int numeroDeDias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);

        cal.add(Calendar.DATE, numeroDeDias);

       return cal.getTime();
    }
}
