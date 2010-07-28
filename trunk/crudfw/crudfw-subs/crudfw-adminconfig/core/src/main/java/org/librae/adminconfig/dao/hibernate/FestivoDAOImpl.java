package org.librae.adminconfig.dao.hibernate;

import org.librae.adminconfig.dao.IFestivoDAO;
import org.librae.adminconfig.model.Festivo;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Festivo
 * 
 * @author asantamaria
 */
public class FestivoDAOImpl extends GenericDAOImpl<Festivo, Long> implements
        IFestivoDAO {

    /**
     * Constructor del DAO
     */
    public FestivoDAOImpl() {
        super(Festivo.class);
    }

    public void eliminarByIdCalendario(Long id) {
        final StringBuilder hql = new StringBuilder();
        hql.append("delete from ");
        hql.append(Festivo.ENTITY_NAME);
        hql.append(" where calendario.id=?");
        getHibernateTemplate().bulkUpdate(hql.toString(), id);
    }

}
