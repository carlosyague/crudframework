
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IContacto2DAO;
import org.tennisclub.manager.model.Contacto2;
import org.tennisclub.manager.service.IContacto2Manager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IContacto2DAO <br/>Entidad: Contacto2
 *
 * @author cyague@lcc.uma
 */
public class Contacto2ManagerImpl extends GenericManagerImpl<Contacto2, Long> implements IContacto2Manager {
    IContacto2DAO contacto2Dao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public Contacto2ManagerImpl(IContacto2DAO contacto2Dao) {
        super(contacto2Dao);
        this.contacto2Dao = contacto2Dao;
    }
}