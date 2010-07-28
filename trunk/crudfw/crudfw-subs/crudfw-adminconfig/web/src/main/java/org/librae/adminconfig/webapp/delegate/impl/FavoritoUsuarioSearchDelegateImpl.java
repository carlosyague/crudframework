package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.librae.common.exception.LibraeException;
import org.librae.adminconfig.model.FavoritoUsuario;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.service.IFavoritoUsuarioManager;
import org.librae.adminconfig.webapp.delegate.IFavoritoUsuarioSearchDelegate;

import org.librae.common.exception.MensajesError;
import org.librae.common.exception.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementacion del interfaz IFavoritoUsuarioSearchDelegate.
 * 
 * @author aropero
 */
public class FavoritoUsuarioSearchDelegateImpl implements
        IFavoritoUsuarioSearchDelegate, Serializable {

    protected final Log             log              = LogFactory.getLog(this
                                                             .getClass());

    /**
     *Serial Version UID
     */
    private static final long       serialVersionUID = 1L;
    /**
     * Manager de FavoritoUsuario.
     */
    private IFavoritoUsuarioManager favoritoUsuarioManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<FavoritoUsuario> buscar(final Map<String, Object> criterios) {
        try {
            return favoritoUsuarioManager.buscar(criterios);
        } catch (final LibraeException e) {
            throw ExceptionUtil.crearLibraeException("ERROR_GENERICO",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        } catch (final Exception e) {
            throw ExceptionUtil.crearLibraeException("ERROR_GENERICO",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        }
    }

    public void bajarOrden(Long idBajarOrden, Usuario usuario) {
        favoritoUsuarioManager.bajarOrden(idBajarOrden, usuario);
    }

    public void subirOrden(Long idSubirOrden, Usuario usuario) {
        favoritoUsuarioManager.subirOrden(idSubirOrden, usuario);
    }

    public List<FavoritoUsuario> getFavoritoUsuario(List<String> listadoId) {
        return favoritoUsuarioManager.getFavoritoUsuario(listadoId);
    }

    public void eliminar(List<FavoritoUsuario> favoritos) {
        favoritoUsuarioManager.eliminar(favoritos);
    }

    // Getters & Setters

    public IFavoritoUsuarioManager getFavoritoUsuarioManager() {
        return favoritoUsuarioManager;
    }

    public void setFavoritoUsuarioManager(
            final IFavoritoUsuarioManager favoritoUsuarioManager) {
        this.favoritoUsuarioManager = favoritoUsuarioManager;
    }

}
