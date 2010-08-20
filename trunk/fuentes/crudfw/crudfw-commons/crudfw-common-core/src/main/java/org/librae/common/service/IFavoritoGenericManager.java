package org.librae.common.service;

import org.librae.common.model.MenuItem;

/**
 * Interfaz para la utilizacion de favoritos.
 * 
 * @author jcisneros
 */
public interface IFavoritoGenericManager {

    /**
     * Insertar una entrada en el menu de favoritos para el usuario.
     * 
     * @param usuario
     * @param url
     */
    void insertarFavorito(Object usuario, String url, Long identificador,
            String texto);

    MenuItem getMenuItem(String url, Long identificador, String texto);

}
