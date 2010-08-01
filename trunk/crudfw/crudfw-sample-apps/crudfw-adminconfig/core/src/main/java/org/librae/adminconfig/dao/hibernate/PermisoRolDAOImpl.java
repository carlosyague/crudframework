package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import org.librae.adminconfig.dao.IPermisoRolDAO;
import org.librae.adminconfig.model.PermisoRol;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad PermisoRol
 * 
 * @author asantamaria
 */
public class PermisoRolDAOImpl extends GenericDAOImpl<PermisoRol, Long>
        implements IPermisoRolDAO {

    /**
     * Constructor del DAO
     */
    public PermisoRolDAOImpl() {
        super(PermisoRol.class);
    }

    /**
     * @see org.librae.adminconfig.dao.IPermisoRolDAO#borrarRoles(java.lang.Long)
     */
    public void borrarRoles(Long idRol) {
        final String hql = "delete from " + PermisoRol.ENTITY_NAME
                + " p where p.rol.id = ?";
        getHibernateTemplate().bulkUpdate(hql, idRol);
    }

    public PermisoRol getPermisoRolByIds(Long idRol, Long idPermiso) {
        final StringBuilder hql = new StringBuilder();
        final Object[] params = { idRol, idPermiso };

        hql.append(" select pr                                          ");
        hql.append(" from ").append(PermisoRol.ENTITY_NAME).append(" pr ");
        hql.append(" where pr.rol.id = ?                                ");
        hql.append(" and pr.permiso.id = ?                              ");

        final List<PermisoRol> count = getHibernateTemplate().find(
                hql.toString(), params);
        return count.get(0);
    }
}
