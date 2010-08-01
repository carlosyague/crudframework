package org.librae.circulacion.model.pib;

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
 * Posibles tipos de resolución de una petición de PIB (dominio)<br>
 * Tabla precargada<br>
 * Valores:<br>
 * A - Aceptada<br>
 * D - Denegada<br>
 * N - La petición del lector necesita aclaración adicional
 * 
 * @author asantamaria
 */
@Entity(name = TipoResolucionPIB.ENTITY_NAME)
@Table(name = TipoResolucionPIB.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class TipoResolucionPIB extends BaseObject {

    /**
     * BaseObject is Serializable, so TipoResolucionPIB needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID                      = -2030245094574144188L;

    public static final String  ENTITY_NAME                           = "org.librae.circulacion.model.TipoResolucionPIB";
    public static final String  TABLE_NAME                            = "CIR_PIB_TIPOS_RESOLUCIONES";
    public static final String  ID_GENERATOR_NAME                     = "generator_cir_pib_tipos_resoluciones";
    private static final String ID_SEQUENCE_NAME                      = "SEQ_CIR_PIB_TIPOS_RESOLUCIONES";
    public static final String  COLUMN_NAME_ID                        = "X_PIB_RESOLUCION";
    public static final String  COLUMN_NAME_CODIGO                    = "C_PIB_RESOLUCION";
    public static final String  COLUMN_NAME_NOMBRE                    = "T_PIB_RESOLUCION";
    public static final String  COLUMN_NAME_DESCRIPCION_ALTERNATIVA   = "T_PIB_RESOLUCION_ALT";

    public static final String  PROPERTY_NAME_ID                      = "id";
    public static final String  PROPERTY_NAME_CODIGO                  = "codigo";
    public static final String  PROPERTY_NAME_NOMBRE                  = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION_ALTERNATIVA = "descripcionAlternativa";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * A, D ó N
     */
    private String              codigo;

    /**
     * NOmbre del tipo de resolución:<br>
     * Valores:<br>
     * Aceptada<br>
     * Denegada<br>
     * Se necesita información adicional
     */
    private String              nombre;

    /**
     * Explicación o descripción alternativa del tipo de resolución
     */
    private String              descripcionAlternativa;

    protected TipoResolucionPIB() {
        super();
    }

    public TipoResolucionPIB(String codigo, String nombre) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = TipoResolucionPIB.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    @Column(name = TipoResolucionPIB.COLUMN_NAME_CODIGO, length = 40)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    @Column(name = TipoResolucionPIB.COLUMN_NAME_NOMBRE, length = 80)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcionAlternativa
     */
    @Column(name = TipoResolucionPIB.COLUMN_NAME_DESCRIPCION_ALTERNATIVA)
    public String getDescripcionAlternativa() {
        return descripcionAlternativa;
    }

    /**
     * @param descripcionAlternativa
     *            the descripcionAlternativa to set
     */
    public void setDescripcionAlternativa(String descripcionAlternativa) {
        this.descripcionAlternativa = descripcionAlternativa;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof TipoResolucionPIB)) {
            return false;
        }

        final TipoResolucionPIB other = (TipoResolucionPIB) obj;

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null
                && !getCodigo().equals(other.getCodigo())) {
            return false;
        }

        if (getNombre() == null && other.getNombre() != null) {
            return false;
        }
        if (getNombre() != null
                && !getNombre().equals(other.getNombre())) {
            return false;
        }

        return true;

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime
                * result
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        result = prime
                * result
                + ((getNombre() == null) ? 0 : getNombre().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(PROPERTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(PROPERTY_NAME_NOMBRE,
                (getNombre() == null) ? "" : getNombre().toString())

        .append(
                PROPERTY_NAME_DESCRIPCION_ALTERNATIVA,
                (getDescripcionAlternativa() == null) ? "" : getDescripcionAlternativa().toString())

        .toString();
    }

}