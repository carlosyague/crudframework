
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Llamada;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.ILlamadaManager;

/**
 * Action-JSF asociado a los formularios de la entidad Llamada
 *
 * @author cyague
 */
public class LlamadaFormAction extends BasePage implements Serializable {
    private ILlamadaManager llamadaManager;
    private Llamada llamada = new Llamada();
    private Long id;

    // getter & setter del manager

    public ILlamadaManager getLlamadaManager() {
        return this.llamadaManager;
    }

    public void setLlamadaManager(ILlamadaManager llamadaManager) {
        this.llamadaManager = llamadaManager;
    }

    public Llamada getLlamada() {
        return llamada;
    }

    public void setLlamada(Llamada llamada) {
        this.llamada = llamada;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        llamadaManager.remove(llamada.getId());
        addMessage("llamada.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            llamada = llamadaManager.get(id);
        } else {
            llamada = new Llamada();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (llamada.getId() == null || llamada.getId() == 0);
        llamadaManager.save(llamada);

        String key = (isNew) ? "llamada.added" : "llamada.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}