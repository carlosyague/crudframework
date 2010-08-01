package org.librae.common.facade;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Base class for running DAO tests.
 * 
 * @author mraible
 */
public abstract class BaseFacadeTestCase extends
        AbstractTransactionalDataSourceSpringContextTests {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    protected Log          log           = LogFactory.getLog(this.getClass());

    /**
     * ResourceBundle loaded from
     * src/test/resources/${package.name}/ClassName.properties (if exists).
     */
    private ResourceBundle rb;

    /**
     * counter to generate unique values in tests
     */
    private static int     testPKCounter = 0;

    /**
     * Sets AutowireMode to AUTOWIRE_BY_NAME and configures all context files
     * needed to tests DAOs.
     * 
     * @return String array of Spring context files.
     */
    @Override
    protected final String[] getConfigLocations() {
        this
                .setAutowireMode(AbstractDependencyInjectionSpringContextTests.AUTOWIRE_BY_NAME);
        return new String[] {
                "classpath:/spring/applicationContext-resources.xml",
                "classpath*:/spring/applicationContext-dao.xml",
                "classpath*:/spring/applicationContext-facade.xml",
                "classpath*:/spring/applicationContext.xml", // for modular projects
                "classpath:**/spring/applicationContext.xml"  };
    }

    /**
     * @return the rb
     */
    protected final ResourceBundle getRb() {
        return this.rb;
    }

    /**
     * @return the log
     */
    protected final Log getLog() {
        return this.log;
    }

    /**
     * Default constructor - populates "rb" variable if properties file exists
     * for the class in src/test/resources.
     */
    public BaseFacadeTestCase() {
        // Since a ResourceBundle is not required for each class, just
        // do a simple check to see if one exists
        final String className = this.getClass().getName();

        try {
            this.rb = ResourceBundle.getBundle(className);
        } catch (final MissingResourceException mre) {
            this.log.warn("No resource bundle found for: " + className);
        }
    }

    /**
     * Utility method to populate a javabean-style object with values from a
     * Properties file.
     * 
     * @param obj
     *            the model object to populate
     * @return Object populated object if BeanUtils fails to copy properly
     */
    protected final Object populate(final Object obj) {
        // loop through all the beans methods and set its properties from its
        // .properties file
        final Map<String, String> map = new HashMap<String, String>();

        for (final Enumeration<String> keys = this.rb.getKeys(); keys
                .hasMoreElements();) {
            final String key = keys.nextElement();
            map.put(key, this.rb.getString(key));
        }

        BeanUtils.copyProperties(map, obj);

        return obj;
    }

    /**
     * Create a HibernateTemplate from the SessionFactory and call flush() and
     * clear() on it. Designed to be used after "save" methods in tests:
     * http://issues.appfuse.org/browse/APF-178.
     */
    protected final void flush() {
        final HibernateTemplate hibernateTemplate = new HibernateTemplate(
                (SessionFactory) this.applicationContext
                        .getBean("sessionFactory"));
        hibernateTemplate.flush();
        hibernateTemplate.clear();
    }

    private int next() {
        BaseFacadeTestCase.testPKCounter++;
        return BaseFacadeTestCase.testPKCounter;
    }

    /**
     * Returns a unique value
     * 
     * @param pk
     * @return
     */
    protected String unique(final String pk) {
        return pk + this.next();
    }
}
