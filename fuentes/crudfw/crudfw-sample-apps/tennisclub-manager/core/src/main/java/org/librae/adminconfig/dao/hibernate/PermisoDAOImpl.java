package org.librae.adminconfig.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IPermisoDAO;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.PermisoRol;
import org.librae.adminconfig.model.Rol;

import org.librae.common.Constants;
import org.librae.common.dao.hibernate.GenericSearchDao;
import org.librae.common.util.StringUtil;

/**
 * Implementación del DAO para la entidad Permiso
 * 
 * @author asantamaria
 */
public class PermisoDAOImpl extends GenericSearchDao<Permiso, Long> implements
        IPermisoDAO {

    /**
     * Constructor del DAO
     */
    public PermisoDAOImpl() {
        super(Permiso.class);
    }

    /**
     * @see org.librae.adminconfig.dao.IPermisoDAO#obtenerPermisosSinAsignar(java.lang.Long,
     *      java.lang.String, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    public List<Permiso> obtenerPermisosSinAsignar(final Long idRol,
            final String codigoPermiso, final Long idTipoPermiso) {
        List<Permiso> permisos = new ArrayList<Permiso>();
        final DetachedCriteria criteriaPermisosNoAsignados = DetachedCriteria
                .forClass(Permiso.class);
        if (codigoPermiso != null) {
            criteriaPermisosNoAsignados.add(Restrictions.ilike("codigo",
                    codigoPermiso, MatchMode.ANYWHERE));
        }
        if (idTipoPermiso != null) {
            criteriaPermisosNoAsignados.add(Restrictions.eq("categoria.id",
                    idTipoPermiso));
        }
        if (idRol != null) {
            final DetachedCriteria criteriaPermisosAsignados = DetachedCriteria
                    .forClass(PermisoRol.class);
            // criteriaPermisosAsignados.createAlias("rol", "rol");
            criteriaPermisosAsignados.add(Restrictions.eq("rol.id", idRol));
            criteriaPermisosAsignados.setProjection(Projections
                    .property("permiso.id"));

            criteriaPermisosNoAsignados.add(Property.forName("id").notIn(
                    criteriaPermisosAsignados));
        }

        criteriaPermisosNoAsignados.addOrder(Order.asc("codigo"));

        permisos = getHibernateTemplate().findByCriteria(
                criteriaPermisosNoAsignados);

        return permisos;
    }

    public List<Permiso> getPermisosByRol(Long idRol) {
        final StringBuilder sb = new StringBuilder();

        sb.append(" select p from                       ");
        sb.append(Permiso.ENTITY_NAME).append(" p,      ");
        sb.append(PermisoRol.ENTITY_NAME).append(" pr,  ");
        sb.append(Rol.ENTITY_NAME).append(" r           ");
        sb.append(" where p.id=pr.permiso.id            ");
        sb.append(" and pr.rol.id=r.id                  ");
        sb.append(" and r.id=?                          ");
        sb.append(" order by ");

        return getHibernateTemplate().find(sb.toString(), idRol);
    }

    @Override
    protected DetachedCriteria obtenerCriteria(Map<String, Object> criterios) {
        final DetachedCriteria criteriaPermisos = DetachedCriteria
                .forClass(Permiso.class);
        if (criterios != null) {
            if (!StringUtil.esVacia(criterios.get(Permiso.PROPTY_NAME_CODIGO))) {
                criteriaPermisos.add(Restrictions.ilike(
                        Permiso.PROPTY_NAME_CODIGO, criterios.get(
                                Permiso.PROPTY_NAME_CODIGO).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil.esVacia(criterios.get(Permiso.PROPTY_NAME_NOMBRE))) {
                criteriaPermisos.add(Restrictions.ilike(
                        Permiso.PROPTY_NAME_NOMBRE, criterios.get(
                                Permiso.PROPTY_NAME_NOMBRE).toString(),
                        MatchMode.ANYWHERE));
            }
            if ((criterios.get(Permiso.PROPTY_NAME_CATEGORIA) != null)
                    && (!(new Long(0)).equals(criterios
                            .get(Permiso.PROPTY_NAME_CATEGORIA)))) {
                criteriaPermisos.createAlias(Permiso.PROPTY_NAME_CATEGORIA,
                        Permiso.PROPTY_NAME_CATEGORIA);
                criteriaPermisos.add(Restrictions.eq("categoria.id", criterios
                        .get(Permiso.PROPTY_NAME_CATEGORIA)));
            }
            if ((criterios.get("idRol") != null)
                    && (!(new Long(0)).equals(criterios.get("idRol")))
                    && ("noAsignados".equals(criterios.get("buscarPor")))) {
                final DetachedCriteria criteriaPermisosAsignados = DetachedCriteria
                        .forClass(PermisoRol.class);
                criteriaPermisosAsignados.add(Restrictions.eq("rol.id",
                        criterios.get("idRol")));
                criteriaPermisosAsignados.setProjection(Projections
                        .property("permiso.id"));

                criteriaPermisos.add(Property.forName("id").notIn(
                        criteriaPermisosAsignados));
            }
            if ((criterios.get("idRol") != null)
                    && (!(new Long(0)).equals(criterios.get("idRol")))
                    && ("asignados".equals(criterios.get("buscarPor")))) {
                final DetachedCriteria criteriaPermisosAsignados = DetachedCriteria
                        .forClass(PermisoRol.class);
                criteriaPermisosAsignados.add(Restrictions.eq("rol.id",
                        criterios.get("idRol")));
                criteriaPermisosAsignados.setProjection(Projections
                        .property("permiso.id"));

                criteriaPermisos.add(Property.forName("id").in(
                        criteriaPermisosAsignados));
            }
        }
        return criteriaPermisos;
    }

    @Override
    protected void setOrdenacion(final DetachedCriteria criteria,
            final Map<String, Object> criterios) {

        if (criterios != null) {
            final String orden = (String) criterios.get(Constants.SORTCOLUMN);
            final Boolean ascendente = (Boolean) criterios
                    .get(Constants.ASCENDING);
            if ("categoria.codigo".equalsIgnoreCase(orden)) {
                criteria.setFetchMode(Permiso.PROPTY_NAME_CATEGORIA,
                        FetchMode.JOIN);
                criteria.createAlias(Permiso.PROPTY_NAME_CATEGORIA,
                        Permiso.PROPTY_NAME_CATEGORIA);
                if (ascendente) {
                    criteria.addOrder(Order.asc("categoria.codigo"));
                } else {
                    criteria.addOrder(Order.desc("categoria.codigo"));
                }
            } else {
                super.setOrdenacion(criteria, criterios);
            }
        }
    }
}
