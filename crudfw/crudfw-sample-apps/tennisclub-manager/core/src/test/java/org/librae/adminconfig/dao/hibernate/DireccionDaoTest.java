package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IDireccionDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class DireccionDaoTest extends BaseDAOTestCase {
    private IDireccionDAO dao;

    public void setDireccionDAO(final IDireccionDAO dao) {
        this.dao = dao;
    }

    // public void testAddAndRemoveDireccion() throws Exception {
    // Direccion direccion = new Direccion("Castilla", 12L);
    //
    // // enter all required fields
    //
    // Localidad localidad = new Localidad("Almería", "AL");
    //
    // direccion.setLocalidad(localidad);
    //
    // Pais pais = new Pais("ESP", "España");
    //
    // direccion.setPais(pais);
    //
    // TipoVia tipoVia = new TipoVia("CL", "Calle");
    //
    // direccion.setTipoVia(tipoVia);
    //
    // direccion.setNombreVia("Soldadito español");
    //
    // log.debug("adding direccion...");
    // direccion = dao.save(direccion);
    // //
    // direccion = dao.get(direccion.getId());
    //
    // assertNotNull(direccion.getId());
    //
    // log.debug("removing direccion...");
    //
    // dao.remove(direccion.getId());
    // //
    // try {
    // dao.get(direccion.getId());
    // fail("Direccion found in database");
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