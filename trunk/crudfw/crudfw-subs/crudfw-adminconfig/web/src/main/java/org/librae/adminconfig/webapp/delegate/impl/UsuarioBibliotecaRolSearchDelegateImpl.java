package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.service.IUsuarioBibliotecaRolManager;
import org.librae.adminconfig.service.IUsuarioManager;
import org.librae.adminconfig.webapp.delegate.IUsuarioBibliotecaRolSearchDelegate;

/**
 * Implementacion del interfaz IRolSearchDelegate.
 * 
 * @author aropero
 */
public class UsuarioBibliotecaRolSearchDelegateImpl implements
        IUsuarioBibliotecaRolSearchDelegate, Serializable {

    protected final Log                  log              = LogFactory
                                                                  .getLog(this
                                                                          .getClass());

    /**
     *Serial Version UID
     */
    private static final long            serialVersionUID = 1L;
    /**
     * Manager de Rol.
     */
    private IUsuarioBibliotecaRolManager usuarioBibliotecaRolManager;

    private IUsuarioManager              usuarioManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<UsuarioBibliotecaRol> buscar(final Map<String, Object> criterios) {
        return usuarioBibliotecaRolManager.buscar(criterios);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IRolSearchDelegate#obtenerRolesById(java.util.List)
     */
    public List<UsuarioBibliotecaRol> obtenerRolesById(
            final List<String> listadoId) {
        // return usuarioBibliotecaRolManager.obtenerRolesById(listadoId);
        return null;
    }

    public TipoIdentificacion getTipoIdentificacion(Long idTipoIdentificacion) {
        return usuarioManager.getTipoIdentificacion(idTipoIdentificacion);
    }

    // Getters & setters ==================

    public IUsuarioManager getUsuarioManager() {
        return usuarioManager;
    }

    public void setUsuarioManager(IUsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }

    public IUsuarioBibliotecaRolManager getUsuarioBibliotecaRolManager() {
        return usuarioBibliotecaRolManager;
    }

    public void setUsuarioBibliotecaRolManager(
            IUsuarioBibliotecaRolManager usuarioBibliotecaRolManager) {
        usuarioBibliotecaRolManager = usuarioBibliotecaRolManager;
    }

}
