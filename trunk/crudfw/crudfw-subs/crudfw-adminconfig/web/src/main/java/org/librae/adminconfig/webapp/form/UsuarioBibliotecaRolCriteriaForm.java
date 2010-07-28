package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.common.webapp.form.ISearchForm;

/**
 * Criteria para las busquedas de roles.
 *
 * @author jcisneros
 */
public class UsuarioBibliotecaRolCriteriaForm implements ISearchForm,
		Serializable {

	/**
	 * Numero de serializacion.
	 */
	private static final long serialVersionUID = 8522996923345207875L;

	/**
	 * Código alfanumérico unívoco del rol
	 */
	private Long idUsuario;

	/**
	 * Nombre del rol
	 */
	private Long idBiblioteca;

	/**
	 * Nombre del rol
	 */
	private Long idRol;

	/**
	 * @see org.librae.common.webapp.form.ISearchForm#toMap()
	 */
	public Map<String, Object> toMap() {
		final HashMap<String, Object> criterios = new HashMap<String, Object>();

		criterios.put("idUsuario", getIdUsuario());
		criterios.put("idRol", getIdRol());
		criterios.put("idBiblioteca", getIdBiblioteca());

		return criterios;
	}

	/**
	 * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
	 *      Object>)
	 */
	public void fromMap(Map<String, Object> criterios) {
		if (criterios != null) {
		}
	}

	// Getters && Setters

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(Long idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

}
