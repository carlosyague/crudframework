package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.delegate.IGestionPerfilesDelegate;
import org.librae.adminconfig.webapp.form.AsignarPerfilesUsuarioForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF para la asignacion de usuario a bibliotecas.
 * 
 * @author jcisneros
 */
public class AsignarPerfilesUsuarioAction extends BasePage implements
        Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long          serialVersionUID = -3353217728383133380L;

    private IGestionPerfilesDelegate   delegate         = null;

    private AsignarPerfilesUsuarioForm form             = new AsignarPerfilesUsuarioForm();

    private String                     idListado        = null;

    private String                     page             = "asignar";

    private List<Usuario>              usuarios         = null;

    private Long                       nodoClicked      = null;

    private String                     asignarRolesForm = "asignarRoles_form";

    /**
     * Recibe el id del listado de usuarios y prepara el formulario.
     * 
     * @return pagina de asignar roles.
     */
    public String init() {
        String page = this.page;
        try {
            getSessionManager().removeAttribute(asignarRolesForm);
            getSessionManager().removeAttribute("idBiblioteca_asignarRoles");
            setUsuarios(obtenerUsuariosSeleccionados(getIdListado()));
            if (usuarios.isEmpty()) {
                page = "list";
            }
        } catch (final Exception e) {
            log.error("No se puede entrar al asignar biblioteca a usuario...",
                    e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
            return "list";
        }
        return page;
    }

    /**
     * Recibe el id del listado de usuarios y prepara el formulario.
     * 
     * @return pagina de asignar roles.
     */
    public String initUsuario() {
        final String page = "asignarUsuario";
        try {
            getSessionManager().removeAttribute("idBiblioteca_asignarRoles");
            form.setUsuario(obtenerUsuarioSeleccionado(getIdListado()));
            getSessionManager().setAttribute(asignarRolesForm, form);
        } catch (final Exception e) {
            log.error("No se puede entrar al asignar biblioteca a usuario...",
                    e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
            return "list";
        }
        return page;
    }

    /**
     * Obtiene la lista de usuarios seleccionados.
     * 
     * @param idListado
     * @return
     */
    private List<Usuario> obtenerUsuariosSeleccionados(final String idListado) {
        if (getSessionManager() != null) {
            if (super.todosMarcados(idListado)) {
                final Map<String, Object> filtros = (Map<String, Object>) getSessionManager()
                        .getAttribute(idListado);
                if (filtros != null) {
                    filtros.remove(Constants.SORTCOLUMN);
                    filtros.remove(Constants.ASCENDING);
                    filtros.remove(Constants.PAGESIZE);
                    filtros.remove(Constants.CURRENTPAGE);
                }

                usuarios = delegate.buscar(filtros);
            } else {
                final List<String> listadoId = super.obtenerMarcados(idListado);

                /* Comprobamos que hemos seleccionado algún Usuario */
                if ((listadoId == null) || (listadoId.isEmpty())) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_ADMINCONFIG,
                            "ERROR_NINGUNA_SELECCION_USUARIO");
                }

                usuarios = delegate.getUsuariosById(listadoId);
            }
        }

        return usuarios;
    }

    /**
     * Obtiene la lista de usuarios seleccionados.
     * 
     * @param idListado
     * @return
     */
    private Usuario obtenerUsuarioSeleccionado(final String idListado) {
        if (getSessionManager() != null) {
            if (super.todosMarcados(idListado)) {
                throw ExceptionUtil
                        .crearLibraeException("ERROR_UNICA_SELECCION_LISTADO");
            } else {
                final List<String> listadoId = super.obtenerMarcados(idListado);

                if ((listadoId == null) || (listadoId.size() != 1)) {
                    throw ExceptionUtil
                            .crearLibraeException("ERROR_UNICA_SELECCION_LISTADO");
                }
                usuarios = delegate.getUsuariosById(listadoId);
            }
        }
        return usuarios.get(0);
    }

    /**
     * Buscar los permisos no asignados.
     * 
     * @return pagina de asignar permisos.
     */
    public void buscar() {
        final WindowMessages wm = new WindowMessages();
        try {
            final AsignarPerfilesUsuarioForm formulario = (AsignarPerfilesUsuarioForm) getSessionManager()
                    .getAttribute(asignarRolesForm);
            formulario.setCodigoRol(form.getCodigoRol());
            form = formulario;
            delegate.buscarRoles(form);
            getSessionManager().setAttribute(asignarRolesForm, form);
        } catch (final LibraeException e) {
            log.info("Error al buscar la lista de roles...", e);
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("Error al buscar la lista de roles...", e);
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_BUSCAR_ROL");
            setWindowMessages(wm);
        }
    }

    /**
     * Asigna todos los permisos al rol.
     * 
     * @return pagina de asignar permisos.
     */
    public void asignarTodos() {
        final WindowMessages wm = new WindowMessages();
        try {
            final AsignarPerfilesUsuarioForm formulario = (AsignarPerfilesUsuarioForm) getSessionManager()
                    .getAttribute(asignarRolesForm);
            delegate.asignarTodos(formulario);
            form = formulario;
            getSessionManager().setAttribute(asignarRolesForm, formulario);
        } catch (final LibraeException e) {
            log.info("No se puede asignar todos los permisos al rol...", e);
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("No se puede asignar todos los permisos al rol...", e);
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ASIGNAR_ROLES");
            setWindowMessages(wm);
        }
    }

    /**
     * Desasigna todos los permisos.
     * 
     * @return pagina de asignar permisos.
     */
    public void desasignarTodos() {
        final WindowMessages wm = new WindowMessages();
        try {
            final AsignarPerfilesUsuarioForm formulario = (AsignarPerfilesUsuarioForm) getSessionManager()
                    .getAttribute(asignarRolesForm);
            delegate.desasignarTodos(formulario);
            form = formulario;
            getSessionManager().setAttribute(asignarRolesForm, formulario);
        } catch (final LibraeException e) {
            log.info("No se pueden desasignar todos los permisos....", e);
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("No se pueden desasignar todos los permisos...", e);
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ASIGNAR_ROLES");
            setWindowMessages(wm);
        }
    }

    /**
     * Asigna el permisos al rol.
     * 
     * @return pagina de asignar permisos.
     */
    public void asignar() {
        final WindowMessages wm = new WindowMessages();
        try {
            final AsignarPerfilesUsuarioForm formulario = (AsignarPerfilesUsuarioForm) getSessionManager()
                    .getAttribute(asignarRolesForm);
            formulario.setIdsRolSinAsignar(form.getIdsRolSinAsignar());
            delegate.asignar(formulario);
            form = formulario;
            getSessionManager().setAttribute(asignarRolesForm, formulario);
        } catch (final LibraeException e) {
            log.info("No se puede asignar el permisos al rol...", e);
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("No se puede asignar el permisos al rol...", e);
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ASIGNAR_ROLES");
            setWindowMessages(wm);
        }
    }

    /**
     * Desasigna un permiso a un rol.
     * 
     * @return pagina de asignar permisos.
     */
    public void desasignar() {
        final WindowMessages wm = new WindowMessages();
        try {
            final AsignarPerfilesUsuarioForm formulario = (AsignarPerfilesUsuarioForm) getSessionManager()
                    .getAttribute(asignarRolesForm);
            formulario.setIdsRolAsignado(form.getIdsRolAsignado());
            delegate.desasignar(formulario);
            form = formulario;
            getSessionManager().setAttribute(asignarRolesForm, formulario);
        } catch (final LibraeException e) {
            log.info("No se puede desasignar un permiso a un rol...", e);
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("No se puede desasignar un permiso a un rol...", e);
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ASIGNAR_ROLES");
            setWindowMessages(wm);
        }
    }

    /**
     * Guarda en base de datos la asignacion.
     * 
     * @return pagina del listado de roles.
     */
    public String asignarUsuarios() {
        String page = "list";
        try {
            form.setIdBiblioteca((Long) getSessionManager().getAttribute(
                    "idBiblioteca_asignarRoles"));
            form.setUsuarios((List<Usuario>) getSessionManager().getAttribute(
                    "usuarios_asignarRoles"));
            delegate.asignarUsuarios(form);
        } catch (final LibraeException e) {
            log.info("Error al asignar los roles, falta la biblioteca...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
            page = this.page;
        } catch (final Exception e) {
            log.error("Error al asignar los roles...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ASIGNAR_ROLES");
            setWindowMessages(wm);
            page = this.page;
        }
        return page;
    }

    /**
     * Guarda en base de datos la asignacion.
     * 
     * @return pagina del listado de roles.
     */
    public String desasignarUsuarios() {
        String page = "list";
        try {
            form.setIdBiblioteca((Long) getSessionManager().getAttribute(
                    "idBiblioteca_asignarRoles"));
            form.setUsuarios((List<Usuario>) getSessionManager().getAttribute(
                    "usuarios_asignarRoles"));
            delegate.desasignarUsuarios(form);
        } catch (final LibraeException e) {
            log.info("Error al asignar los roles, falta la biblioteca...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
            page = this.page;
        } catch (final Exception e) {
            log.error("Error al asignar los roles...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ASIGNAR_ROLES");
            setWindowMessages(wm);
            page = this.page;
        }
        return page;
    }

    /**
     * Esta funcion se ejecuta cuando un nodo del arbol es seleccionado.
     */
    public void nodoClick() {
        final AsignarPerfilesUsuarioForm formulario = (AsignarPerfilesUsuarioForm) getSessionManager()
                .getAttribute(asignarRolesForm);
        if (formulario == null) {
            getSessionManager().setAttribute("idBiblioteca_asignarRoles",
                    getNodoClicked());
        } else {
            formulario.setIdBiblioteca(getNodoClicked());
            formulario
                    .setRolesAsignados(delegate.getRolesAsignados(formulario));
            formulario.setRolesNoAsignados(delegate
                    .getRolesNoAsignados(formulario));
            form = formulario;
            getSessionManager().setAttribute(asignarRolesForm, formulario);
        }
    }

    /**
     * Set para la lista de usuarios.
     * 
     * @param usuarios
     */
    public void setUsuarios(final List<Usuario> usuarios) {
        this.usuarios = usuarios;
        if (usuarios != null) {
            getSessionManager().setAttribute("usuarios_asignarRoles", usuarios);
        }
    }

    /**
     * Obtiene la lista de usuarios.
     * 
     * @return
     */
    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = (List<Usuario>) getSessionManager().getAttribute(
                    "usuarios_asignarRoles");
        }
        return usuarios;
    }

    /**
     * Obtiene el nodo seleccionado del arbol.
     * 
     * @return
     */
    public Long getNodoClicked() {
        final AsignarPerfilesUsuarioForm formulario = (AsignarPerfilesUsuarioForm) getSessionManager()
                .getAttribute(asignarRolesForm);
        if ((nodoClicked == null) && (formulario != null)) {
            nodoClicked = formulario.getIdBiblioteca();
        }
        final Long idBibliotecaSeleccionada = (Long) getSessionManager()
                .getAttribute("idBiblioteca_asignarRoles");
        if ((nodoClicked == null) && (idBibliotecaSeleccionada != null)) {
            nodoClicked = idBibliotecaSeleccionada;
        }
        return nodoClicked;
    }

    /**
     * Guarda en base de datos la asignacion.
     * 
     * @return pagina del listado de roles.
     */
    public String save() {
        final WindowMessages wm = new WindowMessages();
        String page = "list";
        try {
            final AsignarPerfilesUsuarioForm formulario = (AsignarPerfilesUsuarioForm) getSessionManager()
                    .getAttribute(asignarRolesForm);
            form = formulario;
            delegate.save(formulario);
        } catch (final LibraeException e) {
            log.info("Imposible asignar el rol al usuario: ", e);
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
            page = this.page;
        } catch (final Exception e) {
            log.error("No se puede asignar el rol al usuario...", e);
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ASIGNAR_ROLES");
            setWindowMessages(wm);
            page = this.page;
        }
        return page;
    }

    // Getters && Setters

    public IGestionPerfilesDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final IGestionPerfilesDelegate gestionRolesDelegate) {
        delegate = gestionRolesDelegate;
    }

    public AsignarPerfilesUsuarioForm getForm() {
        return form;
    }

    public void setForm(final AsignarPerfilesUsuarioForm form) {
        this.form = form;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getIdListado() {
        return idListado;
    }

    public void setIdListado(final String idListado) {
        this.idListado = idListado;
    }

    public void setNodoClicked(final Long nodoClicked) {
        this.nodoClicked = nodoClicked;
        setSessionManagerParam("idBiblioteca_asignarRoles", nodoClicked);
    }

    public String getAsignarRolesForm() {
        return asignarRolesForm;
    }

    public void setAsignarRolesForm(String asignarRolesForm) {
        this.asignarRolesForm = asignarRolesForm;
    }

}
