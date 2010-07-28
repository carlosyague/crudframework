package org.librae.common.search.index;

import java.io.Serializable;
import java.util.List;

import org.librae.common.exception.LibraeException;
import org.librae.common.search.query.IQuery;

/**
 * Interfaz que deben implementar todos los posibles servidores de búsqueda o
 * indexación de LibrÆ
 * 
 * @author cyague
 */
public interface ISearchIndexer<T, PK extends Serializable> {

	/**
     *
     */
	public static final String POST_ENCODING = "UTF-8";

	/**
     *
     */
	public static final String OK_RESPONSE_EXCERPT = "<int name=\"status\">0</int>";

	// SEARCH SERVER CONFIG XML
	public static final String XML_SEARCH_SERVER_CONFIG_FILENAME = "server-config.xml";

	// SOLR CONFIG XML CONSTANTS: SOLR
	public final static String XML_SEARCH_SERVER_CONFIG = "search-server";
	public final static String XML_SEARCH_SERVER_HOST = "host";
	public final static String XML_SEARCH_SERVER_PORT = "port";
	public final static String XML_SEARCH_SERVER_CONTEXT = "contextName";

	// POJO TAG ANNOTATION TO SPECIFY THE POJO CLASSNAME
	public static final String POJO_FIELD_NAME_CLASS = "class";

	/**
	 * adds an object to index it in the search server
	 * 
	 * @param obj
	 * @throws LibraeException
	 */
	public void addObjectToSearchServer(T obj) throws LibraeException;

	/**
	 * gets an array object lists from the indexed schema in the search server
	 * 
	 * @param o
	 * @return
	 * @throws LibraeException
	 */
	public List<T> getObjectsFromSearchServer(T obj) throws LibraeException;

	/**
	 * gets an array object lists from the indexed schema in the search server
	 * 
	 * @param o
	 * @param query
	 * @return
	 * @throws LibraeException
	 */
	public List<T> getObjectsFromSearchServer(T obj, IQuery query) throws LibraeException;

	/**
	 * gets an array object lists from the indexed schema in the search server
	 * 
	 * @param o
	 * @param query
	 * @return
	 * @throws LibraeException
	 */
	public List<T> getObjectsFromSearchServer(T obj, String query) throws LibraeException;

	/**
	 * removes an existing object in the search server
	 * 
	 * @param id
	 * @throws LibraeException
	 */
	public void deleteById(PK id) throws LibraeException;

	/**
	 * remove a lists of objects in the search server
	 * 
	 * @param id
	 * @throws LibraeException
	 */
	public void deleteByQuery(String query) throws LibraeException;

	public String getXmlString(String q) throws LibraeException;

	/**
	 * Ejecuta la sentencia XML contra solr y hace commit o no en función del
	 * parámetro.
	 * 
	 * @param xmlString sentencia a ejecutar
	 * @param commit determina si se ejecuta el commit o no 
	 * @throws LibraeException si ha habido algún problema
	 */
	public void addXmlStringToSolr(String xmlString, boolean commit) throws LibraeException;

	/**
	 * CUIDADO!!! Elimina todo el contenido de los índices de solr. NO hay
	 * vuelta atrás.
	 * 
	 * @throws LibraeException
	 */
	public void deleteAllIndexes() throws LibraeException;
	
	/**
	 * Hace un commit en solr
	 * @throws LibraeException
	 */
	public void commit() throws LibraeException;
}