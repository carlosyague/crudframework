package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Usuario;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Formulario para la asignacion de usuarios a bibliotecas.
 * 
 * @author jcisneros
 */
public class AsignarPerfilesUsuarioForm implements Serializable {

    /**
     * pestanas
     */
    private final TabGroup    tabGroup           = new TabGroup(
                                                         MensajesError.PROPERTI_ADMINCONFIG);

    private final Tab         tabListado         = new Tab(tabGroup,
                                                         "asignarPerfiles.noAsignados");

    /**
     * subpestanas
     */
    private final SubTabGroup subTabGroup        = new SubTabGroup(
                                                         MensajesError.PROPERTI_ADMINCONFIG);

    private final SubTab      subTabResults      = new SubTab(subTabGroup,
                                                         "asignarPerfiles.roles");

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID   = 3947049993436716802L;
    private Long              idBiblioteca       = null;
    private String            codigoRol          = null;
    private List<String>      idsRolAsignado     = null;
    private List<String>      idsRolSinAsignar   = null;
    private List<String>      rolesSeleccionados = null;
    private List<SelectItem>  rolesAsignados     = new ArrayList<SelectItem>();
    private List<SelectItem>  rolesNoAsignados   = new ArrayList<SelectItem>();
    private List<SelectItem>  roles              = new ArrayList<SelectItem>();
    private List<Usuario>     usuarios           = new ArrayList<Usuario>();
    private Usuario           usuario            = null;

    // Getters && Setter

    public List<SelectItem> getRolesAsignados() {
        return rolesAsignados;
    }

    public void setRolesAsignados(final List<SelectItem> rolesAsignados) {
        this.rolesAsignados = rolesAsignados;
    }

    public List<SelectItem> getRolesNoAsignados() {
        return rolesNoAsignados;
    }

    public void setRolesNoAsignados(final List<SelectItem> rolesNoAsignados) {
        this.rolesNoAsignados = rolesNoAsignados;
    }

    public List<String> getIdsRolAsignado() {
        return idsRolAsignado;
    }

    public void setIdsRolAsignado(final List<String> idsRolAsignado) {
        this.idsRolAsignado = idsRolAsignado;
    }

    public List<String> getIdsRolSinAsignar() {
        return idsRolSinAsignar;
    }

    public void setIdsRolSinAsignar(final List<String> idsRolSinAsignar) {
        this.idsRolSinAsignar = idsRolSinAsignar;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(final String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public Long getIdBiblioteca() {
        return idBiblioteca;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(final List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<String> getRolesSeleccionados() {
        return rolesSeleccionados;
    }

    public List<SelectItem> getRoles() {
        return roles;
    }

    public void setIdBiblioteca(final Long idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public void setRolesSeleccionados(final List<String> rolesSeleccionados) {
        this.rolesSeleccionados = rolesSeleccionados;
    }

    public void setRoles(final List<SelectItem> roles) {
        this.roles = roles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(final Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the tabGroup
     */
    public TabGroup getTabGroup() {
        return tabGroup;
    }

    /**
     * @return the tabListado
     */
    public Tab getTabListado() {
        return tabListado;
    }

    /**
     * @return the subTabGroup
     */
    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    /**
     * @return the subTabResults
     */
    public SubTab getSubTabResults() {
        return subTabResults;
    }

}
