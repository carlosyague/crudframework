package org.librae.adminconfig.service.impl;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IPermisoDAO;
import org.librae.adminconfig.dao.ITipoPermisoDAO;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.TipoPermiso;
import org.librae.adminconfig.service.IPermisoManager;

import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/> DAO: IPermisoDAO <br/> Entidad: Permiso
 * 
 * @author jcisneros
 */
public class PermisoManagerImpl extends GenericManagerImpl<Permiso, Long>
        implements IPermisoManager {

    IPermisoDAO     permisoDao;
    ITipoPermisoDAO tipoPermisoDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public PermisoManagerImpl(IPermisoDAO PermisoDao) {
        super(PermisoDao);
        permisoDao = PermisoDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Permiso> buscar(Map<String, Object> criterios) {
        return permisoDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<Permiso> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    public Permiso getPermisoByCodigo(String codigo) {
        return null;
    }

    public TipoPermiso getTipoPermiso(Long idPermiso) {
        return tipoPermisoDao.get(idPermiso);
    }

    // =================== Getters & Setters ====================

    public IPermisoDAO getPermisoDao() {
        return permisoDao;
    }

    public void setPermisoDao(IPermisoDAO PermisoDao) {
        permisoDao = PermisoDao;
    }

    public ITipoPermisoDAO getTipoPermisoDao() {
        return tipoPermisoDao;
    }

    public void setTipoPermisoDao(ITipoPermisoDAO tipoPermisoDao) {
        this.tipoPermisoDao = tipoPermisoDao;
    }

}