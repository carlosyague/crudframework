package org.librae.adminconfig.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.ICodigoDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.common.dao.hibernate.GenericSearchDao;

/**
 * Implementación del DAO para la entidad Codigo
 * 
 * @author asantamaria
 */
public class CodigoDAOImpl extends GenericSearchDao<Codigo, Long> implements
        ICodigoDAO, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 1824775171171310344L;

    /**
     * Constructor del DAO
     */
    public CodigoDAOImpl() {
        super(Codigo.class);
    }

    /**
     * @see org.librae.common.dao.hibernate.GenericSearchDao#obtenerCriteria(Map)
     */
    protected DetachedCriteria obtenerCriteria(
            final Map<String, Object> criterios) {
        final DetachedCriteria criteriaCodigos = DetachedCriteria
                .forClass(Codigo.class);
        if (criterios != null) {
            if (criterios.get(Codigo.PROPTY_NAME_TIPO_CODIGO) != null) {
                criteriaCodigos.add(Restrictions.eq("tipoCodigo.id", criterios
                        .get(Codigo.PROPTY_NAME_TIPO_CODIGO)));
            }
        }
        if (criterios != null) {
            if (criterios.get(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA) != null) {
                final DetachedCriteria criteriaTipoBiblioteca = DetachedCriteria
                        .forClass(TipoBiblioteca.class);
                criteriaTipoBiblioteca.add(Restrictions.eq("id", criterios
                        .get(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA)));
                criteriaTipoBiblioteca.setProjection(Projections
                        .property("codigo"));

                final List<String> lista = getHibernateTemplate()
                        .findByCriteria(criteriaTipoBiblioteca);

                for (final String element : lista) {
                    criteriaCodigos.add(Restrictions.ilike("aplicaGBS",
                            element, MatchMode.ANYWHERE));
                }
            }
        }
        return criteriaCodigos;
    }

    /**
     * @see org.librae.adminconfig.dao.ICodigoDAO#getCodigoByCodigo(java.lang.String)
     */
    public Codigo getCodigoByCodigo(final String codigo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select c from " + Codigo.ENTITY_NAME
                + " c where c.codigo like  ? ");
        final Object[] parametros = { codigo };
        final List<Codigo> datos = getHibernateTemplate().find(sb.toString(),
                parametros);
        return (datos.isEmpty()) ? null : (Codigo) datos.get(0);
    }

    public List<Codigo> getCodigo (String codigo, Long idTipoCodigo) {
    	List<Codigo> codigos = null;
        final StringBuilder sb = new StringBuilder();
        final Object[] parametros = { codigo, idTipoCodigo };
        sb.append("select c ");
        sb.append(" from " + Codigo.ENTITY_NAME);
        sb.append(" c where c.codigo like ? ");
        sb.append(" and c.tipoCodigo.id=? ");
        codigos = getHibernateTemplate().find(sb.toString(),
                parametros);
    	return codigos;
    }
}
