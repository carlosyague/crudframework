package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.adminconfig.model.Permiso;

import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Criteria para las busquedas de Permisos.
 * 
 * @author jcisneros
 */
public class PermisoCriteriaForm implements ISearchForm, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 8522996923345207875L;

    /**
     * pestañas
     */
    private TabGroup          tabGroup         = new TabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);

    private Tab               tabListado       = new Tab(tabGroup,
                                                       "permiso.listado.tab");

    /**
     * subpestañas
     */
    private SubTabGroup       subTabGroup      = new SubTabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);

    private SubTab            subTabResults    = new SubTab(subTabGroup,
                                                       "permiso.listado.resultados");

    /**
     * Código alfanumérico unívoco del Permiso
     */
    private String            codigo;

    /**
     * Nombre del Permiso
     */
    private String            nombre;

    /**
     * Categoria del Permiso.
     */
    private Long              categoriaPermiso = null;

    private String            buscarPor        = "noBuscar";

    private Long              idRol            = null;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();

        criterios.put(Permiso.PROPTY_NAME_CODIGO, getCodigo());
        criterios.put(Permiso.PROPTY_NAME_NOMBRE, getNombre());
        criterios.put(Permiso.PROPTY_NAME_CATEGORIA, getCategoriaPermiso());
        criterios.put("idRol", getIdRol());
        criterios.put("buscarPor", getBuscarPor());

        return criterios;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
     *      Object>)
     */
    public void fromMap(Map<String, Object> criterios) {
        if (criterios != null) {
            if (criterios.get(Permiso.PROPTY_NAME_CODIGO) != null) {
                setCodigo(criterios.get(Permiso.PROPTY_NAME_CODIGO).toString());
            }
            if (criterios.get(Permiso.PROPTY_NAME_NOMBRE) != null) {
                setNombre(criterios.get(Permiso.PROPTY_NAME_NOMBRE).toString());
            }
            if (criterios.get(Permiso.PROPTY_NAME_CATEGORIA) != null) {
                setCategoriaPermiso((Long) criterios
                        .get(Permiso.PROPTY_NAME_CATEGORIA));
            }
            if (criterios.get("idRol") != null) {
                setIdRol((Long) criterios.get("idRol"));
            }
            if (criterios.get("buscarPor") != null) {
                setBuscarPor((String) criterios.get("buscarPor"));
            }
        }
    }

    // Getters && Setters

    /**
     * @return the codigo
     */
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
     * @return the categoriaPermiso
     */
    public Long getCategoriaPermiso() {
        return categoriaPermiso;
    }

    /**
     * @param categoriaPermiso
     *            the categoriaPermiso to set
     */
    public void setCategoriaPermiso(Long categoriaPermiso) {
        this.categoriaPermiso = categoriaPermiso;
    }

    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor
     *            the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    /**
     * @return the idRol
     */
    public Long getIdRol() {
        return idRol;
    }

    /**
     * @param idRol
     *            the idRol to set
     */
    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

}
