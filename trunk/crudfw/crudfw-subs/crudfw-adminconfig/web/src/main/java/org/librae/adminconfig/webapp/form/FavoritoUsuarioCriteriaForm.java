package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Criteria para las busquedas de roles.
 * 
 * @author jcisneros
 */
public class FavoritoUsuarioCriteriaForm implements ISearchForm, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = -7705758145634550639L;

    /**
     * pestañas
     */
    private TabGroup          tabGroup         = new TabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);

    private Tab               tabListado       = new Tab(tabGroup,
                                                       "favorito.listado.tab");

    /**
     * subpestañas
     */
    private SubTabGroup       subTabGroup      = new SubTabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);

    private SubTab            subTabResults    = new SubTab(subTabGroup,
                                                       "favorito.listado.subTab");

    private Long              idUsuario        = null;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final Map<String, Object> criteriosMap = new HashMap<String, Object>();
        criteriosMap.put("idUsuario", getIdUsuario());
        criteriosMap.put("sortColumn", "orden");
        return criteriosMap;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
     *      Object>)
     */
    public void fromMap(Map<String, Object> criterios) {
        if (criterios != null) {
            setIdUsuario((Long) criterios.get("idUsuario"));
        }
    }

    // Getters && Setters

    /**
     * @return the tabGroup
     */
    public TabGroup getTabGroup() {
        return tabGroup;
    }

    /**
     * @param tabGroup
     *            the tabGroup to set
     */
    public void setTabGroup(TabGroup tabGroup) {
        this.tabGroup = tabGroup;
    }

    /**
     * @return the tabListado
     */
    public Tab getTabListado() {
        return tabListado;
    }

    /**
     * @param tabListado
     *            the tabListado to set
     */
    public void setTabListado(Tab tabListado) {
        this.tabListado = tabListado;
    }

    /**
     * @return the subTabGroup
     */
    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    /**
     * @param subTabGroup
     *            the subTabGroup to set
     */
    public void setSubTabGroup(SubTabGroup subTabGroup) {
        this.subTabGroup = subTabGroup;
    }

    /**
     * @return the subTabResults
     */
    public SubTab getSubTabResults() {
        return subTabResults;
    }

    /**
     * @param subTabResults
     *            the subTabResults to set
     */
    public void setSubTabResults(SubTab subTabResults) {
        this.subTabResults = subTabResults;
    }

    /**
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario
     *            the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

}
