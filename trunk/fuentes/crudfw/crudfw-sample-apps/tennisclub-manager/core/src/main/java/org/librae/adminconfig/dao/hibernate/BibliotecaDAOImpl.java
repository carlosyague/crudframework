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
import java.util.List;
import java.util.Map;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IBibliotecaDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Calendario;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.PermisoRol;
import org.librae.adminconfig.model.Rol;

import org.librae.adminconfig.model.UsuarioBibliotecaRol;

import org.librae.common.Constants;
import org.librae.common.dao.hibernate.GenericSearchDao;
import org.librae.common.util.StringUtil;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Implementación del DAO para la entidad Biblioteca
 * 
 * @author aropero
 */
public class BibliotecaDAOImpl extends GenericSearchDao<Biblioteca, Long>
        implements IBibliotecaDAO, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor del DAO
     */
    public BibliotecaDAOImpl() {
        super(Biblioteca.class);
    }

    /**
     * @see org.librae.common.dao.hibernate.GenericSearchDao#obtenerCriteria(Map)
     */
    protected DetachedCriteria obtenerCriteria(
            final Map<String, Object> criterios) {
        final DetachedCriteria criteriaBibliotecas = DetachedCriteria
                .forClass(Biblioteca.class);
        if (criterios != null) {
            if (!StringUtil.esVacia(criterios.get("codigoBiblioteca"))) {
                criteriaBibliotecas.add(Restrictions.ilike(
                        Biblioteca.PROPTY_NAME_CODIGO, criterios.get(
                                "codigoBiblioteca").toString(),
                        MatchMode.ANYWHERE));
            }

            if (!StringUtil.esVacia(criterios
                    .get(Biblioteca.PROPTY_NAME_DESCRIPCION))) {
                criteriaBibliotecas.add(Restrictions.ilike(
                        Biblioteca.PROPTY_NAME_DESCRIPCION, criterios.get(
                                Biblioteca.PROPTY_NAME_DESCRIPCION).toString(),
                        MatchMode.ANYWHERE));
            }

            if (!StringUtil.esVacia(criterios
                    .get(Biblioteca.PROPTY_NAME_PERSONA_CONTACTO))) {
                criteriaBibliotecas.add(Restrictions.ilike(
                        Biblioteca.PROPTY_NAME_PERSONA_CONTACTO, criterios.get(
                                Biblioteca.PROPTY_NAME_PERSONA_CONTACTO)
                                .toString(), MatchMode.ANYWHERE));
            }

            if ((criterios.get(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA) != null)
                    && (!(new Long(0)).equals(criterios
                            .get(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA)))) {
                criteriaBibliotecas.createAlias(
                        Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA,
                        Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA);
                criteriaBibliotecas.add(Restrictions.eq("tipoBiblioteca.id",
                        criterios.get(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA)));
            }

            if (criterios.get("bibliotecas") != null) {
                final DetachedCriteria criteriaBibliotecasSeleccionadas = DetachedCriteria
                        .forClass(Biblioteca.class);
                listadoToInExpression(criteriaBibliotecasSeleccionadas, "id",
                        (List<Long>) criterios.get("bibliotecas"));
                criteriaBibliotecasSeleccionadas.setProjection(Projections
                        .property("id"));

                criteriaBibliotecas.add(Property.forName(
                        Biblioteca.PROPTY_NAME_ID).in(
                        criteriaBibliotecasSeleccionadas));
            }

        }
        return criteriaBibliotecas;
    }

    /**
     * @see org.librae.adminconfig.dao.IBibliotecaDAO#getBibliotecaByTipo(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Biblioteca> getBibliotecaByTipoConPadre(final String tipo,
            final Long idBibliotecaPadre) {
        Object[] parametros = null;

        final StringBuilder sb = new StringBuilder();
        sb.append("select b from ");
        sb.append(Biblioteca.ENTITY_NAME).append(" b ");
        sb.append("where b.tipoBiblioteca.codigo = ? ");

        if (idBibliotecaPadre == null) {
            parametros = new Object[1];
            parametros[0] = tipo;
        } else {
            sb.append("and b.padre.id = ? ");
            parametros = new Object[2];
            parametros[0] = tipo;
            parametros[1] = idBibliotecaPadre;
        }

        final List<Biblioteca> bibliotecas = getHibernateTemplate().find(
                sb.toString(), parametros);

        return bibliotecas;
    }

    /**
     * @see org.librae.adminconfig.dao.IBibliotecaDAO#getBibliotecaByTipoConUsuario(java.lang.String,
     *      java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    public List<Biblioteca> getBibliotecaByTipoConUsuario(final String tipo,
            final Long idUsuario) {
        final Object[] parametros = { idUsuario, tipo };

        final StringBuilder sb = new StringBuilder();
        sb.append(" select b from                                    ");
        sb.append(Biblioteca.ENTITY_NAME).append(" b                 ");
        sb.append(" where b.id in (                                  ");
        sb.append("     select bur.biblioteca from                   ");
        sb.append(UsuarioBibliotecaRol.ENTITY_NAME).append(" bur     ");
        sb.append("     where bur.usuario.id = ?                     ");
        sb.append("     and bur.biblioteca.tipoBiblioteca.codigo = ?)");

        final List<Biblioteca> bibliotecas = getHibernateTemplate().find(
                sb.toString(), parametros);

        return bibliotecas;
    }

    /**
     * @see org.librae.adminconfig.dao.IBibliotecaDAO#getBibliotecasByUsuario(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    public List<Biblioteca> getBibliotecasByUsuario(final Long idUsuario) {
        final Object[] parametros = { idUsuario };

        final StringBuilder sb = new StringBuilder();
        sb.append("select b from ");
        sb.append(Biblioteca.ENTITY_NAME).append(" b ");
        sb.append("where b.id in (");
        sb.append("select distinct(bur.biblioteca.id) from ");
        sb.append(UsuarioBibliotecaRol.ENTITY_NAME).append(" bur ");
        sb.append("where bur.usuario.id = ?) ");
        sb.append(" order by b.descripcion                       ");

        final List<Biblioteca> bibliotecas = getHibernateTemplate().find(
                sb.toString(), parametros);

        return bibliotecas;
    }

    /**
     * @see org.librae.adminconfig.dao.IBibliotecaDAO#getBibliotecaByCodigo(java.lang.String)
     */
    public Biblioteca getBibliotecaByCodigo(final String codigo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select b from ");
        sb.append(Biblioteca.ENTITY_NAME).append(" b ");
        sb.append("where b.codigo = ? ");
        final List<Biblioteca> bibliotecas = getHibernateTemplate().find(
                sb.toString(), codigo);

        return ((bibliotecas.isEmpty()) ? null : bibliotecas.get(0));
    }

    /**
     * @see org.librae.adminconfig.dao.IBibliotecaDAO#actLabSegunCalendario(java.lang.Long)
     */
    public String actLabSegunCalendario(Long idBiblioteca) {
        final Object[] parametros = { idBiblioteca };

        final StringBuilder sb = new StringBuilder();
        sb.append("select b.calendario.diasSemana from ");
        sb.append(Biblioteca.ENTITY_NAME).append(" b ");
        sb.append("where b.id = ? ");
        final List<String> diasSemana = getHibernateTemplate().find(
                sb.toString(), parametros);

        return ((diasSemana.isEmpty()) ? null : diasSemana.get(0));
    }

    public List<Biblioteca> buscarPorString(final Map<String, Object> criterios) {
        final DetachedCriteria criteriaBibliotecas = DetachedCriteria
                .forClass(Biblioteca.class);
        if (criterios != null) {
            if (!StringUtil.esVacia(criterios
                    .get(Biblioteca.PROPTY_NAME_CODIGO))) {
                criteriaBibliotecas.add(Restrictions.ilike(
                        Biblioteca.PROPTY_NAME_CODIGO, criterios.get(
                                Biblioteca.PROPTY_NAME_CODIGO).toString(),
                        MatchMode.ANYWHERE));
            }

            if (!StringUtil.esVacia(criterios
                    .get(Biblioteca.PROPTY_NAME_DESCRIPCION))) {
                criteriaBibliotecas.add(Restrictions.ilike(
                        Biblioteca.PROPTY_NAME_DESCRIPCION, criterios.get(
                                Biblioteca.PROPTY_NAME_DESCRIPCION).toString(),
                        MatchMode.ANYWHERE));
            }
        }
        return getHibernateTemplate().findByCriteria(criteriaBibliotecas);
    }

    public List<Biblioteca> getBibliotecasByUsuarioPermiso(Long idUsuario,
            String permiso) {
        final Object[] parametros = { idUsuario, permiso };

        final StringBuilder hql = new StringBuilder();
        hql.append("select b from                                   ");
        hql.append(Biblioteca.ENTITY_NAME).append(" b               ");
        hql.append("where b.id in (                                 ");
        hql.append("select distinct(bur.biblioteca.id) from            ");

        hql.append(UsuarioBibliotecaRol.ENTITY_NAME).append(" bur,  ");
        hql.append(Rol.ENTITY_NAME).append(" rol,                   ");
        hql.append(PermisoRol.ENTITY_NAME).append(" pr,             ");
        hql.append(Permiso.ENTITY_NAME).append(" permiso            ");

        hql.append(" where bur.usuario.id = ?                       ");
        hql.append(" and bur.rol.id = rol.id                        ");
        hql.append(" and rol.id = pr.rol.id                         ");
        hql.append(" and pr.permiso.id = permiso.id                 ");
        hql.append(" and permiso.codigo = ?)                        ");
        hql.append(" order by b.descripcion                       ");

        final List<Biblioteca> bibliotecas = getHibernateTemplate().find(
                hql.toString(), parametros);

        return bibliotecas;
    }

    public Calendario getCalendario(Long idBiblioteca) {
        final StringBuilder hql = new StringBuilder();
        hql.append("select biblioteca.calendario from          ");
        hql.append(Biblioteca.ENTITY_NAME).append(" biblioteca ");
        hql.append(" where biblioteca.id=? ");

        final List<Calendario> calendario = getHibernateTemplate().find(
                hql.toString(), idBiblioteca);

        return ((calendario.isEmpty()) ? null : calendario.get(0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setOrdenacion(final DetachedCriteria criteria,
            final Map<String, Object> criterios) {
        final String orden = (String) criterios.get(Constants.SORTCOLUMN);
        if ((criterios != null) && (orden != null)) {
            final Boolean ascendente = (Boolean) criterios
                    .get(Constants.ASCENDING);
            if (orden.equalsIgnoreCase("tipoBiblioteca.codigo")) {
                criteria.createAlias("tipoBiblioteca", "tipoBiblioteca");
                criteria.setFetchMode("lectorTipo", FetchMode.JOIN);
                if (ascendente) {
                    criteria.addOrder(Order.asc("tipoBiblioteca.codigo"));
                } else {
                    criteria.addOrder(Order.desc("tipoBiblioteca.codigo"));
                }

            } else {
                super.setOrdenacion(criteria, criterios);
            }
        }
    }

    public List<Biblioteca> getBibliotecas() {
        final StringBuilder hql = new StringBuilder();
        hql.append("select biblioteca from			            ");
        hql.append(Biblioteca.ENTITY_NAME).append(" biblioteca  ");
        hql.append(" order by descripcion						");
        final HibernateTemplate ht = getHibernateTemplate();
        final List<Biblioteca> bibliotecas = ht.find(hql.toString());
        return bibliotecas;
    }

    /**
     * @see org.librae.adminconfig.dao.IBibliotecaDAO#getBibliotecaByDescripcion(java.lang.String)
     */
    public Biblioteca getBibliotecaByDescripcion(final String codigo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select b from ");
        sb.append(Biblioteca.ENTITY_NAME).append(" b ");
        sb.append("where b.descripcion = ? ");
        final List<Biblioteca> bibliotecas = getHibernateTemplate().find(
                sb.toString(), codigo);

        return ((bibliotecas.isEmpty()) ? null : bibliotecas.get(0));
    }

    public Long getIdBibliotecaByDescripcion(String nodoDescripcionClicked) {
        final DetachedCriteria criteriaBibliotecas = DetachedCriteria
                .forClass(Biblioteca.class);
        if (nodoDescripcionClicked != null) {
            criteriaBibliotecas.add(Restrictions.ilike(
                    Biblioteca.PROPTY_NAME_DESCRIPCION, nodoDescripcionClicked,
                    MatchMode.START));
        }
        criteriaBibliotecas.setProjection(Projections.property("id"));
        final List<Long> bibliotecas = getHibernateTemplate().findByCriteria(
                criteriaBibliotecas);
        return ((bibliotecas.isEmpty()) ? null : bibliotecas.get(0));
    }

    public Long getIdBibliotecaByCodigo(String nodoDescripcionClicked) {
        final DetachedCriteria criteriaBibliotecas = DetachedCriteria
                .forClass(Biblioteca.class);
        if (nodoDescripcionClicked != null) {
            criteriaBibliotecas.add(Restrictions.ilike(
                    Biblioteca.PROPTY_NAME_CODIGO, nodoDescripcionClicked,
                    MatchMode.START));
        }
        criteriaBibliotecas.setProjection(Projections.property("id"));
        final List<Long> bibliotecas = getHibernateTemplate().findByCriteria(
                criteriaBibliotecas);
        return ((bibliotecas.isEmpty()) ? null : bibliotecas.get(0));
    }

}
