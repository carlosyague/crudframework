package org.librae.common.search.index.impl.solr.v1_3;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrException;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.search.index.Constants;
import org.librae.common.search.index.ISearchIndexer;
import org.librae.common.search.index.impl.solr.SearchIndexerSolrWrapper;
import org.librae.common.search.query.IQuery;

/**
 * @param <T>
 *            pojo class
 * @param <PK>
 *            primary key class
 * @author cyague
 */
public class SearchIndexerSolr<T, PK extends Serializable> extends SearchIndexerSolrWrapper<T, PK> {

	private static final String VARIABLE_SOLR_COMMIT = "solr.instant.commmit";
	private SolrServer server = null;
	private String solrServer;
	private String solrPort;
	private String solrContextName;
	private Boolean instantCommit = true;

	public SearchIndexerSolr() {

		super();
	}

	public String toString() {
		return "solrServer = " + solrServer + " solrPort = " + solrPort + " solrContextName = " + solrContextName;
	}

	/**
	 * returns an reusable instance of Solr Server
	 * 
	 * @return
	 */
	private SolrServer getSolrServer() throws LibraeException {

		// server instance can be reused

		if (server == null) {
			URL url;
			try {
				url = new URL(buildSolrUrl(solrServer, solrPort, solrContextName));
				server = createConfiguratedHttpSolrServer(url);
			} catch (MalformedURLException e) {
				throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
			}
		}

		return server;
	}

	protected String getXmlStringToSolr(String q) throws LibraeException {

		return getObjectsFromSolrQueryString(q);
	}

	/**
	 * returns a configurated Http Solr Server
	 * 
	 * @param url
	 * @return
	 * @throws LibraeException
	 */
	private SolrServer createConfiguratedHttpSolrServer(URL url) throws LibraeException {

		final CommonsHttpSolrServer httpSolrServer = new CommonsHttpSolrServer(url);

		httpSolrServer.setSoTimeout(1000); // socket read timeout
		// httpSolrServer.setConnectionTimeout(100);
		httpSolrServer.setDefaultMaxConnectionsPerHost(100);
		httpSolrServer.setMaxTotalConnections(100);
		httpSolrServer.setFollowRedirects(false); // defaults to false
		// allowCompression defaults to false.
		// Server side must support gzip or deflate for this to have any effect.
		httpSolrServer.setAllowCompression(true);
		httpSolrServer.setMaxRetries(1); // defaults to 0. > 1 not
		// recommended.

		httpSolrServer.setParser(new XMLResponseParser());

		return httpSolrServer;

	}

	protected void addStringObjectToSearchServer(T obj) throws LibraeException {
		// TODO Auto-generated method stub

	}

	/**
	 * adds an object to index it in Apache Solr 1.3
	 * 
	 * @param obj
	 * @throws LibraeException
	 */
	@Override
	protected void addObjectToSolr(T obj) throws LibraeException {
		if (obj instanceof java.lang.String) {
			addXmlStringToSolr((String) obj);
		} else {
			addPojoToSolr(obj);
		}
	}

	@Override
	protected List<T> getObjectsFromSolr(T obj) throws LibraeException {

		return getPojosFromSolr(obj);

	}

	@Override
	protected List<T> getObjectsFromSolr(T obj, SolrQuery query) throws LibraeException {

		return getPojosFromSolr(obj, query);
	}

	@Override
	protected List<T> getObjectsFromSolr(T obj, String query) throws LibraeException {

		return getPojosFromSolr(obj, query);
	}

	/**
	 * removes an existing object in Apache Solr 1.3
	 * 
	 * @param id
	 * @throws LibraeException
	 */
	@Override
	public void deleteById(PK id) throws LibraeException {

		log.debug("INI id=" + id);
		final SolrServer server = getSolrServer();
		try {
			final UpdateResponse ur = server.deleteById("" + id);
			log.debug("Response: ur=" + ur + " status=" + ur.getStatus());
			server.commit();
			log.debug("Commit done.");
		} catch (SolrServerException e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		} catch (IOException e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		}
	}

