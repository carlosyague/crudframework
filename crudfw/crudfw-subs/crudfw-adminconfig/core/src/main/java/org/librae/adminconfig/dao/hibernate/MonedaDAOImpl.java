package org.librae.adminconfig.dao.hibernate;

import org.librae.adminconfig.model.Moneda;
import org.librae.adminconfig.dao.IMonedaDAO;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Moneda
 *
 * @author asantamaria
 */
public class MonedaDAOImpl extends GenericDAOImpl<Moneda, Long> implements IMonedaDAO {

    /**
     * Constructor del DAO
     */
    public MonedaDAOImpl() {
        super(Moneda.class);
    }
}
