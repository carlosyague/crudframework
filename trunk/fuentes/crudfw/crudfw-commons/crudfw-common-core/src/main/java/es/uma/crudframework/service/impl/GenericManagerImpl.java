package es.uma.crudframework.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.uma.crudframework.dao.GenericDAO;
import es.uma.crudframework.service.GenericManager;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;es.uma.crudframework.service.impl.GenericManagerImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;es.uma.crudframework.dao.hibernate.GenericDAOImpl&quot;&gt;
 *                 &lt;constructor-arg value=&quot;es.uma.crudframework.model.User&quot;/&gt;
 *                 &lt;property name=&quot;sessionFactory&quot; ref=&quot;sessionFactory&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p>
 * If you're using iBATIS instead of Hibernate, use:
 * 
 * <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;es.uma.crudframework.service.impl.GenericManagerImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;es.uma.crudframework.dao.ibatis.GenericDaoiBatis&quot;&gt;
 *                 &lt;constructor-arg value=&quot;es.uma.crudframework.model.User&quot;/&gt;
 *                 &lt;property name=&quot;dataSource&quot; ref=&quot;dataSource&quot;/&gt;
 *                 &lt;property name=&quot;sqlMapClient&quot; ref=&quot;sqlMapClient&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * 
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
}

