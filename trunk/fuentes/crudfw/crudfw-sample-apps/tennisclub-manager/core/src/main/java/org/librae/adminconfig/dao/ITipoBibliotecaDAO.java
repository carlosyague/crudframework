package org.librae.adminconfig.dao;

import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.common.dao.GenericDAO;

/**
 * Interfaz del DAO para la entidad TipoBiblioteca
 * 
 * @author asantamaria
 */
public interface ITipoBibliotecaDAO extends GenericDAO<TipoBiblioteca, Long> {

    /**
     * Obtiene el tipo de biblioteca por el codigo.
     * 
     * @param codigo
     * @return
     */
    TipoBiblioteca getTipoBibliotecaByCodigo(final String codigo);

}