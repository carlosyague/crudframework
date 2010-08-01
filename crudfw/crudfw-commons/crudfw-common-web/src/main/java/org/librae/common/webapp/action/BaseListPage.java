/*
 * Empresa desarrolladora: U.T.E. SOPRA - INGENIA Autor: Junta de Andalucía
 * Derechos de explotación propiedad de la Junta de Andalucía. Éste programa es
 * software libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los
 * términos de la Licencia EUPL European Public License publicada por el
 * organismo IDABC de la Comisión Europea, en su versión 1.0. o posteriores.
 * Éste programa se distribuye de buena fe, pero SIN NINGUNA GARANTÍA, incluso
 * sin las presuntas garantías implícitas de USABILIDAD o ADECUACIÓN A PROPÓSITO
 * CONCRETO. Para mas información consulte la Licencia EUPL European Public
 * License. Usted recibe una copia de la Licencia EUPL European Public License
 * junto con este programa, si por algún motivo no le es posible visualizarla,
 * puede consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reçu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */
package org.librae.common.webapp.action;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.Constants;
import org.librae.common.service.GenericManager;

/**
 * BasePage es una clase que incializa el contexto de JSF común a todas las
 * acciones. Incluye una serie de móetodos y de propiedades que facilitan la
 * navegación entre páginas.
 * 
 * @author ingenia
 * @param <T>
 *            Clase de objetos que forma el listado
 * @param <PK>
 *            Clase de la clave primaria
 * @param <GM>
 *            Interfaz manejador de la clase a listar
 * @param <IM>
 *            Clase manejador que implementa el interfaz GM
 */
public class BaseListPage<T, PK extends Serializable, GM extends GenericManager<T, PK>, IM extends GM> {

    /**
     * the log.
     */
    protected final Log    log = LogFactory.getLog(getClass());

    /**
     * the templateName.
     */
    protected String       templateName;

    /**
     * the facesContext.
     */
    protected FacesContext facesContext;

    /**
     * the nullsAreHigh.
     */
    protected boolean      nullsAreHigh;

    /**
     * the pag.
     */
    protected Integer      pag;

    /**
     * the numRes.
     */
    protected Integer      numRes;

    /**
     * the totalResult.
     */
    protected Long         totalResult;

    /**
     * the pages.
     */
    protected List         pages;

    /**
     * the numPages.
     */
    protected Integer      numPages;

    /**
     * the orderColumn.
     */
    protected String       orderColumn;

    /**
     * the ascendingOrder.
     */
    protected Boolean      ascendingOrder;

    /**
     * the entityManager.
     */
    protected IM           entityManager;

    /**
     * returns the FacesContext.
     * 
     * @return the FacesContext.
     */
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    // Convenience methods ====================================================

    /**
     * returns the Parameter.
     * 
     * @parameter name the name of the parameter
     * @return the Parameter.
     */
    public String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * returns the Countries.
     * 
     * @return the Countries.
     */
    public Map getCountries() {
        final CountryModel model = new CountryModel();
        return model.getCountries(getRequest().getLocale());
    }

    /**
     * returns the BundleName.
     * 
     * @return the BundleName.
     */
    public String getBundleName() {
        return getFacesContext().getApplication().getMessageBundle();
    }

    /**
     * returns the Bundle.
     * 
     * @return the Bundle.
     */
    public ResourceBundle getBundle() {
        final ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        return ResourceBundle.getBundle(getBundleName(), getRequest()
                .getLocale(), classLoader);
    }

    /**
     * returns the Text.
     * 
     * @param key
     *            the key to search the text
     * @return the text.
     */
    public String getText(String key) {
        String message;

        try {
            message = getBundle().getString(key);
        } catch (java.util.MissingResourceException mre) {
            log.warn("Missing key for '" + key + "'");
            return "???" + key + "???";
        }

        return message;
    }

