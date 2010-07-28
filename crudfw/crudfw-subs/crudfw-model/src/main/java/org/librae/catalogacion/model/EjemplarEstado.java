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
import org.librae.circulacion.model.ValorOET;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar una EjemplarEstado
 *
 * @author jcdiaz
 */
@Entity(name = EjemplarEstado.ENTITY_NAME)
@Table(name = EjemplarEstado.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class EjemplarEstado extends
        SpringRemotableLazyEntity<EjemplarEstado> {

    /**
     * BaseObject es Serializable, por lo tanto EjemplarEstado necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID        = -1777014014957280114L;

    public static final String  ENTITY_NAME             = "org.librae.catalogacion.model.EjemplarEstado";

    /**
     * Constantes para referencia de los atributos de EjemplarEstado
     */
    public static final String  PROPTY_NAME_ID          = "id";
    public static final String  PROPTY_NAME_CODIGO      = "codigo";
    public static final String  PROPTY_NAME_ESTADO      = "estado";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME              = "CAT_EJEMPLAR_ESTADOS";
    private static final String ID_GENERATOR_NAME       = "generator_cat_ejemplar_estados";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CAT_EJEMPLAR_ESTADOS";

    public static final String  COLUMN_NAME_ID          = "X_EJEMPLAR_ESTADO";
    public static final String  COLUMN_NAME_CODIGO      = "C_CODIGO";
    public static final String  COLUMN_NAME_ESTADO      = "T_ESTADO";
    public static final String  COLUMN_NAME_ESTADO_NCIP = "NCIP_X_NCIP_VALUE_OET";

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
     *
     */
    private ValorOET            estadoNCIP;

    /**
     * Constructor sin parámetros
     */
    protected EjemplarEstado() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param estado
     */
    public EjemplarEstado(final String codigo, final String estado) {
        super();
        this.codigo = codigo;
        this.estado = estado;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EjemplarEstado.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = EjemplarEstado.ID_SEQUENCE_NAME)
    @Column(name = EjemplarEstado.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = EjemplarEstado.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
    @Column(name = EjemplarEstado.COLUMN_NAME_ESTADO, nullable = false, length = 80)
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

    /**
     * @return the estadoNCIP
     */
    public ValorOET getEstadoNCIP() {
        return estadoNCIP;
    }

    /**
     * @param estadoNCIP
     *            the estadoNCIP to set
     */
    public void setEstadoNCIP(final ValorOET estadoNCIP) {
        this.estadoNCIP = estadoNCIP;
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
        if (!(obj instanceof EjemplarEstado)) {
            return false;
        }
        final EjemplarEstado other = (EjemplarEstado) obj;
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

        .append(EjemplarEstado.PROPTY_NAME_ID, id)

        .append(EjemplarEstado.PROPTY_NAME_CODIGO,
                (codigo == null) ? 0 : codigo)

        .append(EjemplarEstado.PROPTY_NAME_ESTADO,
                (estado == null) ? 0 : estado)

        .toString();
    }

    @Override
    public EjemplarEstado newInstance() {
        return new EjemplarEstado();
    }

}
