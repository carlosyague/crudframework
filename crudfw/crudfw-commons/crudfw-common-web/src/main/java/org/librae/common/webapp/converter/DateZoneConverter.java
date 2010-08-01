package org.librae.common.webapp.converter;

import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

/**
 * Converter por defecto de la aplicacion para que muestre la zona horario
 * correctamente. Se añade tambien el patron que tendra por defecto en la
 * aplicacion.
 * 
 * @author jcdiaz
 */
public class DateZoneConverter extends DateTimeConverter {

    /**
     * Constructor.
     */
    public DateZoneConverter() {
        super();
        setTimeZone(TimeZone.getDefault());
        setPattern("dd/MM/yyyy");
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    public String getPattern() {
        return "dd/MM/yyyy";
    }

}
