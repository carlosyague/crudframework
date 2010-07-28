package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IFestivoDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class FestivoDaoTest extends BaseDAOTestCase {
    private IFestivoDAO dao;

    public void setFestivoDAO(final IFestivoDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveFestivo() throws Exception {
    // Biblioteca biblioteca = new Biblioteca("BIB01", "Biblioteca 01");
    // Calendario calendario = new Calendario("L,M,X,J,V", biblioteca);
    // Date fechaFestivo = new Date();
    // Festivo festivo = new Festivo(calendario, fechaFestivo);
    //
    // // enter all required fields
    // festivo.setFechaFestivo(new java.util.Date());
    //
    // log.debug("adding festivo...");
    // festivo = dao.save(festivo);
    // //
    // festivo = dao.get(festivo.getId());
    //
    // assertNotNull(festivo.getId());
    //
    // log.debug("removing festivo...");
    //
    // dao.remove(festivo.getId());
    // //
    // try {
    // dao.get(festivo.getId());
    // fail("Festivo found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }

    @Test
    public void testVacio() {
        Assert.assertTrue(true);
    }
}