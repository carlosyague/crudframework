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
 * Estados de circulación de un ejemplar
 * 
 * @author asantamaria
 */
@Entity(name = EstadoCirculacion.ENTITY_NAME)
@Table(name = EstadoCirculacion.TABLE_NAME)
public class EstadoCirculacion extends
        SpringRemotableLazyEntity<EstadoCirculacion> {

    /**
     * BaseObject is Serializable, so EstadoCirculacion needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID        = -2001600834040605047L;

    public static final String  ENTITY_NAME             = "org.librae.circulacion.model.EstadoCirculacion";
    public static final String  TABLE_NAME              = "CIR_ESTADOS_CIRCULACION";
    public static final String  ID_GENERATOR_NAME       = "generator_cir_estados_circulacion";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CIR_ESTADOS_CIRCULACION";
    public static final String  COLUMN_NAME_ID          = "X_ESTADO_CIRCULACION";
    public static final String  COLUMN_NAME_NOMBRE      = "T_ESTADO_CIRCULACION";
    public static final String  COLUMN_NAME_DESCRIPCION = "T_ESTADO_CIRCULACION_ALT";
    public static final String  COLUMN_NAME_CODIGO      = "C_ESTADO_CIRCULACION";
    public static final String  COLUMN_NAME_VALOR_OET   = "NCIP_X_NCIP_VALUE_OET";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Nombre del estado de circulación. Por ejmeplo: 'Prestado'
     */
    private String              nombre;

    /**
     * Descripción explicativa del estado de circulación
     */
    private String              descripcion;

    /**
     * Código asignado por el usuario
     */
    private String              codigo;

    /**
     * >> NCIP_VALUES_OET<br>
     * Referencia a un valor del OET Circulation Status<br>
     *<br>
     * Restricción:<br>
     * Los valores de esta columna deben ser únicos: no puede haber dos filas de
     * esta tabla que referencien al mismo valor de NCIP<br>
     */
    private String              valorOET;

    protected EstadoCirculacion() {
        super();
    }

    public EstadoCirculacion(String codigo) {
        super();
        this.codigo = codigo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = EstadoCirculacion.COLUMN_NAME_ID)
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
    @Column(name = EstadoCirculacion.COLUMN_NAME_NOMBRE, length = 80)
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
     * @return the descripcion
     */
    @Column(name = EstadoCirculacion.COLUMN_NAME_DESCRIPCION)
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
     * @return the codigo
     */
    @Column(name = EstadoCirculacion.COLUMN_NAME_CODIGO, length = 40)
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
     * @return the valorOET
     */
    @ManyToOne(targetEntity = EstadoCirculacion.class, cascade = {
            CascadeType.PERSIST, CascadeType.ALL })
    @JoinColumn(name = EstadoCirculacion.COLUMN_NAME_VALOR_OET, nullable = true)
    public String getValorOET() {
        return valorOET;
    }

    /**
     * @param valorOET
     *            the valorOET to set
     */
    public void setValorOET(String valorOET) {
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
        if (!(obj instanceof EstadoCirculacion)) {
            return false;
        }

        final EstadoCirculacion other = (EstadoCirculacion) obj;

        if (this.getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (this.getCodigo() != null
                && !this.getCodigo().equals(other.getCodigo())) {
            return false;
        }

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
                + ((this.getCodigo() == null) ? 0 : this.getCodigo().hashCode());

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

        .append(this.COLUMN_NAME_ID, this.getId())

        .append(this.COLUMN_NAME_CODIGO,
                (this.getCodigo() == null) ? 0 : this.getCodigo().toString())

        .append(this.COLUMN_NAME_NOMBRE,
                (this.getNombre() == null) ? 0 : this.getNombre().toString())

        .append(
                this.COLUMN_NAME_VALOR_OET,
                (this.getValorOET() == null) ? 0 : this.getValorOET()
                        .toString())

        .toString();
    }

    @Override
    public EstadoCirculacion newInstance() {

        return new EstadoCirculacion();
    }

}