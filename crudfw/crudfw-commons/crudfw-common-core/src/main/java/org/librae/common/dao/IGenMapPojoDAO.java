package org.librae.common.dao;

import java.io.Serializable;

import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * @author aSanjuan
 */
public interface IGenMapPojoDAO<T, PK extends Serializable> extends
IGenericSearchDAO<T, PK> {

    /**
     *
     * Este método permite obtener el pojo correspondiente a un nombre de tabla dado
     * @param tableName
     * @return
     */
    public SingleTableEntityPersister getSingleTableEntity(String tableName);

    /**
     *
     *  Este método permite obtener el property name asociado a un column name dado para
     *  un pojo determinado
     * @param entity
     * @param columnName
     * @return
     */
    public String getPropertyName(SingleTableEntityPersister entity,
            String columnName);

}
