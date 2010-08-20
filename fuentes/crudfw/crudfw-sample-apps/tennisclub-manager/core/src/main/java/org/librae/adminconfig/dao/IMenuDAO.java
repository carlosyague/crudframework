package org.librae.adminconfig.dao;

import java.util.List;

import org.librae.adminconfig.model.Menu;
import org.librae.common.dao.GenericDAO;

/**
 * Interfaz del DAO para la entidad Menu
 *
 * @author asantamaria
 */
public interface IMenuDAO extends GenericDAO<Menu, Long> {

    /**
     * Obtiene los nodos principales del menu, esos que no tienen padres.
     *
     * @return Lista de nodos del menu.
     */
    List<Menu> getPrincipales();

}