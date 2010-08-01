package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IHorarioDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class HorarioDaoTest extends BaseDAOTestCase {
    @SuppressWarnings("unused")
    private IHorarioDAO dao;

    public void setHorarioDAO(final IHorarioDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveHorario() throws Exception {
    // Biblioteca biblioteca = new Biblioteca("BIB01", "Biblioteca 01");
    // Horario horario = new Horario(biblioteca);
    //
    // // enter all required fields
    //
    // log.debug("adding horario...");
    // horario = dao.save(horario);
    // //
    // horario = dao.get(horario.getId());
    //
    // assertNotNull(horario.getId());
    //
    // log.debug("removing horario...");
    //
    // dao.remove(horario.getId());
    // //
    // try {
    // dao.get(horario.getId());
    // fail("Horario found in database");
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