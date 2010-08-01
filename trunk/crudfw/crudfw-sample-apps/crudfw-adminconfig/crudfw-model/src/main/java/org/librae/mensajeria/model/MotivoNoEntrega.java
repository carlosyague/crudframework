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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * @author amDelcampo
 */
@Entity(name = MotivoNoEntrega.NAME_ENTITY)
@Table(name = MotivoNoEntrega.TABLE_NAME)
public class MotivoNoEntrega extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID          = 4853899094282925163L;

    public static final String  NAME_ENTITY               = "org.librae.mensajeria.model.MotivoNoEntrega";
    public static final String  TABLE_NAME                = "MEN_MOTIVO_NO_ENTREGA";

    private static final String ID_GENERATOR_NAME         = "generator_men_notificaciones";
    private static final String ID_SEQUENCE_NAME          = "SEQ_MEN_MOTIVO_NO_ENTREGA";

    public static final String  COLUMN_NAME_ID            = "X_MOTIVO_NO_ENTREGA";
    public static final String  COLUMN_NAME_NOMBRE        = "T_NOMBRE";
    public static final String  COLUMN_NAME_DESCRIPCION   = "T_DESCRIPCION";

    public static final String  PROPERTY_NAME_ID          = "id";
    public static final String  PROPERTY_NAME_NOMBRE      = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION = "descripcion";

    /**
     * Clave primaria.
     */
    private Long                id;

    /**
     * Nombre.
     */
    private String              nombre;

    /**
     * Descripción.
     */
    private String              descripcion;

    /**
     *
     */
    protected MotivoNoEntrega() {
        super();
    }

    public MotivoNoEntrega(String descripcion, String nombre) {
        super();
        this.setDescripcion(descripcion);
        this.setNombre(nombre);
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = MotivoNoEntrega.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = MotivoNoEntrega.ID_SEQUENCE_NAME)
    @Column(name = MotivoNoEntrega.COLUMN_NAME_ID, nullable = false)
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
     * @return the nombre
     */
    @Column(name = MotivoNoEntrega.COLUMN_NAME_NOMBRE, length = 80)
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
    @Column(name = MotivoNoEntrega.COLUMN_NAME_DESCRIPCION, length = 255)
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

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MotivoNoEntrega)) {
            return false;
        }
        final MotivoNoEntrega other = (MotivoNoEntrega) obj;
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
                TipoNotificacion.COLUMN_NAME_ID, id).append(
                TipoNotificacion.COLUMN_NAME_NOMBRE, nombre).append(
                TipoNotificacion.COLUMN_NAME_DESCRIPCION, descripcion)
                .toString();
    }

}
