package org.librae.common.service;

import java.io.Serializable;

import org.hibernate.persister.entity.SingleTableEntityPersister;

/**
 * Interfaz para la gestión de pojos a partir de los datos de de las tablas
 */
public interface IGenMapPojoManager<T, PK extends Serializable> extends
        ISearchManager<T, PK> {


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

   /**
   *
   *  Este método permite obtener el entity name asociado a un table name dado
   * @param tableName
   * @return
   */
   public String getEntityName(String tableName);

   /**
   *
   *  Este método permite obtener el property name asociado a un column name dado para
   *  un nombre de tabla determinado (uso con criteria y hql)
   * @param tableName
   * @param columnName
   * @return
   */
  public String getPropertyName(String tableName,
          String columnName);


}
