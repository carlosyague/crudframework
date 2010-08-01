package org.librae.catalogacion.model;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.librae.common.model.BaseObject;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Bean para almacena la información entre un Registro bibliográfico de LibrAE y
 * un Activo digital del repositorio.
 *
 * @author jcdiaz
 */
@Entity(name = ActivoDigital.ENTITY_NAME)
@Table(name = ActivoDigital.TABLE_NAME)
public class ActivoDigital extends BaseObject {

    /**
     * SERIAL VERSION UID
     */
    private static final long   serialVersionUID              = -4701668978819504964L;

    /**
     * BaseObject es Serializable, por lo tanto Procedencia necesita un Serial
     * Version UID
     */

    public static final String  ENTITY_NAME                   = "org.librae.catalogacion.model.ActivoDigital";

    /**
     * Constantes para referencia de los atributos de ActivoDigital
     */
    public static final String  PROPTY_NAME_ID                = "id";
    public static final String  PROPTY_NAME_PID               = "pid";
    public static final String  PROPTY_NAME_MODELO_CONTENIDO  = "modeloContenido";
    public static final String  PROPTY_NAME_URL               = "url";
    public static final String  PROPTY_NAME_ESTADO            = "estado";
    public static final String  PROPTY_NAME_UID               = "uid";
    public static final String  PROPTY_NAME_VISIBILIDAD       = "visibilidad";
    public static final String  PROPTY_NAME_FECHA_VINCULACION = "fechaVinculacion";
    public static final String  PROPTY_NAME_FECHA_CREACION    = "fechaCreacion";
    public static final String  PROPTY_NAME_AREA    = "area";
    public static final String  PROPTY_NAME_REGISTRO          = "registro";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME                    = "BIB_DIG_ACTIVO_DIGITAl";
    private static final String ID_GENERATOR_NAME             = "generator_big_dig_activo_digital";
    private static final String ID_SEQUENCE_NAME              = "SEQ_BIB_DIG_ACTIVO_DIGITAL";

    public static final String  COLUMN_NAME_ID                = "X_ACTIVO_DIGITAL";
    public static final String  COLUMN_NAME_PID               = "T_PID";
    public static final String  COLUMN_NAME_MODELO_CONTENIDO  = "T_MOD_CONTENIDO";
    public static final String  COLUMN_NAME_URL               = "T_URL";
    public static final String  COLUMN_NAME_ESTADO            = "L_ESTADO";
    public static final String  COLUMN_NAME_UID               = "T_UID";
    public static final String  COLUMN_NAME_VISIBILIDAD       = "L_VISIBILIDAD";
    public static final String  COLUMN_NAME_FECHA_VINCULACION = "F_FECHA_VINCULACION";
    public static final String  COLUMN_NAME_FECHA_CREACION    = "F_FECHA_CREACION";
    public static final String  COLUMN_NAME_AREA    = "T_AREA";

    public static final String  COLUMN_NAME_REGISTRO_FK       = "REG_X_REGISTRO";

    /**
     * Clave autonumérica secuencial sin significado funcional
     */
    private Long                id;

    /**
     * Identificador único del Activo en el Repositorio.
     */
    private String              pid;

    /**
     * En realidad, lo único que necesita conocer LibrÆ acerca del modelo de
     * contenido del activo es qué icono debe utilizar para mostrar el enlace al
     * mismo.
     */
    private String              modeloContenido;

    /**
     * La URL del Activo en el Repositorio. Esta URL puede ser estática (el
     * Repositorio proporciona la URL completa) o dinámica (el Repositorio
     * proporciona una ruta relativa que hay que añadir a una determinada URL
     * base). En cualquier caso, esta será la URL de acceso al visor del Activo
     * Digital ya que LibrÆ delega la responsabilidad de la visualización del
     * Activo en el Repositorio.
     */
    private String              url;

    /**
     * Booleano que indica si el activo digital se encuentra en el estado de
     * "Publicado" (que sería true o 1) o se encuentra en estado "En edición"
     * (que sería false o 0). Inicialmente todos los activos cargados en el
     * repositorio bibliográfico debe setearse a 0 y ser el sistema de gestión
     * quien maneje los valores posibles. Estos dos estados deben tener su
     * correspondencia en el Repositorio de tal manera que los Activos en estado
     * "Publicado" sean visibles desde la Web y el Recolector OAI y aquellos en
     * estado "En Edición" no lo sean.
     */
    private Boolean             estado;

    /**
     * Identificador único del Registro Bibliográfico en LibrÆ. Este campo
     * estará a null en caso de que el Activo aún no tenga asociado un Registro
     * Bibliográfico en LibrÆ. No se considera que un Activo esté correctamente
     * asociado a un Registro hasta que no tenga tanto el UID como la Fecha de
     * vinculación diferente a Null.
     */
    private String              uid;

    /**
     * Flag que indica si el Activo es público (que sería true o 1) o privado
     * (que sería false o 0).
     */
    private Boolean             visibilidad;

    /**
     * Fecha en la que se estableció la relación entre un Registro Bibliográfico
     * de LibrÆ y el Activo en cuestión en el Repositorio. No se considera que
     * un Activo esté correctamente asociado a un Registro hasta que no tenga
     * tanto el UID como la Fecha de vinculación diferente a Null.
     */
    private Date                fechaVinculacion;

    /**
     * Fecha de subida del Activo al Repositorio.
     */
    private Date                fechaCreacion;

    /**
     * Area al que pertenece el activo digital.
     */
    private String                area;

