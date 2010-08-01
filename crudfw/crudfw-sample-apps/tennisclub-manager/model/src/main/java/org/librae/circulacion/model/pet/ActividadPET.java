package org.librae.circulacion.model.pet;

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
 * Descripción de las actividades que componen el workflow.<br>
 * <br>
 * Cada una de las filas de esta tabla corresponde a lo que por ejemplo en
 * OSWorkflow se llama un step.<br>
 * Las actividades del workflow no tienen mantenimiento vía la interfaz de
 * usuario. Se asume que en tiempo de diseño se establecerá como se integra el
 * motor de workflow en la aplicación. Por ejemplo dos alternativas pueden ser:<br>
 * Existe esta tabla en la BD, y se le añade un campo StepId que especifica el
 * identificador único del step en OsWorkflow.<br>
 * NO existe esta tabla en la BD, sino que en el fichero XML del workflow se
 * especifica con elementos meta las propiedades de cada step<br>
 *
 * @author asantamaría
 */
@Entity(name = ActividadPET.ENTITY_NAME)
@Table(name = ActividadPET.TABLE_NAME)
public class ActividadPET extends BaseObject {

    /**
     * BaseObject is Serializable, so User needs a Serial Version UID
     */
    private static final long   serialVersionUID        = -1286380873424576920L;

    public static final String  ENTITY_NAME             = "org.librae.circulacion.model.ActividadPET";
    public static final String  TABLE_NAME              = "CIR_PET_ACTIVIDADES";
    public static final String  ID_GENERATOR_NAME       = "generator_cir_pet_actividades";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CIR_PET_ACTIVIDADES";
    public static final String  COLUMN_NAME_ID          = "X_PET_ACTIVIDAD";
    public static final String  COLUMN_NAME_NOMBRE      = "T_PET_ACTIVIDAD";
    public static final String  COLUMN_NAME_DESCRIPCION = "T_PET_ACTIVIDAD_ALT";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Nombre de actividad, según documento CIRCULACION - PET - Workflow.png
     */
    private String              nombre;

    /**
     * Descripción explicativa de actividad del workflow
     */
    private String              descripcion;

    protected ActividadPET() {
        super();
    }

    public ActividadPET(String nombre) {
        super();
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = ActividadPET.COLUMN_NAME_ID)
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
     * @return nombre
     */
    @Column(name = ActividadPET.COLUMN_NAME_NOMBRE,length=80)
    public String getNombre() {
        return this.nombre;
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
    @Column(name = ActividadPET.COLUMN_NAME_DESCRIPCION)
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((nombre == null) ? 0 : nombre.hashCode());
        /*
         * result = prime result + ((lastName == null) ? 0 :
         * lastName.hashCode());
         */
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append(this.COLUMN_NAME_NOMBRE,
                this.nombre).append(this.COLUMN_NAME_ID, this.id).toString();
    }

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof ActividadPET)) {
            return false;
        }

        final ActividadPET other = (ActividadPET) obj;

        if (this.getNombre() == null && other.getNombre() != null) {
            return false;
        }
        if (this.getNombre() != null
                && !this.getNombre().equals(other.getNombre())) {
            return false;
        }

        return true;
    }
}