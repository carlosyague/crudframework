/**
 *
 */
package org.librae.mensajeria.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.GrupoFuncional;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar la realción entre Tipo de Notificación y Canal de
 * Comunicación.
 *
 * @author amDelcampo
 */
@Entity(name = TipoNotificacionCanalInformacion.NAME_ENTITY)
@Table(name = TipoNotificacionCanalInformacion.TABLE_NAME)
public class TipoNotificacionCanalInformacion extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID                  = 8378571623374575228L;

    public static final String  NAME_ENTITY                       = "org.librae.mensajeria.model.TipoNotificacionCanalInformacion";
    public static final String  TABLE_NAME                        = "MEN_DSI_CANALES_INFORMACION";

    private static final String ID_GENERATOR_NAME                 = "generator_men_canales_inf";
    private static final String ID_SEQUENCE_NAME                  = "SEQ_MEN_DSI_CANALES_INF";

    public static final String  COLUMN_NAME_ID                    = "X_DSI_CANALES_INFORMACION";
    public static final String  COLUMN_NAME_CANAL_FK              = "CAN_X_CANAL_INFORMACION";
    public static final String  COLUMN_NAME_TIPONOTIFICACION_FK   = "DSI_X_DSI_CATALOGO";
    public static final String  COLUMN_NAME_PRIORIDAD             = "C_PRIORIDAD";

    public static final String  PROPERTY_NAME_ID                  = "id";
    public static final String  PROPERTY_NAME_CANAL_FK            = "canalInformacion";
    public static final String  PROPERTY_NAME_TIPONOTIFICACION_FK = "tipoNotificacion";
    public static final String  PROPERTY_NAME_PRIORIDAD           = "prioridad";

    /**
     * Clave primaria.
     */
    private Long                id;

    /**
     * Referencia al Canal de Información.
     */
    private CanalInformacion    canalInformacion;

    /**
     * Referencia al Tipo de Notificación.
     */
    private TipoNotificacion    tipoNotificacion;

    /**
     * Indica la prioridad del canal de información.
     */
    private Boolean             prioridad;

    /**
     * Constructor sin parámetros
     */
    public TipoNotificacionCanalInformacion() {
        super();
    }

    /**
     * Constructor con parámetros
     */
    public TipoNotificacionCanalInformacion(CanalInformacion canalInformacion,
            TipoNotificacion tipoNotificacion, Boolean prioridad) {
        super();
        this.canalInformacion = canalInformacion;
        this.tipoNotificacion = tipoNotificacion;
        this.prioridad = prioridad;

    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TipoNotificacionCanalInformacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = TipoNotificacionCanalInformacion.ID_SEQUENCE_NAME)
    @Column(name = TipoNotificacionCanalInformacion.COLUMN_NAME_ID, nullable = false)
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
     * @return the canalInformacion
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.mensajeria.model.CanalInformacion.class)
    @JoinColumn(name = TipoNotificacionCanalInformacion.COLUMN_NAME_CANAL_FK)
    public CanalInformacion getCanalInformacion() {
        return canalInformacion;
    }

    /**
     * @param canalInformacion
     *            the canalInformacion to set
     */
    public void setCanalInformacion(CanalInformacion canalInformacion) {
        this.canalInformacion = canalInformacion;
    }

    /**
     * @return the tipoNotificacion
     */
    @ManyToOne
    @JoinColumn(name = TipoNotificacionCanalInformacion.COLUMN_NAME_TIPONOTIFICACION_FK)
    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    /**
     * @param tipoNotificacion
     *            the tipoNotificacion to set
     */
    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    /**
     * @return the prioridad
     */
    @Column(name = TipoNotificacionCanalInformacion.COLUMN_NAME_PRIORIDAD)
    public Boolean getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad
     *            the prioridad to set
     */
    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
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
        if (!(obj instanceof TipoNotificacionCanalInformacion)) {
            return false;
        }
        final TipoNotificacionCanalInformacion other = (TipoNotificacionCanalInformacion) obj;
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
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(
                TipoNotificacionCanalInformacion.COLUMN_NAME_ID, id).toString();
    }

}
