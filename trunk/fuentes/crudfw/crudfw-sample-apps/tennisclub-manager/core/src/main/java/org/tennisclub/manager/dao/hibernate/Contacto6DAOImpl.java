package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Contacto6;
import org.tennisclub.manager.dao.IContacto6DAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Contacto6
 *
 * @author cyague@lcc.uma
 */
public class Contacto6DAOImpl extends GenericDAOImpl<Contacto6, Long> implements IContacto6DAO {

    /**
     * Constructor del DAO
     */
    public Contacto6DAOImpl() {
        super(Contacto6.class);
    }
}
