
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Contacto6;
import org.tennisclub.manager.dao.IContacto6DAO;
import org.junit.Test;


public class Contacto6DaoTest extends BaseDAOTestCase {
    private IContacto6DAO dao;

    public void setContacto6DAO(IContacto6DAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveContacto6() throws Exception {
        Contacto6 contacto6 = new Contacto6();

        // enter all required fields

        log.debug("adding contacto6...");
        contacto6 = dao.save(contacto6);
//
        contacto6 = dao.get(contacto6.getId());

        assertNotNull(contacto6.getId());

        log.debug("removing contacto6...");

        dao.remove(contacto6.getId());
//
        try {
            dao.get(contacto6.getId());
            fail("Contacto6 found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}