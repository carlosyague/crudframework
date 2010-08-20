package org.librae.adminconfig.dao;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.common.dao.GenericDAO;

/**
 * Interfaz del DAO para la entidad Biblioteca
 * 
 * @author bagarcia
 */
public interface IBibliotecaViewDAO extends GenericDAO<BibliotecaView, Long> {

	/**
	 * Devuelve la lista de bibliotecas ordenada por la descripcion.
	 * 
	 * @return lista de BibliotecaView
	 */
	List<BibliotecaView> getBibliotecas();

	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	List<BibliotecaView> getBibliotecasByUsuario(final Long idUsuario);

	/**
	 * 
	 * @param criterios
	 * @return
	 */
	List<BibliotecaView> buscarPorString(final Map<String, Object> criterios);

	/**
	 * 
	 * @param idUsuario
	 * @param permiso
	 * @return
	 */
	List<BibliotecaView> getBibliotecasByUsuarioPermiso(Long idUsuario,
			String permiso);

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	BibliotecaView getBibliotecaByDescripcion(final String codigo);
	
}
