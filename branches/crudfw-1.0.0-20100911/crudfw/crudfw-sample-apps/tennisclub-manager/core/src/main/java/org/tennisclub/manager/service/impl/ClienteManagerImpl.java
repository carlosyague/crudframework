
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IClienteDAO;
import org.tennisclub.manager.model.Cliente;
import org.tennisclub.manager.service.IClienteManager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IClienteDAO <br/>Entidad: Cliente
 *
 * @author cyague@lcc.uma
 */
public class ClienteManagerImpl extends GenericManagerImpl<Cliente, Long> implements IClienteManager {
    IClienteDAO clienteDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public ClienteManagerImpl(IClienteDAO clienteDao) {
        super(clienteDao);
        this.clienteDao = clienteDao;
    }
}