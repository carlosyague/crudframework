package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.adminconfig.model.ValorCodigo;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.adminconfig.service.ICodigoManager;
import org.librae.adminconfig.webapp.bean.CodigoValor;
import org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate;
import org.librae.adminconfig.webapp.form.CodigoForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.springframework.dao.DataAccessException;

/**
 * Implementacion del interfaz IGestionCodigosDelegate.
 * 
 * @author jcisneros
 */
public class GestionCodigosDelegateImpl implements IGestionCodigosDelegate,
        Serializable {

    /**
     * Numero de serializacon.
     */
    private static final long  serialVersionUID = 3687777294386658149L;

    /**
     * Manager de codigos.
     */
    private ICodigoManager     codigoManager;

    /**
     * Manager de bibliotecas.
     */
    private IBibliotecaManager bibliotecaManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<CodigoValor> buscar(final Map<String, Object> criterios) {
        final List<Codigo> codigos = codigoManager.buscar(criterios);
        final List<CodigoValor> codigosSalida = new ArrayList<CodigoValor>();
        for (final Codigo codigo : codigos) {
            final CodigoValor codigoValor = new CodigoValor(codigo);
            codigosSalida.add(codigoValor);
        }
        return codigosSalida;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#getTiposCodigo()
     */
    public List<TipoCodigo> getTiposCodigo() {
        return codigoManager.getTiposCodigo();
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#save(org.librae.adminconfig.webapp.form.CodigoForm,
     *      java.util.List)
     */
    public Codigo save(final Long idCodigo, final CodigoForm codigoForm,
            final List<Object> valores) {
        Codigo codigo = null;
        if (idCodigo != null) {
            codigo = codigoManager.get(idCodigo);
        } else if (codigoForm.getIdCodigo() != null) {
            codigo = codigoManager.get(codigoForm.getIdCodigo());
        }
        codigo = codigoForm.toEntity(codigo);
        final List<String> tiposBiblioteca = codigoForm.getTiposBiblioteca();
        codigo = codigoManager.save(codigo, tiposBiblioteca, valores,
                codigoForm.getTipoCodigo());
        return codigo;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#getCodigoById
     *      (java.lang.Long)
     */
    public Codigo getCodigoById(final Long idCodigo) {
        return codigoManager.getCodigoById(idCodigo);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#getCodigoByCodigo(java.lang.String)
     */
    public Codigo getCodigoByCodigo(final String sCodigo) {
        return codigoManager.getCodigoByCodigo(sCodigo);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#getTiposBibliotecaByCodigos(java.lang.String)
     */
    public List<String> getTiposBibliotecaByCodigos(final String aplicaGBS) {
        List<String> tiposBiblioteca = null;
        tiposBiblioteca = bibliotecaManager
                .getTiposBibliotecaByCodigos(aplicaGBS);
        return tiposBiblioteca;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#delete
     *      (java.lang.Long)
     */
    public void delete(final Long idCodigo) {
        try {
            codigoManager.remove(idCodigo);
        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException(dae,
                    MensajesError.PROPERTI_ADMINCONFIG);
        }
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate#getValorCodigoById(java.lang.Long)
     */
    public ValorCodigo getValorCodigoById(final Long idValor) {
        return codigoManager.getValorCodigoById(idValor);
    }

    // Getters && Setters

    public ICodigoManager getCodigoManager() {
        return codigoManager;
    }

    public void setCodigoManager(final ICodigoManager codigoManager) {
        this.codigoManager = codigoManager;
    }

    public IBibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    public void setBibliotecaManager(final IBibliotecaManager bibliotecaManager) {
        this.bibliotecaManager = bibliotecaManager;
    }

}
