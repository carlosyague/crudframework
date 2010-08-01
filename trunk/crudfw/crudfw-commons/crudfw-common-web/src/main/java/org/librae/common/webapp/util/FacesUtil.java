/*
 * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andalucía Derechos de
 * explotación propiedad de la Junta de Andalucía. Éste programa es software
 * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los términos
 * de la Licencia EUPL European Public License publicada por el organismo IDABC
 * de la Comisión Europea, en su versión 1.0. o posteriores. Éste programa se
 * distribuye de buena fe, pero SIN NINGUNA GARANTÍA, incluso sin las presuntas
 * garantías implícitas de USABILIDAD o ADECUACIÓN A PROPÓSITO CONCRETO. Para
 * mas información consulte la Licencia EUPL European Public License. Usted
 * recibe una copia de la Licencia EUPL European Public License junto con este
 * programa, si por algún motivo no le es posible visualizarla, puede
 * consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reçu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */
package org.librae.common.webapp.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * FacesUtil.java
 * 
 * @author Chao Hoi Ka, Eric
 */
public class FacesUtil {

    private static Log log = LogFactory.getLog(FacesUtil.class);

    /**
     * Get servlet context.
     * 
     * @return the servlet context
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance()
                .getExternalContext().getContext();
    }

    /**
     * Get the
     * 
     * @param s
     * @return
     */
    public static String getApplicationRealPath(String s) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final ExternalContext econtext = context.getExternalContext();
        final ServletContext scontext = (ServletContext) econtext.getContext();
        return scontext.getRealPath(s);
    }

    /**
     * Get managed bean based on the bean name.
     * 
     * @param beanName
     *            the bean name
     * @return the managed bean associated with the bean name
     */
    public static Object getManagedBean(String beanName) {
        final Object o = getValueBinding(getJsfEl(beanName)).getValue(
                FacesContext.getCurrentInstance());

        return o;
    }

    /**
     * Remove the managed bean based on the bean name.
     * 
     * @param beanName
     *            the bean name of the managed bean to be removed
     */
    public static void resetManagedBean(String beanName) {
        getValueBinding(getJsfEl(beanName)).setValue(
                FacesContext.getCurrentInstance(), null);
    }

    /**
     * Store the managed bean inside the session scope.
     * 
     * @param beanName
     *            the name of the managed bean to be stored
     * @param managedBean
     *            the managed bean to be stored
     */
    public static void setManagedBeanInSession(String beanName,
            Object managedBean) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put(beanName, managedBean);
    }

    /**
     * Get parameter value from request scope.
     * 
     * @param name
     *            the name of the parameter
     * @return the parameter value
     */
    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get(name);
    }

    /**
     * Add information message.
     * 
     * @param msg
     *            the information message
     */
    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    /**
     * Add information message.
     * 
     * @param msg
     * @param useBundle
     */
    public static void addInfoMessage(String msg, boolean useBundle) {
        addInfoMessage(msg, useBundle, null);
    }

    /**
     * Add information message.
     * 
     * @param msg
     * @param useBundle
     * @param prams
     */
    public static void addInfoMessage(String msg, boolean useBundle,
            Object[] prams) {
        addInfoMessage(null, msg, useBundle, prams);
    }

    /**
     * Add information message to a sepcific client.
     * 
     * @param clientId
     * @param msg
     */
    public static void addInfoMessage(String clientId, String msg) {

        final FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(clientId, new FacesMessage(
                FacesMessage.SEVERITY_INFO, msg, msg));
    }

    /**
     * Add information message to a sepcific client.
     * 
     * @param clientId
     * @param msg
     * @param useBundle
     * @param params
     */
    public static void addInfoMessage(String clientId, String msg,
            boolean useBundle, Object[] params) {

        final FacesContext context = FacesContext.getCurrentInstance();

        String msgs = msg;

        if (useBundle) {
            final Application application = context.getApplication();
            final String messageBundleName = application.getMessageBundle();
            final Locale locale = context.getViewRoot().getLocale();
            final ResourceBundle rb = ResourceBundle.getBundle(
                    messageBundleName, locale);
            final String msgPattern = rb.getString(msg);
            msgs = msgPattern;

            if (params != null && params.length > 0) {
                msgs = MessageFormat.format(msgPattern, params);
            }
        }

        context.addMessage(clientId, new FacesMessage(
                FacesMessage.SEVERITY_INFO, msgs, msgs));
    }

    /**
     * Add error message.
     * 
     * @param msg
     *            the error message
     */
    public static FacesMessage addErrorMessage(String msg) {
        return addErrorMessage(msg, false);
    }

    /**
     * add error message
     * 
     * @param msg
     * @param param
     * @return
     */
    public static FacesMessage addErrorMessage(String msg, Object param) {
        return addErrorMessageById(null, msg, param);
    }

    /**
     * add error message
     * 
     * @param msg
     * @param params
     * @return
     */
    public static FacesMessage addErrorMessage(String msg, Object[] params) {
        return addErrorMessageById(null, msg, params);
    }

    /**
     * add error message
     * 
     * @param msg
     * @param useBundle
     * @return
     */
    public static FacesMessage addErrorMessage(String msg, boolean useBundle) {
        return addErrorMessage(msg, useBundle, null);
    }

    /**
     * add error message
     * 
     * @param msg
     * @param useBundle
     * @param param
     * @return
     */
    public static FacesMessage addErrorMessage(String msg, boolean useBundle,
            Object param) {
        if (param == null) {
            return addErrorMessage(msg, useBundle, new Object[] { param });
        } else {
            return addErrorMessage(msg, useBundle, null);
        }
    }

    /**
     * add error message
     * 
     * @param msg
     * @param useBundle
     * @param params
     * @return
     */
    public static FacesMessage addErrorMessage(String msg, boolean useBundle,
            Object[] params) {
        return addErrorMessageById(null, msg, useBundle, params);
    }

    /**
     * Add error message to a sepcific client.
     * 
     * @param clientId
     *            the client id
     * @param msg
     *            the error message
     */
    public static FacesMessage addErrorMessageById(String clientId, String msg) {
        final FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msg, msg);

        FacesContext.getCurrentInstance().addMessage(clientId, fMsg);

        return fMsg;
    }

    /**
     * add error message
     * 
     * @param clientId
     * @param msg
     * @param useBundle
     * @param params
     * @return
     */
    public static FacesMessage addErrorMessageById(String clientId, String msg,
            boolean useBundle, Object[] params) {

        final FacesContext context = FacesContext.getCurrentInstance();

        String msgs = msg;

        if (useBundle) {
            final Application application = context.getApplication();
            final String messageBundleName = application.getMessageBundle();
            final Locale locale = context.getViewRoot().getLocale();
            final ResourceBundle rb = ResourceBundle.getBundle(
                    messageBundleName, locale);
            final String msgPattern = rb.getString(msg);
            msgs = msgPattern;

            if (params != null && params.length > 0) {
                msgs = MessageFormat.format(msgPattern, params);
            }
        }

        final FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msgs, msgs);

        context.addMessage(clientId, fMsg);

        return fMsg;
    }

    /**
     * add error message
     * 
     * @param clientId
     * @param msg
     * @param param
     * @return
     */
    public static FacesMessage addErrorMessageById(String clientId, String msg,
            Object param) {

        return addErrorMessageById(clientId, msg, new Object[] { param });
    }

    /**
     * Add an error message
     * 
     * @param clientId
     * @param msg
     * @param params
     * @return
     */
    public static FacesMessage addErrorMessageById(String clientId, String msg,
            Object[] params) {

        final FacesContext context = FacesContext.getCurrentInstance();

        String msgs = msg;

        final Application application = context.getApplication();
        final String messageBundleName = application.getMessageBundle();
        final Locale locale = context.getViewRoot().getLocale();
        final ResourceBundle rb = ResourceBundle.getBundle(messageBundleName,
                locale);
        final String msgPattern = rb.getString(msg);
        msgs = msgPattern;

        msgs = MessageFormat.format(msgPattern, params);

        final FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msgs, msgs);

        context.addMessage(clientId, fMsg);

        return fMsg;
    }

    /**
     * get the application
     * 
     * @return the application
     */
    private static Application getApplication() {
        final ApplicationFactory appFactory = getApplicationFactory();
        return appFactory.getApplication();
    }

    /**
     * Get the application factory
     * 
     * @return
     */
    public static ApplicationFactory getApplicationFactory() {
        final ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
                .getFactory(FactoryFinder.APPLICATION_FACTORY);

        return appFactory;
    }

    public static void setLocale(Locale locale) {
        final FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(java.util.Locale.ENGLISH);
    }

    public static Locale getLocale() {
        final FacesContext context = FacesContext.getCurrentInstance();
        return context.getViewRoot().getLocale();
    }

    /**
     * get the value binding
     * 
     * @param bindingName
     * @return
     */
    private static ValueBinding getValueBinding(String bindingName) {
        return getApplication().createValueBinding(bindingName);
    }

    /**
     * get the method binding
     * 
     * @param bindingName
     * @param params
     * @return
     */
    public static MethodBinding getMethodBinding(String bindingName,
            Class params[]) {
        final ApplicationFactory factory = getApplicationFactory();

        final MethodBinding binding = factory.getApplication()
                .createMethodBinding(bindingName, params);
        return binding;
    }

    /**
     * get the ServletRequest
     * 
     * @return
     */
    public static HttpServletRequest getServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    /**
     * get the HttpSession
     * 
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return getSession(false);
    }

    /**
     * get the HttpSession
     * 
     * @param create
     *            create a new HttpSession
     * @return HttpSession
     */
    public static HttpSession getSession(boolean create) {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(create);
    }

    /**
     * Get the EL value
     * 
     * @param el
     * @return
     */
    // Comento por se un metodo privado en desuso
    // private static Object getElValue(String el) {
    // return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
    // }
    /**
     * get the JSF-EL style value
     * 
     * @param value
     * @return
     */
    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }

    public static String getRemoteUser() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final ExternalContext econtext = context.getExternalContext();
        return econtext.getRemoteUser();
    }

    public static boolean hasRole(String role) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final ExternalContext econtext = context.getExternalContext();
        return econtext.isUserInRole(role);
    }

    /**
     * get resource message
     * 
     * @param msg
     * @return
     */
    public static String getMessage(String msg) {
        return getMessage(msg, new Object[] {});
    }

    /**
     * get resource message
     * 
     * @param msg
     * @param params
     * @return
     */
    public static String getMessage(String msg, Object[] params) {
        final FacesContext context = FacesContext.getCurrentInstance();

        final Application application = context.getApplication();
        final String bundle = application.getMessageBundle();

        return getMessage(bundle, msg, params);
    }

    /**
     * get resource message
     * 
     * @param bundle
     * @param msg
     * @param params
     * @return
     */
    public static String getMessage(String bundle, String msg, Object[] params) {
        final FacesContext context = FacesContext.getCurrentInstance();

        final Locale locale = context.getViewRoot().getLocale();

        return getMessage(bundle, locale, msg, params);
    }

    /**
     * get resource message
     * 
     * @param bundle
     * @param locale
     * @param msg
     * @param params
     * @return
     */
    public static String getMessage(String bundle, Locale locale, String msg,
            Object[] params) {
        String result = msg;

        try {
            final ResourceBundle rb = ResourceBundle.getBundle(bundle, locale);
            final String msgPattern = rb.getString(msg);
            result = msgPattern;

            result = MessageFormat.format(msgPattern, params);
        } catch (Exception e) {
            result = msg;
            log.error(e);
        }

        return result;
    }

    /**
     * Recupera el valor para el atributo indicado en determinado objeto
     * 
     * @param atributo
     *            String con el nombre del atributo a recuperar.
     * @param objeto
     *            objeto de cual se desea recuperar el atributo indicado
     * @return Object, valor del atributo indicado en el objeto
     */
    private static Object obtenerAtributoObjeto(String atributo, Object objeto) {
        Object valorAtributo = null;
        if (atributo == null || atributo.trim().equals("") || objeto == null) {
            return valorAtributo;
        }
        Method metodoGet;
        final StringBuilder nombreMetodo = new StringBuilder("get");
        nombreMetodo.append(atributo.substring(0, 1).toUpperCase());
        if (atributo.length() > 1) {
            nombreMetodo.append(atributo.substring(1));
        }
        try {
            metodoGet = objeto.getClass().getMethod(nombreMetodo.toString(),
                    null);
            valorAtributo = metodoGet.invoke(objeto, null);
        } catch (NoSuchMethodException nsmex) {
            log.warn(nsmex.getMessage());
        } catch (IllegalAccessException iacex) {
            log.warn(iacex.getMessage());
        } catch (InvocationTargetException itaex) {
            log.warn(itaex.getMessage());
        }
        return valorAtributo;
    }

    /**
     * Recupera una lista de selectitem a partir de una lista origen,
     * especificando la propiedad descripciÃ³n y la propiedad valor.
     * 
     * @param listaOrigen
     *            Lista de objetos origen, que se desea transformar en una lista
     *            de selectItem.
     * @param atributoDescripcion
     *            nombre del atributo de la clase de objetos de la lista que se
     *            asignara a la descripcion del selecitem.
     * @param atributoValue
     *            nombre del atributo de la clase de objetos de la lista que se
     *            asignara al value del selecitem.
     * @param seleccionarTodos
     *            de tipo boolean si es true indica que al inicio de la lista
     *            agregue una descripciÃ³n en blanco.
     * @return List<SelectItem>, listado de selecitems
     */
    @SuppressWarnings("unchecked")
    public static List<SelectItem> obtenerListaSelectItem(List listaOrigen,
            String atributoDescripcion, String atributoValue,
            boolean seleccionarTodos) {
        final List<SelectItem> lista = new ArrayList<SelectItem>();
        if (listaOrigen == null || listaOrigen.size() < 1
                || atributoDescripcion == null
                || atributoDescripcion.trim().equals("")
                || atributoValue == null || atributoValue.trim().equals("")) {
            return lista;
        }
        Object valorAtributoDescripcion;
        Object valorAtributoValue;
        if (seleccionarTodos) {
            lista.add(new SelectItem("", ""));
        }
        for (Object objeto : listaOrigen) {
            valorAtributoDescripcion = obtenerAtributoObjeto(
                    atributoDescripcion, objeto);

            // Se comprueba si son valores internacionalizables
            if (null != valorAtributoDescripcion
                    && valorAtributoDescripcion.toString().indexOf("#") != -1) {
                String s = ComboLocaleManager.get(valorAtributoDescripcion
                        .toString().replace("#", ""));
                if (s != null) {
                    valorAtributoDescripcion = s;
                }
            }

            valorAtributoValue = obtenerAtributoObjeto(atributoValue, objeto);
            if (valorAtributoDescripcion != null && valorAtributoValue != null) {
                lista.add(new SelectItem(valorAtributoValue.toString(),
                        valorAtributoDescripcion.toString()));
            }
        }
        return lista;
    }

    /**
     * Recupera una lista de selectitem a partir de una lista origen,
     * especificando la propiedad descripciÃ³n y la propiedad valor. Este método
     * se usará en contraposición al método anterior debido a que pueden existir
     * valores en el combo los cuales comienzen por el caracter # provocando
     * error
     * 
     * @param listaOrigen
     *            Lista de objetos origen, que se desea transformar en una lista
     *            de selectItem.
     * @param atributoDescripcion
     *            nombre del atributo de la clase de objetos de la lista que se
     *            asignara a la descripcion del selecitem.
     * @param atributoValue
     *            nombre del atributo de la clase de objetos de la lista que se
     *            asignara al value del selecitem.
     * @param seleccionarTodos
     *            de tipo boolean si es true indica que al inicio de la lista
     *            agregue una descripciÃ³n en blanco.
     * @return List<SelectItem>, listado de selecitems
     */
    @SuppressWarnings("unchecked")
    public static List<SelectItem> obtenerListaSelectItemSinInternacionalizacion(
            List listaOrigen, String atributoDescripcion, String atributoValue,
            boolean seleccionarTodos) {
        final List<SelectItem> lista = new ArrayList<SelectItem>();
        if (listaOrigen == null || listaOrigen.size() < 1
                || atributoDescripcion == null
                || atributoDescripcion.trim().equals("")
                || atributoValue == null || atributoValue.trim().equals("")) {
            return lista;
        }
        Object valorAtributoDescripcion;
        Object valorAtributoValue;
        if (seleccionarTodos) {
            lista.add(new SelectItem("", ""));
        }
        for (Object objeto : listaOrigen) {
            valorAtributoDescripcion = obtenerAtributoObjeto(
                    atributoDescripcion, objeto);

            // Se comprueba si son valores internacionalizables
            String s = ComboLocaleManager.get(valorAtributoDescripcion
                    .toString());
            if (s != null) {
                valorAtributoDescripcion = s;
            }

            valorAtributoValue = obtenerAtributoObjeto(atributoValue, objeto);
            if (valorAtributoDescripcion != null && valorAtributoValue != null) {
                lista.add(new SelectItem(valorAtributoValue.toString(),
                        valorAtributoDescripcion.toString()));
            }
        }
        return lista;
    }
}
