package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.FavoritoUsuario;
import org.librae.adminconfig.model.Usuario;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de favoritos.
 * 
 * @author jcisneros
 */
public interface IFavoritoUsuarioSearchDelegate extends
        ISearchDelegate<FavoritoUsuario> {

    /**
     * Sube el orden del favorito seleccionado.
     * 
     * @param idSubirOrden
     * @param usuario
     */
    void subirOrden(Long idSubirOrden, Usuario usuario);

    /**
     * Bajar el orden del favorito seleccionado.
     * 
     * @param idBajarOrden
     * @param usuario
     */
    void bajarOrden(Long idBajarOrden, Usuario usuario);

    /**
     * Obtiene una lista de favorito a partir de una lista de identificadores de
     * favoritos.
     * 
     * @param listadoId
     * @return
     */
    List<FavoritoUsuario> getFavoritoUsuario(List<String> listadoId);

    /**
     * Elimina la lista de favoritos.
     * 
     * @param favoritos
     */
    void eliminar(List<FavoritoUsuario> favoritos);

}
