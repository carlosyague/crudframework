package org.librae.adminconfig.dao.hibernate;

import org.librae.adminconfig.model.Calendario;
import org.librae.adminconfig.dao.ICalendarioDAO;

import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Calendario
 * 
 * @author asantamaria
 */
public class CalendarioDAOImpl extends GenericDAOImpl<Calendario, Long>
        implements ICalendarioDAO {

    /**
     * Constructor del DAO
     */
    public CalendarioDAOImpl() {
        super(Calendario.class);
    }

}
