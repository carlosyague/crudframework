package org.librae.common.webapp.session;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

/**
 * Clase que se encarga de gestionar los objetos que se guardan en la sesion
 * compartida por todos los contextos.
 * 
 * @author srosa
 */
public class SessionManager {

    /**
     * Objeto que se guarda en la sesion compartida y que contiene al resto de
     * objetos.
     */
    private BeanSession         beanSession;

    /**
     * Nombre del objeto que se guarda en sesion.
     */
    private static final String SESSION_NAME       = "org.librae.common.webapp.session.BeanSession";

    /**
     * Prefijo del identificador de la sesion compartida que se añade al ticket
     * de CAS.
     */
    private static final String TICKET_PREFIX      = "sharedSession_";

    /**
     * Nombre de la cookie en la que CAS guarda el ticket.
     */
    private static final String COOKIE_CAS_NAME    = "JSESSIONID";

    /**
     * Nombre de la cookie en la que nuestra aplicacion guardara el
     * identificador de session compartida.
     */
    private static final String COOKIE_LIBRAE_NAME = "LIBRAEID";

    /**
     * Para tests.
     */
    private static final String TICKET_SIMULADO    = "ST-6-tDd9cVoiaDtQuge4f1v1";

    //===========================METODOS========================================

    /**
     * Método encargado de inicializar SessionManager guardando la sesión
     * indicada en un lugar accesible para el contexto indicado.
     * 
     * @param req
     *            : HttpServletRequest actual
     * @param res
     *            : HttpServletResponse actual
     * @param contexto
     *            : contexto que realiza la inicializaci?n
     */
    public static void init(final HttpServletRequest req,
            final HttpServletResponse res, final ServletContext contexto) {
        final HttpSession sesion = req.getSession();
        if (sesion != null && contexto != null
                && contexto.getContext("/") != null) {
            // 1. Creamos el identificador unico de la sesion compartida
            sesion.removeAttribute(SESSION_NAME);
            final String ticket = crearIdentificador(req, res);
            if (!"".equals(ticket)) {
                // 2. guardamos la session en el contexto global con el
                // identificador
                contexto.getContext("/").setAttribute(ticket, sesion);
            }
        }

        // [SessionManager.init: Fin del metodo]");
    }

    /**
     * Constructor.
     * 
     * @param session
     *            Sesión HTTP.
     */
    public SessionManager(final HttpSession session) {
        prepararObjeto(session);
    }

    /**
     * Método encargado de obtener una instancia de SessionManager con la sesión
     * guardada en la inicialización.
     * 
     * @param contexto
     *            : contexto que realiza la petici?n
     * @param req
     *            : HttpServletRequest del contexto que realiza la petición
     * @return the sessionManager
     */
    public static SessionManager getSessionManager(
            final HttpServletRequest req, final ServletContext contexto) {

        SessionManager res = null;

        if (contexto != null && contexto.getContext("/") != null) {
            // 1. obtego el el identificador unico de la sesion compartida
            final String ticket = obtenerTicket(req);
            if (!"".equals(ticket)
                    && (contexto.getContext("/") != null && contexto
                            .getContext("/").getAttribute(ticket) != null)) {
                // 2. obtengo la sesion compartida
                log.debug("se busca la sesion con id..." + ticket);
                final HttpSession sesion = (HttpSession) contexto.getContext(
                        "/").getAttribute(ticket);
                res = new SessionManager(sesion);
            }
        }

        return res;
    }

    /**
     * Inicializa el objeto que se guardará en la sesión.
     * 
     * @param session
     *            Sesión HTTP.
     */
    private void prepararObjeto(final HttpSession session) {
        if ((session != null) && (!sesionCaducada(session))) {
            beanSession = (BeanSession) session.getAttribute(SESSION_NAME);
            if (beanSession == null) {
                beanSession = new BeanSession();
                session.setAttribute(SESSION_NAME, beanSession);
            }
        }
    }

