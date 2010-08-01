package org.librae.circulacion.model.pib;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.exception.MensajesError;
import org.librae.common.model.BaseObject;

/**
 * Acciones de préstamo realizadas en la actividad de gestion del préstamo del
 * workflow.<br>
 * Las acciones de préstamo son: Suministrar materiales no retornables Prestar
 * en sala Devolver préstamo en sala Prestar a domicilio Renovar préstamo a
 * domicilio Devolver préstamo a domicilio
 * 
 * @author cyague
 */
@Entity(name = AccionPrestamoPIB.ENTITY_NAME)
@Table(name = AccionPrestamoPIB.TABLE_NAME)
public class AccionPrestamoPIB extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID                   = 5830420294372612367L;

    public static final String  ENTITY_NAME                        = "org.librae.circulacion.model.AccionPrestamoPIB";
    public static final String  TABLE_NAME                         = "CIR_PIB_ACCIONES_PRESTAMOS";

    public static final String  ID_GENERATOR_NAME                  = "gen_cir_pib_accion_prestamo";
    private static final String ID_SEQUENCE_NAME                   = "SEQ_CIR_PIB_ACC_PREST";
    public static final String  COLUMN_NAME_ID                     = "X_PIB_ACCION_PRESTAMO";
    public static final String  PROPERTY_NAME_ID                   = "id";

    public static final String  COLUMN_NAME_FECHA_HORA_EJECUCION   = "F_EJECUCION";
    public static final String  PROPERTY_NAME_FECHA_HORA_EJECUCION = "fechaHoraEjecucion";

    public static final String  COLUMN_NAME_PETICION               = "PET_X_PIB_PETICION";
    public static final String  PROPERTY_NAME_PETICION             = "peticion";

    public static final String  COLUMN_NAME_NOTAS                  = "T_NOTAS";
    public static final String  PROPERTY_NAME_NOTAS                = "notas";

    public static final String  COLUMN_NAME_NOMBRE                 = "T_PIB_ACCION_PRESTAMO";
    public static final String  PROPERTY_NAME_NOMBRE               = "nombre";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Nombre de la acción. Véase la doc a nivel de la tabla.
     */
    private String              nombre                             = null;

    /**
     * Fecha-hora de ejecución de la acción
     */
    private Date                fechaHoraEjecucion                 = null;

    /**
     * >> CIR_PIB_PETICIONES Indica a que petición se refiere esta acción de
     * préstamo
     */
    private PeticionPIB         peticion;

    /**
     * Texto libre que el usuario introduce en el momento de la ejecución de la
     * acción, a modo de notas para él.
     */
    private String              notas                              = null;

    /**
     * constructors<br>
     * ================
     */

    protected AccionPrestamoPIB() {
        super();
    }

    public AccionPrestamoPIB(String nombre) {
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

    @Column(name = COLUMN_NAME_NOMBRE, length = 40)
    public String getNombre() {
        return nombre;
    }

    @Transient
    public String getNombreLocale() {
        String result = "";
        final String propertyKey = nombre.replace("#", "");
        if (propertyKey.equals(nombre)) {
            result = nombre;
        } else {
            result = MensajesError.get(MensajesError.PROPERTI_CIRCULACION,
                    propertyKey);
        }
        return result;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = COLUMN_NAME_FECHA_HORA_EJECUCION)
    public Date getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(Date fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
    }

    @ManyToOne(targetEntity = PeticionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = COLUMN_NAME_PETICION)
    public PeticionPIB getPeticion() {
        return peticion;
    }

    public void setPeticion(PeticionPIB peticion) {
        this.peticion = peticion;
    }

    @Column(name = COLUMN_NAME_NOTAS)
    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof AccionPrestamoPIB)) {
            return false;
        }

        final AccionPrestamoPIB other = (AccionPrestamoPIB) obj;

        // equals de un pojo no debe comparar los ids
        /*
         * if (!getId().equals(other.getId())) { return false; }
         */
        if (!getNombre().equals(other.getNombre())) {
            return false;
        }

        if (!getPeticion().equals(other.getPeticion())) {
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
        result = prime * result
                + ((getPeticion() == null) ? 0 : getPeticion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(PROPERTY_NAME_ID, getId())
                .append(PROPERTY_NAME_NOMBRE, getNombre()).append(
                        PROPERTY_NAME_PETICION, getPeticion()).append(
                        PROPERTY_NAME_FECHA_HORA_EJECUCION,
                        getFechaHoraEjecucion()).toString();
    }

}