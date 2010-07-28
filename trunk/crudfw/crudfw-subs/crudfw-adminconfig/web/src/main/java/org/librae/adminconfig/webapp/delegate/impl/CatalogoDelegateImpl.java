package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Catalogo;
import org.librae.adminconfig.service.ICatalogoManager;
import org.librae.adminconfig.webapp.delegate.ICatalogoDelegate;
import org.librae.adminconfig.webapp.form.CatalogoForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.springframework.dao.DataAccessException;

/**
 * Implementacion del interfaz ICatalogoDelegate.
 * 
 * @author impena
 */
public class CatalogoDelegateImpl implements ICatalogoDelegate, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2782127571601184032L;

    /**
     * Atributo para las trazas
     */
    protected final Log       log              = LogFactory
                                                       .getLog(UsuarioDelegateImpl.class);

    /**
     * Manager de catálogo.
     */
    private ICatalogoManager  catalogoManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<Catalogo> buscar(final Map<String, Object> criterios) {
        return catalogoManager.buscar(criterios);
    }

    public void eliminar(Long idCatalogo) {
        try {
            catalogoManager.eliminar(idCatalogo);
        } catch (final LibraeException le) {
            throw le;
        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException(dae,
                    MensajesError.PROPERTI_ADMINCONFIG);
        } catch (final Exception e) {
            log
                    .error("[CatalogoDelegateImpl.eliminar] ERROR eliminando un Catalogo: "
                            + e);
            throw ExceptionUtil.crearLibraeException("ERROR_DELETE_BD", e);
        }
    }

    public Catalogo guardarCatalogo(CatalogoForm form) {
        Catalogo catalogo = null;

        try {
            if (form.getId() == null) {
                /* Estamos creando */
                catalogo = form.toEntity();
            } else {
                /* Estamos modificando */
                catalogo = catalogoManager.get(form.getId());
            }
            // this.prepararDatosVista(form);

            catalogo = form.toEntity(catalogo);
            catalogo = catalogoManager.salvarCatalogo(catalogo);

        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException("ERROR_GUARDAR_CATALOGO",
                    MensajesError.PROPERTI_ADMINCONFIG, dae);
        }
        // catch (final Exception dae) {
        // throw ExceptionUtil.crearLibraeException("ERROR_GUARDAR_CATALOGO",
        // MensajesError.PROPERTI_ADMINCONFIG, dae);
        // }

        return catalogo;
    }

    public void prepararDatosVista(CatalogoForm catalogoForm) {
        Catalogo catalogo = null;
        if (catalogoForm.getId() != null) {
            catalogo = catalogoManager.get(catalogoForm.getId());
            catalogoForm.fromEntity(catalogo);
        }
    }

    // ================== Getters && Setters =====================

    public ICatalogoManager getCatalogoManager() {
        return catalogoManager;
    }

    public void setCatalogoManager(ICatalogoManager catalogoManager) {
        this.catalogoManager = catalogoManager;
    }

}
