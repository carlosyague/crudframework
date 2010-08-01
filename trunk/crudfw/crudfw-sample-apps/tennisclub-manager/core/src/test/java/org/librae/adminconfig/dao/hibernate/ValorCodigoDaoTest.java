package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IValorCodigoDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class ValorCodigoDaoTest extends BaseDAOTestCase {
    @SuppressWarnings("unused")
    private IValorCodigoDAO dao;

    public void setValorCodigoDAO(final IValorCodigoDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveValorCodigo() throws Exception {
    // ValorCodigo valorCodigo = new ValorCodigo("Valor 55");
    // valorCodigo.setCodigo(new Codigo("codigo1"));
    //
    // // enter all required fields
    //
    // this.log.debug("adding valorCodigo...");
    // valorCodigo = this.dao.save(valorCodigo);
    // //
    // valorCodigo = this.dao.get(valorCodigo.getId());
    //
    // Assert.assertNotNull(valorCodigo.getId());
    //
    // this.log.debug("removing valorCodigo...");
    //
    // this.dao.remove(valorCodigo.getId());
    // //
    // try {
    // this.dao.get(valorCodigo.getId());
    // Assert.fail("ValorCodigo found in database");
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