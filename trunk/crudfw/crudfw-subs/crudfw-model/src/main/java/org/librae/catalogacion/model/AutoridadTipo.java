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

/**
 * Bean para almacenar una AutoridadTipo
 *
 * @author jcdiaz
 */
@Entity(name = AutoridadTipo.ENTITY_NAME)
@Table(name = AutoridadTipo.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class AutoridadTipo extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto AutoridadTipo necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID   = 4193306407498468443L;

    public static final String  ENTITY_NAME        = "org.librae.catalogacion.model.AutoridadTipo";

    /**
     * Constantes para referencia de los atributos de AutoridadTipo
     */
    public static final String  PROPTY_NAME_ID     = "id";
    public static final String  PROPTY_NAME_CODIGO = "codigo";
    public static final String  PROPTY_NAME_TIPO   = "tipo";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME         = "CAT_AUTORIDAD_TIPO";
    private static final String ID_GENERATOR_NAME  = "generator_cat_autoridad_tipo";
    private static final String ID_SEQUENCE_NAME   = "SEQ_CAT_AUTORIDAD_TIPO";

    public static final String  COLUMN_NAME_ID     = "X_AUTORIDAD_TIPO";
    public static final String  COLUMN_NAME_CODIGO = "C_CODIGO";
    public static final String  COLUMN_NAME_TIPO   = "T_TIPO";

    /**
     *
     */
    private Long                id;

    /**
    *
    */
    private String              codigo;

    /**
     *
     */
    private String              tipo;

    /**
     * Constructor sin parámetros
     */
    protected AutoridadTipo() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param tipo
     */
    public AutoridadTipo(final String codigo, final String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = AutoridadTipo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = AutoridadTipo.ID_SEQUENCE_NAME)
    @Column(name = AutoridadTipo.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = AutoridadTipo.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
     * @return the tipo
     */
    @Column(name = AutoridadTipo.COLUMN_NAME_TIPO, nullable = false, unique = true, length = 255)
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     *            the tipo to set
     */
    public void setTipo(final String tipo) {
        this.tipo = tipo;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        if (!(obj instanceof AutoridadTipo)) {
            return false;
        }
        final AutoridadTipo other = (AutoridadTipo) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (tipo == null) {
            if (other.tipo != null) {
                return false;
            }
        } else if (!tipo.equals(other.tipo)) {
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

        .append(AutoridadTipo.PROPTY_NAME_ID, this.id)

        .append(AutoridadTipo.PROPTY_NAME_CODIGO,
                (this.codigo == null) ? 0 : this.codigo)

        .append(AutoridadTipo.PROPTY_NAME_TIPO,
                (this.tipo == null) ? 0 : this.tipo)

        .toString();
    }

}
