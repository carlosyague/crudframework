
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Factura;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IFacturaManager;

/**
 * Action-JSF asociado a los formularios de la entidad Factura
 *
 * @author cyague@lcc.uma
 */
public class FacturaFormAction extends BasePage implements Serializable {
    private IFacturaManager facturaManager;
    private Factura factura = new Factura();
    private Long id;

    // getter & setter del manager

    public IFacturaManager getFacturaManager() {
        return this.facturaManager;
    }

    public void setFacturaManager(IFacturaManager facturaManager) {
        this.facturaManager = facturaManager;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        facturaManager.remove(factura.getId());
        addMessage("factura.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            factura = facturaManager.get(id);
        } else {
            factura = new Factura();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (factura.getId() == null || factura.getId() == 0);
        facturaManager.save(factura);

        String key = (isNew) ? "factura.added" : "factura.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}