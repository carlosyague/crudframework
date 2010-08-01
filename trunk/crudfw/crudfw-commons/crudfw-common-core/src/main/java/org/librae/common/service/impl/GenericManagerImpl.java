package org.librae.common.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.dao.GenericDAO;
import org.librae.common.model.SpringRemotableLazyEntity;
import org.librae.common.service.GenericManager;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;org.librae.common.service.impl.GenericManagerImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;org.librae.common.dao.hibernate.GenericDAOImpl&quot;&gt;
 *                 &lt;constructor-arg value=&quot;org.librae.common.model.User&quot;/&gt;
 *                 &lt;property name=&quot;sessionFactory&quot; ref=&quot;sessionFactory&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p>
 * If you're using iBATIS instead of Hibernate, use:
 * 
 * <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;org.librae.common.service.impl.GenericManagerImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;org.librae.common.dao.ibatis.GenericDaoiBatis&quot;&gt;
 *                 &lt;constructor-arg value=&quot;org.librae.common.model.User&quot;/&gt;
 *                 &lt;property name=&quot;dataSource&quot; ref=&quot;dataSource&quot;/&gt;
 *                 &lt;property name=&quot;sqlMapClient&quot; ref=&quot;sqlMapClient&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericManagerImpl<T, PK extends Serializable> implements
        GenericManager<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    protected final Log         log = LogFactory.getLog(getClass());

    /**
     * GenericDAO instance, set by constructor of this class.
     */
    protected GenericDAO<T, PK> genericDAO;

    /**
     * Public constructor for creating a new GenericManagerImpl.
     * 
     * @param genericDao
     *            the genericDao to use for persistence
     */
    public GenericManagerImpl(final GenericDAO<T, PK> genericDao) {
        this.genericDAO = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public Long countAll() {
        return genericDAO.countAll();
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return genericDAO.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getAllXml() {
        return genericDAO.getAllXml();
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAllNonLazy() {
        final List<T> result = toNonLazyEntities(genericDAO.getAll());

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getPage(final int pag, final int numRes) {
        return genericDAO.getPage(pag, numRes);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getPage(final int pag, final int numRes,
            final String orderColumn, final boolean ascendingOrder) {
        return genericDAO.getPage(pag, numRes, orderColumn, ascendingOrder);
    }

    /**
     * {@inheritDoc}
     */
    public T get(final PK id) {
        return genericDAO.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public String getXml(final PK id) {
        return genericDAO.getXml(id);
    }

    /**
     * {@inheritDoc}
     */
    public T getNonLazy(PK id) {
        final T result = toNonLazyEntity(genericDAO.get(id));

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(final PK id) {
        return genericDAO.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(final T object) {
        return genericDAO.save(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(final PK id) {
        genericDAO.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> xmlToEntities(List<String> xmlObjects) {
        return genericDAO.xmlToEntities(xmlObjects);
    }

    /**
     * Converts a possible Lazy entity to Non-Lazy
     */
    private T toNonLazyEntity(T entity) {
        T result = entity;

        if (entity instanceof SpringRemotableLazyEntity) {
            final SpringRemotableLazyEntity resultNonLazy = (SpringRemotableLazyEntity) entity;

            final SpringRemotableLazyEntity copyOfResultNonLazy = (SpringRemotableLazyEntity) resultNonLazy
                    .cloneNonLazy();

            result = (T) copyOfResultNonLazy;
        }

        return result;
    }

    /**
     * Converts a possible Lazy entities List to Non-Lazy entities list
     */
    public List<T> toNonLazyEntities(List<T> entities) {
        List<T> result = null;

        if (entities != null) {
            result = new ArrayList<T>();
            final Iterator<T> it = entities.iterator();

            while (it.hasNext()) {
                final T entity = it.next();
                result.add(toNonLazyEntity(entity));
            }
        }

        return result;
    }
}
