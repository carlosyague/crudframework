
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.IContacto4Manager;
import org.tennisclub.manager.model.Contacto4;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class Contacto4FormTest extends BasePageTestCase {
    private Contacto4FormAction bean;
    private IContacto4Manager contacto4Manager;

    public void setContacto4Manager(IContacto4Manager contacto4Manager) {
        this.contacto4Manager = contacto4Manager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new Contacto4FormAction();
        bean.setContacto4Manager(contacto4Manager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Contacto4 contacto4 = new Contacto4();

        // enter all required fields
        bean.setContacto4(contacto4);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto4());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto4());
        Contacto4 contacto4 = bean.getContacto4();

        // update required fields
        bean.setContacto4(contacto4);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Contacto4 contacto4 = new Contacto4();
        contacto4.setId(-2L);
        bean.setContacto4(contacto4);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}