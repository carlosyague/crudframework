package org.librae.adminconfig.dao;

import org.librae.adminconfig.model.Codigo;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

import java.util.List;

/**
 * Interfaz del DAO para la entidad Codigo
 * 
 * @author asantamaria
 */
public interface ICodigoDAO extends IGenericSearchDAO<Codigo, Long>,
        GenericDAO<Codigo, Long> {

    /**
     * Obtiene el codigo por el id.
     * 
     * @param codigo
     * @return
     */
    Codigo getCodigoByCodigo(final String codigo);

    /**
     * Obtiene si el codigo es unico para ese codigo y tipo de codigo.
     * @param codigo
     * @param idTipoCodigo
     * @return
     */
    List<Codigo> getCodigo (String codigo, Long idTipoCodigo);
    
}