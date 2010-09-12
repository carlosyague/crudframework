
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.IFacturaManager;
import org.tennisclub.manager.model.Factura;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class FacturaFormTest extends BasePageTestCase {
    private FacturaFormAction bean;
    private IFacturaManager facturaManager;

    public void setFacturaManager(IFacturaManager facturaManager) {
        this.facturaManager = facturaManager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new FacturaFormAction();
        bean.setFacturaManager(facturaManager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Factura factura = new Factura();

        // enter all required fields
        bean.setFactura(factura);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getFactura());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getFactura());
        Factura factura = bean.getFactura();

        // update required fields
        bean.setFactura(factura);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Factura factura = new Factura();
        factura.setId(-2L);
        bean.setFactura(factura);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}