
package org.tennisclub.manager.service.impl;

import org.tennisclub.manager.dao.IFacturaDAO;
import org.tennisclub.manager.model.Factura;
import org.tennisclub.manager.service.IFacturaManager;
import es.uma.crudframework.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IFacturaDAO <br/>Entidad: Factura
 *
 * @author cyague@lcc.uma
 */
public class FacturaManagerImpl extends GenericManagerImpl<Factura, Long> implements IFacturaManager {
    IFacturaDAO facturaDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public FacturaManagerImpl(IFacturaDAO facturaDao) {
        super(facturaDao);
        this.facturaDao = facturaDao;
    }
}