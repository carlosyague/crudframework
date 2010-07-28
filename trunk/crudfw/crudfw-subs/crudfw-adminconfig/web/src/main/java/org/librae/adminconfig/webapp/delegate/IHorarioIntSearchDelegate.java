package org.librae.adminconfig.webapp.delegate;

import org.librae.adminconfig.webapp.form.HorarioForm;
import org.librae.common.webapp.delegate.ISearchDelegate;
import org.librae.adminconfig.model.HorarioInt;

/**
 * Interfaz para la gestion de HorarioInt.
 *
 * @author aropero
 */
public interface IHorarioIntSearchDelegate extends ISearchDelegate<HorarioInt> {

    /**
     * Método encargado de preparar la visualización del formulario de edición
     * de un Horario
     *
     * @param form
     *            , formulario de edición de un Horario
     * @param biblioteca
     *            , nombre de la Biblioteca.
     * @return formulario actualizado con los datos.
     */
    public HorarioForm prepararDatosVista(final HorarioForm form,
            String biblioteca);
}
