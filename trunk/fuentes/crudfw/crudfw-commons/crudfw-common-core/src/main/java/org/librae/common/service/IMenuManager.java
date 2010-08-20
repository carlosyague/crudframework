package org.librae.common.service;

import org.librae.common.model.LibraeUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz para la utilizacion de arboles.
 * 
 * @author jcisneros
 */
public interface IMenuManager {

    /**
     * Obtiene los favoritos para el menu del usuario.
     * 
     * @param usuario
     * @return
     */
    String getFavoritos(LibraeUser usuario);

    /**
     * Insertar una entrada en el menu de favoritos para el usuario.
     * 
     * @param usuario
     * @param url
     */
    @Transactional(readOnly = false)
    void insertarFavorito(Object usuario, String url);
}
