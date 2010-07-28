package org.librae.common.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.librae.common.service.ISearchManager;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz para el comportamiento comun para los delegate de búsqueda.
 * 
 * @author jcisneros
 */
public class SearchDelegate<T, PK extends Serializable> implements
        ISearchDelegate<T> {

    private static final long serialVersionUID = 1L;
    ISearchManager<T, PK>     manager          = null;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<T> buscar(final Map<String, Object> criterios) {
        return this.manager.buscar(criterios);
    }

    public ISearchManager<T, PK> getManager() {
        return this.manager;
    }

    public void setManager(final ISearchManager<T, PK> manager) {
        this.manager = manager;
    }

}