	/**
	 * remove a lists of objects in Apache Solr 1.3
	 * 
	 * @param id
	 * @throws LibraeException
	 */
	@Override
	public void deleteByQuery(String query) throws LibraeException {

		log.debug("INI query=" + query);
		final SolrServer server = getSolrServer();
		try {
			final UpdateResponse ur = server.deleteByQuery(query);
			log.debug("Response: ur=" + ur + " status=" + ur.getStatus());
			server.commit();
			log.debug("Commit done.");
		} catch (SolrServerException e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		} catch (IOException e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		}
	}

	/**
	 * adds a POJO object to index it in Apache Solr 1.3
	 * 
	 * @param pojo
	 * @throws LibraeException
	 */
	private void addPojoToSolr(T pojo) throws LibraeException {

		log.debug("INI pojo=" + pojo);
		try {
			final SolrServer server = getSolrServer();
			final UpdateResponse ur = server.addBean(pojo);
			log.debug("Response: ur=" + ur + " status=" + ur.getStatus());
			server.commit();
			log.debug("Commit done.");

		} catch (SolrException e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		} catch (Exception e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		}
	}

	/**
	 * gets an array object lists from the indexed schema in Apache Solr 1.3
	 * 
	 * @param obj
	 * @return
	 * @throws LibraeException
	 */
	private List<T> getPojosFromSolr(T form) throws LibraeException {
		List<T> beans = null;

		log.debug("INI form=" + form);
		try {
			final SolrServer server = getSolrServer();

			// construct a query
			final SolrQuery query = new SolrQuery();

			final String queryLucene = ReflectionUtils.createQueryLucene(form, IQuery.BOOLEAN_OPERATOR_OR);
			final String sortFieldLucene = ReflectionUtils.getSortField(form);

			query.setQuery(queryLucene);
			query.addSortField(sortFieldLucene, SolrQuery.ORDER.asc);

			if (server != null && query != null) {

				// Query the server
				QueryResponse rsp = server.query(query);

				if (rsp != null) {

					// Get the results
					final SolrDocumentList docs = rsp.getResults();

					// We use our implementation of getBeans()
					beans = getBeans(docs, form);

					log.debug("Results: " + beans != null ? (beans.size()) : "0");

					/*
					 * NO FUNCIONA ESTE MÉTODO DEL API DE Solrj #############
					 * beans = rsp.getBeans(form.getClass()); ###############
					 * obtengo esta excepción: ##############################
					 * Caused by: java.lang.RuntimeException: Exception while
					 * setting value : 3 on private java.lang.Long
					 * org.librae.adminconfig.model.Book.id at
					 * org.apache.solr.client
					 * .solrj.beans.DocumentObjectBinder$DocField
					 * .set(DocumentObjectBinder.java:261) at
					 * org.apache.solr.client
					 * .solrj.beans.DocumentObjectBinder$DocField
					 * .inject(DocumentObjectBinder.java:238) at
					 * org.apache.solr.
					 * client.solrj.beans.DocumentObjectBinder.getBeans
					 * (DocumentObjectBinder.java:59) at
					 * org.apache.solr.client.solrj
					 * .response.QueryResponse.getBeans(QueryResponse.java:288)
					 * atorg.librae.common.search.solr.v1_3.SearchIndexerSolr.
					 * getPojosFromSolr(SearchIndexerSolr.java:288) ... 79 more
					 * Caused by: java.lang.IllegalArgumentException at
					 * sun.reflect.UnsafeObjectFieldAccessorImpl.set(
					 * UnsafeObjectFieldAccessorImpl.java:63) at
					 * java.lang.reflect.Field.set(Field.java:656) at
					 * org.apache.
					 * solr.client.solrj.beans.DocumentObjectBinder$DocField
					 * .set(DocumentObjectBinder.java:255)
					 */

				} else {
					throw ExceptionUtil.crearLibraeException("SearchIndexerSolr::getPojosFromSolr::QueryResponse is null:: server=" + server + " query=" + query);
				}

			} else {
				throw ExceptionUtil.crearLibraeException("SearchIndexerSolr::getPojosFromSolr::SolrServer or SolrQuery are null:: server=" + server + " query=" + query);
			}

		} catch (Exception e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		}

		return beans;
	}

	/**
	 * gets an array object lists from the indexed schema in Apache Solr 1.3
	 * 
	 * @param obj
	 * @param query
	 * @return
	 * @throws LibraeException
	 */
	private List<T> getPojosFromSolr(T obj, SolrQuery query) throws LibraeException {
		List<T> beans = null;

		// @TODO

		return beans;
	}

