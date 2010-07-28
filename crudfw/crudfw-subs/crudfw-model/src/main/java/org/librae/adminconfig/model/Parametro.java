package org.librae.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Requisito de almacenamiento para albergar los parámetros de ejecución
 * globales del sistema
 * 
 * @author asantamaria
 */
@Entity(name = Parametro.ENTITY_NAME)
@Table(name = Parametro.TABLE_NAME)
public class Parametro extends BaseObject {

    /**
     * BaseObject is Serializable, so Parametro needs a Serial Version UID
     */
    private static final long   serialVersionUID                         = -3961571896636545975L;

    public static final String  ENTITY_NAME                              = "org.librae.adminconfig.model.Parametro";
    public static final String  TABLE_NAME                               = "ADM_PARAMETROS";
    public static final String  ID_GENERATOR_NAME                        = "generator_adm_parametros";
    private static final String ID_SEQUENCE_NAME                         = "SEQ_ADM_PARAMETROS";

    public static final String  COLUMN_NAME_ID                           = "X_PARAMETRO";
    public static final String  COLUMN_NAME_CODIGO                       = "T_CODIGO";
    public static final String  COLUMN_NAME_DESCRIPCION                  = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_VALOR                        = "T_VALOR";
    public static final String  COLUMN_NAME_NOMBRE_ARCHIVO               = "T_NOMBREBLOB";
    public static final String  COLUMN_NAME_VALOR_ARCHIVO                = "T_VALORBLOB";
    public static final String  COLUMN_NAME_VALOR_ELIMINABLE             = "L_ELIMINABLE";
    public static final String  COLUMN_NAME_VALOR_ARCHIVO_CONTENT_TYPE   = "T_FICHERO_CONTENT_TYPE";

    public static final String  PROPERTY_NAME_ID                         = "id";
    public static final String  PROPERTY_NAME_CODIGO                     = "codigo";
    public static final String  PROPERTY_NAME_DESCRIPCION                = "descripcion";
    public static final String  PROPERTY_NAME_VALOR                      = "valor";
    public static final String  PROPERTY_NAME_NOMBRE_ARCHIVO             = "nombreArchivo";
    public static final String  PROPERTY_NAME_VALOR_ARCHIVO              = "valorArchivo";
    public static final String  PROPERTY_NAME_VALOR_ELIMINABLE           = "eliminable";
    public static final String  PROPERTY_NAME_VALOR_ARCHIVO_CONTENT_TYPE = "valorArchivoContentType";

    /**
     * Identificador interno del parámetro
     */
    private Long                id;

    /**
     * Código único representativo del parámetro
     */
    private String              codigo;

    /**
     * Descripción del parámetro
     */
    private String              descripcion;

    /**
     * Valor global del parámetro. Este atributo se utilizará cuando el valor de
     * parámetro sea de tipo simple: Entero, cadena, fecha etc. En tal caso los
     * atributos T_VALORBLOB y T_NOMBREBLOB serán nulos
     */
    private String              valor;

    /**
     * En el caso de que el valor del parámetro sea un archivo almacenado en el
     * atributo T_VALORBLOB, aquí se almacenará el nombre del archivo
     */
    private String              nombreArchivo;

    /**
     * Valor global del parámetro. Este atributo se utilizará cuando el valor
     * del parámetro sea algún tipo de archivo: XML, XSD, jrxml, etc. En tal
     * caso el atributo T_VALOR será nulo
     */
    private byte[]              valorArchivo;

    /**
     * ContentType del fichero que contiene el xml de la transfomacion o job.
     */
    private String              valorArchivoContentType;

    /**
     * Si es true este parametro no se podra ni eliminar ni editar su codigo.
     */
    private Boolean             eliminable;

    protected Parametro() {
        super();
    }

    public Parametro(String codigo, String descripcion) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

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
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    @Column(name = Parametro.COLUMN_NAME_CODIGO, length = 40)
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
     * @return the descripcion
     */
    @Column(name = Parametro.COLUMN_NAME_DESCRIPCION, length = 80)
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
     * @return the valor
     */
    @Column(name = Parametro.COLUMN_NAME_VALOR)
    public String getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the nombreArchivo
     */
    @Column(name = Parametro.COLUMN_NAME_NOMBRE_ARCHIVO, length = 80)
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo
     *            the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the valorArchivo
     */
    @Column(name = Parametro.COLUMN_NAME_VALOR_ARCHIVO)
    @Lob
    public byte[] getValorArchivo() {
        return valorArchivo;
    }

    /**
     * @param valorArchivo
     *            the valorArchivo to set
     */
    public void setValorArchivo(byte[] valorArchivo) {
        this.valorArchivo = valorArchivo;
    }

    /**
     * @return the eliminable
     */
    @Column(name = Parametro.COLUMN_NAME_VALOR_ELIMINABLE)
    public Boolean getEliminable() {
        return (eliminable == null) ? Boolean.FALSE : eliminable;
    }

    /**
     * @param eliminable
     *            the eliminable to set
     */
    public void setEliminable(Boolean eliminable) {
        this.eliminable = eliminable;
    }

    /**
     * @return the valorArchivoContentType
     */
    @Column(name = Parametro.COLUMN_NAME_VALOR_ARCHIVO_CONTENT_TYPE, length = 40)
    public String getValorArchivoContentType() {
        return valorArchivoContentType;
    }

    /**
     * @param valorArchivoContentType
     *            the valorArchivoContentType to set
     */
    public void setValorArchivoContentType(String valorArchivoContentType) {
        this.valorArchivoContentType = valorArchivoContentType;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Parametro)) {
            return false;
        }

        final Parametro other = (Parametro) obj;

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null && !getCodigo().equals(other.getCodigo())) {
            return false;
        }

        if (getValor() == null && other.getValor() != null) {
            return false;
        }
        if (getValor() != null && !getValor().equals(other.getValor())) {
            return false;
        }

        return true;

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        result = prime * result
                + ((getValor() == null) ? 0 : getValor().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(Parametro.PROPERTY_NAME_ID, getId())

        .append(Parametro.PROPERTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(Parametro.PROPERTY_NAME_DESCRIPCION,
                (getDescripcion() == null) ? "" : getDescripcion().toString())

        .append(Parametro.PROPERTY_NAME_VALOR,
                (getValor() == null) ? "" : getValor().toString())

        .toString();
    }

}