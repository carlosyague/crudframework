package org.librae.circulacion.model;

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
 * Requisito de almacenamiento para albergar los parámetros de ejecución
 * globales del sistema
 *
 * @author asantamaria
 */

@Entity(name = Parametro.ENTITY_NAME)
@Table(name = Parametro.TABLE_NAME)
public class Parametro extends BaseObject {

    /**
     * BaseObject is Serializable, so Parametro needs a Serial Version UID
     */
    private static final long   serialVersionUID                            = -5218682382441464475L;

    public static final String  ENTITY_NAME                                 = "org.librae.circulacion.model.Parametro";
    public static final String  TABLE_NAME                                  = "CIR_PARAMETROS";
    public static final String  ID_GENERATOR_NAME                           = "generator_cir_parametros";
    private static final String ID_SEQUENCE_NAME                            = "SEQ_CIR_PARAMETROS";

    public static final String  PROPERTY_NAME_ID                            = "id";
    public static final String  COLUMN_NAME_ID                              = "X_PARAMETRO";

    public static final String  PROPERTY_NAME_PRIORIDAD_TIPOS_RESERVAS      = "prioridadTiposReservas";
    public static final String  COLUMN_NAME_PRIORIDAD_TIPOS_RESERVAS        = "D_PRIOR_ENTRE_TIPOS_RESER";

    public static final String  PROPERTY_NAME_TIPO_LECTOR_SI_VARIOS         = "tipoLectorSiVarios";
    public static final String  COLUMN_NAME_TIPO_LECTOR_SI_VARIOS           = "D_TIPO_LECTOR_SI_VARIOS";
    public static final String  DEFAULT_VALUE_TIPO_LECTOR_SI_VARIOS         = "D";

    /**
     * Política de Préstamo a domicilio por defecto
     */
    public static final String  PROPERTY_NAME_POLITICA_PRESTAMO_DOM_DEFECTO = "politicaPrestamoDomDefecto";
    public static final String  COLUMN_NAME_POLITICA_PRESTAMO_DOM_DEFECTO   = "POL_DEF_X_DOM_POL_PREST_DEFECT";

    /**
     * Política de Reservas por defecto
     */
    public static final String  PROPERTY_NAME_POLITICA_PRESTAMO_RES_DEFECTO = "politicaPrestamoResDefecto";
    public static final String  COLUMN_NAME_POLITICA_PRESTAMO_RES_DEFECTO   = "POL_DEF_X_RES_POLIT_PREST_DEF";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Si en la devolución de un ejemplar existe una cola de reservas para el
     * registro bibliográfico del ejemplar, y existe una cola de reservas para
     * el ejemplar concreto, con cual de las dos reservas se vincula el
     * ejemplar? <br>
     * Valores: R, E<br>
     * Valor R: El ejemplar devuelto se vincula con la cola de reservas del
     * registro bibliográfico<br>
     * Valor E: El ejemplar devuelto se vincula con la de reservas del ejemplar<br>
     */
    private String              prioridadTiposReservas;

    /**
     * Valor "S" ó "D"<br>
     * Cuando el lector que realiza un préstamo tiene asignados varios tipos de
     * lectores (PPT, Lectores, ptos. 5 y 7), este parámetro especifica como se
     * establece el tipo de lector que la aplicación tomará para la operación de
     * préstamo.<br>
     * Valor S (Seleccionar): La aplicación solicita al usuario que seleccione,
     * de la lista de tipos de lector a los que pertenece el lector, el que
     * desea que se tome para la operación de préstamo.<br>
     * Valor D (Por Defecto): La aplicación escoge automáticamente (sin
     * intervención del usuario), el tipo de lector por defecto<br>
     */
    private String              tipoLectorSiVarios                          = Parametro.DEFAULT_VALUE_TIPO_LECTOR_SI_VARIOS;

    /**
     * >> CIR_POLITICAS_DOM<br>
     * Indica a la aplicación como debe actuar en caso de que se produzca una
     * operación de préstamo entre un tipo de lector y un tipo de ejemplar que
     * no tiene asignada una política de préstamos para la biblioteca que
     * presta.<br>
     * Un valor NULL en este parámetro indica que la operación de préstamo no se
     * puede realizar (por no tener asignada una política de préstamos).<br>
     * Un valor NOT NULL en este parámetro es el identificador de una política
     * de préstamos, que se aplica por defecto a los casos como éste<br>
     */
    private PoliticaPrestamoDom politicaPrestamoDomDefecto;

    /**
     * >> CIR_POLITICAS_RES<br>
     * Indica a la aplicación como debe actuar en caso de que se produzca una
     * operación de reserva para un tipo de lector o para un par (tipo de
     * lector, tipo de ejemplar) que no tiene asignada una política de reservas
     * para la biblioteca en la que se realiza la reserva.<br>
     * Un valor NULL en este parámetro indica que la operación de préstamo no se
     * puede realizar (por no tener asignada una política de reservas).<br>
     * Un valor NOT NULL en este parámetro es el identificador de una política
     * de reservas, que se aplica por defecto a los casos como éste<br>
     */
    private PoliticaReserva     politicaReserva;

