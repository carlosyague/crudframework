package org.librae.adminconfig.dao;

/**
 * Interfaz del DAO para la entidad Favorito.
 * 
 * @author jcisneros
 */
public interface IFavoritoDAO {

	/**
	 * Devuelve un valor si es un favorito.
	 * 
	 * @param url
	 * @return
	 */
	String getFavorito(String url);

}
