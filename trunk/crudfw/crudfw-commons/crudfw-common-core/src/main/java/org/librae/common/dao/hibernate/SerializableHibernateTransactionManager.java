package org.librae.common.dao.hibernate;

import java.io.ObjectStreamException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

/**
 * Workaround de fallo de serialización existente en Spring 2.5.x y corregido en Spring 3.0.x
 * 
 *
 * @see http://jira.springframework.org/browse/SPR-4662
 * @see http://jira.springframework.org/browse/SPR-2121
 *
 */
public class SerializableHibernateTransactionManager extends
        HibernateTransactionManager implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    private Object writeReplace() throws ObjectStreamException {
        /* * null the non-transient references to these non-serializable objects 
         * * before serialization (so that serialization does not fail). */
        super.setBeanFactory(null);
        super.setDataSource(null);
        return this;
    }

    private Object readResolve() throws ObjectStreamException {
        /* * discard the de-serialized object, since it is missing the beanFactory and dataSource, 
         * * and obtain the bean from Spring, and return it instead. This is ok, since it is stateless. */
        return BeanFactoryUtils.beanOfTypeIncludingAncestors(
                applicationContext, HibernateTransactionManager.class);
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SerializableHibernateTransactionManager.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return SerializableHibernateTransactionManager.applicationContext;
    }

}
