package org.librae.pubseriadas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.common.model.BaseObject;

@Entity(name = ModoVisualizacion.ENTITY_NAME)
@Table(name = ModoVisualizacion.TABLE_NAME)
public class ModoVisualizacion extends BaseObject {

	private static final long serialVersionUID = 3974081924390968670L;

	public static final String ENTITY_NAME								= "org.librae.pubseriadas.model.ModoVisualizacion";
	public static final String TABLE_NAME 								= "SER_MODOS_VISUALIZACION";
	private static final String	ID_GENERATOR_NAME						= "generator_ser_modos_visualizacion";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_MODOS_VISUALIZACION";
    public static final String COLUMN_NAME_ID 							= "X_MODO_VISUALIZACION";
    public static final String COLUMN_NAME_NOMBRE 						= "T_NOMBRE";
    public static final String COLUMN_NAME_DESCRIPCION 					= "T_DESCRIPCION";
    public static final String COLUMN_NAME_CARDINALIDAD 				= "N_CARDINALIDAD";

	public static final String PROPTY_NAME_ID 							= "id";
	public static final String PROPTY_NAME_NOMBRE 						= "nombre";
	public static final String PROPTY_NAME_DESCRIPCION 					= "descripcion";
	public static final String PROPTY_NAME_CARDINALIDAD 				= "cardinalidad";
	public static final String PROPTY_NAME_ETIQUETAS_VISUALIZACION		= "etiquetasVisualizacion";

	// Atributos
	private Long id;

	private String nombre;

	private String descripcion;

	private Long cardinalidad;

	private List<EtiquetaVisualizacion> etiquetasVisualizacion;


	protected ModoVisualizacion() {
		super();
	}

	public ModoVisualizacion(Long cardinalidad, String descripcion, String nombre) {
		super();
		this.cardinalidad = cardinalidad;
		this.descripcion = descripcion;
		this.nombre = nombre;
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

	@Column(name = COLUMN_NAME_DESCRIPCION, length = 80)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = COLUMN_NAME_CARDINALIDAD)
	public Long getCardinalidad() {
		return cardinalidad;
	}

	public void setCardinalidad(Long cardinalidad) {
		this.cardinalidad = cardinalidad;
	}

	@OneToMany(targetEntity = EtiquetaVisualizacion.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = EtiquetaVisualizacion.COLUMN_NAME_MODO_VISUALIZACION_FK)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	public List<EtiquetaVisualizacion> getEtiquetasVisualizacion() {
		return etiquetasVisualizacion;
	}

	public void setEtiquetasVisualizacion(
			List<EtiquetaVisualizacion> etiquetasVisualizacion) {
		this.etiquetasVisualizacion = etiquetasVisualizacion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (getClass() != obj.getClass())
			return false;
		ModoVisualizacion other = (ModoVisualizacion) obj;
		if (cardinalidad == null) {
			if (other.cardinalidad != null)
				return false;
		} else if (!cardinalidad.equals(other.cardinalidad))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
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
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardinalidad == null) ? 0 : cardinalidad.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId()).append(
				"nombre", this.getNombre()).append("descripcion",
				this.getDescripcion()).toString();
	}

}
