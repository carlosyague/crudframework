package org.librae.adminconfig.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IHorarioDAO;
import org.librae.adminconfig.dao.IHorarioIntDAO;
import org.librae.adminconfig.model.Horario;
import org.librae.adminconfig.model.HorarioInt;

import org.librae.adminconfig.service.IHorarioManager;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/> DAO: IHorarioDAO <br/> Entidad: Horario
 * 
 * @author asantamaria
 * @author aropero
 */
public class HorarioManagerImpl extends GenericManagerImpl<Horario, Long>
        implements IHorarioManager {

    IHorarioDAO    horarioDao;

    IHorarioIntDAO horarioIntDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public HorarioManagerImpl(final IHorarioDAO horarioDao) {
        super(horarioDao);
        this.horarioDao = horarioDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Horario> buscar(final Map<String, Object> criterios) {
        return horarioDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<Horario> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    /**
     * Metodo que eliminar todos los HorarioInt relaciones con la id del Horario
     * pasada como parámetro.
     * 
     * @param id
     *            , ID del Horario.
     */
    private void eliminarIntervalosHorario(final Long id) {
        horarioIntDao.clear();
        horarioIntDao.eliminarIntervalosHorario(id);
    }

    /**
     * @see org.librae.adminconfig.service.IHorarioManager#guardarHorario(org.librae.adminconfig.model.Horario,
     *      java.util.List)
     */
    public Horario guardarHorario(Horario horario, List<HorarioInt> list) {

        if (horario.getId() != null) {
            /* Eliminamos los intervalos asociados al horario */
            eliminarIntervalosHorario(horario.getId());
        }

        /* Guardamos el horario en BBDD */
        horario.setFechaActualizacion(new Date());
        horario = horarioDao.save(horario);

        /* Actualizamos el listado de HorarioInt referenciando al Horario */
        for (int i = 0; i < list.size(); i++) {
            final HorarioInt horarioIntAct = list.get(i);

            final HorarioInt horarioIntNew = new HorarioInt(horario,
                    horarioIntAct.getFechaInicio(),
                    horarioIntAct.getFechaFin(),
                    horarioIntAct.getHoraInicio1(), horarioIntAct
                            .getHoraInicio2(), horarioIntAct.getHoraInicio3(),
                    horarioIntAct.getHoraInicio4(), horarioIntAct
                            .getHoraInicio5(), horarioIntAct.getHoraFin1(),
                    horarioIntAct.getHoraFin2(), horarioIntAct.getHoraFin3(),
                    horarioIntAct.getHoraFin4(), horarioIntAct.getHoraFin5(),
                    horarioIntAct.getDiaSemana());

            horarioIntDao.save(horarioIntNew);
        }

        return horario;
    }

    // =================== getter & setter ===================
    public IHorarioIntDAO getHorarioIntDao() {
        return horarioIntDao;
    }

    public void setHorarioIntDao(IHorarioIntDAO horarioIntDao) {
        this.horarioIntDao = horarioIntDao;
    }

}
