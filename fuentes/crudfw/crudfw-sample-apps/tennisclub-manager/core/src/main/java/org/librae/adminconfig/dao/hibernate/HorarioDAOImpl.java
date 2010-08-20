package org.librae.adminconfig.dao.hibernate;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IHorarioDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Horario;
import org.librae.common.dao.hibernate.GenericSearchDao;

/**
 * Implementación del DAO para la entidad Horario
 * 
 * @author asantamaria
 */
public class HorarioDAOImpl extends GenericSearchDao<Horario, Long> implements
        IHorarioDAO, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = -4514368084392350543L;

    /**
     * Constructor del DAO
     */
    public HorarioDAOImpl() {
        super(Horario.class);
    }

    /**
     * @see org.librae.common.dao.hibernate.GenericSearchDao#obtenerCriteria(Map)
     */
    protected DetachedCriteria obtenerCriteria(
            final Map<String, Object> criterios) {
        final DetachedCriteria criteriaHorarios = DetachedCriteria
                .forClass(Biblioteca.class);
        if (criterios != null) {
            if (criterios.get("idBiblioteca") != null) {
                criteriaHorarios.add(Restrictions.eq(Biblioteca.PROPTY_NAME_ID,
                        criterios.get("idBiblioteca")));
            }
        }
        criteriaHorarios.setProjection(Projections.property("horario"));
        return criteriaHorarios;
    }
}
