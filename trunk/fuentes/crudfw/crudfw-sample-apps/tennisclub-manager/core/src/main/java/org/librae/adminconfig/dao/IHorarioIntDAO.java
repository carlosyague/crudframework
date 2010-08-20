package org.librae.adminconfig.dao;

import java.util.List;

import org.librae.adminconfig.model.HorarioInt;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad HorarioInt
 * 
 * @author asantamaria
 * @author aropero
 */
public interface IHorarioIntDAO extends IGenericSearchDAO<HorarioInt, Long>,
        GenericDAO<HorarioInt, Long> {

    /**
     * Metodo que eliminar todos los HorarioInt relaciones con la id del Horario
     * pasada como parámetro.
     * 
     * @param id
     *            , ID del Horario.
     */
    public void eliminarIntervalosHorario(final Long id);

	public List<HorarioInt> getIntervalos(Long idHorario);
}