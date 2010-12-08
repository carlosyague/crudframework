
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.ILlamadaManager;

/**
 * Action-JSF asociado a los listados de la entidad Llamada
 *
 * @author cyague
 */
public class LlamadaListAction extends BasePage implements Serializable {
    private ILlamadaManager llamadaManager;

    public ILlamadaManager getLlamadaManager() {
        return this.llamadaManager;
    }

    public void setLlamadaManager(ILlamadaManager llamadaManager) {
        this.llamadaManager = llamadaManager;
    }

    public LlamadaListAction() {
        setSortColumn("fechaHora"); // sets the default sort column
        this.setAscending(false);
    }

    public List getLlamadas() {
        return sort(llamadaManager.getAll());
    }
}

