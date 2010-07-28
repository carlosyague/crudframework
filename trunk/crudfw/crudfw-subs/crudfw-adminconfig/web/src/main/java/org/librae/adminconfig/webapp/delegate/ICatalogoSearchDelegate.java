package org.librae.adminconfig.webapp.delegate;

import org.librae.adminconfig.model.Catalogo;
import org.librae.common.webapp.delegate.IFavoritoDelegate;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de Catalogos.
 * 
 * @author impena
 */
public interface ICatalogoSearchDelegate extends ISearchDelegate<Catalogo>,
        IFavoritoDelegate {

}
