package org.librae.catalogacion.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar un RegistroEtiqueta
 *
 * @author asalas
 * @version 1.0
 * @param <T>
 */
public abstract class EtiquetaMarc extends
        SpringRemotableLazyEntity<EtiquetaMarc> {

    /**
     *
     */
    private static final long  serialVersionUID        = 1L;

    /**
     * BaseObject es Serializable, por lo tanto RegistroEtiqueta necesita un
     * Serial Version UID
     */

    public static final String ENTITY_NAME             = "org.librae.catalogacion.model.EtiquetaMarc";

    /**
     * Constantes para referencia de los atributos de RegistroEtiqueta
     */
    public static final String PROPTY_NAME_ID          = "id";
    public static final String PROPTY_NAME_CODIGO      = "codigo";
    // public static final String PROPTY_NAME_INDICADOR_1 = "indicador1";
    // public static final String PROPTY_NAME_INDICADOR_2 = "indicador2";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    // public static final String COLUMN_NAME_ID = "X_REGISTRO_ETIQUETA_MARC";
    public static final String COLUMN_NAME_CODIGO      = "C_CODIGO";
    public static final String COLUMN_NAME_INDICADOR_1 = "N_INDICADOR_1";
    public static final String COLUMN_NAME_INDICADOR_2 = "N_INDICADOR_2";

    public static final String COLUMN_NAME_XML         = "T_XML";

    /**
     * Clave primaria autonumérica sin significado funcional
     */
    private Long               id;

    /**
     * Código de la etiqueta del registro
     */
    private String             codigo;

    /**
     * Indicador 1 de la etiqueta
     */
    private String             indicador1;

    /**
     * Indicador 2 de la etiqueta
     */
    private String             indicador2;

    /**
     * Representacion XML de los subcampos
     */
    private String             subcamposXML;

    /**
     * Orden establecido entre las estiquetas repetibles de un mismo registro
     */
    private Integer                     ordenRepetibilidad;

    /**
     * Constructor sin parámetros
     */
    protected EtiquetaMarc() {
        super();
    }

    /**
     * COnstructor con parámetros
     *
     * @param codigo
     * @param indicador1
     * @param indicador2
     */
    public EtiquetaMarc(final String codigo, final String indicador1,
            final String indicador2) {
        super();
        this.codigo = codigo;
        this.indicador1 = indicador1;
        this.indicador2 = indicador2;
    }

    /**
     * @param codigo
     * @param indicador1
     * @param indicador2
     * @param subcamposXML
     */
    public EtiquetaMarc(final String codigo, final String indicador1,
            final String indicador2, String subcamposXML) {
        super();
        this.codigo = codigo;
        this.indicador1 = indicador1;
        this.indicador2 = indicador2;
        this.subcamposXML = subcamposXML;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the indicador1
     */
    public String getIndicador1() {
        return this.indicador1;
    }

    /**
     * @param indicador1
     *            the indicador1 to set
     */
    public void setIndicador1(final String indicador1) {
        this.indicador1 = indicador1;
    }

    /**
     * @return the indicador2
     */
    public String getIndicador2() {
        return this.indicador2;
    }

    /**
     * @param indicador2
     *            the indicador2 to set
     */
    public void setIndicador2(final String indicador2) {
        this.indicador2 = indicador2;
    }

    public String getSubcamposXML() {
        return subcamposXML;
    }

    public void setSubcamposXML(String subcamposXML) {
        this.subcamposXML = subcamposXML;
    }

    public Integer getOrdenRepetibilidad() {
        return ordenRepetibilidad;
    }

    public void setOrdenRepetibilidad(Integer ordenRepetibilidad) {
        this.ordenRepetibilidad = ordenRepetibilidad;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        EtiquetaMarc other = (EtiquetaMarc) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (indicador1 == null) {
            if (other.indicador1 != null) {
                return false;
            }
        } else if (!indicador1.equals(other.indicador1)) {
            return false;
        }
        if (indicador2 == null) {
            if (other.indicador2 != null) {
                return false;
            }
        } else if (!indicador2.equals(other.indicador2)) {
            return false;
        }
        if (subcamposXML == null) {
            if (other.subcamposXML != null) {
                return false;
            }
        } else if (!subcamposXML.equals(other.subcamposXML)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this)

        .append(EtiquetaMarc.PROPTY_NAME_ID, this.id)

        .append(EtiquetaMarc.PROPTY_NAME_CODIGO,
                (this.codigo == null) ? 0 : this.codigo).toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result
                + ((indicador1 == null) ? 0 : indicador1.hashCode());
        result = prime * result
                + ((indicador2 == null) ? 0 : indicador2.hashCode());
        result = prime * result
                + ((subcamposXML == null) ? 0 : subcamposXML.hashCode());
        return result;
    }

}