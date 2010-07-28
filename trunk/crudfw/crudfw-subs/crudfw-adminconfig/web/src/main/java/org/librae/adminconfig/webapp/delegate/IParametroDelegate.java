package org.librae.adminconfig.webapp.delegate;

import org.librae.adminconfig.model.Parametro;
import org.librae.adminconfig.webapp.form.ParametroForm;

/**
 * Interfaz para la gestion de parametros.
 * 
 * @author jcisneros
 */
public interface IParametroDelegate {

    /**
     * Método encargado de obtener un Parametro de base de datos.
     * 
     * @param idParametro
     *            identificador del Parametro a eliminar
     * @return Parametro obtenido.
     */
    public Parametro getParametro(final Long idParametro);

    /**
     * Método encargado de guardar un Parametro en base de datos.
     * 
     * @param form
     *            , Formulario de edición de un Parametro.
     * @return Parametro creado.
     **/
    public Parametro guardarParametro(final ParametroForm form);

    /**
     * Método encargado de eliminar un Parametro de base de datos.
     * 
     * @param idParametro
     *            , identificador del Parametro a eliminar
     */
    public void eliminar(final Long idParametro);

}
