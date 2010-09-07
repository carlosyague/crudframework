
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Contacto2;
import org.tennisclub.manager.dao.IContacto2DAO;
import org.junit.Test;


public class Contacto2DaoTest extends BaseDAOTestCase {
    private IContacto2DAO dao;

    public void setContacto2DAO(IContacto2DAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveContacto2() throws Exception {
        Contacto2 contacto2 = new Contacto2();

        // enter all required fields

        log.debug("adding contacto2...");
        contacto2 = dao.save(contacto2);
//
        contacto2 = dao.get(contacto2.getId());

        assertNotNull(contacto2.getId());

        log.debug("removing contacto2...");

        dao.remove(contacto2.getId());
//
        try {
            dao.get(contacto2.getId());
            fail("Contacto2 found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}