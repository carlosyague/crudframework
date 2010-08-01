package org.librae.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.librae.common.dao.IGenMapPojoDAO;
import org.librae.common.service.IGenMapPojoManager;

/**
 * @author aSanjuan
 * @param <T>
 * @param <PK>
 */
public class GenMapPojoManagerImpl<T, PK extends Serializable> extends
        GenericManagerImpl<T, PK> implements IGenMapPojoManager<T, PK> {

    public GenMapPojoManagerImpl(IGenMapPojoDAO<T, PK> genMapPojoDAOFinal) {
        super(genMapPojoDAOFinal);
    }



    /**
     * {@inheritDoc}
     */
    public SingleTableEntityPersister getSingleTableEntity(String tableName) {
        return ((IGenMapPojoDAO<T,PK>)genericDAO).getSingleTableEntity(tableName);
    }

    /**
     * {@inheritDoc}
     */
    public String getEntityName(String tableName) {
        String entityName=null;
        SingleTableEntityPersister pojo=getSingleTableEntity(tableName);
        if (pojo!=null)
            entityName=pojo.getEntityName();
        return entityName;
    }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName(SingleTableEntityPersister entity,
            String columnName) {

        return ((IGenMapPojoDAO<T,PK>)genericDAO).getPropertyName(entity,columnName);
            }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName(String tableName,
            String columnName) {

        SingleTableEntityPersister pojo=getSingleTableEntity(tableName);
        return getPropertyName(pojo, columnName);
    }



    /* (non-Javadoc)
     * @see org.librae.common.service.ISearchManager#buscar(java.util.Map)
     */
    public List<T> buscar(Map<String, Object> criterios) {
        // TODO Auto-generated method stub
        return null;
    }



    /* (non-Javadoc)
     * @see org.librae.common.service.ISearchManager#buscarNonLazy(java.util.Map)
     */
    public List<T> buscarNonLazy(Map<String, Object> criterios) {
        // TODO Auto-generated method stub
        return null;
    }


}
