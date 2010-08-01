package org.librae.pubseriadas.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adquisicion.model.Pedido;
import org.librae.adquisicion.model.Proveedor;

@Entity(name = PedidoSuscripcion.ENTITY_NAME)
@Table(name = PedidoSuscripcion.TABLE_NAME)
public class PedidoSuscripcion { // extends Pedido {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7335504339596703614L;
	
	public static final String  ENTITY_NAME								= "org.librae.pubseriadas.model.PedidoSuscripcion";
	
	/**
     * Constantes para referencia de los atributos de PedidoSuscripcion
     */
    public static final String        PROPTY_NAME_ID                        = "id";
    public static final String        PROPTY_NAME_FECHA_FIN					= "fechaFin";
    public static final String        PROPTY_NAME_FECHA_INICIO				= "fechaInicio";
    public static final String        PROPTY_NAME_FECHA_PARTIDA				= "fPartida";
    public static final String        PROPTY_NAME_SUSCRIPCIONES			    = "suscripciones";
    
    public static final String	TABLE_NAME								= "SER_PEDIDOS_SUSCRIPCION";
	private static final String ID_GENERATOR_NAME						= "generator_ser_pedidos_suscripcion";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_PEDIDOS_SUSCRIPCION";
    public static final String COLUMN_NAME_ID 							= "X_PEDIDO_SUSCRIPCION";
    public static final String COLUMN_NAME_FECHA_FIN 					= "F_FIN";
    public static final String COLUMN_NAME_FECHA_INICIO 				= "F_INICIO";
    public static final String COLUMN_NAME_FECHA_PARTIDA 				= "F_PARTIDA";
//    public static final String  COLUMN_NAME_SUSCRIPCION                 =  "SUS_X_SUSCRIPCION";
	
    /**
	 * clave primaria
	 */
	private Long id;
	
	/**
	 * 
	 */
	private Date fechaFin;
	
	/**
	 * 
	 */
	private Date fechaInicio;
	
	/**
	 * 
	 */
	private Date fPartida;
	
	
	private List<Suscripcion> suscripciones;
	
	

	protected PedidoSuscripcion() {
		super();
	}

	
	public PedidoSuscripcion(String estado, Date fechaPedido, Date fechaPrevista,
            Date fechaReclamacion, String notas, String notasProveedor,
            Integer numeroPedido, Boolean reclamado, String referencia,
			/* Pedido */
			Date partida, Date fechaFin, Date fechaInicio) {
		
//		super(estado, fechaPedido, fechaPrevista, fechaReclamacion, notas,
//				notasProveedor,	numeroPedido, reclamado, referencia);
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		fPartida = partida;
	}
	
//	@ManyToOne(targetEntity = Suscripcion.class)
//    @JoinColumn(name = PedidoSuscripcion.COLUMN_NAME_SUSCRIPCION, referencedColumnName = Suscripcion.COLUMN_NAME_ID)
//	public Suscripcion getSuscripcion() {
//		return suscripcion;
//	}
//
//	public void setSuscripcion(Suscripcion suscripcion) {
//		this.suscripcion = suscripcion;
//	}

	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}
	
//	@OneToMany(mappedBy = Suscripcion.PROPTY_NAME_PEDIDO_SUSCRIPCION, targetEntity = Suscripcion.class, cascade = { CascadeType.ALL })
//	@JoinColumn(name = Suscripcion.COLUMN_NAME_PEDIDO_SUSCRIPCION)
	@OneToMany(mappedBy = Suscripcion.PROPTY_NAME_PEDIDO_SUSCRIPCION, targetEntity = Suscripcion.class)
	public List<Suscripcion> getSuscripciones() {
		return suscripciones;
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



	@Column(name = COLUMN_NAME_FECHA_FIN)
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = COLUMN_NAME_FECHA_INICIO)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name = COLUMN_NAME_FECHA_PARTIDA)
	public Date getFPartida() {
		return fPartida;
	}

	public void setFPartida(Date partida) {
		fPartida = partida;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PedidoSuscripcion))
			return false;
		PedidoSuscripcion other = (PedidoSuscripcion) obj;
		if (fPartida == null) {
			if (other.fPartida != null)
				return false;
		} else if (!fPartida.equals(other.fPartida))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
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
		result = prime * result
				+ ((fPartida == null) ? 0 : fPartida.hashCode());
		result = prime * result
				+ ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result
				+ ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
				.append("fechaFin", this.getFechaFin())
				.append("fechaInicio", this.getFechaInicio())
				.append("fPartida", this.getFPartida())
				.toString();
	}
	
	// TODO:
	/*
	 *  DUMMYS MIENTRAS NO SE INTEGRA LA HERENCIA CON Pedido 
	 *  
	*/
    public Proveedor getProveedor() {
    	return null;
    }
    public void setProveedor(Proveedor proveedor) {
    }
    public Integer getNumeroPedido() {
		return null;
    }
    public void setNumeroPedido(Integer i) {
    }
    public String getEstado() {
		return null;
    }
    public void setEstado(String e) {
    }
    public Boolean getReclamado() {
		return null;
    }
    public void setReclamado(Boolean b) {
    }
    public Date getFechaPedido() {
		return null;
    }
    public void setFechaPedido(Date d) {
    }
    public Date getFechaPrevista() {
		return null;
    }
    public void setFechaPrevista(Date d) {
    }
    public Date getFechaReclamacion() {
		return null;
    }
    public void setFechaReclamacion(Date d) {
    }
    public String getNotas() {
		return null;
    }
    public void setNotas(String n){
    }
    public String getNotasProoveedor(){
		return null;
    }
    public void setNotasProoveedor(String n) {
    }
    public Biblioteca getBiblioteca() {
    	return null;
    }
    public void setBiblioteca(Biblioteca b) {
    }
    /*
     * 
     * FIN DUMMYS
     */
}
