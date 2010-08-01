package org.librae.pubseriadas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

@Entity(name = PedidoSuscripcionRenovacion.ENTITY_NAME)
@Table(name = PedidoSuscripcionRenovacion.TABLE_NAME)
public class PedidoSuscripcionRenovacion extends BaseObject {

    /**
	 *
	 */
	private static final long serialVersionUID = 4974798697263765158L;

	public static final String ENTITY_NAME             			= "org.librae.pubseriadas.model.PedidoSuscripcionRenovacion";

	/**
     * Constantes para referencia de los atributos de PedidoSuscripcionRenovacion
     */
    public static final String        PROPTY_NAME_ID                        = "id";
    public static final String        PROPTY_NAME_FECHA_FIN					= "fechaFin";
    public static final String        PROPTY_NAME_FECHA_INICIO				= "fechaInicio";
    public static final String        PROPTY_NAME_F_RENOVACION				= "fRenovacion";
    public static final String        PROPTY_NAME_NOTA						= "nota";

    public static final String TABLE_NAME              			= "SER_PEDIDOS_SUSCRIP_RENOV";
    private static final String ID_GENERATOR_NAME       		= "generator_ser_pedidos_suscripcion_renovacion";
    private static final String ID_SEQUENCE_NAME        		= "SEQ_SER_PEDIDOS_SUSCRIP_RENOV";
	private static final String COLUMN_NAME_ID 					= "X_PEDIDO_SUSCRIP_RENOV";
	private static final String COLUMN_NAME_FECHA_FIN 			= "F_FIN";
	private static final String COLUMN_NAME_FECHA_INICIO 		= "F_INICIO";
	private static final String COLUMN_NAME_F_RENOVACION 		= "F_RENOVACION";
	private static final String COLUMN_NAME_NOTA 				= "T_NOTA";

    /**
     * clave primaria
     */
    private Long                id;

    /**
	 *
	 */
    private Date                fechaFin;

    /**
	 *
	 */
    private Date                fechaInicio;

    /**
	 *
	 */
    private Date                fRenovacion;

    /**
	 *
	 */
    private String              nota;

    protected PedidoSuscripcionRenovacion() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PedidoSuscripcionRenovacion(Date renovacion, Date fechaFin,
            Date fechaInicio, String nota) {
        super();
        fRenovacion = renovacion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.nota = nota;
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

    @Column(name = COLUMN_NAME_F_RENOVACION)
    public Date getFRenovacion() {
        return fRenovacion;
    }

    public void setFRenovacion(Date renovacion) {
        fRenovacion = renovacion;
    }

    @Column(name = COLUMN_NAME_NOTA, length = 400)
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PedidoSuscripcionRenovacion))
            return false;
        PedidoSuscripcionRenovacion other = (PedidoSuscripcionRenovacion) obj;
        if (fRenovacion == null) {
            if (other.fRenovacion != null)
                return false;
        } else if (!fRenovacion.equals(other.fRenovacion))
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
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result
                + ((fRenovacion == null) ? 0 : fRenovacion.hashCode());
        result = prime * result
                + ((fechaFin == null) ? 0 : fechaFin.hashCode());
        result = prime * result
                + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nota == null) ? 0 : nota.hashCode());
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
				.append("fRenovacion", this.getFRenovacion())
				.append("nota", this.getNota())
				.toString();
	}

}
