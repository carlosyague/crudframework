
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IContacto5DAO;
import org.tennisclub.manager.model.Contacto5;
import org.tennisclub.manager.service.IContacto5Manager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IContacto5DAO <br/>Entidad: Contacto5
 *
 * @author cyague@lcc.uma
 */
public class Contacto5ManagerImpl extends GenericManagerImpl<Contacto5, Long> implements IContacto5Manager {
    IContacto5DAO contacto5Dao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public Contacto5ManagerImpl(IContacto5DAO contacto5Dao) {
        super(contacto5Dao);
        this.contacto5Dao = contacto5Dao;
    }
}