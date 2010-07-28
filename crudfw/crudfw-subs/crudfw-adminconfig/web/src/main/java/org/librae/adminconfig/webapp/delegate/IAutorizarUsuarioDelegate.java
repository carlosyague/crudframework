package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.apache.myfaces.custom.tree2.TreeNode;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.Usuario;

/**
 * Interfaz del delegado para la getion de bibliotecas.
 * 
 * @author jcisneros
 */
public interface IAutorizarUsuarioDelegate {

    /**
     * Monta el arbol a partir de las bibliotecas que tiene asignada el usuario.
     * 
     * @param idUsuario
     * @return
     */
    TreeNode getTreeData(Long idUsuario) throws Exception;

    /**
     * Obtiene una lista de roles del usuario y la biblioteca.
     * 
     * @param idUsuario
     * @param idBiblioteca
     * @return
     */
    List<String> getRoles(Long idUsuario, Long idBiblioteca);

    /**
     * Recupera la lista de permisos y declara la biblioteca como biblioteca por
     * defecto.
     * 
     * @param usuario
     * @param idBiblioteca
     * @param defecto
     * @return
     */
    List<Permiso> save(Usuario usuario, Long idBiblioteca, boolean defecto);

    /**
     * Salva la biblioteca seleccionada por el usuario en la tupla usuario
     * biblioteca rol, en la primera ocasión que entra el usuario desde Orbe
     * 
     * @param usuario
     * @param idBiblioteca
     * @return
     */
    void seleccionaBibOrbe(Usuario usuario, Long idBiblioteca);

    public boolean usuarioRecienLLegadoDeOrbe(Usuario usuario);

    public String getUrlAutorizarOrbe();

    /**
     * Obtiene el objeto Usuario a partir del username de la cookie que inserta
     * CAS tras identificar al usuario
     * 
     * @return
     */
    Usuario getUsuarioByCASUsername();

    /**
     * Obtiene todos los permisos.
     * 
     * @return
     */
    List<Permiso> getPermisos();

    /**
     * Obtiene las bibliotecas del usuario.
     * 
     * @param usuario
     * @return
     */
    List<BibliotecaView> getBibliotecasByUsuario(Usuario usuario);

}
