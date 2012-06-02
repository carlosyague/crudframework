
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Torneo;
import org.tennisclub.manager.dao.ITorneoDAO;
import org.junit.Test;


public class TorneoDaoTest extends BaseDAOTestCase {
    private ITorneoDAO dao;

    public void setTorneoDAO(ITorneoDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveTorneo() throws Exception {
        Torneo torneo = new Torneo();

        // enter all required fields

        log.debug("adding torneo...");
        torneo = dao.save(torneo);
//
        torneo = dao.get(torneo.getId());

        assertNotNull(torneo.getId());

        log.debug("removing torneo...");

        dao.remove(torneo.getId());
//
        try {
            dao.get(torneo.getId());
            fail("Torneo found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}