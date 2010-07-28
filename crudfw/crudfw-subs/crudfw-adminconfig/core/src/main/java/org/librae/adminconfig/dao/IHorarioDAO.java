package org.librae.adminconfig.dao;

import org.librae.adminconfig.model.Horario;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad Horario
 * 
 * @author asantamaria
 */
public interface IHorarioDAO extends IGenericSearchDAO<Horario, Long>,
        GenericDAO<Horario, Long> {

}