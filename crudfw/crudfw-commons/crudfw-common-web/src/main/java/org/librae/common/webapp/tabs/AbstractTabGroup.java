package org.librae.common.webapp.tabs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.context.FacesContext;

import org.apache.myfaces.component.html.ext.HtmlGraphicImage;
import org.apache.myfaces.component.html.ext.HtmlOutputText;
import org.apache.myfaces.component.html.ext.HtmlPanelGrid;
import org.librae.common.exception.MensajesError;

public abstract class AbstractTabGroup implements Serializable {

    /**
     * AbstractTabGroup is Serializable
     */
    private static final long   serialVersionUID      = 1L;
    private static final int    ID_NULO               = -1;

    protected static int        countGroups           = 0;

    protected String            tabDivClass           = TabConstants.TAB_DIV_CLASS;
    protected String            javascriptFunctionTab = TabConstants.DEFAULT_JAVASCRIPT_FUNCTION_TAB;
    protected int               activeTab             = TabConstants.DEFAULT_ACTIVE_TAB;
    protected List<AbstractTab> tabs                  = new ArrayList<AbstractTab>();
    protected String            properties            = MensajesError.PROPERTI_GENERAL;
    protected int               groupId               = -1;
    protected int               id                    = ID_NULO;
    private String              saveInputId           = "";

    /**
     * static methods<br>
     * ==============
     */

    protected static String getCssTab(final String cssTab,
            final String cssStateTab) {
        final StringBuilder sb = new StringBuilder();

        sb.append(cssTab);

        if (cssStateTab != null) {
            sb.append(" ").append(cssStateTab);
        }

        return sb.toString();
    }

    /**
     * public methods<br>
     * ==============
     */

    /**
     * JSF tabs<br>
     * ========
     */

    public HtmlPanelGrid getJSFTab() {
        return getJsfTab(1);
    }

    /**
     * Creates a Tab using <code>&lt;t:panelGrid /&gt;</code> to list a tabGroup
     * 
     * @param indexActiveTab
     * @return
     */
    public abstract HtmlPanelGrid getJsfTab(final int indexActiveTab);

    /**
     * Creates a <code>&lt;t:outputText/&gt;</code> with a javascript block to
     * activate tabs
     * 
     * @return
     */
    public HtmlOutputText getJsfTabGroup() {
        final HtmlOutputText result = new HtmlOutputText();

        result.setValue(getActivateTabJavascript());
        result.setEscape(false);

        return result;
    }

    /**
     * This method has to exist to avoid the following exception when an action
     * is executed:<br>
     * <code>Property 'jsfTabGroup' is not writable on type: org.librae.common.webapp.tabs.AbstractTabGroup</code>
     * 
     * @param jsftabGroup
     */
    public void setJsfTabGroup(final HtmlOutputText jsftabGroup) {
        /**
         * This method has to exist to avoid the following exception when an
         * action is executed:<br>
         * <code>Property 'jsfTabGroup' is not writable on type: org.librae.common.webapp.tabs.AbstractTabGroup</code>
         */
    }

    public String getSaveInputId() {
        return saveInputId;
    }

    public void setSaveInputId(String saveInputId) {
        this.saveInputId = saveInputId;
    }

    public void setSaveInputId(int id) {

        int miId = id;

        if (miId == ID_NULO) {
            saveInputId = "_" + groupId;
        } else {
            miId = miId % 100;
            saveInputId = "_" + miId;
        }
    }

    public Boolean getSaveInputRendered() {
        return (id != ID_NULO);
    }

    public Boolean getSaveInputForceId() {
        return (id != ID_NULO);
    }

    /**
     * actived tab by application's user
     * 
     * @return
     */
    public Integer getActiveTabIndexByUser() {
        Integer result = null;

        try {
            final String param = getSaveInputId();

            result = Integer.parseInt(getRequestParam(param));

        } catch (final NumberFormatException e) {
            result = null;
        }

        if (result == null) {
            result = getActiveTab();
        }

        return result;
    }

    public AbstractTab getActiveTabByUser() {
        AbstractTab result = null;
        final Integer indexActiveTabByUser = getActiveTabIndexByUser();

        if (indexActiveTabByUser != null && indexActiveTabByUser > 0) {
            result = getTab(indexActiveTabByUser);
        }

        return result;
    }

    public String getDefaultActiveTab() {
        return Integer.toString(getActiveTab());
    }

    public void setDefaultActiveTab(final String value) {
        /**
         * This method has to exist to avoid the following exception when an
         * action is executed:<br>
         * <code>Property 'defaultActiveTab' is not writable on type: org.librae.common.webapp.tabs.AbstractTabGroup</code>
         */
    }

    public void disableRenderedTab(AbstractTab tab) {
        tabs.remove(tab);
    }

    /**
     * protected methods<br>
     * ==============
     */

    /**
     * Setups a tabGroup
     */
    protected void setup() {
        AbstractTabGroup.countGroups++;
        groupId = AbstractTabGroup.countGroups;

        setSaveInputId(id);
    }

