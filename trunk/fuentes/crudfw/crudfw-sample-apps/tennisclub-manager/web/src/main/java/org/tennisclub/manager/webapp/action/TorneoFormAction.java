
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Torneo;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.ITorneoManager;

/**
 * Action-JSF asociado a los formularios de la entidad Torneo
 *
 * @author Carlos Yagüe
 */
public class TorneoFormAction extends BasePage implements Serializable {
    private ITorneoManager torneoManager;
    private Torneo torneo = new Torneo();
    private Long id;

    // getter & setter del manager

    public ITorneoManager getTorneoManager() {
        return this.torneoManager;
    }

    public void setTorneoManager(ITorneoManager torneoManager) {
        this.torneoManager = torneoManager;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        torneoManager.remove(torneo.getId());
        addMessage("torneo.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            torneo = torneoManager.get(id);
        } else {
            torneo = new Torneo();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (torneo.getId() == null || torneo.getId() == 0);
        torneoManager.save(torneo);

        String key = (isNew) ? "torneo.added" : "torneo.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}