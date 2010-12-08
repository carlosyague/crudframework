package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Llamada;
import org.tennisclub.manager.dao.ILlamadaDAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Llamada
 *
 * @author cyague
 */
public class LlamadaDAOImpl extends GenericDAOImpl<Llamada, Long> implements ILlamadaDAO {

    /**
     * Constructor del DAO
     */
    public LlamadaDAOImpl() {
        super(Llamada.class);
    }
}
