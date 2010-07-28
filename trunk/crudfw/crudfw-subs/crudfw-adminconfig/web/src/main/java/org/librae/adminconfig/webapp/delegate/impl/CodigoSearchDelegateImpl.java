package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.TipoCodigo;

import org.librae.adminconfig.service.ICodigoManager;
import org.librae.adminconfig.service.IComboManager;
import org.librae.adminconfig.webapp.bean.CodigoBiblioteca;
import org.librae.adminconfig.webapp.delegate.ICodigoSearchDelegate;
import org.librae.adminconfig.webapp.form.CodigoForm;
import org.librae.common.webapp.delegate.impl.FavoritoDelegateImpl;

/**
 * Implementacion del interfaz IGestionCodigosDelegate.
 * 
 * @author jcisneros
 */
public class CodigoSearchDelegateImpl extends FavoritoDelegateImpl implements
        ICodigoSearchDelegate, Serializable {

    /**
     * Numero de serializacon.
     */
    private static final long serialVersionUID = 3687777294386658149L;

    /**
     * Manager de usuario.
     */
    private ICodigoManager    codigoManager;
    private IComboManager     comboManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<CodigoBiblioteca> buscar(final Map<String, Object> criterios) {
        final List<CodigoBiblioteca> codigosBiblioteca = new ArrayList<CodigoBiblioteca>();
        final List<Codigo> codigos = codigoManager.buscar(criterios);
        final List<TipoBiblioteca> tiposBiblioteca = comboManager
                .getTiposBiblioteca();
        for (final Codigo codigo : codigos) {
            final CodigoBiblioteca codigoBiblioteca = new CodigoBiblioteca(
                    codigo, tiposBiblioteca);
            codigosBiblioteca.add(codigoBiblioteca);
        }
        return codigosBiblioteca;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#getTiposCodigo()
     */
    public List<TipoCodigo> getTiposCodigo() {
        return codigoManager.getTiposCodigo();
    }

    public List<TipoBiblioteca> getTiposBilioteca() {

        return comboManager.getTiposBiblioteca();
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#save(org.librae.adminconfig.webapp.form.CodigoForm,
     *      java.util.List)
     */
    public void save(final CodigoForm codigoForm, final List<Object> valores) {
        final Codigo codigo = codigoForm.toEntity();
        final List<String> tiposBiblioteca = codigoForm.getTiposBiblioteca();
        codigoManager.save(codigo, tiposBiblioteca, valores, codigoForm
                .getTipoCodigo());
    }

    public TipoCodigo getTipoCodigo(Long idTipoCodigo) {
        return codigoManager.getTipoCodigo(idTipoCodigo);
    }

    // Getters && Setters

    public ICodigoManager getCodigoManager() {
        return codigoManager;
    }

    public void setCodigoManager(final ICodigoManager codigoManager) {
        this.codigoManager = codigoManager;
    }

    public IComboManager getComboManager() {
        return comboManager;
    }

    public void setComboManager(final IComboManager comboManager) {
        this.comboManager = comboManager;
    }

}
