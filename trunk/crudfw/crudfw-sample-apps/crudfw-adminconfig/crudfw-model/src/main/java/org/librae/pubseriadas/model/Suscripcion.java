package org.librae.pubseriadas.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.catalogacion.model.Registro;
import org.librae.common.model.BaseObject;

@Entity(name = Suscripcion.ENTITY_NAME)
@Table(name = Suscripcion.TABLE_NAME)
public class Suscripcion extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1714142177203728304L;

	public static final String	ENTITY_NAME								= "org.librae.pubseriadas.model.Suscripcion";

	/**
     * Constantes para referencia de los atributos de Suscripcion
     */
    public static final String        PROPTY_NAME_ID                        = "id";
    public static final String        PROPTY_NAME_NUMERO					= "numero";
    public static final String        PROPTY_NAME_NOTA						= "nota";
    public static final String        PROPTY_NAME_NUMERACION				= "numeracion";
    public static final String 		  PROPTY_NAME_PERIODICIDAD              = "periodicidad";
    public static final String 		  PROPTY_NAME_REGISTRO                  = "registro";
    public static final String        PROPTY_NAME_NUMEROS					= "numerosSuscripcion";
    public static final String		  PROPTY_NAME_DATOSFONDO				= "datosFondo";
    public static final String        PROPTY_NAME_PEDIDO_SUSCRIPCION		= "pedidoSuscripcion";
    public static final String		  PROPTY_NAME_BIBLIOTECA				= "biblioteca";
	public static final String 		  PROPTY_NAME_EXCEPCIONES 				= "excepciones";
	public static final String		  PROPTY_NAME_FECHA_INICIO				= "fechaInicio";
	public static final String		  PROPTY_NAME_FECHA_FINAL				= "fechaFinal";

    public static final String	TABLE_NAME								= "SER_SUSCRIPCIONES";
    private static final String	ID_GENERATOR_NAME						= "generator_ser_suscripciones";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_SUSCRIPCIONES";
    public static final String COLUMN_NAME_ID 							= "X_SUSCRIPCION";
    public static final String COLUMN_NAME_NUMERO 						= "N_NUMERO";
    public static final String COLUMN_NAME_NOTA 						= "T_NOTA";
    public static final String COLUMN_NAME_NUMERACION 					= "T_NUMERACION";
    public static final String COLUMN_NAME_PERIODICIDAD_FK				= "PER_X_PERIODICIDAD";
    public static final String COLUMN_NAME_REGISTRO_FK                  = "REG_X_REGISTRO";
    public static final String COLUMN_NAME_NUMEROS_FK				    = "NUM_X_NUM";
    public static final String COLUMN_NAME_DATOSFONDO_FK				= "DAT_X_DATOSFONDO";
    public static final String COLUMN_NAME_PEDIDO_SUSCRIPCION			= "PED_X_PEDIDO";
    public static final String COLUMN_NAME_BIBLIOTECA_FK				= "BIB_X_BIBLIOTECA_FK";
    public static final String COLUMN_NAME_FECHA_INICIO					= "F_INICIO";
    public static final String COLUMN_NAME_FECHA_FINAL					= "F_FINAL";

    public static final String JOIN_SUSCRIPCION_X_EXCEPCION				= "SER_SUSCRIPCION_X_EXCEPCION";
    public static final String COLUMN_NAME_EXCEPCION_FK					= "EXC_X_EXCEPCION";
    public static final String COLUMN_NAME_SUSCRIPCION_FK				= "SUS_X_SUSCRIPCION";

	/**
	 * clave primaria
	 */
	private Long id;

	/**
	 *
	 */
	private Long numero;

	/**
	 *
	 */
	private String nota;

	/**
	 *
	 */
	private String numeracion;

	private Periodicidad periodicidad;

	private Registro registro;

	private List<Numero> numerosSuscripcion;

	private DatosFondo datosFondo;

//	private List<PedidoSuscripcion> pedidosSuscripcion;
	private PedidoSuscripcion pedidoSuscripcion;

	private Biblioteca biblioteca;

	private List<Excepcion> excepciones;

	private Date fechaInicio;

	private Date fechaFinal;


	protected Suscripcion() {
		super();
	}

	public Suscripcion(String nota, String numeracion, Long numero) {
		super();
		this.nota = nota;
		this.numeracion = numeracion;
		this.numero = numero;
	}

//	/**
//	 * @return la fecha de inicio de la suscripción; o, si fuera anterior, fecha del primer número RECIBIDO.
//	 */
//	public Date obtenerFechaInicio() {
//		Date fechaResultante = null;
//
//		if(numerosSuscripcion != null && !numerosSuscripcion.isEmpty()) {
//			fechaResultante = numerosSuscripcion.get(0).getFechaRecepcion();
//			for(Numero i : numerosSuscripcion) {
//				Date unaFecha = i.getFechaRecepcion();
//				if(unaFecha != null) {
//					if(fechaResultante == null || unaFecha.before(fechaResultante)) {
//						fechaResultante = unaFecha;
//					}
//				}
//			}
//		}
//
//		// Si no hay recibidos o this.fechaInicio es anterior, devolver this.fechaInicio
//		if(fechaResultante == null || fechaInicio.before(fechaResultante)) {
//			fechaResultante = fechaInicio;
//		}
//		return fechaResultante;
//	}
//
//	/**
//	 * @return la fecha final de la suscripción; o, si fuera posterior, fecha del último número RECIBIDO.
//	 */
//	public Date obtenerFechaFinal() {
//		Date fechaResultante = null;
//
//		if(numerosSuscripcion != null && !numerosSuscripcion.isEmpty()) {
//			fechaResultante = numerosSuscripcion.get(0).getFechaRecepcion();
//			for(Numero i : numerosSuscripcion) {
//				Date unaFecha = i.getFechaRecepcion();
//				if(unaFecha != null) {
//					if(fechaResultante == null || unaFecha.after(fechaResultante)) {
//						fechaResultante = unaFecha;
//					}
//				}
//			}
//		}
//
//		// Si no hay recibidos o this.fechaFinal es posterior, devolver this.fechaFinal
//		if(fechaResultante == null || fechaFinal.after(fechaResultante)) {
//			fechaResultante = fechaFinal;
//		}
//		return fechaResultante;
//	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
	@JoinColumn(name=Suscripcion.COLUMN_NAME_BIBLIOTECA_FK)
	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public void setExcepciones(List<Excepcion> excepciones) {
		this.excepciones = excepciones;
	}

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Excepcion.class, fetch = FetchType.LAZY)
    @JoinTable(name = Suscripcion.JOIN_SUSCRIPCION_X_EXCEPCION, joinColumns = @JoinColumn(name = Suscripcion.COLUMN_NAME_SUSCRIPCION_FK), inverseJoinColumns = @JoinColumn(name = Suscripcion.COLUMN_NAME_EXCEPCION_FK))
	public List<Excepcion> getExcepciones() {
		return excepciones;
	}

