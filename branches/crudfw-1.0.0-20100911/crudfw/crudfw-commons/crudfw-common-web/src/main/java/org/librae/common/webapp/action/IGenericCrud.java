package org.librae.common.webapp.action;

/**
 * * @author Martin Greco
 *
 * IGenericCrud is a class which defines the crud methods
 */

public interface IGenericCrud {

    /**
     * Generic method to get an object based on class and identifier.*
     * @param
     * @return a string declared in a navegation rule
     *
     */
     public String get();

     /**
      * Generic method to add or edit an object*
      * @param
      * @return a string declared in a navegation rule
      *
      */
     public String save();

     /**
      * Generic method to remove an object based on class and identifier.*
      * @param
      * @return a string declared in a navegation rule
      *
      */
     public String remove();

}