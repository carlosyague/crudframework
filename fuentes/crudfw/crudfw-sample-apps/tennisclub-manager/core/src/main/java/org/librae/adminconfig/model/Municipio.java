package org.librae.adminconfig.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * @author asantamaria
 */
@Entity(name = Municipio.ENTITY_NAME)
@Table(name = Municipio.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Municipio extends SpringRemotableLazyEntity<Municipio> {

	/**
	 * BaseObject is Serializable, so Municipio needs a Serial Version UID
	 */
	private static final long serialVersionUID = 4854135341339637008L;

	public static final String ENTITY_NAME = "org.librae.adminconfig.model.Municipio";
	public static final String TABLE_NAME = "ADM_MUNICIPIOS";
	public static final String ID_GENERATOR_NAME = "generator_adm_municipios";
	private static final String ID_SEQUENCE_NAME = "SEQ_ADM_MUNICIPIOS";
	public static final String COLUMN_NAME_ID = "X_MUNICIPIO";
	public static final String COLUMN_NAME_CODIGO = "C_INE_MUNICIPIO";
	public static final String COLUMN_NAME_NOMBRE = "T_MUNICIPIO";
	public static final String COLUMN_NAME_PROVINCIA = "PRO_X_PROVINCIA";

	/**
	 * Clave primaria autonumérica sin significado
	 */
	private Long id;

	/**
	 * Código INE del municipio
	 */
	private String codigo;

	/**
	 * Nombre del municipio
	 */
	private String nombre;

	/**
	 * Comunidad autónoma la provincia
	 */
	private Provincia provincia;

	protected Municipio() {
		super();
	}

	public Municipio(String codigo, String nombre) {
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
	@Column(name = Municipio.COLUMN_NAME_ID)
	public Long getId() {
		return id;
	}

	/**
	 * @return the codigo
	 */
	@Column(name = Municipio.COLUMN_NAME_CODIGO, length = 20)
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
	@Column(name = Municipio.COLUMN_NAME_NOMBRE, length = 80)
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

	/**
	 * @return the provincia
	 */
	@ManyToOne(targetEntity = Provincia.class, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = Municipio.COLUMN_NAME_PROVINCIA)
	public Provincia getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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
		if (!(obj instanceof Municipio)) {
			return false;
		}

		final Municipio other = (Municipio) obj;

		if (!getCodigo().equals(other.getCodigo())) {
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
		result += ((id == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getCodigo() == null) ? 0 : getCodigo().hashCode());

		return result;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(COLUMN_NAME_ID, getId())
				.append(COLUMN_NAME_CODIGO, getCodigo()).append(
						COLUMN_NAME_NOMBRE, getNombre()).toString();
	}

	@Override
	public Municipio newInstance() {

		return new Municipio();
	}

}