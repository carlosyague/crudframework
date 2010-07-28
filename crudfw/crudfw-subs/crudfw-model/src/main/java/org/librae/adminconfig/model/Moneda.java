package org.librae.adminconfig.model;

import java.util.Date;

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
import org.librae.common.model.BaseObject;

/**
 * Códigos de monedas en el estándar ISO 4217.<br>
 * Precargada con el listado de la página
 * http://www.iso.org/iso/support/faqs/faqs_widely_used_standards
 * /widely_used_standards_other/currency_codes/currency_codes_list-1.htm y la
 * sección 6 del estándar.<br>
 * <br>
 * Lo que sigue es un extracto del apartado 5.4 Representation of Monetary
 * Quantities, el estándar IMP1, que explica como el estándar ISO 4217 establece
 * que se deben expresar las cantidades monetarias:<br>
 * <br>
 * ... currencies of the world be identified and represented according to<br>
 * ISO 4217-1995 (see Section 3). This means that to specify monetary quantities
 * fully, both a<br>
 * three-character currency code, from ISO 4217, and an integer value must be
 * used. The<br>
 * integer value is based on the minor denomination of the specific currency.
 * For example, the<br>
 * currencies of Canada (the dollar), Egypt (the pound), and Bahrain (dinar) are
 * represented by<br>
 * the three-character codes: CAD, EGP, and BHD, respectively. The minor units
 * of each of<br>
 * these currencies are 1/100, 1/100, and 1/1000, respectively, of the major
 * unit. ISO 4217<br>
 * specifies the representation of a monetary quantity as follows: an integer
 * expressed as<br>
 * positive, negative or zero, obtained by multiplying an amount expressed in
 * the major unit (i.e.,<br>
 * expressed as a rational number) by ten to the power M, where M is the value
 * of the minor<br>
 * unit for that currency as defined in ISO 4217, Section 6. For example, 9.53
 * Canadian dollars<br>
 * would be represented as 9.53 x 102 (M = 2 for Canadian dollars), or 953.
 * Similarly, 12.98<br>
 * Egyptian pounds would be represented as 12.98 x 102 (M = 2 for Egyptian
 * pounds), or 1298.<br>
 * As a final example, 16.750 Bahraini dinars would be represented as 16.750 x
 * 103 (M = 3 for<br>
 * Bahraini dinars), or 16750.
 *
 * @author asantamaria
 */
