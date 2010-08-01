package org.librae.adminconfig.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IHorarioIntDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.hibernate.Query;
import org.librae.common.dao.hibernate.GenericSearchDao;

/**
 * Implementación del DAO para la entidad HorarioInt
 * 
 * @author asantamaria
 * @author aropero
 */
public class HorarioIntDAOImpl extends GenericSearchDao<HorarioInt, Long>
        implements IHorarioIntDAO, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor del DAO
     */
    public HorarioIntDAOImpl() {
        super(HorarioInt.class);
    }

    /**
     * @see org.librae.common.dao.hibernate.GenericSearchDao#obtenerCriteria(Map)
     */
    protected DetachedCriteria obtenerCriteria(
            final Map<String, Object> criterios) {
        final DetachedCriteria criteria = DetachedCriteria
                .forClass(HorarioInt.class);
        if (criterios != null) {
            if (criterios.get(HorarioInt.PROPTY_NAME_HORARIO) != null) {
                criteria.add(Restrictions.eq("horario.id", criterios
                        .get(HorarioInt.PROPTY_NAME_HORARIO)));
            }
        }
        return criteria;
    }

    /**
     * @see org.librae.adminconfig.dao.IHorarioIntDAO#eliminarIntervalosHorario(java.lang.Long)
     */
    public void eliminarIntervalosHorario(final Long id) {
        log
                .debug("Eliminamos los HorarioInt relacionados con el Horario de Id : "
                        + id);

        final StringBuilder sb = new StringBuilder();
        sb.append("delete from " + HorarioInt.ENTITY_NAME
                + " h where h.horario.id like " + id);

        log.debug("HQL : " + sb.toString());
        final Query query = getSession().createQuery(sb.toString());

        query.executeUpdate();
    }

	public List<HorarioInt> getIntervalos(Long idHorario) {
	        final Object[] parametros = { idHorario };
	        final StringBuilder sb = new StringBuilder();
	        sb.append(" select horarioInt from           			");
	        sb.append(HorarioInt.ENTITY_NAME).append(" horarioInt 	");
	        sb.append(" where horarioInt.horario.id=?               ");

	        final List<HorarioInt> intervalos = getHibernateTemplate().find(
	                sb.toString(), parametros);

	        return intervalos;
	}
}
