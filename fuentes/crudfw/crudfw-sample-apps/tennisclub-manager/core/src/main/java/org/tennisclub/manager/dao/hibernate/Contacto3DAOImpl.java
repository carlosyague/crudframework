package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Contacto3;
import org.tennisclub.manager.dao.IContacto3DAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Contacto3
 *
 * @author cyague@lcc.uma
 */
public class Contacto3DAOImpl extends GenericDAOImpl<Contacto3, Long> implements IContacto3DAO {

    /**
     * Constructor del DAO
     */
    public Contacto3DAOImpl() {
        super(Contacto3.class);
    }
}
