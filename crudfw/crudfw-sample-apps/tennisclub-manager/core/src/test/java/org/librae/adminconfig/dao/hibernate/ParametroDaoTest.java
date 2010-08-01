package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IParametroDAO;
import org.librae.adminconfig.model.Parametro;
import org.librae.common.dao.BaseDAOTestCase;
import org.librae.common.exception.LibraeException;

public class ParametroDaoTest extends BaseDAOTestCase {

    private IParametroDAO dao;

    public void setParametroDAO(final IParametroDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveParametro() throws Exception {
        Parametro parametro = new Parametro("PARAM55",
                "Parámetro para probar los parámetros");

        // enter all required fields

        this.log.debug("adding parametro...");
        parametro = this.dao.save(parametro);
        //
        parametro = this.dao.get(parametro.getId());

        Assert.assertNotNull(parametro.getId());

        this.log.debug("removing parametro...");

        this.dao.remove(parametro.getId());
        //
        try {
            this.dao.get(parametro.getId());
            Assert.fail("Parametro found in database");
        } catch (final LibraeException dae) {
            this.log.debug("Expected exception: " + dae.getMessage());
            Assert.assertNotNull(dae);
        }
    }

    /*
     * test para el metodo "geParametroByCodigo()". Obtiene el parámetro
     * correspondiente al Código que se le pasa como parámetro
     */
    // @Test
    // public void testGetParametroByCodigo() throws Exception {
    // final String codigo = "Color";
    // Parametro parametro = null;
    // this.log.error("lanzando test con codigo..." + codigo);
    // parametro = this.dao.getParametroByCodigo(codigo);
    // this.log.error("lanzando test con parametro..." + parametro);
    // Assert.assertNotNull("Debe existir el parametro", parametro);
    // }
}