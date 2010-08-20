package org.librae.adminconfig.dao;

import org.librae.adminconfig.model.Parametro;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad Parametro
 * 
 * @author asantamaria
 */
public interface IParametroDAO extends IGenericSearchDAO<Parametro, Long>,
        GenericDAO<Parametro, Long> {

    /**
     * Obtiene el parámetro por el codigo.
     * 
     * @param codigo
     * @return Parametro
     */
    public Parametro getParametroByCodigo(final String codigo);

}