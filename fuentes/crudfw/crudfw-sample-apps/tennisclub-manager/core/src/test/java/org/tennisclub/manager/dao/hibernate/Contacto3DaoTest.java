
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Contacto3;
import org.tennisclub.manager.dao.IContacto3DAO;
import org.junit.Test;


public class Contacto3DaoTest extends BaseDAOTestCase {
    private IContacto3DAO dao;

    public void setContacto3DAO(IContacto3DAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveContacto3() throws Exception {
        Contacto3 contacto3 = new Contacto3();

        // enter all required fields

        log.debug("adding contacto3...");
        contacto3 = dao.save(contacto3);
//
        contacto3 = dao.get(contacto3.getId());

        assertNotNull(contacto3.getId());

        log.debug("removing contacto3...");

        dao.remove(contacto3.getId());
//
        try {
            dao.get(contacto3.getId());
            fail("Contacto3 found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}