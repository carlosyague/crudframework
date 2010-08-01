package org.librae.common.webapp.tabs;

import java.io.Serializable;
import java.util.List;

import org.apache.myfaces.component.html.ext.HtmlPanelGrid;
import org.apache.myfaces.custom.div.Div;

public abstract class AbstractTab implements Serializable {
    /**
     * AbstractTab is Serializable
     */
    private static final long  serialVersionUID = 1L;

    protected AbstractTabGroup tabGroup         = null;
    protected int              index            = TabConstants.NON_ID;
    protected String           propertyLabel    = null;
    protected boolean          enable           = true;
    protected String           cssColor         = null;
    protected List             assignedList     = null;
    protected Boolean          rendered         = Boolean.TRUE;
    protected Long             numero           = null;

    /**
     * public methods<br>
     * ==============
     */

    /**
     * Creates a Tab using <code>&lt;t:panelGrid /&gt;</code> to list a tabGroup
     * 
     * @return
     */
    public HtmlPanelGrid getJsfTab() {
        HtmlPanelGrid result = null;

        if (tabGroup != null && index != TabConstants.NON_ID) {
            result = tabGroup.getJsfTab(index);
        }

        return result;
    }

    /**
     * This method has to exist to avoid the following exception when an action
     * is executed:<br>
     * <code>Property 'jsfTab' is not writable on type: org.librae.common.webapp.tabs.Tab</code>
     * 
     * @param jsfTab
     */
    public void setJsfTab(final HtmlPanelGrid jsfTab) {
        /**
         * This method has to exist to avoid the following exception when an
         * action is executed:<br>
         * <code>Property 'jsfTab' is not writable on type: org.librae.common.webapp.tabs.Tab</code>
         */
    }

    public Div getDivTab() {
        final Div result = new Div();

        result.setStyleClass(getDivStyleClass());
        result.setStyle(TabConstants.STYLE_DIV);

        return result;
    }

    /**
     * @param divTab
     */
    public void setDivTab(final Div divTab) {
        /**
         * This method has to exist to avoid the following exception when an
         * action is executed:<br>
         * <code>Property 'divTab' is not writable on type: org.librae.common.webapp.tabs.AbstractTab</code>
         */
    }

    /**
     * Activates this tab
     */
    public void activate() {

        if (tabGroup != null && index != TabConstants.NON_ID) {
            tabGroup.setActiveTab(index);
        }
    }

    public void enable() {
        setEnable(Boolean.TRUE);
    }

    public void disable() {
        setEnable(Boolean.FALSE);
    }

    /**
     * devuelve true si es la última pestaña que activó el usuario
     * 
     * @return
     */
    public Boolean isActiveTabByUser() {
        Boolean result = false;

        if (tabGroup != null) {
            result = tabGroup.getActiveTabByUser().equals(index);
        }

        return result;
    }

    /**
     * get <code>styleClass</code> attribute of <code>&lt;t:div /&gt;<code>
     */
    protected String getDivStyleClass() {
        final StringBuilder sb = new StringBuilder();
        sb.append(tabGroup.getTabDivClass()).append(index);

        return sb.toString();
    }

    /**
     * protected methods<br>
     * ==============
     */

    protected void setup() {
        // add this tab to Group
        index = tabGroup.addTab(this);
    }

    /**
     * getter & setters<br>
     * ================
     */

    /**
     * @return the id
     */
    public int getId() {
        return index;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final int index) {
        this.index = index;
    }

    /**
     * @return the propertyLabel
     */
    public String getPropertyLabel() {
        return propertyLabel;
    }

    /**
     * @param propertyLabel
     *            the propertyLabel to set
     */
    public void setPropertyLabel(final String propertyLabel) {
        this.propertyLabel = propertyLabel;
    }

    /**
     * @return the enable
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * @param enable
     *            the enable to set
     */
    public void setEnable(final boolean enable) {
        this.enable = enable;
    }

    /**
     * @return the cssColor
     */
    public String getCssColor() {
        return cssColor;
    }

    /**
     * @param cssColor
     *            the cssColor to set
     */
    public void setCssColor(final String cssColor) {
        this.cssColor = cssColor;
    }

    /**
     * @return the assignedList
     */
    public List getAssignedList() {
        return assignedList;
    }

    /**
     * @param assignedList
     *            the assignedList to set
     */
    public void setAssignedList(final List assignedList) {
        this.assignedList = assignedList;
    }

    /**
     * @return the rendered
     */
    public Boolean getRendered() {
        return rendered;
    }

    /**
     * @param rendered
     *            the rendered to set
     */
    public void setRendered(Boolean rendered) {
        this.rendered = rendered;

        if (!rendered) {
            tabGroup.disableRenderedTab(this);
        }
    }

    @Override
    public abstract boolean equals(Object o);

    /**
     * @return the numero
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero
     *            the numero to set
     */
    public void setNumero(Long numero) {
        if ((numero != null) && (numero <= 0)) {
            this.numero = null;
        } else {
            this.numero = numero;
        }
    }

}
