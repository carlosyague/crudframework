package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.ICatalogoDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class CatalogoDaoTest extends BaseDAOTestCase {
    private ICatalogoDAO dao;

    public void setCatalogoDAO(final ICatalogoDAO dao) {
        this.dao = dao;
    }

    //
    // @Test
    // public void testAddAndRemoveCatalogo() throws Exception {
    // Catalogo catalogo = new Catalogo("CAT04", "Catalogo 04");
    //
    // // enter all required fields
    //
    // log.debug("adding catalogo...");
    // catalogo = dao.save(catalogo);
    // //
    // catalogo = dao.get(catalogo.getId());
    //
    // assertNotNull(catalogo.getId());
    //
    // log.debug("removing catalogo...");
    //
    // dao.remove(catalogo.getId());
    // //
    // try {
    // dao.get(catalogo.getId());
    // fail("Catalogo found in database");
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