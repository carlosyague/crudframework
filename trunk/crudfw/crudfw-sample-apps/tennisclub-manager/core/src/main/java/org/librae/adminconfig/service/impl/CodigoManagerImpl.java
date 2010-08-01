package org.librae.adminconfig.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.ICodigoDAO;
import org.librae.adminconfig.dao.ITipoBibliotecaDAO;
import org.librae.adminconfig.dao.ITipoCodigoDAO;
import org.librae.adminconfig.dao.IValorCodigoDAO;
import org.librae.adminconfig.model.Codigo;

import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.adminconfig.model.ValorCodigo;
import org.librae.adminconfig.service.ICodigoManager;
import org.librae.common.Constants;
import org.librae.common.service.impl.GenericManagerImpl;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;

/**
 * Implementación del Manager <br/> DAO: ICodigoBibliotecaDAO <br/> Entidad:
 * CodigoBiblioteca
 * 
 * @author asantamaria
 */
public class CodigoManagerImpl extends GenericManagerImpl<Codigo, Long>
        implements ICodigoManager, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    ICodigoDAO                codigoDao;
    IValorCodigoDAO           valorCodigoDao;
    ITipoCodigoDAO            tipoCodigoDao;
    ITipoBibliotecaDAO        tipoBibliotecaDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public CodigoManagerImpl(final ICodigoDAO codigoDao) {
        super(codigoDao);
        this.codigoDao = codigoDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Codigo> buscar(final Map<String, Object> criterios) {
        return codigoDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<Codigo> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    /**
     * @see org.librae.adminconfig.service.ICodigoManager#getTiposCodigo()
     */
    public List<TipoCodigo> getTiposCodigo() {
        return tipoCodigoDao.getAll();
    }

    /**
     * @see org.librae.adminconfig.service.ICodigoManager#save(org.librae.adminconfig
     *      .model.Codigo, java.util.List, java.util.List)
     */
    public Codigo save(final Codigo codigo, final List<String> tiposBiblioteca,
            final List<Object> valores, final Long idTipoCodigo) {
        final StringBuilder aplicaA = new StringBuilder();

        // Debe de ser unico para cada tipo de biblioteca
        final List<Codigo> codigosRepetidos = codigoDao.getCodigo(codigo
                .getCodigo(), idTipoCodigo);
        if ((codigosRepetidos != null) && (!codigosRepetidos.isEmpty())) {
            for (final Codigo codigoRepetido : codigosRepetidos) {
                if (!codigoRepetido.getId().equals(codigo.getId())) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_ADMINCONFIG,
                            "ERROR_CODIGO_UNICO");
                }
            }
        }

        codigo.getValores().clear();
        codigo.setTipoCodigo(tipoCodigoDao.get(idTipoCodigo));

        // valores
        if (valores != null) {
            for (final Object valor : valores) {
                ((ValorCodigo) valor).setCodigo(codigo);
                codigo.getValores().add((ValorCodigo) valor);
            }
        }

        // tipo biblioteca
        if ((Constants.TIPO_CODIGO_BIBLIOTECA).equals(codigo.getTipoCodigo()
                .getCodigo())) {
            for (final String idTipoBiblioteca : tiposBiblioteca) {
                final TipoBiblioteca tipoBiblioteca = tipoBibliotecaDao
                        .get(new Long(idTipoBiblioteca));
                aplicaA.append(tipoBiblioteca.getCodigo());
            }
            codigo.setAplicaGBS(aplicaA.toString());
        }
        return codigoDao.save(codigo);
    }

    /**
     * @see org.librae.adminconfig.service.ICodigoManager#getCodigoByCodigo(java.
     *      lang.String)
     */
    public Codigo getCodigoByCodigo(final String sCodigo) {
        Codigo codigo = null;
        codigo = codigoDao.getCodigoByCodigo(sCodigo);
        return codigo;
    }

    /**
     * @see org.librae.adminconfig.service.ICodigoManager#getCodigoByIdIni(org.librae
     *      .adminconfig.model.Codigo)
     */
    public Codigo getCodigoById(final Long idCodigo) {
        Codigo codigo = null;
        codigo = codigoDao.get(idCodigo);
        return codigo;
    }

    public ValorCodigo getValorCodigoById(final Long idValor) {
        return valorCodigoDao.get(idValor);
    }

    public TipoCodigo getTipoCodigo(Long idTipoCodigo) {
        return tipoCodigoDao.get(idTipoCodigo);
    }

    // Getters && Setters

    public ICodigoDAO getCodigoDao() {
        return codigoDao;
    }

    public void setCodigoDao(final ICodigoDAO codigoDao) {
        this.codigoDao = codigoDao;
    }

    public ITipoCodigoDAO getTipoCodigoDao() {
        return tipoCodigoDao;
    }

    public void setTipoCodigoDao(final ITipoCodigoDAO tipoCodigoDao) {
        this.tipoCodigoDao = tipoCodigoDao;
    }

    public ITipoBibliotecaDAO getTipoBibliotecaDao() {
        return tipoBibliotecaDao;
    }

    public void setTipoBibliotecaDao(final ITipoBibliotecaDAO tipoBibliotecaDao) {
        this.tipoBibliotecaDao = tipoBibliotecaDao;
    }

    public IValorCodigoDAO getValorCodigoDao() {
        return valorCodigoDao;
    }

    public void setValorCodigoDao(final IValorCodigoDAO valorCodigoDao) {
        this.valorCodigoDao = valorCodigoDao;
    }

}