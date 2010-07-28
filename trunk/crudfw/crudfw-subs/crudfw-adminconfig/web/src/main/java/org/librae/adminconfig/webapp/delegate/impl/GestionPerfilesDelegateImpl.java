package org.librae.adminconfig.webapp.delegate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.service.IRolManager;
import org.librae.adminconfig.service.IUsuarioManager;
import org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate;
import org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.ConvertUtil;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz del delegado para la getion de roles.
 * 
 * @author jcisneros
 */
public class GestionPerfilesDelegateImpl implements IGestionPerfilesDelegate,
        ISearchDelegate<Usuario> {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = -7578344666000414923L;
    private IRolManager       rolManager;
    private IUsuarioManager   usuarioManager;

    protected final Log       log              = LogFactory
                                                       .getLog(GestionPerfilesDelegateImpl.class);

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#asignar(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public void asignar(final AsignarPerfilesUsuarioForm formulario) {
        final List<SelectItem> borrarDeListaNoAsignado = new ArrayList<SelectItem>();
        final ListIterator<String> iteradorIdRoleseleccionado = formulario
                .getIdsRolSinAsignar().listIterator();
        while (iteradorIdRoleseleccionado.hasNext()) {
            final ListIterator<SelectItem> iteradorSinAsignar = formulario
                    .getRolesNoAsignados().listIterator();
            final String idRoleseleccionado = iteradorIdRoleseleccionado.next();
            while (iteradorSinAsignar.hasNext()) {
                final SelectItem permiso = iteradorSinAsignar.next();
                if (permiso.getValue().equals(idRoleseleccionado)) {
                    borrarDeListaNoAsignado.add(permiso);
                }
            }
        }
        formulario.getRolesNoAsignados().removeAll(borrarDeListaNoAsignado);
        formulario.getRolesAsignados().addAll(borrarDeListaNoAsignado);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#asignarTodos(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public void asignarTodos(final AsignarPerfilesUsuarioForm formulario) {
        formulario.getRolesAsignados().addAll(formulario.getRolesNoAsignados());
        formulario.setRolesNoAsignados(new ArrayList<SelectItem>());
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#desasignar(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public void desasignar(final AsignarPerfilesUsuarioForm formulario) {
        final List<SelectItem> borrarDeListaNoAsignado = new ArrayList<SelectItem>();
        final ListIterator<String> iteradorIdRoleseleccionado = formulario
                .getIdsRolAsignado().listIterator();
        while (iteradorIdRoleseleccionado.hasNext()) {
            final ListIterator<SelectItem> iteradorSinAsignar = formulario
                    .getRolesAsignados().listIterator();
            final String idRoleseleccionado = iteradorIdRoleseleccionado.next();
            while (iteradorSinAsignar.hasNext()) {
                final SelectItem permiso = iteradorSinAsignar.next();
                if (permiso.getValue().equals(idRoleseleccionado)) {
                    borrarDeListaNoAsignado.add(permiso);
                }
            }
        }
        formulario.getRolesNoAsignados().addAll(borrarDeListaNoAsignado);
        formulario.getRolesAsignados().removeAll(borrarDeListaNoAsignado);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#desasignarTodos(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public void desasignarTodos(final AsignarPerfilesUsuarioForm formulario) {
        formulario.getRolesNoAsignados().addAll(formulario.getRolesAsignados());
        formulario.setRolesAsignados(new ArrayList<SelectItem>());
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#buscarRoles(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public void buscarRoles(final AsignarPerfilesUsuarioForm form) {
        final List<SelectItem> listaNoAsignadoFiltrado = new ArrayList<SelectItem>();

        if (form.getIdBiblioteca() == null) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_BIBLIOTECA_NO_SELECCIONADA");
        }

        final List<SelectItem> listaRolesSinAsignar = ConvertUtil
                .convertListItem(rolManager.obtenerRolesSinAsignar(form
                        .getUsuario().getId(), form.getIdBiblioteca(), form
                        .getCodigoRol()), "id", "codigo");

        for (final SelectItem rolNoAsignado : listaRolesSinAsignar) {
            boolean encontrado = false;
            for (final SelectItem rolAsignado : form.getRolesAsignados()) {
                if (rolNoAsignado.getValue().equals(rolAsignado.getValue())) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                listaNoAsignadoFiltrado.add(rolNoAsignado);
            }
        }
        form.setRolesNoAsignados(listaNoAsignadoFiltrado);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#save(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public void save(final AsignarPerfilesUsuarioForm form) {
        final List<Long> listaRoles = new ArrayList<Long>();
        if (form.getIdBiblioteca() == null) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_BIBLIOTECA_NO_SELECCIONADA");
        }
        for (final SelectItem rol : form.getRolesAsignados()) {
            listaRoles.add(new Long((String) rol.getValue()));
        }
        rolManager.asignarBibliotecas(form.getIdBiblioteca(), listaRoles, form
                .getUsuario());
    }

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.Map)
     */
    public List<Usuario> buscar(final Map<String, Object> criterios) {
        return usuarioManager.buscar(criterios);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#getUsuariosById(java.util.List)
     */
    public List<Usuario> getUsuariosById(final List<String> listadoIds) {
        return usuarioManager.getUsuariosById(listadoIds);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#asignarUsuarios(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public void asignarUsuarios(final AsignarPerfilesUsuarioForm form) {
        final List<Long> listaRoles = new ArrayList<Long>();
        if (form.getIdBiblioteca() == null) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_BIBLIOTECA_NO_SELECCIONADA");
        }
        if ((form.getRolesSeleccionados() == null)
                || (form.getRolesSeleccionados().isEmpty())) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ROLES_NO_SELECCIONADOS");
        }
        for (final String rol : form.getRolesSeleccionados()) {
            listaRoles.add(new Long(rol));
        }
        rolManager.asignarBibliotecas(form.getIdBiblioteca(), listaRoles, form
                .getUsuarios());
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#desasignarUsuarios(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public void desasignarUsuarios(final AsignarPerfilesUsuarioForm form) {
        final List<Long> listaRoles = new ArrayList<Long>();
        if (form.getIdBiblioteca() == null) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_BIBLIOTECA_NO_SELECCIONADA");
        }
        if ((form.getRolesSeleccionados() == null)
                || (form.getRolesSeleccionados().isEmpty())) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ROLES_NO_SELECCIONADOS");
        }
        for (final String rol : form.getRolesSeleccionados()) {
            listaRoles.add(new Long(rol));
        }
        rolManager.desasignarBibliotecas(form.getIdBiblioteca(), listaRoles,
                form.getUsuarios());
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#getRolesAsignados(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public List<SelectItem> getRolesAsignados(
            final AsignarPerfilesUsuarioForm formulario) {
        List<SelectItem> roles = new ArrayList<SelectItem>();
        if (formulario.getIdBiblioteca() != null) {
            roles = ConvertUtil.convertListItem(rolManager.getRoles(formulario
                    .getUsuario().getId(), formulario.getIdBiblioteca()), "id",
                    "codigo");
        }
        return roles;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate#getRolesNoAsignados(org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm)
     */
    public List<SelectItem> getRolesNoAsignados(
            final AsignarPerfilesUsuarioForm formulario) {
        List<SelectItem> roles = new ArrayList<SelectItem>();
        if (formulario.getIdBiblioteca() != null) {
            roles = ConvertUtil.convertListItem(rolManager
                    .obtenerRolesSinAsignar(formulario.getUsuario().getId(),
                            formulario.getIdBiblioteca(), ""), "id", "codigo");
        }
        return roles;
    }

    // Getters && Setters

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

}
