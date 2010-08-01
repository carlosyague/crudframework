package org.librae.adminconfig.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.librae.adminconfig.dao.ITipoIdentificacionDAO;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad TipoIdentificacion
 * 
 * @author asantamaria
 */
public class TipoIdentificacionDAOImpl extends
        GenericDAOImpl<TipoIdentificacion, Long> implements
        ITipoIdentificacionDAO, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 8689102758306469415L;

    /**
     * Constructor del DAO
     */
    public TipoIdentificacionDAOImpl() {
        super(TipoIdentificacion.class);
    }

    /**
     * @seeorg.librae.adminconfig.dao.ITipoIdentificacionDAO# 
     *                                                        getTipoIdentificacionByCodigo
     *                                                        (java.lang.Long)
     */
    public TipoIdentificacion getTipoIdentificacionByCodigo(final String codigo) {
        final StringBuilder sb = new StringBuilder();
        sb.append(" select ti from                            ");
        sb.append(TipoIdentificacion.ENTITY_NAME).append(" ti ");
        sb.append(" where ti.codigo = ?                       ");

        final List<TipoIdentificacion> tiposIdentificacion = getHibernateTemplate()
                .find(sb.toString(), codigo);

        return (tiposIdentificacion.isEmpty()) ? null : tiposIdentificacion
                .get(0);
    }

    public List<TipoIdentificacion> getTiposIdentificacion() {
        final DetachedCriteria criteria = DetachedCriteria
                .forClass(TipoIdentificacion.class);
        criteria.addOrder(Order.asc(TipoIdentificacion.PROPERTY_NAME_CODIGO));
        return getHibernateTemplate().findByCriteria(criteria);
    }

}
