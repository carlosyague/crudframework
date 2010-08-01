package org.librae.common.webapp.action;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.Constants;
import org.librae.common.model.LibraeUser;
import org.librae.common.model.MenuItem;
import org.librae.common.util.Propiedades;
import org.librae.common.webapp.delegate.IMenuDelegate;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.ConstantesBreadCrumb;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;

/**
 * Action-JSF asociado al menu de la aplicacion.
 *
 * @author jcisneros
 */
public class MenuAction extends BasePage implements Serializable {

    protected final Log        log              = LogFactory
                                                        .getLog(MenuAction.class);

    /**
     * Numero de serializacion.
     */
    private static final long  serialVersionUID = -1382876932678358393L;

    /**
     * acceso a la capa delegate
     */
    private IMenuDelegate      delegate;

    public static final String HTTP_PROTOCOL    = "http";

    /**
     * Método encargado de obtener la lista de MenuItem que representan al menú
     * a mostrar y devolver la concatenación del método toString() de cada uno
     * de ellos que obtiene el formato correcto para JSCookMenu 1. Compruebo si
     * en SessionManager hay ya una lista de MenuItem. En tal me quedo con esta.
     * Ir a 3 2. Obtener la lista de MenuItem invocando al método getRaices()
     * del delegate y guardarla en el SessionManager 3. Devolver la
     * concatenación de los toString() de cada elemento de la lista obtenida
     *
     * @return String con el formato correcto para que JSCookMenu
     */
    public String getMenu() {
        String menu = null;
        String favoritos = null;
        final SessionManager sesion = getSessionManager();
        String resultado = null;
        LibraeUser libraeUser = null;
        try {
            if (sesion != null) {
                final StringBuilder salida = new StringBuilder();
                menu = (String) sesion.getAttribute(Constants.MENU_LIBRAE);
                if (menu == null) {
                    libraeUser = getUsusario(sesion);
                    if (libraeUser != null) {
                        menu = delegate.getMenu(libraeUser);
                        sesion.setAttribute(Constants.MENU_LIBRAE, menu);
                    }
                }
                favoritos = (String) sesion
                        .getAttribute(Constants.MENU_FAVORITO_LIBRAE);
                if (favoritos == null) {
                    if (libraeUser == null) {
                        libraeUser = getUsusario(sesion);
                    }
                    libraeUser = getUsusario(sesion);
                    if (libraeUser != null) {
                        favoritos = delegate.getFavoritos(libraeUser);
                        sesion.setAttribute(Constants.MENU_FAVORITO_LIBRAE,
                                favoritos);
                    }
                }
                if (menu != null) {
                    final String menuCortado = menu.substring(0,
                            menu.length() - 1);
                    salida.append(menuCortado);
                    if (favoritos != null) {
                        salida.append(", ");
                        salida.append(favoritos);
                    }
                    salida.append(", ");
                    salida.append(getHistorial(sesion));
                    salida.append("]");
                }
                resultado = salida.toString();
            }
        } catch (final Exception e) {
            log.error("Error al mostrar el menu...", e);
        }
        return resultado;
    }

    private LibraeUser getUsusario(SessionManager sesion) {
        LibraeUser libraeUser = null;
        if (libraeUser == null) {
            libraeUser = (LibraeUser) sesion
                    .getAttribute(Constants.LIBRAE_USUARIO_SESSION_PARAM);
        }
        return libraeUser;
    }

    private String getHistorial(SessionManager sesion) {
        MenuItem menuItem = null;
        final LibraeUser libraeUser = null;
        final List<MenuItem> historiales = (List<MenuItem>) sesion
                .getAttribute(Constants.MENU_HISTORIAL_LIBRAE);
        if (historiales == null) {
            menuItem = new MenuItem("Historial", null, "_self", null, false);
        } else {
            menuItem = new MenuItem("Historial", null, "_self", historiales,
                    false);
        }
        return menuItem.toString();
    }

    /**
     * Método encargado de hacer el logout de la aplicacion del susbsistema
     * actual
     */
    public String getLogOut() {
        try {
            String urlBack = "";
            urlBack = getParameter("service");
            if (urlBack == null || "".equals(urlBack)) {
                urlBack = this.getUrlSubsistema();
            } else {
                urlBack = this.getUrlSubsistema(urlBack);
            }
            invalidateSessionManager();
            SecurityContextHolder.getContext().setAuthentication(null);
            final HttpSession session = getRequest().getSession(false);
            if (session != null) {
                session.invalidate();
            }
            SecurityContextHolder.clearContext();

            getResponse().sendRedirect(Constants.URL_LOGOUT + urlBack);
        } catch (final Exception e) {
            log.error("Error al hacer el logout...", e);
        }

        return null;
    }

