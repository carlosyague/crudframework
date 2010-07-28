package org.librae.adminconfig.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.common.model.BaseObject;

/**
 * Representa un rol.
 *
 * @author asantamaria
 */
@Entity(name = Rol.ENTITY_NAME)
@Table(name = Rol.TABLE_NAME)
public class Rol extends BaseObject implements Cloneable {

    /**
     * BaseObject is Serializable, so Rol needs a Serial Version UID
     */
    private static final long   serialVersionUID           = 5927814239323950909L;

    public static final String  ENTITY_NAME                = "org.librae.adminconfig.model.Rol";
    public static final String  TABLE_NAME                 = "ADM_ROLES";
    private static final String ID_GENERATOR_NAME          = "generator_adm_roles";
    private static final String ID_SEQUENCE_NAME           = "SEQ_ADM_ROLES";

    public static final String  COLUMN_NAME_ID             = "X_ROL";
    public static final String  COLUMN_NAME_CODIGO         = "C_ROL";
    public static final String  COLUMN_NAME_NOMBRE         = "T_ROL";
    public static final String  COLUMN_NAME_NIVEL          = "N_NIVEL";

    public static final String  PROPTY_NAME_ID             = "id";
    public static final String  PROPTY_NAME_CODIGO         = "codigo";
    public static final String  PROPTY_NAME_NOMBRE         = "nombre";
    public static final String  PROPTY_NAME_NIVEL          = "nivel";
    public static final String  PROPTY_NAME_PERMISOS_ROLES = "permisosRoles";

    /**
     * Clave primaria autonumérica sin significado funcional
     */
    private Long                id;

    /**
     * Código alfanumérico unívoco del rol
     */
    private String              codigo;

    /**
     * Nombre del rol
     */
    private String              nombre;

    /**
     * Nivel del rol. (1 para el nivel mínimo. 10 Para el nivel máximo)
     */
    private Long                nivel;

    /**
     * Listado de permisos
     */
    private List<PermisoRol>    permisosRoles;

    protected Rol() {
        super();
    }

    public Rol(final String codigo, final String nombre, Long nivel) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public Rol(final String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Rol.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Rol.ID_GENERATOR_NAME, sequenceName = Rol.ID_SEQUENCE_NAME)
    @Column(name = Rol.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    @Column(name = Rol.COLUMN_NAME_CODIGO, unique = true, nullable = false,length=40)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    @Column(name = Rol.COLUMN_NAME_NOMBRE,length=120)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombre
     */
    @Column(name = Rol.COLUMN_NAME_NIVEL)
    public Long getNivel() {
        return nivel;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNivel(final Long nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the permisosRoles
     */
    @OneToMany(targetEntity = PermisoRol.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = PermisoRol.COLUMN_NAME_ROL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<PermisoRol> getPermisosRoles() {
        return permisosRoles;
    }

    /**
     * @param permisosRoles
     *            the permisosRoles to set
     */
    public void setPermisosRoles(final List<PermisoRol> permisosRoles) {
        this.permisosRoles = permisosRoles;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Rol)) {
            return false;
        }

        final Rol other = (Rol) obj;

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
        return new ToStringBuilder(this).append(Rol.COLUMN_NAME_ID, getId())
                .append(Rol.COLUMN_NAME_CODIGO, getCodigo()).append(
                        Rol.COLUMN_NAME_NOMBRE, getNombre()).toString();
    }

    /**
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        final Rol rol = (Rol) super.clone();
        rol.id = null;
        rol.setNombre(nombre);
        rol.setCodigo(codigo);
        rol.setNivel(nivel);
        return rol;
    }

}