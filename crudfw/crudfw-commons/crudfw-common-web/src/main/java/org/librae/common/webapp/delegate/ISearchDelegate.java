package org.librae.common.webapp.delegate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interfaz para el comportamiento comun para los delegate de búsqueda.
 * 
 * @author jcisneros
 */
public interface ISearchDelegate<T> extends Serializable {

    /**
     * Busca una lista de objetos de un conjunto de criterios.
     * 
     * @return lista de objetos encontrados.
     */
    List<T> buscar(Map<String, Object> criterios);

}
