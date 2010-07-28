package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de perfiles. Se utiliza para asignar los roles a los
 * usuarios.
 * 
 * @author jcisneros
 */
public interface IGestionPerfilesDelegate extends ISearchDelegate<Usuario> {

    /**
     * Asigna todos los permisos de la lista de no asignados a la lista de
     * asignados.
     * 
     * @param formulario
     */
    public void asignarTodos(AsignarPerfilesUsuarioForm formulario);

    /**
     * Asigna todos los permisos de la lista de no asignados a la lista de
     * asignados.
     * 
     * @param formulario
     */
    public void desasignar(AsignarPerfilesUsuarioForm formulario);

    /**
     * Asigna todos los permisos de la lista de no asignados a la lista de
     * asignados.
     * 
     * @param formulario
     */
    public void asignar(AsignarPerfilesUsuarioForm formulario);

    /**
     * Asigna todos los permisos de la lista de no asignados a la lista de
     * asignados.
     * 
     * @param formulario
     */
    public void desasignarTodos(AsignarPerfilesUsuarioForm formulario);

    /**
     * Buscar por el arbol.
     * 
     * @param form
     */
    public void buscarRoles(AsignarPerfilesUsuarioForm formulario);

    /**
     * Guarda la asignacion.
     * 
     * @param form
     */
    public void save(AsignarPerfilesUsuarioForm form);

    /**
     * Obtiene los usuarios de la lista de identificadores.
     * 
     * @param listadoIds
     *            lista de identificadores.
     * @return lista de usuarios.
     */
    public List<Usuario> getUsuariosById(List<String> ids);

    /**
     * Guarda la asignacion.
     * 
     * @param form
     */
    public void asignarUsuarios(AsignarPerfilesUsuarioForm form);

    /**
     * Guarda la asignacion.
     * 
     * @param form
     */
    public void desasignarUsuarios(AsignarPerfilesUsuarioForm form);

    /**
     * Obtiene la lista de roles asignados al usuario para esa biblioteca.
     * 
     * @param formulario
     * @return
     */
    public List<SelectItem> getRolesAsignados(
            AsignarPerfilesUsuarioForm formulario);

    /**
     * Obtiene la lista de roles asignados al usuario para esa biblioteca.
     * 
     * @param formulario
     * @return
     */
    public List<SelectItem> getRolesNoAsignados(
            AsignarPerfilesUsuarioForm formulario);

}
