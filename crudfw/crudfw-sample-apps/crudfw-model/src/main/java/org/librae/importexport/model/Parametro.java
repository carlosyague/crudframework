package org.librae.importexport.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Parametros globales a todo el subsistema de Importación y Exportación de
 * Datos (IEX).
 * 
 * @author jcisneros
 */
// @Entity(name = Parametro.ENTITY_NAME)
// @Table(name = Parametro.TABLE_NAME)
public class Parametro extends BaseObject {

    /**
     * BaseObject is Serializable, so Parametro needs a Serial Version UID
     */
    private static final long   serialVersionUID                     = 1L;

    public static final String  ENTITY_NAME                          = "org.librae.importexport.model.Parametro";
    public static final String  TABLE_NAME                           = "IEX_PARAMETRO";
    public static final String  ID_GENERATOR_NAME                    = "generator_iex_parametros";
    private static final String ID_SEQUENCE_NAME                     = "SEQ_IEX_PARAMETRO";

    public static final String  COLUMN_NAME_ID                       = "X_PARAMETRO";
    public static final String  COLUMN_NAME_NOMBRE_TABLA_LOG         = "T_NOMBRE_TABLA_LOG";
    public static final String  COLUMN_NAME_NOMBRE_VARIABLE_KETTLE   = "T_NOMBRE_VARIABLE_KETTLE";
    public static final String  COLUMN_NAME_PASSWORD                 = "T_PASSWORD";
    public static final String  COLUMN_NAME_URL                      = "T_URL";
    public static final String  COLUMN_NAME_USUARIO                  = "T_USERNAME";
    public static final String  COLUMN_NAME_CONEXION                 = "CONN_X_CONEXION";

    public static final String  PROPERTY_NAME_ID                     = "id";
    public static final String  PROPERTY_NAME_NOMBRE_TABLA_LOG       = "nombreTablaLog";
    public static final String  PROPERTY_NAME_NOMBRE_VARIABLE_KETTLE = "nombreVariableKettle";
    public static final String  PROPERTY_NAME_PASSWORD               = "password";
    public static final String  PROPERTY_NAME_URL                    = "url";
    public static final String  PROPERTY_NAME_USUARIO                = "usuario";
    public static final String  PROPERTY_NAME_CONEXION               = "conexion";

    /**
     * Identificador.
     */
    private Long                id;

    /**
     * Nombre de la tabla en la que las transformaciones y Jobs de kettle
     * guardan el registro de log de cada ejecución. La base de datos que
     * contiene la tabla se referencia en CONN_X_CONEXION_LOG.
     */
    private String              nombreTablaLog;

    /**
     * Nombre de variable usada por los jobs y transformaciones de Kettle que se
     * usan para importación y exportación de datos. La aplicación copia en esta
     * variable el valor del parámetro T_URL en la ejecución de cada job o
     * transformación que la use.
     */
    private String              nombreVariableKettle;

    /**
     * Password de acceso a la URL.
     */
    private String              password;

    /**
     * URL de la ubicación donde las transformaciones y jobs dejan los ficheros
     * que generan y de donde toman los ficheros que leen para importar datos..
     */
    private String              url;

    /**
     * Unsername de acceso a la URL.
     */
    private String              usuario;

    /**
     * Referencia a la conexión de base de datos cuyos datos se pasarán al API
     * de Kettle para que al ejecutarse una transformación o un job, deje el
     * registro de log en una tabla de esa conexión.
     */
    private Conexion            conexion;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Parametro.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Parametro.ID_GENERATOR_NAME, sequenceName = Parametro.ID_SEQUENCE_NAME)
    @Column(name = Parametro.COLUMN_NAME_ID)
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

    @Column(name = Parametro.COLUMN_NAME_NOMBRE_TABLA_LOG)
    public String getNombreTablaLog() {
        return nombreTablaLog;
    }

    public void setNombreTablaLog(String nombreTablaLog) {
        this.nombreTablaLog = nombreTablaLog;
    }

    @Column(name = Parametro.COLUMN_NAME_NOMBRE_VARIABLE_KETTLE)
    public String getNombreVariableKettle() {
        return nombreVariableKettle;
    }

    public void setNombreVariableKettle(String nombreVariableKettle) {
        this.nombreVariableKettle = nombreVariableKettle;
    }

    @Column(name = Parametro.COLUMN_NAME_PASSWORD)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = Parametro.COLUMN_NAME_URL)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = Parametro.COLUMN_NAME_USUARIO)
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @ManyToOne(targetEntity = Conexion.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Parametro.COLUMN_NAME_CONEXION)
    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
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
        if (!(obj instanceof Parametro)) {
            return false;
        }

        final Parametro other = (Parametro) obj;
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
        return new ToStringBuilder(this).append(Parametro.PROPERTY_NAME_ID,
                getId()).toString();
    }

}