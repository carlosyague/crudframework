
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.ITorneoManager;

/**
 * Action-JSF asociado a los listados de la entidad Torneo
 *
 * @author Carlos Yagüe
 */
public class TorneoListAction extends BasePage implements Serializable {
    private ITorneoManager torneoManager;

    public ITorneoManager getTorneoManager() {
        return this.torneoManager;
    }

    public void setTorneoManager(ITorneoManager torneoManager) {
        this.torneoManager = torneoManager;
    }

    public TorneoListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getTorneoes() {
        return sort(torneoManager.getAll());
    }
}

