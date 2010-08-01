package org.librae.common.webapp.jsf.listener.user;

import java.io.IOException;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletResponse;

import org.librae.common.Constants;
import org.librae.common.util.Propiedades;
import org.librae.common.webapp.jsf.listener.AbstractPhaseListener;
import org.librae.common.webapp.session.SessionManager;
import org.springframework.security.context.SecurityContextHolder;

/**
 * Redirecciona a la página de cas sino existe el usuario en sesión.
 *
 * @author jcisneros
 */
public class UserWatchdogPhaseListener extends AbstractPhaseListener {

    /**
	 *
	 */
    private static final long   serialVersionUID                        = 1382070208272009039L;

    /**
     * properties & keys
     */
    public static final String  USER_WATCHDOG_PROPERTIES                = "org/librae/common/webapp/user_watchdog.properties";
    public static final String  AUTORIZAR_PAGE_URL_KEY                  = "autorizar.page.url";
    public static final String  AUTORIZAR_PAGE_JSF_KEY                  = "autorizar.page.jsf";
    public static final String  AUTORIZAR_ORBE_PAGE_JSF_KEY             = "autorizar.orbe.page.jsf";
    public static final String  SIMULAR_KEY                             = "simular";
    public static final String  HTTP_PROTOCOL_KEY                       = "http.protocol";
    public static final String  SESSION_COMPARTIDA_ACTIVADA_KEY         = "crossContext.activo";

    /**
     * default values
     */
    public static final boolean SIMULAR_DEFAULT_VALUE                   = false;
    public static final boolean SESSION_COMPARTIDA_ACTIVADA_DEFAULT_VAL = true;
    public static final String  SERVER_NAME_DEFAULT_VALUE               = "localhost";
    public static final int     SERVER_PORT_DEFAULT_VALUE               = 8080;
    public static final String  HTTP_PROTOCOL_DEFAULT_VALUE             = "http";

