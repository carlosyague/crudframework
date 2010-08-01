package org.librae.adminconfig.dao;

import org.librae.adminconfig.model.Festivo;
import org.librae.common.dao.GenericDAO;

/**
 * Interfaz del DAO para la entidad Festivo
 * 
 * @author asantamaria
 */
public interface IFestivoDAO extends GenericDAO<Festivo, Long> {

    /**
     * Elimina los festivos asociados a un calendario.
     * 
     * @param id
     *            identificador del calendario.
     */
    void eliminarByIdCalendario(Long idCalendario);

}
