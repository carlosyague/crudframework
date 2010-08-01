package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IMonedaDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class MonedaDaoTest extends BaseDAOTestCase {
    private IMonedaDAO dao;

    public void setMonedaDAO(final IMonedaDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveMoneda() throws Exception {
    // Moneda moneda = new Moneda("EUR", "123", "Euro", new Pais("SPAIN",
    // "Españarr"));
    //
    // log.debug("adding moneda...");
    // moneda = dao.save(moneda);
    // //
    // moneda = dao.get(moneda.getId());
    //
    // assertNotNull(moneda.getId());
    //
    // log.debug("removing moneda...");
    //
    // dao.remove(moneda.getId());
    // //
    // try {
    // dao.get(moneda.getId());
    // fail("Moneda found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //

    @Test
    public void testVacio() {
        Assert.assertTrue(true);
    }
}