    /**
     * Método encargado de obtener el codigo de la sucursal en la que se ha
     * logado el usuario
     */
    public String getSucursal() {
        final StringBuilder res = new StringBuilder();
        try {
            final String nombreBiblioteca = (String) getSessionManagerParam(Constants.DESCRIPCION_SUCURSAL_ACTUAL);
            if (nombreBiblioteca != null) {
                res.append("[ ").append(nombreBiblioteca).append(" ]");
            }
        } catch (final Exception e) {
            log.error("Error al obtener la sucursal...", e);
        }

        return res.toString();
    }

    private LibraeUser getUsusarioSinSesion() {
        LibraeUser libraeUser = null;
        final SessionManager sesion = getSessionManager();
        if ((sesion != null) && (libraeUser == null)) {
            libraeUser = (LibraeUser) sesion
                    .getAttribute(Constants.LIBRAE_USUARIO_SESSION_PARAM);
        }
        return libraeUser;
    }

    /**
     * Método encargado de obtener el nombre de la biblioteca de la sucursal en
     * la que se ha logado el usuario
     */
    public String getNombreBiblioteca() {
        final StringBuilder res = new StringBuilder();
        try {
            final String nombreBiblioteca = (String) getSessionManagerParam(Constants.DESCRIPCION_BIBLIOTECA_ACTUAL);
            if (nombreBiblioteca != null) {
                res.append("[ ").append(nombreBiblioteca).append(" ]");
            }
        } catch (final Exception e) {
            log.error("Error al obtener el nombre de la biblioteca...", e);
        }

        return res.toString();
    }

    /**
     * Método encargado de comporbra si la sucursal en la que se ha logado el
     * usuario tiene logotipo
     */
    public String getBibliotecaConLogo() {
        String res = "false";
        try {
            final Boolean logo = (Boolean) getSessionManagerParam(Constants.TIENE_LOGO_BIBLIOTECA_ACTUAL);
            if (logo != null) {
                res = logo.toString();
            }
        } catch (final Exception e) {
            log.error("Error al comporbar si la biblioetca tiene logo...", e);
            res = "false";
        }

        return res;
    }

    /**
     * Método encargado de obtener la imagen del logotipo de la sucursal en la
     * que se ha logado el usuario
     */
    public void getLogoBiblioteca(final OutputStream output, Object objeto) {
        try {
            final Object logo = getSessionManagerParam(Constants.LOGO_BIBLIOTECA_ACTUAL);
            if (logo != null) {
                final byte[] file = (byte[]) logo;
                FileCopyUtils.copy(file, output);
            }
        } catch (final Exception e) {
            log.error("Error al obtener el logo de la biblioteca...", e);
        }
    }

    /**
     * Inserta el favorito en el menu.
     */
    public void insertarFavorito() {

        final SessionManager sesion = getSessionManager();
        if (sesion != null) {
            final Object usuario = sesion
                    .getAttribute(Constants.USUARIO_SESSION_PARAM);
            if (usuario != null) {
                getDelegate().insertarFavorito(usuario, getUrl());
            }
            sesion.removeAttribute(Constants.MENU_FAVORITO_LIBRAE);
        }
    }

    /**
     * Inseta la url en el menu de historial.
     *
     * @return el menu de historial.
     */
    public String getInsertarHistorial() {
        List<MenuItem> historiales = (List<MenuItem>) getSessionManagerParam(Constants.MENU_HISTORIAL_LIBRAE);
        final String url = super.getUrl();
        Boolean encontrado = Boolean.FALSE;
        final Propiedades properties = Propiedades
                .getInstance(Constants.USER_WATCHDOG_PROPERTIES);
        final Long maximo = new Long(properties
                .getPropiedad(Constants.MAXIMO_MENU));

        if (historiales == null) {
            historiales = new ArrayList<MenuItem>();
        }

        if (historiales.size() <= maximo.intValue()) {
            for (final MenuItem historial : historiales) {
                if (url.equals(historial.getUrl())) {
                    encontrado = Boolean.TRUE;
                }
            }
            if (!encontrado) {
                super.insertarHistorial(delegate.getMenuItem(url, null, null));
            }
        }
        return "";
    }

    /**
     * Busca el ancla de la ayuda.
     *
     * @return
     */
    public String getAncla() {
        return ConstantesBreadCrumb.getHelpCodes(getFacesContext()
                .getCurrentInstance().getViewRoot().getViewId());
    }

    // Getters && Setters

    public IMenuDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final IMenuDelegate delegate) {
        this.delegate = delegate;
    }

}
