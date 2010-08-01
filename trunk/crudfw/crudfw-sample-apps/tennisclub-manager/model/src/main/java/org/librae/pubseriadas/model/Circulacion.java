package org.librae.pubseriadas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

@Entity(name = Circulacion.ENTITY_NAME)
@Table(name = Circulacion.TABLE_NAME)
public class Circulacion extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 4907524119920896742L;

	public static final String	ENTITY_NAME								= "org.librae.pubseriadas.model.Circulacion";

	/**
     * Constantes para referencia de los atributos de Circulacion
     */
    public static final String        PROPTY_NAME_ID                        = "id";
    public static final String		  PROPTY_NAME_NUMERO					= "numero";
    public static final String		  PROPTY_NAME_NOMBRE					= "nombre";

    public static final String	TABLE_NAME								= "SER_CIRCULACIONES";
    private static final String	ID_GENERATOR_NAME						= "generator_ser_circulaciones";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_CIRCULACIONES";
	public static final String COLUMN_NAME_ID 							= "X_CIRCULACION";
	public static final String COLUMN_NAME_NUMERO 						= "T_NUMERO";
	public static final String COLUMN_NAME_NOMBRE 						= "T_NOMBRE";

	/**
	 * clave primaria
	 */
	Long id;

	/**
	 *
	 */
	Integer numero;

	/**
	 *
	 */
	String nombre;


	protected Circulacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Circulacion(String nombre, Integer numero) {
		super();
		this.nombre = nombre;
		this.numero = numero;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = COLUMN_NAME_ID)
	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	@Column(name = COLUMN_NAME_NUMERO)
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(name = COLUMN_NAME_NOMBRE, length = 80)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Circulacion))
			return false;
		Circulacion other = (Circulacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation
	 * of this object.
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this)
				.append("id", this.getId())
				.append("numero", this.getNumero())
				.append("nombre", this.getNombre())
				.toString();
	}

}
