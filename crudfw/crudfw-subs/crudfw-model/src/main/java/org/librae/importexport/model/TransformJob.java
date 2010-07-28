package org.librae.importexport.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;

import org.librae.common.model.BaseObject;

/**
 * Datos de acceso al repositorio de Kettle donde se guardan las
 * transformaciones y jobs que se utilizan para exportar e importar datos.
 * 
 * @author jcisneros
 */
@Entity(name = TransformJob.ENTITY_NAME)
@Table(name = TransformJob.TABLE_NAME)
public class TransformJob extends BaseObject {

    /**
     * BaseObject is Serializable, so Repositorio needs a Serial Version UID
     */
    private static final long   serialVersionUID                      = 1L;

    public static final String  ENTITY_NAME                           = "org.librae.importexport.model.TransformJob";
    public static final String  TABLE_NAME                            = "IEX_TRANSFORMACIONJOB";
    public static final String  ID_GENERATOR_NAME                     = "generator_iex_trasnformjob";
    private static final String ID_SEQUENCE_NAME                      = "SEQ_IEX_TRANSFORMJOB";

    public static final String  COLUMN_NAME_ID                        = "X_TRANSFORMJOB";
    public static final String  COLUMN_NAME_NOMBRE                    = "T_NAME";
    public static final String  COLUMN_NAME_DESCRIPCION               = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_DESCRIPCION_ALT           = "T_DESCRIPCION_ALT";
    public static final String  COLUMN_NAME_TIPO                      = "T_TIPO_X_TIPO_TRANS";
    public static final String  COLUMN_NAME_USUARIO_AUTOR             = "T_USUARIO_AUTOR";
    public static final String  COLUMN_NAME_USUARIO_MODIFICADOR       = "T_USUARIO_MODIFIRADOR";
    public static final String  COLUMN_NAME_FECHA_CREACION            = "F_FECHA_CREACION";
    public static final String  COLUMN_NAME_FECHA_MODIFICACION        = "F_FECHA_MODIFICACION";
    public static final String  COLUMN_NAME_XML                       = "T_XML";
    public static final String  COLUMN_NAME_FICHERO_NOMBRE            = "T_FICHERO_NOMBRE";
    public static final String  COLUMN_NAME_FICHERO_CONTENT_TYPE      = "T_FICHERO_CONTENT_TYPE";

    public static final String  PROPERTY_NAME_ID                      = "id";
    public static final String  PROPERTY_NAME_NOMBRE                  = "nombre";
    public static final String  PROPERTY_NAME_TIPO                    = "tipo";
    public static final String  PROPERTY_NAME_USUARIO_AUTOR           = "usuarioAutor";
    public static final String  PROPERTY_NAME_USUARIO_MODIFICADOR     = "usuarioModificador";
    public static final String  PROPERTY_NAME_FECHA_CREACION          = "fechaCreacion";
    public static final String  PROPERTY_NAME_FECHA_MODIFICACION      = "fechaModificacion";
    public static final String  PROPERTY_NAME_DESCRIPCION             = "descripcion";
    public static final String  PROPERTY_NAME_DESCRIPCION_ALTERNATIVA = "descripcionAlternativa";
    public static final String  PROPERTY_NAME_XML                     = "xml";
    public static final String  PROPERTY_NAME_FICHERO_NOMBRE_FICHERO  = "ficheroNombreFichero";
    public static final String  PROPERTY_NAME_FICHERO_CONTENT_TYPE    = "ficheroContentType";
    public static final String  PROPERTY_NAME_LOGS                    = "logs";

    /**
     * Clave primaria.
     */
    private Long                id;
    private String              nombre;
    private String              usuarioAutor;
    private String              usuarioModificador;
    private TipoTransformJob    tipo;
    private Date                fechaModificacion;
    private Date                fechaCreacion;
    private String              descripcion;
    private String              descripcionAlternativa;

    /**
     * Fichero que contiene el xml de la transfomacion o job.
     */
    private byte[]              xml;

    /**
     * Nombre del fichero que contiene el xml de la transfomacion o job.
     */
    private String              ficheroNombre;

    /**
     * ContentType del fichero que contiene el xml de la transfomacion o job.
     */
    private String              ficheroContentType;

    private List<Log>           logs;

    protected TransformJob() {
        super();
    }

    public TransformJob(final String nombre) {
        super();
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TransformJob.ID_GENERATOR_NAME)
    @SequenceGenerator(name = TransformJob.ID_GENERATOR_NAME, sequenceName = TransformJob.ID_SEQUENCE_NAME)
    @Column(name = TransformJob.COLUMN_NAME_ID)
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
     * @return the permisosRoles
     */
    @OneToMany(targetEntity = Log.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = Log.COLUMN_NAME_TRANSFORMJOB)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<Log> getLogs() {
        return logs;
    }

    /**
     * @param logs
     *            the logs to set
     */
    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    /**
     * @return
     */
    @Column(name = TransformJob.COLUMN_NAME_NOMBRE, length = 80)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return
     */
    @ManyToOne(targetEntity = TipoTransformJob.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = TransformJob.COLUMN_NAME_TIPO)
    public TipoTransformJob getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     */
    public void setTipo(TipoTransformJob tipo) {
        this.tipo = tipo;
    }

    /**
     * @return
     */
    @Column(name = TransformJob.COLUMN_NAME_FECHA_MODIFICACION)
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Column(name = TransformJob.COLUMN_NAME_USUARIO_AUTOR, length = 80)
    public String getUsuarioAutor() {
        return usuarioAutor;
    }

    public void setUsuarioAutor(String usuarioAutor) {
        this.usuarioAutor = usuarioAutor;
    }

    @Column(name = TransformJob.COLUMN_NAME_USUARIO_MODIFICADOR, length = 80)
    public String getUsuarioModificador() {
        return usuarioModificador;
    }

    public void setUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
    }

    @Column(name = TransformJob.COLUMN_NAME_FECHA_CREACION)
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = TransformJob.COLUMN_NAME_DESCRIPCION, length = 80)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = TransformJob.COLUMN_NAME_DESCRIPCION_ALT)
    public String getDescripcionAlternativa() {
        return descripcionAlternativa;
    }

    public void setDescripcionAlternativa(String descripcionAlternativa) {
        this.descripcionAlternativa = descripcionAlternativa;
    }

    /**
     * @return
     */
    @Column(name = TransformJob.COLUMN_NAME_XML)
    @Lob
    public byte[] getXml() {
        return xml;
    }

    public void setXml(byte[] xml) {
        this.xml = xml;
    }

    /**
     * @return
     */
    @Column(name = TransformJob.COLUMN_NAME_FICHERO_CONTENT_TYPE, length = 80)
    public String getFicheroContentType() {
        return ficheroContentType;
    }

    public void setFicheroContentType(String ficheroContentType) {
        this.ficheroContentType = ficheroContentType;
    }

    /**
     * @return the ficheroNombre
     */
    @Column(name = TransformJob.COLUMN_NAME_FICHERO_NOMBRE, length = 80)
    public String getFicheroNombre() {
        return ficheroNombre;
    }

    /**
     * @param ficheroNombre
     *            the ficheroNombre to set
     */
    public void setFicheroNombre(String ficheroNombre) {
        this.ficheroNombre = ficheroNombre;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof TransformJob)) {
            return false;
        }

        final TransformJob other = (TransformJob) obj;
        return true;

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(TransformJob.PROPERTY_NAME_ID,
                getId()).toString();
    }

}