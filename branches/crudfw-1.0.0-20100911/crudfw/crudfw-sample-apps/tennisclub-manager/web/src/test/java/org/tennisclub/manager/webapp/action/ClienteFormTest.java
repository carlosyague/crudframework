
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.IClienteManager;
import org.tennisclub.manager.model.Cliente;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class ClienteFormTest extends BasePageTestCase {
    private ClienteFormAction bean;
    private IClienteManager clienteManager;

    public void setClienteManager(IClienteManager clienteManager) {
        this.clienteManager = clienteManager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new ClienteFormAction();
        bean.setClienteManager(clienteManager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Cliente cliente = new Cliente();

        // enter all required fields
        bean.setCliente(cliente);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCliente());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCliente());
        Cliente cliente = bean.getCliente();

        // update required fields
        bean.setCliente(cliente);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Cliente cliente = new Cliente();
        cliente.setId(-2L);
        bean.setCliente(cliente);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}