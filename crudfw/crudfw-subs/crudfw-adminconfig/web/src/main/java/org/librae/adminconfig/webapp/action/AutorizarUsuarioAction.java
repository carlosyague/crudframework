package org.librae.adminconfig.webapp.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import model.CodPer;

import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.delegate.IAutorizarUsuarioDelegate;
import org.librae.common.Constants;
import org.librae.common.exception.MensajesError;
import org.librae.common.model.LibraeUser;
import org.librae.common.util.Propiedades;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.jsf.listener.user.UserWatchdogPhaseListener;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los formularios de la entidad UsuarioBibliotecaRol
 * 
 * @author jcisneros, cyague(autorización-CAS)
 */
public class AutorizarUsuarioAction extends BasePage implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long         serialVersionUID         = 1L;
    private IAutorizarUsuarioDelegate autorizarUsuarioDelegate = null;
    TreeNode                          treeData                 = null;
    Long                              nodoClicked              = null;
    List<String>                      roles                    = null;
    Boolean                           defecto                  = false;
    String                            urlBack                  = null;
    private Boolean                   orbeActivo               = Boolean.FALSE;

    /**
     * pestañas
     */
    private TabGroup                  tabGroup                 = new TabGroup(
                                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private Tab                       tabBiblioteca            = new Tab(
                                                                       tabGroup,
                                                                       "autorizar.tab.biblioteca");
    private boolean                   isRedirect               = false;

    /**
     * devuelve el usuario logado. si no lo estaba, lo agrega a sesión
     * 
     * @return
     */
    private Usuario getLoggedUserOrSetInSession() {
        Usuario result = null;
        final boolean existsUserInSession = existsUserInSessionManager();
        if (existsUserInSession) {
            // obtenemos el usuario de la session
            result = (Usuario) getUserFromSessionManager();
        } else {
            /**
             * obtenemos el usuario asociado al ticket en forma de cookie que ha
             * insertado CAS, tras validar al usuario actual
             */
            final Usuario usuario = autorizarUsuarioDelegate
                    .getUsuarioByCASUsername();
            if (usuario != null) {
                // añadimos el objeto Usuario a sesión
                setUserInSessionManager(usuario, true);
                result = usuario;
            }
        }
        return result;
    }

    /**
     * Obtiene el username del usuario logado en CAS,
     * 
     * @return
     */
    public String getUrlBack() {
        Usuario usuario = null;
        List<CodPer> permisos = null;

        final Propiedades properties = Propiedades.getInstance();

        final String orbeActivoStr = properties
                .getPropiedad(Constants.ORBE_ACTIVO);

        if (orbeActivoStr != null && orbeActivoStr.equals("true")) {
            orbeActivo = Boolean.TRUE;
        }

        try {
            usuario = (Usuario) getSessionManagerParam(Constants.USUARIO_SESSION_PARAM);
            if (usuario == null) {
                urlBack = getUrlBackRequest();
                usuario = getLoggedUserOrSetInSession();
                if (usuario != null) {
                    if ((orbeActivo)
                            && (autorizarUsuarioDelegate
                                    .usuarioRecienLLegadoDeOrbe(usuario))) {
                        final List<BibliotecaView> bibliotecas = autorizarUsuarioDelegate
                                .getBibliotecasByUsuario(usuario);
                        final BibliotecaView biblioteca = bibliotecas.get(0);
                        permisos = convertPermisoToCodigoPermiso(autorizarUsuarioDelegate
                                .save(usuario, biblioteca.getId(), true));
                        usuario = getLoggedUserOrSetInSession();
                        redirectAutorizarOrbe();
                    } else {

                        if (usuario.getBibliotecaPorDefecto() == null) {
                            final List<BibliotecaView> bibliotecas = autorizarUsuarioDelegate
                                    .getBibliotecasByUsuario(usuario);
                            if (bibliotecas.size() == 1) {
                                final BibliotecaView biblioteca = bibliotecas
                                        .get(0);
                                permisos = convertPermisoToCodigoPermiso(autorizarUsuarioDelegate
                                        .save(usuario, biblioteca.getId(), true));
                                redirect();
                            }
                        } else {
                            permisos = convertPermisoToCodigoPermiso(autorizarUsuarioDelegate
                                    .save(usuario, usuario
                                            .getBibliotecaPorDefecto().getId(),
                                            true));

                            redirect();
                        }
                    }
                    if (permisos == null) {
                        permisos = new ArrayList<CodPer>();
                        permisos.add(new CodPer(Constants.USER_ROLE));
                    }
                }
                setUserInSessionManager(usuario, false);
                final SessionManager session = getSessionManager();
                setLibraeUserInSession(usuario, session, permisos);
            }
        } catch (final Exception e) {
            log.error("Error...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        }

        return urlBack;
    }

    private void setLibraeUserInSession(Usuario usuario,
            SessionManager session, List<CodPer> permisos) {
        final LibraeUser libraeUser = new LibraeUser();
        libraeUser.setId(usuario.getId());
        libraeUser.setUsuario(usuario.getUsuario());
        libraeUser.setPer(permisos);
        if (session != null) {
            session.setAttribute(Constants.LIBRAE_USUARIO_SESSION_PARAM,
                    libraeUser);
            session.setAttribute(Constants.DESCRIPCION_SUCURSAL_ACTUAL, usuario
                    .getBibliotecaActual().getDescripcion());
            session.setAttribute(Constants.NOMBRE_USUARIO_LOGADO, usuario
                    .getUsuario());
            session.setAttribute(Constants.ID_BIBLIOTECA_ACTUAL, usuario
                    .getBibliotecaActual().getId());
            session.setAttribute(Constants.CODIGO_BIBLIOTECA_ACTUAL, usuario
                    .getBibliotecaActual().getCodigo());
            session.setAttribute(Constants.TIPO_CODIGO_BIBLIOTECA_ACTUAL,
                    usuario.getBibliotecaActual().getTipoBiblioteca()
                            .getCodigo());
            session.setAttribute(Constants.ID_USUARIO_LOGADO, usuario.getId());
            if (usuario.getBibliotecaActual().getPadre() != null) {
                session.setAttribute(Constants.DESCRIPCION_BIBLIOTECA_ACTUAL,
                        usuario.getBibliotecaActual().getPadre()
                                .getDescripcion());
            } else {
                session.setAttribute(Constants.DESCRIPCION_BIBLIOTECA_ACTUAL,
                        null);
            }
            final byte[] logo = usuario.getBibliotecaActual().getLogotipo();
            session.setAttribute(Constants.LOGO_BIBLIOTECA_ACTUAL, usuario
                    .getBibliotecaActual().getLogotipo());
            session.setAttribute(Constants.TIENE_LOGO_BIBLIOTECA_ACTUAL,
                    logo != null);
        }
    }

    /**
     * Devuelve el arbol que debe de tener el usuario.
     * 
     * @return
     */
    public TreeNode getTreeData() {
        Usuario usuario = null;
        if ((treeData == null) && (!isRedirect)) {
            TreeNode tree = new TreeNodeBase("bibliotecas", "Bibliotecas",
                    false);
            try {
                usuario = (Usuario) getUserFromSessionManager();
                if (usuario != null) {
                    tree = autorizarUsuarioDelegate
                            .getTreeData(usuario.getId());
                    treeData = tree;
                }
            } catch (final Exception e) {
                log.error("Fallo al hacer el arbol...", e);
            }
        } else if ((treeData == null) && (isRedirect)) {
            treeData = new TreeNodeBase("bibliotecas", "Bibliotecas", false);
        }
        return treeData;
    }

    /**
     * @return
     */
    public String nodoClick() {
        getSessionManager().setAttribute("idBiblioteca", nodoClicked);
        final Usuario usuario = (Usuario) getUserFromSessionManager();
        if (usuario != null) {
            roles = autorizarUsuarioDelegate.getRoles(usuario.getId(),
                    nodoClicked);
        }
        return "autorizar";
    }

    /**
     * Guarda la biblioteca a la que se conecta el usuario.
     * 
     * @return
     */
    public String save() {
        try {
            final SessionManager session = getSessionManager();
            if (session != null) {
                final Usuario usuario = (Usuario) session
                        .getAttribute(Constants.USUARIO_SESSION_PARAM);
                final Long idBiblioteca = (Long) session
                        .getAttribute("idBiblioteca");
                final List<Permiso> permisos = autorizarUsuarioDelegate.save(
                        usuario, idBiblioteca, defecto);
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(getUrlBackRequest());
                session.setAttribute(Constants.USUARIO_SESSION_PARAM, usuario);
                session.removeAttribute(Constants.MENU_LIBRAE);
                setLibraeUserInSession(usuario, session,
                        (convertPermisoToCodigoPermiso(permisos)));
            }
        } catch (final Exception e) {
            final WindowMessages wm = new WindowMessages();
            log.error("Fallo al hacer el arbol...", e);
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_AUTORIZAR_SAVE");
            setWindowMessages(wm);
        }
        return "autorizar";
    }

    /**
     * Cancela operación del usuario.
     * 
     * @return
     */
    public String cancel() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(getUrlBackRequest());
        } catch (final Exception e) {
            final WindowMessages wm = new WindowMessages();
            log.error("Fallo al cancelar...", e);
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_AUTORIZAR_SAVE");
            setWindowMessages(wm);
        }
        return "autorizar";
    }
    
    public static List<CodPer> convertPermisoToCodigoPermiso(
            List<Permiso> permisos) {
        final List<CodPer> codigos = new ArrayList<CodPer>();
        for (final Permiso permiso : permisos) {
            codigos.add(new CodPer(permiso.getCodigo()));
        }
        return codigos;
    }

    /**
     * Guarda la biblioteca a la que se conecta el usuario.
     * 
     * @return
     */
    public String seleccionaBibOrbe() {
        final String result = save();

        final Usuario usuario = (Usuario) getSessionManager().getAttribute(
                Constants.USUARIO_SESSION_PARAM);
        final Long idBiblioteca = (Long) getSessionManager().getAttribute(
                "idBiblioteca");

        autorizarUsuarioDelegate.seleccionaBibOrbe(usuario, idBiblioteca);

        final WindowMessages wm = new WindowMessages();
        wm.addInfoMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                "OK_SELECION_BIBLIOTECA_ORBE");
        setWindowMessages(wm);

        return result;
    }

    /**
     * Devuelve la URL de retorno del subsistema que inicialmente al que el
     * usuario invocó antes de que el UserWatchDogPhaseListener nos redirigiera
     * a la página de autorización.
     * 
     * @return
     */
    public String getUrlBackRequest() {
        return "/librae-mensajeria/pages/consultarTareasPendientes.html";
    }

    /**
     * @param event
     * @throws Exception
     */
    private void redirect() throws IOException {
        final HttpServletResponse response = getResponse();
        response.sendRedirect(urlBack);
        isRedirect = true;
    }

    private void redirectAutorizarOrbe() throws IOException {
        final HttpServletResponse response = getResponse();
        final String urlAutorizarOrbe = autorizarUsuarioDelegate
                .getUrlAutorizarOrbe();
        response.sendRedirect(urlAutorizarOrbe);
        isRedirect = true;
    }

    /**
     * Obtiene el username del usuario logado en CAS,
     * 
     * @return
     */
    public String getUsernameLoggedInCAS() {
        String result = null;
        Usuario usuario = null;

        usuario = (Usuario) getUserFromSessionManager();

        if (usuario != null) {
            result = usuario.getUsuario();
        }

        return result;
    }

    /**
     * Llega a la pagina de autorizar para cambiar las credenciales.
     * 
     * @return
     */
    public String cambiarCredenciales() {
        final String result = "autorizar";
        Usuario usuario = null;
        usuario = (Usuario) getUserFromSessionManager();
        if (autorizarUsuarioDelegate.getBibliotecasByUsuario(usuario).size() == 1) {
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_BIBLIOTECA_UNICA");
            setWindowMessages(wm);
        }
        return result;
    }

    /**
     * Se rellena cuando se pincha en el arbol.
     * 
     * @return
     */
    public Long getNodoClicked() {
        if (nodoClicked == null) {
            if (getSessionManager() != null) {
                final Long idBiblioteca = (Long) getSessionManager()
                        .getAttribute("idBiblioteca");
                final Usuario usuario = (Usuario) getSessionManager()
                        .getAttribute(Constants.USUARIO_SESSION_PARAM);
                if ((usuario != null) && (idBiblioteca == null)
                        && (usuario.getBibliotecaActual() != null)) {
                    nodoClicked = usuario.getBibliotecaActual().getId();
                    getSessionManager().setAttribute("idBiblioteca",
                            nodoClicked);
                } else {
                    nodoClicked = idBiblioteca;
                }
            }
        }
        return nodoClicked;
    }

    /**
     * Obtiene la lista de roles del usuario para una biblioteca.
     * 
     * @return
     */
    public List<String> getRoles() {
        if ((roles == null) && (nodoClicked != null)) {
            final Usuario usuario = (Usuario) getUserFromSessionManager();
            roles = autorizarUsuarioDelegate.getRoles(usuario.getId(),
                    nodoClicked);
        }
        return roles;
    }

    public Boolean getDefecto() {
        final Usuario usuario = (Usuario) getUserFromSessionManager();
        if ((usuario != null) && (getNodoClicked() != null)
                && (usuario.getBibliotecaPorDefecto() != null)) {
            if (getNodoClicked().equals(
                    usuario.getBibliotecaPorDefecto().getId())) {
                defecto = true;
            } else {
                defecto = false;
            }
        }
        return defecto;
    }

    // Getters && Setters

    public IAutorizarUsuarioDelegate getAutorizarUsuarioDelegate() {
        return autorizarUsuarioDelegate;
    }

    public void setAutorizarUsuarioDelegate(
            final IAutorizarUsuarioDelegate autorizarUsuarioDelegate) {
        this.autorizarUsuarioDelegate = autorizarUsuarioDelegate;
    }

    public void setTreeData(final TreeNode treeData) {
        this.treeData = treeData;
    }

    public void setNodoClicked(final Long nodoClicked) {
        this.nodoClicked = nodoClicked;
    }

    public void setRoles(final List<String> roles) {
        this.roles = roles;
    }

    public void setDefecto(final Boolean defecto) {
        this.defecto = defecto;
    }

    public void setUrlBack(final String urlBack) {
        this.urlBack = urlBack;
    }

    /**
     * @return the tabGroup
     */
    public TabGroup getTabGroup() {
        return tabGroup;
    }

    /**
     * @return the tabBiblioteca
     */
    public Tab getTabBiblioteca() {
        return tabBiblioteca;
    }

    /**
     * @param tabGroup
     *            the tabGroup to set
     */
    public void setTabGroup(final TabGroup tabGroup) {
        this.tabGroup = tabGroup;
    }

    /**
     * @param tabBiblioteca
     *            the tabBiblioteca to set
     */
    public void setTabBiblioteca(final Tab tabBiblioteca) {
        this.tabBiblioteca = tabBiblioteca;
    }

    /**
     * @return the orbeActivo
     */
    public Boolean getOrbeActivo() {
        return orbeActivo;
    }

}