package org.librae.adminconfig.webapp.delegate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoPermiso;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.adminconfig.service.IRolManager;
import org.librae.adminconfig.service.IUsuarioManager;
import org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate;
import org.librae.adminconfig.webapp.form.AsignarPermisoRolForm;
import org.librae.adminconfig.webapp.form.RolForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.ConvertUtil;
import org.librae.common.webapp.delegate.impl.FavoritoDelegateImpl;
import org.springframework.dao.DataAccessException;

/**
 * Interfaz del delegado para la getion de roles.
 * 
 * @author jcisneros
 */
public class GestionRolesDelegateImpl extends FavoritoDelegateImpl implements
        IGestionRolesDelegate {

    /**
     * Numero de serializacion.
     */
    private static final long  serialVersionUID = -7578344666000414923L;
    private IRolManager        rolManager;
    private IUsuarioManager    usuarioManager;
    private IBibliotecaManager bibliotecaManager;
    private String             buscarPor;

    protected final Log        log              = LogFactory
                                                        .getLog(GestionRolesDelegateImpl.class);

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#snapshot(org.librae.adminconfig.webapp.form.AsignarPermisoRolForm)
     */
    public void snapshot(final AsignarPermisoRolForm form) {
        Rol rol = null;
        if (form.getIdRol() != null) {
            rol = rolManager.get(form.getIdRol());
            form.fromEntity(rol);
            // form.setPermisosAsignados(listaPermisos(rolManager
            // .obtenerPermisosDeRol(rol, permisos)));
            // form.setPermisosNoAsignados(listaPermisos(rolManager
            // .obtenerPermisosSinAsignar(form.getIdRol(), form
            // .getCodigoPermiso(), form.getCategoriaPermiso())));
            // form.setCategoriasPermisos(listaTipoPermisos(rolManager
            // .getTiposPermisos()));
        }
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#asignar(org.librae.adminconfig.webapp.form.AsignarPermisoRolForm)
     */
    public void asignar(final AsignarPermisoRolForm formulario) {
        final List<SelectItem> borrarDeListaNoAsignado = new ArrayList<SelectItem>();
        final ListIterator<String> iteradorIdPermisoSeleccionado = formulario
                .getIdsPermisoSinAsignar().listIterator();
        while (iteradorIdPermisoSeleccionado.hasNext()) {
            final ListIterator<SelectItem> iteradorSinAsignar = formulario
                    .getPermisosNoAsignados().listIterator();
            final String idPermisoSeleccionado = iteradorIdPermisoSeleccionado
                    .next();
            while (iteradorSinAsignar.hasNext()) {
                final SelectItem permiso = iteradorSinAsignar.next();
                if (permiso.getValue().equals(idPermisoSeleccionado)) {
                    borrarDeListaNoAsignado.add(permiso);
                }
            }
        }
        formulario.getPermisosNoAsignados().removeAll(borrarDeListaNoAsignado);
        formulario.getPermisosAsignados().addAll(borrarDeListaNoAsignado);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#asignarTodos(org.librae.adminconfig.webapp.form.AsignarPermisoRolForm)
     */
    public void asignarTodos(final AsignarPermisoRolForm formulario) {
        formulario.getPermisosAsignados().addAll(
                formulario.getPermisosNoAsignados());
        formulario.setPermisosNoAsignados(new ArrayList<SelectItem>());
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#desasignar(org.librae.adminconfig.webapp.form.AsignarPermisoRolForm)
     */
    public void desasignar(final AsignarPermisoRolForm formulario) {
        final List<SelectItem> borrarDeListaNoAsignado = new ArrayList<SelectItem>();
        final ListIterator<String> iteradorIdPermisoSeleccionado = formulario
                .getIdsPermisoAsignado().listIterator();
        while (iteradorIdPermisoSeleccionado.hasNext()) {
            final ListIterator<SelectItem> iteradorSinAsignar = formulario
                    .getPermisosAsignados().listIterator();
            final String idPermisoSeleccionado = iteradorIdPermisoSeleccionado
                    .next();
            while (iteradorSinAsignar.hasNext()) {
                final SelectItem permiso = iteradorSinAsignar.next();
                if (permiso.getValue().equals(idPermisoSeleccionado)) {
                    borrarDeListaNoAsignado.add(permiso);
                }
            }
        }
        formulario.getPermisosNoAsignados().addAll(borrarDeListaNoAsignado);
        formulario.getPermisosAsignados().removeAll(borrarDeListaNoAsignado);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#desasignarTodos(org.librae.adminconfig.webapp.form.AsignarPermisoRolForm)
     */
    public void desasignarTodos(final AsignarPermisoRolForm formulario) {
        formulario.getPermisosNoAsignados().addAll(
                formulario.getPermisosAsignados());
        formulario.setPermisosAsignados(new ArrayList<SelectItem>());
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#buscar(org.librae.adminconfig.webapp.form.AsignarPermisoRolForm)
     */
    public void buscar(final AsignarPermisoRolForm form) {
        final List<SelectItem> listaNoAsignadoFiltrado = new ArrayList<SelectItem>();

        final List<SelectItem> listaPermisosSinAsignar = listaPermisos(rolManager
                .obtenerPermisosSinAsignar(form.getIdRol(), form
                        .getCodigoPermiso(), form.getCategoriaPermiso()));

        final ListIterator<SelectItem> iteradorPermisoAsignados = listaPermisosSinAsignar
                .listIterator();
        while (iteradorPermisoAsignados.hasNext()) {
            boolean encontrado = false;
            final SelectItem permisoNoAsignado = iteradorPermisoAsignados
                    .next();
            final ListIterator<SelectItem> iteradorSinAsignar = form
                    .getPermisosAsignados().listIterator();
            while (iteradorSinAsignar.hasNext()) {
                final SelectItem permiso = iteradorSinAsignar.next();
                if (permiso.getValue().equals(permisoNoAsignado.getValue())) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                listaNoAsignadoFiltrado.add(permisoNoAsignado);
            }
        }
        form.setPermisosNoAsignados(listaNoAsignadoFiltrado);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#save(org.librae.adminconfig.webapp.form.AsignarPermisoRolForm)
     */
    public void save(final AsignarPermisoRolForm form) {
        final List<Long> listaPermisos = new ArrayList<Long>();
        final ListIterator<SelectItem> permisos = form.getPermisosAsignados()
                .listIterator();
        while (permisos.hasNext()) {
            final SelectItem idPermiso = permisos.next();
            listaPermisos.add(new Long((String) idPermiso.getValue()));
        }
        // rolManager.asignarPermisos(form.getIdRol(), listaPermisos);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#getUsuario(java.lang.Long)
     */
    public Usuario getUsuario(final Long idUsuario) {
        Usuario usuario = null;
        usuario = usuarioManager.get(idUsuario);
        return usuario;
    }

    public List<Rol> buscar(final Map<String, Object> criterios) {
        return rolManager.buscar(criterios);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#guardarRol(org.librae.adminconfig.webapp.form.RolForm)
     */
    public Rol guardarRol(final RolForm rolForm) {
        try {
            Rol rol = null;
            if (rolForm.getIdRol() == null) {
                /* Estamos creando */
                rol = rolForm.toEntity();
            } else {
                /* Estamos modificando */
                rol = rolManager.get(rolForm.getIdRol());
                rol = addDatos(rol, rolForm);
            }
            return rolManager.guardarRol(rol);
        } catch (final LibraeException le) {
            throw le;
        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException("ERROR_GUARDAR_ROL",
                    MensajesError.PROPERTI_ADMINCONFIG, dae);
        } catch (final Exception e) {
            log
                    .error("[GestionRolesDelegateImpl.guardarRol] ERROR guardando un Rol "
                            + e);
            throw ExceptionUtil.crearLibraeException("ERROR_GUARDAR_ROL",
                    MensajesError.PROPERTI_ADMINCONFIG, e);
        }
    }

    /**
     * Método que completa el Rol con los datos del formulario.
     * 
     * @param rol
     *            , Rol a completar.
     * @param form
     *            , formulario con los datos a actualizar del Rol.
     */
    private Rol addDatos(final Rol rol, final RolForm rolForm) {
        rol.setCodigo(rolForm.getCodigo());
        rol.setNombre(rolForm.getNombre());
        rol.setNivel(rolForm.getNivel());
        return rol;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate#prepararDatosVista(org.librae.adminconfig.webapp.form.RolForm)
     */
    public void prepararDatosVista(final RolForm rolForm) {
        Rol rol = null;
        if (rolForm.getIdRol() != null) {
            rol = rolManager.get(rolForm.getIdRol());
            rolForm.fromEntity(rol);
        }
    }

    public void eliminar(final Long idRol) {
        try {
            rolManager.eliminar(idRol);
        } catch (final LibraeException le) {
            throw le;
        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException(dae,
                    MensajesError.PROPERTI_ADMINCONFIG);
        } catch (final Exception e) {
            log
                    .error("[GestionRolesDelegateImpl.eliminar] ERROR eliminando un Rol "
                            + e);
            throw ExceptionUtil.crearLibraeException("ERROR_DELETE_BD", e);
        }
    }

    /**
     * Convierte una lista de permisos en lista de SelectItem.
     * 
     * @param listaPermisos
     * @return lista de items permisos
     */
    private List<SelectItem> listaPermisos(final List listaPermisos) {
        List<SelectItem> permisos = null;
        try {
            permisos = ConvertUtil.convertListItem(listaPermisos, "id",
                    "codigo");
        } catch (final LibraeException e) {
            log.error("Fallo al convertir los permisos en select items...", e);
        }
        return permisos;
    }

    /**
     * Convierte una lista de tipoPermisos en lista de SelectItem.
     * 
     * @param listaPermisos
     * @return lista de items permisos
     */
    private List<SelectItem> listaTipoPermisos(
            final List<TipoPermiso> listaTipoPermisos) {
        List<SelectItem> tiposPermiso = null;
        try {
            tiposPermiso = ConvertUtil.convertListItem(listaTipoPermisos, "id",
                    "codigo");
        } catch (final LibraeException e) {
            log.error("Fallo al convertir los permisos...", e);
        }
        return tiposPermiso;
    }

    public List<Rol> obtenerRolesById(List<String> listadoId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Biblioteca getBiblioteca(Long idBiblioteca) {
        return bibliotecaManager.get(idBiblioteca);
    }

    // Getters && Setters

    public void setBibliotecaManager(IBibliotecaManager bibliotecaManager) {
        this.bibliotecaManager = bibliotecaManager;
    }

    public IBibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    public void setRolManager(final IRolManager rolManager) {
        this.rolManager = rolManager;
    }

    public IRolManager getRolManager() {
        return rolManager;
    }

    public IUsuarioManager getUsuarioManager() {
        return usuarioManager;
    }

    public void setUsuarioManager(final IUsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }

    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor
     *            the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

}
