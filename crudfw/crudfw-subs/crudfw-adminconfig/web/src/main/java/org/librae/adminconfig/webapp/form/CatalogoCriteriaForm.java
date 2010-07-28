package org.librae.adminconfig.webapp.form;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import org.librae.adminconfig.model.Catalogo;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Criteria para las busquedas de catalogo.
 * 
 * @author impena
 */
public class CatalogoCriteriaForm implements ISearchForm, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 8521996923345207875L;

    /**
     * pestañas
     */
    private TabGroup          tabGroup         = new TabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private final Tab         tabListado       = new Tab(tabGroup,
                                                       "catalogoList.tab01Text");

    /**
     * subpestañas
     */
    private SubTabGroup       subTabGroup      = new SubTabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private final SubTab      subTabResults    = new SubTab(subTabGroup,
                                                       "catalogo.resultados");

    /**
     * Código identificativo del catálogo introducido por el usuario.<br>
     * Valores únicos
     */
    private String            codigo;

    /**
     * Descripción del catálogo introducida por el usuario
     */
    private String            nombre;

    /**
     * Descripción Alternativa del catálogo introducida por el usuario
     */
    private String            descripcion;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {

        final HashMap<String, Object> criterios = new HashMap<String, Object>();

        criterios.put(Catalogo.PROPERTY_NAME_CODIGO, getCodigo());
        criterios.put(Catalogo.PROPERTY_NAME_NOMBRE, getNombre());

        return criterios;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
     *      Object>)
     */
    public void fromMap(final Map<String, Object> criterios) {

        if (criterios != null) {
            if (criterios.get(Catalogo.PROPERTY_NAME_CODIGO) != null) {
                setCodigo(criterios.get(Catalogo.PROPERTY_NAME_CODIGO)
                        .toString());

            }
            if (criterios.get(Catalogo.PROPERTY_NAME_NOMBRE) != null) {
                setNombre(criterios.get(Catalogo.PROPERTY_NAME_NOMBRE)
                        .toString());

            }
        }
    }

    // ================ Getters && Setter ===================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

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
     * @param subTabGroup
     *            the subTabGroup to set
     */
    public void setSubTabGroup(final SubTabGroup subTabGroup) {
        this.subTabGroup = subTabGroup;
    }

    public SubTab getSubTabResults() {
        return subTabResults;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Tab getTabListado() {
        return tabListado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
