package org.librae.adminconfig.service.impl;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.ICatalogoDAO;
import org.librae.adminconfig.model.Catalogo;
import org.librae.adminconfig.model.Parametro;
import org.librae.adminconfig.service.ICatalogoManager;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/> DAO: ICatalogoDAO <br/> Entidad: Catalogo
 * 
 * @author asantamaria
 * @author impena
 */
public class CatalogoManagerImpl extends GenericManagerImpl<Catalogo, Long>
        implements ICatalogoManager {
    ICatalogoDAO catalogoDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public CatalogoManagerImpl(ICatalogoDAO catalogoDao) {
        super(catalogoDao);
        this.catalogoDao = catalogoDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Catalogo> buscar(Map<String, Object> criterios) {
        return catalogoDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<Catalogo> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    public Catalogo getCatalogoByCodigo(String codigo) {
        return null;
    }

    public Catalogo salvarCatalogo(Catalogo catalogo) {
        /*
         * Catalogo c = null; try { c =
         * catalogoDao.getCatalogoByCodigo(catalogo.getCodigo()); if (c != null)
         * { throw ExceptionUtil.crearLibraeException(
         * MensajesError.PROPERTI_ADMINCONFIG, "ERROR_CATALOGO_UNICO"); } else {
         * c = catalogoDao.save(catalogo); } } catch (final Exception e) {
         * log.error("Error al guardar el Catalogo: ", e); throw
         * ExceptionUtil.crearLibraeException(
         * MensajesError.PROPERTI_ADMINCONFIG,
         * "ERROR_CODIGO_DUPLICADO_CATALOGO"); } return c;
         */

        final Catalogo catalogoRepetido = catalogoDao
                .getCatalogoByCodigo(catalogo.getCodigo());
        if ((catalogoRepetido != null)
                && (!catalogoRepetido.getId().equals(catalogo.getId()))) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_CODIGO_DUPLICADO_CATALOGO");
        }
        return catalogoDao.save(catalogo);
    }

    public void eliminar(Long catalogo) {
        final Catalogo c = catalogoDao.get(catalogo);
        c.setCodigo(null);
        c.setNombre(null);
        c.setDescripcion(null);

        catalogoDao.remove(catalogo);
    }

    // =================== Getters & Setters ====================

    public ICatalogoDAO getCatalogoDao() {
        return catalogoDao;
    }

    public void setCatalogoDao(ICatalogoDAO catalogoDao) {
        this.catalogoDao = catalogoDao;
    }

}