package org.librae.adminconfig.service;

import java.util.List;

import org.librae.adminconfig.model.HorarioInt;
import org.librae.common.service.ISearchManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager <br/>
 * DAO: IHorarioIntDAO <br/>
 * Entidad: HorarioInt
 * 
 * @author asantamaria
 * @author aropero
 */
public interface IHorarioIntManager extends ISearchManager<HorarioInt, Long> {

    /**
     * Método que elimina un HorarioInt.
     * 
     * @param idHorarioInt
     *            , ID del HorarioInt a eliminar.
     * @param intervHor
     *            , listado de HorarioInt.
     * @param key
     *            , posición en el listado del HorarioInt a eliminar.
     */
    @Transactional(readOnly = false)
    List eliminarIntervalo(Long idHorarioInt, List intervHor, String key);

	List<HorarioInt> getIntervalos(Long idHorario);
}