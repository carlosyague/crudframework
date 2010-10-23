package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Contacto4;
import org.tennisclub.manager.dao.IContacto4DAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Contacto4
 *
 * @author cyague@lcc.uma
 */
public class Contacto4DAOImpl extends GenericDAOImpl<Contacto4, Long> implements IContacto4DAO {

    /**
     * Constructor del DAO
     */
    public Contacto4DAOImpl() {
        super(Contacto4.class);
    }
}
