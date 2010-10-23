
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Contacto4;
import org.tennisclub.manager.dao.IContacto4DAO;
import org.junit.Test;


public class Contacto4DaoTest extends BaseDAOTestCase {
    private IContacto4DAO dao;

    public void setContacto4DAO(IContacto4DAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveContacto4() throws Exception {
        Contacto4 contacto4 = new Contacto4();

        // enter all required fields

        log.debug("adding contacto4...");
        contacto4 = dao.save(contacto4);
//
        contacto4 = dao.get(contacto4.getId());

        assertNotNull(contacto4.getId());

        log.debug("removing contacto4...");

        dao.remove(contacto4.getId());
//
        try {
            dao.get(contacto4.getId());
            fail("Contacto4 found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}