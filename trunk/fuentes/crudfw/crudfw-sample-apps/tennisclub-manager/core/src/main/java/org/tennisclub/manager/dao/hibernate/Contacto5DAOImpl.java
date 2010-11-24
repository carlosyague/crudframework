package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Contacto5;
import org.tennisclub.manager.dao.IContacto5DAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Contacto5
 *
 * @author cyague@lcc.uma
 */
public class Contacto5DAOImpl extends GenericDAOImpl<Contacto5, Long> implements IContacto5DAO {

    /**
     * Constructor del DAO
     */
    public Contacto5DAOImpl() {
        super(Contacto5.class);
    }
}
