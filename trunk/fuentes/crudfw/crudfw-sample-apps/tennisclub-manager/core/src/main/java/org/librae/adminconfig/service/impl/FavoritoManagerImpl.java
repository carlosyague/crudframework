package org.librae.adminconfig.service.impl;

import org.librae.adminconfig.dao.IFavoritoDAO;
import org.librae.adminconfig.dao.IFavoritoUsuarioDAO;
import org.librae.adminconfig.model.FavoritoUsuario;
import org.librae.adminconfig.model.Usuario;
import org.librae.common.model.MenuItem;
import org.librae.common.service.IFavoritoGenericManager;

/**
 * Implementación del Manager de favoritos.
 * 
 * @author jcisneros
 */
public class FavoritoManagerImpl implements IFavoritoGenericManager {

    private IFavoritoDAO        favoritoDao;
    private IFavoritoUsuarioDAO favoritoUsuarioDao;

    public void insertarFavorito(Object usuario, String url,
            Long identificador, String texto) {
        final Usuario u = (Usuario) usuario;
        final String favorito = favoritoDao.getFavorito(url);
        if ((favorito != null)
                && (favoritoUsuarioDao.getFavoritoUsuario(u.getId(), favorito,
                        identificador) == null)) {
            final FavoritoUsuario favoritoUsuario = new FavoritoUsuario(u,
                    favorito);
            favoritoUsuario.setIdentificador(identificador);
            favoritoUsuario.setTexto(texto);
            favoritoUsuario.setOrden(favoritoUsuarioDao.getNumeroOrden(u
                    .getId()));
            favoritoUsuarioDao.save(favoritoUsuario);
        }
    }

    public MenuItem getMenuItem(String url, Long identificador, String texto) {
        MenuItem menuItem = null;
        final StringBuilder urlDetalle = new StringBuilder(url);
        final String favorito = favoritoDao.getFavorito(url);
        if (favorito != null) {
            menuItem = new MenuItem(favorito, urlDetalle.toString(), "_self",
                    null, false);
        }
        return menuItem;
    }

    // Getters && Setters

    /**
     * @return the favoritoDao
     */
    public IFavoritoDAO getFavoritoDao() {
        return favoritoDao;
    }

    /**
     * @param favoritoDao
     *            the favoritoDao to set
     */
    public void setFavoritoDao(IFavoritoDAO favoritoDao) {
        this.favoritoDao = favoritoDao;
    }

    /**
     * @return the favoritoUsuarioDao
     */
    public IFavoritoUsuarioDAO getFavoritoUsuarioDao() {
        return favoritoUsuarioDao;
    }

    /**
     * @param favoritoUsuarioDao
     *            the favoritoUsuarioDao to set
     */
    public void setFavoritoUsuarioDao(IFavoritoUsuarioDAO favoritoUsuarioDao) {
        this.favoritoUsuarioDao = favoritoUsuarioDao;
    }

}
