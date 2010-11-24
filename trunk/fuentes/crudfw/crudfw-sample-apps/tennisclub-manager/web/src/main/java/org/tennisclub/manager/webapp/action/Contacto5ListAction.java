
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto5Manager;

/**
 * Action-JSF asociado a los listados de la entidad Contacto5
 *
 * @author cyague@lcc.uma
 */
public class Contacto5ListAction extends BasePage implements Serializable {
    private IContacto5Manager contacto5Manager;

    public IContacto5Manager getContacto5Manager() {
        return this.contacto5Manager;
    }

    public void setContacto5Manager(IContacto5Manager contacto5Manager) {
        this.contacto5Manager = contacto5Manager;
    }

    public Contacto5ListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getContacto5s() {
        return sort(contacto5Manager.getAll());
    }
}