//	@OneToMany(targetEntity = PedidoSuscripcion.class, cascade = { CascadeType.ALL })
//	@JoinColumn(name = PedidoSuscripcion.COLUMN_NAME_SUSCRIPCION)
//	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
//	public List<PedidoSuscripcion> getPedidosSuscripcion() {
//		return pedidosSuscripcion;
//	}
//
//	public void setPedidosSuscripcion(List<PedidoSuscripcion> pedidosSuscripcion) {
//		this.pedidosSuscripcion = pedidosSuscripcion;
//	}
	public void setPedidoSuscripcion(PedidoSuscripcion pedidoSuscripcion) {
		this.pedidoSuscripcion = pedidoSuscripcion;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.pubseriadas.model.PedidoSuscripcion.class)
	@JoinColumn(name=Suscripcion.COLUMN_NAME_PEDIDO_SUSCRIPCION)
	public PedidoSuscripcion getPedidoSuscripcion() {
		return pedidoSuscripcion;
	}

	@OneToMany(targetEntity = Numero.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = Numero.COLUMN_NAME_SUSCRIPCION)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
    public List<Numero> getNumerosSuscripcion() {
		return numerosSuscripcion;
	}

	public void setNumerosSuscripcion(List<Numero> numerosSuscripcion) {
		this.numerosSuscripcion = numerosSuscripcion;
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
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Column(name = COLUMN_NAME_NOTA, length = 255)
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@Column(name = COLUMN_NAME_NUMERACION, length = 40)
	public String getNumeracion() {
		return numeracion;
	}

	public void setNumeracion(String numeracion) {
		this.numeracion = numeracion;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.catalogacion.model.Registro.class)
	@JoinColumn(name=Suscripcion.COLUMN_NAME_REGISTRO_FK)
	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.pubseriadas.model.Periodicidad.class)
	@JoinColumn(name=Suscripcion.COLUMN_NAME_PERIODICIDAD_FK)
	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}

	/**
	 * @param datosFondo the datosFondo to set
	 */
	public void setDatosFondo(DatosFondo datosFondo) {
		this.datosFondo = datosFondo;
	}

	/**
	 * @return the datosFondo
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, targetEntity = org.librae.pubseriadas.model.DatosFondo.class)
	@JoinColumn(name=Suscripcion.COLUMN_NAME_DATOSFONDO_FK)
	public DatosFondo getDatosFondo() {
		return datosFondo;
	}

	@Column(name = COLUMN_NAME_FECHA_INICIO)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name = COLUMN_NAME_FECHA_FINAL)
	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Suscripcion))
			return false;
		Suscripcion other = (Suscripcion) obj;
//		if (biblioteca == null) {
//			if (other.biblioteca != null)
//				return false;
//		} else if (!biblioteca.equals(other.biblioteca))
//			return false;
//		if (datosFondo == null) {
//			if (other.datosFondo != null)
//				return false;
//		} else if (!datosFondo.equals(other.datosFondo))
//			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
//		if (nota == null) {
//			if (other.nota != null)
//				return false;
//		} else if (!nota.equals(other.nota))
//			return false;
//		if (numeracion == null) {
//			if (other.numeracion != null)
//				return false;
//		} else if (!numeracion.equals(other.numeracion))
//			return false;
//		if (numero == null) {
//			if (other.numero != null)
//				return false;
//		} else if (!numero.equals(other.numero))
//			return false;
//		if (numerosSuscripcion == null) {
//			if (other.numerosSuscripcion != null)
//				return false;
//		} else if (!numerosSuscripcion.equals(other.numerosSuscripcion))
//			return false;
//		if (pedidoSuscripcion == null) {
//			if (other.pedidoSuscripcion != null)
//				return false;
//		} else if (!pedidoSuscripcion.equals(other.pedidoSuscripcion))
//			return false;
//		if (periodicidad == null) {
//			if (other.periodicidad != null)
//				return false;
//		} else if (!periodicidad.equals(other.periodicidad))
//			return false;
//		if (registro == null) {
//			if (other.registro != null)
//				return false;
//		} else if (!registro.equals(other.registro))
//			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result + ((biblioteca == null) ? 0 : biblioteca.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime * result
				+ ((numeracion == null) ? 0 : numeracion.hashCode());
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
				.append("nota", this.getNota())
				.append("numeracion", this.getNumeracion())
				.toString();
	}

}
