package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Tabla con los posibles tipos de reservas
 *
 * @author jcisneros
 */
@Entity(name = TipoPrestamo.ENTITY_NAME)
@Table(name = TipoPrestamo.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class TipoPrestamo extends SpringRemotableLazyEntity<TipoPrestamo> {

    /**
     * BaseObject is Serializable, so TipoPrestamo needs a Serial Version UID
     */
    private static final long   serialVersionUID   = -493113359982334950L;

    public static final String  ENTITY_NAME        = "org.librae.circulacion.model.TipoPrestamo";
    public static final String  TABLE_NAME         = "CIR_TIPO_PRESTAMO";
    public static final String  ID_GENERATOR_NAME  = "generator_cir_tipos_prestamo";
    private static final String ID_SEQUENCE_NAME   = "SEQ_CIR_TIPOS_PRESTAMO";
    public static final String  COLUMN_NAME_ID     = "X_TIPO_PRESTAMO";
    public static final String  COLUMN_NAME_CODIGO = "C_TIPO_PRESTAMO";
    public static final String  COLUMN_NAME_NOMBRE = "T_TIPO_PRESTAMO";

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                id;

    /**
     * Código del tipo de prestamo
     */
    private String              codigo;

    /**
     * Descripción del tipo de prestamo
     */
    private String              nombre;

    /**
     * Constructor.
     */
    protected TipoPrestamo() {
        super();
    }

    /**
     * Constructor.
     */
    public TipoPrestamo(final String codigo) {
        super();
        this.codigo = codigo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = TipoPrestamo.COLUMN_NAME_ID)
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
    @Column(name = TipoPrestamo.COLUMN_NAME_CODIGO,length=40)
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
    @Column(name = TipoPrestamo.COLUMN_NAME_NOMBRE,length=80)
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
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TipoPrestamo)) {
            return false;
        }

        final TipoPrestamo other = (TipoPrestamo) obj;

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
        return new ToStringBuilder(this).append(COLUMN_NAME_ID, getId())
                .append(COLUMN_NAME_CODIGO, getCodigo()).append(
                        COLUMN_NAME_NOMBRE, getNombre()).toString();
    }

    @Override
    public TipoPrestamo newInstance() {
        return new TipoPrestamo();
    }

}