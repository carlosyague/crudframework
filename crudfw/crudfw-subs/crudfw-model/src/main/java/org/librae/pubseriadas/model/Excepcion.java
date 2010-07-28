package org.librae.pubseriadas.model;

import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

@Entity(name = Excepcion.ENTITY_NAME)
@Table(name = Excepcion.TABLE_NAME)
public class Excepcion extends BaseObject {
	private static final long serialVersionUID = 5818091903741425262L;

	public static final String	ENTITY_NAME								= "org.librae.pubseriadas.model.Excepcion";

	/**
     * Constantes para referencia de los atributos de Suscripcion
     */
    public static final String      PROPTY_NAME_ID                      = "id";
    public static final String      PROPTY_NAME_FECHA					= "fecha";
    public static final String		PROPTY_NAME_SUSCRIPCIONES			= "suscripciones";

    public static final String		TABLE_NAME							= "SER_EXCEPCIONES";
    private static final String		ID_GENERATOR_NAME					= "generator_ser_excepciones";
    private static final String		ID_SEQUENCE_NAME					= "SEQ_SER_EXCEPCIONES";
    public static final String 		COLUMN_NAME_ID 						= "X_EXCEPCION";
    public static final String 		COLUMN_NAME_FECHA					= "F_FECHA";


	private Long id;

	private Date fecha;

	private List<Suscripcion> suscripciones;

	protected Excepcion() {
		super();
	}

	public Excepcion(Date fecha) {
		super();
		this.fecha = fecha;
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

	@Column(name = COLUMN_NAME_FECHA)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}

    @ManyToMany(mappedBy = Suscripcion.PROPTY_NAME_EXCEPCIONES, targetEntity = org.librae.pubseriadas.model.Suscripcion.class)	
	public List<Suscripcion> getSuscripciones() {
		return suscripciones;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Excepcion))
			return false;
		Excepcion other = (Excepcion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("id", this.getId())
			.append("fecha", this.getFecha())
			.toString();
	}

}
