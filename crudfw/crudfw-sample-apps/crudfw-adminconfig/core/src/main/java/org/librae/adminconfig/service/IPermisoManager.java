package org.librae.adminconfig.service;

import java.util.List;
import java.util.Map;
import org.librae.common.service.ISearchManager;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.TipoPermiso;

/**
 * Interfaz del Manager <br/> DAO: IPermisoDAO <br/> Entidad: Permiso
 * 
 * @author jcisneros
 */
public interface IPermisoManager extends ISearchManager<Permiso, Long> {

    /**
     * Busca una lista de Permisos que cumplan las mismas caracteristicas que
     * los criterios pasados como parámetro.
     * 
     * @param criterios
     *            de busqueda del catálogo.
     * @return listado de Permisos.
     */
    List<Permiso> buscar(Map<String, Object> criterios);

    /**
     * Obtiene el catálogo a partir del código
     * 
     * @param codigo
     *            , codigo.
     * @return Parámetro obtenido.
     */
    Permiso getPermisoByCodigo(String codigo);

    /**
     * Devuelve un tipo de permiso a partir de su id
     * 
     * @param idPermiso
     * @return
     */
    TipoPermiso getTipoPermiso(Long idPermiso);

}
