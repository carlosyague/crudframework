package org.librae.common.webapp.tabs;

import java.util.List;

/**
 * Subpestaña.
 * 
 * @author cyague
 */
public class SubTab extends AbstractTab {

    /**
     * AbstractTab is Serializable
     */
    private static final long serialVersionUID = 214052593488352383L;

    /**
     * constructors
     */

    public SubTab(final SubTabGroup subTabGroup, final String propertyLabel) {
        tabGroup = subTabGroup;
        this.propertyLabel = propertyLabel;

        setup();
    }

    public SubTab(final SubTabGroup subTabGroup, final String propertyLabel,
            final boolean enable) {
        tabGroup = subTabGroup;
        this.propertyLabel = propertyLabel;
        this.enable = enable;

        setup();
    }

    public SubTab(final SubTabGroup subTabGroup, final String propertyLabel,
            final String styleClassState) {
        tabGroup = subTabGroup;
        this.propertyLabel = propertyLabel;
        cssColor = styleClassState;

        setup();
    }

    public SubTab(final SubTabGroup subTabGroup, final String propertyLabel,
            final List listDataTableTab) {
        tabGroup = subTabGroup;
        this.propertyLabel = propertyLabel;
        assignedList = listDataTableTab;

        setup();
    }

    public SubTab(final SubTabGroup subTabGroup, final String propertyLabel,
            final boolean enable, final String styleClassState) {
        tabGroup = subTabGroup;
        this.propertyLabel = propertyLabel;
        this.enable = enable;
        cssColor = styleClassState;

        setup();
    }

    public SubTab(final SubTabGroup subTabGroup, final String propertyLabel,
            final boolean enable, final List listDataTableTab) {
        tabGroup = subTabGroup;
        this.propertyLabel = propertyLabel;
        this.enable = enable;
        assignedList = listDataTableTab;

        setup();
    }

    public SubTab(final SubTabGroup subTabGroup, final String propertyLabel,
            final String styleClassState, final List listDataTableTab) {
        tabGroup = subTabGroup;
        this.propertyLabel = propertyLabel;
        cssColor = styleClassState;
        assignedList = listDataTableTab;

        setup();
    }

    public SubTab(final SubTabGroup subTabGroup, final String propertyLabel,
            final boolean enable, final String styleClassState,
            final List listDataTableTab) {
        tabGroup = subTabGroup;
        this.propertyLabel = propertyLabel;
        this.enable = enable;
        cssColor = styleClassState;
        assignedList = listDataTableTab;

        setup();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((getPropertyLabel() == null) ? 0 : getPropertyLabel()
                .hashCode());

        result = prime * result
                + ((getDivTab() == null) ? 0 : getDivTab().hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Boolean result = false;

        if (obj instanceof SubTab) {
            final SubTab objSt = (SubTab) obj;
            result = (index == objSt.index);
        }

        return result;

    }
}
