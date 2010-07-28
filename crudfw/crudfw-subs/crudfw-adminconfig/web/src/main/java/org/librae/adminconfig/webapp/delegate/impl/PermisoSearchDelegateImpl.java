package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoPermiso;
import org.librae.adminconfig.service.IPermisoManager;
import org.librae.adminconfig.service.IRolManager;
import org.librae.adminconfig.webapp.delegate.IPermisoSearchDelegate;

/**
 * Implementacion del interfaz IPermisoSearchDelegate.
 * 
 * @author aropero
 */
public class PermisoSearchDelegateImpl implements IPermisoSearchDelegate,
        Serializable {

    protected final Log       log              = LogFactory.getLog(this
                                                       .getClass());

    /**
     *Serial Version UID
     */
    private static final long serialVersionUID = 2890107080694880221L;

    /**
     * Manager de Permiso.
     */
    private IPermisoManager   permisoManager;

    private IRolManager       rolManager;

    private String            buscarPor        = "noBuscar";

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<Permiso> buscar(final Map<String, Object> criterios) {
        if (criterios != null) {
            criterios.put("buscarPor", buscarPor);
        }
        return permisoManager.buscar(criterios);
    }

    public List<Permiso> getPermiso(List<String> listadoId) {
        return null; // permisoManager.getPermiso(listadoId);
    }

    public void eliminar(List<Permiso> favoritos) {
        // permisoManager.eliminar(favoritos);
    }

    public List<Permiso> getPermisoByListadoId(List<String> listadoId) {
        final List<Permiso> permisos = new ArrayList<Permiso>();
        for (final String idPermiso : listadoId) {
            permisos.add(permisoManager.get(new Long(idPermiso)));
        }
        return permisos;
    }

    public void asignar(Long idRol, List<Permiso> permisos) {
        rolManager.asignarPermisos(idRol, permisos);
    }

    public void desasignar(Long idRol, List<Permiso> permisos) {
        rolManager.desasignarPermisos(idRol, permisos);
    }

    public Rol getRol(Long idRol) {
        return rolManager.get(idRol);
    }

    public TipoPermiso getTipoPermiso(Long idPermiso) {
        return permisoManager.getTipoPermiso(idPermiso);
    }

    // Getters & Setters

    public IPermisoManager getPermisoManager() {
        return permisoManager;
    }

    public void setPermisoManager(final IPermisoManager PermisoManager) {
        permisoManager = PermisoManager;
    }

    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor
     *            the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    /**
     * @return the rolManager
     */
    public IRolManager getRolManager() {
        return rolManager;
    }

    /**
     * @param rolManager
     *            the rolManager to set
     */
    public void setRolManager(IRolManager rolManager) {
        this.rolManager = rolManager;
    }

}
