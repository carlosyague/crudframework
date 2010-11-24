
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Contacto5;
import org.tennisclub.manager.dao.IContacto5DAO;
import org.junit.Test;


public class Contacto5DaoTest extends BaseDAOTestCase {
    private IContacto5DAO dao;

    public void setContacto5DAO(IContacto5DAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveContacto5() throws Exception {
        Contacto5 contacto5 = new Contacto5();

        // enter all required fields

        log.debug("adding contacto5...");
        contacto5 = dao.save(contacto5);
//
        contacto5 = dao.get(contacto5.getId());

        assertNotNull(contacto5.getId());

        log.debug("removing contacto5...");

        dao.remove(contacto5.getId());
//
        try {
            dao.get(contacto5.getId());
            fail("Contacto5 found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}