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
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;
import org.librae.common.model.BaseObject;

/**
 * Dado que el usuario puede tener más de un rol en la aplicación, en esta tabla
 * se tienen todos los roles que tiene actualmente el usuario. Un registro por
 * cada par usuario/rol.
 *
 * @author asantamaria
 */
@Entity(name = UsuarioBibliotecaRol.ENTITY_NAME)
@Table(name = UsuarioBibliotecaRol.TABLE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = {
        UsuarioBibliotecaRol.COLUMN_NAME_USUARIO,
        UsuarioBibliotecaRol.COLUMN_NAME_ROL,
        UsuarioBibliotecaRol.COLUMN_NAME_BIBLIOTECA }) })
public class UsuarioBibliotecaRol extends BaseObject {

    /**
     * BaseObject is Serializable, so UsuarioBibliotecaRol needs a Serial
     * Version UID
     */
    private static final long   serialVersionUID       = -8689401356498668465L;

    public static final String  ENTITY_NAME            = "org.librae.adminconfig.model.UsuarioBibliotecaRol";
    public static final String  TABLE_NAME             = "ADM_USUARIOS_BIBLIOTECAS_ROLES";
    private static final String ID_GENERATOR_NAME      = "generator_adm_usuarios_bibliotecas_roles";
    private static final String ID_SEQUENCE_NAME       = "SEQ_ADM_USER_BIB_ROLES";
    public static final String  COLUMN_NAME_ID         = "X_USUARIO_BIBLIOTECA_ROL";
    public static final String  COLUMN_NAME_USUARIO    = "USU_X_USUARIO";
    public static final String  COLUMN_NAME_BIBLIOTECA = "BIB_X_BIBLIOTECA";
    public static final String  COLUMN_NAME_ROL        = "ROL_X_ROL";

    public static final String  PROPTY_NAME_ID         = "id";
    public static final String  PROPTY_NAME_USUARIO    = "usuario";
    public static final String  PROPTY_NAME_BIBLIOTECA = "biblioteca";
    public static final String  PROPTY_NAME_ROL        = "rol";

    /**
     * Clave artificial de la tabla.
     */
    private Long                id;

    /**
     * Clave foránea a la tabla USUARIOS
     */
    private Usuario             usuario;

    /**
     * Clave foránea a la tabla BIBLIOTECAS
     */
    private Biblioteca          biblioteca;

    /**
     * Clave foránea a la tabla de roles
     */
    private Rol                 rol;

    protected UsuarioBibliotecaRol() {
        super();
    }

    public UsuarioBibliotecaRol(final Usuario usuario, final Rol rol,
            final Biblioteca biblioteca) {
        super();
        this.usuario = usuario;
        this.biblioteca = biblioteca;
        this.rol = rol;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = UsuarioBibliotecaRol.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the usuario
     */
    @ManyToOne(targetEntity = Usuario.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = UsuarioBibliotecaRol.COLUMN_NAME_USUARIO)
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     *            the usuario to set
     */
    public void setUsuario(final Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the rol
     */
    @ManyToOne(targetEntity = Rol.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = UsuarioBibliotecaRol.COLUMN_NAME_ROL, nullable = false, updatable = false)
    @ForeignKey(name = "FK_UBR_ROL")
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol
     *            the rol to set
     */
    public void setRol(final Rol rol) {
        this.rol = rol;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = UsuarioBibliotecaRol.COLUMN_NAME_BIBLIOTECA)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA")
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(final Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
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
        if (!(obj instanceof UsuarioBibliotecaRol)) {
            return false;
        }

        final UsuarioBibliotecaRol other = (UsuarioBibliotecaRol) obj;

        if (getUsuario() == null && other.getUsuario() != null) {
            return false;
        }
        if (getUsuario() != null && !getUsuario().equals(other.getUsuario())) {
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
        result += ((getUsuario() == null) ? 0 : getUsuario().hashCode());
        result = prime * result
                + ((getRol() == null) ? 0 : getRol().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(COLUMN_NAME_USUARIO,
                ((getUsuario() == null) ? "" : getUsuario().toString()))
                .append(COLUMN_NAME_ROL,
                        (getRol() == null) ? "" : getRol().toString())
                .toString();
    }
}