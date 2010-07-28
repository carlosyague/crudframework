package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de la relación de usuario bibliotecas y roles.
 * 
 * @author jcisneros
 */
public interface IUsuarioBibliotecaRolSearchDelegate extends
        ISearchDelegate<UsuarioBibliotecaRol> {

    /**
     * Método que obtiene un listado de roles, mediante un listado de sus
     * correspondientes ids.
     * 
     * @param listadoId
     *            ,listado de ids
     * @return listado de UsuarioBibliotecaRol.
     */
    List<UsuarioBibliotecaRol> obtenerRolesById(List<String> listadoId);

    /**
     * Método que devuelve el tipo de identificación partir de un id
     * 
     * @param idTipoIdentificacion
     * @return
     */
    TipoIdentificacion getTipoIdentificacion(Long idTipoIdentificacion);

}
