
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Contacto2;
import org.tennisclub.manager.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto2Manager;

/**
 * Action-JSF asociado a los formularios de la entidad Contacto2
 *
 * @author cyague@lcc.uma
 */
public class Contacto2FormAction extends BasePage implements Serializable {
    private IContacto2Manager contacto2Manager;
    private Contacto2 contacto2 = new Contacto2();
    private Long id;

    // getter & setter del manager

    public IContacto2Manager getContacto2Manager() {
        return this.contacto2Manager;
    }

    public void setContacto2Manager(IContacto2Manager contacto2Manager) {
        this.contacto2Manager = contacto2Manager;
    }

    public Contacto2 getContacto2() {
        return contacto2;
    }

    public void setContacto2(Contacto2 contacto2) {
        this.contacto2 = contacto2;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        contacto2Manager.remove(contacto2.getId());
        addMessage("contacto2.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            contacto2 = contacto2Manager.get(id);
        } else {
            contacto2 = new Contacto2();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (contacto2.getId() == null || contacto2.getId() == 0);
        contacto2Manager.save(contacto2);

        String key = (isNew) ? "contacto2.added" : "contacto2.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}