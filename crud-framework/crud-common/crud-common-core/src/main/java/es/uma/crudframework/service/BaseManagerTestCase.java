package es.uma.crudframework.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.uma.crudframework.util.ConvertUtil;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * BaseManagerTestCase class.
 *
 */
public abstract class BaseManagerTestCase extends
        AbstractTransactionalDataSourceSpringContextTests {
    // ~ Static fields/initializers
    // =============================================

    /**
     * the log.
     */
    protected final Log      log = LogFactory.getLog(getClass());

    /**
     * the ResourceBundle.
     */
    protected ResourceBundle rb  = null;

    /**
     * returns the ConfigLocations.
     *
     * @return the ConfigLocations
     */
    protected final String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] { "/spring/applicationContext-resources.xml",
                "classpath:/spring/applicationContext-dao.xml",
                "/spring/applicationContext-service.xml",
                "classpath*:/**/applicationContext.xml",
                "classpath*:earContext.xml",
                "classpath:spring/appCtx-audit-service.xml" };

        // classpath*:/**/applicationContext.xml
        /**
         * has to be used since this file does not exist in AppFuse, but may
         * exist in projects that depend on it
         */
    }

    // ~ Constructors
    // ===========================================================

    /**
     * the constructor.
     */
    public BaseManagerTestCase() {
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