
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IFacturaManager;

/**
 * Action-JSF asociado a los listados de la entidad Factura
 *
 * @author cyague@lcc.uma
 */
public class FacturaListAction extends BasePage implements Serializable {
    private IFacturaManager facturaManager;

    public IFacturaManager getFacturaManager() {
        return this.facturaManager;
    }

    public void setFacturaManager(IFacturaManager facturaManager) {
        this.facturaManager = facturaManager;
    }

    public FacturaListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getFacturas() {
        return sort(facturaManager.getAll());
    }
}

