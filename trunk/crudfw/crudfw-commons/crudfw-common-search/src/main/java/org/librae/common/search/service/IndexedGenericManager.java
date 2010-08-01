package org.librae.common.search.service;

import java.io.Serializable;
import java.util.List;

import org.librae.common.search.query.IQuery;
import org.librae.common.service.GenericManager;

/**
 * Indexed Generic Manager that talks to GenericDAO to CRUD POJOs and indexs it
 * in a search server
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 * 
 * @author cyague
 * @param <T>
 * @param <PK>
 */
public interface IndexedGenericManager<T, PK extends Serializable> extends
        GenericManager<T, PK> {

    /**
     * Generic method to save an object - handles both update and insert. <br/>
     * Besides, creates/updates an index in search server
     * 
     * @param object
     *            the object to save
     * @return the updated object
     */
    T save(T object);

    /**
     * Generic method to delete an object based on class and id <br/> Besides,
     * removes his index in search server
     * 
     * @param id
     *            the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    /**
     * Generic method used to query in the search server
     * 
     * @param obj
     * @return List of populated objects
     */
    public List<T> query(T obj);

    /**
     * Generic method used to query in the search server
     * 
     * @param obj
     * @param query
     * @return List of populated objects
     */
    public List<T> query(T obj, IQuery query);

    /**
     * Generic method used to query in the search server
     * 
     * @param obj
     * @param query
     * @return List of populated objects
     */
    public List<T> query(T obj, String query);
}
