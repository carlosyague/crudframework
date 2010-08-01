package org.librae.adminconfig.dao;

import java.util.List;

import org.librae.adminconfig.model.Permiso;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad Permiso
 * 
 * @author asantamaria
 */
public interface IPermisoDAO extends IGenericSearchDAO<Permiso, Long>,
        GenericDAO<Permiso, Long> {

    /**
     * Obtiene los permisos que no estan asociados al rol.
     * 
     * @param idRol
     *            identificador del rol.
     * @param codigoPermiso
     *            codigo del permiso que se quiere buscar (Opcional).
     * @return lista de permisos.
     */
    public List<Permiso> obtenerPermisosSinAsignar(Long idRol,
            String codigoPermiso, Long idTipoPermiso);

}