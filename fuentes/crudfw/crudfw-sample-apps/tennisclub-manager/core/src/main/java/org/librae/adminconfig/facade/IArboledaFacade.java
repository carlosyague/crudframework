package org.librae.adminconfig.facade;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.common.model.Arbol;

/**
 * Operaciones con los árboles de bibliotecas.
 * 
 * @author jcisneros
 */
public interface IArboledaFacade {

	/**
	 * Devuelve las bibliotecas y sus descendientes.
	 * 
	 * @param bibliotecasAsignadas
	 *            listado de las bibliotecas que se quiere buscar su
	 *            descendiente.
	 * @param bibliotecas
	 *            listado de todas las bibliotecas.
	 */
	List<BibliotecaView> descendientesBibliotecas(
			List<BibliotecaView> bibliotecasAsignadas,
			List<BibliotecaView> bibliotecas);

	/**
	 * 
	 * @param bibliotecasAsignadas
	 * @param bibliotecas
	 * @return
	 */
	List<Long> descendientesBibliotecasLong(
			List<BibliotecaView> bibliotecasAsignadas,
			List<BibliotecaView> bibliotecas);

	/**
	 * @param criteriosBiblioteca
	 * @return
	 */
	List<Biblioteca> buscarPorString(Map<String, Object> criteriosBiblioteca);

	/**
	 * 
	 * @param bibliotecas
	 * @return
	 */
	List<Arbol> getPadreHijos(List<BibliotecaView> bibliotecas);

	/**
	 * 
	 * @param criterios
	 */
	void getBibliotecasByCriterios(Map<String, Object> criterios);
}
