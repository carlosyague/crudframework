package org.librae.adminconfig.webapp.delegate;

import org.librae.adminconfig.model.Catalogo;
import org.librae.adminconfig.webapp.form.CatalogoForm;

import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de catalogos.
 * 
 * @author impena
 */
public interface ICatalogoDelegate extends ISearchDelegate<Catalogo> {

    /**
     * Método encargado de guardar un Catalogo en base de datos
     * 
     * @param form
     *            , Formulario de edición de un Catalogo.
     * @return Catalogo creado.
     **/
    public Catalogo guardarCatalogo(final CatalogoForm form);

    /**
     * Método encargado de eliminar un Catalogo de base de datos
     * 
     * @param idCatalogo
     *            , identificador del Catalogo a eliminar
     */
    public void eliminar(final Long idCatalogo);

    /**
     * Método encargado de preparar la visualización del formulario de edición
     * de un Catalogo
     * 
     * @param catalogoForm
     *            , formulario de edición de un Catalogo
     */
    void prepararDatosVista(CatalogoForm catalogoForm);

}
