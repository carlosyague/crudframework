
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.IContacto6Manager;
import org.tennisclub.manager.model.Contacto6;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class Contacto6FormTest extends BasePageTestCase {
    private Contacto6FormAction bean;
    private IContacto6Manager contacto6Manager;

    public void setContacto6Manager(IContacto6Manager contacto6Manager) {
        this.contacto6Manager = contacto6Manager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new Contacto6FormAction();
        bean.setContacto6Manager(contacto6Manager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Contacto6 contacto6 = new Contacto6();

        // enter all required fields
        bean.setContacto6(contacto6);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto6());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto6());
        Contacto6 contacto6 = bean.getContacto6();

        // update required fields
        bean.setContacto6(contacto6);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Contacto6 contacto6 = new Contacto6();
        contacto6.setId(-2L);
        bean.setContacto6(contacto6);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}