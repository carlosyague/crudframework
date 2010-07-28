package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.apache.myfaces.custom.tree2.TreeNode;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.webapp.form.UsuarioForm;
import org.librae.common.webapp.delegate.IFavoritoDelegate;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de usuarios.
 * 
 * @author jcisneros
 * @author aropero
 */
public interface IUsuarioDelegate extends ISearchDelegate<Usuario>,
        IFavoritoDelegate {

    /**
     * Modifica la clave del usuario.
     * 
     * @param usuario
     * @param claveNueva
     * @param claveRepetida
     */
    void cambiarClave(UsuarioForm usuarioForm);

    /**
     * Método encargado de guardar un Usuario en base de datos
     * 
     * @param form
     *            , Formulario de edición de un Usuario.
     * @param rolesAsignados
     * @return Usuario creado.
     **/
    public Usuario guardarUsuario(final UsuarioForm form,
            List<UsuarioBibliotecaRol> rolesAsignados);

    /**
     * Método encargado de guardar un Usuario en base de datos
     * 
     * @param form
     *            , Formulario de edición de un Usuario.
     * @param rolesAsignados
     * @param orbeActivo
     * @return Usuario creado.
     **/
    public Usuario guardarUsuario(final UsuarioForm form,
            List<UsuarioBibliotecaRol> rolesAsignados, Boolean orbeActivo);

    /**
     * Método encargado de eliminar un Usuario de base de datos
     * 
     * @param idUsuario
     *            , identificador del Usuario a eliminar
     */
    public void eliminar(final Long idUsuario);

    /**
     * Método encargado de preparar la visualización del formulario de edición
     * de un Usuario
     * 
     * @param usuarioForm
     *            , formulario de edición de un Usuario
     */
    void prepararDatosVista(UsuarioForm usuarioForm);

    /**
     * Obtiene el arbol del usuario.
     * 
     * @param idUsuario
     * @return
     * @throws Exception
     */
    TreeNode getTreeData(Long idUsuario) throws Exception;

    /**
     * Obtiene el tipo de identificacion.
     * 
     * @param tipoIdentificacion
     *            identificador del tipo de identificacion.
     * @return
     */
    TipoIdentificacion getTipoIdentificacion(Long tipoIdentificacion);

    /**
     * Activa el usuario.
     * 
     * @param idUsuario
     */
    void activar(Long idUsuario);

    /**
     * Busca los roles del usuario.
     * 
     * @param idUsuario
     * @return
     */
    List<UsuarioBibliotecaRol> buscarRoles(Long idUsuario);

    /**
     * Obtiene la biblioteca por su identificador.
     * 
     * @param idBiblioteca
     * @return
     */
    Biblioteca getBiblioteca(Long idBiblioteca);

    /**
     * Obtiene una cadena con los nombres de los roles que se pasan por
     * 
     * @param roles
     * @return
     */
    String getNombresFromRoles(List<String> roles);

    Long getIdBibliotecaByDescripcion(String descripcion);

    Long getIdBibliotecaByCodigo(String descripcion);

}
