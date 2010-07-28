package org.librae.common.webapp.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Clase LIFO encargada de manejar los objetos de sesión.
 *
 * @author srosa
 */
public class BeanSession implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long         serialVersionUID = -1876949753295774637L;

    /**
     * Map of objects to persist on session.
     */
    private final Map<Object, Object> attributes;

    /**
     * Default number of objects to persist on session.
     */
    private static final Integer      SESSION_SIZE     = 500;

    /**
     * Default constructor.
     */
    public BeanSession() {
        this.attributes = new HashMap<Object, Object>(SESSION_SIZE);
    }

    /**
     * Constructor al que se le pasan un Map de atributos.
     *
     * @param atributos
     *            los atributos con los que se quiere inicializar el beanSession
     */
    public BeanSession(final Map<Object, Object> atributos) {
        this.attributes = atributos;
    }

    /**
     * Gets the name of the candidated object to leave the session.
     *
     * @return the name of the key where the object is persisted.
     */
    private String getAttributeNameToRemove() {
        Long older = Long.MAX_VALUE;
        String key = null;
        SessionAttribute value = null;
        String result = null;

        if (attributes.size() == SESSION_SIZE) {
            final Iterator<Object> i = attributes.keySet().iterator();
            while (i.hasNext()) {
                key = (String) i.next();
                value = (SessionAttribute) attributes.get(key);
                if (value.isPerecedero() && value.getTimeStamp() < older) {
                    older = value.getTimeStamp();
                    result = key;
                }
            }
        }
        return result;
    }

    /**
     * Add an perishable object to the session.
     *
     * @param name
     *            the key to identify the object.
     * @param o
     *            the object to persist.
     */
    public final void setAttribute(final String name, final Object o) {
        setAttribute(name, o, true);
    }

    /**
     * Add an object to the session.
     *
     * @param name
     *            the key to identify the object.
     * @param o
     *            the object to persist.
     * @param perecedero
     *            flag to make object permanent
     */
    public final void setAttribute(final String name, final Object o,
            final boolean perecedero) {
        SessionAttribute attribute = (SessionAttribute) attributes.get(name);
        if (attribute == null) {
            attributes.remove(getAttributeNameToRemove());
            attribute = new SessionAttribute();
            attribute.setTimeStamp(System.currentTimeMillis());
            attribute.setValue(o);
            attribute.setPerecedero(perecedero);
            attributes.put(name, attribute);
        } else {
            attribute.setTimeStamp(System.currentTimeMillis());
            attribute.setValue(o);
            attribute.setPerecedero(perecedero);
        }
    }

    /**
     * Get the Attribute persisted on Session.
     *
     * @param name
     *            the key of the map.
     * @return the object.
     */
    public final Object getAttribute(final String name) {
        final SessionAttribute attribute = (SessionAttribute) attributes
                .get(name);
        return attribute == null ? null : attribute.getValue();
    }

    /**
     * Removes the attribute of the session.
     *
     * @param name
     *            the key of the mapped object.
     */
    public final void removeAttribute(final String name) {
        attributes.remove(name);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        String key = null;
        final Iterator<Object> i = attributes.keySet().iterator();
        while (i.hasNext()) {
            key = (String) i.next();
            sb.append('[').append(key).append(" -> ").append(getAttribute(key))
                    .append("]<br/>");
        }
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public final Map<Object, Object> getAttributes() {
        return attributes;
    }

}
