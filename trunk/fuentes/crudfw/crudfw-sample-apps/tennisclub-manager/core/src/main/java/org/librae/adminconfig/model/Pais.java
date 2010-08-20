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
 * Tabla de países
 *
 * @author asantamaria
 */
@Entity(name = Pais.ENTITY_NAME)
@Table(name = Pais.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Pais extends SpringRemotableLazyEntity<Pais> {

    /**
     * BaseObject is Serializable, so Pais needs a Serial Version UID
     */
    private static final long   serialVersionUID   = 4256019811614045308L;

    public static final String  ENTITY_NAME        = "org.librae.adminconfig.model.Pais";
    public static final String  TABLE_NAME         = "ADM_PAISES";
    private static final String ID_GENERATOR_NAME  = "generator_adm_paises";
    private static final String ID_SEQUENCE_NAME   = "SEQ_ADM_PAISES";
    public static final String  COLUMN_NAME_ID     = "X_PAIS";
    public static final String  COLUMN_NAME_CODIGO = "C_PAIS";
    public static final String  COLUMN_NAME_NOMBRE = "T_PAIS";
    public static final String  COLUMN_NAME_BAJA   = "L_BAJA";

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                id;

    /**
     * Código ISO3166 del país
     */
    private String              codigo;

    /**
     * Nombre del país
     */
    private String              nombre;

    /**
     * Si o No
     */
    private Boolean             baja;

    protected Pais() {
        super();
    }

    public Pais(String codigo, String nombre) {
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
    @Column(name = Pais.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the codigo
     */
    @Column(name = Pais.COLUMN_NAME_CODIGO, length = 40)
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
    @Column(name = Pais.COLUMN_NAME_NOMBRE, length = 80)
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
    @Column(name = Pais.COLUMN_NAME_BAJA)
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
        if (!(obj instanceof Pais)) {
            return false;
        }

        final Pais other = (Pais) obj;

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
    public Pais newInstance() {

        return new Pais();
    }

}