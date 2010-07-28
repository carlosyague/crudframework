package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.Biblioteca;

import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.form.AsignarPermisoRolForm;
import org.librae.adminconfig.webapp.form.RolForm;
import org.librae.common.webapp.delegate.ISearchDelegate;
import org.librae.common.webapp.delegate.IFavoritoDelegate;

/**
 * Interfaz para la gestion de roles.
 * 
 * @author jcisneros
 */
public interface IGestionRolesDelegate extends ISearchDelegate<Rol>,
        IFavoritoDelegate {

    /**
     * Guarda el formulario en la sesion.
     * 
     * @param form
     */
    public void snapshot(AsignarPermisoRolForm formulario);

    /**
     * Asigna todos los permisos de la lista de no asignados a la lista de
     * asignados.
     * 
     * @param formulario
     */
    public void asignarTodos(AsignarPermisoRolForm formulario);

    /**
     * Asigna todos los permisos de la lista de no asignados a la lista de
     * asignados.
     * 
     * @param formulario
     */
    public void desasignar(AsignarPermisoRolForm formulario);

    /**
     * Asigna todos los permisos de la lista de no asignados a la lista de
     * asignados.
     * 
     * @param formulario
     */
    public void asignar(AsignarPermisoRolForm formulario);

    /**
     * Asigna todos los permisos de la lista de no asignados a la lista de
     * asignados.
     * 
     * @param formulario
     */
    public void desasignarTodos(AsignarPermisoRolForm formulario);

    /**
     * @param form
     */
    public void buscar(AsignarPermisoRolForm formulario);

    /**
     * @param form
     */
    public void save(AsignarPermisoRolForm form);

    /**
     * Obtiene el usuario por su identificador.
     * 
     * @param idUsuario
     *            identificador del usuario.
     * @return usuario.
     */
    public Usuario getUsuario(Long idUsuario);

    /**
     * Método encargado de guardar un Rol en base de datos
     * 
     * @param rolForm
     *            , formulario con los datos del Rol a guardar en base de datos
     * @return Rol creado.
     **/
    public Rol guardarRol(RolForm rolForm);

    /**
     * Método encargado de preparar la visualización del formulario de edición
     * de un Rol
     * 
     * @param form
     *            , RolForm formulario de edición de Rol
     */
    public void prepararDatosVista(RolForm rolForm);

    /**
     * Método encargado de eliminar un Rol de base de datos
     * 
     * @param idRol
     *            , identificador del Rol a eliminar
     */
    public void eliminar(Long idRol);

    public List<Rol> obtenerRolesById(List<String> listadoId);

    public Biblioteca getBiblioteca(Long idBiblioteca);

}
