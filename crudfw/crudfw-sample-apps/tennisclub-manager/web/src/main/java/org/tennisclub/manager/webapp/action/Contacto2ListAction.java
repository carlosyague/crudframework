
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import org.tennisclub.manager.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto2Manager;

/**
 * Action-JSF asociado a los listados de la entidad Contacto2
 *
 * @author cyague@lcc.uma
 */
public class Contacto2ListAction extends BasePage implements Serializable {
    private IContacto2Manager contacto2Manager;

    public IContacto2Manager getContacto2Manager() {
        return this.contacto2Manager;
    }

    public void setContacto2Manager(IContacto2Manager contacto2Manager) {
        this.contacto2Manager = contacto2Manager;
    }

    public Contacto2ListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getContacto2s() {
        return sort(contacto2Manager.getAll());
    }
}

