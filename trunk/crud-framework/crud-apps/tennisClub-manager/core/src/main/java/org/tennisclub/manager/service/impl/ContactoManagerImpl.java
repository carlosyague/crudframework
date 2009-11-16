
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IContactoDAO;
import org.tennisclub.manager.model.Contacto;
import org.tennisclub.manager.service.IContactoManager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IContactoDAO <br/>Entidad: Contacto
 *
 * @author cyague
 */
public class ContactoManagerImpl extends GenericManagerImpl<Contacto, Long> implements IContactoManager {
    IContactoDAO contactoDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public ContactoManagerImpl(IContactoDAO contactoDao) {
        super(contactoDao);
        this.contactoDao = contactoDao;
    }
}