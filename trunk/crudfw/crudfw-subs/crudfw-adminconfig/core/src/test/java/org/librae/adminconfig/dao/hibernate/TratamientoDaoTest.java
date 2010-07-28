package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.ITratamientoDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class TratamientoDaoTest extends BaseDAOTestCase {
    @SuppressWarnings("unused")
    private ITratamientoDAO dao;

    public void setTratamientoDAO(final ITratamientoDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveTratamiento() throws Exception {
    // Tratamiento tratamiento = new Tratamiento("TR01", "Tratamiento 01",
    // Tratamiento.SEXO_HOMBRE);
    //
    // // enter all required fields
    //
    // log.debug("adding tratamiento...");
    // tratamiento = dao.save(tratamiento);
    // //
    // tratamiento = dao.get(tratamiento.getId());
    //
    // assertNotNull(tratamiento.getId());
    //
    // log.debug("removing tratamiento...");
    //
    // dao.remove(tratamiento.getId());
    // //
    // try {
    // dao.get(tratamiento.getId());
    // fail("Tratamiento found in database");
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