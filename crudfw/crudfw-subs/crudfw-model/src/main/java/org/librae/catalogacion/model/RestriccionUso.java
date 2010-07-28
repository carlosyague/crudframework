package org.librae.catalogacion.model;

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
 * Bean para almacenar una RestriccionUso
 *
 * @author jcdiaz
 */
@Entity(name = RestriccionUso.ENTITY_NAME)
@Table(name = RestriccionUso.TABLE_NAME)
public class RestriccionUso extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto RestriccionUso necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID        = -8230319138121178760L;

    public static final String  ENTITY_NAME             = "org.librae.catalogacion.model.RestriccionUso";

    /**
     * Constantes para referencia de los atributos de RestriccionUso
     */
    public static final String  PROPTY_NAME_ID          = "id";
    public static final String  PROPTY_NAME_CODIGO      = "codigo";
    public static final String  PROPTY_NAME_RESTRICCION = "restriccion";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME              = "CAT_RESTRICCION_USO";
    private static final String ID_GENERATOR_NAME       = "generator_cat_restriccion_uso";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CAT_RESTRICCION_USO";

    public static final String  COLUMN_NAME_ID          = "X_RESTRICCION_USO";
    public static final String  COLUMN_NAME_CODIGO      = "C_CODIGO";
    public static final String  COLUMN_NAME_RESTRICCION = "T_RESTRICCION";

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
    private String              restriccion;

    /**
     * Constructor sin parámetros
     */
    protected RestriccionUso() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param restriccion
     */
    public RestriccionUso(final String codigo, final String restriccion) {
        super();
        this.codigo = codigo;
        this.restriccion = restriccion;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = RestriccionUso.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = RestriccionUso.ID_SEQUENCE_NAME)
    @Column(name = RestriccionUso.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = RestriccionUso.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
     * @return the restriccion
     */
    @Column(name = RestriccionUso.COLUMN_NAME_RESTRICCION, nullable = false, unique = true, length = 80)
    public String getRestriccion() {
        return restriccion;
    }

    /**
     * @param restriccion
     *            the restriccion to set
     */
    public void setRestriccion(final String restriccion) {
        this.restriccion = restriccion;
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
        result = prime * result
                + ((restriccion == null) ? 0 : restriccion.hashCode());
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
        if (!(obj instanceof RestriccionUso)) {
            return false;
        }
        final RestriccionUso other = (RestriccionUso) obj;
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
        if (restriccion == null) {
            if (other.restriccion != null) {
                return false;
            }
        } else if (!restriccion.equals(other.restriccion)) {
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

        .append(RestriccionUso.PROPTY_NAME_ID, this.id)

        .append(RestriccionUso.PROPTY_NAME_CODIGO,
                (this.codigo == null) ? 0 : this.codigo)

        .append(RestriccionUso.PROPTY_NAME_RESTRICCION,
                (this.restriccion == null) ? 0 : this.restriccion)

        .toString();
    }

}
