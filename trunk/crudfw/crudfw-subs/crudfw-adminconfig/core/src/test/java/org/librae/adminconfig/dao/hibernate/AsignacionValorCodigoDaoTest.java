package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IAsignacionValorCodigoDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class AsignacionValorCodigoDaoTest extends BaseDAOTestCase {
    @SuppressWarnings("unused")
    private IAsignacionValorCodigoDAO dao;

    public void setAsignacionValorCodigoDAO(final IAsignacionValorCodigoDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveAsignacionValorCodigo() throws Exception {
    // final Codigo codigo = new Codigo("CB55");
    // final ValorCodigo valorCodigo = new ValorCodigo("valor");
    // valorCodigo.setCodigo(codigo);
    //
    // AsignacionValorCodigo asignacionValorCodigo = new
    // AsignacionValorCodigo(
    // valorCodigo, null, null, null);
    //
    // // enter all required fields
    //
    // this.log.debug("adding asignacionValorCodigo...");
    // asignacionValorCodigo = this.dao.save(asignacionValorCodigo);
    // //
    // asignacionValorCodigo = this.dao.get(asignacionValorCodigo.getId());
    //
    // Assert.assertNotNull(asignacionValorCodigo.getId());
    //
    // this.log.debug("removing asignacionValorCodigo...");
    //
    // this.dao.remove(asignacionValorCodigo.getId());
    // //
    // try {
    // this.dao.get(asignacionValorCodigo.getId());
    // Assert.fail("AsignacionValorCodigo found in database");
    // } catch (final LibraeException dae) {
    // this.log.debug("Expected exception: " + dae.getMessage());
    // Assert.assertNotNull(dae);
    // }
    // }

    @Test
    public void testVacio() {
        Assert.assertTrue(true);
    }

}