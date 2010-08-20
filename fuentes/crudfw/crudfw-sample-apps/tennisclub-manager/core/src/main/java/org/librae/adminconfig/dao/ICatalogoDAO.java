package org.librae.adminconfig.dao;

import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

import org.librae.adminconfig.model.Catalogo;

/**
 * Interfaz del DAO para la entidad Catalogo
 * 
 * @author asantamaria
 * @author impena
 */
public interface ICatalogoDAO extends IGenericSearchDAO<Catalogo, Long>,
        GenericDAO<Catalogo, Long> {

    /**
     * Busca el catálogo a partir de su codigo en Librae.
     * 
     * @param codigo
     * @return
     */
    Catalogo getCatalogoByCodigo(String codigo);

    /**
     * Busca el catálogo a partir de su nombre en Librae.
     * 
     * @param codigo
     * @return
     */
    Catalogo getCatalogoByNombre(String nombre);

}