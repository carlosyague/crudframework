package org.librae.catalogacion.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un AutoridadSubcampo
 * 
 * @author asalas
 * @version 1.0
 */
public class SubcampoMarc extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto AutoridadSubcampo necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID               = -9137565770633715643L;

    public static final String  ENTITY_NAME                    = "org.librae.catalogacion.model.SubcampoMarc";

    /**
     * Constantes para referencia de los atributos de AutoridadSubcampo
     */
    public static final String  PROPTY_NAME_ID                 = "id";
    public static final String  PROPTY_NAME_NOMBRE             = "nombre";
    public static final String  PROPTY_NAME_CODIGO_MARC        = "campoMarc";
    public static final String  PROPTY_NAME_VALOR              = "valor";
    public static final String  PROPTY_NAME_AUTORIDAD_ETIQUETA = "autoridadEtiqueta";
    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME                     = "CAT_SUBCAMPO_MARC";
    private static final String ID_GENERATOR_NAME              = "generator_cat_autoridad_subcampo";
    private static final String ID_SEQUENCE_NAME               = "SEQ_CAT_AUTORIDAD_SUBCAMPO";
    public static final String  COLUMN_NAME_ID                 = "X_AUTORIDAD_SUBCAMPO";
    public static final String  COLUMN_NAME_NOMBRE             = "C_NOMBRE";
    public static final String  COLUMN_NAME_CODIGO_MARC        = "D_CODIGO_MARC";
    public static final String  COLUMN_NAME_VALOR              = "T_VALOR";
    /**
     * Clave autonumérica secuencial sin significado funcional
     */
    private Long                id;

    /**
     * Nombre del subcampo
     */
    // private String nombre;
    /**
     * Correspondencia en el formato MARC del campo
     */
    private String              campoMarc;

    /**
     * Valor del campo. Se almacenará el código, no la descripción del subcampo
     * en los casos que aplique
     */
    private String              valor;

    /**
    *
    */
    private RegistroEtiqueta    registroEtiqueta;

    /**
   *
   */
    private AutoridadEtiqueta   autoridadEtiqueta;

    /**
     * Constructor sin parámetros
     */
    protected SubcampoMarc() {
        super();
    }

    /**
     * Constructor con parámetros
     * 
     * @param nombre
     * @param campoMarc
     * @param valor
     */
    public SubcampoMarc(final String campoMarc, final String valor) {
        super();

        this.campoMarc = campoMarc;
        this.valor = valor;
    }

    /**
     * @return the id
     */
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

    // /**
    // * @return the nombre
    // */
    // public String getNombre() {
    // return nombre;
    // }
    //
    // /**
    // * @param nombre
    // * the nombre to set
    // */
    // public void setNombre(final String nombre) {
    // this.nombre = nombre;
    // }

    /**
     * @return the campoMarc
     */
    public String getCampoMarc() {
        return campoMarc;
    }

    /**
     * @param campoMarc
     *            the campoMarc to set
     */
    public void setCampoMarc(final String campoMarc) {
        this.campoMarc = campoMarc;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(final String valor) {
        this.valor = valor;
    }

    /**
     * @return the registroEtiqueta
     */
    public RegistroEtiqueta getRegistroEtiqueta() {
        return registroEtiqueta;
    }

    /**
     * @param registroEtiqueta
     *            the registroEtiqueta to set
     */
    public void setRegistroEtiqueta(RegistroEtiqueta registroEtiqueta) {
        this.registroEtiqueta = registroEtiqueta;
    }

    /**
     * @return the autoridadEtiqueta
     */
    public AutoridadEtiqueta getAutoridadEtiqueta() {
        return autoridadEtiqueta;
    }

    /**
     * @param autoridadEtiqueta
     *            the autoridadEtiqueta to set
     */
    public void setAutoridadEtiqueta(AutoridadEtiqueta autoridadEtiqueta) {
        this.autoridadEtiqueta = autoridadEtiqueta;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        // result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutoridadSubcampo)) {
            return false;
        }
        final SubcampoMarc other = (SubcampoMarc) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        // if (nombre == null) {
        // if (other.nombre != null) {
        // return false;
        // }
        // } else if (!nombre.equals(other.nombre)) {
        // return false;
        // }
        if (valor == null) {
            if (other.valor != null) {
                return false;
            }
        } else if (!valor.equals(other.valor)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this).toString();

        // .append(AutoridadSubcampo.PROPTY_NAME_ID, this.id)
        //
        // // .append(AutoridadSubcampo.PROPTY_NAME_NOMBRE,
        // // (this.nombre == null) ? 0 : this.nombre)
        //
        // .append(AutoridadSubcampo.COLUMN_NAME_VALOR,
        // (this.valor == null) ? 0 : this.valor)
        //
        // .toString();
    }
}