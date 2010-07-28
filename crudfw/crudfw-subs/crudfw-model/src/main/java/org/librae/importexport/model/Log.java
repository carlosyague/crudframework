package org.librae.importexport.model;

import java.util.Date;

import javax.persistence.CascadeType;
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

/**
 * Tabla donde se almacena el log de ejecuciones de transformaciones y jobs.<br>
 * Los campos de este requisito de almacenamiento los determina la herramienta
 * Spoon (o su API), que es la herramienta que lo crea. Por tanto, NO CAMBIAR LA
 * ESTRUCTURA DE ESTA TABLA NI MANUALMENTE NI CON HIBERNATE.<br>
 * !! NOTA PARA EL DESPLIEGUE !! Esta tabla se crea como se explica a
 * continuación. Para el despliegue automatizado, extraer la sentencia CREATE
 * TABLE correspondiente después de creada la tabla, e incluirla en los scripts
 * de despliegue. En este RA especificamos la estructura de la tabla tal como la
 * crea Spoon en MySQL. Para Oracle u otro SGBD los tipos de las columnas serán
 * distintos.<br>
 * Esta se crea con Spoon de la siguiente forma:<br>
 * 1.- Para una transformación cualquiera: Menú emergente / Transformation
 * Settings / Pestaña Archivado. <br>
 * 2.- En el campo "Conexión Archivado" seleccionar una de las conexiones
 * disponibles o crear una nueva <br>
 * 3.- En "Tabla de archivado" especificar el nombre de la tabla que contendrá
 * el log (en nuestra aplicación IEX_LOG_ETL por defecto) <br>
 * 4.- Click en el botón SQL. Spoon muestra un cuadro de diálogo con la
 * sentencia SQL que ejecutará contra la conexión expecificada para crear la
 * tabla. Si la tabla ya existe y no es necesario ejecutar un ALTER TABLE (no se
 * han cambiado las opciones de la pestaña), entonces la herramienta informa de
 * que no es necesario ejecutar un SQL. En caso de que si sea necesario, click
 * en Execute.
 * 
 * @author jcisneros
 */
@Entity(name = Log.ENTITY_NAME)
@Table(name = Log.TABLE_NAME)
public class Log extends BaseObject {

    /**
     * BaseObject is Serializable, so Log needs a Serial Version UID
     */
    private static final long   serialVersionUID           = 7959455273221528637L;

    public static final String  ENTITY_NAME                = "org.librae.importexport.model.Log";
    public static final String  TABLE_NAME                 = "IEX_LOG";
    public static final String  ID_GENERATOR_NAME          = "generator_iex_log";
    private static final String ID_SEQUENCE_NAME           = "SEQ_IEX_LOG";

    public static final String  COLUMN_NAME_ID             = "X_LOG";
    public static final String  COLUMN_NAME_TRANSFORMJOB   = "TRANS_X_TRANSFORMJOB";
    public static final String  COLUMN_NAME_FECHA_INICIO   = "F_FECHA_INICIO";
    public static final String  COLUMN_NAME_FECHA_FIN      = "F_FECHA_FIN";
    public static final String  COLUMN_NAME_TEXTO          = "T_TEXTO";

    public static final String  PROPERTY_NAME_ID           = "id";
    public static final String  PROPERTY_NAME_FECHA_INICIO = "fechaInicio";
    public static final String  PROPERTY_NAME_FECHA_FIN    = "fechaFin";
    public static final String  PROPERTY_NAME_TRANSFORMJOB = "transformJob";
    public static final String  PROPERTY_NAME_TEXTO        = "texto";

    /**
     * Identificador.
     */
    private Long                id;

    private Date                fechaInicio;
    private Date                fechaFin;
    private TransformJob        transformJob;
    private String              texto;

    protected Log() {
        super();
    }

    public Log(String texto) {
        super();
        this.texto = texto;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Log.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Log.ID_GENERATOR_NAME, sequenceName = Log.ID_SEQUENCE_NAME)
    @Column(name = Log.COLUMN_NAME_ID)
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
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Log)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((getTexto() == null) ? 0 : getTexto().hashCode());
        result += ((getFechaInicio() == null) ? 0 : getFechaInicio().hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Log.PROPERTY_NAME_ID, getId())
                .toString();
    }

    /**
     * @return the transformJob
     */
    @ManyToOne(targetEntity = TransformJob.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Log.COLUMN_NAME_TRANSFORMJOB)
    public TransformJob getTransformJob() {
        return transformJob;
    }

    /**
     * @param transformJob
     *            the transformJob to set
     */
    public void setTransformJob(TransformJob transformJob) {
        this.transformJob = transformJob;
    }

    /**
     * @return the fechaInicio
     */
    @Column(name = Log.COLUMN_NAME_FECHA_INICIO)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio
     *            the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    @Column(name = Log.COLUMN_NAME_FECHA_FIN)
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin
     *            the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the texto
     */
    @Column(name = Log.COLUMN_NAME_TEXTO)
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto
     *            the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

}