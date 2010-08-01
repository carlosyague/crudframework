package org.librae.importexport.model;

import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Datos de acceso a las bases de datos, que se necesitan para utilizar con el
 * API de Kettle. El concepto de conexión es un concepto del API de Kettle, que
 * representa una conexión a una base de datos en un SGBD, en un host y en un
 * puerto. En nuestra aplicación necesitamos guardar datos de, al menos, dos
 * conexiones: <br>
 * - Conexión de nuestra aplicación a la base de datos que hace de repositorio
 * de Kettle. La aplicación pasa los datos de esta conexión a los métodos de las
 * clases de org.pentaho.di.repository.RepositoryMeta y
 * org.pentaho.di.core.database.DatabaseMeta para acceder al repositorio de
 * Kettle <br>
 * - Conexión a la base de datos de nuestra aplicación, en la que reside una
 * tabla de base de datos, creada con Spoon, para almacenar los logs de
 * ejecuciones de transformaciones y jobs. La aplicación pasa los datos de esta
 * conexión al API de Kettle para establecer, en los datos de detalle de una
 * transformación o job, donde éste debe guardar la información de log que
 * genera en cada una de sus ejecuciones.
 * 
 * @author jcisneros
 */
// @Entity(name = Conexion.ENTITY_NAME)
// @Table(name = Conexion.TABLE_NAME)
public class Conexion extends BaseObject {

    /**
     * BaseObject is Serializable, so Conexion needs a Serial Version UID
     */
    private static final long   serialVersionUID               = 1L;

    public static final String  ENTITY_NAME                    = "org.librae.importexport.model.Conexion";
    public static final String  TABLE_NAME                     = "IEX_CONEXION";
    public static final String  ID_GENERATOR_NAME              = "generator_cir_conexiones";
    private static final String ID_SEQUENCE_NAME               = "SEQ_IEX_CONEXION";
    public static final String  COLUMN_NAME_ID                 = "X_CONEXION";
    public static final String  COLUMN_NAME_ACCESO             = "T_CONN_ACCESS";
    public static final String  COLUMN_NAME_ATRIBUTO           = "T_CONN_ATTRIBUTES";
    public static final String  COLUMN_NAME_DATA_TABLESPACE    = "T_CONN_DATA_TABLESPACE";
    public static final String  COLUMN_NAME_INDEX_TABLESPACE   = "T_CONN_INDEX_TABLESPACE";
    public static final String  COLUMN_NAME_NOMBRE             = "T_CONN_NAME";
    public static final String  COLUMN_NAME_PASSWORD           = "T_CONN_PASSWORD";
    public static final String  COLUMN_NAME_PUERTO             = "T_CONN_PORT";
    public static final String  COLUMN_NAME_SERVIDOR           = "T_CONN_SERVER";
    public static final String  COLUMN_NAME_TIPO               = "T_CONN_TYPE";
    public static final String  COLUMN_NAME_USUARIO            = "T_CONN_USERNAME";
    public static final String  COLUMN_NAME_CONEXION           = "T_CONN_CONEXION";
    public static final String  COLUMN_NAME_DATABASE           = "T_CONN_DATABASE";

    public static final String  PROPERTY_NAME_ID               = "id";
    public static final String  PROPERTY_NAME_ACCESO           = "acceso";
    public static final String  PROPERTY_NAME_ATRIBUTO         = "atributo";
    public static final String  PROPERTY_NAME_DATA_TABLESPACE  = "dataTableSpace";
    public static final String  PROPERTY_NAME_INDEX_TABLESPACE = "indexTableSpace";
    public static final String  PROPERTY_NAME_NOMBRE           = "nombre";
    public static final String  PROPERTY_NAME_PASSWORD         = "password";
    public static final String  PROPERTY_NAME_PUERTO           = "puerto";
    public static final String  PROPERTY_NAME_SERVIDOR         = "servidor";
    public static final String  PROPERTY_NAME_TIPO             = "tipo";
    public static final String  PROPERTY_NAME_USUARIO          = "usuario";
    public static final String  PROPERTY_NAME_CONEXION         = "conexion";
    public static final String  PROPERTY_NAME_DATABASE         = "database";

