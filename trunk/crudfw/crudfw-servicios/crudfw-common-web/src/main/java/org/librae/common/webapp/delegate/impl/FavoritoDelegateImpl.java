package org.librae.common.webapp.delegate.impl;

import org.librae.common.model.MenuItem;
import org.librae.common.service.IFavoritoGenericManager;
import org.librae.common.webapp.delegate.IFavoritoDelegate;

/**
 * Implementacion del delegate de los favoritos.
 * 
 * @author jcisneros
 */
public class FavoritoDelegateImpl implements IFavoritoDelegate {

    private IFavoritoGenericManager favoritoManager = null;

    public void insertarFavorito(Object usuario, String url,
            Long identificador, String texto) {
        favoritoManager.insertarFavorito(usuario, url, identificador, texto);
    }

    public MenuItem getMenuItem(String url, Long identificador, String texto) {
        return favoritoManager.getMenuItem(url, identificador, texto);
    }

    // Getters && Setters

    public IFavoritoGenericManager getFavoritoManager() {
        return favoritoManager;
    }

    public void setFavoritoManager(final IFavoritoGenericManager favoritoManager) {
        this.favoritoManager = favoritoManager;
    }

}
