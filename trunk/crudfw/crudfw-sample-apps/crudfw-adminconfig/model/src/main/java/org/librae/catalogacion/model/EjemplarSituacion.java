package org.librae.catalogacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.circulacion.model.Reserva;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * Bean para almacenar una EjemplarSituacion
 *
 * @author jcdiaz
 */
@Entity(name = EjemplarSituacion.ENTITY_NAME)
@Table(name = EjemplarSituacion.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class EjemplarSituacion extends
        SpringRemotableLazyEntity<EjemplarSituacion> {

    /**
     * BaseObject es Serializable, por lo tanto EjemplarSituacion necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID      = -4853516580317292314L;

    public static final String  ENTITY_NAME           = "org.librae.catalogacion.model.EjemplarSituacion";

    /**
     * Constantes para referencia de los atributos de EjemplarSituacion
     */
    public static final String  PROPTY_NAME_ID        = "id";
    public static final String  PROPTY_NAME_CODIGO    = "codigo";
    public static final String  PROPTY_NAME_SITUACION = "situacion";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME            = "CAT_EJEMPLAR_SITUACIONES";
    private static final String ID_GENERATOR_NAME     = "generator_cat_ejemplar_situaciones";
    private static final String ID_SEQUENCE_NAME      = "SEQ_CAT_EJEMPLAR_SITUACION";

    public static final String  COLUMN_NAME_ID        = "X_EJEMPLAR_SITUACION";
    public static final String  COLUMN_NAME_CODIGO    = "C_CODIGO";
    public static final String  COLUMN_NAME_SITUACION = "T_SITUACION";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Codigo que identifica la situación del ejemplar
     */
    private String              codigo;

    /**
     * Almacena la situacion de un ejemplar
     */
    private String              situacion;

    /**
     * Constructor sin parámetros
     */
    protected EjemplarSituacion() {
        super();
    }

    /**
     * Constructor sin parámetros
     *
     * @param codigo
     * @param situacion
     */
    public EjemplarSituacion(final String codigo, final String situacion) {
        super();
        this.codigo = codigo;
        this.situacion = situacion;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EjemplarSituacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = EjemplarSituacion.ID_SEQUENCE_NAME)
    @Column(name = EjemplarSituacion.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = EjemplarSituacion.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
    @Column(name = EjemplarSituacion.COLUMN_NAME_SITUACION, nullable = false, length = 80)
    public String getSituacion() {
        return situacion;
    }

    /**
     * @param soporte
     *            the soporte to set
     */
    public void setSituacion(final String situacion) {
        this.situacion = situacion;
    }

    /**
     * Traduce el campo descripcionTipoLector.
     *
     * @return
     */
    @Transient
    public String getSituacionLocale() {
        final String s = ComboLocaleManager.get(situacion.replace(
                "#", ""));
        return (s == null) ? "" : s;
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
                + ((situacion == null) ? 0 : situacion.hashCode());
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
        if (!(obj instanceof EjemplarSituacion)) {
            return false;
        }
        final EjemplarSituacion other = (EjemplarSituacion) obj;
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
        if (situacion == null) {
            if (other.situacion != null) {
                return false;
            }
        } else if (!situacion.equals(other.situacion)) {
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

        .append(EjemplarSituacion.PROPTY_NAME_ID, id)

        .append(EjemplarSituacion.PROPTY_NAME_CODIGO,
                (codigo == null) ? 0 : codigo)

        .append(EjemplarSituacion.PROPTY_NAME_SITUACION,
                (situacion == null) ? 0 : situacion)

        .toString();
    }

    @Override
    public EjemplarSituacion newInstance() {
        return new EjemplarSituacion();
    }

}
