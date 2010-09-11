package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.IClienteManager;
import org.tennisclub.manager.model.Cliente;
import org.junit.Test;

public class ClienteListTest extends BasePageTestCase {
    private ClienteListAction bean;
    private IClienteManager clienteManager;

    public void setClienteManager(IClienteManager clienteManager) {
        this.clienteManager = clienteManager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() {
        super.onSetUp();
        bean = new ClienteListAction();
        bean.setClienteManager(clienteManager);

        // add a test cliente to the database
        Cliente cliente = new Cliente();

        // enter all required fields

        clienteManager.save(cliente);
    }

    @Override
    protected void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getClientes().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}