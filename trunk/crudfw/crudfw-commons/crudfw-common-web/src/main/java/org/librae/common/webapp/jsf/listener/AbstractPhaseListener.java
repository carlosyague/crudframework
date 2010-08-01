package org.librae.common.webapp.jsf.listener;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.webapp.session.SessionManager;

public abstract class AbstractPhaseListener implements PhaseListener {

    private static final long  serialVersionUID   = -3062561552826874815L;

    protected final Log        log                = LogFactory
                                                          .getLog(getClass());

    /**
     * constants
     */
    public static final String JSF_PAGE_EXTENSION = ".xhtml";
    public static final String JSF_URL_EXTENSION  = ".html";

    /**
     * servlet api methods<br>
     * ===================
     */

    /**
     * get request
     *
     * @param event
     * @return
     */
    protected HttpServletRequest getRequest(PhaseEvent event) {
        HttpServletRequest result = null;

        final FacesContext facesContext = event.getFacesContext();
        final ExternalContext externalContext = facesContext
                .getExternalContext();
        result = (HttpServletRequest) externalContext.getRequest();

        return result;
    }

    /**
     * get request param
     *
     * @param event
     * @param param
     * @return
     */
    protected String getRequestParam(PhaseEvent event, String param) {
        String result = null;
        try {
            final FacesContext facesContext = event.getFacesContext();
            final ExternalContext externalContext = facesContext
                    .getExternalContext();

            result = externalContext.getRequestParameterMap().get(param);
        } catch (Exception e) {
            result = null;
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * get response
     *
     * @param event
     * @return
     */
    protected HttpServletResponse getResponse(PhaseEvent event) {
        HttpServletResponse result = null;

        final FacesContext facesContext = event.getFacesContext();
        final ExternalContext externalContext = facesContext
                .getExternalContext();
        result = (HttpServletResponse) externalContext.getResponse();

        return result;
    }

    /**
     * get servlet context
     *
     * @param event
     * @return
     */
    protected ServletContext getServletContext(PhaseEvent event) {
        ServletContext result = null;

        final FacesContext facesContext = event.getFacesContext();
        final ExternalContext externalContext = facesContext
                .getExternalContext();
        result = (ServletContext) externalContext.getContext();

        return result;
    }

    /**
     * get session std (current application)
     *
     * @param event
     * @return
     */
    protected HttpSession getSession(PhaseEvent event) {
        HttpSession result = null;

        final FacesContext facesContext = event.getFacesContext();
        final ExternalContext externalContext = facesContext
                .getExternalContext();
        result = (HttpSession) externalContext.getSession(false);

        return result;
    }

    /**
     * get session param
     *
     * @param event
     * @param param
     * @return
     */
    protected Object getSessionParam(PhaseEvent event, String param) {
        Object result = null;
        try {
            final SessionManager session = getSessionManager(event);

            if (session != null) {
                result = session.getAttribute(param);
            }

        } catch (Exception e) {
            result = null;
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * set session param
     *
     * @param event
     * @param param
     * @param object
     * @return
     */
    protected void setSessionParam(PhaseEvent event, String param, Object object) {
        try {
            final SessionManager session = getSessionManager(event);

            if (session != null) {
                session.setAttribute(param, object);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * get sessionManager
     *
     * @param init
     *            initialize session manager flag
     * @param event
     * @return
     */
    protected SessionManager getSessionManager(PhaseEvent event, boolean init) {
        SessionManager result = null;

        final HttpServletRequest request = this.getRequest(event);
        final ServletContext servletContext = getServletContext(event);

        if (request != null && servletContext != null) {

            if (init) {
                SessionManager.init(request, this.getResponse(event), this
                        .getServletContext(event));
            }

            result = SessionManager.getSessionManager(request, servletContext);
        }

        return result;
    }


    /**
     * get sessionManager
     *
     * @param event
     * @return
     */
    protected SessionManager getSessionManager(PhaseEvent event) {
        return getSessionManager(event, false);
    }


    /**
     * invalidate sessionManager
     *
     * @param event
     * @return
     */
    protected void invalidateSessionManager(PhaseEvent event) {
        final HttpServletRequest request = this.getRequest(event);
        final ServletContext servletContext = getServletContext(event);

        if (request != null && servletContext != null) {
            SessionManager.invalidate(request, servletContext);
        }
    }

    /**
     * get session manager param
     *
     * @param event
     * @param param
     * @return
     */
    protected Object getSessionManagerParam(PhaseEvent event, String param) {
        Object result = null;
        try {
            final SessionManager session = getSessionManager(event);

            if (session != null) {
                result = session.getAttribute(param);
            }

        } catch (Exception e) {
            result = null;
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * set session param
     *
     * @param event
     * @param param
     * @param object
     * @return
     */
    protected void setSessionManagerParam(PhaseEvent event, String param,
            Object object, boolean init) {
        try {
            final SessionManager session = getSessionManager(event, init);

            if (session != null) {
                session.setAttribute(param, object);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * set session param
     *
     * @param event
     * @param param
     * @param object
     * @return
     */
    protected void setSessionManagerParam(PhaseEvent event, String param,
            Object object) {
        setSessionManagerParam(event, param, object, false);
    }

    /**
     * get current context
     *
     * @param event
     * @return
     */
    protected String getCurrentApplicationContext(PhaseEvent event) {
        String result = null;
        try {
            final FacesContext facesContext = event.getFacesContext();
            final ExternalContext externalContext = facesContext
                    .getExternalContext();
            result = externalContext.getRequestContextPath();
        } catch (Exception e) {
            result = null;
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * get the current JSF page
     *
     * @param event
     * @return
     */
    protected String getCurrentJSFPage(PhaseEvent event) {
        String result = null;
        try {
            final FacesContext facesContext = event.getFacesContext();
            result = facesContext.getViewRoot().getViewId();

        } catch (Exception e) {
            result = null;
            log.error(e.getMessage());
        }
        return result;
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

}
