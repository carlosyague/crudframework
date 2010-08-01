package org.librae.adminconfig.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.ForeignKey;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Requisito de almacenamiento para albergar los parámetros de ejecución propios
 * de cada biblioteca
 *
 * @author asantamaria
 */
@Entity(name = ParametroBiblioteca.ENTITY_NAME)
@Table(name = ParametroBiblioteca.TABLE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = {
        ParametroBiblioteca.COLUMN_NAME_BIBLIOTECA,
        ParametroBiblioteca.COLUMN_NAME_PARAMETRO }) })
public class ParametroBiblioteca extends BaseObject {

    /**
     * BaseObject is Serializable, so ParametroBiblioteca needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID             = -8421843461518073912L;

    public static final String  ENTITY_NAME                  = "org.librae.adminconfig.model.ParametroBiblioteca";
    public static final String  TABLE_NAME                   = "ADM_PARAMETRO_X_BIBLIOTECA";
    public static final String  ID_GENERATOR_NAME            = "generator_adm_parametro_x_biblioteca";
    private static final String ID_SEQUENCE_NAME             = "SEQ_ADM_PARAMETRO_X_BIBLIOTECA";
    public static final String  COLUMN_NAME_ID               = "X_PARAMXBIBLIO";
    public static final String  COLUMN_NAME_BIBLIOTECA       = "BIB_X_BIBLIOTECA";
    public static final String  COLUMN_NAME_PARAMETRO        = "PAR_X_PARAMETRO";
    public static final String  COLUMN_NAME_VALOR            = "T_VALOR";
    public static final String  COLUMN_NAME_NOMBRE_ARCHIVO   = "T_NOMBREBLOB";
    public static final String  COLUMN_NAME_VALOR_ARCHIVO    = "T_VALORBLOB";

    public static final String  PROPERTY_NAME_ID             = "id";
    public static final String  PROPERTY_NAME_BIBLIOTECA     = "biblioteca";
    public static final String  PROPERTY_NAME_PARAMETRO      = "parametro";
    public static final String  PROPERTY_NAME_VALOR          = "valor";
    public static final String  PROPERTY_NAME_NOMBRE_ARCHIVO = "nombreArchivo";
    public static final String  PROPERTY_NAME_VALOR_ARCHIVO  = "valorArchivo";

    /**
     * Identificador interno del parámetro
     */
    private Long                id;

    /**
     * Identificador de la biblioteca
     */
    private Biblioteca          biblioteca;

    /**
     * Identificador del parámetro
     */
    private Parametro           parametro;

    /**
     * Valor del parámetro para la biblioteca. Este atributo se utilizará cuando
     * el valor de parámetro sea de tipo simple: Entero, cadena, fecha etc. En
     * tal caso los atributos T_VALORBLOB y T_NOMBREBLOB serán nulos
     */
    private String              valor;

    /**
     * En el caso de que el valor del parámetro sea un archivo almacenado en el
     * atributo T_VALORBLOB, aquí se almacenará el nombre del archivo
     */
    private String              nombreArchivo;

    /**
     * Valor del parámetro en la biblioteca. Este atributo se utilizará cuando
     * el valor del parámetro sea algún tipo de archivo: XML, XSD, jrxml, etc.
     * En tal caso el atributo T_VALOR será nulo
     */
    private byte[]              valorArchivo;

    protected ParametroBiblioteca() {
        super();
    }

    public ParametroBiblioteca(Parametro parametro, Biblioteca biblioteca) {
        super();
        this.parametro = parametro;
        this.biblioteca = biblioteca;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = ParametroBiblioteca.COLUMN_NAME_ID)
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
     * @return the biblioteca
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = ParametroBiblioteca.COLUMN_NAME_BIBLIOTECA, nullable = false)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_PAR_B")
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca
     *            the biblioteca to set
     */
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    /**
     * @return the parametro
     */
    @ManyToOne(targetEntity = Parametro.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = ParametroBiblioteca.COLUMN_NAME_PARAMETRO, nullable = false)
    public Parametro getParametro() {
        return parametro;
    }

    /**
     * @param parametro
     *            the parametro to set
     */
    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    /**
     * @return the valor
     */
    @Column(name = ParametroBiblioteca.COLUMN_NAME_VALOR)
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
    @Column(name = ParametroBiblioteca.COLUMN_NAME_NOMBRE_ARCHIVO,length=80)
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
    @Column(name = ParametroBiblioteca.COLUMN_NAME_VALOR_ARCHIVO)
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
        if (!(obj instanceof ParametroBiblioteca)) {
            return false;
        }

        final ParametroBiblioteca other = (ParametroBiblioteca) obj;

        if (getParametro() == null && other.getParametro() != null) {
            return false;
        }
        if (getParametro() != null
                && !getParametro().equals(other.getParametro())) {
            return false;
        }

        if (getBiblioteca() == null && other.getBiblioteca() != null) {
            return false;
        }
        if (getBiblioteca() != null
                && !getBiblioteca().equals(other.getBiblioteca())) {
            return false;
        }

        if (getValor() == null && other.getValor() != null) {
            return false;
        }
        if (getValor() != null
                && !getValor().equals(other.getValor())) {
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
                + ((getParametro() == null) ? 0 : getParametro()
                        .hashCode());

        result = prime
                * result
                + ((getBiblioteca() == null) ? 0 : getBiblioteca()
                        .hashCode());

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

        .append(PROPERTY_NAME_ID, getId())

        .append(
                PROPERTY_NAME_PARAMETRO,
                (getParametro() == null) ? "" : getParametro()
                        .toString())

        .append(
                PROPERTY_NAME_BIBLIOTECA,
                (getBiblioteca() == null) ? "" : getBiblioteca()
                        .toString())

        .append(PROPERTY_NAME_VALOR,
                (getValor() == null) ? "" : getValor().toString())

        .toString();
    }

}