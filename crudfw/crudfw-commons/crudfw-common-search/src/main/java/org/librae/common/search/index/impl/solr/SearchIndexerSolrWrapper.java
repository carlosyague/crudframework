package org.librae.common.search.index.impl.solr;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.librae.common.exception.LibraeException;
import org.librae.common.search.index.ISearchIndexer;
import org.librae.common.search.query.IQuery;

/**
 * Adaptador entre ISearchIndexer y los distintos SearchIndexers de Apache Solr
 * 
 * @author cyague
 */
public abstract class SearchIndexerSolrWrapper<T, PK extends Serializable>
        implements ISearchIndexer<T, PK> {

    protected final Log log = LogFactory.getLog(getClass());

    public SearchIndexerSolrWrapper() {
        try {
            final ResourceBundle bundle = ResourceBundle
                    .getBundle("search-server");

            if (bundle != null) {
                // solrServer = bundle.getString(XML_SEARCH_SERVER_HOST);
                // solrPort = bundle.getString(XML_SEARCH_SERVER_PORT);
                // solrContextName =
                // bundle.getString(XML_SEARCH_SERVER_CONTEXT);

                // log.debug("Usign this search server: solrServer=" +
                // solrServer
                // + " solrPort=" + solrPort + " solrContextName="
                // + solrContextName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Apache Solr. Hostname
     */
    protected static String solrServer      = null;

    /**
     * Apache Solr. Port
     */
    protected static String solrPort;

    /**
     * Apache Solr. Webapp Context
     */
    protected static String solrContextName = null;

    /**
     * adds an object to index it in Apache Solr
     * 
     * @param obj
     * @throws LibraeException
     */
    abstract protected void addObjectToSolr(T obj) throws LibraeException;

    /**
     * Gets a xml string from a query
     * 
     * @param q
     * @return
     * @throws LibraeException
     */
    abstract protected String getXmlStringToSolr(String q)
            throws LibraeException;

    public String getXmlString(String q) throws LibraeException {
        return getXmlStringToSolr(q);
    }

    /**
     * adds an object to index it in the search server
     * 
     * @param obj
     * @throws LibraeException
     */
    public void addObjectToSearchServer(T obj) throws LibraeException {
        addObjectToSolr(obj);
    }

    /**
     * gets an array object lists from the indexed schema in Apache Solr
     * 
     * @param obj
     * @return
     * @throws LibraeException
     */
    abstract protected List<T> getObjectsFromSolr(T obj) throws LibraeException;

    /**
     * gets an array object lists from the indexed schema in the search server
     * 
     * @param o
     * @return
     * @throws LibraeException
     */
    public List<T> getObjectsFromSearchServer(T obj) throws LibraeException {
        return getObjectsFromSolr(obj);
    }

    /**
     * gets an array object lists from the indexed schema in Apache Solr
     * 
     * @param obj
     * @param query
     * @return
     * @throws LibraeException
     */
    abstract protected List<T> getObjectsFromSolr(T obj, SolrQuery query)
            throws LibraeException;

    /**
     * gets an array object lists from the indexed schema in Apache Solr
     * 
     * @param obj
     * @param query
     * @return
     * @throws LibraeException
     */
    abstract protected List<T> getObjectsFromSolr(T obj, String query)
            throws LibraeException;

    /**
     * gets an array object lists from the indexed schema in the search server
     * 
     * @param o
     * @param query
     * @return
     * @throws LibraeException
     */
    public List<T> getObjectsFromSearchServer(T obj, IQuery query)
            throws LibraeException {
        return getObjectsFromSolr(obj, (SolrQuery) query.toNativeImpl());
    }

    /**
     * gets an array object lists from the indexed schema in the search server
     * 
     * @param o
     * @param query
     * @return
     * @throws LibraeException
     */
    public List<T> getObjectsFromSearchServer(T obj, String query)
            throws LibraeException {
        return getObjectsFromSolr(obj, query);
    }

    /**
     * removes an existing object in Apache Solr
     * 
     * @param id
     * @throws LibraeException
     */
    abstract public void deleteById(PK id) throws LibraeException;

    /**
     * removes an existing object in Apache Solr
     * 
     * @param query
     * @throws LibraeException
     */
    abstract public void deleteByQuery(String query) throws LibraeException;

    /**
     * build physical url of Apache Solr
     * 
     * @return for example: http://localhots:8983/solr
     */
    protected String buildSolrUrl() {
        StringBuffer sb = new StringBuffer();
        sb.append("http://").append(solrServer).append(":").append(solrPort)
                .append("/").append(solrContextName);
        return sb.toString();
    }

    /**
     * build physical url of Apache Solr by request handler
     * 
     * @param requestHandler
     *            (update | select)
     * @return for example: http://localhots:8983/solr/update
     */
    protected String buildSolrUrl(String requestHandler) {
        StringBuffer sb = new StringBuffer();
        sb.append(buildSolrUrl()).append("/").append(requestHandler);
        return sb.toString();
    }

    protected String buildSolrUrl(String requestHandler, String solrServer,
            String solrPort, String solrContextName) {
        StringBuffer sb = new StringBuffer();
        sb.append("http://").append(solrServer).append(":").append(solrPort)
                .append("/").append(solrContextName).append("/").append(
                        requestHandler);
        return sb.toString();
    }

    protected String buildSolrUrl(String solrServer, String solrPort,
            String solrContextName) {
        StringBuffer sb = new StringBuffer();
        sb.append("http://").append(solrServer).append(":").append(solrPort)
                .append("/").append(solrContextName);
        return sb.toString();
    }

}
