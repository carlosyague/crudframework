package org.librae.common;

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
    public static final String BUNDLE_KEY                               = "org.librae.common.exception.messages";

    /**
     * File separator from System properties.
     */
    public static final String FILE_SEP                                 = System
                                                                                .getProperty("file.separator");

    /**
     * User home from System properties.
     */
    public static final String USER_HOME                                = System
                                                                                .getProperty("user.home")
                                                                                + Constants.FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG                                   = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting
     * this key to the same one that Struts uses, we get synchronization in
     * Struts w/o having to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY                     = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored.
     */
    public static final String USER_KEY                                 = "userForm";

    /**
     * The request scope attribute that holds the user list.
     */
    public static final String USER_LIST                                = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user.
     */
    public static final String REGISTERED                               = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml.
     */
    public static final String ADMIN_ROLE                               = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml.
     */
    public static final String USER_ROLE                                = "ROLE_USER";

    /**
     * The name of the user's role list, a request-scoped attribute when
     * adding/editing a user.
     */
    public static final String USER_ROLES                               = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute when
     * adding/editing a user.
     */
    public static final String AVAILABLE_ROLES                          = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME                                = "csstheme";

    /**
     * Menu de librae.
     */
    public static final String MENU_LIBRAE                              = "menu_librae";

    /**
     * Menu para los favoritos de librae.
     */
    public static final String MENU_FAVORITO_LIBRAE                     = "menu_librae_favoritos";

    /**
     * Menu para los historiales de librae.
     */
    public static final String MENU_HISTORIAL_LIBRAE                    = "menu_librae_historial";

    /**
     * request params.
     */
    public static final String URL_BACK_REQUEST_PARAM                   = "url_back";

    /**
     * request params.
     */
    public static final String REDIRECTED_REQUEST_PARAM                 = "redirected";

    /**
     * session params.
     */
    public static final String USUARIO_SESSION_PARAM                    = "librae_logged_user_in_cas";
    public static final String LIBRAE_USUARIO_SESSION_PARAM             = "libraeUser_logado";

    /**
     * Cadena constante con el valor true.
     */
    public static final String BOOLEAN_TRUE_VALUE                       = "true";

    /**
     * Cadena constante con el valor false.
     */
    public static final String BOOLEAN_FALSE_VALUE                      = "false";

    /**
     * Cadena que contiene el valor unchecked
     */
    public static final String SUPPRESS_WARNINGS_UNCHECKED              = "unchecked";

    /**
     * Cadena que contiene el valor deprecation
     */
    public static final String SUPPRESS_WARNINGS_DEPRECATION            = "deprecation";

    /**
     * Cadena que contiene el valor ascending
     */
    public static final String ORDER_ASCENDING                          = "ascending";

    /**
     * Cadena que contiene el valor sortColumn
     */
    public static final String SORT_COLUMN                              = "sortColumn";

    /**
     * Cadena que contiene el valor para Locale español
     */
    public static final String ESPANYOL                                 = "es";

    /**
     * Cadena que contiene el valor para Locale España
     */
    public static final String ESPANYIA                                 = "ES";

    public static final String SORTCOLUMN                               = "sortColumn";
    public static final String ASCENDING                                = "ascending";

    public static final String PAGESIZE                                 = "pageSize";
    public static final String CURRENTPAGE                              = "currentPage";
    public static final String RESULTADO_BUSQUEDA                       = "resultadoBusqueda";

    /** formato de fecha por defecto. */
    public static final String FORMATO_FECHA                            = "dd/MM/yyyy";

    /**
     * funciones HQL
     */
    public static final String HQL_FUNCTION_YEAR                        = "year";
    public static final String HQL_FUNCTION_MONTH                       = "month";
    public static final String HQL_FUNCTION_DAY                         = "day";

    /**
     * Texto descriptivo del estado del lector: Activo, Bloqueado, Suspendido,
     * Pendiente Validar Carnet Online /** Estado del lector suspendido.
     */
    public static final String LECTOR_SUSPENDIDO                        = "Suspendido";
    /** Estado del lector suspendido. */
    public static final String LECTOR_ESTADO_SUSPENDIDO                 = "S";
    /** Estado del lector bloqueado. */
    public static final String LECTOR_BLOQUEADO                         = "Bloqueado";
    /** Estado del lector caducado. */
    public static final String LECTOR_CADUCADO                          = "C";
    /** Estado del lector activo. */
    public static final String LECTOR_ACTIVO                            = "Activo";
    /** Estado del lector activo. */
    public static final String LECTOR_ESTADO_ACTIVO                     = "A";
    /** Estado del lector activo. */
    public static final String LECTOR_PENDIENTE_CARNET_ONLINE           = "Pendiente Validar Carnet Online";

    /**
     * breadcrumb session params.
     */
    public static final String BREADCRUMB_SESSION_PARAM                 = "librae_breadcrumb";

    /** url de logout */
    public static final String URL_LOGOUT                               = "/librae-sso/logout?service=";

    /** url de autorizacion */
    public static final String URL_AUTORIZAR                            = "/librae-adminconfig/pages/iaa/autorizar/autorizar.html";

    /** url de la imagen del menu */
    public static final String URL_IMG_MENU                             = "/librae-adminconfig/images/submenu_arrow01.gif";

    /**
     * calendario session params.
     */
    public static final String CALENDARIO_SESSION_NAME                  = "calendario_session_name";

    /**
     * tipo lector session params.
     */
    public static final String TIPO_LECTOR_SESSION_NAME                 = "tipo_lector_session_name";

    /**
     * biblioteca session params.
     */
    public static final String EJEMPLAR_SESSION_NAME                    = "ejemplar_session_name";
    /**
     * biblioteca session params.
     */
    public static final String REGISTRO_SESSION_NAME                    = "registro_session_name";
    /**
     * biblioteca session params.
     */
    public static final String BIBLIOTECA_SESSION_NAME                  = "biblioteca_session_name";

    /**
     * politicaPrestamoDom session params.
     */
    public static final String POL_PREST_DOM_SESSION_NAME               = "polPrestDom_session_name";

    /**
     * politicaReserva session params.
     */
    public static final String POL_RESERVA_SESSION_NAME                 = "polReserva_session_name";

    /**
     * politicaReserva session params.
     */
    public static final String RESERVA_SESSION_NAME                     = "reserva_session_name";

    /**
     * AsocPoliticaPrestamoDom session params.
     */
    public static final String ASOC_POL_PREST_DOM_SESSION_NAME          = "asocPolPrestDom_session_name";

    /**
     * Consorcio session params.
     */
    public static final String CONSORCIO_SESSION_NAME                   = "consorcio_session_name";

    /**
     * Tipo Ejemplar session params.
     */
    public static final String TIPO_EJEMPLAR_SESSION_NAME               = "tipoejemplar_session_name";

    /**
     * PeticionPET session params.
     */
    public static final String PETICION_PET_SESSION_NAME                = "peticionPET_session_name";

    /**
     * PeticionPIB session params.
     */
    public static final String PETICION_PIB_SESSION_NAME                = "peticionPIB_session_name";

    /**
     * PeticionPIB tab session params.
     */
    public static final String PETICION_PIB_TAB_SESSION_NAME            = "peticionPIB_tab_session_name";

    /**
     * AccionPrestamoPIB session params
     */
    public static final String ACCION_PRESTAMO_PIB_SESSION_NAME         = "accionPrestamoPIB_session_name";

    /**
     * AccionPrestamoPIB session params
     */
    public static final String EVENTO_PIB_SESSION_NAME                  = "eventoPIB_session_name";

    /**
     * BibliotecaConsorcio session params.
     */
    public static final String BIBLIOTECA_CONSORCIO_SESSION_NAME        = "bibliotecaConsorcio_session_name";

    /**
     * AsocPoliticaReserva session params.
     */
    public static final String ASOC_POL_RESERVA_SESSION_NAME            = "asocPolReserva_session_name";

    /**
     * rol session params.
     */
    public static final String ROL_SESSION_NAME                         = "rol_session_name";

    /**
     * biblioteca para el horario session params.
     */
    public static final String BIBLIOTECA_HORARIO_SESSION_NAME          = "biblioteca_hor_session_name";

    /**
     * horario session params.
     */
    public static final String HORARIO_SESSION_NAME                     = "horario_session_name";

    /**
     * direccion session params.
     */
    public static final String DIRECCION_SESSION_NAME                   = "direccion_session_name";

    /**
     *ID de la biblioteca consorcio session
     */
    public static final String ID_BIBLIOTECA_SESSION                    = "id_biblioteca_session_name";

    /**
     * horario session params.
     */
    public static final String HORARIOINT_SESSION_NAME                  = "horario_int_session_name";

    /**
     * PrestamoDomicilio session params.
     */
    public static final String PRESTAMODOM_SESSION_NAME                 = "prestamoDom_session_name";

    /**
     * PrestamoDomicilio - Listado Prestar session params.
     */
    public static final String PRESTAMODOM_LISTADO_PRESTAR_SESSION_NAME = "listadoPrestar";

    /**
     * Prestamo session params.
     */
    public static final String PRESTAMO_SESSION_NAME                    = "prestamo_session_name";

    /**
     * TipoLector de Prestamo session params.
     */
    public static final String PRESTAMO_TIPO_LECTOR_SESSION_NAME        = "prestamo_tipoLector_session_name";

    /**
     * Usuario session params.
     */
    public static final String USUARIO_SESSION_NAME                     = "usuario_session_name";

    /**
     * Lector session params
     */
    public static final String LECTOR_SESSION_NAME                      = "lector_session_name";

    /**
     * Catalogo session params.
     */
    public static final String CATALOGO_SESSION_NAME                    = "catalogo_session_name";

    /**
     * Codigo session params.
     */
    public static final String CODIGO_SESSION_NAME                      = "codigo_session_name";

    /**
     * Listado de horarios session params.
     */
    public static final String LISTADO_HORARIOS_ACT                     = "horariosInt";

    /**
     * Servicio NCIP session params.
     */
    public static final String NCIP_SERVICIO_SESSION_NAME               = "ncip_servicio_session_name";

    /**
     *ID de la biblioteca consorcio session
     */
    public static final String ID_BIBLIOTECA_CONSORCIO_SESSION          = "id_bibl_consorcio_session_name";

    /**
     * ID_BIBLIOTECA_CONSORCIO_RED_SESSION para el prestamo en red
     */
    public static final String ID_BIBLIOTECA_CONSORCIO_RED_SESSION      = "id_bibl_consorcio_red_session_name";

    /**
     * Listado de serviciosNCIP session params.
     */
    public static final String LISTADO_SERVICIO_NCIP_SESSION            = "ncip_servicio_session_listado";

    /**
     * Formulario de prestamo consorcio
     */
    public static final String PRESTAMO_CONSORCIO_SESSION               = "prestamoConsorcio_session_name";

    /**
     * ID de consorcio session
     */
    public static final String ID_CONSORCIO_SESSION                     = "id_consorcio_session_name";

    /**
     * ID del consorcio al crear una biblioteca consorciada
     */
    public static final String BIB_CONSORCIO_ID_CREATE_SESSION_NAME     = "id_bib_create_consorcio_session_name";

    /**
     * consorcio session
     */
    public static final String CONSORCIO_SESSION                        = "consorcio_session_name";

    /**
     * Procesos params.
     */
    public static final String PROCESOS_TABBED_SESSION_NAME             = "procesos_tabbed_session_name";

    /**
     * Listado de procesos params.
     */
    public static final String LISTADO_PROCESOS_SESSION_NAME            = "listado_procesos_session_name";

    /**
     * Listado de ejecuciones params.
     */
    public static final String LISTADO_EJECUCIONES_SESSION_NAME         = "listado_ejecuciones_session_name";

    /**
     * COMPARADOR_CUANTITATIVO
     */
    public static final String COMPARADOR_CUNATITATIVO                  = "COMPARADOR_CUNATITATIVO";

    /**
     * COMPARADOR_CUNATITATIVO
     */
    public static final String MAYOR_O_IGUAL                            = "0";
    /**
     * COMPARADOR_CUNATITATIVO
     */
    public static final String MENOR_O_IGUAL                            = "1";
    /**
     * COMPARADOR_CUNATITATIVO
     */
    public static final String IGUAL                                    = "2";
    /**
     * COMPARADOR_CUNATITATIVO
     */
    public static final String MAYOR                                    = "3";
    /**
     * COMPARADOR_CUNATITATIVO
     */
    public static final String MENOR                                    = "4";

    public static final String TIPO_CODIGO_BIBLIOTECA                   = "B";
    public static final String TIPO_CODIGO_LECTOR                       = "L";
    public static final String TIPO_CODIGO_EJEMPLAR                     = "E";

    public static final String TIPO_BIBLIOTECA_GRUPO                    = "G";
    public static final String TIPO_BIBLIOTECA_BIBLIOTECA               = "B";
    public static final String TIPO_BIBLIOTECA_SUCURSAL                 = "S";

    public static final String TIPO_PRESTAMO_DOMICILIO                  = "DO";
    public static final String TIPO_PRESTAMO_SALA                       = "SA";
    public static final String TIPO_PRESTAMO_SEGURIDAD                  = "SE";
    public static final String TIPO_PRESTAMO_RED                        = "RE";
    public static final String TIPO_PRESTAMO_INTERBIBLIOTECARIO         = "PIB";

    public static final String TIPO_RESERVA_EJEMPLAR                    = "E";
    public static final String TIPO_RESERVA_REGISTRO                    = "R";

    public static final String ESTADO_RESERVA_ACTIVA                    = "A";
    public static final String ESTADO_RESERVA_NO_ACTIVA                 = "N";
    public static final String ESTADO_RESERVA_EXPIRADA                  = "E";
    public static final String ESTADO_RESERVA_PRESTADO                  = "P";
    public static final String ESTADO_RESERVA_DEVUELTO                  = "D";

    /**
     * Constantes TipoIdentificacion
     */
    public static final String TIPO_IDENTIFICACION_PASAPORTE            = "Pasaporte";
    public static final String TIPO_IDENTIFICACION_NIE                  = "NIE";
    public static final String TIPO_IDENTIFICACION_NIF                  = "NIF";
    public static final String TIPO_IDENTIFICACION_CIF                  = "CIF";

    /**
     * Style Classes: Estados tabs - Préstamos sobrepasados
     */
    public static final String TAB_STYLE_CLASS_PRESTAMOS_SOBREPASADOS   = "rojo";

    public static final String URL_BACK_MOSTRAR_BIBLIOTECA              = "urlBack_Session_MostrarBibliiteca";
    public static final String URL_BACK_MOSTRAR_LECTOR                  = "urlBack_Session_MostrarLector";
    public static final String URL_BACK_MOSTRAR_EJEMPLAR                = "urlBack_Session_MostrarEjemplar";
    public static final String URL_BACK_MOSTRAR_REGISTRO                = "urlBack_Session_MostrarRegistro";

    public static final String PERMISO_PRESTAMO_DOM                     = "CIR_Prestamo_Domicilio";
    public static final String PERMISO_PRESTAMO_SALA                    = "CIR_Préstamo_Sala";

    /**
     * Kettle.
     */
    public static final String KETTLE_BUILD_SUCCESFUL                   = "BUILD SUCCESFUL";

    /**
     * Contexto.
     */
    public static final String CONTEXTO_CODIGO_BIBLIOTECA               = "contexto_codigo_biblioteca";
    public static final String CONTEXTO_CODIGO_TIPO_EJEMPLAR            = "contexto_codigo_tipo_ejemplar";
    public static final String CONTEXTO_NUMERO_LECTOR                   = "contexto_numero_lector";
    public static final String CONTEXTO_NOMBRE_LECTOR                   = "contexto_nombre_lector";
    public static final String CONTEXTO_AUTOR_REGISTRO                  = "contexto_autor_registro";
    public static final String CONTEXTO_TITULO_REGISTRO                 = "contexto_titulo_registro";
    public static final String CONTEXTO_ISBN_REGISTRO                   = "contexto_isbn_registro";
    public static final String CONTEXTO_CODIGO_BARRAS_EJEMPLAR          = "contexto_codigo_barras_ejemplar";
    public static final String CONTEXTO_SIGNATURA_EJEMPLAR              = "contexto_signatura_ejemplar";
    public static final String CONTEXTO_AUTORIDAD_AUTORIDAD             = "contexto_autoridad_autoridad";
    public static final String CONTEXTO_CODIGO_ROL                      = "contexto_codigo_rol";
    public static final String CONTEXTO_CODIGO_CATALOGO                 = "contexto_codigo_catalogo";
    public static final String CONTEXTO_CODIGO_USUARIO                  = "contexto_codigo_usuario";
    public static final String CONTEXTO_CODIGO_PARAMETRO                = "contexto_codigo_parametro";
    public static final String CONTEXTO_CODIGO_CONSORCIO                = "contexto_codigo_consorcio";
    public static final String CONTEXTO_CODIGO_BIBLIOTECA_CONSORCIADA   = "contexto_codigo_biblioteca_consorciada";
    public static final String CONTEXTO_CODIGO_POLITCA_PRESTAMO         = "contexto_codigo_prestamo";
    public static final String CONTEXTO_CODIGO_POLITCA_RESERVA          = "contexto_codigo_reserva";
    public static final String CONTEXTO_ID_PETICION                     = "contexto_id_peticion";
    public static final String CONTEXTO_ID_RESERVA                      = "contexto_id_reserva";
    public static final String CONTEXTO_NOMBRE_TRANSFORMACION           = "contexto_nombre_transformacion";
    public static final String CONTEXTO_CODIGO_TIPO_LECTOR              = "contexto_codigo_tipo_lector";
    /**
     * Mensaje de exito cuando se ha guardado correctamente.
     */
    public static final String MENSAJE_EXITO_GUARDAR                    = "mensaje_exito_guardar";

    public static final String USER_WATCHDOG_PROPERTIES                 = "org/librae/common/webapp/user_watchdog.properties";

    public static final String MAXIMO_MENU                              = "maximo.menu";

    public static final String BIBLIOTECA_NODO_PRINCIPAL                = "biblioteca.nodo.principal";

    public static final String CONTENT_TYPE_PDF                         = "application/pdf";
    public static final String SUFFIX_PDF                               = ".pdf";
    public static final String CONTENT_TYPE_EXCEL                       = "application/vnd.ms-excel";
    public static final String SUFFIX_EXCEL                             = ".xls";

    public static final String CONFIRMAR_PRESTAMO_SI_DEVUELTO_MISMO_DIA = "devueltoMismoDia";
    public static final String CONFIRMAR_PERMITIR_AUNQUE_RESERVADO      = "aunqueReservado";
    public static final String CONFIRMAR_LECTOR_SUSPENDIDO              = "suspendido";
    public static final String CONFIRMAR_LECTOR_SOBREPASADOS            = "sobrepasados";

    public static final String ORBE_PROPERTIES                          = "orbe.properties";

    public static final String ROL_ORBE_INICIAL_VACIO                   = "rol.orbe.vacio.codigo";

    public static final String HTTP_PROTOCOL                            = "http.protocol";

    public static final String HOST                                     = "host";

    public static final String PORT                                     = "port";

    public static final String URL_AUTORIZAR_ORBE                       = "autorizar.orbe.page.url";

    public static final String ORBE_ACTIVO                              = "orbe.activo";

    public static final String BUSQUEDA_LISTADO                         = "listado";

    public static final String BUSQUEDA_CRITERIOS                       = "criterios";

    public static final String LUNES                                    = "L";
    public static final String MARTES                                   = "M";
    public static final String MIERCOLES                                = "X";
    public static final String JUEVES                                   = "J";
    public static final String VIERNES                                  = "V";
    public static final String SABADO                                   = "S";
    public static final String DOMINGO                                  = "D";

    public static final String PAGE_REFRESH                             = "refresh";
    public static final String PRODUCTOS_WEB_CARACTER_ESPECIAL          = ";";

    public static final String DESCRIPCION_SUCURSAL_ACTUAL              = "descripcion_biblioteca_actual";
    public static final String DESCRIPCION_BIBLIOTECA_ACTUAL            = "descripcion_sucursal_actual";
    public static final String NOMBRE_USUARIO_LOGADO                    = "nombre_usuario_logado";
    public static final String ID_BIBLIOTECA_ACTUAL                     = "id_biblioteca_actual_sesion";
    public static final String CODIGO_BIBLIOTECA_ACTUAL                 = "codigo_biblioteca_actual_sesion";
    public static final String ID_USUARIO_LOGADO                        = "id_usuario_logado";
    public static final String CODIGO_USUARIO_LOGADO                    = "codigo_usuario_logado";
    public static final String TIPO_CODIGO_BIBLIOTECA_ACTUAL            = "codigo_biblioeca_actual";

    public static final String LOGO_BIBLIOTECA_ACTUAL                   = "logo_biblioeca_actual";
    public static final String TIENE_LOGO_BIBLIOTECA_ACTUAL             = "tiene_logo_biblioeca_actual";
    public static final String NOMBRE_INSTALACION                       = "nombre.instalacion";

    public static final String WORKFLOW_GESTIONAR_PRESTAMOS             = "Gestionar el préstamo";

    public static final String BIBLIOTECA_ID_COMUNIDAD              = "biblioteca.id.comunidad";
    public static final String BIBLIOTECA_ID_PAIS                   = "biblioteca.id.pais";
}

