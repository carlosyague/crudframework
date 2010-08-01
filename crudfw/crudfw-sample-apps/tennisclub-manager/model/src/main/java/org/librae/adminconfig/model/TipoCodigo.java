package org.librae.adminconfig.model;

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
import org.librae.common.model.BaseObject;

/**
 * Gestiona los tipos de codigos.
 *
 * @author asantamaría
 */
@Entity(name = TipoCodigo.ENTITY_NAME)
@Table(name = TipoCodigo.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class TipoCodigo extends BaseObject {

    /**
     * BaseObject is Serializable, so TipoCodigo needs a Serial Version UID
     */
    private static final long   serialVersionUID        = -2560303850504052419L;

    public static final String  ENTITY_NAME             = "org.librae.adminconfig.model.TipoCodigo";
    public static final String  TABLE_NAME              = "ADM_TIPOS_CODIGOS";
    public static final String  ID_GENERATOR_NAME       = "generator_adm_tipos_codigos";
    private static final String ID_SEQUENCE_NAME        = "SEQ_ADM_TIPOS_CODIGOS";
    public static final String  COLUMN_NAME_ID          = "X_TIPO_CODIGO";
    public static final String  COLUMN_NAME_CODIGO      = "C_TIPO_CODIGO";
    public static final String  COLUMN_NAME_DESCRIPCION = "T_TIPO_CODIGO";

    public static final String  PROPTY_NAME_ID          = "id";
    public static final String  PROPTY_NAME_CODIGO      = "codigo";
    public static final String  PROPTY_NAME_DESCRIPCION = "descripcion";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Codigo del tipo de codigo.
     */
    private String              codigo;

    /**
     * Descripcion del tipo de codigo.
     */
    private String              descripcion;

    protected TipoCodigo() {
        super();
    }

    public TipoCodigo(final String codigo, final String descripcion) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TipoCodigo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = TipoCodigo.ID_GENERATOR_NAME, sequenceName = TipoCodigo.ID_SEQUENCE_NAME)
    @Column(name = TipoCodigo.COLUMN_NAME_ID)
    public Long getId() {
        return this.id;
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
    @Column(name = TipoCodigo.COLUMN_NAME_CODIGO, length = 40)
    public String getCodigo() {
        return this.codigo;
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
    @Column(name = TipoCodigo.COLUMN_NAME_DESCRIPCION, length = 80)
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
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
        if (!(obj instanceof TipoCodigo)) {
            return false;
        }

        final TipoCodigo other = (TipoCodigo) obj;

        if (this.getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (this.getCodigo() != null
                && !this.getCodigo().equals(other.getCodigo())) {
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
        result += ((this.id == null) ? 0 : this.getId().hashCode());

        result = prime
                * result
                + ((this.getCodigo() == null) ? 0 : this.getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)

        .append(TipoCodigo.PROPTY_NAME_ID, this.getId())

        .append(TipoCodigo.PROPTY_NAME_CODIGO,
                (this.getCodigo() == null) ? "" : this.getCodigo().toString())

        .append(
                TipoCodigo.PROPTY_NAME_DESCRIPCION,
                (this.getDescripcion() == null) ? "" : this.getDescripcion()
                        .toString())

        .toString();
    }

}