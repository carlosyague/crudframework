package org.librae.common.webapp.delegate.impl;

import java.io.Serializable;

import org.librae.common.model.LibraeUser;
import org.librae.common.model.MenuItem;
import org.librae.common.service.IFavoritoGenericManager;
import org.librae.common.service.IMenuManager;
import org.librae.common.webapp.delegate.IMenuDelegate;

/**
 * Implementacion del delegate del menu.
 * 
 * @author jcisneros
 */
public class MenuDelegateImpl implements IMenuDelegate, Serializable {

    private static final long       serialVersionUID = -563087971545361205L;

    private IMenuManager            manager          = null;
    private IFavoritoGenericManager favoritoManager  = null;

    public String getMenu(final LibraeUser usuario) {
        return manager.getMenu(usuario);
    }

    public String getFavoritos(LibraeUser usuario) {
        return manager.getFavoritos(usuario);
    }

    public void insertarFavorito(Object usuario, String url) {
        manager.insertarFavorito(usuario, url);
    }

    public MenuItem getMenuItem(String url, Long identificador, String texto) {
        return favoritoManager.getMenuItem(url, identificador, texto);
    }

    // Getters && Setters

    public IMenuManager getManager() {
        return manager;
    }

    public void setManager(final IMenuManager manager) {
        this.manager = manager;
    }

    /**
     * @return the favoritoManager
     */
    public IFavoritoGenericManager getFavoritoManager() {
        return favoritoManager;
    }

    /**
     * @param favoritoManager
     *            the favoritoManager to set
     */
    public void setFavoritoManager(IFavoritoGenericManager favoritoManager) {
        this.favoritoManager = favoritoManager;
    }

}
