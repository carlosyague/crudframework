package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IHorarioIntDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class HorarioIntDaoTest extends BaseDAOTestCase {
    @SuppressWarnings("unused")
    private IHorarioIntDAO dao;

    public void setHorarioIntDAO(final IHorarioIntDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveHorarioInt() throws Exception {
    // HorarioInt horarioInt = new HorarioInt(new Horario(new Biblioteca(
    // "B01", "Biblioteca 01")));
    //
    // // enter all required fields
    //
    // log.debug("adding horarioInt...");
    // horarioInt = dao.save(horarioInt);
    // //
    // horarioInt = dao.get(horarioInt.getId());
    //
    // assertNotNull(horarioInt.getId());
    //
    // log.debug("removing horarioInt...");
    //
    // dao.remove(horarioInt.getId());
    // //
    // try {
    // dao.get(horarioInt.getId());
    // fail("HorarioInt found in database");
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