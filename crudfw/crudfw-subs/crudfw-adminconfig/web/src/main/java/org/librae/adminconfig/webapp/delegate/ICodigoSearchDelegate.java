package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.adminconfig.webapp.bean.CodigoBiblioteca;
import org.librae.adminconfig.webapp.form.CodigoForm;
import org.librae.common.webapp.delegate.IFavoritoDelegate;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de codigos.
 * 
 * @author jcisneros
 */
public interface ICodigoSearchDelegate extends
        ISearchDelegate<CodigoBiblioteca>, IFavoritoDelegate {

    /**
     * Obtiene los tipos de codigos.
     * 
     * @return lista de tipos de codigos.
     */
    public List<TipoCodigo> getTiposCodigo();

    /**
     * Guarda el codigo.
     * 
     * @param codigoForm
     * @param valores
     */
    public void save(CodigoForm codigoForm, List<Object> valores);

    /**
     * Obtiene el tipo de codigo por el identificador
     * 
     * @param idTipoCodigo
     * @return
     */
    public TipoCodigo getTipoCodigo(Long idTipoCodigo);

    /**
     * Devuelve todos los tipos de biblioteca
     * 
     * @return
     */
    public List<TipoBiblioteca> getTiposBilioteca();

}
