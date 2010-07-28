package org.librae.adminconfig.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Intervalos horarios que se aplican a rangos de fechas, asociados a un horario
 * 
 * @author asantamaria
 */
@Entity(name = HorarioInt.ENTITY_NAME)
@Table(name = HorarioInt.TABLE_NAME)
public class HorarioInt extends BaseObject {

    /**
     * BaseObject is Serializable, so HorarioInt needs a Serial Version UID
     */
    private static final long   serialVersionUID          = -6965374976945724484L;

    public static final String  ENTITY_NAME               = "org.librae.adminconfig.model.HorarioInt";
    public static final String  TABLE_NAME                = "ADM_HORARIO_INT";
    public static final String  ID_GENERATOR_NAME         = "generator_adm_horario_int";
    private static final String ID_SEQUENCE_NAME          = "SEQ_ADM_HORARIO_INT";

    public static final String  COLUMN_NAME_ID            = "X_INTERVALO";
    public static final String  COLUMN_NAME_FECHA_FIN     = "F_FIN";
    public static final String  COLUMN_NAME_FECHA_INICIO  = "F_INICIO";
    public static final String  COLUMN_NAME_HORA_FIN_1    = "H_FIN1";
    public static final String  COLUMN_NAME_HORA_FIN_2    = "H_FIN2";
    public static final String  COLUMN_NAME_HORA_FIN_3    = "H_FIN3";
    public static final String  COLUMN_NAME_HORA_FIN_4    = "H_FIN4";
    public static final String  COLUMN_NAME_HORA_FIN_5    = "H_FIN5";
    public static final String  COLUMN_NAME_HORA_INICIO_1 = "H_INICIO1";
    public static final String  COLUMN_NAME_HORA_INICIO_2 = "H_INICIO2";
    public static final String  COLUMN_NAME_HORA_INICIO_3 = "H_INICIO3";
    public static final String  COLUMN_NAME_HORA_INICIO_4 = "H_INICIO4";
    public static final String  COLUMN_NAME_HORA_INICIO_5 = "H_INICIO5";
    public static final String  COLUMN_NAME_HORARIO       = "HOR_X_HORARIO";
    public static final String  COLUMN_NAME_DIA_SEMANA    = "T_DIASEMANA";

    public static final String  PROPTY_NAME_ID            = "id";
    public static final String  PROPTY_NAME_FECHA_FIN     = "fechaFin";
    public static final String  PROPTY_NAME_FECHA_INICIO  = "fechaInicio";
    public static final String  PROPTY_NAME_HORA_FIN_1    = "horaFin1";
    public static final String  PROPTY_NAME_HORA_FIN_2    = "horaFin2";
    public static final String  PROPTY_NAME_HORA_FIN_3    = "horaFin3";
    public static final String  PROPTY_NAME_HORA_FIN_4    = "horaFin4";
    public static final String  PROPTY_NAME_HORA_FIN_5    = "horaFin5";
    public static final String  PROPTY_NAME_HORA_INICIO_1 = "horaInicio1";
    public static final String  PROPTY_NAME_HORA_INICIO_2 = "horaInicio2";
    public static final String  PROPTY_NAME_HORA_INICIO_3 = "horaInicio3";
    public static final String  PROPTY_NAME_HORA_INICIO_4 = "horaInicio4";
    public static final String  PROPTY_NAME_HORA_INICIO_5 = "horaInicio5";
    public static final String  PROPTY_NAME_HORARIO       = "horario";
    public static final String  PROPTY_NAME_DIA_SEMANA    = "diaSemana";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Fecha de inicio del intervalo en que se aplica el horario. Primer día en
     * el que se aplica el horario.
     */
    private Date                fechaInicio;

    /**
     * Fecha de fin del intervalo en que se aplica el horario. Último día que se
     * aplica el horario.
     */
    private Date                fechaFin;

    /**
     * Hora de inicio del primer intervalo horario
     */
    private Timestamp           horaInicio1;

    /**
     * Hora de inicio del segundo intervalo horario
     */
    private Timestamp           horaInicio2;

    /**
     * Hora de inicio del tercer intervalo horario
     */
    private Timestamp           horaInicio3;

    /**
     * Hora de inicio del cuarto intervalo horario
     */
    private Timestamp           horaInicio4;

    /**
     * Hora de inicio del quinto intervalo horario
     */
    private Timestamp           horaInicio5;

    /**
     * Hora de fin del primer intervalo horario
     */
    private Timestamp           horaFin1;

    /**
     * Hora de fin del segundo intervalo horario
     */
    private Timestamp           horaFin2;

