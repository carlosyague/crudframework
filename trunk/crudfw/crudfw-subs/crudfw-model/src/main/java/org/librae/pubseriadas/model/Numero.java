package org.librae.pubseriadas.model;

import java.util.Date;

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
import org.librae.catalogacion.model.EjemplarSoporte;
import org.librae.common.model.BaseObject;

@Entity(name = Numero.ENTITY_NAME)
@Table(name = Numero.TABLE_NAME)
public class Numero extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 3933924942687523287L;

	public static final String	ENTITY_NAME								= "org.librae.pubseriadas.model.Numero";

	/**
     * Constantes para referencia de los atributos de Numero
     */
    public static final String        PROPTY_NAME_ID                        = "id";
    public static final String		  PROPTY_NAME_FECHA_PREVISTA_RECEPCION	= "fechaPrevistaRecepcion";
    public static final String		  PROPTY_NAME_RECEPCION					= "fechaRecepcion";
    public static final String		  PROPTY_NAME_NUMERO					= "numero";
    public static final String		  PROPTY_NAME_NOTA						= "nota";
    public static final String        PROPTY_NAME_SUSCRIPCION 			    = "suscripcion";
    public static final String        PROPTY_NAME_ESTADO_NUMERO             = "estadoNumero";
	public static final String		  PROPTY_NAME_NUMERO_ADJUNTO1			= "numeroAdjunto1";
	public static final String		  PROPTY_NAME_SOPOERTE_ADJUNTO1			= "soporteAdjunto1";
	public static final String		  PROPTY_NAME_COMENTARIO_ADJUNTO1		= "comentarioAdjunto1";
	public static final String		  PROPTY_NAME_NUMERO_ADJUNTO2			= "numeroAdjunto2";
	public static final String		  PROPTY_NAME_SOPOERTE_ADJUNTO2			= "soporteAdjunto2";
	public static final String		  PROPTY_NAME_COMENTARIO_ADJUNTO2		= "comentarioAdjunto2";
	public static final String		  PROPTY_NAME_NUMERO_ADJUNTO3			= "numeroAdjunto3";
	public static final String		  PROPTY_NAME_SOPOERTE_ADJUNTO3			= "soporteAdjunto3";
	public static final String		  PROPTY_NAME_COMENTARIO_ADJUNTO3		= "comentarioAdjunto3";


    public static final String	TABLE_NAME								= "SER_NUMEROS";
    private static final String	ID_GENERATOR_NAME						= "generator_ser_numeros";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_NUMEROS";
    public static final String	COLUMN_NAME_ID							= "X_NUMERO";
    public static final String	COLUMN_NAME_FECHA_PREVISTA_RECEPCION	= "F_PREVISTA_RECEPCION";
    public static final String	COLUMN_NAME_FECHA_RECEPCION				= "F_RECEPCION";
    public static final String	COLUMN_NAME_NUMERO 						= "T_NUMERO";
    public static final String  COLUMN_NAME_NOTA						= "T_NOTA";
    public static final String  COLUMN_NAME_SUSCRIPCION                 = "SUS_X_SUSCRIPCION";
    public static final String  COLUMN_NAME_ESTADO_NUMERO_FK            = "EST_X_ESTADO_NUMERO";
    public static final String	COLUMN_NAME_NUMERO_ADJUNTO1				= "N_NUMERO_ADJUNTO1";
    public static final String	COLUMN_NAME_SOPORTE_ADJUNTO1			= "SOP_X_CAT_EJEMPLAR_SOPORTES1";
    public static final String	COLUMN_NAME_COMENTARIO_ADJUNTO1			= "T_COMENTARIO_ADJUNTO1";
    public static final String	COLUMN_NAME_NUMERO_ADJUNTO2				= "N_NUMERO_ADJUNTO2";
    public static final String	COLUMN_NAME_SOPORTE_ADJUNTO2			= "SOP_X_CAT_EJEMPLAR_SOPORTES2";
    public static final String	COLUMN_NAME_COMENTARIO_ADJUNTO2			= "T_COMENTARIO_ADJUNTO2";
    public static final String	COLUMN_NAME_NUMERO_ADJUNTO3				= "N_NUMERO_ADJUNTO3";
    public static final String	COLUMN_NAME_SOPORTE_ADJUNTO3			= "SOP_X_CAT_EJEMPLAR_SOPORTES3";
    public static final String	COLUMN_NAME_COMENTARIO_ADJUNTO3			= "T_COMENTARIO_ADJUNTO3";

	/**
	 * clave primaria
	 */
	private Long id;

	/**
	 * fecha prevista de recepción
	 */
	private Date fechaPrevistaRecepcion;

	/**
	 * fecha de recepción
	 */
	private Date fechaRecepcion;


	/**
	 * suscripción a la que pertenece el número
	 */
	private Suscripcion suscripcion;

	private String numero;

	private String nota;

	/**
	 * estado del número
	 */
	private EstadoNumero estadoNumero;

	private Integer				numeroAdjunto1;
	private EjemplarSoporte 	soporteAdjunto1;
	private String				comentarioAdjunto1;
	private Integer				numeroAdjunto2;
	private EjemplarSoporte 	soporteAdjunto2;
	private String				comentarioAdjunto2;
	private Integer				numeroAdjunto3;
	private EjemplarSoporte 	soporteAdjunto3;
	private String				comentarioAdjunto3;

	protected Numero() {
		super();
	}

	public Numero(Date fechaPrevistaRecepcion, Date fechaRecepcion,
			String numero) {
		super();
		this.fechaPrevistaRecepcion = fechaPrevistaRecepcion;
		this.fechaRecepcion = fechaRecepcion;
		this.numero = numero;
	}

	public Numero(Date fechaPrevistaRecepcion, Date fechaRecepcion,
			String numero, String nota, Suscripcion suscripcion) {
		super();
		this.fechaPrevistaRecepcion = fechaPrevistaRecepcion;
		this.fechaRecepcion = fechaRecepcion;
		this.numero = numero;
		this.setNota(nota);
		this.suscripcion = suscripcion;
		this.soporteAdjunto1 = null;
		this.soporteAdjunto2 = null;
		this.soporteAdjunto3 = null;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.pubseriadas.model.EstadoNumero.class)
    @JoinColumn(name = Numero.COLUMN_NAME_ESTADO_NUMERO_FK)
	public EstadoNumero getEstadoNumero() {
		return estadoNumero;
	}

	public void setEstadoNumero(EstadoNumero estadoNumero) {
		this.estadoNumero = estadoNumero;
	}

	@Column(name = COLUMN_NAME_NUMERO_ADJUNTO1)
	public Integer getNumeroAdjunto1() {
		return numeroAdjunto1;
	}

	public void setNumeroAdjunto1(Integer numeroAdjunto1) {
		this.numeroAdjunto1 = numeroAdjunto1;
	}
	public void setSoporteAdjunto1(EjemplarSoporte soporteAdjunto1) {
		this.soporteAdjunto1 = soporteAdjunto1;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = EjemplarSoporte.class)
	@JoinColumn(name = Numero.COLUMN_NAME_SOPORTE_ADJUNTO1)
	public EjemplarSoporte getSoporteAdjunto1() {
		return soporteAdjunto1;
	}

	@Column(name = COLUMN_NAME_COMENTARIO_ADJUNTO1, length = 255)
	public String getComentarioAdjunto1() {
		return comentarioAdjunto1;
	}

	public void setComentarioAdjunto1(String comentarioAdjunto1) {
		this.comentarioAdjunto1 = comentarioAdjunto1;
	}
	@Column(name = COLUMN_NAME_NUMERO_ADJUNTO2)
	public Integer getNumeroAdjunto2() {
		return numeroAdjunto2;
	}

	public void setNumeroAdjunto2(Integer numeroAdjunto2) {
		this.numeroAdjunto2 = numeroAdjunto2;
	}
	public void setSoporteAdjunto2(EjemplarSoporte soporteAdjunto2) {
		this.soporteAdjunto2 = soporteAdjunto2;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = EjemplarSoporte.class)
	@JoinColumn(name = Numero.COLUMN_NAME_SOPORTE_ADJUNTO2)
	public EjemplarSoporte getSoporteAdjunto2() {
		return soporteAdjunto2;
	}

	@Column(name = COLUMN_NAME_COMENTARIO_ADJUNTO2, length = 255)
	public String getComentarioAdjunto2() {
		return comentarioAdjunto2;
	}

	public void setComentarioAdjunto2(String comentarioAdjunto2) {
		this.comentarioAdjunto2 = comentarioAdjunto2;
	}
	@Column(name = COLUMN_NAME_NUMERO_ADJUNTO3)
	public Integer getNumeroAdjunto3() {
		return numeroAdjunto3;
	}

	public void setNumeroAdjunto3(Integer numeroAdjunto3) {
		this.numeroAdjunto3 = numeroAdjunto3;
	}
	public void setSoporteAdjunto3(EjemplarSoporte soporteAdjunto3) {
		this.soporteAdjunto3 = soporteAdjunto3;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = EjemplarSoporte.class)
	@JoinColumn(name = Numero.COLUMN_NAME_SOPORTE_ADJUNTO3)
	public EjemplarSoporte getSoporteAdjunto3() {
		return soporteAdjunto3;
	}

	@Column(name = COLUMN_NAME_COMENTARIO_ADJUNTO3, length = 255)
	public String getComentarioAdjunto3() {
		return comentarioAdjunto3;
	}

	public void setComentarioAdjunto3(String comentarioAdjunto3) {
		this.comentarioAdjunto3 = comentarioAdjunto3;
	}

	@ManyToOne(targetEntity = Suscripcion.class)
    @JoinColumn(name = Numero.COLUMN_NAME_SUSCRIPCION, referencedColumnName = Suscripcion.COLUMN_NAME_ID)
	public Suscripcion getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
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

	@Column(name = COLUMN_NAME_FECHA_PREVISTA_RECEPCION)
	public Date getFechaPrevistaRecepcion() {
		return fechaPrevistaRecepcion;
	}

	public void setFechaPrevistaRecepcion(Date fechaPrevistaRecepcion) {
		this.fechaPrevistaRecepcion = fechaPrevistaRecepcion;
	}

	@Column(name = COLUMN_NAME_FECHA_RECEPCION)
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	@Column(name = COLUMN_NAME_NUMERO, length = 255)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = COLUMN_NAME_NOTA)
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Numero))
			return false;
		Numero other = (Numero) obj;
		if (estadoNumero == null) {
			if (other.estadoNumero != null)
				return false;
		} else if (!estadoNumero.equals(other.estadoNumero))
			return false;
		if (fechaPrevistaRecepcion == null) {
			if (other.fechaPrevistaRecepcion != null)
				return false;
		} else if (!fechaPrevistaRecepcion.equals(other.fechaPrevistaRecepcion))
			return false;
		if (fechaRecepcion == null) {
			if (other.fechaRecepcion != null)
				return false;
		} else if (!fechaRecepcion.equals(other.fechaRecepcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (suscripcion == null) {
			if (other.suscripcion != null)
				return false;
		} else if (!suscripcion.equals(other.suscripcion))
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
		result = prime
				* result
				+ ((fechaPrevistaRecepcion == null) ? 0
						: fechaPrevistaRecepcion.hashCode());
		result = prime * result
				+ ((fechaRecepcion == null) ? 0 : fechaRecepcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		return result;
	}

	@Override
	public String toString() {
        return new ToStringBuilder(this)
        		.append("id", this.getId())
        		.append("fechaPrevistaRecepcion", this.getFechaPrevistaRecepcion())
        		.append("fechaRecepcion", this.getFechaRecepcion())
        		.append("numero", this.getNumero())
        		.append("nota", this.getNota())
        		.toString();
	}

}
