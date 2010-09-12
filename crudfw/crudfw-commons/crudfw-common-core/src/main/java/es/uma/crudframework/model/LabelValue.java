package es.uma.crudframework.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A simple JavaBean to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user, and a corresponding value to be returned to the server. One
 * example is the <code>&lt;html:options&gt;</code> tag.
 * <p>
 * Note: this class has a natural ordering that is inconsistent with equals.
 *
 * @see org.apache.struts.util.LabelValueBean
 */
public class LabelValue implements Comparable, Serializable {

    /**
     * LabelValue is serializable, so needs a serialVersionUID.
     */
    private static final long      serialVersionUID       = 3689355407466181430L;

    /**
     * Comparator that can be used for a case insensitive sort of
     * <code>LabelValue</code> objects.
     */
    public static final Comparator CASE_INSENSITIVE_ORDER = new Comparator() {
        public int compare(final Object o1,final Object o2) {
            final String label1 = ((LabelValue) o1).getLabel();
            final String label2 = ((LabelValue) o2).getLabel();
            return label1.compareToIgnoreCase(label2);
        }
    };

    // ----------------------------------------------------------- Constructors

    /**
     * Default constructor.
     */
    public LabelValue() {
        super();
    }

    /**
     * Construct an instance with the supplied property values.
     *
     * @param label
     *            The label to be displayed to the user.
     * @param value
     *            The value to be returned to the server.
     */
    public LabelValue(final String label, final String value) {
        this.label = label;
        this.value = value;
    }

    // ------------------------------------------------------------- Properties

    /**
     * The property which supplies the option label visible to the end user.
     */
    private String label;

    /**
     * Returns the label.
     *
     * @return the label
     */
    public final String getLabel() {
        return this.label;
    }

    /**
     * Sets the label.
     *
     * @param label
     *            the label.
     */
    public final void setLabel(final String label) {
        this.label = label;
    }

    /**
     * The property which supplies the value returned to the server.
     */
    private String value;

    /**
     * returns the value.
     *
     * @return the value
     */
    public final String getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     *
     * @param value
     *            the value.
     */
    public final void setValue(final String value) {
        this.value = value;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Compare LabelValueBeans based on the label, because that's the human
     * viewable part of the object.
     *
     * @see Comparable
     * @param o
     *            LabelValue object to compare to
     * @return 0 if labels match for compared objects
     */
    public final int compareTo(final Object o) {
        // Implicitly tests for the correct type, throwing
        // ClassCastException as required by interface
        final String otherLabel = ((LabelValue) o).getLabel();

        return this.getLabel().compareTo(otherLabel);
    }

    /**
     * Return a string representation of this object.
     *
     * @return object as a string
     */
    public final String toString() {
        final StringBuffer sb = new StringBuffer("LabelValue[");
        sb.append(this.label);
        sb.append(", ");
        sb.append(this.value);
        sb.append(']');
        return (sb.toString());
    }

    /**
     * LabelValueBeans are equal if their values are both null or equal.
     *
     * @see java.lang.Object#equals(java.lang.Object)
     * @param obj
     *            object to compare to
     * @return true/false based on whether values match or not
     */
    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof LabelValue)) {
            return false;
        }

        final LabelValue bean = (LabelValue) obj;
        int nil = (this.getValue() == null) ? 1 : 0;
        nil += (bean.getValue() == null) ? 1 : 0;

        if (nil == 2) {
            return true;
        } else if (nil == 1) {
            return false;
        } else {
            return this.getValue().equals(bean.getValue());
        }

    }

    /**
     * The hash code is based on the object's value.
     *
     * @see java.lang.Object#hashCode()
     * @return hashCode
     */
    public final int hashCode() {
        return (this.getValue() == null) ? 17 : this.getValue().hashCode();
    }
}
