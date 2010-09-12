package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Cliente;
import org.tennisclub.manager.dao.IClienteDAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Cliente
 *
 * @author cyague@lcc.uma
 */
public class ClienteDAOImpl extends GenericDAOImpl<Cliente, Long> implements IClienteDAO {

    /**
     * Constructor del DAO
     */
    public ClienteDAOImpl() {
        super(Cliente.class);
    }
}
