package org.librae.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Vista para acelerar la búsqueda de la Biblioteca
 * 
 * @author bagarcia
 */
@Entity(name = BibliotecaView.ENTITY_NAME)
@Table(name = BibliotecaView.TABLE_NAME)
public class BibliotecaView extends BaseObject {

	/**
	 * BaseObject is Serializable, so Prestamo needs a Serial Version UID
	 */
	private static final long serialVersionUID = 6245974682179963953L;

	public static final String ENTITY_NAME = "org.librae.adminconfig.model.BibliotecaView";
	public static final String TABLE_NAME = "ADM_VIEW_BIBLIOTECA";
	public static final String ID_GENERATOR_NAME = "generator_adm_biblioteca_view";

	public static final String COLUMN_NAME_ID = "X_VIEW_BIBLIOTECA";
	public static final String COLUMN_NAME_PADRE = "BIBLIOTECA_X_PADRE";
	public static final String COLUMN_NAME_DESCRIPCION = "T_DESCRIPCION";
	public static final String COLUMN_NAME_TIPO_BIBLIOTECA = "TIPO_BIBLIOTECA";
	public static final String COLUMN_NAME_CODIGO = "T_CODIGO";

	public static final String PROPTY_NAME_ID = "id";
	public static final String PROPTY_NAME_PADRE = "padre";
	public static final String PROPTY_NAME_DESCRIPCION = "descripcion";
	public static final String PROPTY_NAME_CODIGO = "codigo";
	public static final String PROPTY_NAME_TIPO_BIBLIOTECA = "tipoBiblioteca";

	/**
	 * Clave primaria de la tabla de bibliotecas.
	 */
	private Long id;

	private Long padre;

	private String descripcion;

	private String codigo;

	private String tipoBiblioteca;

	protected BibliotecaView() {
		super();
	}

	public BibliotecaView(final String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = BibliotecaView.COLUMN_NAME_ID)
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	protected void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the padre
	 */
	@Column(name = BibliotecaView.COLUMN_NAME_PADRE)
	public Long getPadre() {
		return padre;
	}

	/**
	 * @param padre
	 *            the padre to set
	 */
	public void setPadre(final Long padre) {
		this.padre = padre;
	}

	@Column(name = BibliotecaView.COLUMN_NAME_DESCRIPCION)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = BibliotecaView.COLUMN_NAME_CODIGO)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		// if they are the same instance
		if (this == obj) {
			return true;
		}

		// if they are classify in different classes
		if (!(obj instanceof BibliotecaView)) {
			return false;
		}

		final BibliotecaView other = (BibliotecaView) obj;

		if (!getId().equals(other.getId())) {
			return false;
		}
		return true;

	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result += ((id == null) ? 0 : getId().hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(BibliotecaView.PROPTY_NAME_ID,
				getId()).toString();
	}

	@Column(name = BibliotecaView.COLUMN_NAME_TIPO_BIBLIOTECA)
	public String getTipoBiblioteca() {
		return tipoBiblioteca;
	}

	public void setTipoBiblioteca(String tipoBiblioteca) {
		this.tipoBiblioteca = tipoBiblioteca;
	}
}
