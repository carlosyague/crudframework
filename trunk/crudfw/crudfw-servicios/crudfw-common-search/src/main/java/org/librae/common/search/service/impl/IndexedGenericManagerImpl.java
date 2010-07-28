package org.librae.common.search.service.impl;

import java.io.Serializable;
import java.util.List;

import org.librae.common.dao.GenericDAO;
import org.librae.common.search.index.ISearchIndexer;
import org.librae.common.search.query.IQuery;
import org.librae.common.search.service.IndexedGenericManager;
import org.librae.common.service.impl.GenericManagerImpl;
import org.springframework.transaction.annotation.Transactional;


/**
 * Indexed Generic Manager Implementation
 *
 * @author cyague
 * @param <T>
 * @param <PK>
 */
@Transactional(readOnly = false)
public class IndexedGenericManagerImpl<T, PK extends Serializable> extends
        GenericManagerImpl<T, PK> implements IndexedGenericManager<T, PK> {

    private ISearchIndexer<T, PK> searchIndexer = null;

    public IndexedGenericManagerImpl(GenericDAO<T, PK> genericDao,
            ISearchIndexer<T, PK> searchIndexer) {
        super(genericDao);

        this.searchIndexer = searchIndexer;
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {

        final T savedObject = super.save(object);

        if (this.searchIndexer != null) {
            this.searchIndexer.addObjectToSearchServer(savedObject);
        }

        return savedObject;
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {

        super.remove(id);

        if (this.searchIndexer != null) {
            this.searchIndexer.deleteById(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<T> query(T obj) {

        List<T> result = null;

        if (this.searchIndexer != null) {
            result = this.searchIndexer.getObjectsFromSearchServer(obj);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> query(T obj, IQuery query) {

        List<T> result = null;

        if (this.searchIndexer != null) {
            result = this.searchIndexer.getObjectsFromSearchServer(obj, query);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> query(T obj, String query) {

        List<T> result = null;

        if (this.searchIndexer != null) {
            result = this.searchIndexer.getObjectsFromSearchServer(obj, query);
        }

        return result;
    }

}
