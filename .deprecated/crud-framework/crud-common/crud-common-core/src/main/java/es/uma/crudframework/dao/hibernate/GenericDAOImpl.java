package es.uma.crudframework.dao.hibernate;

import es.uma.crudframework.Constants;
import es.uma.crudframework.dao.GenericDAO;
import es.uma.crudframework.dao.hibernate.support.CrudHibernateTemplate;
import es.uma.crudframework.exception.ExceptionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id=&quot;fooDao&quot; class=&quot;es.uma.crudframework.dao.hibernate.GenericDAOImpl&quot;&gt;
 *          &lt;constructor-arg value=&quot;es.uma.crudframework.model.Foo&quot;/&gt;
 *          &lt;property name=&quot;sessionFactory&quot; ref=&quot;sessionFactory&quot;/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericDAOImpl<T, PK extends Serializable> extends
        HibernateDaoSupport implements GenericDAO<T, PK> {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging.
     */
    protected final Log    log = LogFactory.getLog(this.getClass());

    /**
     * persistentClass.
     */
    private final Class<T> persistentClass;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * 
     * @param persistentClassP
     *            the class type you'd like to persist
     */
    public GenericDAOImpl(final Class<T> persistentClassP) {
        this.persistentClass = persistentClassP;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final List<T> getAll() {
        List<T> result = null;
        try {
            result = super.getHibernateTemplate().loadAll(this.persistentClass);
        } catch (final Exception e) {
            this.log
                    .error("ERROR al obtener el listado completo en BD:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_GET_ALL_BD", e);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final List<T> getAllDistinct() {
        final Collection result = new LinkedHashSet(this.getAll());
        return new ArrayList(result);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final List<T> findByNamedQuery(final String queryName,
            final Map<String, Object> queryParams) {
        final String[] params = new String[queryParams.size()];
        final Object[] values = new Object[queryParams.size()];

        int index = 0;
        for (final String s : queryParams.keySet()) {
            params[index] = s;
            values[index++] = queryParams.get(s);
        }

        return this.getHibernateTemplate().findByNamedQueryAndNamedParam(
                queryName, params, values);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final List<T> getPage(final int pag, final int numRes) {
        List<T> result = null;
        try {
            final HibernateTemplate ht = super.getHibernateTemplate();
            final CrudHibernateTemplate lht = new CrudHibernateTemplate(ht);
            result = lht.loadPage(this.persistentClass, pag, numRes);
        } catch (final Exception e) {
            this.log.error("ERROR al obtener la página " + pag + " en BD:\n "
                    + e);
            throw ExceptionUtil.crearCrudException("ERROR_GET_ALL_BD", e);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final List<T> getPage(final int pag, final int numRes,
            final String orderColumn, final boolean ascendingOrder) {
        List<T> result = null;
        try {
            final HibernateTemplate ht = super.getHibernateTemplate();

            final CrudHibernateTemplate lht = new CrudHibernateTemplate(ht);
            result = lht.loadPage(this.persistentClass, pag, numRes,
                    orderColumn, ascendingOrder);
        } catch (final Exception e) {
            this.log.error("ERROR al obtener la página " + pag + " en BD:\n "
                    + e);
            throw ExceptionUtil.crearCrudException("ERROR_GET_ALL_BD", e);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public final Long countAll() {
        Long result = 0L;
        try {
            final HibernateTemplate ht = super.getHibernateTemplate();
            final List<?> datos = ht.find("select count(*) from "
                    + this.persistentClass.getName());
            if (datos.size() == 1) {
                result = (Long) datos.get(0);
            }
        } catch (final Exception e) {
            this.log
                    .error("ERROR al obtener el número de elementos del listado completo en BD:\n "
                            + e);
            throw ExceptionUtil.crearCrudException("ERROR_GET_ALL_BD", e);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final T get(final PK id) {
        final T entity = (T) super.getHibernateTemplate().get(
                this.persistentClass, id);
        try {
            if (entity == null) {
                this.log.warn("Uh oh, '" + this.persistentClass
                        + "' object with id '" + id + "' not found...");
                throw ExceptionUtil.crearCrudException("ERROR_SELECT_BD",
                        new ObjectRetrievalFailureException(
                                this.persistentClass, id));
            }
        } catch (final DataAccessException e) {
            this.log.error("ERROR al obtener en BD:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_SELECT_BD", e);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final boolean exists(final PK id) {
        boolean result = false;

        try {
            final T entity = (T) super.getHibernateTemplate().get(
                    this.persistentClass, id);

            result = (entity != null);

        } catch (final DataAccessException e) {
            this.log
                    .error("ERROR al comprobar la existencia de un objeto en BD:\n "
                            + e);
            throw ExceptionUtil.crearCrudException("ERROR_EXISTS_BD", e);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final T save(final T object) {
        T result = null;

        try {
            result = (T) super.getHibernateTemplate().merge(object);
        } catch (final DataAccessException e) {
            this.log.error("ERROR al guardar en BD:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_INSERT_BD", e);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final T merge(final T object) {
        try {
            return (T) this.getHibernateTemplate().merge(object);
        } catch (final DataAccessException e) {
            this.log.error("ERROR al guardar en BD con merge:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_INSERT_BD", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void remove(final PK id) {
        try {
            super.getHibernateTemplate().delete(this.get(id));
        } catch (final Exception e) {
            this.log.error("ERROR al eliminar en BD:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_DELETE_BD", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void evict(final T obj) {
        try {
            this.getHibernateTemplate().evict(obj);
        } catch (final DataAccessException e) {
            this.log.error("ERROR en clear en hibernate:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_CLEAR_BD", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void clear() {
        try {
            this.getHibernateTemplate().clear();
        } catch (final DataAccessException e) {
            this.log.error("ERROR en clear en hibernate:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_CLEAR_BD", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings(Constants.SUPPRESS_WARNINGS_UNCHECKED)
    public final List<T> execSQL(final String sql) {
        return this.getORMSession().createSQLQuery(sql).list();
    }

    /**
     * {@inheritDoc}
     */
    public final Session getORMSession() {
        try {
            if (this.getSessionFactory().getCurrentSession().isOpen()) {
                return this.getSessionFactory().getCurrentSession();
            } else {
                return this.getSessionFactory().openSession();
            }
        } catch (final DataAccessException e) {
            this.log.error("ERROR en getSessionOpen en hibernate:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_GET_SESSION_BD", e);
        }

    }

    /**
     * {@inheritDoc}
     */
    public final void flush() {
        try {
            this.getHibernateTemplate().flush();
        } catch (final DataAccessException e) {
            this.log.error("ERROR en flush en BD:\n " + e);
            throw ExceptionUtil.crearCrudException("ERROR_FLUSH_BD", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getResults(final String table, final String label,
            final String[] conditions, final Object[] params) {
        final StringBuilder hql = new StringBuilder();

        final String whereCondition = this.createWhereCondition(conditions);

        for (int i = 0; i < params.length; ++i) {
            this.log.debug("param (i=" + i + "): " + params[i]);
        }

        hql.append("from ").append(table).append(" ").append(label).append(" ")
                .append(whereCondition);

        this.log.debug("getResults(hql=" + hql.toString());

        final List<T> result = this.getHibernateTemplate().find(hql.toString(),
                params);

        this.log.debug(result.size() + " results.");

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public String createCondition(final String label, final String column) {
        final StringBuilder result = new StringBuilder();

        result.append(label).append(".").append(column).append(" = ?");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String createCondition(final String label, final String column,
            final String function) {
        final StringBuilder result = new StringBuilder();

        result.append(function).append("(").append(label).append(".").append(
                column).append(") = ?");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String createWhereCondition(final String[] conditions) {
        final StringBuilder result = new StringBuilder();

        result.append(" where ");

        for (int i = 0; i < conditions.length; ++i) {

            result.append(conditions[i]);

            if (i + 1 < conditions.length) {
                result.append(" and ");
            }
        }

        return result.toString();
    }
}
