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
import org.librae.circulacion.model.Reserva;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar una EjemplarSoporte
 *
 * @author jcdiaz
 */
@Entity(name = EjemplarSoporte.ENTITY_NAME)
@Table(name = EjemplarSoporte.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class EjemplarSoporte extends
        SpringRemotableLazyEntity<EjemplarSoporte> {

    /**
     * BaseObject es Serializable, por lo tanto EjemplarSoporte necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID    = -4853516580317292314L;

    public static final String  ENTITY_NAME         = "org.librae.catalogacion.model.EjemplarSoporte";

    /**
     * Constantes para referencia de los atributos de EjemplarSoporte
     */
    public static final String  PROPTY_NAME_ID      = "id";
    public static final String  PROPTY_NAME_CODIGO  = "codigo";
    public static final String  PROPTY_NAME_SOPORTE = "soporte";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME          = "CAT_EJEMPLAR_SOPORTES";
    private static final String ID_GENERATOR_NAME   = "generator_cat_ejemplar_soportes";
    private static final String ID_SEQUENCE_NAME    = "SEQ_CAT_EJEMPLAR_SOPORTES";

    public static final String  COLUMN_NAME_ID      = "X_EJEMPLAR_SOPORTE";
    public static final String  COLUMN_NAME_CODIGO  = "C_CODIGO";
    public static final String  COLUMN_NAME_SOPORTE = "T_SOPORTE";

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
    private String              soporte;

    /**
     * Constructor sin parámetros
     */
    protected EjemplarSoporte() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param soporte
     */
    public EjemplarSoporte(final String codigo, final String soporte) {
        super();
        this.codigo = codigo;
        this.soporte = soporte;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EjemplarSoporte.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = EjemplarSoporte.ID_SEQUENCE_NAME)
    @Column(name = EjemplarSoporte.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = EjemplarSoporte.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
     * @return the soporte
     */
    @Column(name = EjemplarSoporte.COLUMN_NAME_SOPORTE, nullable = false, length = 80)
    public String getSoporte() {
        return soporte;
    }

    /**
     * @param soporte
     *            the soporte to set
     */
    public void setSoporte(final String soporte) {
        this.soporte = soporte;
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
        result = prime * result + ((soporte == null) ? 0 : soporte.hashCode());
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
        if (!(obj instanceof EjemplarSoporte)) {
            return false;
        }
        final EjemplarSoporte other = (EjemplarSoporte) obj;
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
        if (soporte == null) {
            if (other.soporte != null) {
                return false;
            }
        } else if (!soporte.equals(other.soporte)) {
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

        .append(EjemplarSoporte.PROPTY_NAME_ID, id)

        .append(EjemplarSoporte.PROPTY_NAME_CODIGO,
                (codigo == null) ? 0 : codigo)

        .append(EjemplarSoporte.PROPTY_NAME_SOPORTE,
                (soporte == null) ? 0 : soporte)

        .toString();
    }

    @Override
    public EjemplarSoporte newInstance() {
        return new EjemplarSoporte();
    }

}
