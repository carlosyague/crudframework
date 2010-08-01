package org.librae.lectores.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Direccion;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean que almacena un LectorDireccion
 * 
 * @author jcdiaz
 */
@Entity(name = LectorDireccion.ENTITY_NAME)
@Table(name = LectorDireccion.TABLE_NAME)
public class LectorDireccion extends SpringRemotableLazyEntity<LectorDireccion> {

    /**
     * BaseObject is Serializable, por lo tanto LectorDireccion necesita un
     * Serial Version UID
     */
    private static final long  serialVersionUID          = -1703573565004303668L;

    public static final String ENTITY_NAME               = "org.librae.lectores.model.LectorDireccion";

    /**
     * Constantes para referencia de los atributos de LectorDireccion
     */
    public static final String PROPTY_NAME_ID            = "id";
    public static final String PROPTY_NAME_ORDEN         = "orden";
    public static final String PROPTY_NAME_CODIGO_POSTAl = "codigoPostal";
    public static final String PROPTY_NAME_LECTOR        = "lector";
    public static final String PROPTY_NAME_DIRECCION     = "direccion";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String TABLE_NAME                = "LEC_LECTOR_DIRECCIONES";
    public static final String ID_GENERATOR_NAME         = "generator_lec_lector_direcciones";
    public static final String ID_SEQUENCE_NAME          = "SEQ_LEC_LECTOR_DIRECCIONES";

    public static final String COLUMN_NAME_ID            = "X_LECTOR_DIRECCION";
    public static final String COLUMN_NAME_CODIGOPOSTAL  = "N_CODIGO_POSTAL";
    public static final String COLUMN_NAME_ORDEN         = "N_ORDEN";

    public static final String COLUMN_NAME_LECTOR_FK     = "LEC_X_LECTOR";
    public static final String COLUMN_NAME_DIRECCION_FK  = "DIR_X_DIRECCION";

    /**
     * Clave numérica secuencial sin significado funcional
     */
    private Long               id;

    /**
     * Número que indica para un mismo lector el orden de preferencia en las n
     * direcciones a la hora de tener en cuenta para notificaciones, envío de
     * información. Por orden descendente, el 0 se considera el primero
     */
    private Integer            orden;

    /**
     * Desnormalización del código postal, que se añade como columna de la tabla
     * de direcciones del lector para agilizar las búsquedas
     */
    private String             codigoPostal;

    /**
     * Clave foránea a la tabla de lectores para identificar al lector que tiene
     * n direcciones
     */
    private Lector             lector;

    /**
     * Clave foránea a la tabla direcciones para identificar la dirección
     * perteneciente al lector
     */
    private Direccion          direccion;

    /**
     * Constructor sin parámetros
     */
    protected LectorDireccion() {
        super();
    }

    /**
     * Constructor con parámetros
     * 
     * @param orden
     */
    public LectorDireccion(final Integer orden, final String codigoPostal) {
        super();
        this.orden = orden;
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = LectorDireccion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = LectorDireccion.ID_GENERATOR_NAME, sequenceName = LectorDireccion.ID_SEQUENCE_NAME)
    @Column(name = LectorDireccion.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return orden
     */
    @Column(name = LectorDireccion.COLUMN_NAME_ORDEN, nullable = false)
    public Integer getOrden() {
        return orden;
    }

    /**
     * @param orden
     */
    public void setOrden(final Integer orden) {
        this.orden = orden;
    }

    /**
     * @return codigoPostal
     */
    @Column(name = LectorDireccion.COLUMN_NAME_CODIGOPOSTAL, length = 10)
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal
     */
    public void setCodigoPostal(final String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the lector
     */
    @ManyToOne(targetEntity = org.librae.lectores.model.Lector.class)
    @JoinColumn(name = LectorDireccion.COLUMN_NAME_LECTOR_FK, nullable = false)
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
     * @return the direccion
     */
    @OneToOne(targetEntity = org.librae.adminconfig.model.Direccion.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = LectorDireccion.COLUMN_NAME_DIRECCION_FK, nullable = false)
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param direccion
     *            the direccion to set
     */
    public void setDireccion(final Direccion direccion) {
        this.direccion = direccion;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result
                + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((orden == null) ? 0 : orden.hashCode());
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
        if (!(obj instanceof LectorDireccion)) {
            return false;
        }
        final LectorDireccion other = (LectorDireccion) obj;
        if (codigoPostal == null) {
            if (other.codigoPostal != null) {
                return false;
            }
        } else if (!codigoPostal.equals(other.codigoPostal)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (orden == null) {
            if (other.orden != null) {
                return false;
            }
        } else if (!orden.equals(other.orden)) {
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

        .append(LectorDireccion.COLUMN_NAME_ID, id)

        .append(LectorDireccion.COLUMN_NAME_CODIGOPOSTAL,
                (codigoPostal == null) ? 0 : codigoPostal.toString())

        .append(LectorDireccion.COLUMN_NAME_ORDEN, (orden == null) ? 0 : orden

        .toString()).toString();
    }

    @Override
    public LectorDireccion newInstance() {
        return new LectorDireccion();
    }

}
