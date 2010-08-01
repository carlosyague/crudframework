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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.circulacion.model.ValorOET;
import org.librae.common.model.BaseObject;

/**
 * Estados en los que puede encontrarse una petición de PIB<br>
 * Tabla precargada<br>
 * Datos para la precarga tomados de los valores del elemento de dato Requst
 * Status Type de NCIP (ver [IMP1]):<br>
 * <br>
 * Disponible para préstamo (Available For Pickup)<br>
 * Biblioteca prestamista no puede realizar el préstamo (Cannot Fulfill Request)<br>
 * Expirado (Expired)<br>
 * Tramitándose (In Process)<br>
 * Se necesita aceptación por el lector (Need to Accept Conditions)<br>
 * Pedido vía PIB (Requested Via ILL)
 * 
 * @author asantamaria
 */
@Entity(name = EstadoPeticionPIB.ENTITY_NAME)
@Table(name = EstadoPeticionPIB.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class EstadoPeticionPIB extends BaseObject {

    /**
     * BaseObject is Serializable, so EstadoPeticionPIB needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID                      = -1135224270185807912L;

    public static final String  ENTITY_NAME                           = "org.librae.circulacion.model.EstadoPeticionPIB";
    public static final String  TABLE_NAME                            = "CIR_PIB_ESTADOS_PETICIONES";
    public static final String  ID_GENERATOR_NAME                     = "generator_cir_pib_estados_peticiones";
    private static final String ID_SEQUENCE_NAME                      = "SEQ_CIR_PIB_ESTADOS_PETICIONES";
    public static final String  COLUMN_NAME_ID                        = "X_PIB_ESTADO_PETICION";
    public static final String  COLUMN_NAME_NOMBRE                    = "T_PIB_ESTADO_PETICION";
    public static final String  COLUMN_NAME_DESCRIPCION_ALTERNATIVA   = "T_PIB_ESTADO_PETICION_ALT";
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
     * Nombre por el que se conoce a un estado de petición
     */
    private String              nombre;

    /**
     * Descripción ampliada o alternativa de un estado de petición
     */
    private String              descripcionAlternativa;

    /**
     * >> NCIP_VALUES_OET<br>
     * Mapea este estado de petición en una de las contempladas en NCIP
     */
    private ValorOET            valorOET;

    protected EstadoPeticionPIB() {
        super();
    }

    public EstadoPeticionPIB(String nombre) {
        super();
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = EstadoPeticionPIB.COLUMN_NAME_ID)
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
    @Column(name = EstadoPeticionPIB.COLUMN_NAME_NOMBRE, length = 80)
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
    @Column(name = EstadoPeticionPIB.COLUMN_NAME_DESCRIPCION_ALTERNATIVA)
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
    @JoinColumn(name = EstadoPeticionPIB.COLUMN_NAME_VALOR_OET)
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
        if (!(obj instanceof EstadoPeticionPIB)) {
            return false;
        }

        final EstadoPeticionPIB other = (EstadoPeticionPIB) obj;

        if (getNombre() == null && other.getNombre() != null) {
            return false;
        }
        if (getNombre() != null
                && !getNombre().equals(other.getNombre())) {
            return false;
        }

        if (getValorOET() == null && other.getValorOET() != null) {
            return false;
        }
        if (getValorOET() != null
                && !getValorOET().equals(other.getValorOET())) {
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
        result = prime
                * result
                + ((getNombre() == null) ? 0 : getNombre().hashCode());

        result = prime
                * result
                + ((getValorOET() == null) ? 0 : getValorOET()
                        .hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(PROPERTY_NAME_NOMBRE,
                (getNombre() == null) ? "" : getNombre().toString())

        .append(
                PROPERTY_NAME_DESCRIPCION_ALTERNATIVA,
                (getDescripcionAlternativa() == null) ? "" : getDescripcionAlternativa().toString())

        .append(
                PROPERTY_NAME_VALOR_OET,
                (getValorOET() == null) ? "" : getValorOET()
                        .toString())

        .toString();
    }

}