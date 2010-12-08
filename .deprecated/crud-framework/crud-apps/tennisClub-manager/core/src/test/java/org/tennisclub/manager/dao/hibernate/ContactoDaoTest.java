
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Contacto;
import org.tennisclub.manager.dao.IContactoDAO;
import org.junit.Test;


public class ContactoDaoTest extends BaseDAOTestCase {
    private IContactoDAO dao;

    public void setContactoDAO(IContactoDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveContacto() throws Exception {
        Contacto contacto = new Contacto();

        // enter all required fields

        log.debug("adding contacto...");
        contacto = dao.save(contacto);
//
        contacto = dao.get(contacto.getId());

        assertNotNull(contacto.getId());

        log.debug("removing contacto...");

        dao.remove(contacto.getId());
//
        try {
            dao.get(contacto.getId());
            fail("Contacto found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}