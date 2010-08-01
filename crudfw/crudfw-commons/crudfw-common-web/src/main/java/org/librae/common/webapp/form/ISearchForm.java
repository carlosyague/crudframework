package org.librae.common.webapp.form;

import java.io.Serializable;
import java.util.Map;

/**
 * Interfaz para el comportamiento comun para los formularios de búsqueda.
 *
 * @author jcisneros
 */
public interface ISearchForm  extends Serializable {

    /**
     * Pasa los valores del formulario a un Map.
     *
     * @return
     */
    public Map<String, Object> toMap();


    /**
     * Pasa los valores de un map al formulario.
     *
     * @param Map
     */
    public void fromMap(Map<String, Object> criterios);

}
