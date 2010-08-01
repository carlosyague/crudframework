package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.ITipoPermisoDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class TipoPermisoDaoTest extends BaseDAOTestCase {
    @SuppressWarnings("unused")
    private ITipoPermisoDAO dao;

    public void setTipoPermisoDAO(final ITipoPermisoDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveTipoPermiso() throws Exception {
    // TipoPermiso tipoPermiso = new TipoPermiso("TP01", "Tipo Permiso 01");
    //
    // // enter all required fields
    //
    // log.debug("adding tipoPermiso...");
    // tipoPermiso = dao.save(tipoPermiso);
    // //
    // tipoPermiso = dao.get(tipoPermiso.getId());
    //
    // assertNotNull(tipoPermiso.getId());
    //
    // log.debug("removing tipoPermiso...");
    //
    // dao.remove(tipoPermiso.getId());
    // //
    // try {
    // dao.get(tipoPermiso.getId());
    // fail("TipoPermiso found in database");
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