    /**
     * Clave foránea al objeto registro con el id como el identificador único.
     */
    private Registro            registro;

    /**
     * Constructor sin parámetros.
     */
    protected ActivoDigital() {
        super();
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param pid
     * @param modeloContenido
     * @param url
     * @param publicado
     * @param uid
     * @param visibilidad
     * @param fechaVinculacion
     * @param fechaCreacion
     * @param area
     * @param registro
     */
    public ActivoDigital(final String pid, final String modeloContenido,
            final String url, final Boolean estado, final String uid,
            final Boolean visibilidad, final Date fechaVinculacion,
            final Date fechaCreacion,
            final String area, final Registro registro) {
        super();
        this.pid = pid;
        this.modeloContenido = modeloContenido;
        this.url = url;
        this.estado = estado;
        this.uid = uid;
        this.visibilidad = visibilidad;
        this.fechaVinculacion = fechaVinculacion;
        this.fechaCreacion = fechaCreacion;
        this.area = area;
        this.registro = registro;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ActivoDigital.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ActivoDigital.ID_SEQUENCE_NAME)
    @Column(name = ActivoDigital.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the pid
     */
    @Column(name = ActivoDigital.COLUMN_NAME_PID, length = 20)
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the modeloContenido
     */
    @Column(name = ActivoDigital.COLUMN_NAME_MODELO_CONTENIDO, length = 80)
    public String getModeloContenido() {
        return modeloContenido;
    }

    /**
     * @param modeloContenido
     *            the modeloContenido to set
     */
    public void setModeloContenido(final String modeloContenido) {
        this.modeloContenido = modeloContenido;
    }

    /**
     * @return the url
     */
    @Column(name = ActivoDigital.COLUMN_NAME_URL, length = 255)
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * @return the estado
     */
    @Column(name = ActivoDigital.COLUMN_NAME_ESTADO)
    public Boolean getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            the estado to set
     */
    public void setEstado(final Boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the uid
     */
    @Column(name = ActivoDigital.COLUMN_NAME_UID, length = 20)
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(final String uid) {
        this.uid = uid;
    }

    /**
     * @return the visbilidad
     */
    @Column(name = ActivoDigital.COLUMN_NAME_VISIBILIDAD)
    public Boolean getVisibilidad() {
        return visibilidad;
    }

    /**
     * @param visbilidad
     *            the visbilidad to set
     */
    public void setVisibilidad(final Boolean visbilidad) {
        this.visibilidad = visbilidad;
    }

    /**
     * @return the fechaVinculacion
     */
    @Column(name = ActivoDigital.COLUMN_NAME_FECHA_VINCULACION)
    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    /**
     * @param fechaVinculacion
     *            the fechaVinculacion to set
     */
    public void setFechaVinculacion(final Date fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    /**
     * @return the fechaCreacion
     */
    @Column(name = ActivoDigital.COLUMN_NAME_FECHA_CREACION)
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion
     *            the fechaCreacion to set
     */
    public void setFechaCreacion(final Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }



    /**
     * @return the area
     */
    @Column(name = ActivoDigital.COLUMN_NAME_AREA, length = 80)
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(final String area) {
        this.area = area;
    }

    /**
     * @return the registro
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.catalogacion.model.Registro.class, fetch = FetchType.LAZY)
    @JoinColumn(name = ActivoDigital.COLUMN_NAME_REGISTRO_FK)
    public Registro getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(final Registro registro) {
        this.registro = registro;
    }

    /**
     * @return
     */
    @Transient
    public String getEstadoLabel() {
        String estado;
        if (this.estado) {
            estado = "Publicado";
        } else {
            estado = "En edición";
        }
        return estado;
    }

    /**
     * @return
     */
    @Transient
    public String getVisibilidadLabel() {
        String visibilidad;
        if (this.visibilidad) {
            visibilidad = "Público";
        } else {
            visibilidad = "Privado";
        }
        return visibilidad;
    }

    /**
     * @return
     */
    @Transient
    public String getImagenModeloContenido() {
        String logo;
        if (this.modeloContenido.equals("Libro")) {
            logo = "/Libro.JPG";
        } else if (this.modeloContenido.equals("Audio")) {
            logo = "/Audio.JPG";
        } else if (this.modeloContenido.equals("Video")) {
            logo = "/Video.JPG";
        } else {
            logo = "/Lupa.gif";
        }
        return logo;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pid == null) ? 0 : pid.hashCode());
        result = prime * result
                + ((modeloContenido == null) ? 0 : modeloContenido.hashCode());
        result = prime * result
                + ((registro == null) ? 0 : registro.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ActivoDigital)) {
            return false;
        }
        ActivoDigital other = (ActivoDigital) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (pid == null) {
            if (other.pid != null) {
                return false;
            }
        } else if (!pid.equals(other.pid)) {
            return false;
        }
        if (modeloContenido == null) {
            if (other.modeloContenido != null) {
                return false;
            }
        } else if (!modeloContenido.equals(other.modeloContenido)) {
            return false;
        }
        if (registro == null) {
            if (other.registro != null) {
                return false;
            }
        } else if (!registro.equals(other.registro)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this).append(ActivoDigital.COLUMN_NAME_ID,
                id).append(ActivoDigital.COLUMN_NAME_PID,
                (pid == null) ? 0 : pid).append(
                ActivoDigital.COLUMN_NAME_MODELO_CONTENIDO,
                (modeloContenido == null) ? 0 : modeloContenido).append(
                ActivoDigital.COLUMN_NAME_REGISTRO_FK,
                (registro == null) ? 0 : registro).toString();
    }

}
