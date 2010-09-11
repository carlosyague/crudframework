package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.IFacturaManager;
import org.tennisclub.manager.model.Factura;
import org.junit.Test;

public class FacturaListTest extends BasePageTestCase {
    private FacturaListAction bean;
    private IFacturaManager facturaManager;

    public void setFacturaManager(IFacturaManager facturaManager) {
        this.facturaManager = facturaManager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() {
        super.onSetUp();
        bean = new FacturaListAction();
        bean.setFacturaManager(facturaManager);

        // add a test factura to the database
        Factura factura = new Factura();

        // enter all required fields

        facturaManager.save(factura);
    }

    @Override
    protected void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getFacturas().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}