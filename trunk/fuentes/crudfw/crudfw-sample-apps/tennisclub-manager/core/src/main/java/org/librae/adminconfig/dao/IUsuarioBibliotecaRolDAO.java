package org.librae.adminconfig.dao;

import java.util.List;

import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad UsuarioBibliotecaRol
 *
 * @author asantamaria
 */
public interface IUsuarioBibliotecaRolDAO extends
		IGenericSearchDAO<UsuarioBibliotecaRol, Long>,
		GenericDAO<UsuarioBibliotecaRol, Long> {

	/**
	 * Obtiene el objecto UsuarioBibliotecaRol a partir de una de los tres
	 * objetos con los que se relaciona.
	 *
	 * @param idUsuario
	 * @param idBiblioteca
	 * @param idRol
	 * @return
	 */
	List<UsuarioBibliotecaRol> getUsuarioBibliotecaRolByIds(Long idUsuario,
			Long idBiblioteca, Long idRol);

	/**
	 * Guarda la biblioteca por defecto del usuario.
	 *
	 * @param idUsuario
	 * @param idBiblioteca
	 */
	void saveBibliotecaPorDefecto(Long idUsuario, Long idBiblioteca,
			Boolean defecto);

	/**
	 * Elimina las asoaciones de usuarios y bibliotecas.
	 *
	 * @param idUsuario
	 * @param idBiblioteca
	 */
	void delete(final Long idUsuario, final Long idBiblioteca);
}