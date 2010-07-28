package org.librae.adminconfig.service;

import java.util.List;

import org.librae.adminconfig.model.FavoritoUsuario;
import org.librae.adminconfig.model.Usuario;
import org.librae.common.service.ISearchManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager para la gestion de favoritos.
 * 
 * @author jcisneros
 */
public interface IFavoritoUsuarioManager extends
        ISearchManager<FavoritoUsuario, Long> {

    /**
     * Subir el orden del favorito seleccionado.
     * 
     * @param idSubirOrden
     * @param usuario
     */
    @Transactional(readOnly = false)
    void subirOrden(Long idSubirOrden, Usuario usuario);

    /**
     * Bajar el orden del favorito seleccionado.
     * 
     * @param idBajarOrden
     * @param usuario
     */
    @Transactional(readOnly = false)
    void bajarOrden(Long idBajarOrden, Usuario usuario);

    /**
     * Elimina la lista de favoritos.
     * 
     * @param favoritos
     */
    @Transactional(readOnly = false)
    void eliminar(List<FavoritoUsuario> favoritos);

    /**
     * Obtiene los Favoritos con los identificadores de la lista.
     * 
     * @param listadoId
     * @return
     */
    List<FavoritoUsuario> getFavoritoUsuario(List<String> listadoId);

}
