package org.librae.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interfaz para el comportamiento comun de las busquedas.
 * 
 * @author jcisneros
 * @param <T>
 *            tipo variable de la interfaz.
 * @param <PK>
 *            primary key para este tipo.
 */
public interface IGenericSearchDAO<T, PK extends Serializable> extends
        GenericDAO<T, PK> {

    /**
     * Método encargado de obtener el listado de entidades según los filtros
     * indicados en el Map.
     * 
     * @param criterios
     *            con los criterios de búsqueda
     * @return lista de entidades del modelo
     */
    List<T> buscar(Map<String, Object> criterios);

    /**
     * Método encargado de obtener el listado de entidades según los filtros
     * indicados en el Map.
     * 
     * @param criterios
     *            con los criterios de búsqueda
     * @return lista de entidades del modelo
     */
    Integer contarTotal(Map<String, Object> criterios);

}