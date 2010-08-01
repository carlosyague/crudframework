
package org.librae.adminconfig.service.impl;

import org.librae.adminconfig.dao.ITratamientoDAO;
import org.librae.adminconfig.model.Tratamiento;
import org.librae.adminconfig.service.ITratamientoManager;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: ITratamientoDAO <br/>Entidad: Tratamiento
 *
 * @author asantamaria
 */
public class TratamientoManagerImpl extends GenericManagerImpl<Tratamiento, Long> implements ITratamientoManager {
    ITratamientoDAO tratamientoDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public TratamientoManagerImpl(ITratamientoDAO tratamientoDao) {
        super(tratamientoDao);
        this.tratamientoDao = tratamientoDao;
    }
}