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
import org.hibernate.annotations.ForeignKey;
import org.librae.common.model.BaseObject;
import org.librae.lectores.model.Lector;

/**
 * @author amDelcampo
 */
@Entity(name = TipoNotificacionLector.NAME_ENTITY)
@Table(name = TipoNotificacionLector.TABLE_NAME)
public class TipoNotificacionLector extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID                  = -3132144300252232252L;
    public static final String  NAME_ENTITY                       = "org.librae.mensajeria.model.TipoNotificacionLector";
    public static final String  TABLE_NAME                        = "MEN_DSI_LECTOR";

    private static final String ID_GENERATOR_NAME                 = "generator_men_dsi_lector";
    private static final String ID_SEQUENCE_NAME                  = "SEQ_MEN_DSI_LECTOR";

    public static final String  COLUMN_NAME_ID                    = "X_DSI_LECTOR";
    public static final String  COLUMN_NAME_TIPONOTIFICACION_FK   = "DSI_X_DSI_CATALOGO";
    public static final String  COLUMN_NAME_LECTOR_FK             = "LEC_X_LECTOR";
    public static final String  COLUMN_NAME_ENVIAR                = "L_ENVIAR";
    public static final String  COLUMN_NAME_CANAL_FK              = "CAN_X_CANAL_INFORMACION";

    public static final String  PROPERTY_NAME_ID                  = "id";
    public static final String  PROPERTY_NAME_TIPONOTIFICACION_FK = "tipo";
    public static final String  PROPERTY_NAME_LECTOR_FK           = "lector";
    public static final String  PROPERTY_NAME_ENVIAR              = "enviar";
    public static final String  PROPERTY_NAME_CANAL_FK            = "canal";

    /**
     * Clave primaria.
     */
    private Long                id;

    /**
     * Referencia al Tipo de Notificación.
     */
    private TipoNotificacion    tipo;

    /**
     * Referencia al Lector.
     */
    private Lector              lector;

    /**
     * Boolean que indica si el lector desea que se le envíe la notificacion.
     */
    private Boolean             enviar;

    /**
     * Referencia al Lector.
     */
    private CanalInformacion    canal;

    /**
     * Constructor sin parámetros.
     */
    public TipoNotificacionLector() {
        super();
    }

    /**
     * Constructor con parámetros.
     */
    public TipoNotificacionLector(Lector lectorFinal, TipoNotificacion tipoNotificacionFinal, CanalInformacion canalInformacionFinal){
        super();
        this.tipo = tipoNotificacionFinal;
        this.lector = lectorFinal;
        this.canal = canalInformacionFinal;
    }
    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TipoNotificacionLector.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = TipoNotificacionLector.ID_SEQUENCE_NAME)
    @Column(name = TipoNotificacionLector.COLUMN_NAME_ID, nullable = false)
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
     * @return the tipoNotificacion.
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.mensajeria.model.TipoNotificacion.class)
    @JoinColumn(name = TipoNotificacionLector.COLUMN_NAME_TIPONOTIFICACION_FK)
    @ForeignKey(name = "FK_DSI_X_DSI_CATALOGO_MEN_DSI")
    public TipoNotificacion getTipo() {
        return tipo;
    }

    /**
     * @param tipoNotificacion
     *            the tipoNotificacion to set.
     */
    public void setTipo(TipoNotificacion tipoNotificacion) {
        this.tipo = tipoNotificacion;
    }

    /**
     * @return the lector.
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.Lector.class, fetch = FetchType.LAZY)
    @JoinColumn(name = TipoNotificacionLector.COLUMN_NAME_LECTOR_FK, nullable = false)
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector
     *            the lector to set.
     */
    public void setLector(Lector lector) {
        this.lector = lector;
    }

    /**
     * @return the enviar.
     */
    @Column(name = TipoNotificacionLector.COLUMN_NAME_ENVIAR)
    public Boolean getEnviar() {
        return enviar;
    }

    /**
     * @param enviarFinal
     *            the enviar to set.
     */
    public void setEnviar(Boolean enviarFinal) {
        this.enviar = enviarFinal;
    }

    /**
     * @return the canal
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.mensajeria.model.CanalInformacion.class, fetch = FetchType.LAZY)
    @JoinColumn(name = TipoNotificacionLector.COLUMN_NAME_CANAL_FK, nullable = false)
    public CanalInformacion getCanal() {
        return canal;
    }

    /**
     * @param canal
     *            the canal to set
     */
    public void setCanal(CanalInformacion canal) {
        this.canal = canal;
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
        if (!(obj instanceof TipoNotificacionLector)) {
            return false;
        }
        final TipoNotificacionLector other = (TipoNotificacionLector) obj;
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
                TipoNotificacionLector.COLUMN_NAME_ID, id).toString();
    }

}
