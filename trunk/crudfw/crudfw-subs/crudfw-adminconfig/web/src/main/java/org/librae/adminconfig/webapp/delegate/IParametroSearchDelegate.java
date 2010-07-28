package org.librae.adminconfig.webapp.delegate;

import org.librae.adminconfig.model.Parametro;
import org.librae.common.webapp.delegate.IFavoritoDelegate;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la busqueda de parametros.
 * 
 * @author jcisneros
 */
public interface IParametroSearchDelegate extends ISearchDelegate<Parametro>,
        IFavoritoDelegate {

}
