package com.camunda.jsf.tools.urlparameter;

/**
 * Class UrlParameter.
 * 
 * @author ingenia
 */
public class UrlParameter {

    /**
     * The constructor.
     */
    public UrlParameter() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param name
     *            the name
     * @param managedBeanName
     *            the managedBeanName
     * @param managedBeanClass
     *            the managedBeanClass
     * @param managedBeanMethod
     *            the managedBeanMethod
     */
    public UrlParameter(final String name, final String managedBeanName,
            final Class managedBeanClass, final String managedBeanMethod) {
        this.name = name;
        this.managedBeanName = managedBeanName;
        this.managedBeanClass = managedBeanClass;
        this.managedBeanMethod = managedBeanMethod;
    }

    /**
     * the name.
     */
    private String name;

    /**
     * the managedBeanName.
     */
    private String managedBeanName;

    /**
     * the managedBeanClass.
     */
    private Class  managedBeanClass;

    /**
     * the managedBeanMethod.
     */
    private String managedBeanMethod;

    /**
     * Returns the name.
     * 
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the managedBeanMethod.
     * 
     * @return the managedBeanMethod
     */
    public final String getManagedBeanMethod() {
        return managedBeanMethod;
    }

    /**
     * Returns the managedBeanClass.
     * 
     * @return the managedBeanClass
     */
    public final Class getManagedBeanClass() {
        return managedBeanClass;
    }

    /**
     * Returns the managedBeanClass.
     * 
     * @return the managedBeanClass
     */
    public final String getManagedBeanName() {
        return managedBeanName;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the value to set.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the managedBeanMethod.
     * 
     * @param managedBeanMethod
     *            the value to set.
     */
    public final void setManagedBeanMethod(final String managedBeanMethod) {
        this.managedBeanMethod = managedBeanMethod;
    }

    /**
     * Sets the managedBeanClass.
     * 
     * @param managedBeanClass
     *            the value to set.
     */
    public final void setManagedBeanClass(final Class managedBeanClass) {
        this.managedBeanClass = managedBeanClass;
    }

    /**
     * Sets the managedBeanName.
     * 
     * @param managedBeanName
     *            the value to set.
     */
    public final void setManagedBeanName(final String managedBeanName) {
        this.managedBeanName = managedBeanName;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "UrlParameter: " + name + ", " + managedBeanName + ", "
                + managedBeanClass + ", " + managedBeanMethod;
    }

}
