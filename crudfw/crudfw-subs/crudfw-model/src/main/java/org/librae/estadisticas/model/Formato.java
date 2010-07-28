/**
 *
 */
package org.librae.estadisticas.model;

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
 * @author jVillegas
 */
@Entity(name = Formato.NAME_ENTITY)
@Table(name = Formato.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Formato extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID          = 1L;

    public static final String  NAME_ENTITY               = "org.librae.estadisticas.model.Formato";
    public static final String  TABLE_NAME                = "EIL_FORMATOS";
    private static final String ID_GENERATOR_NAME         = "generator_eil_formatos";
    private static final String ID_SEQUENCE_NAME          = "SEQ_EIL_FORMATOS";
    public static final String  COLUMN_NAME_ID            = "X_FORMATO";
    public static final String  COLUMN_NAME_DESCRIPCION   = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_EXTENSION     = "T_EXTENSION";

    public static final String  PROPERTY_NAME_ID          = "id";
    public static final String  PROPERTY_NAME_DESCRIPCION = "descripcion";
    public static final String  PROPERTY_NAME_EXTENSION   = "extension";

    /**
     * Clave primaria.
     */
    private Long                id;
    /**
     * Extension correspondiente al formato.
     */
    private String              extension;
    /**
     * Descripción del formato.
     */
    private String              descripcion;

    /**
     * Constructor sin parametros
     */
    protected Formato() {

    }

    /**
     * @param extension
     * @param descripcion
     */
    public Formato(String extension) {
        super();
        this.extension = extension;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Formato.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the extension
     */
    @Column(name = Formato.COLUMN_NAME_EXTENSION, nullable = false, length = 10)
    public String getExtension() {
        return extension;
    }

    /**
     * @param extensionFinal
     *            the extension to set.
     */
    public void setExtension(String extensionFinal) {
        this.extension = extensionFinal;
    }

    /**
     * @return the descripcion.
     */
    @Column(name = Formato.COLUMN_NAME_DESCRIPCION, length = 40)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcionFinal
     *            the descripcion to set.
     */
    public void setDescripcion(String descripcionFinal) {
        this.descripcion = descripcionFinal;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Formato)) {
            return false;
        }
        final Formato other = (Formato) obj;
        if (extension == null) {
            if (other.extension != null) {
                return false;
            }
        } else if (!extension.equals(other.extension)) {
            return false;
        }
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        } else if (!descripcion.equals(other.descripcion)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result
                + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((extension == null) ? 0 : extension.hashCode());
        result = prime * result
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Formato.COLUMN_NAME_ID, id)
                .append(Formato.COLUMN_NAME_DESCRIPCION, descripcion).append(
                        Formato.COLUMN_NAME_EXTENSION, extension).toString();
    }

}
