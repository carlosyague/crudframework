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
 * Tabla precargada. Valores para la precarga tomados de la definición del
 * elemento de dato NCIP Fiscal Transaction Type en [IMP1])
 *
 * @author asantamaria
 */
@Entity(name = ConceptoTransaccionPIB.ENTITY_NAME)
@Table(name = ConceptoTransaccionPIB.TABLE_NAME)
public class ConceptoTransaccionPIB extends BaseObject {

    /**
     * BaseObject is Serializable, so ConceptoTransaccionPIB needs a Serial
     * Version UID
     */
    private static final long   serialVersionUID        = 4191201607795744950L;

    public static final String  ENTITY_NAME             = "org.librae.circulacion.model.ConceptoTransaccionPIB";
    public static final String  TABLE_NAME              = "CIR_PIB_CONCEPTOS_TRANSACC";
    public static final String  ID_GENERATOR_NAME       = "generator_cir_pib_conceptos_transacciones";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CIR_PIB_CONCEPTOS_TRANSAC";
    public static final String  COLUMN_NAME_ID          = "X_PIB_CONCEPTO_TRANSACCION";
    public static final String  COLUMN_NAME_NOMBRE      = "T_PIB_CONCEPTO_TRANSACCION";
    public static final String  COLUMN_NAME_DESCRIPCION = "T_PIB_CONCEPTO_TRANSACCION_ALT";
    public static final String  COLUMN_NAME_VALOR_OET   = "NCIP_X_NCIP_VALUE_OET";

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
    private String              descripcion;

    /**
     * >> NCIP_VALUES_OET Mapea esta fila en uno de los valores contemplados en
     * NCIP
     */
    private ValorOET            valorOET;

    protected ConceptoTransaccionPIB() {
        super();
    }

    public ConceptoTransaccionPIB(String nombre) {
        super();
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = ConceptoTransaccionPIB.COLUMN_NAME_ID)
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
    @Column(name = ConceptoTransaccionPIB.COLUMN_NAME_NOMBRE,length=80)
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
     * @return the descripcion
     */
    @Column(name = ConceptoTransaccionPIB.COLUMN_NAME_DESCRIPCION)
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
     * @return the valorOET
     */
    @ManyToOne(targetEntity = ValorOET.class, cascade = { CascadeType.PERSIST,
            CascadeType.ALL })
    @JoinColumn(name = ConceptoTransaccionPIB.COLUMN_NAME_VALOR_OET, nullable = true)
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
        if (!(obj instanceof ConceptoTransaccionPIB)) {
            return false;
        }

        final ConceptoTransaccionPIB other = (ConceptoTransaccionPIB) obj;

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

        .append(this.COLUMN_NAME_ID, this.getId())

        .append(this.COLUMN_NAME_NOMBRE,
                (this.getNombre() == null) ? 0 : this.getNombre().toString())

        .append(
                this.COLUMN_NAME_VALOR_OET,
                (this.getValorOET() == null) ? 0 : this.getValorOET()
                        .toString())

        .toString();
    }

}