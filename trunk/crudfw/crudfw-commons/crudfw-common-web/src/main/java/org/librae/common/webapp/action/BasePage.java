package org.librae.common.webapp.action;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.Constants;
import org.librae.common.exception.MensajesError;
import org.librae.common.model.LibraeUser;
import org.librae.common.model.MenuItem;
import org.librae.common.util.Propiedades;
import org.librae.common.webapp.jsf.listener.user.UserWatchdogPhaseListener;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.WindowMessages;

/**
 * BasePage es una clase que incializa el contexto de JSF común a todas las
 * acciones. Incluye una serie de móetodos y de propiedades que facilitan la
 * navegación entre páginas.
 */
public class BasePage {
    protected final Log           log                         = LogFactory
                                                                      .getLog(this
                                                                              .getClass());
    protected String              templateName;
    protected FacesContext        facesContext;
    protected String              sortColumn;
    protected boolean             ascending;
    protected boolean             nullsAreHigh;
    private Boolean               valorTrue                   = true;
    protected String              mensajeExito;

    /**
     * constants
     */
    public static final String    JSF_PAGE_EXTENSION          = ".xhtml";
    public static final String    JSF_URL_EXTENSION           = ".html";

    protected static final String WM_SESSION_NAME             = "WindowMessages_session";
    protected static final String PREFIJO                     = "seleccion_";
    protected static final String PREFIJOALL                  = "seleccionTodos_";
    public static final String    USER_WATCHDOG_PROPERTIES    = "org/librae/common/webapp/user_watchdog.properties";
    public static final String    HTTP_PROTOCOL_DEFAULT_VALUE = "http";
    public static final String    HTTP_PROTOCOL_KEY           = "http.protocol";

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    // Convenience methods ====================================================
    public Boolean getValorTrue() {
        return valorTrue;
    }

    public void setValorTrue(final Boolean valorTrue) {
        this.valorTrue = valorTrue;
    }

    public String getParameter(final String name) {
        return getRequest().getParameter(name);
    }

    public Map getCountries() {
        final CountryModel model = new CountryModel();
        return model.getCountries(getRequest().getLocale());
    }

    public String getBundleName() {
        return getFacesContext().getApplication().getMessageBundle();
    }

    public ResourceBundle getBundle() {
        final ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        return ResourceBundle.getBundle(getBundleName(), getRequest()
                .getLocale(), classLoader);
    }

    public String getText(final String key) {
        String message;

        try {
            message = getBundle().getString(key);
        } catch (final java.util.MissingResourceException mre) {
            log.warn("Missing key for '" + key + "'");
            return "???" + key + "???";
        }

        return message;
    }

    public String getText(final String key, final Object arg) {
        if (arg == null) {
            return this.getText(key);
        }

        final MessageFormat form = new MessageFormat(getBundle().getString(key));

        if (arg instanceof String) {
            return form.format(new Object[] { arg });
        } else if (arg instanceof Object[]) {
            return form.format(arg);
        } else {
            log.error("arg '" + arg + "' not String or Object[]");

            return "";
        }
    }

    @SuppressWarnings("unchecked")
    protected void addMessage(final String key, final Object arg) {
        // JSF Success Messages won't live past a redirect, so it's not used
        // FacesUtils.addInfoMessage(formatMessage(key, arg));
        List<String> messages = (List) getSession().getAttribute("messages");

        if (messages == null) {
            messages = new ArrayList<String>();
        }

        messages.add(this.getText(key, arg));
        getSession().setAttribute("messages", messages);
    }

    protected void addMessage(final String key) {
        this.addMessage(key, null);
    }

    @SuppressWarnings("unchecked")
    protected void addError(final String key, final Object arg) {
        // The "JSF Way" doesn't allow you to put HTML in your error messages,
        // so I don't use it.
        // FacesUtils.addErrorMessage(formatMessage(key, arg));
        List<String> errors = (List) getSession().getAttribute("errors");

        if (errors == null) {
            errors = new ArrayList<String>();
        }

        // if key contains a space, don't look it up, it's likely a raw message
        if (key.contains(" ") && arg == null) {
            errors.add(key);
        } else {
            errors.add(this.getText(key, arg));
        }

        getSession().setAttribute("errors", errors);
    }

    protected void addError(final String key) {
        this.addError(key, null);
    }

    /**
     * Convenience method for unit tests.
     * 
     * @return boolean indicator of an "errors" attribute in the session
     */
    public boolean hasErrors() {
        return (getSession().getAttribute("errors") != null);
    }

