package org.librae.circulacion.model;

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

import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Valores de los Open Enumerated Types que se usan en la aplicación<br>
 * Las filas de esta tabla se precargan con los valores de los schemes
 * especificados en IMP1, que implemente nuestra aplicación.<br>
 * <br>
 * IMP1 = Circulation Interchange Part 2: Protocol Implementation Profile 1<br>
 * <br>
 * Ejemplo de un valor de "Agency User Privilege Type" (IMP1 pag. 28):<br>
 * <br>
 * T_VALUE = 'Barcode Id'<br>
 * T_TRADUCCION = 'Código de barras'<br>
 * T_DESCRIPCION = 'Printed and variously patterned bars, spaces and sometimes
 * numerals,<br>
 * designed to be scanned and read into computer memory, and used as input<br>
 * for User authentication.'
 * 
 * @author asantamaria
 */
@Entity(name = ValorOET.ENTITY_NAME)
@Table(name = ValorOET.TABLE_NAME)
public class ValorOET extends SpringRemotableLazyEntity<ValorOET> {

    /**
     * BaseObject is Serializable, so ValorOET needs a Serial Version UID
     */
    private static final long   serialVersionUID             = -2053232069137377403L;

    public static final String  ENTITY_NAME                  = "org.librae.circulacion.model.ValorOET";
    public static final String  TABLE_NAME                   = "NCIP_VALUES_OET";
    public static final String  ID_GENERATOR_NAME            = "generator_ncip_values_oet";
    private static final String ID_SEQUENCE_NAME             = "SEQ_NCIP_VALUES_OET";
    public static final String  COLUMN_NAME_ID               = "X_NCIP_VALUE_OET";
    public static final String  COLUMN_NAME_VALOR_INGLES     = "T_VALUE";
    public static final String  COLUMN_NAME_VALOR_CASTELLANO = "T_TRADUCCION";
    public static final String  COLUMN_NAME_DESCRIPCION      = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_ESQUEMA          = "NCIP_SCH_X_NCIP_SCHEME_OET";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Value de un scheme, en inglés, tal como se especifican en IMP1
     */
    private String              valorEnIngles;

    /**
     * Traducción al castellano de T_VALUE
     */
    private String              valorEnCastellano;

    /**
     * Descripcion del value, según se especifica en IMP1, Appendix C
     */
    private String              descripcion;

    /**
     * Clave ajena a NCIP_SCHEMES_OPEN_ENUM_TYPE<br>
     * Indica a que scheme de NCIP pertenece el value que representa esta fila
     */
    private EsquemaOET          esquema;

    protected ValorOET() {
        super();
    }

    public ValorOET(String valorEnIngles) {
        super();
        this.valorEnIngles = valorEnIngles;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = ValorOET.COLUMN_NAME_ID)
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
     * @return the valorEnIngles
     */
    @Column(name = ValorOET.COLUMN_NAME_VALOR_INGLES)
    public String getValorEnIngles() {
        return valorEnIngles;
    }

    /**
     * @param valorEnIngles
     *            the valorEnIngles to set
     */
    public void setValorEnIngles(String valorEnIngles) {
        this.valorEnIngles = valorEnIngles;
    }

    /**
     * @return the valorEnCastellano
     */
    @Column(name = ValorOET.COLUMN_NAME_VALOR_CASTELLANO)
    public String getValorEnCastellano() {
        return valorEnCastellano;
    }

    /**
     * @param valorEnCastellano
     *            the valorEnCastellano to set
     */
    public void setValorEnCastellano(String valorEnCastellano) {
        this.valorEnCastellano = valorEnCastellano;
    }

    /**
     * @return the descripcion
     */
    @Column(name = ValorOET.COLUMN_NAME_DESCRIPCION)
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
     * @return the esquema
     */
    @ManyToOne(targetEntity = EsquemaOET.class, cascade = {
            CascadeType.PERSIST, CascadeType.ALL })
    @JoinColumn(name = ValorOET.COLUMN_NAME_ESQUEMA, nullable = true)
    public EsquemaOET getEsquema() {
        return esquema;
    }

    /**
     * @param esquema
     *            the esquema to set
     */
    public void setEsquema(EsquemaOET esquema) {
        this.esquema = esquema;
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
        if (!(obj instanceof ValorOET)) {
            return false;
        }

        final ValorOET other = (ValorOET) obj;

        if (getValorEnIngles() == null && other.getValorEnIngles() != null) {
            return false;
        }
        if (getValorEnIngles() != null
                && !getValorEnIngles().equals(other.getValorEnIngles())) {
            return false;
        }

        if (getValorEnCastellano() == null
                && other.getValorEnCastellano() != null) {
            return false;
        }
        if (getValorEnCastellano() != null
                && !getValorEnCastellano().equals(
                        other.getValorEnCastellano())) {
            return false;
        }

        if (getEsquema() == null && other.getEsquema() != null) {
            return false;
        }
        if (getEsquema() != null
                && !getEsquema().equals(other.getEsquema())) {
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
                + ((getValorEnIngles() == null) ? 0 : getValorEnIngles().hashCode());

        result = prime
                * result
                + ((getValorEnCastellano() == null) ? 0 : getValorEnCastellano().hashCode());

        result = prime
                * result
                + ((getEsquema() == null) ? 0 : getEsquema()
                        .hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(COLUMN_NAME_ID, getId())

        .append(
                COLUMN_NAME_VALOR_INGLES,
                (getValorEnIngles() == null) ? 0 : getValorEnIngles()
                        .toString())

        .append(
                COLUMN_NAME_VALOR_CASTELLANO,
                (getValorEnCastellano() == null) ? 0 : getValorEnCastellano().toString())

        .append(COLUMN_NAME_ESQUEMA,
                (getEsquema() == null) ? 0 : getEsquema().toString())

        .toString();
    }

    @Override
    public ValorOET newInstance() {

        return new ValorOET();
    }

}