    /**
     * Hora de fin del tercer intervalo horario
     */
    private Timestamp           horaFin3;

    /**
     * Hora de fin del cuarto intervalo horario
     */
    private Timestamp           horaFin4;

    /**
     * Hora de fin del quinto intervalo horario
     */
    private Timestamp           horaFin5;

    /**
     * Clave foránea que identifica al horario al que pertenece el intervalo.
     */
    private Horario             horario;

    /**
     * Lista de días de la semana del intervalo en los que se aplica el horario.
     * Valores permitidos: lista de valores no repetidos de los caracteres L, M,
     * X, J, V, S, D.
     */
    private String              diaSemana;

    protected HorarioInt() {
        super();
    }

    public HorarioInt(final Horario horario) {
        super();
        this.horario = horario;
    }

    public HorarioInt(final Horario horario, Date fechaInicio, Date fechaFin,
            Timestamp horaInicio1, Timestamp horaInicio2,
            Timestamp horaInicio3, Timestamp horaInicio4,
            Timestamp horaInicio5, Timestamp horaFin1, Timestamp horaFin2,
            Timestamp horaFin3, Timestamp horaFin4, Timestamp horaFin5,
            String diaSemana) {
        super();
        this.horario = horario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio1 = horaInicio1;
        this.horaInicio2 = horaInicio2;
        this.horaInicio3 = horaInicio3;
        this.horaInicio4 = horaInicio4;
        this.horaInicio5 = horaInicio5;
        this.horaFin1 = horaFin1;
        this.horaFin2 = horaFin2;
        this.horaFin3 = horaFin3;
        this.horaFin4 = horaFin4;
        this.horaFin5 = horaFin5;
        this.diaSemana = diaSemana;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = HorarioInt.COLUMN_NAME_ID)
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
     * @return the fechaInicio
     */
    @Column(name = HorarioInt.COLUMN_NAME_FECHA_INICIO)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio
     *            the fechaInicio to set
     */
    public void setFechaInicio(final Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    @Column(name = HorarioInt.COLUMN_NAME_FECHA_FIN)
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin
     *            the fechaFin to set
     */
    public void setFechaFin(final Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the horaInicio1
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_INICIO_1)
    public Timestamp getHoraInicio1() {
        return horaInicio1;
    }

    /**
     * @param horaInicio1
     *            the horaInicio1 to set
     */
    public void setHoraInicio1(final Timestamp horaInicio1) {
        this.horaInicio1 = horaInicio1;
    }

    /**
     * @return the horaInicio2
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_INICIO_2)
    public Timestamp getHoraInicio2() {
        return horaInicio2;
    }

    /**
     * @param horaInicio2
     *            the horaInicio2 to set
     */
    public void setHoraInicio2(final Timestamp horaInicio2) {
        this.horaInicio2 = horaInicio2;
    }

    /**
     * @return the horaInicio3
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_INICIO_3)
    public Timestamp getHoraInicio3() {
        return horaInicio3;
    }

    /**
     * @param horaInicio3
     *            the horaInicio3 to set
     */
    public void setHoraInicio3(final Timestamp horaInicio3) {
        this.horaInicio3 = horaInicio3;
    }

    /**
     * @return the horaInicio4
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_INICIO_4)
    public Timestamp getHoraInicio4() {
        return horaInicio4;
    }

    /**
     * @param horaInicio4
     *            the horaInicio4 to set
     */
    public void setHoraInicio4(final Timestamp horaInicio4) {
        this.horaInicio4 = horaInicio4;
    }

    /**
     * @return the horaInicio5
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_INICIO_5)
    public Timestamp getHoraInicio5() {
        return horaInicio5;
    }

    /**
     * @param horaInicio5
     *            the horaInicio5 to set
     */
    public void setHoraInicio5(final Timestamp horaInicio5) {
        this.horaInicio5 = horaInicio5;
    }

    /**
     * @return the horaFin1
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_FIN_1)
    public Timestamp getHoraFin1() {
        return horaFin1;
    }

    /**
     * @param horaFin1
     *            the horaFin1 to set
     */
    public void setHoraFin1(final Timestamp horaFin1) {
        this.horaFin1 = horaFin1;
    }

    /**
     * @return the horaFin2
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_FIN_2)
    public Timestamp getHoraFin2() {
        return horaFin2;
    }

    /**
     * @param horaFin2
     *            the horaFin2 to set
     */
    public void setHoraFin2(final Timestamp horaFin2) {
        this.horaFin2 = horaFin2;
    }

    /**
     * @return the horaFin3
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_FIN_3)
    public Timestamp getHoraFin3() {
        return horaFin3;
    }

    /**
     * @param horaFin3
     *            the horaFin3 to set
     */
    public void setHoraFin3(final Timestamp horaFin3) {
        this.horaFin3 = horaFin3;
    }

    /**
     * @return the horaFin4
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_FIN_4)
    public Timestamp getHoraFin4() {
        return horaFin4;
    }

    /**
     * @param horaFin4
     *            the horaFin4 to set
     */
    public void setHoraFin4(final Timestamp horaFin4) {
        this.horaFin4 = horaFin4;
    }

    /**
     * @return the horaFin5
     */
    @Column(name = HorarioInt.COLUMN_NAME_HORA_FIN_5)
    public Timestamp getHoraFin5() {
        return horaFin5;
    }

    /**
     * @param horaFin5
     *            the horaFin5 to set
     */
    public void setHoraFin5(final Timestamp horaFin5) {
        this.horaFin5 = horaFin5;
    }

    /**
     * @return the horario
     */
    @ManyToOne(targetEntity = Horario.class, cascade = { CascadeType.PERSIST,
            CascadeType.ALL })
    @JoinColumn(name = HorarioInt.COLUMN_NAME_HORARIO, nullable = false)
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario
     *            the horario to set
     */
    public void setHorario(final Horario horario) {
        this.horario = horario;
    }

    /**
     * @return the diaSemana
     */
    @Column(name = HorarioInt.COLUMN_NAME_DIA_SEMANA, length = 10)
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * @param diaSemana
     *            the diaSemana to set
     */
    public void setDiaSemana(final String diaSemana) {
        this.diaSemana = diaSemana;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HorarioInt)) {
            return false;
        }
        final HorarioInt other = (HorarioInt) obj;
        if (getDiaSemana() == null && other.getDiaSemana() != null) {
            return false;
        }
        if (getDiaSemana() != null
                && !getDiaSemana().equals(other.getDiaSemana())) {
            return false;
        }
        if (getFechaInicio() == null && other.getFechaInicio() != null) {
            return false;
        }
        if (getFechaInicio() != null
                && !getFechaInicio().equals(other.getFechaInicio())) {
            return false;
        }
        if (getFechaFin() == null && other.getFechaFin() != null) {
            return false;
        }
        if (getFechaFin() != null && !getFechaFin().equals(other.getFechaFin())) {
            return false;
        }
        if (getHoraInicio1() == null && other.getHoraInicio1() != null) {
            return false;
        }
        if (getHoraInicio1() != null
                && !getHoraInicio1().equals(other.getHoraInicio1())) {
            return false;
        }
        if (getHoraInicio2() == null && other.getHoraInicio2() != null) {
            return false;
        }
        if (getHoraInicio2() != null
                && !getHoraInicio2().equals(other.getHoraInicio2())) {
            return false;
        }
        if (getHoraInicio3() == null && other.getHoraInicio3() != null) {
            return false;
        }
        if (getHoraInicio3() != null
                && !getHoraInicio3().equals(other.getHoraInicio3())) {
            return false;
        }
        if (getHoraInicio4() == null && other.getHoraInicio4() != null) {
            return false;
        }
        if (getHoraInicio4() != null
                && !getHoraInicio4().equals(other.getHoraInicio4())) {
            return false;
        }
        if (getHoraInicio5() == null && other.getHoraInicio5() != null) {
            return false;
        }
        if (getHoraInicio5() != null
                && !getHoraInicio5().equals(other.getHoraInicio5())) {
            return false;
        }
        if (getHoraFin1() == null && other.getHoraFin1() != null) {
            return false;
        }
        if (getHoraFin1() != null && !getHoraFin1().equals(other.getHoraFin1())) {
            return false;
        }
        if (getHoraFin2() == null && other.getHoraFin2() != null) {
            return false;
        }
        if (getHoraFin2() != null && !getHoraFin2().equals(other.getHoraFin2())) {
            return false;
        }
        if (getHoraFin3() == null && other.getHoraFin3() != null) {
            return false;
        }
        if (getHoraFin3() != null && !getHoraFin3().equals(other.getHoraFin3())) {
            return false;
        }
        if (getHoraFin4() == null && other.getHoraFin4() != null) {
            return false;
        }
        if (getHoraFin4() != null && !getHoraFin4().equals(other.getHoraFin4())) {
            return false;
        }
        if (getHoraFin5() == null && other.getHoraFin5() != null) {
            return false;
        }
        if (getHoraFin5() != null && !getHoraFin5().equals(other.getHoraFin5())) {
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
                + ((getDiaSemana() == null) ? 0 : getDiaSemana().hashCode());

        result = prime
                * result
                + ((getFechaInicio() == null) ? 0 : getFechaInicio().hashCode());

        result = prime * result
                + ((getFechaFin() == null) ? 0 : getFechaFin().hashCode());

        result = prime
                * result
                + ((getHoraInicio1() == null) ? 0 : getHoraInicio1().hashCode());

        result = prime
                * result
                + ((getHoraInicio2() == null) ? 0 : getHoraInicio2().hashCode());

        result = prime
                * result
                + ((getHoraInicio3() == null) ? 0 : getHoraInicio3().hashCode());

        result = prime
                * result
                + ((getHoraInicio4() == null) ? 0 : getHoraInicio4().hashCode());

        result = prime
                * result
                + ((getHoraInicio5() == null) ? 0 : getHoraInicio5().hashCode());

        result = prime * result
                + ((getHoraFin1() == null) ? 0 : getHoraFin1().hashCode());

        result = prime * result
                + ((getHoraFin2() == null) ? 0 : getHoraFin2().hashCode());

        result = prime * result
                + ((getHoraFin3() == null) ? 0 : getHoraFin3().hashCode());

        result = prime * result
                + ((getHoraFin4() == null) ? 0 : getHoraFin4().hashCode());

        result = prime * result
                + ((getHoraFin5() == null) ? 0 : getHoraFin5().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(COLUMN_NAME_ID, getId())

        .append(COLUMN_NAME_DIA_SEMANA,
                (getDiaSemana() == null) ? 0 : getDiaSemana().toString())

        .append(COLUMN_NAME_FECHA_INICIO,
                (getFechaInicio() == null) ? 0 : getFechaInicio().toString())

        .append(COLUMN_NAME_FECHA_FIN,
                (getFechaFin() == null) ? 0 : getFechaFin().toString())

        .append(COLUMN_NAME_HORA_INICIO_1,
                (getHoraInicio1() == null) ? 0 : getHoraInicio1().toString())

        .append(COLUMN_NAME_HORA_INICIO_2,
                (getHoraInicio2() == null) ? 0 : getHoraInicio2().toString())

        .append(COLUMN_NAME_HORA_INICIO_3,
                (getHoraInicio3() == null) ? 0 : getHoraInicio3().toString())

        .append(COLUMN_NAME_HORA_INICIO_4,
                (getHoraInicio4() == null) ? 0 : getHoraInicio4().toString())

        .append(COLUMN_NAME_HORA_INICIO_5,
                (getHoraInicio5() == null) ? 0 : getHoraInicio5().toString())

        .append(COLUMN_NAME_HORA_FIN_1,
                (getHoraFin1() == null) ? 0 : getHoraFin1().toString())

        .append(COLUMN_NAME_HORA_FIN_2,
                (getHoraFin2() == null) ? 0 : getHoraFin2().toString())

        .append(COLUMN_NAME_HORA_FIN_3,
                (getHoraFin3() == null) ? 0 : getHoraFin3().toString())

        .append(COLUMN_NAME_HORA_FIN_4,
                (getHoraFin4() == null) ? 0 : getHoraFin4().toString())

        .append(COLUMN_NAME_HORA_FIN_5,
                (getHoraFin5() == null) ? 0 : getHoraFin5().toString())

        .toString();
    }

    @Transient
    public StringBuilder getMostrarHorarios() {
        final StringBuilder sb = new StringBuilder();

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        if (horaInicio1 != null) {
            sb.append(sdf.format(horaInicio1));
            sb.append(" - ");
        }
        if (horaFin1 != null) {
            sb.append(sdf.format(horaFin1));
        }
        if (horaInicio2 != null) {
            sb.append("; ");
            sb.append(sdf.format(horaInicio2));
            sb.append(" - ");
        }
        if (horaFin2 != null) {
            sb.append(sdf.format(horaFin2));
        }
        if (horaInicio3 != null) {
            sb.append("; ");
            sb.append(sdf.format(horaInicio3));
            sb.append(" - ");
        }
        if (horaFin3 != null) {
            sb.append(sdf.format(horaFin3));
        }
        if (horaInicio4 != null) {
            sb.append("; ");
            sb.append(sdf.format(horaInicio4));
            sb.append(" - ");
        }

        if (horaFin4 != null) {
            sb.append(sdf.format(horaFin4));
        }

        if (horaInicio5 != null) {
            sb.append("; ");
            sb.append(sdf.format(horaInicio5));
            sb.append(" - ");
        }

        if (horaFin5 != null) {
            sb.append(sdf.format(horaFin5));
        }
        return sb;
    }
}