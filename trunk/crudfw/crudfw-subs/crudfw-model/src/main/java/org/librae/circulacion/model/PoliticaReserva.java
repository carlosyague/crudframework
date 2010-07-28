package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * @author aropero
 */
@Entity(name = PoliticaReserva.ENTITY_NAME)
@Table(name = PoliticaReserva.TABLE_NAME)
public class PoliticaReserva extends BaseObject {

    /**
     * BaseObject is Serializable, so PoliticaReserva needs a Serial Version UID
     */
    private static final long   serialVersionUID            = 6245974682779963953L;

    public static final String  ENTITY_NAME                 = "org.librae.circulacion.model.PoliticaReserva";
    public static final String  TABLE_NAME                  = "CIR_RES_POLITICAS";
    private static final String ID_GENERATOR_NAME           = "generator_cir_res_politicas";
    private static final String ID_SEQUENCE_NAME            = "SEQ_CIR_RES_POLITICAS";
    public static final String  COLUMN_NAME_ID              = "X_POLITICA_RES";
    public static final String  COLUMN_NAME_CODIGO          = "C_POLITICA_RES";
    public static final String  COLUMN_NAME_NOMBRE          = "T_POLITICA_RES";
    public static final String  COLUMN_NAME_DESCRIPCION     = "T_POLITICA_RES_ALT";
    public static final String  COLUMN_NAME_DIAS_EN_COLA    = "N_DIAS_PERMANENCIA_EN_COLA";
    public static final String  COLUMN_NAME_DIAS_SUSPENSION = "N_DIAS_SUSPENSION";
    public static final String  COLUMN_NAME_DIAS_RESERVADO  = "N_DIAS_RESERVADO";

    public static final String  PROPTY_NAME_ID              = "id";
    public static final String  PROPTY_NAME_CODIGO          = "codigo";
    public static final String  PROPTY_NAME_NOMBRE          = "nombre";
    public static final String  PROPTY_NAME_DESCRIPCION     = "descripcion";
    public static final String  PROPTY_NAME_DIAS_EN_COLA    = "diasCola";
    public static final String  PROPTY_NAME_DIAS_SUSPENSION = "diasSuspension";
    public static final String  PROPTY_NAME_DIAS_RESERVADO  = "diasReservado";

    /**
     * Clave primaria autonumérica sin significado funcional
     */
    private Long                id;

    /**
     * Código único asignado por el usuario
     */
    private String              codigo;

    /**
     * Descripción (nombre) asignado por el usuario a la política de reservas
     */
    private String              nombre;

    /**
     * Descripción alternativa introducida por el usuario
     */
    private String              descripcion;

    /**
     * Cuantos días, a partir de la fecha-hora en que se produzca la vinculación
     * de un ejemplar con la reserva, va a permanecer la reserva en la cola de
     * reservas, si el lector no hace efectivo el préstamo.
     */
    private Long                diasCola                    = new Long(90);

    /**
     * Días de suspensión (dssu en Ab*NET). Número de días con los que se va a
     * sancional al lector que no retire en préstamo la reserva cuando esta está
     * diponible para hacer efectivo del préstamo.
     */
    private Long                diasSuspension              = new Long(5);

    /**
     * Numero de dias que tiene un lector de este tipo para formalizar el
     * prestamo correspondiente a la reserva, desde que esta este activada
     */
    private Long                diasReservado               = new Long(5);

    protected PoliticaReserva() {
        super();
    }

    public PoliticaReserva(final String codigo, final String nombre) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public PoliticaReserva(final String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = PoliticaReserva.ID_GENERATOR_NAME)
    @SequenceGenerator(name = PoliticaReserva.ID_GENERATOR_NAME, sequenceName = PoliticaReserva.ID_SEQUENCE_NAME)
    @Column(name = PoliticaReserva.COLUMN_NAME_ID)
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
     * @return the codigo
     */
    @Column(name = PoliticaReserva.COLUMN_NAME_CODIGO, nullable = false, length = 40)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    @Column(name = PoliticaReserva.COLUMN_NAME_NOMBRE, length = 80)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    @Column(name = PoliticaReserva.COLUMN_NAME_DESCRIPCION)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the diasCola
     */
    @Column(name = PoliticaReserva.COLUMN_NAME_DIAS_EN_COLA)
    public Long getDiasCola() {
        return diasCola;
    }

    /**
     * @param diasCola
     *            the diasCola to set
     */
    public void setDiasCola(final Long diasCola) {
        this.diasCola = diasCola;
    }

    /**
     * @return the diasCola
     */
    @Column(name = PoliticaReserva.COLUMN_NAME_DIAS_SUSPENSION)
    public Long getDiasSuspension() {
        return diasSuspension;
    }

    /**
     * @param diasSuspension
     *            the diasSuspension to set
     */
    public void setDiasSuspension(final Long diasSuspension) {
        this.diasSuspension = diasSuspension;
    }

    /**
     * @return the diasCola
     */
    @Column(name = PoliticaReserva.COLUMN_NAME_DIAS_RESERVADO)
    public Long getDiasReservado() {
        return diasReservado;
    }

    public void setDiasReservado(Long diasReservado) {
        this.diasReservado = diasReservado;
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
        if (!(obj instanceof PoliticaReserva)) {
            return false;
        }

        final PoliticaReserva other = (PoliticaReserva) obj;

        if (!getCodigo().equals(other.getCodigo())) {
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
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PoliticaReserva.PROPTY_NAME_ID, getId())

        .append(PoliticaReserva.PROPTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString()).append(
                PoliticaReserva.PROPTY_NAME_NOMBRE,
                (getNombre() == null) ? "" : getNombre().toString())

        .append(PoliticaReserva.PROPTY_NAME_DESCRIPCION,
                (getDescripcion() == null) ? "" : getDescripcion().toString())

        .append(PoliticaReserva.PROPTY_NAME_DIAS_EN_COLA,
                (getDiasCola() == null) ? "" : getDiasCola().toString())

        .append(
                PoliticaReserva.PROPTY_NAME_DIAS_SUSPENSION,
                (getDiasSuspension() == null) ? "" : getDiasSuspension()
                        .toString())

        .toString();
    }

}
