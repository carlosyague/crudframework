package org.librae.adquisicion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un TipoOperacion
 * 
 * @author jcdiaz
 */
@Entity(name = TipoOperacion.ENTITY_NAME)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = TipoOperacion.TABLE_NAME)
public class TipoOperacion extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Factura necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID   = 6596258231008773961L;

    public static final String  ENTITY_NAME        = "org.librae.adquisicion.model.TipoOperacion";
    public static final String  TABLE_NAME         = "ADQ_TIPOS_OPERACION";
    private static final String ID_GENERATOR_NAME  = "generator_adq_tipos_operacions";
    private static final String ID_SEQUENCE_NAME   = "SEQ_ADQ_TIPOS_OPERACION";
    public static final String  COLUMN_NAME_ID     = "X_TIPO_OPERACION";
    public static final String  COLUMN_NAME_NOMBRE = "T_NOMBRE";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     *
     */
    private String              nombre;

    /**
     * COnstructor sin parámetros
     */
    protected TipoOperacion() {
    }

    /**
     * Constructor con parámetros
     * 
     * @param nombre
     */
    public TipoOperacion(String nombre) {
        this.nombre = nombre;
    }

    /*
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TipoOperacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = TipoOperacion.ID_SEQUENCE_NAME)
    @Column(name = TipoOperacion.COLUMN_NAME_ID)
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
     * @return the nombre
     */
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TipoOperacion)) {
            return false;
        }
        TipoOperacion other = (TipoOperacion) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(TipoOperacion.COLUMN_NAME_ID,
                this.id).append(TipoOperacion.COLUMN_NAME_NOMBRE, this.nombre)
                .toString();
    }
}