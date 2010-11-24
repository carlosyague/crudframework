package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.IContacto5Manager;
import org.tennisclub.manager.model.Contacto5;
import org.junit.Test;

public class Contacto5ListTest extends BasePageTestCase {
    private Contacto5ListAction bean;
    private IContacto5Manager contacto5Manager;

    public void setContacto5Manager(IContacto5Manager contacto5Manager) {
        this.contacto5Manager = contacto5Manager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() {
        super.onSetUp();
        bean = new Contacto5ListAction();
        bean.setContacto5Manager(contacto5Manager);

        // add a test contacto5 to the database
        Contacto5 contacto5 = new Contacto5();

        // enter all required fields

        contacto5Manager.save(contacto5);
    }

    @Override
    protected void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getContacto5s().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}