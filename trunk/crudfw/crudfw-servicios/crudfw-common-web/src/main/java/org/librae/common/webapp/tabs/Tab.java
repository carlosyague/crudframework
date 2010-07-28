package org.librae.common.webapp.tabs;

import java.util.List;

/**
 * Pestaña.
 * 
 * @author cyague
 */
public class Tab extends AbstractTab {

    /**
     * AbstractTab is Serializable
     */
    private static final long serialVersionUID = 140723512823575780L;

    /**
     * constructors
     */

    public Tab(final TabGroup tabGroup, final String propertyLabel) {
        this.tabGroup = tabGroup;
        this.propertyLabel = propertyLabel;

        setup();
    }

    public Tab(final TabGroup tabGroup, final String propertyLabel,
            final boolean enable) {
        this.tabGroup = tabGroup;
        this.propertyLabel = propertyLabel;
        this.enable = enable;

        setup();
    }

    public Tab(final TabGroup tabGroup, final String propertyLabel,
            final String cssColor) {
        this.tabGroup = tabGroup;
        this.propertyLabel = propertyLabel;
        this.cssColor = cssColor;

        setup();
    }

    public Tab(final TabGroup tabGroup, final String propertyLabel,
            final List assignedList) {
        this.tabGroup = tabGroup;
        this.propertyLabel = propertyLabel;
        this.assignedList = assignedList;

        setup();
    }

    public Tab(final TabGroup tabGroup, final String propertyLabel,
            final boolean enable, final String cssColor) {
        this.tabGroup = tabGroup;
        this.propertyLabel = propertyLabel;
        this.enable = enable;
        this.cssColor = cssColor;

        setup();
    }

    public Tab(final TabGroup tabGroup, final String propertyLabel,
            final boolean enable, final List assignedList) {
        this.tabGroup = tabGroup;
        this.propertyLabel = propertyLabel;
        this.enable = enable;
        this.assignedList = assignedList;

        setup();
    }

    public Tab(final TabGroup tabGroup, final String propertyLabel,
            final String cssColor, final List assignedList) {
        this.tabGroup = tabGroup;
        this.propertyLabel = propertyLabel;
        this.cssColor = cssColor;
        this.assignedList = assignedList;

        setup();
    }

    public Tab(final TabGroup tabGroup, final String propertyLabel,
            final boolean enable, final String cssColor, final List assignedList) {
        this.tabGroup = tabGroup;
        this.propertyLabel = propertyLabel;
        this.enable = enable;
        this.cssColor = cssColor;
        this.assignedList = assignedList;

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

        if (obj instanceof Tab) {
            final Tab objSt = (Tab) obj;
            result = (index == objSt.index);
        }

        return result;

    }

}
