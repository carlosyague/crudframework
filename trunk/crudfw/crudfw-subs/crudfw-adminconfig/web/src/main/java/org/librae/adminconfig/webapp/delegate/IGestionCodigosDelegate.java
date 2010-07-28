package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.adminconfig.model.ValorCodigo;
import org.librae.adminconfig.webapp.bean.CodigoValor;
import org.librae.adminconfig.webapp.form.CodigoForm;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para la gestion de codigos.
 * 
 * @author jcisneros
 */
public interface IGestionCodigosDelegate extends ISearchDelegate<CodigoValor> {

    /**
     * Obtiene los tipos de codigos.
     * 
     * @return lista de tipos de codigos.
     */
    List<TipoCodigo> getTiposCodigo();

    /**
     * Guarda el codigo.
     * 
     * @param codigoForm
     * @param valores
     */
    Codigo save(Long idCodigo, CodigoForm codigoForm, List<Object> valores);

    /**
     * Obtiene el codigo a partir de su identificador.
     * 
     * @param idCodigo
     *            identificador del codigo.
     * @return devuelve el codigo.
     */
    Codigo getCodigoById(Long idCodigo);

    /**
     * Obtiene el codigo por su codigo.
     * 
     * @param string
     * @return
     */
    Codigo getCodigoByCodigo(String string);

    /**
     * Obtiene una lista de identificadores de una lista de codigos de tipos de
     * 
     * @param aplicaGBS
     * @return
     */
    List<String> getTiposBibliotecaByCodigos(String aplicaGBS);

    /**
     * Elimina el codigo.
     * 
     * @param idCodigo
     *            identificador del codigo.
     */
    void delete(Long idCodigo);

    /**
     * Obtiene el valor del codigo por el identificador.
     * 
     * @param idValor
     * @return valor del codigo.
     */
    ValorCodigo getValorCodigoById(Long idValor);

}
