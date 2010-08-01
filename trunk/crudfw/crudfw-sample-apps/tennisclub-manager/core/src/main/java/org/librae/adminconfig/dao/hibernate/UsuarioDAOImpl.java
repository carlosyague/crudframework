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

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IUsuarioDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.PermisoRol;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.Constants;
import org.librae.common.dao.hibernate.GenericSearchDao;
import org.librae.common.util.CollectionsUtil;
import org.librae.common.util.StringUtil;

/**
 * Implementación del DAO para la entidad Usuario
 * 
 * @author asantamaria
 */
public class UsuarioDAOImpl extends GenericSearchDao<Usuario, Long> implements
        IUsuarioDAO, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5258082493083250432L;

    /**
     * Constructor del DAO
     */
    public UsuarioDAOImpl() {
        super(Usuario.class);
    }

    /**
     * @see org.librae.adminconfig.dao.IUsuarioDAO#buscar(org.librae.adminconfig.model.Usuario)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected DetachedCriteria obtenerCriteria(
            final Map<String, Object> criterios) {
        final DetachedCriteria criteriaUsuarios = DetachedCriteria
                .forClass(Usuario.class);
        if (criterios != null) {
            if (!StringUtil.esVacia(criterios
                    .get(Usuario.PROPERTY_NAME_APELLIDO1))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_APELLIDO1, criterios.get(
                                Usuario.PROPERTY_NAME_APELLIDO1).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil.esVacia(criterios
                    .get(Usuario.PROPERTY_NAME_APELLIDO2))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_APELLIDO2, criterios.get(
                                Usuario.PROPERTY_NAME_APELLIDO2).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil
                    .esVacia(criterios.get(Usuario.PROPERTY_NAME_NOMBRE))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_NOMBRE, criterios.get(
                                Usuario.PROPERTY_NAME_NOMBRE).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil.esVacia(criterios.get(Usuario.PROPERTY_NAME_EMAIL))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_EMAIL, criterios.get(
                                Usuario.PROPERTY_NAME_EMAIL).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil.esVacia(criterios
                    .get(Usuario.PROPERTY_NAME_TELEFONO))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_TELEFONO, criterios.get(
                                Usuario.PROPERTY_NAME_TELEFONO).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil.esVacia(criterios.get(Usuario.PROPERTY_NAME_FAX))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_FAX, criterios.get(
                                Usuario.PROPERTY_NAME_FAX).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil.esVacia(criterios.get(Usuario.PROPERTY_NAME_MOVIL))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_MOVIL, criterios.get(
                                Usuario.PROPERTY_NAME_MOVIL).toString(),
                        MatchMode.ANYWHERE));
            }
            if (!StringUtil.esVacia(criterios
                    .get(Usuario.PROPERTY_NAME_USUARIO))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_USUARIO, criterios.get(
                                Usuario.PROPERTY_NAME_USUARIO).toString(),
                        MatchMode.ANYWHERE));
            }
            if ((criterios.get(Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION) != null)
                    && (!(new Long(0)).equals(criterios
                            .get(Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION)))) {
                criteriaUsuarios.createAlias(
                        Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION,
                        Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION);
                criteriaUsuarios
                        .add(Restrictions
                                .eq(
                                        "tipoIdentificacion.id",
                                        criterios
                                                .get(Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION)));
            }
            if (!StringUtil.esVacia(criterios
                    .get(Usuario.PROPERTY_NAME_NUMERO_IDENTIFICACION))) {
                criteriaUsuarios.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_NUMERO_IDENTIFICACION,
                        criterios.get(
                                Usuario.PROPERTY_NAME_NUMERO_IDENTIFICACION)
                                .toString(), MatchMode.ANYWHERE));
            }

            // Fechas
            if (criterios.get("fechaAltaDesde") != null) {
                criteriaUsuarios.add(Restrictions.ge(
                        Usuario.PROPERTY_NAME_FECHA_ALTA, criterios
                                .get("fechaAltaDesde")));
            }
            if (criterios.get("fechaAltaHasta") != null) {
                criteriaUsuarios.add(Restrictions.le(
                        Usuario.PROPERTY_NAME_FECHA_ALTA, criterios
                                .get("fechaAltaHasta")));
            }
            if (criterios.get("fechaBajaDesde") != null) {
                criteriaUsuarios.add(Restrictions.ge(
                        Usuario.PROPERTY_NAME_FECHA_BAJA, criterios
                                .get("fechaBajaDesde")));
            }
            if (criterios.get("fechaBajaHasta") != null) {
                criteriaUsuarios.add(Restrictions.le(
                        Usuario.PROPERTY_NAME_FECHA_BAJA, criterios
                                .get("fechaBajaHasta")));
            }

            if (criterios.get("bibliotecas") != null) {
                final List<Long> bibliotecas = (List<Long>) criterios
                        .get("bibliotecas");

                final DetachedCriteria criteriaUsuariosAsignados = DetachedCriteria
                        .forClass(UsuarioBibliotecaRol.class);

                listadoToInExpression(criteriaUsuariosAsignados,
                        "biblioteca.id", bibliotecas);
                criteriaUsuariosAsignados.setProjection(Projections
                        .property("usuario.id"));

                criteriaUsuarios.add(Property.forName(Usuario.PROPERTY_NAME_ID)
                        .in(criteriaUsuariosAsignados));
            }
            if ((criterios.get("idRoles") != null)
                    && !(((List<String>) criterios.get("idRoles")).isEmpty())) {
                final List<String> idRoles = (List<String>) criterios
                        .get("idRoles");
                if (!idRoles.contains("0")) {
                    final List<Long> listaIdLong = CollectionsUtil
                            .listStringToLong(idRoles);
                    final DetachedCriteria criteriaUsuariosAsignados = DetachedCriteria
                            .forClass(UsuarioBibliotecaRol.class);
                    // criteriaUsuariosAsignados.createAlias("rol", "rol");
                    criteriaUsuariosAsignados.add(Restrictions.in("rol.id",
                            listaIdLong));
                    criteriaUsuariosAsignados.setProjection(Projections
                            .property("usuario.id"));

                    criteriaUsuarios.add(Property.forName(
                            Usuario.PROPERTY_NAME_ID).in(
                            criteriaUsuariosAsignados));
                }
            }

            if ((criterios.get(Usuario.PROPERTY_NAME_ACTIVO) != null)
                    && (!(new Long(2)).equals(criterios
                            .get(Usuario.PROPERTY_NAME_ACTIVO)))) {
                criteriaUsuarios.add(Restrictions.eq(
                        Usuario.PROPERTY_NAME_ACTIVO, (criterios
                                .get(Usuario.PROPERTY_NAME_ACTIVO)
                                .equals(new Long(1)))));
            }
        }
        return criteriaUsuarios;
    }

    /**
     * @see org.librae.adminconfig.dao.IUsuarioDAO#getUsuarioByUsuario(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Usuario getUsuarioByUsuario(final String usuario) {
        final StringBuilder hql = new StringBuilder();
        final Object[] parametros = { usuario };
        hql.append("select u from " + Usuario.ENTITY_NAME
                + " u where u.usuario = ?");

        log.debug("hql..." + hql.toString());
        final List<Usuario> usuarios = getHibernateTemplate().find(
                hql.toString(), parametros);
        return (usuarios.size() == 1) ? (Usuario) usuarios.get(0) : null;
    }

    /**
     * {@inheritDoc}
     */
    public Usuario getUsuarioByNIF(String nif) {
        final StringBuilder hql = new StringBuilder();

        if (nif != null) {
            nif = nif.toUpperCase();
        }

        final Object[] parametros = { Constants.TIPO_IDENTIFICACION_NIF, nif };
        hql.append("select u from ").append(Usuario.ENTITY_NAME).append(
                " u where u.")
                .append(Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION).append(".")
                .append(TipoIdentificacion.PROPERTY_NAME_CODIGO).append(
                        " = ? and ");
        hql.append("upper(u.");
        hql.append(Usuario.PROPERTY_NAME_NUMERO_IDENTIFICACION);
        hql.append(") = ?");

        log.debug("hql..." + hql.toString());
        final List<Usuario> usuarios = getHibernateTemplate().find(
                hql.toString(), parametros);
        return (usuarios.size() == 1) ? (Usuario) usuarios.get(0) : null;
    }

    /**
     * @see org.librae.adminconfig.dao.IUsuarioDAO#getUsuariosById(java.util.List)
     */
    public List<Usuario> getUsuariosById(final List<String> listadoIds) {
        final List<Long> listaIdLong = CollectionsUtil
                .listStringToLong(listadoIds);

        List<Usuario> usuarios = new ArrayList<Usuario>();

        final DetachedCriteria criteria = DetachedCriteria
                .forClass(Usuario.class);
        criteria
                .add(Property.forName(Usuario.PROPERTY_NAME_ID).in(listaIdLong));

        usuarios = getHibernateTemplate().findByCriteria(criteria);

        return usuarios;
    }

    public List<Usuario> getUsuariosBySucursalId(String sucursalId) {

        final StringBuilder hqlSb = new StringBuilder();
        final Object[] parametros = { Long.parseLong(sucursalId) };
        hqlSb.append("select u from ");
        hqlSb.append(Usuario.ENTITY_NAME);
        hqlSb.append(" u where u.");
        hqlSb.append(Usuario.PROPERTY_NAME_BIB_ACTUAL);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);
        hqlSb.append(" = ?");

        final String hql = hqlSb.toString();

        log.debug("hql..." + hql);
        final List<Usuario> usuarios = getHibernateTemplate().find(
                hqlSb.toString(), parametros);
        return usuarios;

    }

    /**
     * @deprecated
     */
    public List<Usuario> getUsuariosBySucIdXaPrestSeg(String sucursalId) {

        final StringBuilder hqlSb = new StringBuilder();
        final Object[] parametros = { Long.parseLong(sucursalId),
                "CIR_Prestamo_Domicilio", "CIR_Prestamo_Sala" };

        hqlSb.append("select u");
        hqlSb.append(" from ");
        hqlSb.append(Usuario.ENTITY_NAME);
        hqlSb.append(" u , ");
        hqlSb.append(Biblioteca.ENTITY_NAME);
        hqlSb.append(" b , ");
        hqlSb.append(UsuarioBibliotecaRol.ENTITY_NAME);
        hqlSb.append(" ubr ,");
        hqlSb.append(PermisoRol.ENTITY_NAME);
        hqlSb.append(" pr ");

        hqlSb.append(" where b.");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);
        hqlSb.append(" = ? ");

        hqlSb.append(" and ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_USUARIO);
        hqlSb.append(".");
        hqlSb.append(Usuario.PROPERTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" u.");
        hqlSb.append(Usuario.PROPERTY_NAME_ID);

        hqlSb.append(" and ");

        hqlSb.append(" ( ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_BIBLIOTECA);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" b.");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);

        hqlSb.append(" or ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_BIBLIOTECA);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" b.");
        hqlSb.append(Biblioteca.PROPTY_NAME_PADRE);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);

        hqlSb.append(" or ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_BIBLIOTECA);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" b.");
        hqlSb.append(Biblioteca.PROPTY_NAME_PADRE);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_PADRE);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);

        hqlSb.append(" ) ");

        hqlSb.append(" and ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_ROL);
        hqlSb.append(".");
        hqlSb.append(Rol.PROPTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" pr.");
        hqlSb.append(PermisoRol.PROPTY_NAME_ROL);
        hqlSb.append(".");
        hqlSb.append(Rol.PROPTY_NAME_ID);

        hqlSb.append(" and ");

        hqlSb.append(" ( ");

        hqlSb.append(" pr.");
        hqlSb.append(PermisoRol.PROPTY_NAME_PERMISO);
        hqlSb.append(".");
        hqlSb.append(Permiso.PROPTY_NAME_CODIGO);
        hqlSb.append(" = ? ");

        hqlSb.append(" or ");

        hqlSb.append(" pr.");
        hqlSb.append(PermisoRol.PROPTY_NAME_PERMISO);
        hqlSb.append(".");
        hqlSb.append(Permiso.PROPTY_NAME_CODIGO);
        hqlSb.append(" = ? ");

        hqlSb.append(" ) ");

        final String hql = hqlSb.toString();

        log.debug("hql..." + hql);
        final List<Usuario> usuarios = getHibernateTemplate().find(
                hqlSb.toString(), parametros);
        return usuarios;

    }

    /**
     * @deprecated
     */
    public List<Usuario> getUsuariosByCodSucXaPrestSeg(String codSucursal) {

        final StringBuilder hqlSb = new StringBuilder();
        final Object[] parametros = { codSucursal, "CIR_Prestamo_Domicilio",
                "CIR_Prestamo_Sala" };

        hqlSb.append("select u");
        hqlSb.append(" from ");
        hqlSb.append(Usuario.ENTITY_NAME);
        hqlSb.append(" u , ");
        hqlSb.append(Biblioteca.ENTITY_NAME);
        hqlSb.append(" b , ");
        hqlSb.append(UsuarioBibliotecaRol.ENTITY_NAME);
        hqlSb.append(" ubr ,");
        hqlSb.append(PermisoRol.ENTITY_NAME);
        hqlSb.append(" pr ");

        hqlSb.append(" where b.");
        hqlSb.append(Biblioteca.PROPTY_NAME_CODIGO);
        hqlSb.append(" = ? ");

        hqlSb.append(" and ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_USUARIO);
        hqlSb.append(".");
        hqlSb.append(Usuario.PROPERTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" u.");
        hqlSb.append(Usuario.PROPERTY_NAME_ID);

        hqlSb.append(" and ");

        hqlSb.append(" ( ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_BIBLIOTECA);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" b.");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);

        hqlSb.append(" or ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_BIBLIOTECA);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" b.");
        hqlSb.append(Biblioteca.PROPTY_NAME_PADRE);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);

        hqlSb.append(" or ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_BIBLIOTECA);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" b.");
        hqlSb.append(Biblioteca.PROPTY_NAME_PADRE);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_PADRE);
        hqlSb.append(".");
        hqlSb.append(Biblioteca.PROPTY_NAME_ID);

        hqlSb.append(" ) ");

        hqlSb.append(" and ");

        hqlSb.append(" ubr.");
        hqlSb.append(UsuarioBibliotecaRol.PROPTY_NAME_ROL);
        hqlSb.append(".");
        hqlSb.append(Rol.PROPTY_NAME_ID);
        hqlSb.append(" = ");
        hqlSb.append(" pr.");
        hqlSb.append(PermisoRol.PROPTY_NAME_ROL);
        hqlSb.append(".");
        hqlSb.append(Rol.PROPTY_NAME_ID);

        hqlSb.append(" and ");

        hqlSb.append(" ( ");

        hqlSb.append(" pr.");
        hqlSb.append(PermisoRol.PROPTY_NAME_PERMISO);
        hqlSb.append(".");
        hqlSb.append(Permiso.PROPTY_NAME_CODIGO);
        hqlSb.append(" = ? ");

        hqlSb.append(" or ");

        hqlSb.append(" pr.");
        hqlSb.append(PermisoRol.PROPTY_NAME_PERMISO);
        hqlSb.append(".");
        hqlSb.append(Permiso.PROPTY_NAME_CODIGO);
        hqlSb.append(" = ? ");

        hqlSb.append(" ) ");

        final String hql = hqlSb.toString();

        log.debug("hql..." + hql);
        final List<Usuario> usuarios = getHibernateTemplate().find(
                hqlSb.toString(), parametros);
        return usuarios;

    }

    /**
     * {@inheritDoc}
     */
    public List<Usuario> getUsuariosUnRol() {
        final StringBuilder sb = new StringBuilder();

        sb.append(" select usuario from                  		");
        sb.append(Usuario.ENTITY_NAME).append(" u				");
        sb.append(" where usuario.id in (						");
        sb.append(" select bur.usuario.id from                  ");
        sb.append(UsuarioBibliotecaRol.ENTITY_NAME).append(" bur");
        sb.append(" group by bur.usuario.id                     ");
        sb.append(" having count(bur.usuario.id)=1)             ");

        final List<Usuario> usuarios = getHibernateTemplate().find(
                sb.toString());

        return usuarios;
    }
}
