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

@Entity(name = Encuadernacion.ENTITY_NAME)
@Table(name = Encuadernacion.TABLE_NAME)
public class Encuadernacion extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = -6731837305602163504L;

	public static final String  ENTITY_NAME								= "org.librae.pubseriadas.model.Encuadernacion";

	/**
     * Constantes para referencia de los atributos de Encuadernacion
     */
    public static final String        PROPTY_NAME_ID                        = "id";
    public static final String		  PROPTY_NAME_NOTA						= "nota";
    public static final String		  PROPTY_NAME_TIPO_ENCUADERNACION		= "tipoEncuadernacion";

    public static final String	TABLE_NAME								= "SER_ENCUADERNACIONES";
	private static final String ID_GENERATOR_NAME						= "generator_ser_encuadernacion";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_ENCUADERNACIONES";
    public static final String COLUMN_NAME_ID 							= "X_ENCUADERNACION";
    public static final String COLUMN_NAME_NOTA 						= "T_NOTA";
    public static final String COLUMN_NAME_TIPO_ENCUADERNACION 			= "T_TIPO_ENCUADERNACION";


	/**
	 * clave primaria
	 */
	private Long id;

	/**
	 *
	 */
	private String nota;

	/**
	 *
	 */
	private String tipoEncuadernacion;

	protected Encuadernacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Encuadernacion(String nota, String tipoEncuadernacion) {
		super();
		this.nota = nota;
		this.tipoEncuadernacion = tipoEncuadernacion;
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

	@Column(name = COLUMN_NAME_NOTA, length = 400)
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@Column(name = COLUMN_NAME_TIPO_ENCUADERNACION, length = 40)
	public String getTipoEncuadernacion() {
		return tipoEncuadernacion;
	}

	public void setTipoEncuadernacion(String tipoEncuadernacion) {
		this.tipoEncuadernacion = tipoEncuadernacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Encuadernacion))
			return false;
		Encuadernacion other = (Encuadernacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (tipoEncuadernacion == null) {
			if (other.tipoEncuadernacion != null)
				return false;
		} else if (!tipoEncuadernacion.equals(other.tipoEncuadernacion))
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
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime
				* result
				+ ((tipoEncuadernacion == null) ? 0 : tipoEncuadernacion
						.hashCode());
		return result;
	}

	@Override
	public String toString() {
        return new ToStringBuilder(this)
        		.append("id", this.getId())
        		.append("nota", this.getNota())
        		.append("tipoEncuadernacion", this.getTipoEncuadernacion())
        		.toString();
	}

}
