package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import javax.persistence.Transient;
import org.librae.common.model.BaseObject;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * Codigo del servicios de NCIP.
 * 
 * @author jcisneros
 */
@Entity(name = CodigoNcipServicio.ENTITY_NAME)
@Table(name = CodigoNcipServicio.TABLE_NAME)
public class CodigoNcipServicio extends BaseObject {

    /**
     * BaseObject is Serializable, so Consorcio needs a Serial Version UID
     */
    private static final long   serialVersionUID          = -5228076118887814317L;

    public static final String  ENTITY_NAME               = "org.librae.circulacion.model.CodigoNcipServicio";
    public static final String  TABLE_NAME                = "CIR_CODIGO_NCIP_SERVICIOS";
    public static final String  ID_GENERATOR_NAME         = "generator_cir_codigoNcipServicios";
    private static final String ID_SEQUENCE_NAME          = "SEQ_CIR_CODIGO_NCIP";

    public static final String  COLUMN_NAME_ID            = "X_NCIP_SERVICIOS";
    public static final String  COLUMN_NAME_CODIGO        = "C_NCIP_SERVICIO";
    public static final String  COLUMN_NAME_DESCRIPCION   = "T_NCIP_SERVICIO";

    public static final String  PROPERTY_NAME_ID          = "id";
    public static final String  PROPERTY_NAME_CODIGO      = "codigo";
    public static final String  PROPERTY_NAME_DESCRIPCION = "descripcion";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Nombre de servicio NCIP, sin espacios intermedios, tal como se
     * especifican en el documento ncip_v1_01.dtd. Por ejemplo: 'CheckOutItem'
     */
    private String              codigo;

    private String              descripcion;

    protected CodigoNcipServicio() {
        super();
    }

    public CodigoNcipServicio(String codigo, String descripcion) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public CodigoNcipServicio(String codigo) {
        super();
        this.codigo = codigo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = CodigoNcipServicio.COLUMN_NAME_ID)
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
    @Column(name = CodigoNcipServicio.COLUMN_NAME_CODIGO, length = 40)
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
     * Traduce el campo descripcion.
     * 
     * @return
     */
    @Transient
    public String getDescripcionLocale() {
        return ComboLocaleManager.getOptional(getDescripcion());
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof CodigoNcipServicio)) {
            return false;
        }

        final CodigoNcipServicio other = (CodigoNcipServicio) obj;

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null && !getCodigo().equals(other.getCodigo())) {
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
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(PROPERTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString()).append(
                PROPERTY_NAME_DESCRIPCION,
                (getCodigo() == null) ? "" : getDescripcion().toString())

        .toString();
    }

    /**
     * @return the descripcion
     */
    @Column(name = CodigoNcipServicio.COLUMN_NAME_DESCRIPCION, length = 80)
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
}