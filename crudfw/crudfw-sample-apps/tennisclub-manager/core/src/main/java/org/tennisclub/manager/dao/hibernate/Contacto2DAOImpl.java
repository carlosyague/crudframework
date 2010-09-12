package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Contacto2;
import org.tennisclub.manager.dao.IContacto2DAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Contacto2
 *
 * @author cyague@lcc.uma
 */
public class Contacto2DAOImpl extends GenericDAOImpl<Contacto2, Long> implements IContacto2DAO {

    /**
     * Constructor del DAO
     */
    public Contacto2DAOImpl() {
        super(Contacto2.class);
    }
}
