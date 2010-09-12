package org.librae.common.webapp.tabs;

import java.util.List;

import javax.faces.component.UIComponent;

import org.apache.myfaces.component.html.ext.HtmlPanelGrid;

/**
 * Grupo de subpestañas.
 * 
 * @author cyague
 */
public class SubTabGroup extends AbstractTabGroup {

    /**
     * GenericTabGroup is Serializable
     */
    private static final long serialVersionUID = -6534892552942128341L;

    /**
     * constructors<br>
     * ============
     */

    public SubTabGroup() {
        tabDivClass = TabConstants.SUBTAB_DIV_CLASS;
        javascriptFunctionTab = TabConstants.JAVASCRIPT_FUNCTION_SUBTAB;

        setup();
    }

    public SubTabGroup(final int id) {
        tabDivClass = TabConstants.SUBTAB_DIV_CLASS;
        javascriptFunctionTab = TabConstants.JAVASCRIPT_FUNCTION_SUBTAB;
        this.id = id;

        setup();
    }

    public SubTabGroup(final String properties) {
        tabDivClass = TabConstants.SUBTAB_DIV_CLASS;
        javascriptFunctionTab = TabConstants.JAVASCRIPT_FUNCTION_SUBTAB;
        this.properties = properties;

        setup();
    }

    public SubTabGroup(final String properties, final int id) {
        tabDivClass = TabConstants.SUBTAB_DIV_CLASS;
        javascriptFunctionTab = TabConstants.JAVASCRIPT_FUNCTION_SUBTAB;
        this.properties = properties;
        this.id = id;

        setup();
    }

    public SubTabGroup(final String tabDivClass,
            final String javascriptFunctionTab) {
        this.tabDivClass = tabDivClass;
        this.javascriptFunctionTab = javascriptFunctionTab;

        setup();
    }

    public SubTabGroup(final String tabDivClass,
            final String javascriptFunctionTab, final int id) {
        this.tabDivClass = tabDivClass;
        this.javascriptFunctionTab = javascriptFunctionTab;
        this.id = id;

        setup();
    }

    public SubTabGroup(final String tabDivClass,
            final String javascriptFunctionTab, final String properties) {

        this.tabDivClass = tabDivClass;
        this.javascriptFunctionTab = javascriptFunctionTab;
        this.properties = properties;

        setup();
    }

    public SubTabGroup(final String tabDivClass,
            final String javascriptFunctionTab, final String properties,
            final int id) {

        this.tabDivClass = tabDivClass;
        this.javascriptFunctionTab = javascriptFunctionTab;
        this.properties = properties;
        this.id = id;

        setup();
    }

    /**
     * methods<br>
     * ============
     */

    @Override
    protected String getTabDivClass() {
        final StringBuilder sb = new StringBuilder();

        sb.append(tabDivClass).append(groupId).append("-");

        return sb.toString();
    }

    @Override
    protected List<UIComponent> addBeginTabGroupBorder(
            final List<UIComponent> panelGridChildren, final int indexActiveTab) {
        final List<UIComponent> result = panelGridChildren;

        if (indexActiveTab == 1) {
            // enable
            result.add(createImage(TabConstants.IMG_SUBTAB_1ST_ENABLE));
        } else {
            // disable
            result.add(createImage(TabConstants.IMG_SUBTAB_1ST_DISABLE));
        }

        return result;
    }

    @Override
    protected List<UIComponent> addEndTabGroupBorder(
            final List<UIComponent> panelGridChildren, final int indexActiveTab) {
        final List<UIComponent> result = panelGridChildren;

        if (indexActiveTab == getTabsSize()) {
            // enable
            result.add(createImage(TabConstants.IMG_SUBTAB_LAST_ENABLE));
        } else {
            // disable
            result.add(createImage(TabConstants.IMG_SUBTAB_LAST_DISABLE));
        }

        return result;
    }

    @Override
    protected List<UIComponent> addTab(
            final List<UIComponent> panelGridChildren, final int index,
            final int indexActiveTab) {
        List<UIComponent> result = panelGridChildren;
        final String tabName = getTabName(index);

        if (index == indexActiveTab) {
            panelGridChildren.add(createText(tabName));

            if (index < getTabsSize()) {
                result.add(createImage(TabConstants.IMG_SUBTAB_ENABLE_DISABLE));
            }

        } else {
            result = addLinkOrText(index, tabName, panelGridChildren);

            if (index < getTabsSize() && (index + 1) == indexActiveTab) {
                result.add(createImage(TabConstants.IMG_SUBTAB_DISABLE_ENABLE));
            } else if (index < getTabsSize() && (index + 1) != indexActiveTab) {
                result
                        .add(createImage(TabConstants.IMG_SUBTAB_DISABLE_DISABLE));
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

        final HtmlPanelGrid wrapperPanelGrid = new HtmlPanelGrid();
        wrapperPanelGrid
                .setStyleClass(TabConstants.PANEL_GRID_WRAPPER_STYLE_CLASS);
        final List<UIComponent> wrapperPanelGridChildren = wrapperPanelGrid
                .getChildren();

        /**
         * creates PanelGridTab
         */
        HtmlPanelGrid internalPanelGrid = createPanelGridTab(indexActiveTab,
                TabConstants.SUBTAB_PANEL_GRID_STYLE_CLASS);

        /**
         * creates JSF Tab List
         */
        internalPanelGrid = createTabs(internalPanelGrid, indexActiveTab);

        wrapperPanelGridChildren.add(internalPanelGrid);

        return wrapperPanelGrid;
    }

}
