package org.librae.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Generic Manager that talks to GenericDAO to CRUD POJOs.
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
@Transactional(readOnly = true)
public interface GenericManager<T, PK extends Serializable> {

    /**
     * Count how many rows does the entity have.
     * 
     * @return how many rows does the entity have.
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
     * Generic method used to get all objects of a particular type. This is the
     * same as lookup up all rows in a table.<br>
     * Usado para evitar la existencia de campos perezosos (Lazy) en los
     * resultados que ofrece Hibernate
     * 
     * @return List of populated objects
     */
    List<T> getAllNonLazy();

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
     * found.<br>
     * Usado para obtener una entidad en formato XML.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.<br>
     * Usado para evitar la existencia de campos perezosos (Lazy) en los
     * resultados que ofrece Hibernate
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T getNonLazy(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * 
     * @param id
     *            the identifier (primary key) of the object to get
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     * 
     * @param object
     *            the object to save
     * @return the updated object
     */
    @Transactional(readOnly = false)
    T save(T object);

    /**
     * Generic method to delete an object based on class and id.
     * 
     * @param id
     *            the identifier (primary key) of the object to remove
     */
    @Transactional(readOnly = false)
    void remove(PK id);

}
