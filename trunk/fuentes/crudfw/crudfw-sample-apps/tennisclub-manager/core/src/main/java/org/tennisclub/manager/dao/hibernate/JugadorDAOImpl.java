package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Jugador;
import org.tennisclub.manager.dao.IJugadorDAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Jugador
 *
 * @author cyague@lcc.uma.es
 */
public class JugadorDAOImpl extends GenericDAOImpl<Jugador, Long> implements IJugadorDAO {

    /**
     * Constructor del DAO
     */
    public JugadorDAOImpl() {
        super(Jugador.class);
    }
}
