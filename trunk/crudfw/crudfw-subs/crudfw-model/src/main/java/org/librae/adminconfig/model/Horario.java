package org.librae.adminconfig.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Un horario es una entidad que indica a que intervalos horarios está abierta
 * una biblioteca al público en los días (laborables) que correspondan a uno o
 * más días de la semana de uno o más rangos.<br>
 * Los intervalos horarios aplicables a los rangos de fechas se guardan en la
 * tabla ADM_HORARIO_INT
 * 
 * @author asantamaria
 */
@Entity(name = Horario.ENTITY_NAME)
@Table(name = Horario.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Horario extends SpringRemotableLazyEntity<Horario> {

    /**
     * BaseObject is Serializable, so Horario needs a Serial Version UID
     */
    private static final long   serialVersionUID                = 8309236974973890139L;

    public static final String  ENTITY_NAME                     = "org.librae.adminconfig.model.Horario";
    public static final String  TABLE_NAME                      = "ADM_HORARIO";
    public static final String  ID_GENERATOR_NAME               = "generator_adm_horario";
    private static final String ID_SEQUENCE_NAME                = "SEQ_ADM_HORARIO";

    public static final String  COLUMN_NAME_ID                  = "X_HORARIO";
    public static final String  COLUMN_NAME_FECHA_ACTUALIZACION = "F_ACT";

    public static final String  PROPTY_NAME_ID                  = "id";
    public static final String  PROPTY_NAME_FECHA_ACTUALIZACION = "fechaActualizacion";
    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Fecha de la ultima actualización del horario
     */
    private Date                fechaActualizacion;

    /**
     * Listado de intervalos de horarios
     */
    private List<HorarioInt>    horariosInt;

    protected Horario() {
        super();
    }

    public Horario(final Date fechaActualizacion) {
        super();
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Horario.COLUMN_NAME_ID)
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
     * @return the fechaActualizacion
     */
    @Column(name = Horario.COLUMN_NAME_FECHA_ACTUALIZACION)
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion
     *            the fechaActualizacion to set
     */
    public void setFechaActualizacion(final Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the horariosInt
     */
    @OneToMany(targetEntity = HorarioInt.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = HorarioInt.COLUMN_NAME_HORARIO)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<HorarioInt> getHorariosInt() {
        return horariosInt;
    }

    /**
     * @param horariosInt
     *            the horariosInt to set
     */
    public void setHorariosInt(final List<HorarioInt> horariosInt) {
        this.horariosInt = horariosInt;
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
        if (!(obj instanceof Horario)) {
            return false;
        }

        final Horario other = (Horario) obj;

        if (getFechaActualizacion() == null
                && other.getFechaActualizacion() != null) {
            return false;
        }
        if (getFechaActualizacion() != null
                && !getFechaActualizacion().equals(
                        other.getFechaActualizacion())) {
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
        result = prime
                * result
                + ((getFechaActualizacion() == null) ? 0
                        : getFechaActualizacion().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(COLUMN_NAME_ID, getId())
                .append(
                        COLUMN_NAME_FECHA_ACTUALIZACION,
                        (getFechaActualizacion() == null) ? 0
                                : getFechaActualizacion().toString())
                .toString();
    }

    @Override
    public Horario newInstance() {

        return new Horario();
    }
}