    /**
     * @param event
     */
    public void afterPhase(final PhaseEvent event) {
        final PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.RESTORE_VIEW)) {
            checkUserObjectOrRedirect(event);
        }
    }

    public void beforePhase(final PhaseEvent event) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * auxiliary methods<br>
     * =================
     */
    /**
     * @param event
     */
    private void checkUserObjectOrRedirect(final PhaseEvent event) {
        try {
            final Propiedades properties = Propiedades
                    .getInstance(USER_WATCHDOG_PROPERTIES);
            final SessionManager session = getSessionManager(event);
            if (session != null) {
                if ((!(isInLogOutPageJsf(event, properties)))
                        && (session.getAttribute(Constants.ID_USUARIO_LOGADO) == null)) {
                    if (!(isInAutorizarPageJsf(event, properties))) {
                        redirectToAutorizacionPage(event, properties);
                    }
                }
            } else {
                redirectToAutorizacionPage(event, properties);
            }
        } catch (final Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * @param event
     * @throws IOException
     * @throws Exception
     * @throws Exception
     */
    private void redirectToAutorizacionPage(final PhaseEvent event,
            final Propiedades properties) throws IOException {

        final HttpServletResponse response = getResponse(event);
        /**
         * http://localhost:8081/librae-procplanificados/index.html
         */
        final String urlBack = getUrlBack(event, properties);
        /**
         * http://localhost:8081/librae-adminconfig/pages/autorizar/autorizar.
         * html?url_back=http://localhost:8081/librae-procplanificados
         */
        final String autorizarPageURL = getAutorizarPageUrl(event, properties,
                urlBack);
        response.sendRedirect(autorizarPageURL);
    }

    /**
     * @param properties
     * @return
     */
    private static String getHttpProtocol(final Propiedades properties) {
        String result = HTTP_PROTOCOL_DEFAULT_VALUE;
        if (properties != null) {
            final String value = properties.getPropiedad(HTTP_PROTOCOL_KEY);

            if (value != null && !value.equals("")) {
                result = value;
            }
        }
        return result;
    }

    /**
     * Obtiene el protocolo Http que tiene definido esta aplicación en el
     * user_watchdog.properties
     *
     * @return
     */
    public static String getHttpProtocol() {
        final Propiedades properties = Propiedades
                .getInstance(USER_WATCHDOG_PROPERTIES);
        return getHttpProtocol(properties);
    }

    /**
     * @param properties
     * @param urlBackContext
     * @return
     */
    private String getUrlBack(final PhaseEvent event,
            final Propiedades properties) {
        final StringBuilder sb = new StringBuilder();
        String serverName = SERVER_NAME_DEFAULT_VALUE;
        int serverPort = SERVER_PORT_DEFAULT_VALUE;

        try {
            serverName = getRequest(event).getServerName();
            serverPort = getRequest(event).getServerPort();
        } catch (final Exception e) {
            log.error(e.getMessage());
        }
        // http://localhost:8081
        final String httpProtocol = getHttpProtocol(properties);
        sb.append(httpProtocol).append("://").append(serverName).append(":")
                .append(serverPort);
        // /librae-mensajeria
        sb.append("/librae-adminconfig");
        // /pages/consultarTareasPendientes.html
        final String jsf = jsfPageToUrl(getCurrentJSFPage(event));
        if (jsf != null) {
            sb.append("/index.html");
        }
        return sb.toString();
    }

    /**
     * Método encargado de el servidor y puerto del subsistema actual
     */
    private String getConfSubsistema(final PhaseEvent event) {
        final StringBuilder sb = new StringBuilder();
        try {
            final Propiedades properties = Propiedades
                    .getInstance(USER_WATCHDOG_PROPERTIES);
            final String httpProtocol = getHttpProtocol(properties);
            sb.append(httpProtocol).append("://").append(
                    getRequest(event).getServerName()).append(":").append(
                    getRequest(event).getServerPort());
        } catch (final Exception e) {
            log.error(e.getMessage());
        }

        return sb.toString();
    }

    /**
     * Método encargado de obtener la url del subsistema actual
     */
    protected String getUrlSubsistema(final PhaseEvent event) {
        final StringBuilder sb = new StringBuilder();
        String subsistema = "";
        try {
            sb.append(getConfSubsistema(event));
            sb.append("/librae-adminconfig");
        } catch (final Exception e) {
            log.error(e.getMessage());
        }
        return sb.toString();
    }

    private static boolean isConfigParam(final Propiedades properties,
            final String key, final boolean defaultValue) {
        boolean result = defaultValue;
        final String value = properties.getPropiedad(key);
        result = (value != null && value
                .equalsIgnoreCase(Constants.BOOLEAN_TRUE_VALUE));
        return result;
    }

    private static boolean isSessionCompartidaActivada(
            final Propiedades properties) {
        return isConfigParam(properties, SESSION_COMPARTIDA_ACTIVADA_KEY,
                SESSION_COMPARTIDA_ACTIVADA_DEFAULT_VAL);
    }

    public static boolean isSessionCompartidaActivada() {
        final Propiedades properties = Propiedades
                .getInstance(USER_WATCHDOG_PROPERTIES);
        return isSessionCompartidaActivada(properties);
    }

    /**
     * Devuelve la página de Autoricación del subsistema de Adim&Config
     *
     * @param event
     * @param properties
     * @param urlBack
     * @param simular
     * @return
     */
    private String getAutorizarPageUrl(PhaseEvent event,
            final Propiedades properties, final String urlBack) {
        final StringBuilder sb = new StringBuilder();
        final String autorizarPageURL = properties
                .getPropiedad(AUTORIZAR_PAGE_URL_KEY);
        sb.append(autorizarPageURL);
        sb.append("?").append(Constants.URL_BACK_REQUEST_PARAM).append("=")
                .append(urlBack);
        return sb.toString();
    }

    /**
     * obtiene la página JSF en la que debemos evitar hacer la redirección
     *
     * @param properties
     * @param urlBack
     * @param simular
     * @return
     */
    private String getAutorizarPageJsf(final Propiedades properties) {
        return properties.getPropiedad(AUTORIZAR_PAGE_JSF_KEY);
    }

    private String getLogOutPageJsf(final Propiedades properties) {
        return "/logOut.html";
    }

    /**
     * obtiene la página JSF en la que debemos evitar hacer la redirección
     *
     * @param properties
     * @param urlBack
     * @param simular
     * @return
     */
    private String getAutorizarOrbePageJsf(final Propiedades properties) {
        return properties.getPropiedad(AUTORIZAR_ORBE_PAGE_JSF_KEY);
    }

    /**
     * Devuelve true si el listener se encuentra en la página de
     * "autorizar usuario"
     *
     * @param event
     * @param properties
     * @return
     */
    private boolean isInAutorizarPageJsf(final PhaseEvent event,
            final Propiedades properties) {
        boolean result = false;
        final String currentJSFPage = getCurrentJSFPage(event);
        final String autorizarPageJsf = getAutorizarPageJsf(properties);
        final String autorizarOrbePageJsf = getAutorizarOrbePageJsf(properties);

        if (currentJSFPage != null && autorizarPageJsf != null) {
            result = currentJSFPage.equalsIgnoreCase(autorizarPageJsf);
            if (!result) {
                result = currentJSFPage.equalsIgnoreCase(autorizarOrbePageJsf);
            }
        }

        return result;
    }

    /**
     * Devuelve true si el listener se encuentra en la página de "logOut"
     *
     * @param event
     * @param properties
     * @return
     */
    private boolean isInLogOutPageJsf(final PhaseEvent event,
            final Propiedades properties) {
        boolean result = false;
        final String currentJSFPage = getCurrentJSFPage(event);
        final String logOutPageJsf = "/logOut.xhtml";
        final String logOutPageParentJsf = "/logOutParent.xhtml";
        if (currentJSFPage != null && logOutPageJsf != null) {
            result = currentJSFPage.equalsIgnoreCase(logOutPageJsf);
        }
        if (!result && currentJSFPage != null && logOutPageParentJsf != null) {
            result = currentJSFPage.equalsIgnoreCase(logOutPageParentJsf);
        }
        return result;
    }
}
