
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IClienteManager;

/**
 * Action-JSF asociado a los listados de la entidad Cliente
 *
 * @author cyague@lcc.uma
 */
public class ClienteListAction extends BasePage implements Serializable {
    private IClienteManager clienteManager;

    public IClienteManager getClienteManager() {
        return this.clienteManager;
    }

    public void setClienteManager(IClienteManager clienteManager) {
        this.clienteManager = clienteManager;
    }

    public ClienteListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getClientes() {
        return sort(clienteManager.getAll());
    }
}