    protected String getActivateTabJavascript() {
        final StringBuilder sb = new StringBuilder();

        sb.append(TabConstants.BEGIN_JAVASCRIPT_BLOCK);
        sb.append(javascriptFunctionTab).append("(").append("'").append(
                activeTab).append("'").append(",").append("'").append(
                getTabsSize()).append("'").append(",").append("'").append(
                getTabDivClass()).append("'").append(");");
        sb.append(TabConstants.END_JAVASCRIPT_BLOCK);

        return sb.toString();
    }

    protected abstract String getTabDivClass();

    /**
     * Adds a Tab
     * 
     * @param tab
     * @return
     */
    protected int addTab(final AbstractTab tab) {
        tabs.add(tab);

        return getTabsSize();
    }

    protected int getTabsSize() {
        int result = 0;

        if (tabs != null) {
            result = tabs.size();
        }

        return result;
    }

    protected boolean getEnable(final int id) {
        boolean result = true;
        final AbstractTab tab = getTab(id);
        if (tab != null) {
            result = getTab(id).isEnable();
        }
        return result;
    }

    protected int getActiveTab() {
        return activeTab;
    }

    protected void setActiveTab(final int activeTab) {
        this.activeTab = activeTab;
    }

    protected AbstractTab getTab(final int id) {
        AbstractTab result = null;

        if (tabs != null && !tabs.isEmpty() && tabs.size() >= id) {
            result = tabs.get(id - 1);
        }

        return result;
    }

    /**
     * JSF tabs<br>
     * ========
     */

    protected int getPanelGridColumns() {
        return (getTabsSize() * 2) + 1;
    }

    /**
     * creating tabs<br>
     * =============
     */

    protected abstract List<UIComponent> addBeginTabGroupBorder(
            final List<UIComponent> panelGridChildren, final int indexActiveTab);

    protected abstract List<UIComponent> addTab(
            final List<UIComponent> panelGridChildren, final int index,
            final int indexActiveTab);

    protected abstract List<UIComponent> addEndTabGroupBorder(
            final List<UIComponent> panelGridChildren, final int indexActiveTab);

    protected HtmlPanelGrid createTabs(final HtmlPanelGrid panelGrid,
            final int indexActiveTab) {

        List<UIComponent> tabs = panelGrid.getChildren();

        for (int index = 1; index <= getTabsSize(); ++index) {

            if (index == 1) {
                tabs = addBeginTabGroupBorder(tabs, indexActiveTab);
            }

            tabs = this.addTab(tabs, index, indexActiveTab);

            if (index == getTabsSize()) {
                tabs = addEndTabGroupBorder(tabs, indexActiveTab);
            }
        }

        return panelGrid;
    }

    /**
     * Creates a <code>&lt;t:panelGrid /&gt;</code> to show a tab list
     * 
     * @param indexActiveTab
     * @param styleClass
     * @return
     */
    protected HtmlPanelGrid createPanelGridTab(final int indexActiveTab,
            final String styleClass) {
        final HtmlPanelGrid result = new HtmlPanelGrid();
        result.setColumns(getPanelGridColumns());
        result.setColumnClasses(getColumnClasses(indexActiveTab));
        result.setStyleClass(styleClass);
        result.setCellpadding(TabConstants.PANEL_GRID_CELLPADDING);
        result.setCellspacing(TabConstants.PANEL_GRID_CELLSPACING);
        result.setBorder(TabConstants.PANEL_GRID_BORDER);

        return result;
    }

    /**
     * column classes<br>
     * ==============
     */

    protected String addColumnClassesBeginTabGroupBorder(
            final int indexActiveTab) {
        final StringBuilder sb = new StringBuilder();

        if (indexActiveTab != 1) {
            sb.append(TabConstants.STYLE_CLASS_BLANK).append(",");
        }

        return sb.toString();
    }

    protected String addColumnClassesEndTabGroupBorder(final int indexActiveTab) {
        final StringBuilder sb = new StringBuilder();

        if (indexActiveTab != getTabsSize()) {
            sb.append(",").append(TabConstants.STYLE_CLASS_BLANK);
        }

        return sb.toString();
    }

    protected String addColumnClassesTab(final int index,
            final int indexActiveTab, final AbstractTab currentTab) {
        final StringBuilder sb = new StringBuilder();

        if (index == indexActiveTab) {
            sb.append(TabConstants.STYLE_CLASS_BLANK).append(",");

            sb.append(
                    AbstractTabGroup.getCssTab(
                            TabConstants.STYLE_CLASS_TITULO_ACTIVA, currentTab
                                    .getCssColor())).append(",");
            sb.append(TabConstants.STYLE_CLASS_BLANK);
        } else {
            sb.append(AbstractTabGroup.getCssTab(
                    TabConstants.STYLE_CLASS_TITULO, currentTab.getCssColor()));

            if ((index < getTabsSize()) && index + 1 != indexActiveTab) {
                sb.append(",").append(TabConstants.STYLE_CLASS_BLANK);
            }
        }

        return sb.toString();
    }

