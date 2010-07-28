package org.librae.circulacion.model.pib;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.BaseObject;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Tipos de eventos que el usuario puede registrar en un PIB. <br>
 * Por ejemplo: Respuesta a la petición de PIB (RequestItemResponse),
 * Notificación de préstamo del material solicitado (ItemCheckedOut), ...<br>
 * Tabla precargada
 * 
 * @author cyague
 */
@Entity(name = TipoEventoPIB.ENTITY_NAME)
@Table(name = TipoEventoPIB.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class TipoEventoPIB extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID          = 2359930769335707682L;

    public static final String  ENTITY_NAME               = "org.librae.circulacion.model.TipoEventoPIB";
    public static final String  TABLE_NAME                = "CIR_PIB_TIPOS_EVENTOS";

    public static final String  ID_GENERATOR_NAME         = "generator_cir_pib_tipo_evento";
    private static final String ID_SEQUENCE_NAME          = "SEQ_CIR_PIB_TIPO_EVENTO";
    public static final String  COLUMN_NAME_ID            = "X_PIB_TIPO_EVENTO";
    public static final String  PROPERTY_NAME_ID          = "id";

    public static final String  COLUMN_NAME_NOMBRE        = "T_PIB_TIPO_EVENTO";
    public static final String  PROPERTY_NAME_NOMBRE      = "nombre";

    public static final String  COLUMN_NAME_DESCRIPCION   = "T_PIB_TIPO_EVENTO_ALT";
    public static final String  PROPERTY_NAME_DESCRIPCION = "descripcion";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Nombre por el que el usuario conoce al tipo de evento
     */
    private String              nombre;

    /**
     * Nombre o descripción alternativa del tipo de evento
     */
    private String              descripcion;

    /**
     * constructors<br>
     * ================
     */

    protected TipoEventoPIB() {
        super();
    }

    public TipoEventoPIB(String nombre) {
        super();
        this.nombre = nombre;
    }

    /**
     * getter & setters<br>
     * ================
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    @Column(name = COLUMN_NAME_NOMBRE, length = 80)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = COLUMN_NAME_DESCRIPCION)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof TipoEventoPIB)) {
            return false;
        }

        final TipoEventoPIB other = (TipoEventoPIB) obj;

        // equals de un pojo no debe comparar los ids
        /*
         * if (!getId().equals(other.getId())) { return false; }
         */
        if (!getNombre().equals(other.getNombre())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getNombre() == null) ? 0 : getNombre().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(PROPERTY_NAME_ID, getId())
                .append(PROPERTY_NAME_NOMBRE, getNombre()).append(
                        PROPERTY_NAME_DESCRIPCION, getDescripcion()).toString();
    }
}