package org.librae.common.dao.hibernate;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.librae.common.dao.IGenMapPojoDAO;

/**
 * @author aSanjuan
 */
public abstract class GenMapPojoDAOImpl<T, PK extends Serializable> extends
        GenericSearchDao<T, PK> implements IGenMapPojoDAO<T, PK> {

    /**
     * Constructor del DAO.
     */
    public GenMapPojoDAOImpl(final Class<T> persistentClass) {
        super(persistentClass);
    }

    /**
     * {@inheritDoc}
     */
    public SingleTableEntityPersister getSingleTableEntity(String tableName) {

        SingleTableEntityPersister myTable = null;

        if (tableName != null) {

            boolean found = false;
            Map map = getHibernateTemplate().getSessionFactory()
                    .getAllClassMetadata();

            for (Iterator i = map.values().iterator(); i.hasNext() && !found;) {
                SingleTableEntityPersister entity = (SingleTableEntityPersister) i
                        .next();
                if (entity.getTableName().equals(tableName)) {
                    myTable = entity;
                    found = true;
                }
            }
        }

        return myTable;
    }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName(SingleTableEntityPersister entity,
            String columnName) {

        String propertyName = null;
        boolean found = false;

        //Obtener property name de la clave
        propertyName=this.getKeyPropertyName(entity, columnName);
        if (propertyName!=null)
            found=true;

        if ((!found)&&(entity != null) && (columnName != null)) {
            for (int i = 0; !found && (i < entity.getPropertyNames().length); i++) {

                if ((entity.getPropertyColumnNames(i).length > 0)
                        && (entity.getPropertyColumnNames(i)[0]
                                .equals(columnName))) {
                    log.debug("\n " + entity.getPropertyNames()[i] + " -> "
                            + entity.getPropertyColumnNames(i)[0]);
                    propertyName = entity.getPropertyNames()[i];
                }
            }
        }

        return propertyName;
    }

    /**
     * {@inheritDoc}
     */
    public String getKeyPropertyName(SingleTableEntityPersister entity,
            String columnName) {

        String keyPropertyName = null;
        boolean found = false;

        if ((entity != null) && (columnName != null)) {
            for (int i = 0; !found && (i < entity.getKeyColumnNames().length); i++) {

                String[] idColumnNames = entity.getIdentifierColumnNames();
                //String[] keyColumnNames = entity.getKeyColumnNames();
                String idColumnName = idColumnNames[i];

                if ((idColumnName != null) && (idColumnName.equals(columnName))) {
                    keyPropertyName = entity.getIdentifierPropertyName();
                    log.debug("\n " + idColumnName + " -> " + keyPropertyName);
                }
            }
        }

        return keyPropertyName;

    }

}