@Entity(name = Moneda.ENTITY_NAME)
@Table(name = Moneda.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Moneda extends BaseObject {

    /**
     * BaseObject is Serializable, so Moneda needs a Serial Version UID
     */
    private static final long   serialVersionUID                = 4650387846836284519L;

    public static final String  ENTITY_NAME                     = "org.librae.adminconfig.model.Moneda";
    public static final String  TABLE_NAME                      = "ADM_MONEDAS";
    public static final String  ID_GENERATOR_NAME               = "generator_adm_monedas";
    private static final String ID_SEQUENCE_NAME                = "SEQ_ADM_MONEDAS";
    public static final String  COLUMN_NAME_ID                  = "X_MONEDA";
    public static final String  COLUMN_NAME_CODIGO_ALFABETICO   = "C_CODIGO_ALFABETICO";
    public static final String  COLUMN_NAME_CODIGO_NUMERICO     = "N_CODIGO_NUMERICO";
    public static final String  COLUMN_NAME_NOMBRE              = "T_MONEDA";
    public static final String  COLUMN_NAME_PAIS                = "PAIS_X_PAIS";
    public static final String  COLUMN_NAME_POTENCIA_M          = "N_POTENCIA_M";
    public static final String  COLUMN_NAME_FECHA_OBSOLETA      = "F_OBSOLETA";

    public static final String  PROPERTY_NAME_ID                = "id";
    public static final String  PROPERTY_NAME_CODIGO_ALFABETICO = "codigoAlfabetico";
    public static final String  PROPERTY_NAME_CODIGO_NUMERICO   = "codigoNumerico";
    public static final String  PROPERTY_NAME_NOMBRE            = "nombre";
    public static final String  PROPERTY_NAME_PAIS              = "pais";
    public static final String  PROPERTY_NAME_POTENCIA_M        = "potenciaM";
    public static final String  PROPERTY_NAME_FECHA_OBSOLETA    = "fechaObsoleta";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Código de moneda de tres letras según estándar ISO 4217
     */
    private String              codigoAlfabetico;

    /**
     * Código numérico de la moneda según estándar ISO 4217
     */
    private String              codigoNumerico;

    /**
     * Nombre de moneda. Ejemplo: "Dihram de los Emiratos Arabes Unidos"
     */
    private String              nombre;

    /**
     * Referencia al pais al que corresponde la moneda.<br>
     * Antes era solo un texto:<br>
     * Nombre de pais al que corresponde la moneda, según estándar ISO 4712
     */
    private Pais                pais;

    /**
     * ... by ten to the power M, where M is the value of the minor unit for
     * that currency as defined in ISO 4217, Section 6 ...
     */
    private Long                potenciaM;

    /**
     * Fecha desde la que el código de moneda está obsoleto, y no se debe de
     * usar
     */
    private Date                fechaObsoleta;

    protected Moneda() {
        super();
    }

    public Moneda(String codigoAlfabetico, String codigoNumerico,
            String nombre, Pais pais) {
        super();
        this.codigoAlfabetico = codigoAlfabetico;
        this.codigoNumerico = codigoNumerico;
        this.nombre = nombre;
        this.pais = pais;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Moneda.COLUMN_NAME_ID)
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
     * @return the codigoAlfabetico
     */
    @Column(name = Moneda.COLUMN_NAME_CODIGO_ALFABETICO, nullable = false,length=40)
    public String getCodigoAlfabetico() {
        return codigoAlfabetico;
    }

    /**
     * @param codigoAlfabetico
     *            the codigoAlfabetico to set
     */
    public void setCodigoAlfabetico(String codigoAlfabetico) {
        this.codigoAlfabetico = codigoAlfabetico;
    }

    /**
     * @return the codigoNumerico
     */
    @Column(name = Moneda.COLUMN_NAME_CODIGO_NUMERICO, nullable = false,length=40)
    public String getCodigoNumerico() {
        return codigoNumerico;
    }

    /**
     * @param codigoNumerico
     *            the codigoNumerico to set
     */
    public void setCodigoNumerico(String codigoNumerico) {
        this.codigoNumerico = codigoNumerico;
    }

    /**
     * @return the nombre
     */
    @Column(name = Moneda.COLUMN_NAME_NOMBRE, nullable = false,length=40)
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
     * @return the pais
     */
    @ManyToOne(targetEntity = Pais.class, cascade = { CascadeType.PERSIST,
            CascadeType.ALL })
    @JoinColumn(name = Moneda.COLUMN_NAME_PAIS, nullable = true)
    public Pais getPais() {
        return pais;
    }

    /**
     * @param pais
     *            the pais to set
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * @return the potenciaM
     */
    @Column(name = Moneda.COLUMN_NAME_POTENCIA_M)
    public Long getPotenciaM() {
        return potenciaM;
    }

    /**
     * @param potenciaM
     *            the potenciaM to set
     */
    public void setPotenciaM(Long potenciaM) {
        this.potenciaM = potenciaM;
    }

    /**
     * @return the fechaObsoleta
     */
    @Column(name = Moneda.COLUMN_NAME_FECHA_OBSOLETA)
    public Date getFechaObsoleta() {
        return fechaObsoleta;
    }

    /**
     * @param fechaObsoleta
     *            the fechaObsoleta to set
     */
    public void setFechaObsoleta(Date fechaObsoleta) {
        this.fechaObsoleta = fechaObsoleta;
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
        if (!(obj instanceof Moneda)) {
            return false;
        }

        final Moneda other = (Moneda) obj;

        if (this.getCodigoAlfabetico() == null
                && other.getCodigoAlfabetico() != null) {
            return false;
        }
        if (this.getCodigoAlfabetico() != null
                && !this.getCodigoAlfabetico().equals(
                        other.getCodigoAlfabetico())) {
            return false;
        }

        if (this.getCodigoNumerico() == null
                && other.getCodigoNumerico() != null) {
            return false;
        }
        if (this.getCodigoNumerico() != null
                && !this.getCodigoNumerico().equals(other.getCodigoNumerico())) {
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
                + ((this.getCodigoAlfabetico() == null) ? 0 : this
                        .getCodigoAlfabetico().hashCode());

        result = prime
                * result
                + ((this.getCodigoNumerico() == null) ? 0 : this
                        .getCodigoNumerico().hashCode());

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
                this.COLUMN_NAME_CODIGO_ALFABETICO,
                (this.getCodigoAlfabetico() == null) ? 0 : this
                        .getCodigoAlfabetico().toString())

        .append(
                this.COLUMN_NAME_CODIGO_NUMERICO,
                (this.getCodigoNumerico() == null) ? 0 : this
                        .getCodigoNumerico().toString())

        .append(this.COLUMN_NAME_PAIS,
                (this.getPais() == null) ? 0 : this.getPais().toString())

        .toString();
    }

}