    /**
     * Pone un nuevo objeto perecedero en sesion.
     * 
     * @param name
     *            Nombre del objeto.
     * @param o
     *            Objeto.
     */
    public final void setAttribute(final String name, final Object o) {
        setAttribute(name, o, true);
    }

    /**
     * Pone un nuevo objeto en sesion.
     * 
     * @param name
     *            Nombre del objeto.
     * @param o
     *            Objeto.
     * @param perecedero
     *            indica si el atributo es perecedero o no
     */
    public final void setAttribute(final String name, final Object o,
            final boolean perecedero) {
        beanSession.setAttribute(name, o, perecedero);
    }

    /**
     * Pone un nuevo objeto en sesion.
     * 
     * @param name
     *            Nombre del objeto.
     * @param o
     *            Objeto.
     * @param perecedero
     *            indica si el atributo es perecedero o no
     */
    public final void setAttributeSerializado(final String name,
            final Object o, final boolean perecedero) {
        beanSession.setAttribute(name, serializar(o), perecedero);
    }

    /**
     * Obtiene un objeto de la sesion.
     * 
     * @param name
     *            Nombre del objeto.
     * @return Objeto.
     */
    public final Object getAttribute(final String name) {
        if (beanSession != null) {
            return beanSession.getAttribute(name);
        } else {
            return null;
        }
    }

    /**
     * Obtiene un objeto de la sesion en forma de xml.
     * 
     * @param name
     *            Nombre del objeto.
     * @return Objeto.
     */
    public final Object getAttributeSerializado(final String name) {
        Object result = null;
        final Object obj = beanSession.getAttribute(name);

        if (obj != null) {
            result = deserializar(obj.toString());
        }

        return result;
    }

    /**
     * Elimina un objeto de la sesion.
     * 
     * @param name
     *            Nombre del objeto.
     */
    public final void removeAttribute(final String name) {
        if (beanSession!=null) {
            beanSession.removeAttribute(name);
        }
    }

    /**
     * Limpia el objeto mfSession.
     */
    public final void clear() {
        beanSession = new BeanSession();
    }

    /**
     * Metodo encargado de obtener el identificador unico de sesion compartida
     * teniendo en cuenta la posible simulación.
     * 
     * @param req
     *            HttpServletRequest
     * @return Ticket o "" si no se ha posido obtener
     */
    private static String obtenerTicket(final HttpServletRequest req) {
        String res = "";
        // Se esta obteniendo una instancia, obtengo el ticket de la
        // cookie
        Cookie ck = getCookie(req, COOKIE_LIBRAE_NAME);
        if (ck == null) {
            ck = getCookie(req, COOKIE_CAS_NAME);
        }
        if (ck != null) {
            res = TICKET_PREFIX + ck.getValue();
        }
        return res;
    }

    protected final static Log log = LogFactory.getLog(SessionManager.class);

