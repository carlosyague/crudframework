package org.librae.adminconfig.webapp.delegate;

import org.librae.adminconfig.webapp.form.DireccionForm;

/**
 * Interfaz del delegado para la getion de direcciones.
 *
 * @author aropero
 */
public interface IDireccionDelegate {

    /**
     * Método que valida que la direccion al menos contenga un nombre de calle
     *
     * @param form
     *            , formulario de direccion.
     * @return String, nombre del error producido o cadena vacia en caso de que
     *         todo vaya bien.
     */
    public String validar(DireccionForm form);

}
