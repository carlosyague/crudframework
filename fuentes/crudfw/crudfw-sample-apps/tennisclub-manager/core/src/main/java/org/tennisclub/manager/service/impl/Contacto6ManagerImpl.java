
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IContacto6DAO;
import org.tennisclub.manager.model.Contacto6;
import org.tennisclub.manager.service.IContacto6Manager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IContacto6DAO <br/>Entidad: Contacto6
 *
 * @author cyague@lcc.uma
 */
public class Contacto6ManagerImpl extends GenericManagerImpl<Contacto6, Long> implements IContacto6Manager {
    IContacto6DAO contacto6Dao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public Contacto6ManagerImpl(IContacto6DAO contacto6Dao) {
        super(contacto6Dao);
        this.contacto6Dao = contacto6Dao;
    }
}