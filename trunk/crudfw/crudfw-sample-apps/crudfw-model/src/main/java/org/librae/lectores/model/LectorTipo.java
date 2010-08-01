package org.librae.lectores.model;

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
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar un LectorTipo
 * 
 * @author jcdiaz
 */
@Entity(name = LectorTipo.ENTITY_NAME)
@Table(name = LectorTipo.TABLE_NAME)
public class LectorTipo extends SpringRemotableLazyEntity<LectorTipo> {

    /**
     * BaseObject is Serializable, por lo tanto necesita un Serial Version UID
     */
    private static final long  serialVersionUID           = -549815633752800961L;

    public static final String ENTITY_NAME                = "org.librae.lectores.model.LectorTipo";

    /**
     * Constantes para referencia de los atributos de LectorTipo
     */
    public static final String PROPTY_NAME_ID             = "id";
    public static final String PROPTY_NAME_VALOR_DEFECTO  = "valorDefecto";
    public static final String PROPTY_NAME_LECTOR         = "lector";
    public static final String PROPTY_NAME_TIPO_LECTOR    = "tipoLector";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String TABLE_NAME                 = "LEC_LECTOR_TIPOS";
    public static final String ID_GENERATOR_NAME          = "generator_lec_lector_tipos";
    public static final String ID_SEQUENCE_NAME           = "SEQ_LEC_LECTOR_TIPO";

    public static final String COLUMN_NAME_ID             = "X_LECTOR_TIPO";
    public static final String COLUMN_NAME_VALORDEFECTO   = "L_VALOR_DEFECTO";

    public static final String COLUMN_NAME_LECTOR_FK      = "LEC_X_LEC_LECTOR";
    public static final String COLUMN_NAME_TIPO_LECTOR_FK = "LEC_X_LEC_TIPO";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long               id;

    /**
     *
     */
    private Boolean            valorDefecto;

    /**
     * Clave foránea a la tabla de lector para identificar el lector que tiene
     * un tipo
     */
    private Lector             lector;

    /**
     * Clave foránea a la tabla de TipoLector para identificar el tipo de lector
     * asociado al lector
     */
    private TipoLector         tipoLector;

    /**
     * Constructor sin parámetros
     */
    protected LectorTipo() {
        super();
    }

    /**
     * Constructor con parámetros
     * 
     * @param tipoLector
     * @param valorDefecto
     */
    public LectorTipo(final TipoLector tipoLector, final Boolean valorDefecto) {
        super();
        this.tipoLector = tipoLector;
        this.valorDefecto = valorDefecto;
    }

    /**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = LectorTipo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = LectorTipo.ID_GENERATOR_NAME, sequenceName = LectorTipo.ID_SEQUENCE_NAME)
    @Column(name = LectorTipo.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return valorDefecto
     */
    @Column(name = LectorTipo.COLUMN_NAME_VALORDEFECTO, nullable = false)
    public Boolean getValorDefecto() {
        return valorDefecto;
    }

    /**
     * @param valorDefecto
     */
    public void setValorDefecto(final Boolean valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    /**
     * @return the lector
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Lector.class)
    @JoinColumn(name = LectorTipo.COLUMN_NAME_LECTOR_FK, nullable = false)
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector
     *            the lector to set
     */
    public void setLector(final Lector lector) {
        this.lector = lector;
    }

    /**
     * @return the tipoLector
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = TipoLector.class)
    @JoinColumn(name = LectorTipo.COLUMN_NAME_TIPO_LECTOR_FK, nullable = false)
    public TipoLector getTipoLector() {
        return tipoLector;
    }

    /**
     * @param tipoLector
     *            the tipoLector to set
     */
    public void setTipoLector(final TipoLector tipoLector) {
        this.tipoLector = tipoLector;
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
        result = prime * result
                + ((valorDefecto == null) ? 0 : valorDefecto.hashCode());
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
        if (!(obj instanceof LectorTipo)) {
            return false;
        }
        final LectorTipo other = (LectorTipo) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (valorDefecto == null) {
            if (other.valorDefecto != null) {
                return false;
            }
        } else if (!valorDefecto.equals(other.valorDefecto)) {
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

        .append(LectorTipo.COLUMN_NAME_ID, id)

        .append(LectorTipo.COLUMN_NAME_VALORDEFECTO,
                (valorDefecto == null) ? 0 : valorDefecto)

        .toString();
    }

    @Override
    public LectorTipo newInstance() {
        return new LectorTipo();
    }
}
