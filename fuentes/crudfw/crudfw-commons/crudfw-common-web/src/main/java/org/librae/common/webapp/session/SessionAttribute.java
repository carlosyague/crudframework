package org.librae.common.webapp.session;

import java.io.Serializable;

/**
 * Un atributo de session.
 * 
 * @author srosa
 */
public class SessionAttribute implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -4828416371772661550L;

    /**
     * the timeStamp.
     */
    private Long              timeStamp;

    /**
     * the value.
     */
    private Object            value;

    /**
     * the perecedero.
     */
    private boolean           perecedero;

    /**
     * Default constructor.
     */
    public SessionAttribute() {
        super();
    }

    /**
     * Getter timeStamp property.
     * 
     * @return the timeStamp value.
     */
    public final Long getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * Setter timeStamp property.
     * 
     * @param timeStamp
     *            the timeStamp to set
     */
    public final void setTimeStamp(final Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Getter value object property.
     * 
     * @return the value
     */
    public final Object getValue() {
        return value;
    }

    /**
     * Setter value object property.
     * 
     * @param value
     *            the object to persist
     */
    public final void setValue(final Object value) {
        this.value = value;
    }

    /**
     * Setter value object perecedero.
     * 
     * @param b
     *            indica si el parametro es perecedero o no
     */
    public final void setPerecedero(final boolean b) {
        this.perecedero = b;
    }

    /**
     * Getter value object perecedero.
     * 
     * @return true si es perecedero
     */
    public final boolean isPerecedero() {
        return this.perecedero;
    }

}
