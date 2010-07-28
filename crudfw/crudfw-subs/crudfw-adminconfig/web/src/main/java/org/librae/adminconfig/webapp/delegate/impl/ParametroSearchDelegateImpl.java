package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Parametro;
import org.librae.adminconfig.service.IParametroManager;
import org.librae.adminconfig.webapp.delegate.IParametroSearchDelegate;
import org.librae.common.webapp.delegate.impl.FavoritoDelegateImpl;

/**
 * Implementacion del interfaz IParametroDelegate.
 * 
 * @author jcisneros
 */
public class ParametroSearchDelegateImpl extends FavoritoDelegateImpl implements
        IParametroSearchDelegate, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2782127571601184032L;

    /**
     * Atributo para las trazas
     */
    protected final Log       log              = LogFactory
                                                       .getLog(ParametroDelegateImpl.class);

    /**
     * Manager de parametro.
     */
    private IParametroManager parametroManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<Parametro> buscar(final Map<String, Object> criterios) {
        return parametroManager.buscar(criterios);
    }

    // ================== Getters && Setters =====================

    public IParametroManager getParametroManager() {
        return parametroManager;
    }

    public void setParametroManager(final IParametroManager parametroManager) {
        this.parametroManager = parametroManager;
    }

}
