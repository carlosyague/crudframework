package org.librae.adminconfig.service;

import java.util.List;

import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.common.service.GenericManager;

/**
 * Interfaz del Manager <br/>
 * DAO: ITipoIdentificacionDao <br/>
 * Entidad: TipoIdentificacion
 * 
 * @author jcisneros
 */
public interface ITipoIdentificacionManager extends
        GenericManager<TipoIdentificacion, Long> {

    /**
     * Obtiene el tipo de identificacion por el codigo.
     * 
     * @param codigo
     *            codigo del tipo de identificacion.
     * @return tipo de identificacion.
     */
    TipoIdentificacion getTipoIdentificacionByCodigo(String codigo);

    /**
     * Obtiene el listado de tipos de identificacion.
     * 
     * @return
     */
    List<TipoIdentificacion> getTiposIdentificacion();

}