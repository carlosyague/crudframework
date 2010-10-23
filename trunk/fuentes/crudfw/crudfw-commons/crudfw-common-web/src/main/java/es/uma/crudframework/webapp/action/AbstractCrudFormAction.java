package es.uma.crudframework.webapp.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.uma.crudframework.model.BaseObject;
import es.uma.crudframework.model.annotations.SemanticTypeField;
import es.uma.crudframework.model.annotations.CrudSemanticTag;
import es.uma.crudframework.service.GenericManager;
import es.uma.crudframework.webapp.action.BasePage;

public abstract class AbstractCrudFormAction<T extends BaseObject> extends
		BasePage {

	protected GenericManager<T, Long> manager;
	protected T entity;
	protected Long id;

	/**
	 * navigation tokens<br>
	 * =================
	 */
	protected final static String NAVIGATION_TOKEN_LIST = "list";
	protected final static String NAVIGATION_TOKEN_EDIT = "edit";

	/**
	 * Expresión regular que valida el email de un usuario
	 */
	protected static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";

	protected static final String NIF_CONTROL_CHARS = "XYZ";
	protected static final int MAX_LENGTH_NIFCIF = 9;
	protected static final int CONSTANT_23 = 23;

	/**
	 * abstract methods<br>
	 * ================
	 */

	protected abstract T createEmptyEntity();

	protected abstract T getEntity();

	/**
	 * actions<br>
	 * =======
	 */

	public String delete() {
		manager.remove(entity.getId());
		addMessage(getEntityName() + ".deleted");

		return NAVIGATION_TOKEN_LIST;
	}

	public String edit() {
		if (id != null && id != 0) {
			entity = manager.get(id);

			/**
			 * guardamos en sesión la entidad consultada
			 */
			this.setEntitySession(entity);
		} else {
			entity = createEmptyEntity();
		}

		return NAVIGATION_TOKEN_EDIT;
	}

	public String save() {
		String result = null;
		boolean isNew = (entity.getId() == null || entity.getId() == 0);

		if (saveValidate()) {
			manager.save(entity);

			String key = (isNew) ? getEntityName() + ".added" : getEntityName()
					+ ".updated";
			addMessage(key);

			if (isNew) {
				result = NAVIGATION_TOKEN_LIST;
			} else {
				result = NAVIGATION_TOKEN_EDIT;
			}
		} else {
			result = NAVIGATION_TOKEN_EDIT;
		}

		return result;
	}

	/**
	 * this method validates an entity during save operation
	 * 
	 * @return
	 */
	protected Boolean saveValidate() {
		Boolean result = Boolean.TRUE;

		Method[] pojoGetterFields = getPojoGetterFields(entity);

		for (Method pojoGetterField : pojoGetterFields) {
			final Object fieldValue = getPojoFieldValue(pojoGetterField, entity);

			final CrudSemanticTag fieldViewParams = (CrudSemanticTag) pojoGetterField
					.getAnnotation(CrudSemanticTag.class);

			if (fieldViewParams != null) {
				final Boolean fieldValidation = validateField(fieldValue,
						fieldViewParams.type());

				if (!fieldValidation) {
					this.addCommonError("field.validation.error", getFieldName(pojoGetterField));
				}

				result = result && fieldValidation;
			}
		}

		return result;
	}

	private Method[] getPojoGetterFields(T entity) {
		List<Method> result = new ArrayList<Method>();
		Method[] entityMethods = entity.getClass().getMethods();

		for (Method entityMethod : entityMethods) {
			if (!Modifier.isStatic(entityMethod.getModifiers())
					&& isGetterMethodByName(entityMethod.getName())
					&& entityMethod.getAnnotation(CrudSemanticTag.class) != null
					&& entityMethod.getAnnotation(CrudSemanticTag.class)
							.type() != null) {
				result.add(entityMethod);
			}
		}

		Method[] resultArray = new Method[result.size()];
		int i = 0;
		for (Method mth : result) {
			resultArray[i] = mth;
			i++;
		}

		return resultArray;
	}
	
	private String getFieldName(Method getter) {
		final StringBuilder fieldName = new StringBuilder();
		final String methodName = getter.getName();

		if (methodName.length() > 3) {
			String firstChar = ""+methodName.charAt(3);
			fieldName.append(firstChar.toLowerCase());
		}
		if (methodName.length() > 4) {
			fieldName.append(methodName.substring(4));
		}
		return fieldName.toString();
	}

	private Object getPojoFieldValue(Method getter, T entity) {
		Object result = null;
		final String fieldName = getFieldName(getter);

		if (fieldName.length() > 0) {
			try {
				Field pojoField = entity.getClass().getDeclaredField(
						fieldName.toString());
				pojoField.setAccessible(Boolean.TRUE);

				result = pojoField.get(entity);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	private Boolean isGetterMethodByName(String methodName) {
		return methodName != null && methodName.length() > 3
				&& methodName.substring(0, 3).equals("get");
	}

	/**
	 * this method validates a field during save operation
	 * 
	 * @return
	 */
	protected Boolean validateField(Object value, String validator) {
		Boolean result = Boolean.FALSE;

		if (validator.equalsIgnoreCase(SemanticTypeField.NONE)) {
			result = Boolean.TRUE;
		} else if (value != null) {
			final String valueStr = value.toString();
			if (validator
					.equalsIgnoreCase(SemanticTypeField.NOT_NULLABLE)) {
							result = validateRequiredStringField(valueStr);
			} else if (validator
					.equalsIgnoreCase(SemanticTypeField.EMAIL)) {
				result = validateEmailField(valueStr);
			} else if (validator.equalsIgnoreCase(SemanticTypeField.NIF)) {
				result = validateNifField(valueStr);
			}
		}

		return result;
	}

	protected Boolean validateRequiredStringField(String value) {
		return value != null && !value.equalsIgnoreCase("");
	}

	protected Boolean validateEmailField(String value) {
		// Patrón del email
		final Pattern p = Pattern.compile(EMAIL_REGEX);
		final Matcher m = p.matcher(value);

		// Comprueba si el email cumple el patrón
		return m.matches();
	}

	protected Boolean validateNifField(String value) {
		final String s1 = value.toUpperCase();

		if (NIF_CONTROL_CHARS.indexOf(s1.charAt(0)) != -1) {
			Integer primerDigito = -1;
			if (s1.charAt(0) == 'X') {
				primerDigito = 0;
			}
			if (s1.charAt(0) == 'Y') {
				primerDigito = 1;
			}
			if (s1.charAt(0) == 'Z') {
				primerDigito = 2;
			}

			return isDniCorrecto(new StringBuilder(primerDigito.toString())
					.append(s1.substring(1)).toString());
		}
		return isDniCorrecto(value);
	}

	/**
	 * auxiliary methods<br>
	 * =================
	 */

	protected String getEntityName() {
		return entity.getClass().getName().toLowerCase();
	}

	/**
	 * Comprueba si el DNI de una persona es válido.
	 * 
	 * @param dni
	 *            DNI a validar.
	 * @return devuelve true si el DNI es válido.
	 */
	protected Boolean isDniCorrecto(final String dni) {
		boolean correcto = true;
		final char[] letras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P',
				'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C',
				'K', 'E', 'T' };

		if (dni.length() != MAX_LENGTH_NIFCIF) {
			correcto = false;
		}
		try {
			final int numero = Integer.parseInt(dni.substring(0,
					dni.length() - 1));
			final String letraCadena = new StringBuilder()
					.append(dni.charAt(dni.length() - 1)).append("").toString();
			if (!letraCadena
					.equalsIgnoreCase(new StringBuilder()
							.append(letras[numero % CONSTANT_23]).append("")
							.toString())) {
				correcto = false;
			}
		} catch (final NumberFormatException e) {
			correcto = false;
		}
		return correcto;
	}

	/**
	 * getter & setters<br>
	 * ================
	 */

	public GenericManager<T, Long> getManager() {
		return manager;
	}

	public void setManager(GenericManager<T, Long> manager) {
		this.manager = manager;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getExistsEntity() {
		return this.entity != null && entity.getId() != null;
	}

	public String getEntitySessionKey() {
		return "ENTITY_" + this.getClass().getSimpleName();
	}

	@SuppressWarnings("unchecked")
	public T getEntitySession() {
		return (T) this.getSessionParam(getEntitySessionKey());
	}

	public void setEntitySession(T entity) {
		this.setSessionParam(getEntitySessionKey(), entity);
	}

}
