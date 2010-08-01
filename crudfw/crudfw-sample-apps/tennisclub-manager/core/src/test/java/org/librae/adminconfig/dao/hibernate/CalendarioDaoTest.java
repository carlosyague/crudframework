package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.ICalendarioDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class CalendarioDaoTest extends BaseDAOTestCase {
    private ICalendarioDAO dao;

    public void setCalendarioDAO(final ICalendarioDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveCalendario() throws Exception {
    // Biblioteca biblioteca = new Biblioteca("BIB01", "Biblioteca 01");
    // Calendario calendario = new Calendario("L,M,X,J,V", biblioteca);
    //
    // // enter all required fields
    //
    // log.debug("adding calendario...");
    // calendario = dao.save(calendario);
    // //
    // calendario = dao.get(calendario.getId());
    //
    // assertNotNull(calendario.getId());
    //
    // log.debug("removing calendario...");
    //
    // dao.remove(calendario.getId());
    // //
    // try {
    // dao.get(calendario.getId());
    // fail("Calendario found in database");
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