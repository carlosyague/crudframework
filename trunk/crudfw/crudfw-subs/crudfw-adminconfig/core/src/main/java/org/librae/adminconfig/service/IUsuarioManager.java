package org.librae.adminconfig.service;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.service.ISearchManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager <br/>
 * DAO: IUsuarioDAO <br/>
 * Entidad: Usuario
 * 
 * @author asantamaria
 * @author jcisneros
 * @author aropero
 */
public interface IUsuarioManager extends ISearchManager<Usuario, Long> {

    /**
     * Busca una lista de usuarios que cumplan las mismas caracteristicas que
     * los criterios pasados como parámetro.
     * 
     * @param criterios
     *            de busqueda del usuario.
     * @return listado de usuarios.
     */
    List<Usuario> buscar(Map<String, Object> criterios);

    /**
     * Modifica la clave de usuario.
     * 
     * @param usuario
     * @param clave
     * @param claveNueva
     * @param claveRepetida
     */
    void cambiarClave(String usuario, String claveNueva, String claveRepetida);

    /**
     * Obtiene el usuario a partir del username
     * 
     * @param usuario
     *            , nombre del usuario.
     * @return Usuario obtenido.
     */
    Usuario getUsuarioByUsername(String usuario);

    /**
     * Guarda al usuario en BBDD.
     * 
     * @param usuario
     *            , a guardar
     * @param tratamiento
     *            , tratamiento a aplicarle al usuario
     * @param tipoIdentificacion
     *            , del usuario a guardar
     * @return Usuario guardado.
     */
    @Transactional(readOnly = false)
    Usuario salvarUsuario(Usuario usuario, Long tratamiento,
            Long tipoIdentificacion, List<UsuarioBibliotecaRol> rolesAsignados);

    /**
     * Guarda al usuario en BBDD.
     * 
     * @param usuario
     *            , a guardar
     * @param tratamiento
     *            , tratamiento a aplicarle al usuario
     * @param tipoIdentificacion
     *            , del usuario a guardar
     * @param orbeActivo
     * @return Usuario guardado.
     */
    @Transactional(readOnly = false)
    Usuario salvarUsuario(Usuario usuario, Long tratamiento,
            Long tipoIdentificacion, List<UsuarioBibliotecaRol> rolesAsignados,
            Boolean orbeActivo);

    /**
     * Obtiene los usuarios de la lista de identificadores.
     * 
     * @param listadoIds
     *            lista de identificadores.
     * @return lista de usuarios.
     */
    List<Usuario> getUsuariosById(List<String> listadoIds);

    /**
     * @param sucursalId
     * @return
     */
    List<Usuario> getUsuariosBySucursalId(String sucursalId);

    /**
     * @param sucursalId
     * @return
     */
    List<Usuario> getUsuariosBySucIdXaPrestSeg(String sucursalId);

    /**
     * @param codSucursal
     * @return
     */
    List<Usuario> getUsuariosByCodSucXaPrestSeg(String codSucursal);

    /**
     * @param idTipoIdentificacion
     * @return
     */
    TipoIdentificacion getTipoIdentificacion(Long idTipoIdentificacion);

    /**
     * @param idTipoIdentificacion
     * @return
     */
    TipoIdentificacion getTipoIdentificacionByCod(String codTipoIdentificacion);

    /**
     * Activa el usuario.
     * 
     * @param idUsuario
     */
    void activar(Long idUsuario);

    /**
     * Activa el usuario.
     * 
     * @param idUsuario
     */
    void desactivar(Long idUsuario);

    /**
     * Obtiene el usuario a partir del NIF
     * 
     * @param nif
     *            , NIF del usuario.
     * @return Usuario obtenido.
     */
    Usuario getUsuarioByNIF(String nif);

    /**
     * Crea y salva la tupla necesaria para que un usuario que entra por primera
     * vez en Librae, estando en Orbe, pueda existir. Se le da un rol especifico
     * vacio, y se le asigna a la biblioteca raiz de la red
     * 
     * @param usuario
     */
    void asignaRolInicialOrbe(Usuario usuario);

    /**
     * Obtiene una cadena con los nombres de los roles que se pasan por
     * 
     * @param roles
     * @return
     */
    String getNombresFromRoles(List<String> roles);

}
