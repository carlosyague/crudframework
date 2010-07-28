package org.librae.lectores.model;

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
 * Bean que almacena un LectorTelefono
 * 
 * @author jcdiaz
 */
@Entity(name = LectorTelefono.ENTITY_NAME)
@Table(name = LectorTelefono.TABLE_NAME)
public class LectorTelefono extends SpringRemotableLazyEntity<LectorTelefono> {

    /**
     * BaseObject is Serializable, por lo tanto desiderata necesita un Serial
     * Version UID
     */
    private static final long  serialVersionUID      = 20081103L;

    public static final String ENTITY_NAME           = "org.librae.lectores.model.LectorTelefono";

    /**
     * Constantes para referencia de los atributos de LectorTelefono
     */
    public static final String PROPTY_NAME_ID        = "id";
    public static final String PROPTY_NAME_ORDEN     = "orden";
    public static final String PROPTY_NAME_TELEFONO  = "telefono";
    public static final String PROPTY_NAME_LECTOR    = "lector";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String TABLE_NAME            = "LEC_LECTOR_TELEFONOS";
    public static final String ID_GENERATOR_NAME     = "generator_lec lector_telefonos";
    public static final String ID_SEQUENCE_NAME      = "SEQ_LEC_LECTOR_TELEFONOS";

    public static final String COLUMN_NAME_ID        = "C_LECTOR_TELEFONO";
    public static final String COLUMN_NAME_TELEFONO  = "T_TELEFONO";
    public static final String COLUMN_NAME_ORDEN     = "N_ORDEN";

    public static final String COLUMN_NAME_LECTOR_FK = "LEC_X_LECTOR";

    /**
     * Clave numérica secuencial sin significado funcional
     */
    private Long               id;

    /**
     * Clave foránea a la tabla de lector para identificar el lector que tiene n
     * teléfonos
     */
    private Lector             lector;

    /**
     * Teléfono del lector. letfn1,letfn2 y letmov en Absys
     */
    private String             telefono;

    /**
     * Número que indica para un mismo lector el orden de preferencia en los n
     * teléfonos a la hora de tener en cuenta para notificaciones, envío de
     * información. Por orden descendente, el 0 se considera el primero
     */
    private Integer            orden;

    /**
     * Constructor sin parámetros
     */
    protected LectorTelefono() {
        super();
    }

    /**
     * Constructor con parámetros
     */
    public LectorTelefono(final String telefono, final Integer orden) {
        super();
        this.telefono = telefono;
        this.orden = orden;
    }

    /**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = LectorTelefono.ID_GENERATOR_NAME)
    @SequenceGenerator(name = LectorTelefono.ID_GENERATOR_NAME, sequenceName = LectorTelefono.ID_SEQUENCE_NAME)
    @Column(name = LectorTelefono.COLUMN_NAME_ID, nullable = false)
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
     * @return lector
     */
    @ManyToOne
    @JoinColumn(name = LectorTelefono.COLUMN_NAME_LECTOR_FK, nullable = false)
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector
     */
    public void setLector(final Lector lector) {
        this.lector = lector;
    }

    /**
     * @return telefono
     */
    @Column(name = LectorTelefono.COLUMN_NAME_TELEFONO, nullable = false, length = 15)
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono
     */
    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return orden
     */
    @Column(name = LectorTelefono.COLUMN_NAME_ORDEN, nullable = false)
    public Integer getOrden() {
        return orden;
    }

    /**
     * @param orden
     */
    public void setOrden(final Integer orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LectorTelefono)) {
            return false;
        }

        final LectorTelefono other = (LectorTelefono) obj;

        if (id != other.getId()) {
            return false;
        }

        if (!telefono.equals(other.getTelefono())) {
            return false;
        }

        if (orden != other.getOrden()) {
            return false;
        }

        if (lector == null && other.getLector() != null) {
            return false;
        }
        if (lector != null && !lector.equals(other.getLector())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((telefono == null) ? 0 : telefono.hashCode());
        result = prime * result + ((orden == null) ? 0 : orden.hashCode());
        result = prime * result + ((lector == null) ? 0 : lector.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(LectorTelefono.COLUMN_NAME_ID,
                id).append(LectorTelefono.COLUMN_NAME_TELEFONO, telefono)
                .append(LectorTelefono.COLUMN_NAME_ORDEN, orden).toString();
    }

    @Override
    public LectorTelefono newInstance() {
        return new LectorTelefono();
    }

}