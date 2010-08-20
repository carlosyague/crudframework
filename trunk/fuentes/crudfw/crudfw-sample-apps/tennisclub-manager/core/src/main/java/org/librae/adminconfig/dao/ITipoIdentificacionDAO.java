package org.librae.adminconfig.dao;

import java.util.List;

import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.common.dao.GenericDAO;

/**
 * Interfaz del DAO para la entidad TipoIdentificacion
 * 
 * @author asantamaria
 */
public interface ITipoIdentificacionDAO extends
        GenericDAO<TipoIdentificacion, Long> {

    /**
     * Obtiene el tipo de identificacion por el codigo.
     * 
     * @param codigo
     *            codigo del tipo de identificacion.
     * @return tipo de identificacion.
     */
    TipoIdentificacion getTipoIdentificacionByCodigo(String codigo);

    /**
     * Obtiene los tipos de identificacion ordenados por el codigo.
     * 
     * @return
     */
    List<TipoIdentificacion> getTiposIdentificacion();

}