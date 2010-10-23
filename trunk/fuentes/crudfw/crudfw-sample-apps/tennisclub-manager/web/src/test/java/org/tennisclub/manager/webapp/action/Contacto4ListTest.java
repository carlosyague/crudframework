package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.IContacto4Manager;
import org.tennisclub.manager.model.Contacto4;
import org.junit.Test;

public class Contacto4ListTest extends BasePageTestCase {
    private Contacto4ListAction bean;
    private IContacto4Manager contacto4Manager;

    public void setContacto4Manager(IContacto4Manager contacto4Manager) {
        this.contacto4Manager = contacto4Manager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() {
        super.onSetUp();
        bean = new Contacto4ListAction();
        bean.setContacto4Manager(contacto4Manager);

        // add a test contacto4 to the database
        Contacto4 contacto4 = new Contacto4();

        // enter all required fields

        contacto4Manager.save(contacto4);
    }

    @Override
    protected void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getContacto4s().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}