	/**
	 * gets an array object lists from the indexed schema in Apache Solr 1.3
	 * 
	 * @param obj
	 * @param query
	 * @return
	 * @throws LibraeException
	 */
	private List<T> getPojosFromSolr(T obj, String strQuery) throws LibraeException {
		List<T> beans = null;

		// @TODO

		return beans;
	}

	/**
	 * create a list of POJOs from a solr document list
	 * 
	 * @param solrDocs
	 * @param obj
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private List<T> getBeans(SolrDocumentList solrDocs, T obj) throws LibraeException {
		final List<T> result = new ArrayList<T>();

		for (int i = 0; (solrDocs != null) && i < solrDocs.getNumFound(); ++i) {
			final SolrDocument doc_i = solrDocs.get(i);

			if (isPojoClass(doc_i, obj)) {
				final T element = (T) getBean(doc_i, obj);

				if (element != null) {
					result.add(element);
				}
			}
		}

		return result;
	}

	/**
	 * returns true if a solr documents contains a class field that must be
	 * equals to pojo class <br/>
	 * NOTE: Pojo class must have an annotated arroba field named "class" <br/>
	 * <br/>
	 * <code>@Field("class") <br/> private String idClass =
	 *       "org.librae.adminconfig.model.Book";</code> <br/>
	 * for example
	 * 
	 * @param doc
	 * @param pojo
	 * @return
	 */
	private boolean isPojoClass(SolrDocument doc, T pojo) {
		boolean result = false;

		final String pojoClassName = pojo.getClass().getCanonicalName();

		final Collection<String> fieldNames = doc.getFieldNames();
		final Iterator<String> it = fieldNames.iterator();
		while (it.hasNext() && !result) {
			final String fieldName = it.next();

			if (fieldName.equalsIgnoreCase(ISearchIndexer.POJO_FIELD_NAME_CLASS)) {

				final Object fieldValue = doc.getFieldValue(fieldName);

				result = (fieldValue instanceof String && ((String) fieldValue).equalsIgnoreCase(pojoClassName));
			}
		}

		return result;
	}

