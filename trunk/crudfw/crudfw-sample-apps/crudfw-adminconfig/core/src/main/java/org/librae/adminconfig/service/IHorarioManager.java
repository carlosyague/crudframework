package org.librae.adminconfig.service;

import java.util.List;

import org.librae.adminconfig.model.Horario;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.common.service.ISearchManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager <br/>
 * DAO: IHorarioDAO <br/>
 * Entidad: Horario
 * 
 * @author asantamaria
 * @author aropero
 */
public interface IHorarioManager extends ISearchManager<Horario, Long> {

    /**
     *Metodo que guarda un horario en BBDD con su listado de HorarioInt
     * asociado.
     * 
     * @param horario
     *            , horario a guardar en BBDD.
     * @param list
     *            , lista de horariosInt
     * @return Horario, horario guardado en BBDD.
     */
    @Transactional(readOnly = false)
    Horario guardarHorario(Horario horario, List<HorarioInt> list);

}