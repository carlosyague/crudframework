
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto4Manager;

/**
 * Action-JSF asociado a los listados de la entidad Contacto4
 *
 * @author cyague@lcc.uma
 */
public class Contacto4ListAction extends BasePage implements Serializable {
    private IContacto4Manager contacto4Manager;

    public IContacto4Manager getContacto4Manager() {
        return this.contacto4Manager;
    }

    public void setContacto4Manager(IContacto4Manager contacto4Manager) {
        this.contacto4Manager = contacto4Manager;
    }

    public Contacto4ListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getContacto4s() {
        return sort(contacto4Manager.getAll());
    }
}

