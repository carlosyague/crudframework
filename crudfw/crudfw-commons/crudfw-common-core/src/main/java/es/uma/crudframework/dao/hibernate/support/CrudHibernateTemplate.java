package es.uma.crudframework.dao.hibernate.support;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

/**
 * LibraeHibernateTemplate.
 * 
 * @author ingenia
 */
public class CrudHibernateTemplate extends HibernateTemplate {

    /**
     * El HibernateTemplate.
     */
    HibernateTemplate ht;

    /**
     * El fetchSize.
     */
    private int       fetchSize      = 0;

    /**
     * El maxResults.
     */
    private int       maxResults     = 0;

    /**
     * El page.
     */
    private int       page           = 0;

    /**
     * El numRes.
     */
    private int       numRes         = 0;

    /**
     * El orderColumn.
     */
    private String    orderColumn    = "id";

    /**
     * El ascendingOrder.
     */
    private boolean   ascendingOrder = true;

    /**
     * El constructor.
     * 
     * @param htP
     *            the HibernateTemplate
     */
    public CrudHibernateTemplate(final HibernateTemplate htP) {
        this.ht = htP;
    }

    /**
     * Carga la página indicada.
     * 
     * @param entityClass
     *            la clase de la entidad
     * @param pag
     *            la página
     * @param numResP
     *            numero de elementos por página
     * @return listado de elementos de la página
     */
    public final List loadPage(final Class entityClass, final int pag,
            final int numResP) {
        return loadPage(entityClass, pag, numResP, this.getOrderColumn(), this
                .isAscendingOrder());
    }

    /**
     * Carga la página indicada.
     * 
     * @param entityClass
     *            la clase de la entidad
     * @param pag
     *            la página
     * @param numResP
     *            numero de elementos por página
     * @param orderColumnP
     *            columna por la que ordenar
     * @param ascendingOrderP
     *            tipo de orden, ascendente o descendente
     * @return listado de elementos de la página
     */
    public final List loadPage(final Class entityClass, final int pag,
            final int numResP, final String orderColumnP,
            final boolean ascendingOrderP) {
        this.setPage(pag);
        this.setNumRes(numResP);
        this.setOrderColumn(orderColumnP);
        this.setAscendingOrder(ascendingOrderP);
        return (List) this.ht.executeWithNativeSession(new HibernateCallback() {
            public Object doInHibernate(final Session session) {
                final Criteria criteria = session.createCriteria(entityClass);
                prepareCriteria(criteria);
                return criteria.list();
            }
        });
    }

    /**
     * Prepare the given Criteria object, applying cache settings and/or a
     * transaction timeout.
     * 
     * @param criteria
     *            the Criteria object to prepare
     * @see #setCacheQueries
     * @see #setQueryCacheRegion
     * @see SessionFactoryUtils#applyTransactionTimeout
     */
    protected final void prepareCriteria(final Criteria criteria) {
        if (this.ht.isCacheQueries()) {
            criteria.setCacheable(true);
            if (this.ht.getQueryCacheRegion() != null) {
                criteria.setCacheRegion(this.ht.getQueryCacheRegion());
            }
        }
        if (this.ht.getFetchSize() > 0) {
            criteria.setFetchSize(this.ht.getFetchSize());
        }
        if (this.ht.getMaxResults() > 0) {
            criteria.setMaxResults(this.ht.getMaxResults());
        }

        if (this.getPage() > 0) {
            final int firstElem = this.getPage() * this.getNumRes();
            criteria.setFirstResult(firstElem);
        }
        if (this.getNumRes() > 0) {
            criteria.setMaxResults(this.getNumRes());
        }
        if (!this.getOrderColumn().equals("")) {
            if (this.isAscendingOrder()) {
                criteria.addOrder(Order.asc(this.getOrderColumn()));
            } else {
                criteria.addOrder(Order.desc(this.getOrderColumn()));
            }
        }

        SessionFactoryUtils.applyTransactionTimeout(criteria, this.ht
                .getSessionFactory());
    }

    /**
     * FetchSize setter.
     * 
     * @param fetchSizeP
     *            el fetchSize
     */
    public final void setFetchSize(final int fetchSizeP) {
        this.fetchSize = fetchSizeP;
    }

    /**
     * Return the fetch size specified for this HibernateTemplate.
     * 
     * @return the fetchSize
     */
    public final int getFetchSize() {
        return this.fetchSize;
    }

    /**
     * Set the maximum number of rows for this HibernateTemplate. This is
     * important for processing subsets of large result sets, avoiding to read
     * and hold the entire result set in the database or in the JDBC driver if
     * we're never interested in the entire result in the first place (for
     * example, when performing searches that might return a large number of
     * matches).
     * <p>
     * Default is 0, indicating to use the JDBC driver's default.
     * 
     * @param maxResultsP
     *            numer máximo de resultados
     */
    public final void setMaxResults(final int maxResultsP) {
        this.maxResults = maxResultsP;
    }

    /**
     * Return the maximum number of rows specified for this HibernateTemplate.
     * 
     * @return the maxResults
     */
    public final int getMaxResults() {
        return this.maxResults;
    }

    /**
     * @return the page
     */
    public final int getPage() {
        return page;
    }

    /**
     * @param pag
     *            the page to set
     */
    public final void setPage(final int pag) {
        this.page = pag;
    }

    /**
     * @return the numRes
     */
    public final int getNumRes() {
        return numRes;
    }

    /**
     * @param numResP
     *            the numRes to set
     */
    public final void setNumRes(final int numResP) {
        this.numRes = numResP;
    }

    /**
     * @return the orderColumn
     */
    public final String getOrderColumn() {
        return orderColumn;
    }

    /**
     * @param orderColumnP
     *            the orderColumn to set
     */
    public final void setOrderColumn(final String orderColumnP) {
        this.orderColumn = orderColumnP;
    }

    /**
     * @return the ascendingOrder
     */
    public final boolean isAscendingOrder() {
        return ascendingOrder;
    }

    /**
     * @param ascendingOrderP
     *            the ascendingOrder to set
     */
    public final void setAscendingOrder(final boolean ascendingOrderP) {
        this.ascendingOrder = ascendingOrderP;
    }

}
