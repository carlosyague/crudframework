package org.librae.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for
 * your domain objects.
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericDAO<T, PK extends Serializable> {

    /**
     * Count how many rows does the entity have.
     * 
     * @return how many rows does the entity have
     */
    Long countAll();

    /**
     * Generic method used to get all objects of a particular type. This is the
     * same as lookup up all rows in a table.
     * 
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Gets all records without duplicates.
     * <p>
     * Note that if you use this method, it is imperative that your model
     * classes correctly implement the hashcode/equals methods
     * </p>
     * 
     * @return List of populated objects
     */
    List<T> getAllDistinct();

    /**
     * Find a list of records by using a named query.
     * 
     * @param queryName
     *            query name of the named query
     * @param queryParams
     *            a map of the query names and the values
     * @return a list of the records found
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    /**
     * Used to get just a page of all contents.
     * 
     * @param pag
     *            Pagina que se deséa ver
     * @param numRes
     *            Numero de resultados por página
     * @return List of populated objects
     */
    List<T> getPage(int pag, int numRes);

    /**
     * Used to get just a page of all contents.
     * 
     * @param pag
     *            Pagina que se deséa ver
     * @param numRes
     *            Numero de resultados por página
     * @param orderColumn
     *            Columna por la que ordenar
     * @param ascendingOrder
     *            Orden ascendente o descendente
     * @return List of populated objects
     */
    List<T> getPage(int pag, int numRes, String orderColumn,
            boolean ascendingOrder);

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * 
     * @param id
     *            the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     * 
     * @param object
     *            the object to save
     * @return the persisted object
     */
    T save(T object);

    /**
     * Generic method to REALLY save an object - handles both update and insert.
     * Persist the given transient instance.
     * 
     * @param object
     *            the transient instance to persist
     * @return the generated identifier
     */
    PK realSave(T object);

    /**
     * Generic method to delete an object based on class and id.
     * 
     * @param id
     *            the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    /**
     * Generic method to merge an object instead of save.
     * 
     * @param object
     *            the object to merge
     * @return the merge
     */
    T merge(T object);

    /**
     * @param table
     * @param label
     * @param conditions
     * @param params
     * @return
     */
    public List<T> getResults(final String table, final String label,
            final String[] conditions, final Object[] params);

    /**
     * @param table
     * @param label
     * @param selectClause
     * @param conditions
     * @param params
     * @return
     */
    public List getResults(final String table, final String label,
            final String selectClause, final String[] conditions,
            final Object[] params);

    /**
     * @param label
     * @param column
     * @return
     */
    public String createCondition(final String label, final String column);

    /**
     * @param label
     * @param column
     * @param function
     * @return
     */
    public String createCondition(final String label, final String column,
            final String function);

    /**
     * @param conditions
     * @return
     */
    public String createWhereCondition(final String[] conditions);

    /*
     * ======================================================================
     * Otros métodos de gestión de sesión, caché,... de persistencia
     * ======================================================================
     */

    /**
     * Método que actualiza (commit) en BD las operaciones pendientes del ORM.
     */
    void flush();

    /**
     * Método que limpia la sesión del ORM.
     */
    void clear();

    /**
     * Método que elimina de la caché de primer nivel el objeto solicitado.
     * 
     * @param obj
     *            the object
     */
    void evict(T obj);

    /**
     * Método que obtiene la sesión abierta o abre una en caso de que no exista
     * otra.
     * 
     * @return the ORMSession
     */
    Session getORMSession();

    /**
     * Método que ejecuta la sentencia SQL a nivel DAO mediante el ORM, devuelve
     * el List con los objetos resultados.
     * 
     * @param sql
     *            sentencia SQL a ejecutar contra BD
     * @return List Lista de resultados
     */
    List<T> execSQL(String sql);

    /**
     * Elimina el objeto.
     * 
     * @param object
     */
    void remove(final T object);

    /**
     * Se buscan todos los elementos de la entidad. Utiliza cache para sacar los
     * elementos.
     * 
     * @return
     */
    List<T> getAllCacheable();
}
