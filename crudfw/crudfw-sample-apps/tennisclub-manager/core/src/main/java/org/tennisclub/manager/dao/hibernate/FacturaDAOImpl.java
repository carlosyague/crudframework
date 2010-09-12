package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Factura;
import org.tennisclub.manager.dao.IFacturaDAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Factura
 *
 * @author cyague@lcc.uma
 */
public class FacturaDAOImpl extends GenericDAOImpl<Factura, Long> implements IFacturaDAO {

    /**
     * Constructor del DAO
     */
    public FacturaDAOImpl() {
        super(Factura.class);
    }
}
