package org.librae.adminconfig.model;

import java.util.Date;
import java.util.List;

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
 * Un calendario es una entidad que indica que días laborables, y por exclusión,
 * que días son festivos.<br>
 * <br>
 * A cada calendario le pueden corresponder varios días festivos en la tabla
 * ADM_FESTIVOS.<br>
 * <br>
 * Un calendario se puede asociar a biblioteca o sucursal, pero sólo a una (así
 * evitamos la problemática relacionada con la imposibilidad de borrar un
 * calendario por estar asociado a más de una biblioteca por ejemplo).<br>
 * 
 * @author asantamaria
 */
@Entity(name = Calendario.ENTITY_NAME)
@Table(name = Calendario.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Calendario extends SpringRemotableLazyEntity<Calendario> {

    /**
     * BaseObject is Serializable, so Calendario needs a Serial Version UID
     */
    private static final long   serialVersionUID                = 2074352101214682821L;

    public static final String  ENTITY_NAME                     = "org.librae.adminconfig.model.Calendario";
    public static final String  TABLE_NAME                      = "ADM_CALENDARIO";
    private static final String ID_GENERATOR_NAME               = "generator_adm_calendario";
    private static final String ID_SEQUENCE_NAME                = "SEQ_ADM_CALENDARIO";
    public static final String  COLUMN_NAME_ID                  = "X_CALENDARIO";
    public static final String  COLUMN_NAME_DIASEMANA           = "T_DIASEMANA";
    public static final String  COLUMN_NAME_FECHA_ACTUALIZACION = "F_ACT";

    public static final String  PROPTY_NAME_ID                  = "id";
    public static final String  PROPTY_NAME_DIASEMANA           = "diasSemana";
    public static final String  PROPTY_NAME_FECHA_ACTUALIZACION = "fechaActualizacion";
    public static final String  PROPTY_NAME_FESTIVOS            = "festivos";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Días de la semana que son laborables por defecto en este calendario.
     * Valores permitdos: cualquier combinación no repetida de los caracteres L,
     * M, X, J, V, S, D
     */
    private String              diasSemana;

    /**
     * Fecha de la última actualización del calendario
     */
    private Date                fechaActualizacion;

    /**
     * Lista de dias festivos del calendario
     */
    private List<Festivo>       festivos;

    protected Calendario() {
        super();
    }

    /**
     * Crea el objeto Calendario con los días de la semana. La fecha de
     * actualización se establece al momento de la creación.
     * 
     * @param diasSemana
     * @param biblioteca
     */
    public Calendario(String diasSemana) {
        super();
        this.diasSemana = diasSemana;
        final Date ahora = new Date();
        fechaActualizacion = ahora;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Calendario.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }
    
    /**
     * @return the diasSemana
     */
    @Column(name = Calendario.COLUMN_NAME_DIASEMANA, length = 10)
    public String getDiasSemana() {
        return diasSemana;
    }

    /**
     * @param diasSemana
     *            the diasSemana to set
     */
    public void setDiasSemana(String diasSemana) {
        this.diasSemana = diasSemana;
    }

    /**
     * @return the fechaActualizacion
     */
    @Column(name = Calendario.COLUMN_NAME_FECHA_ACTUALIZACION)
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion
     *            the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the permisosRoles
     */
    @OneToMany(targetEntity = Festivo.class)
    @JoinColumn(name = Festivo.COLUMN_NAME_CALENDARIO)
    @Cascade( { org.hibernate.annotations.CascadeType.ALL })
    public List<Festivo> getFestivos() {
        return festivos;
    }

    /**
     * @param festivos
     *            the festivos to set
     */
    public void setFestivos(List<Festivo> festivos) {
        this.festivos = festivos;
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
        if (!(obj instanceof Calendario)) {
            return false;
        }

        final Calendario other = (Calendario) obj;

        if (getDiasSemana() == null && other.getDiasSemana() != null) {
            return false;
        }
        if (getDiasSemana() != null
                && !getDiasSemana().equals(other.getDiasSemana())) {
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

        result = prime * result
                + ((getDiasSemana() == null) ? 0 : getDiasSemana().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPTY_NAME_ID, getId())

        .append(PROPTY_NAME_DIASEMANA,
                (getDiasSemana() == null) ? "" : getDiasSemana().toString())

        .append(
                PROPTY_NAME_FECHA_ACTUALIZACION,
                (getFechaActualizacion() == null) ? ""
                        : getFechaActualizacion().toString())

        .toString();
    }

    @Override
    public Calendario newInstance() {

        return new Calendario();
    }

}