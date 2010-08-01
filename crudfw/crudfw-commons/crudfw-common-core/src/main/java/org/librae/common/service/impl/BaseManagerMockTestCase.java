package org.librae.common.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jmock.MockObjectTestCase;
import org.librae.common.util.ConvertUtil;

/**
 * BaseManagerMockTestCase class.
 * 
 * @author ingenia
 */
public abstract class BaseManagerMockTestCase extends MockObjectTestCase {
    // ~ Static fields/initializers
    // =============================================

    /**
     * the log.
     */
    protected final Log      log = LogFactory.getLog(getClass());

    /**
     * the resource bundle.
     */
    protected ResourceBundle rb;

    // ~ Constructors
    // ===========================================================

    /**
     * Constructor.
     */
    public BaseManagerMockTestCase() {
        // Since a ResourceBundle is not required for each class, just
        // do a simple check to see if one exists
        final String className = this.getClass().getName();

        try {
            rb = ResourceBundle.getBundle(className);
        } catch (MissingResourceException mre) {
            log.warn("No resource bundle found for: " + className);
        }
    }

    // ~ Methods
    // ================================================================

    /**
     * Utility method to populate a javabean-style object with values from a
     * Properties file.
     * 
     * @param obj
     *            the model object to populate
     * @return Object populated object
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws Exception
     *             if BeanUtils fails to copy properly
     */
    protected final Object populate(final Object obj)
            throws IllegalAccessException, InvocationTargetException {
        // loop through all the beans methods and set its properties from
        // its .properties file
        final Map map = ConvertUtil.convertBundleToMap(rb);

        BeanUtils.copyProperties(obj, map);

        return obj;
    }
}
