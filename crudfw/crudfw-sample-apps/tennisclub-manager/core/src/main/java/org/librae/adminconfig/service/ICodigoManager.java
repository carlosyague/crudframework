package org.librae.adminconfig.service;

import java.util.List;

import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.adminconfig.model.ValorCodigo;
import org.librae.common.service.ISearchManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager <br/>
 * DAO: ICodigoBibliotecaDAO <br/>
 * Entidad: CodigoBiblioteca
 *
 * @author asantamaria
 */
public interface ICodigoManager extends ISearchManager<Codigo, Long> {

    /**
     * Obtiene los tipos de codigos.
     *
     * @return lista de tipos de codigos.
     */
    List<TipoCodigo> getTiposCodigo();

    /**
     * @param codigo
     * @param tiposBiblioteca
     * @param valores
     */
    @Transactional(readOnly = false)
    Codigo save(final Codigo codigo, final List<String> tiposBiblioteca,
            final List<Object> valores, final Long idTipoCodigo);

    /**
     * Obtiene el codigo por el id.
     *
     * @param codigo
     * @return
     */
    Codigo getCodigoByCodigo(final String codigo);

    /**
     * Obtiene el codigo por el identificador
     *
     * @param codigo
     * @return
     */
    Codigo getCodigoById(Long codigo);

    /**
     * Obtiene el valor del codigo por el identificador.
     *
     * @param idValor
     * @return valor del codigo.
     */
    ValorCodigo getValorCodigoById(Long idValor);
    /**
     * Obtiene el tipo de codigo por el identificador
     * @param idTipoCodigo
     * @return
     */
	TipoCodigo getTipoCodigo(Long idTipoCodigo);

}
