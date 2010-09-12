
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Cliente;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IClienteManager;

/**
 * Action-JSF asociado a los formularios de la entidad Cliente
 *
 * @author cyague@lcc.uma
 */
public class ClienteFormAction extends BasePage implements Serializable {
    private IClienteManager clienteManager;
    private Cliente cliente = new Cliente();
    private Long id;

    // getter & setter del manager

    public IClienteManager getClienteManager() {
        return this.clienteManager;
    }

    public void setClienteManager(IClienteManager clienteManager) {
        this.clienteManager = clienteManager;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        clienteManager.remove(cliente.getId());
        addMessage("cliente.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            cliente = clienteManager.get(id);
        } else {
            cliente = new Cliente();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (cliente.getId() == null || cliente.getId() == 0);
        clienteManager.save(cliente);

        String key = (isNew) ? "cliente.added" : "cliente.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}