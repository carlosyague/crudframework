package org.librae.catalogacion.model;

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
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar una RegistroFormato
 *
 * @author jcdiaz
 */
@Entity(name = RegistroFormato.ENTITY_NAME)
@Table(name = RegistroFormato.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class RegistroFormato extends SpringRemotableLazyEntity<RegistroFormato> {

    /**
     * BaseObject es Serializable, por lo tanto RegistroFormato necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID    = -4892543449791314720L;

    public static final String  ENTITY_NAME         = "org.librae.catalogacion.model.RegistroFormato";

    /**
     * Constantes para referencia de los atributos de RegistroFormato
     */
    public static final String  PROPTY_NAME_ID      = "id";
    public static final String  PROPTY_NAME_CODIGO  = "codigo";
    public static final String  PROPTY_NAME_FORMATO = "formato";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME          = "CAT_REGISTRO_FORMATO";
    private static final String ID_GENERATOR_NAME   = "generator_cat_registro_formato";
    private static final String ID_SEQUENCE_NAME    = "SEQ_CAT_REGISTRO_FORMATO";

    public static final String  COLUMN_NAME_ID      = "X_REGISTRO_FORMATO";
    public static final String  COLUMN_NAME_CODIGO  = "C_CODIGO";
    public static final String  COLUMN_NAME_FORMATO = "T_FORMATO";

    /**
     * Clave autonumérica secuencial sin significado funcional
     */
    private Long                id;

    /**
    *
    */
    private String              codigo;

    /**
     *
     */
    private String              formato;

    /**
     * Constructor sin parámetros
     */
    protected RegistroFormato() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param formato
     */
    public RegistroFormato(final String codigo, final String formato) {
        super();
        this.codigo = codigo;
        this.formato = formato;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = RegistroFormato.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = RegistroFormato.ID_SEQUENCE_NAME)
    @Column(name = RegistroFormato.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = RegistroFormato.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
     * @return the formato
     */
    @Column(name = RegistroFormato.COLUMN_NAME_FORMATO, nullable = false, unique = true, length = 80)
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato
     *            the formato to set
     */
    public void setFormato(final String formato) {
        this.formato = formato;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((formato == null) ? 0 : formato.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (!(obj instanceof RegistroFormato)) {
            return false;
        }
        final RegistroFormato other = (RegistroFormato) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (formato == null) {
            if (other.formato != null) {
                return false;
            }
        } else if (!formato.equals(other.formato)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this)

        .append(RegistroFormato.PROPTY_NAME_ID, this.id)

        .append(RegistroFormato.PROPTY_NAME_CODIGO,
                (this.codigo == null) ? 0 : this.codigo)

        .append(RegistroFormato.PROPTY_NAME_FORMATO,
                (this.formato == null) ? 0 : this.formato)

        .toString();
    }

    @Override
    public RegistroFormato newInstance() {

        return new RegistroFormato();
    }

}