    /**
     * Servlet API Convenience method
     * 
     * @return HttpServletRequest from the FacesContext
     */
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext()
                .getRequest();
    }

    /**
     * Servlet API Convenience method
     * 
     * @return String from the FacesContext
     */
    protected String getRequestParam(final String param) {
        String result = null;

        result = getFacesContext().getExternalContext()
                .getRequestParameterMap().get(param);

        return result;
    }

    /**
     * Servlet API Convenience method
     * 
     * @return the current user's session
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * Servlet API Convenience method
     * 
     * @return HttpServletResponse from the FacesContext
     */
    protected HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext().getExternalContext()
                .getResponse();
    }

    /**
     * Servlet API Convenience method
     * 
     * @return the ServletContext form the FacesContext
     */
    protected ServletContext getServletContext() {
        return (ServletContext) getFacesContext().getExternalContext()
                .getContext();
    }

    /**
     * Método encargado de obtener la clase SessionManager que gestiona la
     * sesion compartida
     * 
     * @return SessionManager
     */
    protected SessionManager getSessionManager() {
        return SessionManager.getSessionManager(getRequest(),
                getServletContext());
    }

    /**
     * invalidate sessionManager
     */
    protected void invalidateSessionManager() {
        final HttpServletRequest request = getRequest();
        final ServletContext servletContext = getServletContext();

        if (request != null && servletContext != null) {
            SessionManager.invalidate(request, servletContext);
        }
    }

    /**
     * obtiene el objeto usuario de SessionManager
     * 
     * @return
     */
    protected Object getUserFromSessionManager() {
        Object result = null;
        final SessionManager session = getSessionManager();
        if (session != null) {
            result = session.getAttribute(Constants.USUARIO_SESSION_PARAM);
        }

        return result;
    }

    /**
     * devuelve true si existe el usuario en session
     * 
     * @return
     */
    protected boolean existsUserInSessionManager() {
        return (getUserFromSessionManager() != null);
    }

    /**
     * añade a SessionManager el objeto usuario
     * 
     * @param usuario
     */
    protected void setUserInSessionManager(final Object usuario,
            final boolean init) {
        if (init) {
            SessionManager.init(getRequest(), getResponse(),
                    getServletContext());
        }
        if (getSessionManager() != null) {
            getSessionManager().setAttribute(Constants.USUARIO_SESSION_PARAM,
                    usuario);
        }
    }

    /**
     * Devuelve el valor de un parámetro guardado en SessionManager
     * 
     * @param param
     * @return
     */
    protected Object getSessionManagerParam(final String param) {
        Object result = null;

        final SessionManager sessionManager = getSessionManager();
        if (sessionManager != null) {
            result = sessionManager.getAttribute(param);
        }

        return result;
    }

    /**
     * Guarda un parámetro en SessionManager
     * 
     * @param param
     * @param value
     */
    protected void setSessionManagerParam(final String param, final Object value) {

        final SessionManager sessionManager = getSessionManager();
        if (sessionManager != null) {
            sessionManager.setAttribute(param, value);
        }
    }

    /**
     * Elimina un parÃ¡metro en SessionManager
     * 
     * @param param
     * @param value
     */
    protected void removeSessionManagerParam(final String param) {

        final SessionManager sessionManager = getSessionManager();
        if (sessionManager != null) {
            sessionManager.removeAttribute(param);
        }
    }

    /**
     * obtiene el username del usuario logado
     * 
     * @param usuario
     */
    public String getUsuarioLogado() {
        String result = null;
        try {
            final SessionManager session = getSessionManager();
            if (session != null) {
                result = (String) session
                        .getAttribute(Constants.NOMBRE_USUARIO_LOGADO);
            }
        } catch (final Exception e) {
            log.info("Error al obtener el usuario", e);
        }
        return result;
    }

    /**
     * Convenience method to get the Configuration HashMap from the servlet
     * context.
     * 
     * @return the user's populated form from the session
     */
    protected Map getConfiguration() {
        final Map config = (HashMap) getServletContext().getAttribute(
                Constants.CONFIG);

        // so unit tests don't puke when nothing's been set
        if (config == null) {
            return new HashMap();
        }

        return config;
    }

    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }

    // The following methods are used by t:dataTable for sorting.
    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(final String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(final boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * Sort list according to which column has been clicked on.
     * 
     * @param list
     *            the java.util.List to sort
     * @return ordered list
     */
    @SuppressWarnings("unchecked")
    protected List sort(final List list) {
        Comparator comparator = new BeanComparator(sortColumn,
                new NullComparator(nullsAreHigh));
        if (!ascending) {
            comparator = new ReverseComparator(comparator);
        }
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Método encargado de hacer uso del SessionManager para almacenar con un
     * identificador constante el WindowMassage indicado
     * 
     * @param wm
     *            : WindowMessages con los mensajes a mostrar al usuario
     */
    @SuppressWarnings("unchecked")
    public void setWindowMessages(final WindowMessages wm) {
        final SessionManager session = getSessionManager();
        if (session != null) {
            session.setAttribute(BasePage.WM_SESSION_NAME, wm);
        }
    }

    /**
     * Método encargado de hacer uso del SessionManager para obtener el
     * WindowMassage anteriormente almacenado con un identificador constante. En
     * caso de no existir se devolvería un WindowMessages vacío
     * 
     * @return wm: WindowMessages con los mensajes a mostrar al usuario
     */
    @SuppressWarnings("unchecked")
    public WindowMessages getWindowMessages() {
        WindowMessages wm = new WindowMessages();
        final SessionManager session = getSessionManager();

        if (session != null
                && session.getAttribute(BasePage.WM_SESSION_NAME) != null) {
            wm = (WindowMessages) session
                    .getAttribute(BasePage.WM_SESSION_NAME);
        }

        return wm;
    }

    /**
     * Método encargado de eliminar ded session el posible objeto WindowMassage
     */
    public String getClearWindowMessages() {
        final SessionManager session = getSessionManager();
        if (session != null) {
            session.removeAttribute(BasePage.WM_SESSION_NAME);
        }

        return "";
    }

    /**
     * Método encargado de obtener la url del subsistema actual
     */
    protected String getUrlSubsistema() {
        final StringBuilder sb = new StringBuilder();
        String subsistema = "";

        try {
            subsistema = getFacesContext().getExternalContext()
                    .getRequestContextPath();
            sb.append(getConfSubsistema());
            sb.append(subsistema);
        } catch (final Exception e) {
            log.error(e.getMessage());
        }

        return sb.toString();
    }

    /**
     * Método encargado de obtener la url del subsistema actual a partir de una
     * url base
     */
    protected String getUrlSubsistema(final String url) {
        final StringBuilder sb = new StringBuilder();

        try {
            sb.append(getConfSubsistema());
            sb.append(url);
        } catch (final Exception e) {
            log.error(e.getMessage());
        }

        return sb.toString();
    }

    /**
     * Método encargado de el servidor y puerto del subsistema actual
     */
    protected String getConfSubsistema() {
        final StringBuilder sb = new StringBuilder();

        try {
            final String httpProtocol = UserWatchdogPhaseListener
                    .getHttpProtocol();
            sb.append(httpProtocol).append("://").append(
                    getRequest().getServerName()).append(":").append(
                    getRequest().getServerPort());
        } catch (final Exception e) {
            log.error(e.getMessage());
        }

        return sb.toString();
    }

    /**
     * Método que comprueba si el usuario ha seleccionado la opcion de marcar
     * todos los elementos de todas las paginas.
     * 
     * @param idListado
     *            , identificador del listado a comprobar.
     * @return Boolean, true si estan todos marcados, false en caso contrario.
     */
    public Boolean todosMarcados(final String idListado) {
        final SessionManager sesion = getSessionManager();
        Boolean todosSeleccionados = Boolean.FALSE;
        if (sesion != null) {
            if (sesion.getAttribute(BasePage.PREFIJOALL + idListado) != null) {
                if ("true".equalsIgnoreCase(sesion.getAttribute(
                        BasePage.PREFIJOALL + idListado).toString())) {
                    todosSeleccionados = Boolean.TRUE;
                } else {
                    todosSeleccionados = Boolean.FALSE;
                }
            }
        }

        return todosSeleccionados;
    }

    /**
     * Método que devuelve un listado de todos los identificadores de los
     * elementos seleccionados en un listado.
     * 
     * @param idListado
     *            , identificador del listado a comprobar.
     * @return listado de ids de los elementos seleccionados.
     */
    public List<String> obtenerMarcados(final String idListado) {
        final SessionManager sesion = getSessionManager();
        List<String> seleccionados = new ArrayList<String>();
        if (sesion != null) {
            if (sesion.getAttribute(BasePage.PREFIJO + idListado) != null) {
                seleccionados = (ArrayList) sesion
                        .getAttribute(BasePage.PREFIJO + idListado);
            }
        }

        return seleccionados;
    }

    /**
     * Método encargado de ejecutar el metodo indicado del objeto indicado
     * 
     * @retrun Object resultado del metodo
     */
    public Object ejecutarMetodoReflexion(String strMetodo, Object objeto) {
        final Class[] vacio = new Class[0];
        final Object[] vacioObjetos = new Object[0];

        return ejecutarMetodoReflexion(strMetodo, objeto, vacio, vacioObjetos);
    }

    /**
     * Método encargado de ejecutar el metodo indicado del objeto indicado con
     * los argumentos indicados
     * 
     * @retrun Object resultado del metodo
     */
    public Object ejecutarMetodoReflexion(String strMetodo, Object objeto,
            Class[] claseArgumentos, Object[] argumentos) {
        Object res = null;
        Method metodo = null;

        try {
            metodo = objeto.getClass().getMethod(strMetodo, claseArgumentos);
            res = metodo.invoke(objeto, argumentos);
        } catch (final Exception e) {
            log.info("Error al obtener al ejecutar el metodo " + strMetodo, e);
            res = null;
        }

        return res;
    }

    /**
     * método usado para recuperar la fila seleccionada del componente de
     * sandbox: <b>selectOneRow</b><br>
     * <code>&lt;s:selectOneRow groupName="groupName" id="hugo" value="#{bean.selectedRowIndex}" disable="#{bean.form.readMode}"/&gt;</code>
     * 
     * @param groupName
     * @return
     */
    protected Integer getSelectedOneRow(String groupName) {
        Integer selectedRow = -1;
        final String result = getRequestParam(groupName);
        if (result != null) {
            final StringTokenizer st = new StringTokenizer(result, ":");

            if (st.countTokens() == 4) {
                final String htmlForm = st.nextToken();
                final String datatableId = st.nextToken();

                final String selectedRowStr = st.nextToken();
                if (selectedRowStr != null) {
                    selectedRow = Integer.parseInt(selectedRowStr);
                }

                final String radioId = st.nextToken();
            }
        }

        return selectedRow;
    }

    /**
     * @param jsfPage
     *            Por ejemplo: /pages/iaa/autorizar/autorizar.xhtml
     * @return /pages/iaa/autorizar/autorizar.html
     */
    protected String jsfPageToUrl(String jsfPage) {
        String result = null;

        if (jsfPage != null && jsfPage.endsWith(JSF_PAGE_EXTENSION)) {
            /**
             * suponemos que no existen páginas JSF del tipo page.xhtml.xhtml ;)
             */
            result = jsfPage.replaceAll(JSF_PAGE_EXTENSION, JSF_URL_EXTENSION);
        }

        return result;
    }

    protected String getPage() {
        final StringBuilder url = new StringBuilder(getFacesContext()
                .getExternalContext().getRequestContextPath());
        url.append(getFacesContext().getViewRoot().getViewId());
        return url.toString();
    }

    protected String getUrl() {
        return jsfPageToUrl(getPage());
    }

    protected void insertarHistorial(MenuItem historial) {
        if (historial != null) {
            List<MenuItem> historiales = (List<MenuItem>) getSessionManagerParam(Constants.MENU_HISTORIAL_LIBRAE);
            if (historiales == null) {
                historiales = new ArrayList<MenuItem>();
            }
            historiales.add(historial);
            setSessionManagerParam(Constants.MENU_HISTORIAL_LIBRAE, historiales);
        }
    }

    public String getMensajeExito() {
        final SessionManager session = getSessionManager();
        if (session != null
                && session.getAttribute(Constants.MENSAJE_EXITO_GUARDAR) != null) {
            mensajeExito = (String) session
                    .getAttribute(Constants.MENSAJE_EXITO_GUARDAR);
            session.removeAttribute(Constants.MENSAJE_EXITO_GUARDAR);
        }
        return mensajeExito;
    }

    public void setMensajeExito(final String mensajeExito, String propertiFile) {
        final SessionManager session = getSessionManager();
        if (session != null) {
            session.setAttribute(Constants.MENSAJE_EXITO_GUARDAR, MensajesError
                    .get(propertiFile, mensajeExito));
        }
        this.mensajeExito = mensajeExito;
    }

}
