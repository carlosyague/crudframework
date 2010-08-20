package org.librae.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interfaz para la utilizacion de arboles.
 */
public interface ISearchManager<T, PK extends Serializable> extends
        GenericManager<T, PK> {

    /**
     * Busca una lista de objetos de un conjunto de criterios.
     * 
     * @param criterios
     *            map con los criterios.
     * @return lista de objetos encontrados.
     */
    List<T> buscar(Map<String, Object> criterios);

    /**
     * Busca una lista de objetos de un conjunto de criterios.<br>
     * Usado para evitar la existencia de campos perezosos (Lazy) en los
     * resultados que ofrece Hibernate
     * 
     * @param criterios
     *            map con los criterios.
     * @return lista de objetos encontrados.
     */
    List<T> buscarNonLazy(Map<String, Object> criterios);

}
