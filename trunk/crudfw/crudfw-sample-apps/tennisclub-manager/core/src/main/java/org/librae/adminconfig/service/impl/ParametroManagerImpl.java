package org.librae.adminconfig.service.impl;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IParametroDAO;
import org.librae.adminconfig.model.Parametro;
import org.librae.adminconfig.service.IParametroManager;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>
 * DAO: IParametroDAO <br/>
 * Entidad: Parametro
 * 
 * @author jcisneros
 */
public class ParametroManagerImpl extends GenericManagerImpl<Parametro, Long>
        implements IParametroManager {

    IParametroDAO parametroDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public ParametroManagerImpl(IParametroDAO parametroDao) {
        super(parametroDao);
        this.parametroDao = parametroDao;
    }

    /**
     * {@inheritDoc}
     */
    public Parametro getParametroByCodigo(String codigo) {
        return parametroDao.getParametroByCodigo(codigo);
    }

    /**
     * {@inheritDoc}
     */
    public void eliminar(Long idParametro) {
        final Parametro parametro = get(idParametro);
        if (parametro.getEliminable()) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_PARAMETRO_CODIGO_NO_ELIMINABLE");
        } else {
            parametroDao.remove(idParametro);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Parametro guardarParametro(Parametro parametro) {
        final Parametro parametroRepetido = getParametroByCodigo(parametro
                .getCodigo());
        if ((parametroRepetido != null)
                && (!parametroRepetido.getId().equals(parametro.getId()))) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_CODIGO_DUPLICADO_PARAMETRO");
        }
        return parametroDao.save(parametro);
    }

    /**
     * {@inheritDoc}
     */
    public List<Parametro> buscar(Map<String, Object> criterios) {
        return parametroDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<Parametro> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    // Getters && Setters

    public IParametroDAO getParametroDao() {
        return parametroDao;
    }

    public void setParametroDao(IParametroDAO parametroDao) {
        this.parametroDao = parametroDao;
    }

}