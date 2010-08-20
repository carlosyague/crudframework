package org.librae.common.webapp.tabs;

import java.util.List;

import javax.faces.component.UIComponent;

import org.apache.myfaces.component.html.ext.HtmlPanelGrid;

/**
 * Grupo de pestañas.
 * 
 * @author cyague
 */
public class TabGroup extends AbstractTabGroup {

    /**
     * AbstractTabGroup is Serializable
     */
    private static final long serialVersionUID = -8725589925692671027L;

    /**
     * constructors<br>
     * ============
     */

    /**
     */
    public TabGroup() {
        tabDivClass = TabConstants.TAB_DIV_CLASS;
        javascriptFunctionTab = TabConstants.DEFAULT_JAVASCRIPT_FUNCTION_TAB;

        setup();
    }

    /**
     */
    public TabGroup(final int id) {
        tabDivClass = TabConstants.TAB_DIV_CLASS;
        javascriptFunctionTab = TabConstants.DEFAULT_JAVASCRIPT_FUNCTION_TAB;
        this.id = id;

        setup();
    }

    /**
     * @param properties
     */
    public TabGroup(final String properties) {
        tabDivClass = TabConstants.TAB_DIV_CLASS;
        javascriptFunctionTab = TabConstants.DEFAULT_JAVASCRIPT_FUNCTION_TAB;
        this.properties = properties;

        setup();
    }

    /**
     * @param properties
     */
    public TabGroup(final String properties, final int id) {
        tabDivClass = TabConstants.TAB_DIV_CLASS;
        javascriptFunctionTab = TabConstants.DEFAULT_JAVASCRIPT_FUNCTION_TAB;
        this.properties = properties;
        this.id = id;

        setup();
    }

    /**
     * @param tabDivClass
     * @param javascriptFunctionTab
     * @param tabsSize
     */
    protected TabGroup(final String tabDivClass,
            final String javascriptFunctionTab) {
        this.tabDivClass = tabDivClass;
        this.javascriptFunctionTab = javascriptFunctionTab;

        setup();
    }

    /**
     * @param tabDivClass
     * @param javascriptFunctionTab
     * @param tabsSize
     */
    protected TabGroup(final String tabDivClass,
            final String javascriptFunctionTab, final int id) {
        this.tabDivClass = tabDivClass;
        this.javascriptFunctionTab = javascriptFunctionTab;
        this.id = id;

        setup();
    }

    /**
     * @param tabDivClass
     * @param javascriptFunctionTab
     * @param tabNames
     * @param properties
     */
    protected TabGroup(final String tabDivClass,
            final String javascriptFunctionTab, final String properties) {
        this.tabDivClass = tabDivClass;
        this.javascriptFunctionTab = javascriptFunctionTab;
        this.properties = properties;

        setup();
    }

    /**
     * @param tabDivClass
     * @param javascriptFunctionTab
     * @param tabNames
     * @param properties
     */
    protected TabGroup(final String tabDivClass,
            final String javascriptFunctionTab, final String properties,
            final int id) {
        this.tabDivClass = tabDivClass;
        this.javascriptFunctionTab = javascriptFunctionTab;
        this.properties = properties;
        this.id = id;

        setup();
    }

    /**
     * @return
     */
    @Override
    protected String getTabDivClass() {
        return tabDivClass;
    }

    /**
     * JSF Methods<br>
     * ============
     */

    /**
     * FIRST Tab
     */
    @Override
    protected List<UIComponent> addBeginTabGroupBorder(
            final List<UIComponent> panelGridChildren, final int indexActiveTab) {
        final List<UIComponent> result = panelGridChildren;

        if (indexActiveTab != 1) {
            result.add(createImage(TabConstants.IMG_TAB_1ST_DISABLE));
        }

        return result;
    }

    /**
     * LAST Tab
     */
    @Override
    protected List<UIComponent> addEndTabGroupBorder(
            final List<UIComponent> panelGridChildren, final int indexActiveTab) {
        final List<UIComponent> result = panelGridChildren;
        if (indexActiveTab != getTabsSize()) {
            result.add(createImage(TabConstants.IMG_TAB_DISABLE_2));
        }
        return result;
    }

    /**
     * CURRENT Tab
     */
    @Override
    protected List<UIComponent> addTab(
            final List<UIComponent> panelGridChildren, final int index,
            final int indexActiveTab) {
        List<UIComponent> result = panelGridChildren;

        final String tabName = getTabName(index);

        if (index == indexActiveTab) {
            result.add(createImage(TabConstants.IMG_TAB_ENABLE_1));
            result.add(createText(tabName));
            result.add(createImage(TabConstants.IMG_TAB_ENABLE_2));
        } else {
            result = addLinkOrText(index, tabName, panelGridChildren);

            if ((index < getTabsSize()) && index + 1 != indexActiveTab) {
                result.add(createImage(TabConstants.IMG_TAB_DISABLE_2));
            }
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.webapp.tabs.AbstractTabGroup#getJsfTab(int)
     */
    @Override
    public HtmlPanelGrid getJsfTab(final int indexActiveTab) {

        /**
         * creates PanelGridTab
         */
        HtmlPanelGrid panelGrid = createPanelGridTab(indexActiveTab,
                TabConstants.TAB_PANEL_GRID_STYLE_CLASS);

        /**
         * creates JSF Tab List
         */
        panelGrid = createTabs(panelGrid, indexActiveTab);

        return panelGrid;
    }
}
