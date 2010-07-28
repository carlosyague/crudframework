package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.Usuario;
import org.librae.common.exception.LibraeException;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de roles.
 * 
 * @author aropero
 */
public interface IRolSearchDelegate extends ISearchDelegate<Rol> {

    /**
     * Método que obtiene un listado de roles, mediante un listado de sus
     * correspondientes ids.
     * 
     * @param listadoId
     *            ,listado de ids
     * @return listado de roles.
     */
    List<Rol> obtenerRolesById(List<String> listadoId);

    /**
     * Método que obtiene todos los roles existentes en BBDD.
     * 
     * @return listado de todos los roles existentes.
     */
    List<Rol> getAll();

    /**
     * Método encargado de duplicar los roles indicados de base de datos
     * 
     * @param roles
     *            : listado de roles a duplicar
     */
    void duplicar(List<Rol> listaRoles);

    /**
     * Método encargado de eliminar los roles indicados de base de datos
     * 
     * @param roles
     *            : lista de roles a eliminar
     */
    void eliminar(List<Rol> listaRoles) throws LibraeException;

    Biblioteca getBiblioteca(Long idBiblioteca);

    Usuario getUsuario(Long idUsuario);

}
