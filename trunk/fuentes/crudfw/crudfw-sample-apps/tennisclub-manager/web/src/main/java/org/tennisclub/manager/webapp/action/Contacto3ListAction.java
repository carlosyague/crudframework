
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto3Manager;

/**
 * Action-JSF asociado a los listados de la entidad Contacto3
 *
 * @author cyague@lcc.uma
 */
public class Contacto3ListAction extends BasePage implements Serializable {
    private IContacto3Manager contacto3Manager;

    public IContacto3Manager getContacto3Manager() {
        return this.contacto3Manager;
    }

    public void setContacto3Manager(IContacto3Manager contacto3Manager) {
        this.contacto3Manager = contacto3Manager;
    }

    public Contacto3ListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getContacto3s() {
        return sort(contacto3Manager.getAll());
    }
}

