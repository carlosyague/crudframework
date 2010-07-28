package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.adminconfig.model.Parametro;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Criteria para las busquedas de parametros.
 * 
 * @author jcisneros
 */
public class ParametroCriteriaForm implements ISearchForm, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 8522996923345207875L;

    /**
     * pestañas
     */
    private TabGroup          tabGroup         = new TabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private Tab               tabBuscarUsuario = new Tab(tabGroup,
                                                       "parametroSearch.tab");

    /**
     * subpestañas
     */
    private SubTabGroup       subTabGroup      = new SubTabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private SubTab            subTabResultados = new SubTab(subTabGroup,
                                                       "usuarioSearch.resultados");

    /**
     * Codigo del parametro
     */
    private String            codigo;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();
        criterios.put(Parametro.PROPERTY_NAME_CODIGO, getCodigo());
        return criterios;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
     *      Object>)
     */
    public void fromMap(final Map<String, Object> criterios) {
        if (criterios != null) {
            if (criterios.get(Parametro.PROPERTY_NAME_CODIGO) != null) {
                setCodigo(criterios.get(Parametro.PROPERTY_NAME_CODIGO)
                        .toString());
            }
        }
    }

    // Getters && Setter

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
    public void setTabGroup(final TabGroup tabGroup) {
        this.tabGroup = tabGroup;
    }

    /**
     * @return the subTabGroup
     */
    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    /**
     * @return the subTabResultados
     */
    public SubTab getSubTabResultados() {
        return subTabResultados;
    }

    /**
     * @param subTabGroup
     *            the subTabGroup to set
     */
    public void setSubTabGroup(final SubTabGroup subTabGroup) {
        this.subTabGroup = subTabGroup;
    }

    /**
     * @param subTabResultados
     *            the subTabResultados to set
     */
    public void setSubTabResultados(final SubTab subTabResultados) {
        this.subTabResultados = subTabResultados;
    }

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
     * @return the tabBuscarUsuario
     */
    public Tab getTabBuscarUsuario() {
        return tabBuscarUsuario;
    }

    /**
     * @param tabBuscarUsuario
     *            the tabBuscarUsuario to set
     */
    public void setTabBuscarUsuario(Tab tabBuscarUsuario) {
        this.tabBuscarUsuario = tabBuscarUsuario;
    }

}
