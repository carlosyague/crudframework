
package org.tennisclub.manager.dao.hibernate;

import es.uma.crudframework.exception.CrudException;

import es.uma.crudframework.dao.BaseDAOTestCase;
import org.tennisclub.manager.model.Factura;
import org.tennisclub.manager.dao.IFacturaDAO;
import org.junit.Test;


public class FacturaDaoTest extends BaseDAOTestCase {
    private IFacturaDAO dao;

    public void setFacturaDAO(IFacturaDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveFactura() throws Exception {
        Factura factura = new Factura();

        // enter all required fields

        log.debug("adding factura...");
        factura = dao.save(factura);
//
        factura = dao.get(factura.getId());

        assertNotNull(factura.getId());

        log.debug("removing factura...");

        dao.remove(factura.getId());
//
        try {
            dao.get(factura.getId());
            fail("Factura found in database");
        } catch (CrudException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}