
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Contacto5;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto5Manager;

/**
 * Action-JSF asociado a los formularios de la entidad Contacto5
 *
 * @author cyague@lcc.uma
 */
public class Contacto5FormAction extends BasePage implements Serializable {
    private IContacto5Manager contacto5Manager;
    private Contacto5 contacto5 = new Contacto5();
    private Long id;

    // getter & setter del manager

    public IContacto5Manager getContacto5Manager() {
        return this.contacto5Manager;
    }

    public void setContacto5Manager(IContacto5Manager contacto5Manager) {
        this.contacto5Manager = contacto5Manager;
    }

    public Contacto5 getContacto5() {
        return contacto5;
    }

    public void setContacto5(Contacto5 contacto5) {
        this.contacto5 = contacto5;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        contacto5Manager.remove(contacto5.getId());
        addMessage("contacto5.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            contacto5 = contacto5Manager.get(id);
        } else {
            contacto5 = new Contacto5();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (contacto5.getId() == null || contacto5.getId() == 0);
        contacto5Manager.save(contacto5);

        String key = (isNew) ? "contacto5.added" : "contacto5.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}