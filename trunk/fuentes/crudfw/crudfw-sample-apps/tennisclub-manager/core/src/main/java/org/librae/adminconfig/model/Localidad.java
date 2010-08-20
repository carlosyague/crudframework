package org.librae.adminconfig.model;

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
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * @author asantamaria
 */
@Entity(name = Localidad.ENTITY_NAME)
@Table(name = Localidad.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Localidad extends SpringRemotableLazyEntity<Localidad> {

    /**
     * BaseObject is Serializable, so Localidad needs a Serial Version UID
     */
    private static final long   serialVersionUID      = -8790071423537368004L;

    public static final String  ENTITY_NAME           = "org.librae.adminconfig.model.Localidad";
    public static final String  TABLE_NAME            = "ADM_LOCALIDADES";
    public static final String  ID_GENERATOR_NAME     = "generator_adm_localidades";
    private static final String ID_SEQUENCE_NAME      = "SEQ_ADM_LOCALIDADES";
    public static final String  COLUMN_NAME_ID        = "X_LOCALIDAD";
    public static final String  COLUMN_NAME_CODIGO    = "C_INE_LOCALIDAD";
    public static final String  COLUMN_NAME_NOMBRE    = "T_LOCALIDAD";
    public static final String  COLUMN_NAME_BAJA      = "L_BAJA";
    public static final String  COLUMN_NAME_MUNICIPIO = "L_MUNICIPIO";

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                id;

    /**
     * Código INE de la localidad
     */
    private String              codigo;

    /**
     * Nombre de la localidad
     */
    private String              nombre;

    /**
     * Si o No
     */
    private Boolean             baja;

    /**
     * Si o No (Es municipio)
     */
    private Boolean             esMunicipio;
    /**
     * Municipio a la que pertenece la localidad
     */
    private Municipio           municipio;

    protected Localidad() {
        super();
    }

    public Localidad(String codigo, String nombre) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Localidad.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the codigo
     */
    @Column(name = Localidad.COLUMN_NAME_CODIGO, length = 20)
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
     * @return the nombre
     */
    @Column(name = Localidad.COLUMN_NAME_NOMBRE, length = 80)
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
     * @return the baja
     */
    @Column(name = Localidad.COLUMN_NAME_BAJA)
    public Boolean isBaja() {
        return baja;
    }

    /**
     * @param baja
     *            the baja to set
     */
    public void setBaja(Boolean baja) {
        this.baja = baja;
    }

    /**
     * @return the esMunicipio
     */
    @Column(name = Localidad.COLUMN_NAME_MUNICIPIO)
    public Boolean isEsMunicipio() {
        return esMunicipio;
    }

    /**
     * @param esMunicipio
     *            the esMunicipio to set
     */
    public void setEsMunicipio(Boolean esMunicipio) {
        this.esMunicipio = esMunicipio;
    }

    /**
     * @return the municipio
     */
    @ManyToOne(targetEntity = Municipio.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = "MUN_X_MUNICIPIO")
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio
     *            the municipio to set
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
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
        if (!(obj instanceof Localidad)) {
            return false;
        }

        final Localidad other = (Localidad) obj;

        if (!getCodigo().equals(other.getCodigo())) {
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
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(COLUMN_NAME_ID,
                getId()).append(COLUMN_NAME_CODIGO, getCodigo())
                .append(COLUMN_NAME_NOMBRE, getNombre()).toString();
    }

    @Override
    public Localidad newInstance() {

        return new Localidad();
    }

}