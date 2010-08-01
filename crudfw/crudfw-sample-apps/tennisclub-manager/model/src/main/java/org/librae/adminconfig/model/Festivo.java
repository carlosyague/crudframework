package org.librae.adminconfig.model;

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
 * Una fila por cada día festivo de un calendario
 * 
 * @author asantamaria
 */
@Entity(name = Festivo.ENTITY_NAME)
@Table(name = Festivo.TABLE_NAME)
public class Festivo extends BaseObject {

    /**
     * BaseObject is Serializable, so Festivo needs a Serial Version UID
     */
    private static final long   serialVersionUID          = 4948100197937253027L;

    public static final String  ENTITY_NAME               = "org.librae.adminconfig.model.Festivo";
    public static final String  TABLE_NAME                = "ADM_FESTIVOS";
    private static final String ID_GENERATOR_NAME         = "generator_adm_festivos";
    private static final String ID_SEQUENCE_NAME          = "SEQ_ADM_FESTIVOS";
    public static final String  COLUMN_NAME_ID            = "X_FESTIVO";
    public static final String  COLUMN_NAME_FECHA_FESTIVO = "F_FESTIVO";
    public static final String  COLUMN_NAME_CALENDARIO    = "CAL_X_CALENDARIO";

    /**
     * Clave primaria artificial, que identifica de forma única cada fila
     */
    private Long                id;

    /**
     * Fecha correspondiente a un día festivo en el calendario
     */
    private Date                fechaFestivo;

    /**
     * Clave foránea a Calendarios. El calendario al que pertenecen los
     * festivos.
     */
    private Calendario          calendario;

    protected Festivo() {
        super();
    }

    public Festivo(Calendario calendario, Date fechaFestivo) {
        super();
        this.calendario = calendario;
        this.fechaFestivo = fechaFestivo;
    }

    public Festivo(Date fechaFestivo) {
        super();
        this.fechaFestivo = fechaFestivo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Festivo.COLUMN_NAME_ID)
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
     * @return the fechaFestivo
     */
    @Column(name = Festivo.COLUMN_NAME_FECHA_FESTIVO, nullable = false)
    public Date getFechaFestivo() {
        return fechaFestivo;
    }

    /**
     * @param fechaFestivo
     *            the fechaFestivo to set
     */
    public void setFechaFestivo(Date fechaFestivo) {
        this.fechaFestivo = fechaFestivo;
    }

    /**
     * @return the calendario
     */
    @ManyToOne(targetEntity = Calendario.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = Festivo.COLUMN_NAME_CALENDARIO)
    public Calendario getCalendario() {
        return calendario;
    }

    /**
     * @param calendario
     *            the calendario to set
     */
    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Festivo)) {
            return false;
        }

        final Festivo other = (Festivo) obj;

        if (getFechaFestivo() == null && other.getFechaFestivo() != null) {
            return false;
        }
        if (getFechaFestivo() != null
                && !getFechaFestivo().equals(other.getFechaFestivo())) {
            return false;
        }

        if (getCalendario() == null && other.getCalendario() != null) {
            return false;
        }
        if (getCalendario() != null
                && !getCalendario().equals(other.getCalendario())) {
            return false;
        }

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
        result += ((id == null) ? 0 : getId().hashCode());

        result = prime
                * result
                + ((getFechaFestivo() == null) ? 0 : getFechaFestivo()
                        .hashCode());

        result = prime * result
                + ((getCalendario() == null) ? 0 : getCalendario().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(COLUMN_NAME_ID, getId())
                .append(
                        COLUMN_NAME_CALENDARIO,
                        (getCalendario() == null) ? 0 : getCalendario()
                                .toString()).append(
                        COLUMN_NAME_FECHA_FESTIVO,
                        (getFechaFestivo() == null) ? 0 : getFechaFestivo()
                                .toString()).toString();
    }

}