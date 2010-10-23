
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Contacto4;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto4Manager;

/**
 * Action-JSF asociado a los formularios de la entidad Contacto4
 *
 * @author cyague@lcc.uma
 */
public class Contacto4FormAction extends BasePage implements Serializable {
    private IContacto4Manager contacto4Manager;
    private Contacto4 contacto4 = new Contacto4();
    private Long id;

    // getter & setter del manager

    public IContacto4Manager getContacto4Manager() {
        return this.contacto4Manager;
    }

    public void setContacto4Manager(IContacto4Manager contacto4Manager) {
        this.contacto4Manager = contacto4Manager;
    }

    public Contacto4 getContacto4() {
        return contacto4;
    }

    public void setContacto4(Contacto4 contacto4) {
        this.contacto4 = contacto4;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        contacto4Manager.remove(contacto4.getId());
        addMessage("contacto4.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            contacto4 = contacto4Manager.get(id);
        } else {
            contacto4 = new Contacto4();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (contacto4.getId() == null || contacto4.getId() == 0);
        contacto4Manager.save(contacto4);

        String key = (isNew) ? "contacto4.added" : "contacto4.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}