package org.tennisclub.manager.dao.hibernate;

import org.tennisclub.manager.model.Socio;
import org.tennisclub.manager.dao.ISocioDAO;
import es.uma.crudframework.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Socio
 *
 * @author cyague
 */
public class SocioDAOImpl extends GenericDAOImpl<Socio, Long> implements ISocioDAO {

    /**
     * Constructor del DAO
     */
    public SocioDAOImpl() {
        super(Socio.class);
    }
}
