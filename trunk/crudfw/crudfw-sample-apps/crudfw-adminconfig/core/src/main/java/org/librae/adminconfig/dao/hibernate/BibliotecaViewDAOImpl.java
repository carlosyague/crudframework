package org.librae.adminconfig.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IBibliotecaViewDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.PermisoRol;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.dao.hibernate.GenericDAOImpl;
import org.librae.common.util.StringUtil;

/**
 * Implementación del DAO para la entidad BibliotecaView
 * 
 * @author bagarcia
 */
public class BibliotecaViewDAOImpl extends GenericDAOImpl<BibliotecaView, Long>
		implements IBibliotecaViewDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234123412341234L;

	/**
	 * Constructor del DAO
	 */
	public BibliotecaViewDAOImpl() {
		super(BibliotecaView.class);
	}

	public List<BibliotecaView> getBibliotecas() {
		final StringBuilder hql = new StringBuilder();
		hql.append("select biblioteca from			            	");
		hql.append(BibliotecaView.ENTITY_NAME).append(" biblioteca  ");
		hql.append(" order by descripcion							");
		final List<BibliotecaView> bibliotecas = (List<BibliotecaView>) getHibernateTemplate()
				.find(hql.toString());
		return bibliotecas;
	}

	public List<BibliotecaView> getBibliotecasByUsuario(final Long idUsuario) {
		final Object[] parametros = { idUsuario };

		final StringBuilder sb = new StringBuilder();
		sb.append(" select b from ");
		sb.append(BibliotecaView.ENTITY_NAME).append(" b 			");
		sb.append(" where b.id in (									");
		sb.append(" select distinct(bur.biblioteca.id) from 		");
		sb.append(UsuarioBibliotecaRol.ENTITY_NAME).append(" bur 	");
		sb.append(" where bur.usuario.id = ?) 						");
		sb.append(" order by b.descripcion                       	");

		final List<BibliotecaView> bibliotecas = getHibernateTemplate().find(
				sb.toString(), parametros);

		return bibliotecas;
	}

	public List<BibliotecaView> buscarPorString(
			final Map<String, Object> criterios) {
		final DetachedCriteria criteriaBibliotecas = DetachedCriteria
				.forClass(BibliotecaView.class);
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
	
	public List<BibliotecaView> getBibliotecasByUsuarioPermiso(Long idUsuario,
            String permiso) {
        final Object[] parametros = { idUsuario, permiso };

        final StringBuilder hql = new StringBuilder();
        hql.append("select b from                                   ");
        hql.append(BibliotecaView.ENTITY_NAME).append(" b           ");
        hql.append("where b.id in (                                 ");
        hql.append("select distinct(bur.biblioteca.id) from         ");

        hql.append(UsuarioBibliotecaRol.ENTITY_NAME).append(" bur,  ");
        hql.append(Rol.ENTITY_NAME).append(" rol,                   ");
        hql.append(PermisoRol.ENTITY_NAME).append(" pr,             ");
        hql.append(Permiso.ENTITY_NAME).append(" permiso            ");

        hql.append(" where bur.usuario.id = ?                       ");
        hql.append(" and bur.rol.id = rol.id                        ");
        hql.append(" and rol.id = pr.rol.id                         ");
        hql.append(" and pr.permiso.id = permiso.id                 ");
        hql.append(" and permiso.codigo = ?)                        ");
        hql.append(" order by b.descripcion                       	");

        final List<BibliotecaView> bibliotecas = getHibernateTemplate().find(
                hql.toString(), parametros);

        return bibliotecas;
    }
	
	/**
     * @see org.librae.adminconfig.dao.IBibliotecaDAO#getBibliotecaByDescripcion(java.lang.String)
     */
    public BibliotecaView getBibliotecaByDescripcion(final String codigo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select b from ");
        sb.append(BibliotecaView.ENTITY_NAME).append(" b ");
        sb.append("where b.descripcion = ? ");
        final List<BibliotecaView> bibliotecas = getHibernateTemplate().find(
                sb.toString(), codigo);

        return ((bibliotecas.isEmpty()) ? null : bibliotecas.get(0));
    }
}
