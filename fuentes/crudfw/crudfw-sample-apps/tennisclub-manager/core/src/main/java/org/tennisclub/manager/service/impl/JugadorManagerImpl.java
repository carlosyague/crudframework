
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IJugadorDAO;
import org.tennisclub.manager.model.Jugador;
import org.tennisclub.manager.service.IJugadorManager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IJugadorDAO <br/>Entidad: Jugador
 *
 * @author cyague@lcc.uma.es
 */
public class JugadorManagerImpl extends GenericManagerImpl<Jugador, Long> implements IJugadorManager {
    IJugadorDAO jugadorDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public JugadorManagerImpl(IJugadorDAO jugadorDao) {
        super(jugadorDao);
        this.jugadorDao = jugadorDao;
    }
}