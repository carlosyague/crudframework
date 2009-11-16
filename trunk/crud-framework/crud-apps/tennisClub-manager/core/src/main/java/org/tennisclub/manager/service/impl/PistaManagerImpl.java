
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IPistaDAO;
import org.tennisclub.manager.model.Pista;
import org.tennisclub.manager.service.IPistaManager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IPistaDAO <br/>Entidad: Pista
 *
 * @author cyague@lcc.uma
 */
public class PistaManagerImpl extends GenericManagerImpl<Pista, Long> implements IPistaManager {
    IPistaDAO pistaDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public PistaManagerImpl(IPistaDAO pistaDao) {
        super(pistaDao);
        this.pistaDao = pistaDao;
    }
}