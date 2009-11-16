
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Llamada;
import org.tennisclub.manager.dao.ILlamadaDAO;
import org.junit.Test;


public class LlamadaDaoTest extends BaseDAOTestCase {
    private ILlamadaDAO dao;

    public void setLlamadaDAO(ILlamadaDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveLlamada() throws Exception {
        Llamada llamada = new Llamada();

        // enter all required fields

        log.debug("adding llamada...");
        llamada = dao.save(llamada);
//
        llamada = dao.get(llamada.getId());

        assertNotNull(llamada.getId());

        log.debug("removing llamada...");

        dao.remove(llamada.getId());
//
        try {
            dao.get(llamada.getId());
            fail("Llamada found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}