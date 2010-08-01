package org.librae.adminconfig.service.impl;

import java.util.List;

import org.librae.adminconfig.dao.ITipoIdentificacionDAO;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.service.ITipoIdentificacionManager;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>
 * DAO: ITipoIdentificacionDAO <br/>
 * Entidad: TipoIdentificacion
 * 
 * @author jcisneros
 */
public class TipoIdentificacionManagerImpl extends
        GenericManagerImpl<TipoIdentificacion, Long> implements
        ITipoIdentificacionManager {

    ITipoIdentificacionDAO tipoIdentificacionDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public TipoIdentificacionManagerImpl(
            final ITipoIdentificacionDAO tipoIdentificacionDao) {
        super(tipoIdentificacionDao);
        this.tipoIdentificacionDao = tipoIdentificacionDao;
    }

    /**
     * Obtiene el tipo de identificacion por el codigo.
     * 
     * @param codigo
     *            codigo del tipo de identificacion.
     * @return tipo de identificacion.
     */
    public TipoIdentificacion getTipoIdentificacionByCodigo(final String codigo) {
        final TipoIdentificacion tipoIdentificacion = tipoIdentificacionDao
                .getTipoIdentificacionByCodigo(codigo);
        return tipoIdentificacion;
    }

    /**
     * @see org.librae.adminconfig.service.ITipoIdentificacionManager#getTiposIdentificacion()
     */
    public List<TipoIdentificacion> getTiposIdentificacion() {
        return tipoIdentificacionDao.getTiposIdentificacion();
    }

}