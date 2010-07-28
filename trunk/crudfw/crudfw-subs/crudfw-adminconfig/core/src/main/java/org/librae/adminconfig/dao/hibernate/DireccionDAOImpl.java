package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import org.librae.adminconfig.dao.IDireccionDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Direccion;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Direccion
 * 
 * @author aropero
 */
public class DireccionDAOImpl extends GenericDAOImpl<Direccion, Long> implements
		IDireccionDAO {

	/**
	 * Constructor del DAO
	 */
	public DireccionDAOImpl() {
		super(Direccion.class);
	}

	public Direccion getDireccionByIdBiblioteca(Long idBiblioteca) {
		final StringBuilder sb = new StringBuilder();
		sb.append(" select b.direccion from             ");
		sb.append(Biblioteca.ENTITY_NAME).append(" b    ");
		sb.append(" where b.id = ?           			");

		final List<Direccion> direcciones = getHibernateTemplate().find(
				sb.toString(), idBiblioteca);
		return (direcciones.isEmpty()) ? null : (Direccion) direcciones.get(0);
	}
}
