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
 * Datos de acceso al repositorio de Kettle donde se guardan las
 * transformaciones y jobs que se utilizan para exportar e importar datos.
 * 
 * @author jcisneros
 */
// @Entity(name = Repositorio.ENTITY_NAME)
// @Table(name = Repositorio.TABLE_NAME)
public class Repositorio extends BaseObject {

    /**
     * BaseObject is Serializable, so Repositorio needs a Serial Version UID
     */
    private static final long   serialVersionUID          = 1L;

    public static final String  ENTITY_NAME               = "org.librae.importexport.model.Repositorio";
    public static final String  TABLE_NAME                = "IEX_REPOSITORIO";
    public static final String  ID_GENERATOR_NAME         = "generator_iex_repositorios";
    private static final String ID_SEQUENCE_NAME          = "SEQ_IEX_REPOSITORIO";

    public static final String  COLUMN_NAME_ID            = "X_REPOSITORIO";
    public static final String  COLUMN_NAME_NOMBRE        = "T_NAME";
    public static final String  COLUMN_NAME_DESCRIPCION   = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_CONEXION      = "CONN_X_CONEXION";

    public static final String  PROPERTY_NAME_ID          = "id";
    public static final String  PROPERTY_NAME_DESCRIPCION = "descripcion";
    public static final String  PROPERTY_NAME_NOMBRE      = "nombre";
    public static final String  PROPERTY_NAME_CONEXION    = "conexion";

    /**
     * Clave primaria.
     */
    private Long                id;

    /**
     * Nombre por el que el uaurio conoce al repositorio. NOT NULL.
     */
    private String              nombre;

    /**
     * Descripción del repositorio.
     */
    private String              descripcion;

    /**
     * Referencia a la conexión que se utilizará con el API de Kettle para
     * acceder al repositorio de esta herramienta.
     */
    private Conexion            conexion;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Repositorio.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Repositorio.ID_GENERATOR_NAME, sequenceName = Repositorio.ID_SEQUENCE_NAME)
    @Column(name = Repositorio.COLUMN_NAME_ID)
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
     * @return
     */
    @ManyToOne(targetEntity = Conexion.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Repositorio.COLUMN_NAME_CONEXION)
    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    @Column(name = Repositorio.COLUMN_NAME_NOMBRE)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = Repositorio.COLUMN_NAME_DESCRIPCION)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(obj instanceof Repositorio)) {
            return false;
        }

        final Repositorio other = (Repositorio) obj;
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
        return new ToStringBuilder(this).append(Repositorio.PROPERTY_NAME_ID,
                getId()).toString();
    }

}