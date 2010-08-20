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
import org.hibernate.annotations.ForeignKey;
import org.librae.common.model.BaseObject;

/**
 * Asignación de valores de códigos<br>
 * Cada fila de esta tabla representa la signación de un valor de algún código.
 *
 * @author asantamaría
 */
@Entity(name = AsignacionValorCodigo.ENTITY_NAME)
@Table(name = AsignacionValorCodigo.TABLE_NAME)
public class AsignacionValorCodigo extends BaseObject {

    /**
     * BaseObject is Serializable, so AsignacionValorCodigo needs a Serial
     * Version UID
     */
    private static final long   serialVersionUID         = 6460351658562405980L;

    public static final String  ENTITY_NAME              = "org.librae.adminconfig.model.AsignacionValorCodigo";
    public static final String  TABLE_NAME               = "ADM_CODIGOS_ASIG";
    public static final String  ID_GENERATOR_NAME        = "generator_adm_codigos_asig";
    private static final String ID_SEQUENCE_NAME         = "SEQ_ADM_CODIGOS_ASIG";

    public static final String  COLUMN_NAME_ID           = "X_CODIGO_VALOR";
    public static final String  COLUMN_NAME_VALOR_CODIGO = "VAL_X_VALOR_CODIGO";
    public static final String  COLUMN_NAME_LECTOR       = "LEC_X_LECTOR";
    public static final String  COLUMN_NAME_BIBLIOTECA   = "BIB_X_VAL_BIBLIOTECA";
    public static final String  COLUMN_NAME_TIPO_CODIGO  = "TIP_X_TIPO_CODIGO";

    public static final String  PROPTY_NAME_ID           = "id";
    public static final String  PROPTY_NAME_BIBLIOTECA   = "biblioteca";
    public static final String  PROPTY_NAME_LECTOR       = "lector";
    public static final String  PROPTY_NAME_VALOR_CODIGO = "valorCodigo";
    public static final String  PROPTY_NAME_TIPO_CODIGO  = "tipoCodigo";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Identificador de grupo de bibliotecas, biblioteca o sucursal (id de la
     * tabla BIBLIOTECAS)
     */
    private Biblioteca          biblioteca;

    /**
     * Valor de código. Id de la tabla CODIGOS_VAL
     */
    private ValorCodigo         valorCodigo;

    protected AsignacionValorCodigo() {
        super();
    }

    public AsignacionValorCodigo(final ValorCodigo valorCodigo,
            final Biblioteca biblioteca) {
        super();
        this.biblioteca = biblioteca;
        this.valorCodigo = valorCodigo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = AsignacionValorCodigo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = AsignacionValorCodigo.ID_GENERATOR_NAME, sequenceName = AsignacionValorCodigo.ID_SEQUENCE_NAME)
    @Column(name = AsignacionValorCodigo.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = AsignacionValorCodigo.COLUMN_NAME_BIBLIOTECA)
    @ForeignKey(name = "FK_BIB_X_VAL_BIBLIOTECA")
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca
     *            the biblioteca to set
     */
    public void setBiblioteca(final Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    /**
     * @return the valorCodigoBiblioteca
     */
    @ManyToOne(targetEntity = ValorCodigo.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = AsignacionValorCodigo.COLUMN_NAME_VALOR_CODIGO, nullable = false)
    @ForeignKey(name = "FK_AVC_VALOR_CODIGO")
    public ValorCodigo getValorCodigo() {
        return valorCodigo;
    }

    /**
     * @param valorCodigo
     *            the valorCodigo to set
     */
    public void setValorCodigo(final ValorCodigo valorCodigo) {
        this.valorCodigo = valorCodigo;
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
        if (!(obj instanceof AsignacionValorCodigo)) {
            return false;
        }

        final AsignacionValorCodigo other = (AsignacionValorCodigo) obj;

        if (getBiblioteca() != null
                && !getBiblioteca().equals(other.getBiblioteca())) {
            return false;
        }

        if (getValorCodigo() == null && other.getValorCodigo() != null) {
            return false;
        }
        if (getValorCodigo() != null
                && !getValorCodigo().equals(other.getValorCodigo())) {
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

        result = prime
                * result
                + ((getValorCodigo() == null) ? 0 : getValorCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)

        .append(AsignacionValorCodigo.PROPTY_NAME_ID, getId())

        .append(AsignacionValorCodigo.PROPTY_NAME_VALOR_CODIGO,
                (getValorCodigo() == null) ? "" : getValorCodigo().toString())

        .toString();
    }

}