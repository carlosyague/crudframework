package org.librae.adminconfig.dao;

import org.librae.common.dao.GenericDAO;

import org.librae.adminconfig.model.Direccion;

/**
 * Interfaz del DAO para la entidad Direccion
 * 
 * @author aropero
 */
public interface IDireccionDAO extends GenericDAO<Direccion, Long> {

	/**
	 * Se obtiene la direccion de la biblioteca.
	 * 
	 * @param idBiblioteca
	 * @return
	 */
	public Direccion getDireccionByIdBiblioteca(Long idBiblioteca);

}