package org.librae.adquisicion.model;

import java.math.BigDecimal;
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
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un Presupuesto
 *
 * @author jcdiaz
 */
@Entity(name = PartidaPresupuestaria.NAME_ENTITY)
@Table(name = PartidaPresupuestaria.TABLE_NAME)
public class PartidaPresupuestaria extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Presupuesto necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID           = -8664793251042721123L;

    public static final String  NAME_ENTITY                = "org.librae.adquisicion.model.PartidaPresupuestaria";
    public static final String  TABLE_NAME                 = "ADQ_PARTIDAS_PRESUPUESTARIAS";
    private static final String ID_GENERATOR_NAME          = "generator_adq_partidas_presupuestarias";
    private static final String ID_SEQUENCE_NAME           = "SEQ_ADQ_PART_PRESUPUESTARIAS";
    public static final String  COLUMN_NAME_ID             = "X_PARTIDA_PRESUPUESTARIA";
    public static final String  COLUMN_NAME_FECHA_INICIO   = "F_FECHA_INICIO";
    public static final String  COLUMN_NAME_FECHA_FIN      = "F_FECHA_FIN";
    public static final String  COLUMN_NAME_ASIGNADO       = "I_ASIGNADO";
    public static final String  COLUMN_NAME_COMPROMETIDO   = "I_COMPROMETIDO";
    public static final String  COLUMN_NAME_GASTADO        = "I_GASTADA";
    public static final String  COLUMN_NAME_ACTIVA         = "L_ACTIVA";
    public static final String  COLUMN_NAME_PRESUPUESTO_FK = "PRE_X_PRESUPUESTO";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * fecha de inicio de la partida
     */
    private Date                fechaInicio;

    /**
     * fecha fin de la partida
     */
    private Date                fechaFin;

    /**
     * importe asignado
     */
    private BigDecimal          asignado;

    /**
     * importe comprometido
     */
    private BigDecimal          comprometido;

    /**
     * importe gastado
     */
    private BigDecimal          gastado;

    /**
     * indica si está activo o no
     */
    private Boolean             activa;

    /**
     * referencia del presupuesto asociado
     */
    private Presupuesto         presupuesto;

    /**
     * Constructor sin parámetros
     */
    protected PartidaPresupuestaria() {
    }

    /**
     * Constructor con parámetros
     *
     * @param activa
     * @param asignado
     * @param comprometido
     * @param fechaFin
     * @param fechaInicio
     * @param gastado
     */
    public PartidaPresupuestaria(Boolean activa, BigDecimal asignado,
            BigDecimal comprometido, Date fechaFin, Date fechaInicio,
            BigDecimal gastado) {
        this.activa = activa;
        this.asignado = asignado;
        this.comprometido = comprometido;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.gastado = gastado;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = PartidaPresupuestaria.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = PartidaPresupuestaria.ID_SEQUENCE_NAME)
    @Column(name = PartidaPresupuestaria.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fechaInicio
     */
    @Column(name = PartidaPresupuestaria.COLUMN_NAME_FECHA_INICIO)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio
     *            the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    @Column(name = PartidaPresupuestaria.COLUMN_NAME_FECHA_FIN)
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin
     *            the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the asignado
     */
    @Column(name = PartidaPresupuestaria.COLUMN_NAME_ASIGNADO)
    public BigDecimal getAsignado() {
        return asignado;
    }

    /**
     * @param asignado
     *            the asignado to set
     */
    public void setAsignado(BigDecimal asignado) {
        this.asignado = asignado;
    }

    /**
     * @return the comprometido
     */
    @Column(name = PartidaPresupuestaria.COLUMN_NAME_COMPROMETIDO)
    public BigDecimal getComprometido() {
        return comprometido;
    }

    /**
     * @param comprometido
     *            the comprometido to set
     */
    public void setComprometido(BigDecimal comprometido) {
        this.comprometido = comprometido;
    }

    /**
     * @return the gastado
     */
    @Column(name = PartidaPresupuestaria.COLUMN_NAME_GASTADO)
    public BigDecimal getGastado() {
        return gastado;
    }

    /**
     * @param gastado
     *            the gastado to set
     */
    public void setGastado(BigDecimal gastado) {
        this.gastado = gastado;
    }

    /**
     * @return the activa
     */
    @Column(name = PartidaPresupuestaria.COLUMN_NAME_ACTIVA)
    public Boolean getActiva() {
        return activa;
    }

    /**
     * @param activa
     *            the activa to set
     */
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    /**
     * @return the presupuesto
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = Presupuesto.class)
    @JoinColumn(name = COLUMN_NAME_PRESUPUESTO_FK)
    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    /**
     * @param presupuesto
     *            the presupuesto to set
     */
    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((activa == null) ? 0 : activa.hashCode());
        result = prime * result
                + ((asignado == null) ? 0 : asignado.hashCode());
        result = prime * result
                + ((comprometido == null) ? 0 : comprometido.hashCode());
        result = prime * result
                + ((fechaFin == null) ? 0 : fechaFin.hashCode());
        result = prime * result
                + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
        result = prime * result + ((gastado == null) ? 0 : gastado.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((presupuesto == null) ? 0 : presupuesto.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PartidaPresupuestaria)) {
            return false;
        }
        PartidaPresupuestaria other = (PartidaPresupuestaria) obj;
        if (activa == null) {
            if (other.activa != null) {
                return false;
            }
        } else if (!activa.equals(other.activa)) {
            return false;
        }
        if (asignado == null) {
            if (other.asignado != null) {
                return false;
            }
        } else if (!asignado.equals(other.asignado)) {
            return false;
        }
        if (comprometido == null) {
            if (other.comprometido != null) {
                return false;
            }
        } else if (!comprometido.equals(other.comprometido)) {
            return false;
        }
        if (fechaFin == null) {
            if (other.fechaFin != null) {
                return false;
            }
        } else if (!fechaFin.equals(other.fechaFin)) {
            return false;
        }
        if (fechaInicio == null) {
            if (other.fechaInicio != null) {
                return false;
            }
        } else if (!fechaInicio.equals(other.fechaInicio)) {
            return false;
        }
        if (gastado == null) {
            if (other.gastado != null) {
                return false;
            }
        } else if (!gastado.equals(other.gastado)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (presupuesto == null) {
            if (other.presupuesto != null) {
                return false;
            }
        } else if (!presupuesto.equals(other.presupuesto)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(PartidaPresupuestaria.COLUMN_NAME_ID, this.id)
                .append(PartidaPresupuestaria.COLUMN_NAME_FECHA_INICIO,
                        this.fechaInicio)
                .append(PartidaPresupuestaria.COLUMN_NAME_FECHA_FIN,
                        this.fechaFin)
                .append(PartidaPresupuestaria.COLUMN_NAME_ASIGNADO,
                        this.asignado)
                .append(PartidaPresupuestaria.COLUMN_NAME_GASTADO, this.gastado)
                .append(PartidaPresupuestaria.COLUMN_NAME_COMPROMETIDO,
                        this.comprometido).append(
                        PartidaPresupuestaria.COLUMN_NAME_ACTIVA, this.activa)
                .toString();
    }

}