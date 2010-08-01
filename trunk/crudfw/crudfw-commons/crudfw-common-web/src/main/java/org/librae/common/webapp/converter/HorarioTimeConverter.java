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
public class HorarioTimeConverter extends DateTimeConverter {

    /**
     * Constructor.
     */
    public HorarioTimeConverter() {
        super();
        setTimeZone(TimeZone.getDefault());
        setPattern("HH:mm");
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    public String getPattern() {
        return "HH:mm";
    }

}
