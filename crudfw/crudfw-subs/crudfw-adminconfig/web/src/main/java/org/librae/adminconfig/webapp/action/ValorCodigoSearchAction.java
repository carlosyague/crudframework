package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.librae.adminconfig.model.ValorCodigo;
import org.librae.adminconfig.webapp.form.ValorCodigoForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.SessionSearchAction;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF para la gestion de codigos.
 *
 * @author jcisneros
 */
public class ValorCodigoSearchAction extends SessionSearchAction implements
		Serializable {

	public ValorCodigoSearchAction() {
		super();
		setSortColumn("codigo");
	}

	/**
	 * Numero de serializacion.
	 */
	private static final long serialVersionUID = 9193482532222506427L;

	/**
	 * Formulario de codigos
	 */
	private ValorCodigoForm valorCodigoForm = new ValorCodigoForm();

	private String key;

	private Integer pos = -1;

	/**
	 * Guarda el valor.
	 *
	 * @return siguiente pagina.
	 */
	public String save() {
		String page = "list";
		ValorCodigo valorCodigo = null;
		try {
			List<ValorCodigo> valores = (List) getSessionManager()
					.getAttribute("valores");
			if (valores == null) {
				valores = new ArrayList<ValorCodigo>();
			}
			if (getSortColumn() != null) {
				sort(valores);
			}
			final String pos = (String) getSessionManager().getAttribute(
					"valor_codigo_key");
			if (pos != null) {
				valorCodigo = (ValorCodigo) valores.get(new Integer(pos));
			}
			getSessionManager()
					.setAttribute("valorCodigoForm", valorCodigoForm);
			valorCodigo = valorCodigoForm.toEntity(valorCodigo);
			if (pos == null) {
				comprobarUnico(valores, valorCodigo);
				valores.add(valorCodigo);
			} else {
				comprobarUnico(valores, valorCodigo, new Long(pos));
				valores.set(new Integer(pos), valorCodigo);
			}
			getSessionManager().setAttribute("valores", valores);
		} catch (final LibraeException e) {
			log.info("Error al salvar el valor del codigo...", e);
			final WindowMessages wm = new WindowMessages(e);
			setWindowMessages(wm);
			page = "form";
		} catch (final Exception e) {
			log.error("Error al salvar el valor del codigo...", e);
			final WindowMessages wm = new WindowMessages();
			wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
					"ERROR_INSERTAR_VALOR_CODIGO");
			setWindowMessages(wm);
			page = "form";
		}
		return page;
	}

	private void comprobarUnico(List<ValorCodigo> valores,
			ValorCodigo valorCodigo) {
		if (valores != null) {
			for (ValorCodigo valor : valores) {
				if (valor.getValor().equals(valorCodigo.getValor())) {
					throw ExceptionUtil.crearLibraeException(
							MensajesError.PROPERTI_ADMINCONFIG,
							"ERROR_VALOR_CODIGO_UNICO");
				}
			}
		}
	}
	
	private void comprobarUnico(List<ValorCodigo> valores,
			ValorCodigo valorCodigo, Long modificando) {
		Long contador= new Long(0);
		if (valores != null) {
			for (ValorCodigo valor : valores) {
				if (valor.getValor().equals(valorCodigo.getValor()) && (!modificando.equals(contador))) {
					throw ExceptionUtil.crearLibraeException(
							MensajesError.PROPERTI_ADMINCONFIG,
							"ERROR_VALOR_CODIGO_UNICO");
				}
				contador++;
			}
		}
	}

	/**
	 * Guarda el valor.
	 *
	 * @return siguiente pagina.
	 */
	public String getInit() {
		String page = null;
		try {
			ValorCodigoForm valorCodigoFormSession = (ValorCodigoForm) getSessionManager()
					.getAttribute("valorCodigoForm");
			if (valorCodigoFormSession != null) {
				valorCodigoForm = valorCodigoFormSession;
			} else {
				setSortColumn((String) getSessionManager().getAttribute(
						"valor_codigo_order"));
				setAscending((Boolean) getSessionManager().getAttribute(
						"valor_codigo_ascending"));
				final List<ValorCodigo> valores = (List) getSessionManager()
						.getAttribute("valores");
				if (getSortColumn() != null) {
					sort(valores);
				}
				final String pos = (String) getSessionManager().getAttribute(
						"valor_codigo_key");
				if (pos != null) {
					final Object valorCodigo = valores.get(new Integer(pos));
					valorCodigoForm.fromEntity((ValorCodigo) valorCodigo);
				} else {
					valorCodigoForm = new ValorCodigoForm();
				}
			}
		} catch (final Exception e) {
			log.error("Error al cargar el valor del codigo...", e);
			final WindowMessages wm = new WindowMessages();
			wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
					"ERROR_INIT_VALOR_CODIGO");
			setWindowMessages(wm);
		}
		return page;
	}

	/**
	 * Guarda el valor.
	 *
	 * @return siguiente pagina.
	 */
	public String delete() {
		String page = "list";
		try {
			setSortColumn((String) getSessionManager().getAttribute(
					"valor_codigo_order"));
			setAscending((Boolean) getSessionManager().getAttribute(
					"valor_codigo_ascending"));
			List<ValorCodigo> valores = (List) getSessionManager()
					.getAttribute("valores");
			if (getSortColumn() != null) {
				sort(valores);
			}
			final Integer posicion = new Integer(getKey());
			if (posicion != null) {
				valores.remove(posicion.intValue());
			}
			getSessionManager().setAttribute("valores", valores);
		} catch (final Exception e) {
			log.error("Error al salvar el valor del codigo...", e);
			final WindowMessages wm = new WindowMessages();
			wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
					"ERROR_ELIMINAR_VALOR_CODIGO");
			setWindowMessages(wm);
			page = null;
		}
		return page;
	}

	public Integer getPos() {
		pos = pos + 1;
		return pos;
	}

	/**
	 * Cancela el crear/editar una Biblioteca.
	 *
	 * @return String con el identificador de navigation rule (cancel)
	 */
	public String cancel() {
		valorCodigoForm.setReadMode(true);
		return "cancel";
	}

	/**
	 * Método que devuelve el formulario en forma editable. Responde al clic en
	 * el boton Editar en la pantalla de Detalle de Biblioteca.
	 *
	 * @return cadena vacia
	 */
	public String editable() {
		valorCodigoForm.setReadMode(false);
		return "";
	}

	// Getters && Setters

	public ValorCodigoForm getValorCodigoForm() {
		return valorCodigoForm;
	}

	public void setValorCodigoForm(final ValorCodigoForm valorCodigoForm) {
		this.valorCodigoForm = valorCodigoForm;
	}

	public String getKey() {
		return ((key == null) ? (String) getSessionManager().getAttribute(
				"valor_codigo_key") : key);
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public void setPos(final Integer pos) {
		this.pos = pos;
	}
}
