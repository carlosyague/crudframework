package org.librae.adminconfig.webapp.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Rol;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.form.IBaseForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Formulario para la asignacion de permisos a roles.
 * 
 * @author jcisneros
 */
public class AsignarPermisoRolForm implements IBaseForm<Rol> {

	private static final long serialVersionUID = 8522996923345207175L;

	/**
     * pestañas
     */
    private TabGroup         tabGroup             = new TabGroup(
                                                          MensajesError.PROPERTI_ADMINCONFIG);

    private Tab              tabListado           = new Tab(tabGroup,
                                                          "asignarPermisoRol.tab");

    /**
     * subpestañas
     */
    private SubTabGroup      subTabGroup          = new SubTabGroup(
                                                          MensajesError.PROPERTI_ADMINCONFIG);

    private SubTab           subTabResults        = new SubTab(subTabGroup,
                                                          "asignarPermisoRol.asignados");

    private Long             idRol                = null;

    private String           codigoPermiso        = null;

    private String           codigoRol            = null;

    private String           nombreRol            = null;

    private List<String>     idsPermisoAsignado   = null;

    private List<String>     idsPermisoSinAsignar = null;

    private Long             categoriaPermiso     = null;

    private List<SelectItem> permisosAsignados    = new ArrayList<SelectItem>();

    private List<SelectItem> permisosNoAsignados  = new ArrayList<SelectItem>();

    public void fromEntity(final Rol rol) {
        setCodigoRol(rol.getCodigo());
        setNombreRol(rol.getNombre());
    }

    public Rol toEntity() {
        final Rol rol = new Rol(codigoRol);
        rol.setNombre(nombreRol);
        return rol;
    }

    // Getters && Setter

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(final Long idRol) {
        this.idRol = idRol;
    }

    public String getCodigoPermiso() {
        return codigoPermiso;
    }

    public void setCodigoPermiso(final String codigoPermiso) {
        this.codigoPermiso = codigoPermiso;
    }

    public List<String> getIdsPermisoAsignado() {
        return idsPermisoAsignado;
    }

    public void setIdsPermisoAsignado(final List<String> idsPermisoAsignado) {
        this.idsPermisoAsignado = idsPermisoAsignado;
    }

    public List<String> getIdsPermisoSinAsignar() {
        return idsPermisoSinAsignar;
    }

    public void setIdsPermisoSinAsignar(final List<String> idsPermisoSinAsignar) {
        this.idsPermisoSinAsignar = idsPermisoSinAsignar;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(final String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public Long getCategoriaPermiso() {
        return categoriaPermiso;
    }

    public void setCategoriaPermiso(final Long categoriaPermiso) {
        if (!(new Long(0)).equals(categoriaPermiso)) {
            this.categoriaPermiso = categoriaPermiso;
        }
    }

    /**
     * @return the nombreRol
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * @param nombreRol
     *            the nombreRol to set
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
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

    /**
     * @param tabGroup
     *            the tabGroup to set
     */
    public void setTabGroup(TabGroup tabGroup) {
        this.tabGroup = tabGroup;
    }

    /**
     * @param tabListado
     *            the tabListado to set
     */
    public void setTabListado(Tab tabListado) {
        this.tabListado = tabListado;
    }

    /**
     * @param subTabGroup
     *            the subTabGroup to set
     */
    public void setSubTabGroup(SubTabGroup subTabGroup) {
        this.subTabGroup = subTabGroup;
    }

    /**
     * @param subTabResults
     *            the subTabResults to set
     */
    public void setSubTabResults(SubTab subTabResults) {
        this.subTabResults = subTabResults;
    }

    /**
     * @return the permisosAsignados
     */
    public List<SelectItem> getPermisosAsignados() {
        return permisosAsignados;
    }

    /**
     * @param permisosAsignados
     *            the permisosAsignados to set
     */
    public void setPermisosAsignados(List<SelectItem> permisosAsignados) {
        this.permisosAsignados = permisosAsignados;
    }

    /**
     * @return the permisosNoAsignados
     */
    public List<SelectItem> getPermisosNoAsignados() {
        return permisosNoAsignados;
    }

    /**
     * @param permisosNoAsignados
     *            the permisosNoAsignados to set
     */
    public void setPermisosNoAsignados(List<SelectItem> permisosNoAsignados) {
        this.permisosNoAsignados = permisosNoAsignados;
    }

}
