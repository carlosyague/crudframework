package org.librae.circulacion.model.pib;

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
import org.librae.circulacion.model.ValorOET;
import org.librae.common.model.BaseObject;

/**
 * Tabla precargada.<br>
 * Valores para la precarga tomados de la definición del elemento de dato NCIP
 * Fiscal Action Type en [IMP1])
 *
 * @author asantamaria
 */
@Entity(name = TipoAccionTransaccionPIB.ENTITY_NAME)
@Table(name = TipoAccionTransaccionPIB.TABLE_NAME)
public class TipoAccionTransaccionPIB extends BaseObject {

    /**
     * BaseObject is Serializable, so TipoAccionTransaccionPIB needs a Serial
     * Version UID
     */
    private static final long   serialVersionUID                      = -2103435260916392427L;

    public static final String  ENTITY_NAME                           = "org.librae.circulacion.model.TipoAccionTransaccionPIB";
    public static final String  TABLE_NAME                            = "CIR_PIB_TIPOS_ACCIONES_TRANSAC";
    public static final String  ID_GENERATOR_NAME                     = "generator_cir_pib_tipos_acciones_transacciones";
    private static final String ID_SEQUENCE_NAME                      = "SEQ_CIR_PIB_TIPOS_ACC_TRANSAC";
    public static final String  COLUMN_NAME_ID                        = "X_PIB_TIPO_ACCION_TRANSACCION";
    public static final String  COLUMN_NAME_NOMBRE                    = "T_PIB_TIPO_ACCION_TRANSACCION";
    public static final String  COLUMN_NAME_DESCRIPCION_ALTERNATIVA   = "T_PIB_TIPO_ACCION_TRANSAC_ALT";
    public static final String  COLUMN_NAME_VALOR_OET                 = "NCIP_X_NCIP_VALUE_OET";

    public static final String  PROPERTY_NAME_ID                      = "id";
    public static final String  PROPERTY_NAME_NOMBRE                  = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION_ALTERNATIVA = "descripcionAlternativa";
    public static final String  PROPERTY_NAME_VALOR_OET               = "valorOET";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Nombre
     */
    private String              nombre;

    /**
     * Descripción ampliada o alternativa
     */
    private String              descripcionAlternativa;

    /**
     * >> NCIP_VALUES_OET<br>
     * Mapea esta fila en uno de los valores contemplados en NCIP
     */
    private ValorOET            valorOET;

    protected TipoAccionTransaccionPIB() {
        super();
    }

    public TipoAccionTransaccionPIB(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = TipoAccionTransaccionPIB.COLUMN_NAME_ID)
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
     * @return the nombre
     */
    @Column(name = TipoAccionTransaccionPIB.COLUMN_NAME_NOMBRE,length=80)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcionAlternativa
     */
    @Column(name = TipoAccionTransaccionPIB.COLUMN_NAME_DESCRIPCION_ALTERNATIVA)
    public String getDescripcionAlternativa() {
        return descripcionAlternativa;
    }

    /**
     * @param descripcionAlternativa
     *            the descripcionAlternativa to set
     */
    public void setDescripcionAlternativa(String descripcionAlternativa) {
        this.descripcionAlternativa = descripcionAlternativa;
    }

    /**
     * @return the valorOET
     */
    @ManyToOne(targetEntity = ValorOET.class, cascade = { CascadeType.PERSIST,
            CascadeType.ALL })
    @JoinColumn(name = TipoAccionTransaccionPIB.COLUMN_NAME_VALOR_OET, nullable = true)
    public ValorOET getValorOET() {
        return valorOET;
    }

    /**
     * @param valorOET
     *            the valorOET to set
     */
    public void setValorOET(ValorOET valorOET) {
        this.valorOET = valorOET;
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
        if (!(obj instanceof TipoAccionTransaccionPIB)) {
            return false;
        }

        final TipoAccionTransaccionPIB other = (TipoAccionTransaccionPIB) obj;

        if (this.getNombre() == null && other.getNombre() != null) {
            return false;
        }
        if (this.getNombre() != null
                && !this.getNombre().equals(other.getNombre())) {
            return false;
        }

        if (this.getValorOET() == null && other.getValorOET() != null) {
            return false;
        }
        if (this.getValorOET() != null
                && !this.getValorOET().equals(other.getValorOET())) {
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
        result += ((id == null) ? 0 : this.getId().hashCode());
        result = prime
                * result
                + ((this.getNombre() == null) ? 0 : this.getNombre().hashCode());

        result = prime
                * result
                + ((this.getValorOET() == null) ? 0 : this.getValorOET()
                        .hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, this.getId())

        .append(PROPERTY_NAME_NOMBRE,
                (this.getNombre() == null) ? "" : this.getNombre().toString())

        .append(
                PROPERTY_NAME_DESCRIPCION_ALTERNATIVA,
                (this.getDescripcionAlternativa() == null) ? "" : this
                        .getDescripcionAlternativa().toString())

        .append(
                PROPERTY_NAME_VALOR_OET,
                (this.getValorOET() == null) ? "" : this.getValorOET()
                        .toString())

        .toString();
    }

}