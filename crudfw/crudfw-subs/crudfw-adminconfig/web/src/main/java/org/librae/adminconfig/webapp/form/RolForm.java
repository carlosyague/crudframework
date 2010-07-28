package org.librae.adminconfig.webapp.form;

import java.io.Serializable;

import org.librae.adminconfig.model.Rol;

import org.librae.common.webapp.form.IBaseForm;

/**
 * Formulario para la inserccion de un rol.
 * 
 * @author jcisneros
 */
public class RolForm implements Serializable, IBaseForm<Rol> {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Codigo del rol.
     */
    private String            codigo;

    /**
     * Nombre del rol.
     */
    private String            nombre;

    /**
     * Nivel del rol.
     */
    private Long              nivel;

    /**
     * Id del Rol
     */
    private Long              idRol;

    /** modo de Edición o Lectura de un Rol */
    private Boolean           readMode         = false;

    /** Distinguimos entre Creación o Edición */
    private Boolean           creacion         = true;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Rol toEntity() {
        final Rol rol = new Rol(codigo);
        rol.setNombre(nombre);
        rol.setNivel(nivel);

        return rol;
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(final Rol rol) {
        idRol = rol.getId();
        codigo = rol.getCodigo();
        nombre = rol.getNombre();
        nivel = rol.getNivel();
    }

    // Getters && Setter

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(final Long idRol) {
        this.idRol = idRol;
    }

    public Boolean getReadMode() {
        return readMode;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

    /**
     * Habilita/deshabilita el botón eliminar de la edición de roles según
     * estemos modificando/creando respectivamente.
     * 
     * @return true si estamos creando, false en caso contrario.
     */
    public Boolean getCreacion() {
        return creacion;
    }

    public void setCreacion(final Boolean creacion) {
        this.creacion = creacion;
    }

    /**
     * @return the nivel
     */
    public Long getNivel() {
        return nivel;
    }

    /**
     * @param nivel
     *            the nivel to set
     */
    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

}
