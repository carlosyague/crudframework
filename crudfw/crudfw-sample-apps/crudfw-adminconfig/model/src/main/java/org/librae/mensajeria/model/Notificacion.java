/**
 *
 */
package org.librae.mensajeria.model;

import java.util.Date;

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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.parampoliticas.AbstractParamPolNotificacion;
import org.librae.lectores.model.Lector;

/**
 * @author amDelcampo
 */
@Entity(name = Notificacion.NAME_ENTITY)
@Table(name = Notificacion.TABLE_NAME)
public class Notificacion extends AbstractParamPolNotificacion {

    /**
     *
     */
    private static final long   serialVersionUID                  = -5724759397286534475L;

    public static final String  NAME_ENTITY                       = "org.librae.mensajeria.model.Notificacion";
    public static final String  TABLE_NAME                        = "MEN_NOTIFICACIONES";

    private static final String ID_GENERATOR_NAME                 = "generator_men_notificaciones";
    private static final String ID_SEQUENCE_NAME                  = "SEQ_MEN_NOTIFICACIONES";

    public static final String  COLUMN_NAME_ID                    = "X_NOTIFICACIONES";
    public static final String  COLUMN_NAME_TIPONOTIFICACION_FK   = "DSI_X_DSI_CATALOGO";
    public static final String  COLUMN_NAME_CANAL_FK              = "CAN_X_CANAL_INFORMACION";
    public static final String  COLUMN_NAME_LECTOR_FK             = "LEC_X_LECTOR";
    public static final String  COLUMN_NAME_MOTIVO_FK             = "MOT_X_MOTIVO_NO_ENTREGA";
    public static final String  COLUMN_NAME_FECHAALTA             = "F_FECHA_ALTA";
    public static final String  COLUMN_NAME_FECHANOTIFICACION     = "F_FECHA_NOTIFICACION";
    public static final String  COLUMN_NAME_ACCION                = "L_ACCION_BIBLIOTECARIO";
    public static final String  COLUMN_NAME_DESEANOTIFICACION     = "L_DESEA_NOTIFICACION";
    public static final String  COLUMN_NAME_MENSAJE               = "T_MENSAJE";
    public static final String  COLUMN_NAME_NOTA                  = "T_NOTA";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK         = "BIB_X_BIBLIOTECA_FK";

    public static final String  PROPERTY_NAME_ID                  = "id";
    public static final String  PROPERTY_NAME_TIPONOTIFICACION_FK = "tipoNotificacion";
    public static final String  PROPERTY_NAME_CANAL_FK            = "canalInformacion";
    public static final String  PROPERTY_NAME_LECTOR_FK           = "lector";
    public static final String  PROPERTY_NAME_MOTIVO_FK           = "motivoNoEntrega";
    public static final String  PROPERTY_NAME_FECHAALTA           = "fechaAlta";
    public static final String  PROPERTY_NAME_FECHANOTIFICACION   = "fechaNotificacion";
    public static final String  PROPERTY_NAME_ACCION              = "accion";
    public static final String  PROPERTY_NAME_DESEANOTIFICACION   = "deseaNotificacion";
    public static final String  PROPERTY_NAME_MENSAJE             = "mensaje";
    public static final String  PROPERTY_NAME_NOTA                = "nota";
    public static final String  PROPERTY_NAME_BIBLIOTECA_FK       = "biblioteca";

    /**
     * Clave primaria.
     */
    private Long                id;

    /**
     * Referencia al Tipo de Notificación.
     */
    private TipoNotificacion    tipoNotificacion;

    /**
     * Referencia al Canal de Información.
     */
    private CanalInformacion    canalInformacion;

    /**
     * Referencia al Lector.
     */
    private Lector              lector;

    /**
     * Referencia a Motivo No Entrega.
     */
    private MotivoNoEntrega     motivoNoEntrega;

    /**
     * Fecha en la que se dio de alta la notificación.
     */
    private Date                fechaAlta;

    /**
     * Fecha en la que se realiza la notificación.
     */
    private Date                fechaNotificacion;

    /**
     * Indica si la notificación requiere una acción por parte del
     * bibliotecario.
     */
    private Boolean             accion;

    /**
     * Indica si el usuario desea que se le notifique el DSI.
     */
    private Boolean             deseaNotificacion;

    /**
     * Mensaje que se le mostrará al usuario. Este mensaje lo genera el
     * subsistema que da de alta la notificación.
     */
    private String              mensaje;

