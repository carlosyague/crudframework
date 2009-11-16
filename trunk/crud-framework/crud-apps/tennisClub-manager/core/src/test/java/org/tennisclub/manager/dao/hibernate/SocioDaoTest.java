
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Socio;
import org.tennisclub.manager.dao.ISocioDAO;
import org.junit.Test;


public class SocioDaoTest extends BaseDAOTestCase {
    private ISocioDAO dao;

    public void setSocioDAO(ISocioDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveSocio() throws Exception {
        Socio socio = new Socio();

        // enter all required fields

        log.debug("adding socio...");
        socio = dao.save(socio);
//
        socio = dao.get(socio.getId());

        assertNotNull(socio.getId());

        log.debug("removing socio...");

        dao.remove(socio.getId());
//
        try {
            dao.get(socio.getId());
            fail("Socio found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}