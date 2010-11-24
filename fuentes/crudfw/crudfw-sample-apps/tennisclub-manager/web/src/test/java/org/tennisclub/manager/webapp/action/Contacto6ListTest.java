package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.IContacto6Manager;
import org.tennisclub.manager.model.Contacto6;
import org.junit.Test;

public class Contacto6ListTest extends BasePageTestCase {
    private Contacto6ListAction bean;
    private IContacto6Manager contacto6Manager;

    public void setContacto6Manager(IContacto6Manager contacto6Manager) {
        this.contacto6Manager = contacto6Manager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() {
        super.onSetUp();
        bean = new Contacto6ListAction();
        bean.setContacto6Manager(contacto6Manager);

        // add a test contacto6 to the database
        Contacto6 contacto6 = new Contacto6();

        // enter all required fields

        contacto6Manager.save(contacto6);
    }

    @Override
    protected void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getContacto6s().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}