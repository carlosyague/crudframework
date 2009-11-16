package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Pista;
import org.tennisclub.manager.dao.IPistaDAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Pista
 *
 * @author cyague@lcc.uma
 */
public class PistaDAOImpl extends GenericDAOImpl<Pista, Long> implements IPistaDAO {

    /**
     * Constructor del DAO
     */
    public PistaDAOImpl() {
        super(Pista.class);
    }
}
