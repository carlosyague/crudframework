package org.librae.common.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.Constants;
import org.librae.common.dao.UniversalDAO;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This class serves as the a class that can CRUD any object witout any Spring
 * configuration. The only downside is it does require casting from Object to
 * the object class.
 * 
 * @author Bryan Noll
 * @deprecated
 */
public class UniversalDAOImpl extends HibernateDaoSupport implements
        UniversalDAO {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * {@inheritDoc}
     */
    public final Object save(final Object o) {
        return getHibernateTemplate().merge(o);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final Object get(final Class clazz, final Serializable id) {
        final Object o = getHibernateTemplate().get(clazz, id);

        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }

        return o;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final List getAll(final Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final void remove(final Class clazz, final Serializable id) {
        getHibernateTemplate().delete(get(clazz, id));
    }
}