    /**
     * Identificador.
     */
    private Long                id;

    /**
     * Tipo de acceso al SGBD del repositorio. NOT NULL.
     */
    private String              acceso;

    /**
     * Cadena XML con los atributos de la conexión, en el mismo formato que el
     * contenido del elemento Attributes del fichero C:\Document and
     * Settings\%USERNAME%\.kettle\repositories.xml que genera Spoon.
     */
    private String              atributo;

    /**
     * Nombre de tablespace de datos.
     */
    private String              dataTableSpace;

    /**
     * Nombre de tablespace de indices.
     */
    private String              indexTableSpace;

    /**
     * Nombre de la conexión que se utiliza para conectar con el repositorio.
     * NOT NULL.
     */
    private String              nombre;

    /**
     * Password de usuario de acceso al repositorio El valor por defecto es
     * Spoon asigna por defecto a la cuenta de administrador cuando con esta
     * herramienta se crea un repositorio.
     */
    private String              password;

    /**
     * Número de puerto de escucha del servidor SGBD que contiene el repositorio
     * NOT NULL.
     */
    private String              puerto;

    /**
     * Dirección IP o nombre de host del servidor en el que se esta ejecutando
     * el SGBD que contiene el repositorio NOT NULL.
     */
    private String              servidor;

    /**
     * Tipo de servidor de base de datos que gestiona el repositorio NOT NULL.
     */
    private String              tipo;

    /**
     * Nombre de usuario para acceso al repositorio El valor por defecto es el
     * nombre de usuario que Spoon asigna a la cuenta de administrador del
     * repositorio.
     */
    private String              usuario;

    /**
     * Clave primaria artificial.
     */
    private String              conexion;

    /**
     * Nombre de la base de datos que constituye el repositorio de Kettle.
     */
    private String              database;

    protected Conexion() {
        super();
    }

    public Conexion(final String conexion) {
        super();
        this.conexion = conexion;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Conexion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Conexion.ID_GENERATOR_NAME, sequenceName = Conexion.ID_SEQUENCE_NAME)
    @Column(name = Conexion.COLUMN_NAME_ID)
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

    @Column(name = Conexion.COLUMN_NAME_ACCESO)
    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    @Column(name = Conexion.COLUMN_NAME_ATRIBUTO)
    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    @Column(name = Conexion.COLUMN_NAME_DATA_TABLESPACE)
    public String getDataTableSpace() {
        return dataTableSpace;
    }

    public void setDataTableSpace(String dataTableSpace) {
        this.dataTableSpace = dataTableSpace;
    }

    @Column(name = Conexion.COLUMN_NAME_INDEX_TABLESPACE)
    public String getIndexTableSpace() {
        return indexTableSpace;
    }

    public void setIndexTableSpace(String indexTableSpace) {
        this.indexTableSpace = indexTableSpace;
    }

    @Column(name = Conexion.COLUMN_NAME_NOMBRE)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = Conexion.COLUMN_NAME_PASSWORD)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = Conexion.COLUMN_NAME_PUERTO)
    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    @Column(name = Conexion.COLUMN_NAME_SERVIDOR)
    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    @Column(name = Conexion.COLUMN_NAME_TIPO)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(name = Conexion.COLUMN_NAME_USUARIO)
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Column(name = Conexion.COLUMN_NAME_CONEXION)
    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
        this.conexion = conexion;
    }

    @Column(name = Conexion.COLUMN_NAME_DATABASE)
    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Conexion)) {
            return false;
        }

        final Conexion other = (Conexion) obj;
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
        return new ToStringBuilder(this).append(Conexion.PROPERTY_NAME_ID,
                getId()).toString();
    }

}