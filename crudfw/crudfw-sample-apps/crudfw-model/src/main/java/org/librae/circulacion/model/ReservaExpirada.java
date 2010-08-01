package org.librae.circulacion.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.catalogacion.model.Ejemplar;
import org.librae.common.model.BaseObject;

import org.librae.lectores.model.Lector;
import org.librae.lectores.model.TipoLector;

/**
 * Reservas expiradas.<br>
 * <br>
 * permite sustituir los localizadores antiguos por los nuevos
 * 
 * @author aropero
 */
// @Entity(name = ReservaExpirada.ENTITY_NAME)
// @Table(name = ReservaExpirada.TABLE_NAME)
public class ReservaExpirada extends BaseObject {

    /**
     * BaseObject is Serializable, so ReservaExpirada needs a Serial Version UID
     */
    private static final long   serialVersionUID               = -5100155092910964704L;

    public static final String  ENTITY_NAME                    = "org.librae.circulacion.model.ReservaExpirada";
    public static final String  TABLE_NAME                     = "CIR_RES_RESERVAS_EXPIRADAS";
    public static final String  ID_GENERATOR_NAME              = "generator_cir_reservas_exp";
    private static final String ID_SEQUENCE_NAME               = "SEQ_CIR_RESERVAS_EXP";
    public static final String  COLUMN_NAME_ID                 = "X_RESERVA_EXP";
    public static final String  COLUMN_NAME_FECHA_IMPR_LOCAL   = "F_IMPRESION_LOC";
    public static final String  COLUMN_NAME_EJEMPLAR           = "EJE_X_EJEMPLAR";
    public static final String  COLUMN_NAME_LECTOR             = "LEC_X_LECTOR";
    public static final String  COLUMN_NAME_TIPO_LECTOR        = "LEC_TIPO_X_TIPO_LECTOR";
    public static final String  COLUMN_NAME_RESERVA_ACTIVA     = "RES_HIST_X_RESERVA_H_A";
    public static final String  COLUMN_NAME_RESERVA_EXPIRADA   = "RES_HIST_X_RESERVA_H_E";

    public static final String  PROPERTY_NAME_ID               = "id";
    public static final String  PROPERTY_NAME_FECHA_IMPR_LOCAL = "fechaImpresionLocalizador";
    public static final String  PROPERTY_NAME_EJEMPLAR         = "ejemplar";
    public static final String  PROPERTY_NAME_LECTOR           = "lector";
    public static final String  PROPERTY_NAME_TIPO_LECTOR      = "tipoLector";
    public static final String  PROPERTY_NAME_RESERVA_ACTIVA   = "reservaActiva";
    public static final String  PROPERTY_NAME_RESERVA_EXPIRADA = "reservaExpirada";

    /**
     * Identificador interno del parámetro
     */
    private Long                id;

    /**
     * Indica la última fecha en que fue impreso el localizador correspondiente.
     * Las filas que no tienen dato en esta columna corresponden a localizadores
     * que no han sido impresos.
     */
    private Date                fechaImpresionLocalizador;

    /**
     * Ejemplar de la reserva
     */
    private Ejemplar            ejemplar;

    /**
     * Identificación del lector en su biblioteca. Este valor debe ser único
     * para cada lector en su biblioteca.
     */
    private Lector              lector;

    /**
     * >> LEC_TIPO_LECTORES
     */
    private TipoLector          tipoLector;

    /**
     * Reserva (activa) a la que pasa el turno
     */
    private ReservaHistorico    reservaActiva;

    /**
     * Reserva (expirada)
     */
    private ReservaHistorico    reservaExpirada;

    protected ReservaExpirada() {
        super();
    }

    public ReservaExpirada(final Lector lector, final Ejemplar ejemplar) {
        super();
        this.lector = lector;
        this.ejemplar = ejemplar;
    }

