package org.librae.pubseriadas.model;

import java.util.Calendar;
import java.util.Set;

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

@Entity(name = TipoPeriodicidad.ENTITY_NAME)
@Table(name = TipoPeriodicidad.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class TipoPeriodicidad extends BaseObject {
	private static final long serialVersionUID = -246584510774414742L;

	public static final String	ENTITY_NAME								= "org.librae.pubseriadas.model.TipoPeriodicidad";

	/**
     * Constantes para referencia de los atributos de Suscripcion
     */
    public static final String      PROPTY_NAME_ID                      = "id";
    public static final String      PROPTY_NAME_NOMBRE					= "nombre";
    public static final String      PROPTY_NAME_DESCRIPCION				= "descripcion";
    public static final String		PROPTY_NAME_CODIGO					= "codigo";
    public static final String		PROPTY_NAME_CARDINALIDAD			= "cardinalidad";

    public static final String		TABLE_NAME							= "SER_TIPOS_PERIODICIDAD";
    private static final String		ID_GENERATOR_NAME					= "generator_ser_tipos_periodicidad";
    private static final String		ID_SEQUENCE_NAME					= "SEQ_SER_TIPOS_PERIODICIDAD";
    public static final String 		COLUMN_NAME_ID 						= "X_TIPO_PERIODICIDAD";
    public static final String 		COLUMN_NAME_NOMBRE					= "T_NOMBRE";
    public static final String 		COLUMN_NAME_DESCRIPCION				= "T_DESCRIPCION";
    public static final String		COLUMN_NAME_CODIGO					= "T_CODIGO";
    public static final String 		COLUMN_NAME_CARDINALIDAD			= "N_CARDINALIDAD";

	private Long id;

	private String nombre;

	private String descripcion;

	private String codigo;

	/**
	 * Constante indicando el campo temporal de repetición. Puede tomar
	 * los siguientes valores:
	 *
	 * Calendar.YEAR 					0x01
	 * Calendar.MONTH					0x02
	 * Calendar.WEEK_OF_YEAR			0x03
	 *
	 *
	 * REFERENCIA:
	 * Calendar.YEAR 					0x01 r
	 * Calendar.MONTH 					0x02 r
	 * Calendar.WEEK_OF_YEAR 			0x03 r
	 * Calendar.WEEK_OF_MONTH			0x04
	 * Calendar.DAY_OF_MONTH 			0x05 r	a31		aka Calendar.DATE	1
	 * Calendar.DAY_OF_YEAR				0x06 	b365						1
	 * Calendar.DAY_OF_WEEK				0x07 	c7							1
	 * Calendar.DAY_OF_WEEK_IN_MONTH	0x08	d4
	 */
	private Integer cardinalidad;



	protected TipoPeriodicidad() {
		super();
	}

	public TipoPeriodicidad(String nombre, String descripcion, String codigo,
			Integer cardinalidad) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.cardinalidad = cardinalidad;
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

	@Column(name = COLUMN_NAME_NOMBRE, length = 40)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = COLUMN_NAME_DESCRIPCION, length = 400)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = COLUMN_NAME_CODIGO, length = 40)
	public String getCodigo() {
		return codigo;
	}

	@Column(name = COLUMN_NAME_CARDINALIDAD)
	public Integer getCardinalidad() {
		return cardinalidad;
	}

	public void setCardinalidad(Integer cardinalidad) {
		this.cardinalidad = cardinalidad;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TipoPeriodicidad))
			return false;
		TipoPeriodicidad other = (TipoPeriodicidad) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result
				+ ((codigo == null) ? 0 : codigo.hashCode());

		return result;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("codigo", this.getCodigo())
			.toString();
	}
}
