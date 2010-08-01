package org.librae.common.search.query.solr.v1_3;

import org.apache.solr.client.solrj.SolrQuery;
import org.librae.common.search.query.IQuery;

/**
 * @author cyague
 */
public class QuerySolrWrapper implements IQuery {

    private SolrQuery query = null;

    public QuerySolrWrapper() {
        this.query = new SolrQuery();
    }

    public QuerySolrWrapper(SolrQuery query) {
        this.query = query;
    }

    public QuerySolrWrapper setQuery(String query) {
        final SolrQuery solrQuery = this.query.setQuery(query);

        return new QuerySolrWrapper(solrQuery);
    }

    public void addSortField(String field) {
        addSortField(field, true);
    }

    public void addSortField(String field, boolean ascending) {

        final SolrQuery.ORDER orden = ascending ? SolrQuery.ORDER.asc
                : SolrQuery.ORDER.desc;
        query.addSortField(field, orden);
    }

    /**
     * @param value
     */
    public void setFacet(boolean value) {
        query.setFacet(value);
    }

    /**
     * @param value
     */
    public void setFacetMinCount(int value) {
        query.setFacetMinCount(value);
    }

    /**
     * @param value
     */
    public void setFacetLimit(int value) {
        query.setFacetLimit(value);
    }

    /**
     * @param field
     */
    public void addFacetField(String field) {
        query.addFacetField(field);
    }

    /**
     * gets the native implementation of this IQuery object
     *
     * @return
     */
    public Object toNativeImpl() {
        return query;
    }

}
