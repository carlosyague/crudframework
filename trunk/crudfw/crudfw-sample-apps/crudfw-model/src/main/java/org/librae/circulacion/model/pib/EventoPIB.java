package org.librae.circulacion.model.pib;

import java.util.Date;

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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.exception.MensajesError;
import org.librae.common.model.BaseObject;

/**
 * Ocurrencias de eventos que el usuario puede registrar en un PIB
 * 
 * @author cyague
 */
@Entity(name = EventoPIB.ENTITY_NAME)
@Table(name = EventoPIB.TABLE_NAME)
public class EventoPIB extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID                = -4037818719052203534L;

    public static final String  ENTITY_NAME                     = "org.librae.circulacion.model.EventoPIB";
    public static final String  TABLE_NAME                      = "CIR_PIB_EVENTOS";

    public static final String  ID_GENERATOR_NAME               = "generator_cir_pib_eventos";
    private static final String ID_SEQUENCE_NAME                = "SEQ_CIR_PIB_EVENTO";
    public static final String  COLUMN_NAME_ID                  = "X_PIB_EVENTO";
    public static final String  PROPERTY_NAME_ID                = "id";

    public static final String  COLUMN_NAME_FECHA_HORA_EVENTO   = "F_EVENTO";
    public static final String  PROPERTY_NAME_FECHA_HORA_EVENTO = "fechaHoraEvento";

    public static final String  COLUMN_NAME_PETICION            = "PET_X_PIB_PETICION";
    public static final String  PROPERTY_NAME_PETICION          = "peticion";

    public static final String  COLUMN_NAME_NOTAS               = "T_NOTAS";
    public static final String  PROPERTY_NAME_NOTAS             = "notas";

    public static final String  COLUMN_NAME_TIPO_EVENTO         = "TIPO_EV_X_PIB_TIPO_EVENTO";
    public static final String  PROPERTY_NAME_TIPO_EVENTO       = "tipoEvento";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Fecha-hora del evento
     */
    private Date                fechaHoraEvento;

    /**
     * >> CIR_PIB_PETICIONES
     */
    private PeticionPIB         peticion;

    /**
     * Notas o datos adicionales sobre el evento
     */
    private String              notas                           = null;

    /**
     * >> CIR_PIB_TIPOS_EVENTOS
     */
    private TipoEventoPIB       tipoEvento;

    /**
     * constructors<br>
     * ================
     */

    protected EventoPIB() {
        super();
    }

    public EventoPIB(TipoEventoPIB tipoEvento) {
        super();
        this.tipoEvento = tipoEvento;
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

    @Column(name = COLUMN_NAME_FECHA_HORA_EVENTO)
    public Date getFechaHoraEvento() {
        return fechaHoraEvento;
    }

    public void setFechaHoraEvento(Date fechaHoraEvento) {
        this.fechaHoraEvento = fechaHoraEvento;
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

    @Column(name = COLUMN_NAME_NOTAS, length = 4000)
    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @OneToOne(targetEntity = TipoEventoPIB.class)
    @JoinColumn(name = COLUMN_NAME_TIPO_EVENTO)
    public TipoEventoPIB getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEventoPIB tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    @Transient
    public String getLocaleTipoEvento() {
        String result = null;

        if (tipoEvento != null) {
            result = tipoEvento.getNombre();

            if (result != null && result.contains("#")) {
                final String propertyKey = result.replace("#", "");

                result = MensajesError.get(MensajesError.PROPERTI_COMBOS,
                        propertyKey);
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof EventoPIB)) {
            return false;
        }

        final EventoPIB other = (EventoPIB) obj;

        // equals de un pojo no debe comparar los ids
        /*
         * if (!getId().equals(other.getId())) { return false; }
         */
        if (!getFechaHoraEvento().equals(other.getFechaHoraEvento())) {
            return false;
        }

        if (!getPeticion().equals(other.getPeticion())) {
            return false;
        }

        if (!getTipoEvento().equals(other.getTipoEvento())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime
                * result
                + ((getFechaHoraEvento() == null) ? 0 : getFechaHoraEvento()
                        .hashCode());
        result = prime * result
                + ((getTipoEvento() == null) ? 0 : getTipoEvento().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(PROPERTY_NAME_ID, getId())
                .append(PROPERTY_NAME_FECHA_HORA_EVENTO, getFechaHoraEvento())
                .append(PROPERTY_NAME_PETICION, getPeticion()).append(
                        PROPERTY_NAME_TIPO_EVENTO, getTipoEvento()).toString();
    }

}