    // ======================================================
    protected Parametro() {
        super();
    }

    public Parametro(final String tipoLectorSiVarios) {
        super();
        this.tipoLectorSiVarios = tipoLectorSiVarios;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Parametro.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Parametro.ID_GENERATOR_NAME, sequenceName = Parametro.ID_SEQUENCE_NAME)
    @Column(name = Parametro.COLUMN_NAME_ID)
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
     * @return the politicaPrestamoDomDefecto
     */
    @ManyToOne(targetEntity = PoliticaPrestamoDom.class)
    @JoinColumn(name = Parametro.COLUMN_NAME_POLITICA_PRESTAMO_DOM_DEFECTO, nullable = true)
    public PoliticaPrestamoDom getPoliticaPrestamoDomDefecto() {
        return politicaPrestamoDomDefecto;
    }

    /**
     * @param politicaPrestamoDomDefecto
     *            the politicaPrestamoDomDefecto to set
     */
    public void setPoliticaPrestamoDomDefecto(
            final PoliticaPrestamoDom politicaPrestamoDomDefecto) {
        this.politicaPrestamoDomDefecto = politicaPrestamoDomDefecto;
    }

    /**
     * @return the prioridadTiposReservas
     */
    @Column(name = Parametro.COLUMN_NAME_PRIORIDAD_TIPOS_RESERVAS,length=10)
    public String getPrioridadTiposReservas() {
        return prioridadTiposReservas;
    }

    /**
     * @param prioridadTiposReservas
     *            the prioridadTiposReservas to set
     */
    public void setPrioridadTiposReservas(final String prioridadTiposReservas) {
        this.prioridadTiposReservas = prioridadTiposReservas;
    }

    /**
     * @return the tipoLectorSiVarios
     */
    @Column(name = Parametro.COLUMN_NAME_TIPO_LECTOR_SI_VARIOS,length=10)
    public String getTipoLectorSiVarios() {
        return tipoLectorSiVarios;
    }

    /**
     * @param tipoLectorSiVarios
     *            the tipoLectorSiVarios to set
     */
    public void setTipoLectorSiVarios(final String tipoLectorSiVarios) {
        this.tipoLectorSiVarios = tipoLectorSiVarios;
    }

    @ManyToOne(targetEntity = PoliticaReserva.class)
    @JoinColumn(name = Parametro.COLUMN_NAME_POLITICA_PRESTAMO_RES_DEFECTO, nullable = false)
    public PoliticaReserva getPoliticaReserva() {
        return politicaReserva;
    }

    public void setPoliticaReserva(PoliticaReserva politicaReserva) {
        this.politicaReserva = politicaReserva;
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
        if (!(obj instanceof Parametro)) {
            return false;
        }

        final Parametro other = (Parametro) obj;

        // TipoLectorSiVarios
        if (getTipoLectorSiVarios() == null
                && other.getTipoLectorSiVarios() != null) {
            return false;
        }
        if (getTipoLectorSiVarios() != null
                && !getTipoLectorSiVarios().equals(
                        other.getTipoLectorSiVarios())) {
            return false;
        }

        // PrioridadTiposReservas
        if (getPrioridadTiposReservas() == null
                && other.getPrioridadTiposReservas() != null) {
            return false;
        }
        if (getPrioridadTiposReservas() != null
                && !getPrioridadTiposReservas().equals(
                        other.getPrioridadTiposReservas())) {
            return false;
        }

        // PoliticaPrestamoDomDefecto
        if (getPoliticaPrestamoDomDefecto() == null
                && other.getPoliticaPrestamoDomDefecto() != null) {
            return false;
        }
        if (getPoliticaPrestamoDomDefecto() != null
                && !getPoliticaPrestamoDomDefecto().equals(
                        other.getPoliticaPrestamoDomDefecto())) {
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
                + ((getPrioridadTiposReservas() == null) ? 0
                        : getPrioridadTiposReservas().hashCode());
        result = prime
                * result
                + ((getTipoLectorSiVarios() == null) ? 0
                        : getTipoLectorSiVarios().hashCode());
        result = prime
                * result
                + ((getPoliticaPrestamoDomDefecto() == null) ? 0
                        : getPoliticaPrestamoDomDefecto().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        final String prioridadTiposReservasString = (getPrioridadTiposReservas() == null) ? ""
                : getPrioridadTiposReservas().toString();
        final String tipoLectorSiVariosString = (getTipoLectorSiVarios() == null) ? ""
                : getTipoLectorSiVarios().toString();

        final ToStringBuilder tsb = new ToStringBuilder(this);
        tsb.append(Parametro.PROPERTY_NAME_ID, getId());
        tsb.append(Parametro.PROPERTY_NAME_PRIORIDAD_TIPOS_RESERVAS,
                prioridadTiposReservasString);
        tsb.append(Parametro.PROPERTY_NAME_TIPO_LECTOR_SI_VARIOS,
                tipoLectorSiVariosString);

        return tsb.toString();
    }

}