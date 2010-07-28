package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Catalogo;
import org.librae.adminconfig.service.ICatalogoManager;
import org.librae.adminconfig.webapp.delegate.ICatalogoSearchDelegate;
import org.librae.common.webapp.delegate.impl.FavoritoDelegateImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementacion del interfaz ICatalogoSearchDelegate.
 * 
 * @author impena
 */
public class CatalogoSearchDelegateImpl extends FavoritoDelegateImpl implements
        ICatalogoSearchDelegate, Serializable {

    protected final Log          log              = LogFactory.getLog(this
                                                          .getClass());

    /**
     *Serial Version UID
     */
    private static final long    serialVersionUID = 1L;

    private ICatalogoManager     catalogoManager;

    private static final Boolean selectedAll      = false;

    public List<Catalogo> buscar(final Map<String, Object> criterios) {
        return catalogoManager.buscar(criterios);
    }

    // =========== getters & setters ==================

    public Boolean getSelectedAll() {
        return selectedAll;
    }

    public ICatalogoManager getCatalogoManager() {
        return catalogoManager;
    }

    public void setCatalogoManager(ICatalogoManager catalogoManager) {
        this.catalogoManager = catalogoManager;
    }

}
