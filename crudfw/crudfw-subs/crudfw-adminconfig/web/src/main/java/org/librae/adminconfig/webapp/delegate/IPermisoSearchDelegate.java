package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoPermiso;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de favoritos.
 * 
 * @author jcisneros
 */
public interface IPermisoSearchDelegate extends ISearchDelegate<Permiso> {

    /**
     * Obtiene una lista de favorito a partir de una lista de identificadores de
     * favoritos.
     * 
     * @param listadoId
     * @return
     */
    List<Permiso> getPermiso(List<String> listadoId);

    /**
     * Elimina la lista de favoritos.
     * 
     * @param favoritos
     */
    void eliminar(List<Permiso> favoritos);

    /**
     * Obtener el listado de los permisos.
     * 
     * @param listadoId
     * @return
     */
    List<Permiso> getPermisoByListadoId(List<String> listadoId);

    /**
     * @param obtenerPermisosSeleccionados
     */
    void desasignar(Long idRol, List<Permiso> permisos);

    /**
     * @param obtenerPermisosSeleccionados
     */
    void asignar(Long idRol, List<Permiso> permisos);

    /**
     * Obtiene un rol a partir de su id
     * 
     * @param idRol
     * @return
     */
    Rol getRol(Long idRol);

    /**
     * Devuelve un tipo de permiso a partir de su id
     * 
     * @param idPermiso
     * @return
     */
    TipoPermiso getTipoPermiso(Long idPermiso);

}
