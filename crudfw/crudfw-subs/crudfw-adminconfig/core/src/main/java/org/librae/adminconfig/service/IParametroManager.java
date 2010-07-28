package org.librae.adminconfig.service;

import org.librae.adminconfig.model.Parametro;
import org.librae.common.service.ISearchManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager <br/>
 * DAO: IParametroDAO <br/>
 * Entidad: Parametro
 * 
 * @author jcisneros
 */
public interface IParametroManager extends ISearchManager<Parametro, Long> {

    /**
     * Obtiene el parámetro por el codigo.
     * 
     * @param codigo
     * @return Parametro
     */
    public Parametro getParametroByCodigo(final String codigo);

    /**
     * Elimina el parametro.
     * 
     * @param idParametro
     */
    @Transactional(readOnly = false)
    public void eliminar(Long idParametro);

    /**
     * Guarda el parametro.
     * 
     * @param parametro
     * @return
     */
    @Transactional(readOnly = false)
    public Parametro guardarParametro(Parametro parametro);

}