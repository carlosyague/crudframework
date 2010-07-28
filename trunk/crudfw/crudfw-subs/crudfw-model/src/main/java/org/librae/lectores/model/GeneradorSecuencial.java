package org.librae.lectores.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Bean que almacena una secuencia
 * 
 * @author jcdiaz
 */
@Entity(name = GeneradorSecuencial.ENTITY_NAME)
@Table(name = GeneradorSecuencial.TABLE_NAME)
public class GeneradorSecuencial {

    /**
     * BaseObject is Serializable, por lo tanto desiderata necesita un Serial
     * Version UID
     */
    private static final long  serialVersionUID  = 1;

    public static final String ENTITY_NAME       = "org.librae.lectores.model.GeneradorSecuencial";

    /**
     * Constantes para referencia de los atributos de GeneradorSecuencial
     */
    public static final String PROPTY_NAME_ID    = "id";
    public static final String PROPTY_NAME_VALUE = "valor";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String TABLE_NAME        = "LEC_GENERADOR_SECUENCIAL";
    public static final String ID_GENERATOR_NAME = "generator_lec_generador_secuencial";
    public static final String ID_SEQUENCE_NAME  = "SEQ_LEC_GENERADOR_SECUENCIAL";

    public static final String COLUMN_NAME_ID    = "X_GENERADOR_SECUENCIAL";
    public static final String COLUMN_NAME_VALOR = "N_VALOR";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial.
     */
    private Long               id;

    /**
     * Valor secuencial.
     */
    private Long               valor;

    /**
     * Constructor sin parámetros
     */
    protected GeneradorSecuencial() {
        super();
    }

    /**
     * Constructor con parámetros
     */
    public GeneradorSecuencial(final Long valor) {
        super();
        this.valor = valor;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = GeneradorSecuencial.ID_GENERATOR_NAME)
    @SequenceGenerator(name = GeneradorSecuencial.ID_GENERATOR_NAME, sequenceName = GeneradorSecuencial.ID_SEQUENCE_NAME)
    @Column(name = GeneradorSecuencial.COLUMN_NAME_ID, nullable = false)
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
     * @return the valor
     */
    @Column(name = GeneradorSecuencial.COLUMN_NAME_VALOR, nullable = false, unique = true)
    public Long getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(final Long valor) {
        this.valor = valor;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
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
        if (!(obj instanceof MotivoBloqueo)) {
            return false;
        }
        final GeneradorSecuencial other = (GeneradorSecuencial) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (this.valor == null) {
            if (other.valor != null) {
                return false;
            }
        } else if (this.valor != other.valor) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(MotivoBloqueo.COLUMN_NAME_ID,
                id).append(GeneradorSecuencial.PROPTY_NAME_VALUE,
                (null == this.valor) ? 0 : valor).toString();
    }

}
