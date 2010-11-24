
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto6Manager;

/**
 * Action-JSF asociado a los listados de la entidad Contacto6
 *
 * @author cyague@lcc.uma
 */
public class Contacto6ListAction extends BasePage implements Serializable {
    private IContacto6Manager contacto6Manager;

    public IContacto6Manager getContacto6Manager() {
        return this.contacto6Manager;
    }

    public void setContacto6Manager(IContacto6Manager contacto6Manager) {
        this.contacto6Manager = contacto6Manager;
    }

    public Contacto6ListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getContacto6s() {
        return sort(contacto6Manager.getAll());
    }
}

