
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Contacto3;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IContacto3Manager;

/**
 * Action-JSF asociado a los formularios de la entidad Contacto3
 *
 * @author cyague@lcc.uma
 */
public class Contacto3FormAction extends BasePage implements Serializable {
    private IContacto3Manager contacto3Manager;
    private Contacto3 contacto3 = new Contacto3();
    private Long id;

    // getter & setter del manager

    public IContacto3Manager getContacto3Manager() {
        return this.contacto3Manager;
    }

    public void setContacto3Manager(IContacto3Manager contacto3Manager) {
        this.contacto3Manager = contacto3Manager;
    }

    public Contacto3 getContacto3() {
        return contacto3;
    }

    public void setContacto3(Contacto3 contacto3) {
        this.contacto3 = contacto3;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        contacto3Manager.remove(contacto3.getId());
        addMessage("contacto3.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            contacto3 = contacto3Manager.get(id);
        } else {
            contacto3 = new Contacto3();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (contacto3.getId() == null || contacto3.getId() == 0);
        contacto3Manager.save(contacto3);

        String key = (isNew) ? "contacto3.added" : "contacto3.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}