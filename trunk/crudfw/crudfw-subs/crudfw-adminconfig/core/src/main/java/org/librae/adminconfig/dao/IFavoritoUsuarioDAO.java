package org.librae.adminconfig.dao;

import java.util.List;

import org.librae.adminconfig.model.FavoritoUsuario;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad FavoritoUsuario.
 * 
 * @author jcisneros
 */
public interface IFavoritoUsuarioDAO extends
        IGenericSearchDAO<FavoritoUsuario, Long>,
        GenericDAO<FavoritoUsuario, Long> {

    /**
     * @param idUsuario
     *            identificador del usuario.
     * @param idFavorito
     *            identificador del favorito.
     * @param identificador
     * @return
     */
    FavoritoUsuario getFavoritoUsuario(Long idUsuario, String url,
            Long identificador);

    /**
     * @param idUsuario
     *            identificador del usuario.
     * @param idFavorito
     *            identificador del favorito.
     * @return
     */
    FavoritoUsuario getFavoritoUsuario(Long idUsuario, String url);

    /**
     * Obtiene los favoritos del usuario.
     * 
     * @param idUsuario
     *            identificador del usuario.
     * @return
     */
    List<FavoritoUsuario> getFavoritosByUsuario(Long idUsuario);

    /**
     * @param orden
     * @return
     */
    FavoritoUsuario getOrden(Long idUsuario, Long orden);

    /**
     * @param id
     * @return
     */
    Long getNumeroOrden(Long idUsuario);

}
