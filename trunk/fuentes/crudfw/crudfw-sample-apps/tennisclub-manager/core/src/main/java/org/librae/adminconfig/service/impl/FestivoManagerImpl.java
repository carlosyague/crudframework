
package org.librae.adminconfig.service.impl;

import org.librae.adminconfig.dao.IFestivoDAO;
import org.librae.adminconfig.model.Festivo;
import org.librae.adminconfig.service.IFestivoManager;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IFestivoDAO <br/>Entidad: Festivo
 *
 * @author asantamaria
 */
public class FestivoManagerImpl extends GenericManagerImpl<Festivo, Long> implements IFestivoManager {
    IFestivoDAO festivoDao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public FestivoManagerImpl(IFestivoDAO festivoDao) {
        super(festivoDao);
        this.festivoDao = festivoDao;
    }
}