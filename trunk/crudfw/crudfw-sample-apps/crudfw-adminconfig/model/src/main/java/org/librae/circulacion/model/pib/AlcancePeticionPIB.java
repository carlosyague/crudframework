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
 *Alcance de una petición.<br>
 * Tabla precargada.<br>
 * Para la precarga se usan los valores definidos para el elemento de dato
 * RequestScopeType de NCIP ( ver [IMP1]):<br>
 * <br>
 * Ejemplar (Item)<br>
 * Registro BIbliográfico (Bibliographic Item)
 *
 * @author asantamaria
 */
@Entity(name = AlcancePeticionPIB.ENTITY_NAME)
@Table(name = AlcancePeticionPIB.TABLE_NAME)
public class AlcancePeticionPIB extends BaseObject {

    /**
     * BaseObject is Serializable, so AlcancePeticionPIB needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID                      = 4732257131697610077L;

    public static final String  ENTITY_NAME                           = "org.librae.circulacion.model.AlcancePeticionPIB";
    public static final String  TABLE_NAME                            = "CIR_PIB_ALCANCES_PETICIONES";
    public static final String  ID_GENERATOR_NAME                     = "generator_cir_pib_alcances_peticiones";
    private static final String ID_SEQUENCE_NAME                      = "SEQ_CIR_PIB_ALCANCES_PET";
    public static final String  COLUMN_NAME_ID                        = "X_PIB_ALCANCES_PETICION";
    public static final String  COLUMN_NAME_NOMBRE                    = "T_PIB_ALCANCES_PETICION";
    public static final String  COLUMN_NAME_DESCRIPCION_ALTERNATIVA   = "T_PIB_ALCANCES_PETICION_ALT";
    public static final String  COLUMN_NAME_VALOR_OET                 = "NCIP_X_NCIP_VALUE_OET";

    public static final String  PROPERTY_NAME_ID                      = "id";
    public static final String  PROPERTY_NAME_NOMBRE                  = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION_ALTERNATIVA = "descripcionAlternativa";
    public static final String  PROPERTY_NAME_VALOR_OET               = "valorOET";

    /**
     * Identificador interno del parámetro
     */
    private Long                id;

    /**
     * Nombre por el que se conoce a un alcance de petición
     */
    private String              nombre;

    /**
     * Descripción ampliada o alternativa de un alcance de petición
     */
    private String              descripcionAlternativa;

    /**
     * >> NCIP_VALUES_OET<br>
     * Mapea este alcance de petición en una de las contempladas en NCIP
     */
    private ValorOET            valorOET;

    protected AlcancePeticionPIB() {
        super();
    }

    public AlcancePeticionPIB(String nombre) {
        super();
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = AlcancePeticionPIB.COLUMN_NAME_ID)
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
    @Column(name = AlcancePeticionPIB.COLUMN_NAME_NOMBRE,length=80)
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
    @Column(name = AlcancePeticionPIB.COLUMN_NAME_DESCRIPCION_ALTERNATIVA)
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
    @JoinColumn(name = AlcancePeticionPIB.COLUMN_NAME_VALOR_OET)
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
        if (!(obj instanceof AlcancePeticionPIB)) {
            return false;
        }

        final AlcancePeticionPIB other = (AlcancePeticionPIB) obj;

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