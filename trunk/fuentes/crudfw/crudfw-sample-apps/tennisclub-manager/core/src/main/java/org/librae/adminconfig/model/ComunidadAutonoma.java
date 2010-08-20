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
@Entity(name = ComunidadAutonoma.ENTITY_NAME)
@Table(name = ComunidadAutonoma.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class ComunidadAutonoma extends
        SpringRemotableLazyEntity<ComunidadAutonoma> {

    /**
     * BaseObject is Serializable, so ComunidadAutonoma needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID   = -5548428224195833175L;

    public static final String  ENTITY_NAME        = "org.librae.adminconfig.model.ComunidadAutonoma";
    public static final String  TABLE_NAME         = "ADM_COM_AUTONOMAS";
    private static final String ID_GENERATOR_NAME  = "generator_adm_com_autonomas";
    private static final String ID_SEQUENCE_NAME   = "SEQ_ADM_COM_AUTONOMAS";
    public static final String  COLUMN_NAME_ID     = "X_COM_AUTONOMAS";
    public static final String  COLUMN_NAME_CODIGO = "C_COM_AUTONOMAS";
    public static final String  COLUMN_NAME_NOMBRE = "T_COM_AUTONOMAS";
    public static final String  COLUMN_NAME_PAIS   = "PAI_X_PAIS";

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                id;

    /**
     * Código de la comunidad autónoma (AND para Andalucía)
     */
    private String              codigo;

    /**
     * Nombre de la comunidad autónoma
     */
    private String              nombre;

    /**
     * Pais al que pertenece la Comunidad Autónoma. Relación con la tabla PAIS
     */
    private Pais                pais;

    protected ComunidadAutonoma() {
        super();
    }

    public ComunidadAutonoma(String codigo, String nombre) {
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
    @Column(name = ComunidadAutonoma.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the codigo
     */
    @Column(name = ComunidadAutonoma.COLUMN_NAME_CODIGO, length = 40)
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
    @Column(name = ComunidadAutonoma.COLUMN_NAME_NOMBRE, length = 80)
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
            CascadeType.MERGE })
    @JoinColumn(name = ComunidadAutonoma.COLUMN_NAME_PAIS)
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
        if (!(obj instanceof ComunidadAutonoma)) {
            return false;
        }

        final ComunidadAutonoma other = (ComunidadAutonoma) obj;

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
        result = prime * result
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(COLUMN_NAME_ID, getId())
                .append(COLUMN_NAME_CODIGO, getCodigo()).append(
                        COLUMN_NAME_NOMBRE, getNombre()).toString();
    }

    @Override
    public ComunidadAutonoma newInstance() {

        return new ComunidadAutonoma();
    }

}