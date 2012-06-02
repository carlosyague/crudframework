package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Torneo;
import org.tennisclub.manager.dao.ITorneoDAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Torneo
 *
 * @author Carlos Yag�e
 */
public class TorneoDAOImpl extends GenericDAOImpl<Torneo, Long> implements ITorneoDAO {

    /**
     * Constructor del DAO
     */
    public TorneoDAOImpl() {
        super(Torneo.class);
    }
}
