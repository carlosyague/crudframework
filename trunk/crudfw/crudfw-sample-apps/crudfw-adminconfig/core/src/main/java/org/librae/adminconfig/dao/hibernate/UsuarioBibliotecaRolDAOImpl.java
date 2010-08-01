package org.librae.adminconfig.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IUsuarioBibliotecaRolDAO;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.dao.hibernate.GenericSearchDao;

/**
 * Implementación del DAO para la entidad UsuarioBibliotecaRol
 *
 * @author asantamaria
 */
public class UsuarioBibliotecaRolDAOImpl extends
		GenericSearchDao<UsuarioBibliotecaRol, Long> implements
		IUsuarioBibliotecaRolDAO {

	/**
	 * Constructor del DAO
	 */
	public UsuarioBibliotecaRolDAOImpl() {
		super(UsuarioBibliotecaRol.class);
	}

	/**
	 * @see org.librae.adminconfig.dao.IUsuarioBibliotecaRolDAO#getUsuarioBibliotecaRolByIds(java.lang.Long,
	 *      java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioBibliotecaRol> getUsuarioBibliotecaRolByIds(
			final Long idUsuario, final Long idBiblioteca, final Long idRol) {
		List<UsuarioBibliotecaRol> usuarioBibliotecaRol = new ArrayList<UsuarioBibliotecaRol>();
		final DetachedCriteria criteriaUsuarioBibliotecaRol = DetachedCriteria
				.forClass(UsuarioBibliotecaRol.class);
		criteriaUsuarioBibliotecaRol
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		if (idUsuario != null) {
			criteriaUsuarioBibliotecaRol.add(Restrictions.eq("usuario.id",
					idUsuario));
		}
		if (idRol != null) {
			criteriaUsuarioBibliotecaRol.add(Restrictions.eq("rol.id", idRol));
		}
		if (idBiblioteca != null) {
			criteriaUsuarioBibliotecaRol.add(Restrictions.eq("biblioteca.id",
					idBiblioteca));
		}

		usuarioBibliotecaRol = getHibernateTemplate().findByCriteria(
				criteriaUsuarioBibliotecaRol);

		return usuarioBibliotecaRol;
	}

	/**
	 * @see org.librae.adminconfig.dao.IUsuarioBibliotecaRolDAO#saveBibliotecaPorDefecto(java.lang.Long,
	 *      java.lang.Long)
	 */
	public void saveBibliotecaPorDefecto(final Long idUsuario,
			final Long idBiblioteca, final Boolean defecto) {
		final StringBuilder hql = new StringBuilder();
		final Object[] parametros = { defecto, idUsuario, idBiblioteca };
		hql.append(" update ");
		hql.append(UsuarioBibliotecaRol.ENTITY_NAME).append(" bur ");
		hql.append(" set bur.bibliotecaPorDefecto=?  ");
		hql.append(" where bur.usuario.id = ?  ");
		hql.append(" and bur.biblioteca.id = ?  ");
		getHibernateTemplate().bulkUpdate(hql.toString(), parametros);
	}

	public void delete(final Long idUsuario, final Long idBiblioteca) {
		final StringBuilder hql = new StringBuilder();

		final Object[] parametros = { idBiblioteca, idUsuario };

		hql.append(" delete from ");
		hql.append(UsuarioBibliotecaRol.ENTITY_NAME).append(" ubr ");
		hql.append(" where ubr.biblioteca.id = ? ");
		hql.append(" and ubr.usuario.id = ? ");

		getHibernateTemplate().bulkUpdate(hql.toString(), parametros);
	}

	@Override
	protected DetachedCriteria obtenerCriteria(Map<String, Object> criterios) {
		 final DetachedCriteria criteria = DetachedCriteria
         .forClass(UsuarioBibliotecaRol.class);
		 if (criterios != null) {
			 if (criterios.get("idUsuario") != null) {
				 criteria.createAlias(
	                        UsuarioBibliotecaRol.PROPTY_NAME_USUARIO,
	                        UsuarioBibliotecaRol.PROPTY_NAME_USUARIO);
				 criteria.add(Restrictions.eq("usuario.id",
	                        criterios.get("idUsuario")));
	            }
		 }
		 return criteria;
	}
}
