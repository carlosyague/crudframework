package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Contacto;
import org.tennisclub.manager.dao.IContactoDAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Contacto
 *
 * @author cyague
 */
public class ContactoDAOImpl extends GenericDAOImpl<Contacto, Long> implements IContactoDAO {

    /**
     * Constructor del DAO
     */
    public ContactoDAOImpl() {
        super(Contacto.class);
    }
}
