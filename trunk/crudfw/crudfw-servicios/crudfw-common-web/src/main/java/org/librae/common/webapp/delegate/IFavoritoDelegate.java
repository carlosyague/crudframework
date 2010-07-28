package org.librae.common.webapp.delegate;

import org.librae.common.model.MenuItem;

/**
 * Interfaz para los favoritos.
 * 
 * @author jcisneros
 */
public interface IFavoritoDelegate {

    void insertarFavorito(Object usuario, String url, Long identificador,
            String texto);

    MenuItem getMenuItem(String url, Long identificador, String texto);

}
