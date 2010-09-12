
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.ILlamadaDAO;
import org.tennisclub.manager.model.Llamada;
import org.tennisclub.manager.service.ILlamadaManager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: ILlamadaDAO <br/>Entidad: Llamada
 *
 * @author cyague
 */
public class LlamadaManagerImpl extends GenericManagerImpl<Llamada, Long> implements ILlamadaManager {
    ILlamadaDAO llamadaDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public LlamadaManagerImpl(ILlamadaDAO llamadaDao) {
        super(llamadaDao);
        this.llamadaDao = llamadaDao;
    }
}