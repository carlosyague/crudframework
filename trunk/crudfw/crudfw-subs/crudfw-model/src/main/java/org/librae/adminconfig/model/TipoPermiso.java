package org.librae.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.BaseObject;

/**
 * Tabla con los posibles tipos de permisos
 * 
 * @author asantamaria
 */
@Entity(name = TipoPermiso.ENTITY_NAME)
@Table(name = TipoPermiso.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class TipoPermiso extends BaseObject {

	/**
	 * BaseObject is Serializable, so TipoPermiso needs a Serial Version UID
	 */
	private static final long serialVersionUID = -493113359982334950L;

	public static final String ENTITY_NAME = "org.librae.adminconfig.model.TipoPermiso";
	public static final String TABLE_NAME = "ADM_TIPOS_PERMISO";
	public static final String ID_GENERATOR_NAME = "generator_adm_tipos_permiso";
	private static final String ID_SEQUENCE_NAME = "SEQ_ADM_TIPOS_PERMISO";
	public static final String COLUMN_NAME_ID = "X_TIPO_PERMISO";
	public static final String COLUMN_NAME_CODIGO = "C_TIPO_PERMISO";
	public static final String COLUMN_NAME_NOMBRE = "T_TIPO_PERMISO";

	/**
	 * Clave primaria autonumérica sin significado
	 */
	private Long id;

	/**
	 * Código del tipo de permiso
	 */
	private String codigo;

	/**
	 * Descripción del tipo de permiso
	 */
	private String nombre;

	protected TipoPermiso() {
		super();
	}

	public TipoPermiso(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
	@SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
	@Column(name = TipoPermiso.COLUMN_NAME_ID)
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	protected void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the codigo
	 */
	@Column(name = TipoPermiso.COLUMN_NAME_CODIGO, length = 40)
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	@Column(name = TipoPermiso.COLUMN_NAME_NOMBRE, length = 80)
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// if they are the same instance
		if (this == obj) {
			return true;
		}

		// if they are classify in different classes
		if (!(obj instanceof TipoPermiso)) {
			return false;
		}

		final TipoPermiso other = (TipoPermiso) obj;

		if (!this.getCodigo().equals(other.getCodigo())) {
			return false;
		}

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result += ((id == null) ? 0 : this.getId().hashCode());
		result = prime
				* result
				+ ((this.getCodigo() == null) ? 0 : this.getCodigo().hashCode());

		return result;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.COLUMN_NAME_ID,
				this.getId()).append(this.COLUMN_NAME_CODIGO, this.getCodigo())
				.append(this.COLUMN_NAME_NOMBRE, this.getNombre()).toString();
	}

}