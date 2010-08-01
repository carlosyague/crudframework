package org.librae.adminconfig.service;

import java.util.List;

import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoPermiso;
import org.librae.adminconfig.model.Usuario;
import org.librae.common.service.ISearchManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager para la gestion de roles.
 * 
 * @author asantamaria
 * @author jcisneros, aropero
 */
public interface IRolManager extends ISearchManager<Rol, Long> {

    /**
     * Método encargado de eliminar los roles indicados de base de datos
     * 
     * @param roles
     *            : lista de roles a eliminar
     */
    @Transactional(readOnly = false)
    void eliminar(List<Rol> roles);

    /**
     * Método encargado de eliminar un Rol de BBDD mediante su identificador.
     * 
     * @param idRol
     *            : ID del Rol a eliminar
     */
    @Transactional(readOnly = false)
    void eliminar(Long idRol);

    /**
     * Obtiene los permisos que no estan asociados al rol.
     * 
     * @param idRol
     *            identificador del rol.
     * @param codigoPermiso
     *            codigo del permiso que se quiere buscar (Opcional).
     * @param tipoPermiso
     *            tipo de permiso que se quiere buscar.
     * @return lista de permisos.
     */
    List<Permiso> obtenerPermisosSinAsignar(Long idRol, String codigoPermiso,
            Long idTipoPermiso);

    /**
     * Método encargado de duplicar los roles indicados de base de datos
     * 
     * @param roles
     *            : listado de roles a duplicar
     * @throws CloneNotSupportedException
     */
    @Transactional(readOnly = false)
    void duplicar(List<Rol> roles) throws CloneNotSupportedException;

    /**
     * Obtiene los permisos que no tiene asociado un rol.
     * 
     * @param idRol
     *            identificador del rol.
     * @param codigoPermiso
     *            cadena que tiene que coincidir con el codigo del permiso.
     * @return lista de permisos.
     */
    List<Permiso> obtenerPermisosDeRol(Rol rol, List<Permiso> permisos);

    /**
     * Asigna una lista de permisos a un rol.
     * 
     * @param idRol
     * @param permisosAsignados
     */
    @Transactional(readOnly = false)
    void asignarPermisos(Long idRol, List<Permiso> permisos);

    /**
     * Desasigna una lista de permisos a un rol.
     * 
     * @param idRol
     * @param permisosAsignados
     */
    @Transactional(readOnly = false)
    void desasignarPermisos(Long idRol, List<Permiso> permisos);

    /**
     * Obtiene la lista de tipos de permisos.
     * 
     * @return lista de tipos permisos.
     */
    List<TipoPermiso> getTiposPermisos();

    /**
     * Obtiene una lista de biblioteca rol concatenados en un String.
     * 
     * @param idUsuario
     *            identificador del usuario.
     * @param idBiblioteca
     *            identificador de la biblioteca.
     * @return String con la lista de roles - bibliotecas.
     */
    List<String> getListaBibliotecaRol(Long idUsuario, Long idBiblioteca);

    /**
     * Recupera la lista de permisos y declara la biblioteca como biblioteca por
     * defecto.
     * 
     * @param idUsuario
     * @param idBiblioteca
     * @param defecto
     * @return listado de Permisos
     */
    @Transactional
    List<Permiso> autorizar(Usuario usuario, Long idBiblioteca, boolean defecto);

    /**
     * Recupera la lista de permisos.
     * 
     * @return listado de Permisos.
     */
    List<Permiso> getPermisos();

    /**
     * Obtiene la lista de roles sin asignar del usuario para esa biblioteca.
     * 
     * @param idUsuario
     * @param idBiblioteca
     * @param codigoRol
     * @return
     */
    List<Rol> obtenerRolesSinAsignar(Long idUsuario, Long idBiblioteca,
            String codigoRol);

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
     * Asigna los roles a los usuarios para una biblioteca.
     * 
     * @param idBiblioteca
     * @param listaRoles
     * @param usuarios
     */
    @Transactional(readOnly = false)
    void asignarBibliotecas(Long idBiblioteca, List<Long> listaRoles,
            List<Usuario> usuarios);

    /**
     * Desasigna los roles a los usuarios para una biblioteca.
     * 
     * @param idBiblioteca
     * @param listaRoles
     * @param usuarios
     */
    @Transactional(readOnly = false)
    void desasignarBibliotecas(Long idBiblioteca, List<Long> listaRoles,
            List<Usuario> usuarios);

    /**
     * Asigna una lista de roles a un usuario para una biblioteca.
     * 
     * @param idBiblioteca
     * @param listaRoles
     * @param usuario
     */
    @Transactional(readOnly = false)
    void asignarBibliotecas(Long idBiblioteca, List<Long> listaRoles,
            Usuario usuario);

    /**
     * Obtiene la lista de roles de un usuario para una biblioteca
     * 
     * @param id
     * @param idBiblioteca
     * @return
     */
    List<Rol> getRoles(Long idUsuario, Long idBiblioteca);

    /**
     * Guarda el rol.
     * 
     * @param rol
     * @return
     */
    @Transactional(readOnly = false)
    Rol guardarRol(Rol rol);

    /**
     * Obtiene los permisos del usuario para la biblioteca.
     * 
     * @param permisos
     * @param idBiblioteca
     * @param idUsuario
     */
    public List<Permiso> getPermisos(final Long idBiblioteca,
            final Long idUsuario);

    public Rol getRolByCodigo(String codigo);

}
