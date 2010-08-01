package org.librae.common.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.persistence.Transient;

import com.thoughtworks.xstream.XStream;

/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public abstract class BaseObject implements Serializable {

    /**
     * usado en los checkboxes de los listados JSF.
     */
    @Transient
    protected Boolean     selected = Boolean.FALSE;
    private final boolean par      = Boolean.FALSE;

    /**
     * usado en la creación temporal de beans que no queremos que sean salvables
     */
    @Transient
    private Boolean       temporal = Boolean.FALSE;

    /**
     * usado en los POJOs con IDs remotos obtenidos por NCIP
     */
    @Transient
    private Long          idNcip   = null;

    /**
     * getter & setters<br>
     * ================
     */

    /**
     * @return the idNcip
     */
    public Long getIdNcip() {
        return idNcip;
    }

    /**
     * @param idNcip
     *            the idNcip to set
     */
    public void setIdNcip(final Long idNcip) {
        this.idNcip = idNcip;
    }

    public Boolean getIsPojoNcip() {
        return (idNcip != null);
    }

    /**
     * @return the selected
     */
    public final Boolean getSelected() {
        return selected;
    }

    /**
     * @param selected
     *            the selected to set
     */
    public final void setSelected(final Boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the temporal
     */
    public final Boolean getTemporal() {
        return temporal;
    }

    /**
     * @param temporal
     *            the temporal to set
     */
    public final void setTemporal(final Boolean temporal) {
        this.temporal = temporal;
    }

    /**
     * Metodo encargado de devolver la clase css que adornara el rowStyleClass
     * del t:dfatatable. En caso de devolver null se hara caso a lo indicado en
     * rowClasses
     * 
     * @return estilo css de tr
     */
    public String getEstilo() {
        String res = "";

        if (getSelected()) {
            res = "FilaSelec";
        }

        return res;
    }

    /**
     * Copia los parámetros de un POJO <code>sourcePojo</code> de tipo
     * <code>sourceClass</code> a la instancia actual (<code>this</code>) de
     * <code>BaseObject</code>
     * 
     * @param sourcePojo
     * @param sourceClass
     */
    protected void copyParamsPojo(final BaseObject sourcePojo,
            final Class sourceClass) {

        final Field[] fields = sourceClass.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            final Field field = fields[i];
            final int modifiers = field.getModifiers();

            if (!Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers)) {

                try {
                    field.setAccessible(true);

                    final Object value = field.get(sourcePojo);
                    field.set(this, value);

                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * mandatory methods<br>
     * =================
     */

    /**
     * Returns a multi-line String with key=value pairs.
     * 
     * @return a String representation of this class.
     */
    @Override
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * 
     * @param o
     *            object to compare to
     * @return true/false based on equality tests
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * 
     * @return hashCode
     */
    @Override
    public abstract int hashCode();

    /**
     * Método usado para convertir POJOs a XML.
     * 
     * @return
     */
    public String toXML() {
        final XStream xstream = new XStream();
        return xstream.toXML(this);
    }

    /**
     * Método usado para convertir un objeto serializado en formato XML a
     * Object.
     * 
     * @param xmlObject
     * @return
     */
    public static Object fromXML(String xmlObject) {
        final XStream xstream = new XStream();
        return xstream.fromXML(xmlObject);
    }

}
