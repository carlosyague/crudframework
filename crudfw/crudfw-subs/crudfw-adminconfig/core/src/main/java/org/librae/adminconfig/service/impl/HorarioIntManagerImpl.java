package org.librae.adminconfig.service.impl;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IHorarioIntDAO;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.service.IHorarioIntManager;

import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/> DAO: IHorarioIntDAO <br/> Entidad:
 * HorarioInt
 * 
 * @author asantamaria
 * @author aropero
 */
public class HorarioIntManagerImpl extends GenericManagerImpl<HorarioInt, Long>
        implements IHorarioIntManager {
    IHorarioIntDAO horarioIntDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public HorarioIntManagerImpl(final IHorarioIntDAO horarioIntDao) {
        super(horarioIntDao);
        this.horarioIntDao = horarioIntDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<HorarioInt> buscar(final Map<String, Object> criterios) {
        return horarioIntDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<HorarioInt> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    /**
     * @see org.librae.adminconfig.service.IHorarioIntManager#eliminarIntervalo(java.lang.Long,
     *      java.util.List, java.lang.String)
     */
    public List eliminarIntervalo(Long idHorarioInt, List intervHor, String key) {
        final HorarioInt horarioInt = null;

        /** Eliminamos objeto de Session */
        intervHor.remove(Integer.valueOf(key).intValue());
        return intervHor;
    }

	public List<HorarioInt> getIntervalos(Long idHorario) {
		return horarioIntDao.getIntervalos(idHorario);
	}
}