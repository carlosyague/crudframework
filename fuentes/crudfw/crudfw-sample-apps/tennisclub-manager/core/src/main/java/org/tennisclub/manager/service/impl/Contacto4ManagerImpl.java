
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IContacto4DAO;
import org.tennisclub.manager.model.Contacto4;
import org.tennisclub.manager.service.IContacto4Manager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IContacto4DAO <br/>Entidad: Contacto4
 *
 * @author cyague@lcc.uma
 */
public class Contacto4ManagerImpl extends GenericManagerImpl<Contacto4, Long> implements IContacto4Manager {
    IContacto4DAO contacto4Dao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public Contacto4ManagerImpl(IContacto4DAO contacto4Dao) {
        super(contacto4Dao);
        this.contacto4Dao = contacto4Dao;
    }
}