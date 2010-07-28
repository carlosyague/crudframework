package org.librae.common.webapp.delegate;

import org.librae.common.model.LibraeUser;
import org.librae.common.model.MenuItem;

/**
 * Interfaz para el menu.
 * 
 * @author jcisneros
 */
public interface IMenuDelegate {

    /**
     * Método encargado de acceder a la capa manager para obtener la lista de
     * pojos Menu que no tienen padre, ordenados por el campo orden y a
     * continución convertirlos a objetos MenuItem.
     * 
     * @param usuario
     * @param
     * @return cadena con el menu para que lo entienda JSCookMenu
     */
    String getMenu(LibraeUser usuario);

    /**
     * Método encargado de acceder a la capa manager para obtener la lista de
     * pojos Menu que no tienen padre, ordenados por el campo orden y a
     * continución convertirlos a objetos MenuItem.
     * 
     * @param usuario
     * @param
     * @return cadena con el menu para que lo entienda JSCookMenu
     */
    String getFavoritos(LibraeUser usuario);

    /**
     * @param usuario
     * @param url
     */
    void insertarFavorito(Object usuario, String url);

    /**
     * @param url
     * @param identificador
     * @param texto
     * @return
     */
    MenuItem getMenuItem(String url, Long identificador, String texto);

}
