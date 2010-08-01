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
 * Bean para almacenar una RegistroEstado
 *
 * @author jcdiaz
 */
@Entity(name = RegistroEstado.ENTITY_NAME)
@Table(name = RegistroEstado.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class RegistroEstado extends SpringRemotableLazyEntity<RegistroEstado> {

    /**
     * BaseObject es Serializable, por lo tanto RegistroEstado necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID   = -6406518230143283455L;

    public static final String  ENTITY_NAME        = "org.librae.catalogacion.model.RegistroEstado";

    /**
     * Constantes para referencia de los atributos de RegistroEstado
     */
    public static final String  PROPTY_NAME_ID     = "id";
    public static final String  PROPTY_NAME_CODIGO = "codigo";
    public static final String  PROPTY_NAME_ESTADO = "estado";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME         = "CAT_REGISTRO_ESTADO";
    private static final String ID_GENERATOR_NAME  = "generator_cat_registro_estado";
    private static final String ID_SEQUENCE_NAME   = "SEQ_CAT_REGISTRO_ESTADO";

    public static final String  COLUMN_NAME_ID     = "X_REGISTRO_ESTADO";
    public static final String  COLUMN_NAME_CODIGO = "C_CODIGO";
    public static final String  COLUMN_NAME_ESTADO = "T_ESTADO";

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
    private String              estado;

    /**
     * Constructor sin parámetros
     */
    protected RegistroEstado() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param estado
     */
    public RegistroEstado(final String codigo, final String estado) {
        super();
        this.codigo = codigo;
        this.estado = estado;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = RegistroEstado.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = RegistroEstado.ID_SEQUENCE_NAME)
    @Column(name = RegistroEstado.COLUMN_NAME_ID, nullable = false)
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

    /*
     * @return the codigo
     */
    @Column(name = RegistroEstado.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
     * @return the estado
     */
    @Column(name = RegistroEstado.COLUMN_NAME_ESTADO, nullable = false, unique = true, length = 80)
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            the estado to set
     */
    public void setEstado(final String estado) {
        this.estado = estado;
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
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
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
        if (!(obj instanceof RegistroEstado)) {
            return false;
        }
        final RegistroEstado other = (RegistroEstado) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (estado == null) {
            if (other.estado != null) {
                return false;
            }
        } else if (!estado.equals(other.estado)) {
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

        .append(RegistroEstado.PROPTY_NAME_ID, this.id)

        .append(RegistroEstado.PROPTY_NAME_CODIGO,
                (this.codigo == null) ? 0 : this.codigo)

        .append(RegistroEstado.PROPTY_NAME_ESTADO,
                (this.estado == null) ? 0 : this.estado)

        .toString();
    }

    @Override
    public RegistroEstado newInstance() {

        return new RegistroEstado();
    }

}
