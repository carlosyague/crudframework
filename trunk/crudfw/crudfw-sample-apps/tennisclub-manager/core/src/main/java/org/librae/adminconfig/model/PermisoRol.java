package org.librae.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Para cada rol existe una serie de permisos asociados. Un registro por cada
 * par rol/permiso.
 * 
 * @author asantamaria
 */
@Entity(name = PermisoRol.ENTITY_NAME)
@Table(name = PermisoRol.TABLE_NAME)
public class PermisoRol extends BaseObject {

    /**
     * BaseObject is Serializable, so PermisoRol needs a Serial Version UID
     */
    private static final long   serialVersionUID    = -1694257566013444101L;

    public static final String  ENTITY_NAME         = "org.librae.adminconfig.model.PermisoRol";
    public static final String  TABLE_NAME          = "ADM_ROLES_PERMISOS";
    private static final String ID_GENERATOR_NAME   = "generator_adm_roles_permisos";
    private static final String ID_SEQUENCE_NAME    = "SEQ_ADM_ROLES_PERMISOS";
    public static final String  COLUMN_NAME_ID      = "X_ROLES_PERMISOS";
    public static final String  COLUMN_NAME_PERMISO = "PER_X_PERMISO";
    public static final String  COLUMN_NAME_ROL     = "ROL_X_ROL";
    public static final String  PROPTY_NAME_ID      = "id";
    public static final String  PROPTY_NAME_PERMISO = "permiso";
    public static final String  PROPTY_NAME_ROL     = "rol";

    private Long                id;

    /**
     * Para cada rol existe una serie de permisos asociados. Un registro por
     * cada par rol/permiso.
     */
    private Rol                 rol;

    private Permiso             permiso;

    protected PermisoRol() {
        super();
    }

    public PermisoRol(Permiso permiso, Rol rol) {
        super();
        this.permiso = permiso;
        this.rol = rol;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = PermisoRol.COLUMN_NAME_ID)
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
     * @return the rol
     */
    @ManyToOne(targetEntity = Rol.class)
    @JoinColumn(name = PermisoRol.COLUMN_NAME_ROL, referencedColumnName = Rol.COLUMN_NAME_ID)
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol
     *            the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * @return the permiso
     */
    @ManyToOne(targetEntity = Permiso.class)
    @JoinColumn(name = PermisoRol.COLUMN_NAME_PERMISO, referencedColumnName = Permiso.COLUMN_NAME_ID)
    @OrderBy("codigo")
    public Permiso getPermiso() {
        return permiso;
    }

    /**
     * @param permiso
     *            the permiso to set
     */
    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
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
        if (!(obj instanceof PermisoRol)) {
            return false;
        }

        final PermisoRol other = (PermisoRol) obj;

        if (getPermiso() == null && other.getPermiso() != null) {
            return false;
        }
        if (getPermiso() != null
                && !getPermiso().equals(other.getPermiso())) {
            return false;
        }

        if (getRol() == null && other.getRol() != null) {
            return false;
        }
        if (getRol() != null && !getRol().equals(other.getRol())) {
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
        result += ((getPermiso() == null) ? 0 : getPermiso()
                .hashCode());
        result = prime * result
                + ((getRol() == null) ? 0 : getRol().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(
                        COLUMN_NAME_PERMISO,
                        (getPermiso() == null) ? "" : getPermiso()
                                .toString())
                .append(COLUMN_NAME_ROL,
                        (getRol() == null) ? "" : getRol().toString())
                .toString();
    }

}