package org.librae.adminconfig.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IValorCodigoDAO;
import org.librae.adminconfig.model.ValorCodigo;
import org.librae.adminconfig.service.IValorCodigoManager;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>
 * DAO: IValorCodigoDAO <br/>
 * Entidad: ValorCodigo
 * 
 * @author asantamaria
 */
public class ValorCodigoManagerImpl extends
        GenericManagerImpl<ValorCodigo, Long> implements IValorCodigoManager,
        Serializable {

    /**
     * Numero de Serializacion.
     */
    private static final long serialVersionUID = 1L;

    IValorCodigoDAO           valorCodigoDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public ValorCodigoManagerImpl(final IValorCodigoDAO valorCodigoDao) {
        super(valorCodigoDao);
        this.valorCodigoDao = valorCodigoDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<ValorCodigo> buscar(final Map<String, Object> criterios) {
        return valorCodigoDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<ValorCodigo> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }
}