    /**
     * Convenience method to get a cookie by name.
     * 
     * @param request
     *            the current request
     * @param name
     *            the name of the cookie to find
     * @return the cookie (if found), null if not found
     */
    private static Cookie getCookie(final HttpServletRequest request,
            final String name) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            log.debug("Se han encontrado cookies, número..." + cookies.length);
        }
        Cookie returnCookie = null;
        if (cookies == null) {
            return returnCookie;
        }

        for (final Cookie thisCookie : cookies) {
            log.debug("Se han encontrado la cookie..." + thisCookie.getName()
                    + ", " + thisCookie.getComment() + ", "
                    + thisCookie.getPath() + ", " + thisCookie.getValue()
                    + ", " + thisCookie.getDomain() + ", "
                    + thisCookie.getMaxAge() + ", " + thisCookie.getVersion());
            if (thisCookie.getName().equals(name)) {
                // cookies with no value do me no good!
                if (!thisCookie.getValue().equals("")) {
                    returnCookie = thisCookie;

                    break;
                }
            }
        }
        if (returnCookie != null) {
            log.debug("Se han encontrado la cookie..." + returnCookie.getName()
                    + ", " + returnCookie.getComment() + ", "
                    + returnCookie.getPath() + ", " + returnCookie.getValue()
                    + ", " + returnCookie.getDomain() + ", "
                    + returnCookie.getMaxAge() + ", "
                    + returnCookie.getVersion());
        }
        return returnCookie;
    }

    /**
     * Metodo encargado de crear el identificador unico de sesion compartida
     * durante la inicializacion del sessionManager.
     * 
     * @param req
     *            HttpServletRequest
     * @param res
     *            HttpServletResponse
     * @return el identificador unico de sesion compartida durante la
     *         inicializacion del sessionManager
     */
    private static String crearIdentificador(final HttpServletRequest req,
            final HttpServletResponse res) {
        final StringBuilder id = new StringBuilder("");
        // 1 obtengo el ticket de CAS o el simulado
        final Cookie ck = getCookie(req, COOKIE_CAS_NAME);
        if (ck != null) {
            id.append(ck.getValue());
            // 2. guardo el identificador en la cookie de la aplicacion
            final Cookie c = new Cookie(COOKIE_LIBRAE_NAME, id.toString());
            c.setMaxAge(-1);
            c.setPath("/");
            res.addCookie(c);
            try {
                final PrintWriter out = res.getWriter();
            } catch (final Exception e) {
                // ignoramos la excepcion solo queremos refrescar response
            }
            id.insert(0, TICKET_PREFIX);
        }

        return id.toString();
    }

    /**
     * Metodo encargado de serializar el objeto indicado a un xml.
     * 
     * @param o
     *            objeto a serializar
     * @return xml resultante de serializar el objeto.
     */
    private String serializar(final Object o) {
        String strXML = "";
        final XStream xs = new XStream();
        final StringWriter sw = new StringWriter();
        xs.marshal(o, new CompactWriter(sw));
        strXML = sw.toString();
        return strXML;
    }

    /**
     * Metodo encargado de deserializar el string indicado a object.
     * 
     * @param s
     *            Cadena que contiene el XML para deserializar y obtener el
     *            objeto.
     * @return objeto resultante de deserializar un XML
     */
    private Object deserializar(final String s) {
        final XStream xstream = new XStream();
        return xstream.fromXML(s);
    }

    /**
     * Metodo encargado de invalidar la session actual
     * 
     * @param contexto
     *            : contexto que realiza la petici?n
     * @param req
     *            : HttpServletRequest del contexto que realiza la petición
     */
    public static void invalidate(final HttpServletRequest req,
            final ServletContext contexto) {
        if (contexto != null && contexto.getContext("/") != null) {
            final String ticket = obtenerTicket(req);
            if (!"".equals(ticket)) {
                contexto.getContext("/").removeAttribute(ticket);
            }
            final Cookie cookie = getCookie(req, COOKIE_LIBRAE_NAME);
            cookie.setMaxAge(0);
        }
    }

    /**
     * Método encargado de comprobra si la sesion ha caducado, y en tal caso
     * Eliminar al usuario de CAS
     * 
     * @param session
     */
    public static boolean sesionCaducada(HttpSession session) {
        boolean caducada = false;
        try {
            session.getAttribute("");
        } catch (final Exception e) {
            caducada = true;
        }
        return caducada;
    }

    public static HttpSession getSession(final HttpServletRequest req,
            final ServletContext contexto) {
        HttpSession sesion = null;
        if (contexto != null && contexto.getContext("/") != null) {
            // 1. obtego el el identificador unico de la sesion compartida
            final String ticket = obtenerTicket(req);
            if (!"".equals(ticket)
                    && (contexto.getContext("/") != null && contexto
                            .getContext("/").getAttribute(ticket) != null)) {
                // 2. obtengo la sesion compartida

                sesion = (HttpSession) contexto.getContext("/").getAttribute(
                        ticket);
            }
        }

        return sesion;
    }
}
