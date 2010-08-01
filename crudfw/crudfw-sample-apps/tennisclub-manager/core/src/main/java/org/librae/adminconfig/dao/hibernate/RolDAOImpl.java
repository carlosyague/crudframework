/*
 * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
 * explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
 * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los t�rminos
 * de la Licencia EUPL European Public License publicada por el organismo IDABC
 * de la Comisi�n Europea, en su versi�n 1.0. o posteriores. �ste programa se
 * distribuye de buena fe, pero SIN NINGUNA GARANT�A, incluso sin las presuntas
 * garant�as impl�citas de USABILIDAD o ADECUACI�N A PROP�SITO CONCRETO. Para
 * mas informaci�n consulte la Licencia EUPL European Public License. Usted
 * recibe una copia de la Licencia EUPL European Public License junto con este
 * programa, si por alg�n motivo no le es posible visualizarla, puede
 * consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */

package org.librae.adminconfig.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IRolDAO;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.dao.hibernate.GenericSearchDao;
import org.librae.common.util.CollectionsUtil;
import org.librae.common.util.StringUtil;

/**
 * Implementación del DAO para la entidad Rol
 * 
 * @author asantamaria
 * @author jcisneros
 */
public class RolDAOImpl extends GenericSearchDao<Rol, Long> implements IRolDAO,
        Serializable {

    /**
     * Serial Version UID
     */
    private static final long   serialVersionUID = 1L;
    /** atributo de trazas */
    private transient final Log log              = LogFactory.getLog(this
                                                         .getClass());

    /**
     * Constructor del DAO
     */
    public RolDAOImpl() {
        super(Rol.class);
    }

    /**
     * @see src.main.java.org.librae.adminconfig.dao.IRolDAO#obtenerRolByCodigo(String)
     */
    @SuppressWarnings("unchecked")
    public Rol getRolByCodigo(final String codigo) {
        log.debug("Obtenemos el Rol con codigo : " + codigo);

        final StringBuilder sb = new StringBuilder();
        sb.append("select r from " + Rol.ENTITY_NAME
                + " r where r.codigo like  ? ");
        final Object[] parametros = { codigo };

        log.debug("HQL : " + sb.toString());
        final List datos = getHibernateTemplate().find(sb.toString(),
                parametros);

        log.info((datos.isEmpty()) ? "No se ha obtenido el Rol con codigo : "
                + codigo : "Se ha obtenido el Rol con codigo : " + codigo);
        return (datos.isEmpty()) ? null : (Rol) datos.get(0);
    }

    /**
     * @see org.librae.adminconfig.dao.IRolDAO#obtenerRolesSinAsignar(java.lang.Long,
     *      java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    public List<Rol> obtenerRolesSinAsignar(final Long idUsuario,
            final Long idBiblioteca, final String codigoRol) {
        List<Rol> roles = new ArrayList<Rol>();
        final StringBuilder hql = new StringBuilder();
        final Object[] parametros = { idUsuario, idBiblioteca };

        hql.append(" select r ");
        hql.append(" from ").append(Rol.ENTITY_NAME);
        hql.append(" r ");
        hql.append(" where r.codigo like '%");
        hql.append(codigoRol);
        hql.append("%' and r.id not in (");
        hql.append(" select ubr.rol.id ");
        hql.append(" from ").append(UsuarioBibliotecaRol.ENTITY_NAME);
        hql.append(" ubr ");
        hql.append(" where ubr.biblioteca.id = ? ");
        hql.append(" and ubr.usuario.id = ?) ");

        roles = getHibernateTemplate().find(hql.toString(), parametros);
        return roles;
    }

    /**
     * @see org.librae.common.dao.hibernate.GenericSearchDao#obtenerCriteria(Map)
     */
    protected DetachedCriteria obtenerCriteria(
            final Map<String, Object> criterios) {
        final DetachedCriteria criteriaRoles = DetachedCriteria
                .forClass(Rol.class);
        if (criterios != null) {
            if (!StringUtil.esVacia(criterios.get(Rol.PROPTY_NAME_CODIGO))) {
                criteriaRoles.add(Restrictions.ilike(Rol.PROPTY_NAME_CODIGO,
                        criterios.get(Rol.PROPTY_NAME_CODIGO).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil.esVacia(criterios.get(Rol.PROPTY_NAME_NOMBRE))) {
                criteriaRoles.add(Restrictions.ilike(Rol.PROPTY_NAME_NOMBRE,
                        criterios.get(Rol.PROPTY_NAME_NOMBRE).toString(),
                        MatchMode.ANYWHERE));
            }
            if ((criterios.get("asignados") != null)
                    && !((List<Rol>) criterios.get("asignados")).isEmpty()) {
                final List<Long> idRolesAsignados = new ArrayList<Long>();
                final List<Rol> rolesAsignados = (List<Rol>) criterios
                        .get("asignados");
                for (final Rol rol : rolesAsignados) {
                    idRolesAsignados.add(rol.getId());
                }
                final DetachedCriteria criteriaAsignados = DetachedCriteria
                        .forClass(Rol.class);
                criteriaAsignados.add(Restrictions.in("id", idRolesAsignados));
                criteriaAsignados.setProjection(Projections.property("id"));
            }
            if (criterios.get("nivelUsuario") != null) {
                criteriaRoles.add(Restrictions.le(Rol.PROPTY_NAME_NIVEL,
                        criterios.get("nivelUsuario")));
            }
        }
        return criteriaRoles;
    }

    /**
     * @see org.librae.adminconfig.dao.IRolDAO#obtenerRolesById(java.util.List)
     */
    public List<Rol> obtenerRolesById(final List<String> listadoId) {
        log.debug("Obtenemos un listado de Roles con los Ids : " + listadoId);

        final List<Long> listaIdLong = CollectionsUtil
                .listStringToLong(listadoId);

        List<Rol> roles = new ArrayList<Rol>();

        final DetachedCriteria criteria = DetachedCriteria.forClass(Rol.class);
        criteria.add(Property.forName(Rol.PROPTY_NAME_ID).in(listaIdLong));

        roles = getHibernateTemplate().findByCriteria(criteria);

        return roles;
    }

    /**
     * @see org.librae.adminconfig.dao.IRolDAO#getRoles(java.lang.Long,
     *      java.lang.Long)
     */
    public List<Rol> getRoles(final Long idUsuario, final Long idBiblioteca) {
        final StringBuilder hql = new StringBuilder();

        final Object[] parametros = { idBiblioteca, idUsuario };

        hql.append(" select ubr.rol ");
        hql.append(" from ").append(UsuarioBibliotecaRol.ENTITY_NAME).append(
                " ubr ");
        hql.append(" where ubr.biblioteca.id = ? ");
        hql.append(" and ubr.usuario.id = ? ");

        final List<Rol> roles = getHibernateTemplate().find(hql.toString(),
                parametros);
        return roles;
    }

}