	/**
	 * create a POJO from a solr document
	 * 
	 * @param doc
	 * @param obj
	 * @return
	 */
	private Object getBean(SolrDocument doc, T obj) throws LibraeException {
		Object instance = null;
		Class clazz = null;

		try {
			clazz = obj.getClass();
			instance = clazz.newInstance();

			final Collection<String> fieldNames = doc.getFieldNames();
			final Iterator<String> itFieldNames = fieldNames.iterator();
			while (itFieldNames.hasNext()) {
				final String fieldName = itFieldNames.next();
				final Object fieldValue = doc.getFieldValue(fieldName);

				ReflectionUtils.setFieldValue(instance, fieldName, fieldValue, obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.crearLibraeException("SearchIndexerSolr.getBean::No se pudo crear el POJO [" + clazz + "] a partir del SolrDocument [" + doc + "]" + " debido al error: " + e);
		}

		return instance;
	}

	/**
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection openConnection(String resource) throws Exception {

		final URL url = new URL(resource);
		return openConnection(url);
	}

	private void addXmlStringToSolr(String xmlString) throws LibraeException {
		boolean systemEnvironmentCommit = true;
		final String variableSolrCommit = System.getProperty(VARIABLE_SOLR_COMMIT);
		if (variableSolrCommit != null) {
			systemEnvironmentCommit = Boolean.valueOf(variableSolrCommit);
		}

		addXmlStringToSolr(xmlString, systemEnvironmentCommit && instantCommit);
	}

	/**
	 * @param pojo
	 * @throws LibraeException
	 */
	public void addXmlStringToSolr(String xmlString, boolean commit) throws LibraeException {
		final String serverUrl = buildSolrUrl(Constants.REQUEST_HANDLER_UPDATE, solrServer, solrPort, solrContextName);
		log.debug("xmlObject=" + "\n" + xmlString);
		try {

			final URL url = new URL(serverUrl);
			final StringWriter stringWriter = new StringWriter();
			final StringReader sr = new StringReader(xmlString);
			postData(sr, stringWriter, url);
			// Se obtiene la cadena que contiene el XML de respuesta
			final String solrResponseValue = stringWriter.getBuffer().toString();
			// Si el xml de respuesta viene el elemento de OK se hace commit
			if (solrResponseValue.indexOf(OK_RESPONSE_EXCERPT) > 0 && commit) {
				// nuevo writer
				final StringWriter sw2 = new StringWriter();
				// Se hace commit
				postData(new StringReader("<commit/>"), sw2, url);
				sw2.close();
			}
			stringWriter.close();

			log.debug("Commit done.");

		} catch (SolrException e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		} catch (Exception e) {
			log.error("Ha fallado solr al enviarle el String:");
			log.error(xmlString);
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		}
	}

	/**
	 * @param data
	 * @param output
	 * @param huc
	 * @throws Exception
	 */
	private void postData(Reader data, Writer output, URL url) throws Exception {
		// SimplePostTool post = null;
		HttpURLConnection huc = null;
		try {
			huc = openConnection(url);
			final OutputStream out = huc.getOutputStream();

			try {
				final Writer writer = new OutputStreamWriter(out, Constants.XML_ENCODING);
				pipe(data, writer);
				writer.close();
			} catch (IOException e) {
				throw new Exception("IOException while posting data", e);
			} finally {
				if (out != null) {
					out.close();
				}
			}

			final InputStream in = huc.getInputStream();
			try {
				final Reader reader = new InputStreamReader(in);
				pipe(reader, output);
				reader.close();
			} catch (IOException e) {
				throw new Exception("IOException while reading response", e);
			} finally {
				if (in != null) {
					in.close();
				}
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (huc != null) {
				huc.disconnect();
			}
		}
	}

	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection openConnection(URL url) throws Exception {
		final HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		try {
			huc.setRequestMethod("POST");
		} catch (ProtocolException e) {
			throw new Exception("HttpURLConnection parece no soportar POST??", e);
		}
		huc.setDoOutput(true);
		huc.setDoInput(true);
		huc.setUseCaches(false);
		huc.setAllowUserInteraction(false);
		huc.setRequestProperty("Content-type", "text/xml; charset=" + Constants.XML_ENCODING);
		return huc;
	}

	/**
	 * Pipes everything from the reader to the writer via a buffer
	 */
	private void pipe(Reader reader, Writer writer) throws IOException {

		final char[] buf = new char[1024];
		int read = 0;
		while ((read = reader.read(buf)) >= 0) {
			writer.write(buf, 0, read);
		}
		writer.flush();
	}

	private String getObjectsFromSolrQueryString(String q) {
		String solrResponseValue = null;
		String serverUrl = buildSolrUrl(Constants.REQUEST_HANDLER_SELECT, solrServer, solrPort, solrContextName);
		serverUrl += q;
		log.debug("xmlObject=" + "\n" + q);
		try {
			final URL url = new URL(serverUrl);
			final StringWriter stringWriter = new StringWriter();
			final StringReader sr = new StringReader(q);
			postData(sr, stringWriter, url);
			// Se obtiene la cadena que contiene el XML de respuesta
			solrResponseValue = stringWriter.getBuffer().toString();
			stringWriter.close();
			log.debug("query done.");

		} catch (SolrException e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		} catch (Exception e) {
			throw ExceptionUtil.crearLibraeException("ERROR_SEARCH_GENERICO", e);
		}
		return solrResponseValue;
	}

	public String getSolrPort() {
		return solrPort;
	}

	public void setSolrPort(String solrPort) {
		this.solrPort = solrPort;
	}

	public String getSolrContextName() {
		return solrContextName;
	}

	public void setSolrContextName(String solrContextName) {
		this.solrContextName = solrContextName;
	}

	public void setSolrServer(String solrServer) {
		this.solrServer = solrServer;
	}

	public void setInstantCommit(Boolean instantCommit) {
		log.info("Se establece la propiedad instantCommit a " + instantCommit);
		this.instantCommit = instantCommit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.librae.common.search.index.ISearchIndexer#deleteAllIndexes()
	 */
	public void deleteAllIndexes() throws LibraeException {
		addXmlStringToSolr("<delete><query>*:*</query></delete>", true);
		addXmlStringToSolr("<optimize/>", true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.librae.common.search.index.ISearchIndexer#commit()
	 */
	public void commit() {
		addXmlStringToSolr("<commit/>", true);
	}
}