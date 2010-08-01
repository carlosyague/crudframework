package org.librae.adminconfig.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.librae.adminconfig.dao.IBibliotecaDAO;
import org.librae.adminconfig.dao.IPermisoDAO;
import org.librae.adminconfig.dao.IPermisoRolDAO;
import org.librae.adminconfig.dao.IRolDAO;
import org.librae.adminconfig.dao.ITipoPermisoDAO;
import org.librae.adminconfig.dao.IUsuarioBibliotecaRolDAO;
import org.librae.adminconfig.dao.IUsuarioDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.PermisoRol;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoPermiso;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.service.IRolManager;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.GenericManagerImpl;
import org.librae.common.util.DateUtil;

/**
 * Implementación del Manager <br/>
 * DAO: IRolDAO <br/>
 * Entidad: Rol
 * 
 * @author asantamaria
 * @author jcisneros, aropero
 */
public class RolManagerImpl extends GenericManagerImpl<Rol, Long> implements
        IRolManager, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    IRolDAO                   rolDao;
    IPermisoDAO               permisoDao;
    IPermisoRolDAO            permisoRolDao;
    ITipoPermisoDAO           tipoPermisoDao;
    IUsuarioBibliotecaRolDAO  usuarioBibliotecaRolDao;
    IBibliotecaDAO            bibliotecaDao;
    IUsuarioDAO               usuarioDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public RolManagerImpl(final IRolDAO rolDao) {
        super(rolDao);
        this.rolDao = rolDao;
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#eliminar(java.util.List)
     */
    public void eliminar(final List<Rol> roles) {
        log.debug("Vamos a eliminar: " + roles.size() + " roles");
        if (roles != null) {
            final Iterator<Rol> it = roles.iterator();
            while (it.hasNext()) {
                final Rol rol = it.next();
                rol.getPermisosRoles().clear();
                rolDao.remove(rol.getId());
            }
        }
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#eliminar(java.lang.Long)
     */
    public void eliminar(final Long idRol) {
        log.debug("Vamos a eliminar el Rol con id: " + idRol);

        final Rol rol = rolDao.get(idRol);
        rol.getPermisosRoles().clear();
        rolDao.remove(rol.getId());
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#duplicar(java.util.List)
     */
    public void duplicar(final List<Rol> roles)
            throws CloneNotSupportedException {
        Permiso permiso = null;
        final List<PermisoRol> permisosRoles = new ArrayList<PermisoRol>();
        Rol rolDuplicado = null;
        if (roles != null) {
            final Iterator<Rol> it = roles.iterator();
            while (it.hasNext()) {
                final Rol rol = it.next();
                int i = 1;
                final String codigo = rol.getCodigo() + "_duplicado";

                /* Buscamos si existe un Rol duplicado del nuestro */
                Rol rolDup = rolDao.getRolByCodigo(codigo + i);
                if (rolDup != null) {
                    // Existe un rol duplicado, buscamos mas duplicados
                    boolean fin = false;
                    while (!fin) {
                        i++;
                        rolDup = rolDao.getRolByCodigo(codigo + i);
                        if (rolDup == null) {
                            fin = true;
                        }
                    }
                }
                /* Creamos un rol duplicado */
                rolDuplicado = (Rol) rol.clone();
                /* Desasociamos permisos del original para darselos al clonado. */
                for (final PermisoRol permisoRol : rol.getPermisosRoles()) {
                    permiso = permisoRol.getPermiso();
                    final PermisoRol permisoRolClonado = new PermisoRol(
                            permiso, rol);
                    permisosRoles.add(permisoRolClonado);
                }
                rolDuplicado.setPermisosRoles(permisosRoles);

                rolDuplicado.setCodigo(codigo + i);

                rolDao.save(rolDuplicado);
            }
        }
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#obtenerPermisosSinAsignar(java.lang.Long,
     *      java.lang.String)
     */
    public List<Permiso> obtenerPermisosSinAsignar(final Long idRol,
            final String codigoPermiso, final Long idTipoPermiso) {
        return permisoDao.obtenerPermisosSinAsignar(idRol, codigoPermiso,
                idTipoPermiso);
    }

    /**
     * @param biblioteca
     * @param permisos2
     * @see org.librae.adminconfig.service.IRolManager#obtenerPermisosDeRol(org.librae.adminconfig.model.Rol)
     */
    public List<Permiso> obtenerPermisosDeRol(final Rol rol,
            final List<Permiso> permisos, Biblioteca bibliotecaSeleccionada) {
        PermisoRol permisoRol = null;
        final List<PermisoRol> permisosRoles = rol.getPermisosRoles();
        final ListIterator<PermisoRol> permisoRolIterator = permisosRoles
                .listIterator();
        while (permisoRolIterator.hasNext()) {
            permisoRol = permisoRolIterator.next();
            if (!permisos.contains(permisoRol.getPermiso())) {
                if (((bibliotecaSeleccionada.getTipoBiblioteca().getCodigo()
                        .equals(Constants.TIPO_BIBLIOTECA_SUCURSAL)) && (permisoRol
                        .getPermiso().getSoloSucursales()))
                        || (!permisoRol.getPermiso().getSoloSucursales())) {
                    permisos.add(permisoRol.getPermiso());
                }
            }
        }
        return permisos;
    }

    /**
     * @param permisos
     * @see org.librae.adminconfig.service.IRolManager#obtenerPermisosDeRol(org.librae.adminconfig.model.Rol)
     */
    public List<Permiso> obtenerPermisosDeRol(final Rol rol,
            final List<Permiso> permisos) {
        PermisoRol permisoRol = null;
        final List<PermisoRol> permisosRoles = rol.getPermisosRoles();
        final ListIterator<PermisoRol> permisoRolIterator = permisosRoles
                .listIterator();
        while (permisoRolIterator.hasNext()) {
            permisoRol = permisoRolIterator.next();
            if (!permisos.contains(permisoRol.getPermiso())) {
                permisos.add(permisoRol.getPermiso());
            }
        }
        return permisos;
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#asignarPermisos(java.lang.Long,
     *      java.util.List)
     */
    public void asignarPermisos(final Long idRol, final List<Permiso> permisos) {
        final Rol rol = rolDao.get(idRol);

        for (final Permiso permiso : permisos) {
            final PermisoRol permisoRol = new PermisoRol(permiso, rol);
            permisoRolDao.save(permisoRol);
        }
        rolDao.flush();
    }

    public void desasignarPermisos(final Long idRol,
            final List<Permiso> permisos) {
        for (final Permiso permiso : permisos) {
            final PermisoRol permisoRol = permisoRolDao.getPermisoRolByIds(
                    idRol, permiso.getId());
            permisoRolDao.remove(permisoRol.getId());
        }
        rolDao.flush();
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#getTiposPermisos()
     */
    public List<TipoPermiso> getTiposPermisos() {
        return tipoPermisoDao.getAll();
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#getRolByUsuarioBiblioteca(java.lang.Long,
     *      java.lang.Long, java.lang.Long)
     */
    public List<String> getListaBibliotecaRol(final Long idUsuario,
            final Long idBiblioteca) {
        final List<String> roles = new ArrayList<String>();
        listarRoles(roles, idBiblioteca, idUsuario);
        return roles;
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#autorizar(java.lang.Long,
     *      java.lang.Long, boolean)
     */
    public List<Permiso> autorizar(final Usuario usuario,
            final Long idBiblioteca, final boolean defecto) {
        final List<Permiso> permisos = new ArrayList<Permiso>();
        final Biblioteca biblioteca = bibliotecaDao.get(idBiblioteca);
        listarPermisos(permisos, biblioteca, usuario.getId(), biblioteca);
        if (defecto) {
            usuario.setBibliotecaPorDefecto(biblioteca);
        } else if ((usuario.getBibliotecaPorDefecto() != null)
                && (idBiblioteca.equals(usuario.getBibliotecaPorDefecto()
                        .getId())) && (!defecto)) {
            usuario.setBibliotecaPorDefecto(null);
        }
        usuario.setBibliotecaActual(biblioteca);
        biblioteca.setFechaHoraUltimaConexion(DateUtil.getCurrentDate());
        bibliotecaDao.save(biblioteca);
        usuarioDao.save(usuario);
        bibliotecaDao.flush();
        return permisos;
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#getPermisos()
     */
    public List<Permiso> getPermisos() {
        return permisoDao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public List<Rol> buscar(final Map<String, Object> criterios) {
        if (criterios.get("usuario") != null) {
            final Usuario usuario = (Usuario) criterios.get("usuario");
            final Long nivel = getNivelRolByUsuario(usuario);
            criterios.put("nivelUsuario", nivel);
        }
        return rolDao.buscar(criterios);
    }

    /**
     * Añade los roles del usuario para esa biblioteca.
     * 
     * @param permisos
     * @param idBiblioteca
     * @param idUsuario
     */
    public Long getNivelRolByUsuario(final Usuario usuario) {
        Long nivel = new Long(1);
        List<Rol> roles = new ArrayList<Rol>();
        roles = getRolesByUsuarioBibliboteca(usuario.getId(), usuario
                .getBibliotecaActual());
        for (final Rol rol : roles) {
            if (rol.getNivel() > nivel) {
                nivel = rol.getNivel();
            }
        }
        return nivel;
    }

    /**
     * Añade los roles del usuario para esa biblioteca.
     * 
     * @param permisos
     * @param idBiblioteca
     * @param idUsuario
     */
    public List<Rol> getRolesByUsuarioBibliboteca(final Long idUsuario,
            Biblioteca biblioteca) {
        final List<Rol> roles = new ArrayList<Rol>();
        listarRoles(roles, biblioteca, idUsuario);
        return roles;
    }

    /**
     * Listado de roles de un usuario en una biblioteca.
     * 
     * @param roles
     * @param biblioteca
     * @param idUsuario
     */
    public void listarRoles(final List<Rol> roles, final Biblioteca biblioteca,
            final Long idUsuario) {
        final List<UsuarioBibliotecaRol> listaUsuariosBibliotecasRoles = usuarioBibliotecaRolDao
                .getUsuarioBibliotecaRolByIds(idUsuario, biblioteca.getId(),
                        null);
        for (final UsuarioBibliotecaRol item : listaUsuariosBibliotecasRoles) {
            roles.add(item.getRol());
        }
        if (biblioteca.getPadre() != null) {
            listarRoles(roles, biblioteca.getPadre(), idUsuario);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Rol> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#obtenerRolesById(java.util.List)
     */
    public List<Rol> obtenerRolesById(final List<String> listadoId) {
        return rolDao.obtenerRolesById(listadoId);
    }

    /**
     * Obtiene los roles que no han sido asignado a un usuario para una
     * biblioteca.
     * 
     * @param idUsuario
     * @param idBiblioteca
     * @return listado de roles
     */
    public List<Rol> obtenerRolesSinAsignar(final Long idUsuario,
            final Long idBiblioteca, final String codigoRol) {
        return rolDao
                .obtenerRolesSinAsignar(idUsuario, idBiblioteca, codigoRol);
    }

    /**
     * Añade los permisos de los roles del usuario para esa biblioteca.
     * 
     * @param permisos
     * @param idBiblioteca
     * @param idUsuario
     */
    public List<Permiso> getPermisos(final Long idBiblioteca,
            final Long idUsuario) {
        final List<Permiso> permisos = new ArrayList<Permiso>();
        final Biblioteca biblioteca = bibliotecaDao.get(idBiblioteca);
        listarPermisos(permisos, biblioteca, idUsuario, biblioteca);
        return permisos;
    }

    /**
     * Añade los permisos de los roles del usuario para esa biblioteca.
     * 
     * @param permisos
     * @param idBiblioteca
     * @param idUsuario
     */
    public void listarPermisos(final List<Permiso> permisos,
            final Biblioteca biblioteca, final Long idUsuario,
            final Biblioteca bibliotecaSeleccionada) {
        final List<UsuarioBibliotecaRol> listaUsuariosBibliotecasRoles = usuarioBibliotecaRolDao
                .getUsuarioBibliotecaRolByIds(idUsuario, biblioteca.getId(),
                        null);
        for (final UsuarioBibliotecaRol item : listaUsuariosBibliotecasRoles) {
            obtenerPermisosDeRol(item.getRol(), permisos,
                    bibliotecaSeleccionada);
        }
        if (biblioteca.getPadre() != null) {
            listarPermisos(permisos, biblioteca.getPadre(), idUsuario,
                    bibliotecaSeleccionada);
        }
    }

    /**
     * Saca la lista de roles para un usuario y biblioteca.
     * 
     * @param roles
     * @param idBiblioteca
     * @param idUsuario
     */
    private void listarRoles(final List<String> roles, final Long idBiblioteca,
            final Long idUsuario) {
        final List<UsuarioBibliotecaRol> listaUsuariosBibliotecasRoles = usuarioBibliotecaRolDao
                .getUsuarioBibliotecaRolByIds(idUsuario, idBiblioteca, null);
        for (final UsuarioBibliotecaRol item : listaUsuariosBibliotecasRoles) {
            final StringBuilder sb = new StringBuilder();
            sb.append(item.getRol().getCodigo());
            sb.append(" - ");
            sb.append(item.getBiblioteca().getCodigo());
            sb.append("\n");
            roles.add(sb.toString());
        }
        final Biblioteca biblioteca = bibliotecaDao.get(idBiblioteca);
        if (biblioteca.getPadre() != null) {
            listarRoles(roles, biblioteca.getPadre().getId(), idUsuario);
        }
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#asignarBibliotecas(java.lang.Long,
     *      java.util.List, java.util.List)
     */
    public void asignarBibliotecas(final Long idBiblioteca,
            final List<Long> listaRoles, final List<Usuario> usuarios) {
        final Biblioteca biblioteca = bibliotecaDao.get(idBiblioteca);
        for (final Long idRol : listaRoles) {
            final Rol rol = rolDao.get(idRol);
            for (final Usuario usuario : usuarios) {
                final UsuarioBibliotecaRol perfil = new UsuarioBibliotecaRol(
                        usuario, rol, biblioteca);
                if (usuarioBibliotecaRolDao.getUsuarioBibliotecaRolByIds(
                        usuario.getId(), biblioteca.getId(), rol.getId())
                        .isEmpty()) {
                    usuarioBibliotecaRolDao.save(perfil);
                }
            }
        }
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#desasignarBibliotecas(java
     *      .lang.Long, java.util.List, java.util.List)
     */
    public void desasignarBibliotecas(final Long idBiblioteca,
            final List<Long> listaRoles, final List<Usuario> usuarios) {
        // Los usuarios que no pueden ser desasignados, pq solo tienen un rol
        final List<Usuario> usuariosNoEliminables = usuarioDao
                .getUsuariosUnRol();
        usuarios.removeAll(usuariosNoEliminables);

        final Biblioteca biblioteca = bibliotecaDao.get(idBiblioteca);
        for (final Long idRol : listaRoles) {
            final Rol rol = rolDao.get(idRol);
            for (final Usuario usuario : usuarios) {
                final List<UsuarioBibliotecaRol> lista = usuarioBibliotecaRolDao
                        .getUsuarioBibliotecaRolByIds(usuario.getId(),
                                biblioteca.getId(), rol.getId());
                if (!lista.isEmpty()) {
                    final UsuarioBibliotecaRol ubr = lista.get(0);
                    usuarioBibliotecaRolDao.remove(ubr.getId());
                }
            }
        }
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#asignarBibliotecas(java.lang.Long,
     *      java.util.List, org.librae.adminconfig.model.Usuario)
     */
    public void asignarBibliotecas(final Long idBiblioteca,
            final List<Long> listaRoles, final Usuario usuario) {
        usuarioBibliotecaRolDao.delete(usuario.getId(), idBiblioteca);

        final Biblioteca biblioteca = bibliotecaDao.get(idBiblioteca);
        for (final Long idRol : listaRoles) {
            final Rol rol = rolDao.get(idRol);
            final UsuarioBibliotecaRol ubr = new UsuarioBibliotecaRol(usuario,
                    rol, biblioteca);
            usuarioBibliotecaRolDao.save(ubr);
        }
        usuarioBibliotecaRolDao.flush();
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#getRoles(java.lang.Long,
     *      java.lang.Long)
     */
    public List<Rol> getRoles(final Long idUsuario, final Long idBiblioteca) {
        return rolDao.getRoles(idUsuario, idBiblioteca);
    }

    public Rol guardarRol(Rol rol) {
        final Rol rolRepetido = rolDao.getRolByCodigo(rol.getCodigo());
        if ((rolRepetido != null) && (!rolRepetido.getId().equals(rol.getId()))) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_CODIGO_DUPLICADO_ROL");
        }
        return rolDao.save(rol);
    }

    public Rol getRolByCodigo(String codigo) {
        return rolDao.getRolByCodigo(codigo);
    }

    // Getters && Setters

    public IPermisoDAO getPermisoDao() {
        return permisoDao;
    }

    public void setPermisoDao(final IPermisoDAO permisoDao) {
        this.permisoDao = permisoDao;
    }

    public IPermisoRolDAO getPermisoRolDao() {
        return permisoRolDao;
    }

    public void setPermisoRolDao(final IPermisoRolDAO permisoRolDao) {
        this.permisoRolDao = permisoRolDao;
    }

    public ITipoPermisoDAO getTipoPermisoDao() {
        return tipoPermisoDao;
    }

    public void setTipoPermisoDao(final ITipoPermisoDAO tipoPermisoDao) {
        this.tipoPermisoDao = tipoPermisoDao;
    }

    public IUsuarioBibliotecaRolDAO getUsuarioBibliotecaRolDao() {
        return usuarioBibliotecaRolDao;
    }

    public void setUsuarioBibliotecaRolDao(
            final IUsuarioBibliotecaRolDAO usuarioBibliotecaRolDao) {
        this.usuarioBibliotecaRolDao = usuarioBibliotecaRolDao;
    }

    public IBibliotecaDAO getBibliotecaDao() {
        return bibliotecaDao;
    }

    public void setBibliotecaDao(final IBibliotecaDAO bibliotecaDao) {
        this.bibliotecaDao = bibliotecaDao;
    }

    public IUsuarioDAO getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(final IUsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public IRolDAO getRolDao() {
        return rolDao;
    }

    public void setRolDao(final IRolDAO rolDao) {
        this.rolDao = rolDao;
    }

}
