package org.librae.adminconfig.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;
import org.librae.adminconfig.dao.ITipoIdentificacionDAO;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.common.dao.BaseDAOTestCase;
import org.librae.common.exception.LibraeException;

public class TipoIdentificacionDaoTest extends BaseDAOTestCase {

    private ITipoIdentificacionDAO dao;

    public void setTipoIdentificacionDAO(final ITipoIdentificacionDAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemoveTipoIdentificacion() throws Exception {
        TipoIdentificacion tipoIdentificacion = new TipoIdentificacion("TI01",
                "Tipo Identificacion 01");

        // enter all required fields

        log.debug("adding tipoIdentificacion...");
        tipoIdentificacion = dao.save(tipoIdentificacion);
        //
        tipoIdentificacion = dao.get(tipoIdentificacion.getId());

        Assert.assertNotNull(tipoIdentificacion.getId());

        log.debug("removing tipoIdentificacion...");

        dao.remove(tipoIdentificacion.getId());
        //
        try {
            dao.get(tipoIdentificacion.getId());
            Assert.fail("TipoIdentificacion found in database");
        } catch (final LibraeException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            Assert.assertNotNull(dae);
        }
    }

    /**
     * test para el metodo "geTipoIdentificacionByCodigo()". Obtiene el tipo de
     * identificación correspondiente al Código que se le pasa como parámetro
     */
    public void testGetTipoIdentificacionByCodigo() throws Exception {
        final String codigo = "NIF";
        TipoIdentificacion tipoIdentificacion = null;
        tipoIdentificacion = dao.getTipoIdentificacionByCodigo(codigo);
        Assert.assertNotNull("Debe existir el tipo de identificacion",
                tipoIdentificacion);
    }
}