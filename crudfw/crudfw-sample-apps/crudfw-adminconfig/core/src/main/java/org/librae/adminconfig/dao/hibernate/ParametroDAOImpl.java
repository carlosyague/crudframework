package org.librae.adminconfig.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IParametroDAO;
import org.librae.adminconfig.model.Parametro;
import org.librae.common.dao.hibernate.GenericSearchDao;
import org.librae.common.util.StringUtil;

/**
 * Implementación del DAO para la entidad Parametro.
 * 
 * @author jcisneros
 */
public class ParametroDAOImpl extends GenericSearchDao<Parametro, Long>
        implements IParametroDAO {

    /**
     * Constructor del DAO
     */
    public ParametroDAOImpl() {
        super(Parametro.class);
    }

    /**
     * @see org.librae.adminconfig.dao.IParametroDAO#geParametroByCodigo(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Parametro getParametroByCodigo(final String codigo) {
        final StringBuilder sb = new StringBuilder();
        List<Parametro> parametros = null;
        sb.append("from " + Parametro.ENTITY_NAME + " where codigo = ? ");
        log.debug("HQL..." + sb.toString());
        final String[] array = { codigo };
        parametros = getHibernateTemplate().find(sb.toString(), array);
        return ((parametros.isEmpty()) ? null : parametros.get(0));
    }

    @Override
    protected DetachedCriteria obtenerCriteria(Map<String, Object> criterios) {
        final DetachedCriteria criteriaParametros = DetachedCriteria
                .forClass(Parametro.class);
        if (!StringUtil.esVacia(criterios.get(Parametro.PROPERTY_NAME_CODIGO))) {
            criteriaParametros.add(Restrictions.ilike(
                    Parametro.PROPERTY_NAME_CODIGO, criterios.get(
                            Parametro.PROPERTY_NAME_CODIGO).toString(),
                    MatchMode.ANYWHERE));
        }
        return criteriaParametros;
    }
}
