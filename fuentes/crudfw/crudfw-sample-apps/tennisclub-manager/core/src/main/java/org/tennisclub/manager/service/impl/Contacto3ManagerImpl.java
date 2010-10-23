
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IContacto3DAO;
import org.tennisclub.manager.model.Contacto3;
import org.tennisclub.manager.service.IContacto3Manager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IContacto3DAO <br/>Entidad: Contacto3
 *
 * @author cyague@lcc.uma
 */
public class Contacto3ManagerImpl extends GenericManagerImpl<Contacto3, Long> implements IContacto3Manager {
    IContacto3DAO contacto3Dao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public Contacto3ManagerImpl(IContacto3DAO contacto3Dao) {
        super(contacto3Dao);
        this.contacto3Dao = contacto3Dao;
    }
}