
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.ITorneoDAO;
import org.tennisclub.manager.model.Torneo;
import org.tennisclub.manager.service.ITorneoManager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * ImplementaciÃ³n del Manager <br/>DAO: ITorneoDAO <br/>Entidad: Torneo
 *
 * @author Carlos Yagüe
 */
public class TorneoManagerImpl extends GenericManagerImpl<Torneo, Long> implements ITorneoManager {
    ITorneoDAO torneoDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public TorneoManagerImpl(ITorneoDAO torneoDao) {
        super(torneoDao);
        this.torneoDao = torneoDao;
    }
}