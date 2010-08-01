package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.IParametroBibliotecaDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class ParametroBibliotecaDaoTest extends BaseDAOTestCase {
    private IParametroBibliotecaDAO dao;

    public void setParametroBibliotecaDAO(final IParametroBibliotecaDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveParametroBiblioteca() throws Exception {
    // ParametroBiblioteca parametroBiblioteca = new ParametroBiblioteca(
    // new Parametro("P55", "Param 55"), new Biblioteca("B55",
    // "Bib 55"));
    //
    // // enter all required fields
    //
    // log.debug("adding parametroBiblioteca...");
    // parametroBiblioteca = dao.save(parametroBiblioteca);
    // //
    // parametroBiblioteca = dao.get(parametroBiblioteca.getId());
    //
    // assertNotNull(parametroBiblioteca.getId());
    //
    // log.debug("removing parametroBiblioteca...");
    //
    // dao.remove(parametroBiblioteca.getId());
    // //
    // try {
    // dao.get(parametroBiblioteca.getId());
    // fail("ParametroBiblioteca found in database");
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