    /**
     * Nota que añade el bibliotecario de la sucursal.
     */
    private String              nota;

    private Biblioteca          biblioteca;

    /**
     * Constructor sin parámetros
     */
    protected Notificacion() {
        super();
    }

    public Notificacion(TipoNotificacion tipo) {
        super();
        this.setTipoNotificacion(tipo);
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Notificacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Notificacion.ID_SEQUENCE_NAME)
    @Column(name = Notificacion.COLUMN_NAME_ID, nullable = false)
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
     * @return the tipoNotificacion
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = TipoNotificacion.class, fetch = FetchType.LAZY)
    @JoinColumn(name = TipoNotificacion.COLUMN_NAME_ID, nullable = false)
    @ForeignKey(name = Notificacion.COLUMN_NAME_TIPONOTIFICACION_FK)
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
     * @return the canalInformacion
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.mensajeria.model.CanalInformacion.class)
    @JoinColumn(name = Notificacion.COLUMN_NAME_CANAL_FK)
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
     * @return the lector
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.Lector.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Lector.COLUMN_NAME_ID, nullable = false)
    @ForeignKey(name = Notificacion.COLUMN_NAME_LECTOR_FK)
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector
     *            the lector to set
     */
    public void setLector(Lector lector) {
        this.lector = lector;
    }

    /**
     * @return the motivoNoEntrega
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.mensajeria.model.MotivoNoEntrega.class, fetch = FetchType.LAZY)
    @JoinColumn(name = MotivoNoEntrega.COLUMN_NAME_ID)
    @ForeignKey(name = Notificacion.COLUMN_NAME_MOTIVO_FK)
    public MotivoNoEntrega getMotivoNoEntrega() {
        return motivoNoEntrega;
    }

    /**
     * @param motivoNoEntrega
     *            the motivoNoEntrega to set
     */
    public void setMotivoNoEntrega(MotivoNoEntrega motivoNoEntrega) {
        this.motivoNoEntrega = motivoNoEntrega;
    }

    /**
     * @return the fechaAlta
     */
    @Column(name = Notificacion.COLUMN_NAME_FECHAALTA)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta
     *            the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the fechaNotificacion
     */
    @Column(name = Notificacion.COLUMN_NAME_FECHANOTIFICACION)
    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    /**
     * @param fechaNotificacion
     *            the fechaNotificacion to set
     */
    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    /**
     * @return the accion
     */
    @Column(name = Notificacion.COLUMN_NAME_ACCION, nullable = false)
    public Boolean getAccion() {
        return accion;
    }

    /**
     * @param accion
     *            the accion to set
     */
    public void setAccion(Boolean accion) {
        this.accion = accion;
    }

    /**
     * @return the deseaNotificacion
     */
    @Column(name = Notificacion.COLUMN_NAME_DESEANOTIFICACION)
    public Boolean getDeseaNotificacion() {
        return deseaNotificacion;
    }

    /**
     * @param deseaNotificacion
     *            the deseaNotificacion to set
     */
    public void setDeseaNotificacion(Boolean deseaNotificacion) {
        this.deseaNotificacion = deseaNotificacion;
    }

    /**
     * @return the mensaje
     */
    @Column(name = Notificacion.COLUMN_NAME_MENSAJE, length = 800)
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje
     *            the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the nota
     */
    @Column(name = Notificacion.COLUMN_NAME_NOTA, length = 255)
    public String getNota() {
        return nota;
    }

    /**
     * @param nota
     *            the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = Notificacion.COLUMN_NAME_BIBLIOTECA_FK)
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
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
        if (!(obj instanceof Notificacion)) {
            return false;
        }
        final Notificacion other = (Notificacion) obj;
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
        return new ToStringBuilder(this)
                .append(Notificacion.COLUMN_NAME_ID, id).append(
                        Notificacion.COLUMN_NAME_FECHAALTA, fechaAlta).append(
                        Notificacion.COLUMN_NAME_FECHANOTIFICACION,
                        fechaNotificacion).append(
                        Notificacion.COLUMN_NAME_ACCION, accion).append(
                        Notificacion.COLUMN_NAME_DESEANOTIFICACION,
                        deseaNotificacion).append(
                        Notificacion.COLUMN_NAME_MENSAJE, mensaje).append(
                        Notificacion.COLUMN_NAME_NOTA, nota).toString();
    }

    @Transient
    public String getEmailPrincipal() {
        return this.getLector().getEmailPrincipal();
    }

}
