
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Cliente;
import org.tennisclub.manager.dao.IClienteDAO;
import org.junit.Test;


public class ClienteDaoTest extends BaseDAOTestCase {
    private IClienteDAO dao;

    public void setClienteDAO(IClienteDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveCliente() throws Exception {
        Cliente cliente = new Cliente();

        // enter all required fields

        log.debug("adding cliente...");
        cliente = dao.save(cliente);
//
        cliente = dao.get(cliente.getId());

        assertNotNull(cliente.getId());

        log.debug("removing cliente...");

        dao.remove(cliente.getId());
//
        try {
            dao.get(cliente.getId());
            fail("Cliente found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}