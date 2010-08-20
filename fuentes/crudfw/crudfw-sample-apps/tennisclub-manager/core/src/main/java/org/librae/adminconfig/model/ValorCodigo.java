package org.librae.adminconfig.model;

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
 * Valores de códigos.
 *
 * @author asantamaría
 */
@Entity(name = ValorCodigo.ENTITY_NAME)
@Table(name = ValorCodigo.TABLE_NAME)
public class ValorCodigo extends BaseObject {

    /**
     * BaseObject is Serializable, so ValorCodigo needs a Serial Version UID
     */
    private static final long   serialVersionUID                    = 2764759550927899739L;

    public static final String  ENTITY_NAME                         = "org.librae.adminconfig.model.ValorCodigo";
    public static final String  TABLE_NAME                          = "ADM_CODIGOS_VAL";
    public static final String  ID_GENERATOR_NAME                   = "generator_adm_codigos_val";
    private static final String ID_SEQUENCE_NAME                    = "SEQ_ADM_CODIGOS_VAL";
    public static final String  COLUMN_NAME_ID                      = "X_COD_VAL";
    public static final String  COLUMN_NAME_VALOR                   = "C_COD_VAL";
    public static final String  COLUMN_NAME_DESCRIPCION             = "T_COD_VAL";
    public static final String  COLUMN_NAME_DESCRIPCION_ALTERNATIVA = "T_COD_VAL_ALT";
    public static final String  COLUMN_NAME_CODIGO                  = "COD_X_CODIGO";

    public static final String  PROPTY_NAME_ID                      = "id";
    public static final String  PROPTY_NAME_VALOR                   = "valor";
    public static final String  PROPTY_NAME_DESCRIPCION             = "descripcion";
    public static final String  PROPTY_NAME_DESCRIPCION_ALTERNATIVA = "descripcionAlternativa";
    public static final String  PROPTY_NAME_CODIGO                  = "codigo";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Valor de un código asignado por el usuaurio
     */
    private String              valor;

    /**
     * Descripción asignada por el usuario
     */
    private String              descripcion;

    /**
     * Descripción alternativa asignada por el usuario
     */
    private String              descripcionAlternativa;

    /**
     * Identificación del código al que pertenece el valor de código que
     * representa esta fila.
     */
    private Codigo              codigo;

    protected ValorCodigo() {
        super();
    }

    public ValorCodigo(final String valor) {
        super();
        this.valor = valor;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ValorCodigo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ValorCodigo.ID_GENERATOR_NAME, sequenceName = ValorCodigo.ID_SEQUENCE_NAME)
    @Column(name = ValorCodigo.COLUMN_NAME_ID)
    public Long getId() {
        return this.id;
    }

    /**
     * @return the valor
     */
    @Column(name = ValorCodigo.COLUMN_NAME_VALOR, nullable = false,length=40)
    public String getValor() {
        return this.valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(final String valor) {
        this.valor = valor;
    }

    /**
     * @return the descripcion
     */
    @Column(name = ValorCodigo.COLUMN_NAME_DESCRIPCION,length=40)
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
     * @return the descripcionAlternativa
     */
    @Column(name = ValorCodigo.COLUMN_NAME_DESCRIPCION_ALTERNATIVA)
    public String getDescripcionAlternativa() {
        return this.descripcionAlternativa;
    }

    /**
     * @param descripcionAlternativa
     *            the descripcionAlternativa to set
     */
    public void setDescripcionAlternativa(final String descripcionAlternativa) {
        this.descripcionAlternativa = descripcionAlternativa;
    }

    /**
     * @return the codigo
     */
    @ManyToOne(targetEntity = Codigo.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = ValorCodigo.COLUMN_NAME_CODIGO)
    public Codigo getCodigo() {
        return this.codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(final Codigo codigo) {
        this.codigo = codigo;
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
        if (!(obj instanceof ValorCodigo)) {
            return false;
        }

        final ValorCodigo other = (ValorCodigo) obj;

        if (getValor() == null && other.getValor() != null) {
            return false;
        }
        if (getValor() != null && !getValor().equals(other.getValor())) {
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
        result += ((this.id == null) ? 0 : getId().hashCode());

        result = prime * result
                + ((getValor() == null) ? 0 : getValor().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(ValorCodigo.PROPTY_NAME_ID, getId())

        .append(ValorCodigo.PROPTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(ValorCodigo.PROPTY_NAME_VALOR,
                (getValor() == null) ? "" : getValor().toString())

        .append(ValorCodigo.PROPTY_NAME_DESCRIPCION,
                (getDescripcion() == null) ? "" : getDescripcion().toString())

        .toString();
    }

}