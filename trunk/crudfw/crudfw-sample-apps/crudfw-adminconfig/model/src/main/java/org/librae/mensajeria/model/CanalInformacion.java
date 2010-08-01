/**
 *
 */
package org.librae.mensajeria.model;

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
import org.librae.common.model.BaseObject;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * Bean para almacenar un Canal de Información.
 *
 * @author amDelcampo
 */
@Entity(name = CanalInformacion.NAME_ENTITY)
@Table(name = CanalInformacion.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class CanalInformacion extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID          = -6016777894218801679L;
    public static final String  NAME_ENTITY               = "org.librae.mensajeria.model.CanalInformacion";
    public static final String  TABLE_NAME                = "MEN_CANALES_INFORMACION";

    private static final String ID_GENERATOR_NAME         = "generator_men_canales_inform";
    private static final String ID_SEQUENCE_NAME          = "SEQ_MEN_CANALES_INFORMACION";

    public static final String  COLUMN_NAME_ID            = "X_CANAL_INFORMACION";
    public static final String  COLUMN_NAME_ACCION        = "L_ACCION_BIBLIOTECARIO";
    public static final String  COLUMN_NAME_NOMBRE        = "T_NOMBRE";
    public static final String  COLUMN_NAME_DESCRIPCION   = "T_DESCRIPCION";

    public static final String  PROPERTY_NAME_ID          = "id";
    public static final String  PROPERTY_NAME_ACTIVO      = "accion";
    public static final String  PROPERTY_NAME_NOMBRE      = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION = "descripcion";

    /**
     * Clave primaria.
     */
    private Long                id;

    /**
     * Indica si la transmisión de la información requiere una acción por parte
     * del bibliotecario.
     */
    private Boolean             accion;
    /**
     * Nombre del canal de información. SMS, email, teléfono, fax.
     */
    private String              nombre;

    /**
     * Descripción del canal de información.
     */
    private String              descripcion;

    /**
     *
     */
    public CanalInformacion() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = CanalInformacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = CanalInformacion.ID_GENERATOR_NAME, sequenceName = CanalInformacion.ID_SEQUENCE_NAME)
    @Column(name = CanalInformacion.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the accion
     */
    @Column(name = CanalInformacion.COLUMN_NAME_ACCION, nullable = false)
    public Boolean getAccion() {
        return accion;
    }

    /**
     * @param accion
     *            the accion to set
     */
    public void setAccion(Boolean accion) {
        this.accion = accion;
    }

    /**
     * @return the nombre
     */
    @Column(name = CanalInformacion.COLUMN_NAME_NOMBRE, length = 40)
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
     * @return the descripcion
     */
    @Column(name = CanalInformacion.COLUMN_NAME_DESCRIPCION, length = 80)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Traduce el campo nombre.
     *
     * @return
     */
    @Transient
    public String getNombreLocale() {
        final String s = ComboLocaleManager.get(nombre.replace("#", ""));
        return (s == null) ? "" : s;
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
        if (!(obj instanceof CanalInformacion)) {
            return false;
        }
        final CanalInformacion other = (CanalInformacion) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        return new ToStringBuilder(this).append(
                CanalInformacion.COLUMN_NAME_ID, id).append(
                CanalInformacion.COLUMN_NAME_NOMBRE, nombre).append(
                CanalInformacion.COLUMN_NAME_DESCRIPCION, descripcion).append(
                CanalInformacion.COLUMN_NAME_ACCION, accion).toString();
    }

}
