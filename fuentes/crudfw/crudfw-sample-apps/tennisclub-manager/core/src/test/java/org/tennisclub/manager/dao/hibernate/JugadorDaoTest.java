
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Jugador;
import org.tennisclub.manager.dao.IJugadorDAO;
import org.junit.Test;


public class JugadorDaoTest extends BaseDAOTestCase {
    private IJugadorDAO dao;

    public void setJugadorDAO(IJugadorDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveJugador() throws Exception {
        Jugador jugador = new Jugador();

        // enter all required fields

        log.debug("adding jugador...");
        jugador = dao.save(jugador);
//
        jugador = dao.get(jugador.getId());

        assertNotNull(jugador.getId());

        log.debug("removing jugador...");

        dao.remove(jugador.getId());
//
        try {
            dao.get(jugador.getId());
            fail("Jugador found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}