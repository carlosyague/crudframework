/**
 *
 */
package org.librae.mensajeria.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.GrupoFuncional;
import org.librae.common.model.parampoliticas.AbstractParamPolTipoNotificacion;

/**
 * Bean para almacenar un Tipo de Notificacion.
 * 
 * @author amDelcampo
 */
@Entity(name = TipoNotificacion.NAME_ENTITY)
@Table(name = TipoNotificacion.TABLE_NAME)
public class TipoNotificacion extends AbstractParamPolTipoNotificacion {

    /**
     *
     */
    private static final long                      serialVersionUID                 = 2762511829041939957L;

    public static final String                     NAME_ENTITY                      = "org.librae.mensajeria.model.TipoNotificacion";
    public static final String                     TABLE_NAME                       = "MEN_DSI_CATALOGO";

    private static final String                    ID_GENERATOR_NAME                = "generator_men_dsi_catalogo";
    private static final String                    ID_SEQUENCE_NAME                 = "SEQ_MEN_DSI_CATALOGO";

    public static final String                     COLUMN_NAME_ID                   = "X_DSI_CATALOGO";
    public static final String                     COLUMN_NAME_GRUPOFUNCIONAL_FK    = "GRU_X_GRUPOS_FUNCIONALES";
    public static final String                     COLUMN_NAME_ACTIVO               = "L_ACTIVO";
    public static final String                     COLUMN_NAME_ENVIOOBLIGATORIO     = "L_ENVIO_OBLIGATORIO";
    public static final String                     COLUMN_NAME_CODIGO               = "T_COD_TIPONOTIFICACION";
    public static final String                     COLUMN_NAME_NOMBRE               = "T_NOMBRE";
    public static final String                     COLUMN_NAME_DESCRIPCION          = "T_DESCRIPCION";

    public static final String                     PROPERTY_NAME_ID                 = "id";
    public static final String                     PROPERTY_NAME_GRUPOFUNCIONAL_FK  = "grupoFuncional";
    public static final String                     PROPERTY_NAME_ACTIVO             = "activo";
    public static final String                     PROPERTY_NAME_ENVIOOBLIGATORIO   = "envioObligatorio";
    public static final String                     PROPERTY_NAME_CODIGO             = "codigo";
    public static final String                     PROPERTY_NAME_NOMBRE             = "nombre";
    public static final String                     PROPERTY_NAME_DESCRIPCION        = "descripcion";
    public static final String                     PROPERTY_NAME_CANALESINFORMACION = "canalesInformacion";
    public static final String                     PROPERTY_NAME_LECTORES           = "lectores";

    /**
     * Clave primaria.
     */
    private Long                                   id;

    /**
     * Referencia al Grupo Funcional.
     */
    private GrupoFuncional                         grupoFuncional;

    /**
     * Indica si la notificación esta activa.
     */
    private Boolean                                activo;

    /**
     * Indica si el envío de una notificación es configurable por el lector.
     */
    private Boolean                                envioObligatorio;

    /**
     * Código del tipo de notificación.
     */
    private String                                 codigo;

    /**
     * Nombre del DSI.
     */
    private String                                 nombre;

    /**
     * Descripción del DSI.
     */
    private String                                 descripcion;

    /**
     * lista de sucursales que ofrecen el tipo de notificacion.
     */
    private List<Sucursal>                         sucursales;

    /**
     * Array de elementos que relacionan el tipo de notificacion con sus
     * canales.
     */
    private List<TipoNotificacionCanalInformacion> canalesInformacion;

    /**
     * Constructor sin parámetros.
     */
    public TipoNotificacion() {
        super();
    }

    /**
     * Constructor con parámetros.
     */

    public TipoNotificacion(Long id, GrupoFuncional grupoFuncional,
            Boolean activo, Boolean envioObligatorio, String codigo,
            String nombre, String descripcion) {

        this.id = id;
        this.grupoFuncional = grupoFuncional;
        this.activo = activo;
        this.envioObligatorio = envioObligatorio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TipoNotificacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = TipoNotificacion.ID_SEQUENCE_NAME)
    @Column(name = TipoNotificacion.COLUMN_NAME_ID, nullable = false)
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
     * @return the grupoFuncional
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.GrupoFuncional.class)
    @JoinColumn(name = TipoNotificacion.COLUMN_NAME_GRUPOFUNCIONAL_FK)
    public GrupoFuncional getGrupoFuncional() {
        return grupoFuncional;
    }

    /**
     * @param grupoFuncional
     *            the grupoFuncional to set
     */
    public void setGrupoFuncional(GrupoFuncional grupoFuncional) {
        this.grupoFuncional = grupoFuncional;
    }

    /**
     * @return the activo
     */
    @Column(name = TipoNotificacion.COLUMN_NAME_ACTIVO)
    public Boolean getActivo() {
        return activo;
    }

    /**
     * @param activo
     *            the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the envioObligatorio
     */
    @Column(name = TipoNotificacion.COLUMN_NAME_ENVIOOBLIGATORIO)
    public Boolean getEnvioObligatorio() {
        return envioObligatorio;
    }

    /**
     * @param envioObligatorio
     *            the envioObligatorio to set
     */
    public void setEnvioObligatorio(Boolean envioObligatorio) {
        this.envioObligatorio = envioObligatorio;
    }

    /**
     * @return the codigo
     */
    @Column(name = TipoNotificacion.COLUMN_NAME_CODIGO, length = 15)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    @Column(name = TipoNotificacion.COLUMN_NAME_NOMBRE, length = 40)
    public String getNombre() {
        return nombre;
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
    @Column(name = TipoNotificacion.COLUMN_NAME_DESCRIPCION, length = 255)
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

    /**
     * @return the canalesInformacion
     */
    @OneToMany(mappedBy = TipoNotificacionCanalInformacion.PROPERTY_NAME_TIPONOTIFICACION_FK, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    public List<TipoNotificacionCanalInformacion> getCanalesInformacion() {
        return canalesInformacion;
    }

    /**
     * @param canalesInformacionFinal
     *            the canalesInformacion to set
     */
    public void setCanalesInformacion(
            List<TipoNotificacionCanalInformacion> canalesInformacionFinal) {
        canalesInformacion = canalesInformacionFinal;
    }

    /**
     * @return the sucursales
     */
    @OneToMany(mappedBy = Sucursal.PROPERTY_NAME_TIPONOTIFICACION_FK, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    /**
     * @param sucursales
     *            the sucursales to set
     */
    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TipoNotificacion)) {
            return false;
        }
        final TipoNotificacion other = (TipoNotificacion) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        } else if (!descripcion.equals(other.descripcion)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#hashCode()
     */
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append(
                TipoNotificacion.COLUMN_NAME_ID, id).append(
                TipoNotificacion.COLUMN_NAME_NOMBRE, nombre).append(
                TipoNotificacion.COLUMN_NAME_DESCRIPCION, descripcion).append(
                TipoNotificacion.COLUMN_NAME_ACTIVO, activo).append(
                TipoNotificacion.COLUMN_NAME_CODIGO, codigo)
                .append(TipoNotificacion.COLUMN_NAME_ENVIOOBLIGATORIO,
                        envioObligatorio).toString();
    }

    @Transient
    public TipoNotificacionCanalInformacion getCanalPrioritario() {
        TipoNotificacionCanalInformacion canal = null;
        for (int i = 0; i < this.getCanalesInformacion().size(); i++) {
            if (this.getCanalesInformacion().get(i).getPrioridad()) {
                canal = this.getCanalesInformacion().get(i);
                break;
            }

        }

        return canal;
    }

}
