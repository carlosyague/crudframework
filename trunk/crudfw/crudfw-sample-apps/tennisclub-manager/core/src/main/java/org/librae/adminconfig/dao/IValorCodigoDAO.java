package org.librae.adminconfig.dao;

import org.librae.adminconfig.model.ValorCodigo;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad ValorCodigoBiblioteca
 * 
 * @author asantamaria
 */
public interface IValorCodigoDAO extends IGenericSearchDAO<ValorCodigo, Long>,
        GenericDAO<ValorCodigo, Long> {

}