package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IPaisDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class PaisDaoTest extends BaseDAOTestCase {
    @SuppressWarnings("unused")
    private IPaisDAO dao;

    public void setPaisDAO(final IPaisDAO dao) {
        this.dao = dao;
    }

    // public void testAddAndRemovePais() throws Exception {
    // Pais pais = new Pais("ESP", "España");
    //
    // // enter all required fields
    //
    // log.debug("adding pais...");
    // pais = dao.save(pais);
    // //
    // pais = dao.get(pais.getId());
    //
    // assertNotNull(pais.getId());
    //
    // log.debug("removing pais...");
    //
    // dao.remove(pais.getId());
    // //
    // try {
    // dao.get(pais.getId());
    // fail("Pais found in database");
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