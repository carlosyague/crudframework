package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.BaseObject;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * Gestiona los estados de reservas.
 * 
 * @author asantamaría
 */
@Entity(name = EstadoReserva.ENTITY_NAME)
@Table(name = EstadoReserva.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class EstadoReserva extends BaseObject {

    /**
     * BaseObject is Serializable, so TipoCodigo needs a Serial Version UID
     */
    private static final long   serialVersionUID        = -2560303850504052419L;

    public static final String  ENTITY_NAME             = "org.librae.circulacion.model.EstadoReserva";
    public static final String  TABLE_NAME              = "CIR_ESTADOS_RESERVAS";
    public static final String  ID_GENERATOR_NAME       = "generator_cir_estados_reservas";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CIR_ESTADOS_RESERVAS";
    public static final String  COLUMN_NAME_ID          = "X_ESTADO_RESERVA";
    public static final String  COLUMN_NAME_CODIGO      = "C_ESTADO_RESERVA";
    public static final String  COLUMN_NAME_DESCRIPCION = "T_ESTADO_RESERVA";

    public static final String  PROPTY_NAME_ID          = "id";
    public static final String  PROPTY_NAME_CODIGO      = "codigo";
    public static final String  PROPTY_NAME_DESCRIPCION = "descripcion";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Codigo del estado de codigo.
     */
    private String              codigo;

    /**
     * Descripcion del estado de codigo.
     */
    private String              descripcion;

    protected EstadoReserva() {
        super();
    }

    public EstadoReserva(final String codigo, final String descripcion) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EstadoReserva.ID_GENERATOR_NAME)
    @SequenceGenerator(name = EstadoReserva.ID_GENERATOR_NAME, sequenceName = EstadoReserva.ID_SEQUENCE_NAME)
    @Column(name = EstadoReserva.COLUMN_NAME_ID)
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
    @Column(name = EstadoReserva.COLUMN_NAME_CODIGO, length = 40)
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
     * @return the descripcion
     */
    @Column(name = EstadoReserva.COLUMN_NAME_DESCRIPCION, length = 80)
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
     * Traduce el campo nombre.
     * 
     * @return
     */
    @Transient
    public String getDescripcionLocale() {
        return ComboLocaleManager.getOptional(descripcion);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(final Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof EstadoReserva)) {
            return false;
        }

        final EstadoReserva other = (EstadoReserva) obj;

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null && !getCodigo().equals(other.getCodigo())) {
            return false;
        }

        return true;

    }

    /**
     * @see java.lang.Object#hashCode()
     */
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
    public String toString() {
        return new ToStringBuilder(this)

        .append(EstadoReserva.PROPTY_NAME_ID, getId())

        .append(EstadoReserva.PROPTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(EstadoReserva.PROPTY_NAME_DESCRIPCION,
                (getDescripcion() == null) ? "" : getDescripcion().toString())

        .toString();
    }

}