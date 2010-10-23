
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.IContacto3Manager;
import org.tennisclub.manager.model.Contacto3;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class Contacto3FormTest extends BasePageTestCase {
    private Contacto3FormAction bean;
    private IContacto3Manager contacto3Manager;

    public void setContacto3Manager(IContacto3Manager contacto3Manager) {
        this.contacto3Manager = contacto3Manager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new Contacto3FormAction();
        bean.setContacto3Manager(contacto3Manager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Contacto3 contacto3 = new Contacto3();

        // enter all required fields
        bean.setContacto3(contacto3);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto3());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getContacto3());
        Contacto3 contacto3 = bean.getContacto3();

        // update required fields
        bean.setContacto3(contacto3);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Contacto3 contacto3 = new Contacto3();
        contacto3.setId(-2L);
        bean.setContacto3(contacto3);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}