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
 * Bean que almacena un LectorCorreo
 * 
 * @author jcdiaz
 */
@Entity(name = LectorCorreo.ENTITY_NAME)
@Table(name = LectorCorreo.TABLE_NAME)
public class LectorCorreo extends SpringRemotableLazyEntity<LectorCorreo> {

    /**
     * BaseObject is Serializable, por lo tanto desiderata necesita un Serial
     * Version UID
     */
    private static final long  serialVersionUID      = 20081031L;

    public static final String ENTITY_NAME           = "org.librae.lectores.model.LectorCorreo";

    /**
     * Constantes para referencia de los atributos de LectorCorreo
     */
    public static final String PROPTY_NAME_ID        = "id";
    public static final String PROPTY_NAME_ORDEN     = "orden";
    public static final String PROPTY_NAME_CORREO    = "correo";
    public static final String PROPTY_NAME_LECTOR    = "lector";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String TABLE_NAME            = "LEC_LECTOR_CORREOS";
    public static final String ID_GENERATOR_NAME     = "generator_lec_lector_correos";
    public static final String ID_SEQUENCE_NAME      = "SEQ_LEC_LECTOR_CORREOS";

    public static final String COLUMN_NAME_ID        = "X_LECTOR_CORREO";
    public static final String COLUMN_NAME_ORDEN     = "N_ORDEN";
    public static final String COLUMN_NAME_CORREO    = "T_CORREO";

    public static final String COLUMN_NAME_LECTOR_FK = "LEC_X_LECTOR";

    /**
     * Clave numérica secuencial sin significado funcional
     */
    private Long               id;

    /**
     * Clave foránea a la tabla de lector para identificar el lector que tiene n
     * correos
     */
    private Lector             lector;

    /**
     * Número que indica para un mismo lector el orden de preferencia en los n
     * correos a la hora de tener en cuenta para notificaciones, envío de
     * información. Por orden descendente, el 0 se considera el primero
     */
    private Integer            orden;

    /**
     * Correo del lector (lemail en Abs*Net)
     */
    private String             correo;

    /**
     * Constructor sin parámetros
     */
    protected LectorCorreo() {
        super();
    }

    /**
     * Constructor con parámetros
     */
    public LectorCorreo(final String correo, final Integer orden) {
        super();
        this.correo = correo;
        this.orden = orden;
    }

    /**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = LectorCorreo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = LectorCorreo.ID_GENERATOR_NAME, sequenceName = LectorCorreo.ID_SEQUENCE_NAME)
    @Column(name = LectorCorreo.COLUMN_NAME_ID, nullable = false)
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
    @JoinColumn(name = LectorCorreo.COLUMN_NAME_LECTOR_FK, nullable = false)
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
     * @return orden
     */
    @Column(name = LectorCorreo.COLUMN_NAME_ORDEN, nullable = false)
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
     * @return correo
     */
    @Column(name = LectorCorreo.COLUMN_NAME_CORREO, nullable = false, length = 100)
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo
     */
    public void setCorreo(final String correo) {
        this.correo = correo;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LectorCorreo)) {
            return false;
        }

        final LectorCorreo other = (LectorCorreo) obj;

        if (id != other.getId()) {
            return false;
        }

        if (orden != other.getOrden()) {
            return false;
        }

        if (!correo.equals(other.getOrden())) {
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
        result = prime * result + ((orden == null) ? 0 : orden.hashCode());
        result = prime * result + ((correo == null) ? 0 : correo.hashCode());
        result = prime * result + ((lector == null) ? 0 : lector.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(LectorCorreo.COLUMN_NAME_ID, id).append(
                        LectorCorreo.COLUMN_NAME_ORDEN, orden).append(
                        LectorCorreo.COLUMN_NAME_CORREO, correo).toString();
    }

    @Override
    public LectorCorreo newInstance() {
        return new LectorCorreo();
    }

}