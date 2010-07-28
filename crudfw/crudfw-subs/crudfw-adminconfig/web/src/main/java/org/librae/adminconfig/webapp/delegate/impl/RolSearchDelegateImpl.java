package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.adminconfig.service.IRolManager;
import org.librae.adminconfig.service.IUsuarioManager;
import org.librae.adminconfig.webapp.delegate.IRolSearchDelegate;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.springframework.dao.DataAccessException;

/**
 * Implementacion del interfaz IRolSearchDelegate.
 * 
 * @author aropero
 */
public class RolSearchDelegateImpl implements IRolSearchDelegate, Serializable {

    protected final Log        log              = LogFactory.getLog(this
                                                        .getClass());

    /**
     * Serial Version UID
     */
    private static final long  serialVersionUID = 1L;
    /**
     * Manager de Rol.
     */
    private IRolManager        rolManager;
    private IBibliotecaManager bibliotecaManager;
    private IUsuarioManager    usuarioManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<Rol> buscar(final Map<String, Object> criterios) {
        return rolManager.buscar(criterios);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IRolSearchDelegate#obtenerRolesById(java.util.List)
     */
    public List<Rol> obtenerRolesById(final List<String> listadoId) {
        try {
            return rolManager.obtenerRolesById(listadoId);
        } catch (final LibraeException e) {
            throw ExceptionUtil.crearLibraeException("ERROR_GENERICO",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        } catch (final Exception e) {
            throw ExceptionUtil.crearLibraeException("ERROR_GENERICO",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        }
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IRolSearchDelegate#getAll()
     */
    public List<Rol> getAll() {
        try {
            return rolManager.getAll();
        } catch (final LibraeException e) {
            throw ExceptionUtil.crearLibraeException("ERROR_GENERICO",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        } catch (final Exception e) {
            throw ExceptionUtil.crearLibraeException("ERROR_GENERICO",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        }
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IRolSearchDelegate#duplicar(java.util.List)
     */
    public void duplicar(final List<Rol> listaRoles) {
        try {
            rolManager.duplicar(listaRoles);
        } catch (final LibraeException e) {
            throw ExceptionUtil.crearLibraeException("ERROR_DUPLICAR_ROL",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        } catch (final Exception e) {
            throw ExceptionUtil.crearLibraeException("ERROR_DUPLICAR_ROL",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        }
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IRolSearchDelegate#eliminar(java.util.List)
     */
    public void eliminar(final List<Rol> listaRoles) throws LibraeException {
        try {
            rolManager.eliminar(listaRoles);
        } catch (final LibraeException le) {
            throw le;
        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException(dae,
                    MensajesError.PROPERTI_ADMINCONFIG);
        } catch (final Exception e) {
            log.error("[RolSearchDelegate.eliminar] ERROR eliminando roles "
                    + e);
            throw ExceptionUtil.crearLibraeException("ERROR_DELETE_BD", e);
        }
    }

    public Biblioteca getBiblioteca(Long idBiblioteca) {
        return bibliotecaManager.get(idBiblioteca);
    }

    public Usuario getUsuario(Long idUsuario) {
        return usuarioManager.get(idUsuario);
    }

    // Getters & setters

    public IRolManager getRolManager() {
        return rolManager;
    }

    public void setRolManager(final IRolManager rolManager) {
        this.rolManager = rolManager;
    }

    /**
     * @return the bibliotecaManager
     */
    public IBibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    /**
     * @param bibliotecaManager
     *            the bibliotecaManager to set
     */
    public void setBibliotecaManager(IBibliotecaManager bibliotecaManager) {
        this.bibliotecaManager = bibliotecaManager;
    }

}
