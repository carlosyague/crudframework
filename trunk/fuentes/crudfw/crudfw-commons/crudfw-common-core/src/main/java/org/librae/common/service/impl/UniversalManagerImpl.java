package org.librae.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.dao.UniversalDAO;
import org.librae.common.service.UniversalManager;

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @deprecated
 */
public class UniversalManagerImpl implements UniversalManager {
    /**
     * Log instance for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    protected final Log    log = LogFactory.getLog(getClass());

    /**
     * UniversalDAO instance, ready to charge forward and persist to the
     * database
     */
    protected UniversalDAO dao;

    /**
     * sets the dao.
     * 
     * @param dao
     *            the dao
     */
    public void setDao(UniversalDAO dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    public final Object get(final Class clazz, final Serializable id) {
        return dao.get(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    public final List getAll(final Class clazz) {
        return dao.getAll(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public final void remove(final Class clazz, final Serializable id) {
        dao.remove(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    public final Object save(final Object o) {
        return dao.save(o);
    }
}
