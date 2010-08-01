package org.librae.common.webapp.action;

import java.io.IOException;

import org.librae.common.util.Propiedades;
import org.librae.common.webapp.util.WindowMessages;

/**
 * MostrarDetalleAction es una clase con las utilidades para las pantallas de
 * redireccion.
 * 
 * @author jcisneros
 */
public class MostrarDetalleAction extends BasePage {

	private String identificador;
	private String urlBack;
	private String url;
	private String nombreIdentificador;
	private String codigoError;
	private String codigoUrlBack;

	/**
	 * Obtiene el procolo usado en esta implementación.
	 * 
	 * @param properties
	 * @return
	 */
	private static String getHttpProtocol(final Propiedades properties) {
		String result = HTTP_PROTOCOL_DEFAULT_VALUE;

		if (properties != null) {
			final String value = properties.getPropiedad(HTTP_PROTOCOL_KEY);

			if (value != null && !value.equals("")) {
				result = value;
			}
		}
		return result;
	}

	/**
	 * Metodo invocado al cargarse la pagina Se encargara de restablecer los
	 * posibles valores guardados del formulario en sesion
	 * 
	 * @return Cadena vacia
	 */
	public String mostrarDetalle() {
		try {
			if (getIdentificador()!=null) {
				setSessionManagerParam(getCodigoUrlBack(), getUrlBack());
				final StringBuilder url = new StringBuilder();
				url.append(getConfSubsistema());
				url.append(getUrl());
				url.append("?");
				url.append(getNombreIdentificador());
				url.append("=");
				url.append(getIdentificador());
				getFacesContext().getCurrentInstance().getExternalContext()
						.redirect(url.toString());
			}
		} catch (final IOException e) {
			final WindowMessages wm = new WindowMessages();
			wm.addErrorMessage(getCodigoError());
			setWindowMessages(wm);
		}
		return "refresh";
	}

	// Getter && Setters

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getUrlBack() {
		return urlBack;
	}

	public void setUrlBack(String urlBack) {
		this.urlBack = urlBack;
	}

	public String getNombreIdentificador() {
		return nombreIdentificador;
	}

	public void setNombreIdentificador(String nombreIdentificador) {
		this.nombreIdentificador = nombreIdentificador;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getCodigoUrlBack() {
		return codigoUrlBack;
	}

	public void setCodigoUrlBack(String codigoUrlBack) {
		this.codigoUrlBack = codigoUrlBack;
	}
}
