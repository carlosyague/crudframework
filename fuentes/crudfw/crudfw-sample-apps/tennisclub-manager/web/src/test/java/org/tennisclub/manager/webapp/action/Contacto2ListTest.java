package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.IContacto2Manager;
import org.tennisclub.manager.model.Contacto2;
import org.junit.Test;

public class Contacto2ListTest extends BasePageTestCase {
    private Contacto2ListAction bean;
    private IContacto2Manager contacto2Manager;

    public void setContacto2Manager(IContacto2Manager contacto2Manager) {
        this.contacto2Manager = contacto2Manager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new Contacto2ListAction();
        bean.setContacto2Manager(contacto2Manager);

        // add a test contacto2 to the database
        Contacto2 contacto2 = new Contacto2();

        // enter all required fields

        contacto2Manager.save(contacto2);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getContacto2s().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}