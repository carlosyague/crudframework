package org.librae.adminconfig.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.model.Catalogo;
import org.librae.adminconfig.dao.ICatalogoDAO;
import org.librae.common.dao.hibernate.GenericSearchDao;
import org.librae.common.util.StringUtil;

/**
 * Implementación del DAO para la entidad Catalogo
 * 
 * @author asantamaria
 * @author impena
 */
public class CatalogoDAOImpl extends GenericSearchDao<Catalogo, Long> implements
        ICatalogoDAO {

    /**
     * Constructor del DAO
     */
    public CatalogoDAOImpl() {
        super(Catalogo.class);
    }

    /**
     * @see org.librae.common.dao.hibernate.GenericSearchDao#obtenerCriteria(Map)
     */
    @Override
    protected DetachedCriteria obtenerCriteria(Map<String, Object> criterios) {
        final DetachedCriteria criteriaCatalogo = DetachedCriteria
                .forClass(Catalogo.class);
        if (criterios != null) {
            if (!StringUtil.esVacia(criterios
                    .get(Catalogo.PROPERTY_NAME_CODIGO))) {
                criteriaCatalogo.add(Restrictions.ilike(
                        Catalogo.PROPERTY_NAME_CODIGO, criterios.get(
                                Catalogo.PROPERTY_NAME_CODIGO).toString(),
                        MatchMode.ANYWHERE));
            }

            if (!StringUtil.esVacia(criterios
                    .get(Catalogo.PROPERTY_NAME_NOMBRE))) {
                criteriaCatalogo.add(Restrictions.ilike(
                        Catalogo.PROPERTY_NAME_NOMBRE, criterios.get(
                                Catalogo.PROPERTY_NAME_NOMBRE).toString(),
                        MatchMode.ANYWHERE));
            }
        }
        return criteriaCatalogo;
    }

    /**
     * @see org.librae.adminconfig.dao.ICatalogoDAO#getCatalogoByCodigo(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Catalogo getCatalogoByCodigo(String codigo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select c from ");
        sb.append(Catalogo.ENTITY_NAME).append(" c ");
        sb.append("where c.codigo = ? ");
        final List<Catalogo> catalogos = this.getHibernateTemplate().find(
                sb.toString(), codigo);

        return ((catalogos.isEmpty()) ? null : catalogos.get(0));
    }

    /**
     * @see org.librae.adminconfig.dao.ICatalogoDAO#getCatalogoByNombre(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Catalogo getCatalogoByNombre(String nombre) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select c from ");
        sb.append(Catalogo.ENTITY_NAME).append(" c ");
        sb.append("where c.nombre = ? ");
        final List<Catalogo> catalogos = this.getHibernateTemplate().find(
                sb.toString(), nombre);

        return ((catalogos.isEmpty()) ? null : catalogos.get(0));
    }

}
