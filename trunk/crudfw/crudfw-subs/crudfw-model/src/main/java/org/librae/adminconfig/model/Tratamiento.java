package org.librae.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Tabla con los posibles tratamiento
 *
 * @author asantamaria
 */
@Entity(name = Tratamiento.ENTITY_NAME)
@Table(name = Tratamiento.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Tratamiento extends SpringRemotableLazyEntity<Tratamiento> {

    /**
     * BaseObject is Serializable, so Tratamiento needs a Serial Version UID
     */
    private static final long   serialVersionUID          = -235515792050626625L;

    public static final String  ENTITY_NAME               = "org.librae.adminconfig.model.Tratamiento";
    public static final String  TABLE_NAME                = "ADM_TRATAMIENTOS";
    public static final String  ID_GENERATOR_NAME         = "generator_adm_tratamientos";
    private static final String ID_SEQUENCE_NAME          = "SEQ_ADM_TRATAMIENTOS";
    public static final String  COLUMN_NAME_ID            = "X_TRATAMIENTO";
    public static final String  COLUMN_NAME_CODIGO        = "C_TRATAMIENTO";
    public static final String  COLUMN_NAME_TRATAMIENTO   = "T_TRATAMIENTO";
    public static final String  COLUMN_NAME_SEXO          = "C_SEXO";

    public static final String  PROPERTY_NAME_ID          = "id";
    public static final String  PROPERTY_NAME_CODIGO      = "codigo";
    public static final String  PROPERTY_NAME_TRATAMIENTO = "tratamiento";
    public static final String  PROPERTY_NAME_SEXO        = "sexo";

    public static final String  SEXO_MUJER                = "M";
    public static final String  SEXO_HOMBRE               = "H";

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                id;

    /**
     * Código del tratamiento
     */
    private String              codigo;

    /**
     * Descripción del tratamiento
     */
    private String              tratamiento;

    /**
     * Sexo al que aplica el tratamiento: Hombre (H), Mujer(M) para tratamientos
     * diferenciados
     */
    private String              sexo;

    protected Tratamiento() {
        super();
    }

    public Tratamiento(String codigo, String tratamiento, String sexo) {
        super();
        this.codigo = codigo;
        this.tratamiento = tratamiento;
        this.sexo = sexo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Tratamiento.COLUMN_NAME_ID)
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
    @Column(name = Tratamiento.COLUMN_NAME_CODIGO, length = 40)
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
    @Column(name = Tratamiento.COLUMN_NAME_TRATAMIENTO, length = 80)
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * @param Tratamiento
     *            the Tratamiento to set
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * @return the sexo
     */
    @Column(name = Tratamiento.COLUMN_NAME_SEXO, length = 10)
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo
     *            the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
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
        if (!(obj instanceof Tratamiento)) {
            return false;
        }

        final Tratamiento other = (Tratamiento) obj;

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null
                && !getCodigo().equals(other.getCodigo())) {
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
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(PROPERTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(
                PROPERTY_NAME_TRATAMIENTO,
                (getTratamiento() == null) ? "" : getTratamiento()
                        .toString())

        .append(PROPERTY_NAME_SEXO,
                (getSexo() == null) ? "" : getSexo().toString())

        .toString();
    }

    @Override
    public Tratamiento newInstance() {

        return new Tratamiento();
    }

}