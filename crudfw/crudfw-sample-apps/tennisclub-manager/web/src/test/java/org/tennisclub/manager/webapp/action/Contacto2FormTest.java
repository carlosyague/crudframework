
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.IContacto2Manager;
import org.tennisclub.manager.model.Contacto2;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class Contacto2FormTest extends BasePageTestCase {
    private Contacto2FormAction bean;
    private IContacto2Manager contacto2Manager;

    public void setContacto2Manager(IContacto2Manager contacto2Manager) {
        this.contacto2Manager = contacto2Manager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new Contacto2FormAction();
        bean.setContacto2Manager(contacto2Manager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Contacto2 contacto2 = new Contacto2();

        // enter all required fields
        bean.setContacto2(contacto2);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto2());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto2());
        Contacto2 contacto2 = bean.getContacto2();

        // update required fields
        bean.setContacto2(contacto2);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Contacto2 contacto2 = new Contacto2();
        contacto2.setId(-2L);
        bean.setContacto2(contacto2);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}