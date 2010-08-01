package org.librae.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.Constants;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Tabla con los posibles tipos de identificacion
 *
 * @author asantamaria
 */
@Entity(name = TipoIdentificacion.ENTITY_NAME)
@Table(name = TipoIdentificacion.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class TipoIdentificacion extends
        SpringRemotableLazyEntity<TipoIdentificacion> {

    /**
     * BaseObject is Serializable, so TipoIdentificacion needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID     = -2706034060591893257L;

    public static final String  ENTITY_NAME          = "org.librae.adminconfig.model.TipoIdentificacion";
    public static final String  TABLE_NAME           = "ADM_TIPOS_IDENTIFICACION";
    public static final String  ID_GENERATOR_NAME    = "generator_adm_tipos_identificacion";
    private static final String ID_SEQUENCE_NAME     = "SEQ_ADM_TIPOS_IDENTIFICACION";
    public static final String  COLUMN_NAME_ID       = "X_TIPO_IDENTIFICACION";
    public static final String  COLUMN_NAME_CODIGO   = "C_TIPO_IDENTIFICACION";
    public static final String  COLUMN_NAME_NOMBRE   = "T_TIPO_IDENTIFICACION";

    public static final String  PROPERTY_NAME_ID     = "id";
    public static final String  PROPERTY_NAME_CODIGO = "codigo";
    public static final String  PROPERTY_NAME_NOMBRE = "nombre";

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                id;

    /**
     * Código de la identificación
     */
    private String              codigo;

    /**
     * Descripción de la identificación
     */
    private String              nombre;

    protected TipoIdentificacion() {
        super();
    }

    public TipoIdentificacion(String codigo, String nombre) {
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
    @Column(name = TipoIdentificacion.COLUMN_NAME_ID)
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
    @Column(name = TipoIdentificacion.COLUMN_NAME_CODIGO,length=40)
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
    @Column(name = TipoIdentificacion.COLUMN_NAME_NOMBRE,length=80)
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
        if (!(obj instanceof TipoIdentificacion)) {
            return false;
        }

        final TipoIdentificacion other = (TipoIdentificacion) obj;

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
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(PROPERTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(PROPERTY_NAME_NOMBRE,
                (getNombre() == null) ? "" : getNombre().toString())

        .toString();
    }

    /**
     * Comprueba si es un <code>TipoIdentificacion</code> de tipo PASAPORTE
     *
     * @return <code>true</code> si es un <b>Documento de validez
     *         internacional</b>
     */
    @Transient
    public Boolean isPasaporte() {
        return isTipoIdentificacion(Constants.TIPO_IDENTIFICACION_PASAPORTE);
    }

    /**
     * Comprueba si es un <code>TipoIdentificacion</code> de tipo NIE
     *
     * @return <code>true</code> si es una <b>Tarjeta de Extranjería</b>
     */
    @Transient
    public Boolean isNie() {
        return isTipoIdentificacion(Constants.TIPO_IDENTIFICACION_NIE);
    }

    /**
     * Comprueba si es un <code>TipoIdentificacion</code> de tipo NIF
     *
     * @return <code>true</code> si es un <b>Número de Identificación Fiscal</b>
     */
    @Transient
    public Boolean isNif() {
        return isTipoIdentificacion(Constants.TIPO_IDENTIFICACION_NIF);
    }

    /**
     * Comprueba si es un <code>TipoIdentificacion</code> de tipo CIF
     *
     * @return <code>true</code> si es un <b>Código de Identificación Fiscal</b>
     */
    @Transient
    public Boolean isCif() {
        return isTipoIdentificacion(Constants.TIPO_IDENTIFICACION_CIF);
    }

    /**
     * Comprueba si esta instancia tiene el código de tipo de identificación
     * <code>codigoTipoIdentificacion</code>
     *
     * @param codigoTipoIdentificacion
     * @return
     */
    private Boolean isTipoIdentificacion(String codigoTipoIdentificacion) {
        return getCodigo().equals(codigoTipoIdentificacion);
    }

    @Override
    public TipoIdentificacion newInstance() {
        return new TipoIdentificacion();
    }

}