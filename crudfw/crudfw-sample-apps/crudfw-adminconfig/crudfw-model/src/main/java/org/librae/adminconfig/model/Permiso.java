package org.librae.adminconfig.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * Objeto que cotiene los permisos de librae. No se crean ni se eliminan. Son
 * utilizados en los botones y el menu.
 * 
 * @author asantamaria
 */
@Entity(name = Permiso.ENTITY_NAME)
@Table(name = Permiso.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Permiso extends BaseObject {

    /**
     * BaseObject is Serializable, so Permiso needs a Serial Version UID
     */
    private static final long   serialVersionUID            = 6788833152607745563L;

    public static final String  ENTITY_NAME                 = "org.librae.adminconfig.model.Permiso";
    public static final String  TABLE_NAME                  = "ADM_PERMISOS";
    private static final String ID_GENERATOR_NAME           = "generator_adm_permisos";
    private static final String ID_SEQUENCE_NAME            = "SEQ_ADM_PERMISOS";
    public static final String  COLUMN_NAME_ID              = "X_PERMISO";
    public static final String  COLUMN_NAME_CODIGO          = "C_PERMISO";
    public static final String  COLUMN_NAME_NOMBRE          = "T_PERMISOS";
    public static final String  COLUMN_NAME_CATEGORIA       = "TIP_X_TIPO_PERMISO";
    public static final String  COLUMN_NAME_SOLO_SUCURSALES = "L_SOLO_SUCURSALES";
    public static final String  PROPTY_NAME_ID              = "id";
    public static final String  PROPTY_NAME_CODIGO          = "codigo";
    public static final String  PROPTY_NAME_NOMBRE          = "nombre";
    public static final String  PROPTY_NAME_CATEGORIA       = "categoria";
    public static final String  PROPTY_NAME_SOLO_SUCURSALES = "soloSucursales";

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                id;

    /**
     * Código alfanumérico unívoco del permiso
     */
    private String              codigo;

    /**
     * Nombre del permiso
     */
    private String              nombre;

    /**
     * Campo para categorizar los permisos en cada grupo funcional o pantalla.
     * Posibles valores: Menu, Circulación,...
     */
    private TipoPermiso         categoria;

    /**
     * Sólo aplica si D_GBS == "S" <br>
     * Implementado en Ab*NET <br>
     * Indica si SI/NO una sucursal admite que ejemplares prestados en otras
     * sucursales de su misma biblioteca sean devueltos en ella.
     */
    private Boolean             soloSucursales;

    protected Permiso() {
        super();
    }

    public Permiso(String codigo, String nombre) {
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
    @Column(name = Permiso.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    @Column(name = Permiso.COLUMN_NAME_CODIGO, length = 80)
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
    @Column(name = Permiso.COLUMN_NAME_NOMBRE, length = 80)
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
     * @return the categoria
     */
    @ManyToOne(targetEntity = TipoPermiso.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    @JoinColumn(name = Permiso.COLUMN_NAME_CATEGORIA)
    public TipoPermiso getCategoria() {
        return categoria;
    }

    /**
     * @param categoria
     *            the categoria to set
     */
    public void setCategoria(TipoPermiso categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the admiteDevolucionMismaBiblioteca
     */
    @Column(name = Permiso.COLUMN_NAME_SOLO_SUCURSALES)
    public Boolean getSoloSucursales() {
        return soloSucursales;
    }

    /**
     * @param soloSucursales
     */
    public void setSoloSucursales(Boolean soloSucursales) {
        this.soloSucursales = soloSucursales;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Permiso)) {
            return false;
        }

        final Permiso other = (Permiso) obj;

        if (!getCodigo().equals(other.getCodigo())) {
            return false;
        }

        return true;

    }

    /**
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

}