    public ReservaExpirada(final Lector lector,
            final ReservaHistorico reservaActiva,
            final ReservaHistorico reservaExpirada) {
        super();
        this.lector = lector;
        this.reservaActiva = reservaActiva;
        this.reservaExpirada = reservaExpirada;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ReservaExpirada.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ReservaExpirada.ID_GENERATOR_NAME, sequenceName = ReservaExpirada.ID_SEQUENCE_NAME)
    @Column(name = ReservaExpirada.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the fechaImpresionLocalizador
     */
    @Column(name = ReservaExpirada.COLUMN_NAME_FECHA_IMPR_LOCAL)
    public Date getFechaImpresionLocalizador() {
        return fechaImpresionLocalizador;
    }

    /**
     * @param fechaImpresionLocalizador
     *            the fechaImpresionLocalizador to set
     */
    public void setFechaImpresionLocalizador(Date fechaImpresionLocalizador) {
        this.fechaImpresionLocalizador = fechaImpresionLocalizador;
    }

    /**
     * @return the ejemplar
     */
    @ManyToOne(targetEntity = Ejemplar.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = ReservaExpirada.COLUMN_NAME_EJEMPLAR)
    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    /**
     * @param ejemplar
     *            the ejemplar to set
     */
    public void setEjemplar(final Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    /**
     * @return the lector
     */
    @ManyToOne(targetEntity = Lector.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = ReservaExpirada.COLUMN_NAME_LECTOR)
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector
     *            the lector to set
     */
    public void setLector(final Lector lector) {
        this.lector = lector;
    }

    /**
     * @return the lectorTipo
     */
    @ManyToOne(targetEntity = TipoLector.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = ReservaExpirada.COLUMN_NAME_TIPO_LECTOR)
    public TipoLector getTipoLector() {
        return tipoLector;
    }

    /**
     * @param lectorTipo
     *            the lectorTipo to set
     */
    public void setTipoLector(final TipoLector tipoLector) {
        this.tipoLector = tipoLector;
    }

    /**
     * @return the reservaActiva
     */
    @ManyToOne(targetEntity = ReservaHistorico.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = ReservaExpirada.COLUMN_NAME_RESERVA_ACTIVA)
    public ReservaHistorico getReservaActiva() {
        return reservaActiva;
    }

    /**
     * @param reservaActiva
     *            the reservaActiva to set
     */
    public void setReservaActiva(ReservaHistorico reservaActiva) {
        this.reservaActiva = reservaActiva;
    }

    /**
     * @return the reservaExpirada
     */
    @ManyToOne(targetEntity = ReservaHistorico.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = ReservaExpirada.COLUMN_NAME_RESERVA_EXPIRADA)
    public ReservaHistorico getReservaExpirada() {
        return reservaExpirada;
    }

    /**
     * @param reservaExpirada
     *            the reservaExpirada to set
     */
    public void setReservaExpirada(ReservaHistorico reservaExpirada) {
        this.reservaExpirada = reservaExpirada;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof ReservaExpirada)) {
            return false;
        }

        final ReservaExpirada other = (ReservaExpirada) obj;

        if (getLector() == null && other.getLector() != null) {
            return false;
        }
        if (getLector() != null && !getLector().equals(other.getLector())) {
            return false;
        }

        if (getEjemplar() == null && other.getEjemplar() != null) {
            return false;
        }
        if (getEjemplar() != null && !getEjemplar().equals(other.getEjemplar())) {
            return false;
        }

        if (getFechaImpresionLocalizador() == null
                && other.getFechaImpresionLocalizador() != null) {
            return false;
        }
        if (getFechaImpresionLocalizador() != null
                && !getFechaImpresionLocalizador().equals(
                        other.getFechaImpresionLocalizador())) {
            return false;
        }

        return true;

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getLector() == null) ? 0 : getLector().hashCode());

        result = prime * result
                + ((getEjemplar() == null) ? 0 : getEjemplar().hashCode());

        result = prime
                * result
                + ((getFechaImpresionLocalizador() == null) ? 0
                        : getFechaImpresionLocalizador().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(ReservaExpirada.PROPERTY_NAME_ID, getId())

        .append(ReservaExpirada.PROPERTY_NAME_LECTOR,
                (getLector() == null) ? "" : getLector().toString())

        .append(ReservaExpirada.PROPERTY_NAME_EJEMPLAR,
                (getEjemplar() == null) ? "" : getEjemplar().toString())

        .append(
                ReservaExpirada.PROPERTY_NAME_FECHA_IMPR_LOCAL,
                (getFechaImpresionLocalizador() == null) ? ""
                        : getFechaImpresionLocalizador().toString())

        .toString();
    }
}