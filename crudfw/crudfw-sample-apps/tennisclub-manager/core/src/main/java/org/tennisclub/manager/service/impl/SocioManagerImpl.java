
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.ISocioDAO;
import org.tennisclub.manager.model.Socio;
import org.tennisclub.manager.service.ISocioManager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: ISocioDAO <br/>Entidad: Socio
 *
 * @author cyague
 */
public class SocioManagerImpl extends GenericManagerImpl<Socio, Long> implements ISocioManager {
    ISocioDAO socioDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public SocioManagerImpl(ISocioDAO socioDao) {
        super(socioDao);
        this.socioDao = socioDao;
    }
}