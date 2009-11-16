package es.uma.crudframework;

/**
 * Constant values used throughout the application.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class Constants {

    /**
     * Se protege el constructor, ya que esta clase no se debe instanciar.
     */
    protected Constants() {
        super();
    }

    // ~ Static fields/initializers
    // =============================================

    /**
     * The name of the ResourceBundle used in this application.
     */
    public static final String  BUNDLE_KEY                  = "ApplicationResources";

    /**
     * File separator from System properties.
     */
    public static final String  FILE_SEP                    = System
                                                                    .getProperty("file.separator");

    /**
     * User home from System properties.
     */
    public static final String  USER_HOME                   = System
                                                                    .getProperty("user.home")
                                                                    + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String  CONFIG                      = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting
     * this key to the same one that Struts uses, we get synchronization in
     * Struts w/o having to do extra work or have two session-level variables.
     */
    public static final String  PREFERRED_LOCALE_KEY        = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored.
     */
    public static final String  USER_KEY                    = "userForm";

    /**
     * The request scope attribute that holds the user list.
     */
    public static final String  USER_LIST                   = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user.
     */
    public static final String  REGISTERED                  = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml.
     */
    public static final String  ADMIN_ROLE                  = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml.
     */
    public static final String  USER_ROLE                   = "ROLE_USER";

    /**
     * The name of the user's role list, a request-scoped attribute when
     * adding/editing a user.
     */
    public static final String  USER_ROLES                  = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute when
     * adding/editing a user.
     */
    public static final String  AVAILABLE_ROLES             = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String  CSS_THEME                   = "csstheme";

    /**
     * Constantes para administracion y configuracion.
     */
    public static final String  MENU_LIBRAE                 = "menu_librae";

    /**
     * request params.
     */
    public static final String  URL_BACK_REQUEST_PARAM      = "url_back";

    /**
     * request params.
     */
    public static final String  REDIRECTED_REQUEST_PARAM    = "redirected";

    /**
     * session params.
     */
    public static final String  USUARIO_SESSION_PARAM       = "librae_logged_user_in_cas";

    /**
     * Cadena constante con el valor true.
     */
    public static final String  BOOLEAN_TRUE_VALUE          = "true";

    /**
     * Cadena constante con el valor false.
     */
    public static final String  BOOLEAN_FALSE_VALUE         = "false";

    /**
     * Cadena que contiene el valor unchecked
     */
    public static final String  SUPPRESS_WARNINGS_UNCHECKED = "unchecked";

    /**
     * Cadena que contiene el valor ascending
     */
    public static final String  ORDER_ASCENDING             = "ascending";

    /**
     * Cadena que contiene el valor sortColumn
     */
    public static final String  SORT_COLUMN                 = "sortColumn";

    /**
     * Cadena que contiene el valor para Locale español
     */
    public static final String  ESPANYOL                    = "es";

    /**
     * Cadena que contiene el valor para Locale España
     */
    public static final String  ESPANYIA                    = "ES";

    public static final String  SORTCOLUMN                  = "sortColumn";
    public static final String  ASCENDING                   = "ascending";

    public static final String  PAGESIZE                    = "pageSize";
    public static final String  CURRENTPAGE                 = "currentPage";
    public static final String  RESULTADO_BUSQUEDA          = "resultadoBusqueda";

    public static final String  PRESTAMO_DOMICILIO          = "DO";

    /** formato de fecha por defecto. */
    public static final String  FORMATO_FECHA               = "dd/MM/yyyy";

    /** Estado del lector suspendido. */
    public static final Integer LECTOR_SUSPENDIDO           = 4;
    /** Estado del lector bloqueado. */
    public static final Integer LECTOR_BLOQUEADO            = 5;
    /** Estado del lector caducado. */
    public static final Integer LECTOR_CADUCADO             = 2;

    /**
     * breadcrumb session params.
     */
    public static final String  BREADCRUMB_SESSION_PARAM    = "librae_breadcrumb";


    /** url de logout*/
    public static final String  URL_LOGOUT                  = "/librae-sso/logout?service=";


    /**
     * calendario session params.
     */
    public static final String  CALENDARIO_SESSION_NAME    = "calendario_session_name";
    
    /**
     * Ancho máximo de columnas
     */
    public static final int COLUMN_DATA_TABLE_MAX_LENGTH = 25;

}
