package es.uma.crudframework.webapp.action;

import es.uma.crudframework.Constants;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

/**
 * BasePage es una clase que incializa el contexto de JSF común a todas las
 * acciones. Incluye una serie de móetodos y de propiedades que facilitan la
 * navegación entre páginas.
 */
public class BasePage {
	protected final Log log = LogFactory.getLog(this.getClass());

	protected String templateName;
	protected FacesContext facesContext;
	protected String sortColumn;
	protected boolean ascending;
	protected boolean nullsAreHigh;
	protected static final String WM_SESSION_NAME = "WindowMessages_session";
	protected static final String PREFIJO = "seleccion_";
	protected static final String PREFIJOALL = "seleccionTodos_";
	private Boolean valorTrue = true;

	private static final String COMMON_BUNDLE_NAME = "es.uma.crudframework.messages";

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	// Convenience methods ====================================================
	public Boolean getValorTrue() {
		return this.valorTrue;
	}

	public void setValorTrue(final Boolean valorTrue) {
		this.valorTrue = valorTrue;
	}

	public String getParameter(final String name) {
		return this.getRequest().getParameter(name);
	}

	public Object getSessionParam(final String name) {
		Object result = null;
		if (this.getSession() != null) {
			result = this.getSession().getAttribute(name);
		}
		return result;
	}

	public void setSessionParam(String name, Object value) {
		if (this.getSession() != null) {
			this.getSession().setAttribute(name, value);
		}
	}

	public Map getCountries() {
		final CountryModel model = new CountryModel();
		return model.getCountries(this.getRequest().getLocale());
	}

	public String getBundleName() {
		return this.getFacesContext().getApplication().getMessageBundle();
	}

	public ResourceBundle getBundle() {
		final ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		return ResourceBundle.getBundle(this.getBundleName(), this.getRequest()
				.getLocale(), classLoader);
	}

	public ResourceBundle getCommonBundle() {
		final ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		return ResourceBundle.getBundle(COMMON_BUNDLE_NAME, this.getRequest()
				.getLocale(), classLoader);
	}

	public String getText(final String key) {
		return getText(key, this.getBundle());
	}
	
	public String getCommonText(final String key) {
		return getText(key, this.getCommonBundle());
	}
	
	public String getText(final String key, ResourceBundle bundle) {
		String message;

		try {
			message = bundle.getString(key);
		} catch (final java.util.MissingResourceException mre) {
			this.log.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}

		return message;
	}

	public String getAcortarTexto(String texto) {
		String result = "";

		if (texto != null
				&& texto.length() < Constants.COLUMN_DATA_TABLE_MAX_LENGTH) {
			result = texto;
		} else if (texto != null) {
			result = texto.substring(0, Constants.COLUMN_DATA_TABLE_MAX_LENGTH);
		}

		return result;
	}

	public String getText(final String key, final Object arg) {
		return getText(key, arg, this.getBundle());
	}
	
	public String getCommonText(final String key, final Object arg) {
		return getText(key, arg, this.getCommonBundle());
	}
	
	public String getText(final String key, final Object arg, ResourceBundle bundle) {
		if (arg == null) {
			return this.getText(key);
		}

		final MessageFormat form = new MessageFormat(bundle.getString(key));

		if (arg instanceof String) {
			return form.format(new Object[] { arg });
		} else if (arg instanceof Object[]) {
			return form.format(arg);
		} else {
			this.log.error("arg '" + arg + "' not String or Object[]");

			return "";
		}
	}

	@SuppressWarnings("unchecked")
	protected void addMessage(final String key, final Object arg) {
		// JSF Success Messages won't live past a redirect, so it's not used
		// FacesUtils.addInfoMessage(formatMessage(key, arg));
		List<String> messages = (List) this.getSession().getAttribute(
				"messages");

		if (messages == null) {
			messages = new ArrayList<String>();
		}

		messages.add(this.getText(key, arg));
		this.getSession().setAttribute("messages", messages);
	}

	protected void addMessage(final String key) {
		this.addMessage(key, null);
	}
	
	protected void addError(final String key, final Object arg) {
		addError(key, arg, this.getBundle());
	}
	
	protected void addCommonError(final String key, final Object arg) {
		addError(key, arg, this.getCommonBundle());
	}

	@SuppressWarnings("unchecked")
	protected void addError(final String key, final Object arg, ResourceBundle bundle) {
		// The "JSF Way" doesn't allow you to put HTML in your error messages,
		// so I don't use it.
		// FacesUtils.addErrorMessage(formatMessage(key, arg));
		List<String> errors = (List) this.getSession().getAttribute("errors");

		if (errors == null) {
			errors = new ArrayList<String>();
		}

		// if key contains a space, don't look it up, it's likely a raw message
		if (key.contains(" ") && arg == null) {
			errors.add(key);
		} else {
			errors.add(this.getText(key, arg, bundle));
		}

		this.getSession().setAttribute("errors", errors);
	}

	protected void addError(final String key) {
		this.addError(key, null);
	}
	
	protected void addCommonError(final String key) {
		this.addCommonError(key, null);
	}

	/**
	 * Convenience method for unit tests.
	 * 
	 * @return boolean indicator of an "errors" attribute in the session
	 */
	public boolean hasErrors() {
		return (this.getSession().getAttribute("errors") != null);
	}
	
	public Boolean getHasErrors() {
		return hasErrors();
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return HttpServletRequest from the FacesContext
	 */
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) this.getFacesContext().getExternalContext()
				.getRequest();
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return String from the FacesContext
	 */
	protected String getRequestParam(final String param) {
		String result = null;

		result = this.getFacesContext().getExternalContext()
				.getRequestParameterMap().get(param);

		return result;
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return the current user's session
	 */
	protected HttpSession getSession() {
		return this.getRequest().getSession();
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return HttpServletResponse from the FacesContext
	 */
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) this.getFacesContext()
				.getExternalContext().getResponse();
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return the ServletContext form the FacesContext
	 */
	protected ServletContext getServletContext() {
		return (ServletContext) this.getFacesContext().getExternalContext()
				.getContext();
	}

	/**
	 * Convenience method to get the Configuration HashMap from the servlet
	 * context.
	 * 
	 * @return the user's populated form from the session
	 */
	protected Map getConfiguration() {
		final Map config = (HashMap) this.getServletContext().getAttribute(
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
		return this.sortColumn;
	}

	public void setSortColumn(final String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public boolean isAscending() {
		return this.ascending;
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
		Comparator comparator = new BeanComparator(this.sortColumn,
				new NullComparator(this.nullsAreHigh));
		if (!this.ascending) {
			comparator = new ReverseComparator(comparator);
		}
		Collections.sort(list, comparator);
		return list;
	}

	/**
	 * Método encargado de ejecutar el metodo indicado del objeto indicado
	 * 
	 * @retrun Object resultado del metodo
	 */
	public Object ejecutarMetodoReflexion(String strMetodo, Object objeto) {
		Class[] vacio = new Class[0];
		Object[] vacioObjetos = new Object[0];

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
			res = (Object) metodo.invoke(objeto, argumentos);
		} catch (Exception e) {
			log.error("Error al obtener al ejecutar el metodo " + strMetodo, e);
			res = null;
		}

		return res;
	}

}