    /**
     * returns the Text.
     * 
     * @param key
     *            the key to search the text
     * @param arg
     *            the argument
     * @return the text.
     */
    public String getText(final String key, final Object arg) {
        if (arg == null) {
            return getText(key);
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

    /**
     * Adds a Message.
     * 
     * @param key
     *            the key
     * @param arg
     *            the arg
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    protected void addMessage(String key, Object arg) {
        // JSF Success Messages won't live past a redirect, so it's not used
        // FacesUtils.addInfoMessage(formatMessage(key, arg));
        List<String> messages = (List) getSession().getAttribute("messages");

        if (messages == null) {
            messages = new ArrayList<String>();
        }

        messages.add(getText(key, arg));
        getSession().setAttribute("messages", messages);
    }

    /**
     * Adds a Message.
     * 
     * @param key
     *            the key
     */
    protected void addMessage(String key) {
        addMessage(key, null);
    }

    /**
     * Adds an Error.
     * 
     * @param key
     *            the key
     * @param arg
     *            the arg
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    protected void addError(String key, Object arg) {
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
            errors.add(getText(key, arg));
        }

        getSession().setAttribute("errors", errors);
    }

    /**
     * Adds an Error.
     * 
     * @param key
     *            the key
     */
    protected void addError(String key) {
        addError(key, null);
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
     * Servlet API Convenience method.
     * 
     * @return HttpServletRequest from the FacesContext
     */
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext()
                .getRequest();
    }

    /**
     * Servlet API Convenience method.
     * 
     * @return the current user's session
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * Servlet API Convenience method.
     * 
     * @return HttpServletResponse from the FacesContext
     */
    protected HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext().getExternalContext()
                .getResponse();
    }

    /**
     * Servlet API Convenience method.
     * 
     * @return the ServletContext form the FacesContext
     */
    protected ServletContext getServletContext() {
        return (ServletContext) getFacesContext().getExternalContext()
                .getContext();
    }

    /**
     * Convenience method to get the Configuration HashMap from the servlet
     * context.
     * 
     * @return the user's populated form the session
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

    /**
     * sets the templateName.
     * 
     * @param templateName
     *            the value to set.
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * returns true if the order is ascending.
     * 
     * @return the ascendingOrder
     */
    public Boolean getAscendingOrder() {
        return ascendingOrder;
    }

    /**
     * sets the order (ascending true).
     * 
     * @param ascendingOrder
     *            the ascendingOrder to set
     */
    public void setAscendingOrder(Boolean ascendingOrder) {
        this.ascendingOrder = ascendingOrder;
    }

    /**
     * returns the sortColumn.
     * 
     * @return the sortColumn
     */
    public String getOrderColumn() {
        return orderColumn;
    }

    /**
     * Sets the sortColumn.
     * 
     * @param sortColumn
     *            the sortColumn to set
     */
    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    /**
     * returns the entityManager.
     * 
     * @return the entityManager
     */
    public IM getEntityManager() {
        return entityManager;
    }

    /**
     * sets the entityManager.
     * 
     * @param entityManager
     *            the entityManager to set
     */
    public void setEntityManager(IM entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * returns the pag.
     * 
     * @return the pag
     */
    public Integer getPag() {
        if (this.pag == null) {
            this.pag = new Integer(1);
        }
        return pag;
    }

    /**
     * sets the pag.
     * 
     * @param pag
     *            the pag to set
     */
    public void setPag(Integer pag) {
        this.pag = pag;
    }

    /**
     * returns the numRes.
     * 
     * @return the numRes
     */
    public Integer getNumRes() {
        return numRes;
    }

    /**
     * sets the numRes.
     * 
     * @param numRes
     *            the numRes to set
     */
    public void setNumRes(Integer numRes) {
        this.numRes = numRes;
    }

    /**
     * returns list.
     * 
     * @return list
     */
    public String list() {
        return "list";
    }

    /**
     * returns the totalResult.
     * 
     * @return the totalResult
     */
    public Long getTotalResult() {
        return totalResult;
    }

    /**
     * sets the totalResult.
     * 
     * @param totalResult
     *            the totalResult to set
     */
    public void setTotalResult(Long totalResult) {
        this.totalResult = totalResult;
    }

    /**
     * returns the pages.
     * 
     * @return the pages
     */
    public List getPages() {
        if (this.numRes == null) {
            this.numRes = new Integer(10);
        }

        this.totalResult = entityManager.countAll();

        this.pages = new ArrayList<String>();

        int numPags = (this.totalResult.intValue() / this.numRes.intValue());

        if ((this.totalResult.intValue() % this.numRes.intValue()) != 0) {
            numPags++;
        }
        for (int i = 0; i < numPags; i++) {
            this.pages.add((new Integer(i + 1)).toString());
        }

        return pages;
    }

    /**
     * sets the pages.
     * 
     * @param pages
     *            the pages to set
     */
    public void setPages(List pages) {
        this.pages = pages;
    }

    /**
     * returns the numPages.
     * 
     * @return the numPages
     */
    public Integer getNumPages() {
        if (this.pages == null) {
            this.numPages = new Integer(this.getPages().size());
        } else {
            this.numPages = new Integer(this.pages.size());
        }
        return numPages;
    }

    /**
     * sets the numPages.
     * 
     * @param numPages
     *            the numPages to set
     */
    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

}
