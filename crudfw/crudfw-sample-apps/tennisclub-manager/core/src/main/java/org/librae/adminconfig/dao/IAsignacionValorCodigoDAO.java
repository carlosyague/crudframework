package org.librae.adminconfig.dao;

import org.librae.adminconfig.model.AsignacionValorCodigo;
import org.librae.common.dao.GenericDAO;

/**
 * Interfaz del DAO para la entidad AsignacionValorCodigo
 * 
 * @author asantamaria
 * @author aropero
 */
public interface IAsignacionValorCodigoDAO extends
        GenericDAO<AsignacionValorCodigo, Long> {

    /**
     * Elmina todos los AsignacionValorCodigo relacionados con una Biblioteca.
     * 
     * @param id
     *            , id de la Biblioteca a actualizar.
     */
    void deleteValoresByBiblioteca(Long id);

}