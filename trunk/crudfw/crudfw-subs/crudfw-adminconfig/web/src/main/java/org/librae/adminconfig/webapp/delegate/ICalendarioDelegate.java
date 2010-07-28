package org.librae.adminconfig.webapp.delegate;

import org.librae.adminconfig.webapp.form.CalendarioForm;

/**
 * Interfaz del delegate de calendarios
 *
*/
public interface ICalendarioDelegate{

    /**
     * Valida el calendario actual usando si es necesario
     * datos del antiguo
     *
     * @param nuevoCalendario
     * @param antiguoCalendario
     * @return String con el code del posible error
     */
    public String validar(CalendarioForm nuevoCalendario, CalendarioForm antiguoCalendario);
}
