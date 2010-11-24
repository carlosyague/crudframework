
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.IContacto5Manager;
import org.tennisclub.manager.model.Contacto5;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class Contacto5FormTest extends BasePageTestCase {
    private Contacto5FormAction bean;
    private IContacto5Manager contacto5Manager;

    public void setContacto5Manager(IContacto5Manager contacto5Manager) {
        this.contacto5Manager = contacto5Manager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new Contacto5FormAction();
        bean.setContacto5Manager(contacto5Manager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Contacto5 contacto5 = new Contacto5();

        // enter all required fields
        bean.setContacto5(contacto5);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto5());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto5());
        Contacto5 contacto5 = bean.getContacto5();

        // update required fields
        bean.setContacto5(contacto5);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Contacto5 contacto5 = new Contacto5();
        contacto5.setId(-2L);
        bean.setContacto5(contacto5);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}