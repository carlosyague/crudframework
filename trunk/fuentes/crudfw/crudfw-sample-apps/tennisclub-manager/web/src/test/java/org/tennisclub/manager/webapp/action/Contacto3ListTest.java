package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.IContacto3Manager;
import org.tennisclub.manager.model.Contacto3;
import org.junit.Test;

public class Contacto3ListTest extends BasePageTestCase {
    private Contacto3ListAction bean;
    private IContacto3Manager contacto3Manager;

    public void setContacto3Manager(IContacto3Manager contacto3Manager) {
        this.contacto3Manager = contacto3Manager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() {
        super.onSetUp();
        bean = new Contacto3ListAction();
        bean.setContacto3Manager(contacto3Manager);

        // add a test contacto3 to the database
        Contacto3 contacto3 = new Contacto3();

        // enter all required fields

        contacto3Manager.save(contacto3);
    }

    @Override
    protected void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getContacto3s().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}