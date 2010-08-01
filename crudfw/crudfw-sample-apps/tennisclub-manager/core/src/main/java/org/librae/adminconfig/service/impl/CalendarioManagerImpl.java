
package org.librae.adminconfig.service.impl;

import org.librae.adminconfig.dao.ICalendarioDAO;
import org.librae.adminconfig.model.Calendario;
import org.librae.adminconfig.service.ICalendarioManager;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: ICalendarioDAO <br/>Entidad: Calendario
 *
 * @author asantamaria
 */
public class CalendarioManagerImpl extends GenericManagerImpl<Calendario, Long> implements ICalendarioManager {
    ICalendarioDAO calendarioDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public CalendarioManagerImpl(ICalendarioDAO calendarioDao) {
        super(calendarioDao);
        this.calendarioDao = calendarioDao;
    }
}