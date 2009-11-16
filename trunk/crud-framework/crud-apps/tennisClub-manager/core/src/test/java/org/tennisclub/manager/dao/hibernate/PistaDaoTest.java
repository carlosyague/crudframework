
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Pista;
import org.tennisclub.manager.dao.IPistaDAO;
import org.junit.Test;


public class PistaDaoTest extends BaseDAOTestCase {
    private IPistaDAO dao;

    public void setPistaDAO(IPistaDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemovePista() throws Exception {
        Pista pista = new Pista();

        // enter all required fields

        log.debug("adding pista...");
        pista = dao.save(pista);
//
        pista = dao.get(pista.getId());

        assertNotNull(pista.getId());

        log.debug("removing pista...");

        dao.remove(pista.getId());
//
        try {
            dao.get(pista.getId());
            fail("Pista found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}