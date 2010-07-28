package org.librae.catalogacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar una Procedencia
 *
 * @author jcdiaz
 */
@Entity(name = Procedencia.ENTITY_NAME)
@Table(name = Procedencia.TABLE_NAME)
public class Procedencia extends SpringRemotableLazyEntity<Procedencia> {

    /**
     * BaseObject es Serializable, por lo tanto Procedencia necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID        = -7524130586431004428L;

    public static final String  ENTITY_NAME             = "org.librae.catalogacion.model.Procedencia";

    /**
     * Constantes para referencia de los atributos de Procedencia
     */
    public static final String  PROPTY_NAME_ID          = "id";
    public static final String  PROPTY_NAME_CODIGO      = "codigo";
    public static final String  PROPTY_NAME_PROCEDENCIA = "procedencia";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME              = "CAT_PROCEDENCIAS";
    private static final String ID_GENERATOR_NAME       = "generator_cat_procedencias";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CAT_PROCEDENCIAS";

    public static final String  COLUMN_NAME_ID          = "X_PROCEDENCIA";
    public static final String  COLUMN_NAME_CODIGO      = "C_CODIGO";
    public static final String  COLUMN_NAME_PROCEDENCIA = "T_PROCEDENCIA";

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
    private String              procedencia;

    /**
     * Constructor sin parámetros
     */
    protected Procedencia() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param procedencia
     */
    public Procedencia(final String codigo, final String procedencia) {
        super();
        this.codigo = codigo;
        this.procedencia = procedencia;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Procedencia.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Procedencia.ID_SEQUENCE_NAME)
    @Column(name = Procedencia.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = Procedencia.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
     * @return the procedencia
     */
    @Column(name = Procedencia.COLUMN_NAME_PROCEDENCIA, nullable = false, length = 80)
    public String getProcedencia() {
        return procedencia;
    }

    /**
     * @param procedencia
     *            the procedencia to set
     */
    public void setProcedencia(final String procedencia) {
        this.procedencia = procedencia;
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
                + ((procedencia == null) ? 0 : procedencia.hashCode());
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
        if (!(obj instanceof Procedencia)) {
            return false;
        }
        final Procedencia other = (Procedencia) obj;
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
        if (procedencia == null) {
            if (other.procedencia != null) {
                return false;
            }
        } else if (!procedencia.equals(other.procedencia)) {
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

        .append(Procedencia.PROPTY_NAME_ID, id)

        .append(Procedencia.PROPTY_NAME_CODIGO, (codigo == null) ? 0 : codigo)

        .append(Procedencia.PROPTY_NAME_PROCEDENCIA,
                (procedencia == null) ? 0 : procedencia)

        .toString();
    }

    @Override
    public Procedencia newInstance() {
        return new Procedencia();
    }

}
