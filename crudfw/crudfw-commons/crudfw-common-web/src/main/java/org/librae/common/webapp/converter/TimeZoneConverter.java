package org.librae.common.webapp.converter;

import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

/**
 * Converter por defecto de la aplicacion para que muestre la zona horario
 * correctamente. Se añade tambien el patron que tendra por defecto en la
 * aplicacion.
 * 
 * @author jcisneros
 */
public class TimeZoneConverter extends DateTimeConverter {

    /**
     * Constructor.
     */
    public TimeZoneConverter() {
        super();
        setTimeZone(TimeZone.getDefault());
        setPattern("dd/MM/yyyy HH:mm");
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    public String getPattern() {
        return "dd/MM/yyyy HH:mm";
    }

}
