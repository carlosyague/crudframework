
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Contacto6;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto6Manager;

/**
 * Action-JSF asociado a los formularios de la entidad Contacto6
 *
 * @author cyague@lcc.uma
 */
public class Contacto6FormAction extends BasePage implements Serializable {
    private IContacto6Manager contacto6Manager;
    private Contacto6 contacto6 = new Contacto6();
    private Long id;

    // getter & setter del manager

    public IContacto6Manager getContacto6Manager() {
        return this.contacto6Manager;
    }

    public void setContacto6Manager(IContacto6Manager contacto6Manager) {
        this.contacto6Manager = contacto6Manager;
    }

    public Contacto6 getContacto6() {
        return contacto6;
    }

    public void setContacto6(Contacto6 contacto6) {
        this.contacto6 = contacto6;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        contacto6Manager.remove(contacto6.getId());
        addMessage("contacto6.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            contacto6 = contacto6Manager.get(id);
        } else {
            contacto6 = new Contacto6();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (contacto6.getId() == null || contacto6.getId() == 0);
        contacto6Manager.save(contacto6);

        String key = (isNew) ? "contacto6.added" : "contacto6.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}