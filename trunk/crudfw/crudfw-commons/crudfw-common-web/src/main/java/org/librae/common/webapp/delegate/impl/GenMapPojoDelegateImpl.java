package org.librae.common.webapp.delegate.impl;

import java.io.Serializable;

import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.librae.common.service.IGenMapPojoManager;
import org.librae.common.webapp.delegate.IGenMapPojoDelegate;

/**
 * Interfaz para el comportamiento común para los delegate con gestión de pojos
 * a partir de datos de tablas
 *
 * @author aSanjuan
 */
public class GenMapPojoDelegateImpl<T, PK extends Serializable> extends
        SearchDelegate<T, PK> implements IGenMapPojoDelegate<T> {

    private static final long serialVersionUID = 1L;


    /**
     * {@inheritDoc}
     */
    public SingleTableEntityPersister getSingleTableEntity(String tableName) {
        return ((IGenMapPojoManager<T, PK>) manager)
                .getSingleTableEntity(tableName);
    }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName(SingleTableEntityPersister entity,
            String columnName) {

        return ((IGenMapPojoManager<T, PK>) manager).getPropertyName(
                entity, columnName);
    }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName(String tableName,
            String columnName) {
        return ((IGenMapPojoManager<T, PK>) manager).getPropertyName(
                tableName,columnName);
    }

    /**
     * {@inheritDoc}
     */
    public String getEntityName(String tableName) {
        return ((IGenMapPojoManager<T, PK>) manager).getEntityName(
                tableName);
    }


}