    /**
     * @param indexActiveTab
     *            like "blank,titulo_activa,blank,titulo,blank"
     * @return
     */
    protected String getColumnClasses(final int indexActiveTab) {
        final StringBuilder sb = new StringBuilder();

        for (int index = 1; index <= getTabsSize(); ++index) {

            if (index == 1) {
                sb.append(addColumnClassesBeginTabGroupBorder(indexActiveTab));
            }

            sb
                    .append(addColumnClassesTab(index, indexActiveTab,
                            getTab(index)));

            if (index == getTabsSize()) {
                sb.append(addColumnClassesEndTabGroupBorder(indexActiveTab));
            } else if (index < getTabsSize()) {
                sb.append(",");
            }

        }

        return sb.toString();
    }

    /**
     * Gets tab name<br>
     * + multi-language ??propertyLabel??<br>
     * + appends list size ()
     * 
     * @param id
     * @return
     */
    protected String getTabName(final int id) {
        String result = null;
        String key = null;
        final AbstractTab tab = getTab(id);

        // get property key
        if (tab != null) {
            key = tab.getPropertyLabel();
        }

        final boolean useSubsistemaProperties = (key != null)
                && (properties != null);
        final boolean useCommonProperties = (key != null)
                && (properties == null);

        // get property value
        if (useSubsistemaProperties) {
            result = MensajesError.get(properties, key, true);
        } else if (useCommonProperties) {
            result = MensajesError.get(key, true);
        }

        // if no exists this property..
        if (result == null && key != null) {
            final StringBuilder sb = new StringBuilder();

            sb.append("??").append(key).append("??");

            result = sb.toString();
        }

        // appends list size
        if (tab != null && tab.getAssignedList() != null
                && tab.getAssignedList().size() > 0) {
            final StringBuilder sb = new StringBuilder();

            sb.append(result).append(" (").append(tab.getAssignedList().size())
                    .append(")");

            result = sb.toString();
        }
        // Muestra un numero al lado del nombre la pestaña
        if (tab != null && tab.getNumero() != null
                && (!tab.getNumero().equals(new Long(0)))) {
            final StringBuilder sb = new StringBuilder();
            sb.append(result).append(" (").append(tab.getNumero()).append(")");
            result = sb.toString();
        }

        return result;
    }

    /**
     * JSF methods<br>
     * ===========
     */

    protected HtmlGraphicImage createImage(final String src) {
        final HtmlGraphicImage result = new HtmlGraphicImage();
        result.setValue(src);
        return result;
    }

    protected HtmlOutputText createText(final String value) {
        final HtmlOutputText result = new HtmlOutputText();
        result.setValue(value);
        return result;
    }

    protected HtmlOutputLink createLink(final String value, final String title) {
        final HtmlOutputLink link = new HtmlOutputLink();
        link.setValue(value);
        link.setTitle(title);

        final List<UIComponent> linkChildren = link.getChildren();
        linkChildren.add(createText(title));

        return link;
    }

    protected HtmlOutputLink createLink(final int index, final String title) {
        final HtmlOutputLink link = new HtmlOutputLink();

        final String value = this.getTargetUrlTab(index);

        link.setOnclick(value);
        link.setValue("#");
        link.setTitle(title);

        final List<UIComponent> linkChildren = link.getChildren();
        linkChildren.add(createText(title));

        return link;
    }

    protected List<UIComponent> addLinkOrText(final int index,
            final String tabName, final List<UIComponent> children) {

        final List<UIComponent> result = children;
        final boolean enable = getEnable(index);

        if (enable) {
            result.add(this.createLink(index, tabName));
        } else {
            result.add(createText(tabName));
        }

        return result;
    }

    /**
     * target URLs<br>
     * ===========
     */

    protected String getTargetUrlTab(final int activeTab) {

        return this.getTargetUrlTab(activeTab, true, javascriptFunctionTab,
                getTabsSize());
    }

    protected String getTargetUrlTab(final int activeTab, final boolean enable,
            final String javascriptFunction, final int size) {
        final StringBuilder sb = new StringBuilder();

        if (enable) {
            sb.append("javascript:").append(javascriptFunction).append("(")
                    .append("'").append(activeTab).append("'").append(",")
                    .append("'").append(size).append("'").append(",").append(
                            "'").append(getTabDivClass()).append("'").append(
                            getSaveInputJS()).append(");");
        } else {
            sb.append("#");
        }

        return sb.toString();
    }

    private String getSaveInputJS() {
        String result = "";

        if (getSaveInputRendered()) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(",").append("'").append(getSaveInputId()).append("'");
            result = sb2.toString();
        }

        return result;
    }

    private FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    private String getRequestParam(final String param) {
        String result = null;

        result = getFacesContext().getExternalContext()
                .getRequestParameterMap().get(param);

        return result;
    }
}
