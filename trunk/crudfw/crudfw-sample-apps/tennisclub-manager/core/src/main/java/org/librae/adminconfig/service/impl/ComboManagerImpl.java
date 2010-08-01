package org.librae.adminconfig.service.impl;

import java.io.Serializable;
import java.util.List;

import org.librae.adminconfig.dao.IComunidadAutonomaDAO;
import org.librae.adminconfig.dao.ILocalidadDAO;
import org.librae.adminconfig.dao.IMunicipioDAO;
import org.librae.adminconfig.dao.IPaisDAO;
import org.librae.adminconfig.dao.IProvinciaDAO;
import org.librae.adminconfig.dao.IRolDAO;
import org.librae.adminconfig.dao.ITipoBibliotecaDAO;
import org.librae.adminconfig.dao.ITipoCodigoDAO;
import org.librae.adminconfig.dao.ITipoIdentificacionDAO;
import org.librae.adminconfig.dao.ITipoPermisoDAO;
import org.librae.adminconfig.dao.ITipoViaDAO;
import org.librae.adminconfig.dao.ITratamientoDAO;
import org.librae.adminconfig.model.ComunidadAutonoma;
import org.librae.adminconfig.model.Localidad;
import org.librae.adminconfig.model.Municipio;
import org.librae.adminconfig.model.Pais;
import org.librae.adminconfig.model.Provincia;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.TipoPermiso;
import org.librae.adminconfig.model.TipoVia;
import org.librae.adminconfig.model.Tratamiento;
import org.librae.adminconfig.service.IComboManager;

/**
 * Implementación del manager IComboManager
 * 
 * @author jcisneros
 */
public class ComboManagerImpl implements IComboManager, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long             serialVersionUID       = 6232037012891600763L;

    ITipoIdentificacionDAO                tipoIdentificacionDao  = null;
    ITratamientoDAO                       tratamientoDao         = null;
    ITipoCodigoDAO                        tipoCodigoDao          = null;
    IRolDAO                               rolDao                 = null;
    ITipoBibliotecaDAO                    tipoBibliotecaDao      = null;
    IPaisDAO                              paisDao                = null;
    IComunidadAutonomaDAO                 comAutonomaDao         = null;
    IProvinciaDAO                         provinciaDao           = null;
    IMunicipioDAO                         municipioDao           = null;
    ILocalidadDAO                         localidadDao           = null;
    ITipoViaDAO                           tipoViaDao             = null;
    ITipoPermisoDAO                       tipoPermisoDao         = null;

    /**
     * {@inheritDoc}
     */
    public List<TipoIdentificacion> getTiposIdentificacion() {
        return tipoIdentificacionDao.getAllCacheable();
    }

    /**
     * {@inheritDoc}
     */
    public List<Tratamiento> getTratamientos() {
        List<Tratamiento> tratamientos = null;
        tratamientos = tratamientoDao.getAllCacheable();
        return tratamientos;
    }

    /**
     * {@inheritDoc}
     */
    public List<TipoCodigo> getTiposCodigo() {
        List<TipoCodigo> tiposCodigo = null;
        tiposCodigo = tipoCodigoDao.getAllCacheable();
        return tiposCodigo;
    }

    /**
     * {@inheritDoc}
     */
    public List<Rol> getRoles() {
        List<Rol> roles = null;
        roles = rolDao.getAll();
        return roles;
    }

    /**
     * {@inheritDoc}
     */
    public List<TipoBiblioteca> getTiposBiblioteca() {
        List<TipoBiblioteca> tiposBiblioteca = null;
        tiposBiblioteca = tipoBibliotecaDao.getAllCacheable();
        return tiposBiblioteca;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Pais> getPaises() {
        List<Pais> paises = null;
        paises = paisDao.getAllCacheable();
        return paises;
    }

    /**
     * {@inheritDoc}
     */
    public List<ComunidadAutonoma> getComAutonomas(Long idPais) {
        List<ComunidadAutonoma> comAutonomas = null;
        comAutonomas = comAutonomaDao.obtenerComunidadesByPais(idPais);
        return comAutonomas;
    }

    /**
     * {@inheritDoc}
     */
    public List<Provincia> getProvincias(Long idComAutonoma) {
        List<Provincia> provincias = null;
        provincias = provinciaDao.obtenerProvinciasByComunidad(idComAutonoma);
        return provincias;
    }

    /**
     * {@inheritDoc}
     */
    public List<Municipio> getMunicipios(Long idProvincia) {
        List<Municipio> municipios = null;
        municipios = municipioDao.obtenerMunicipiosByProvincia(idProvincia);
        return municipios;
    }

    /**
     * {@inheritDoc}
     */
    public List<Localidad> getLocalidades(Long idMunicipio) {
        List<Localidad> localidades = null;
        localidades = localidadDao.obtenerLocalidadesByMunicipio(idMunicipio);
        return localidades;
    }

    /**
     * {@inheritDoc}
     */
    public List<TipoVia> getTiposVia() {
        List<TipoVia> tiposVia = null;
        tiposVia = tipoViaDao.getAllCacheable();
        return tiposVia;
    }

    /**
     * {@inheritDoc}
     */
    public List<ComunidadAutonoma> getComAutonomas() {
        List<ComunidadAutonoma> comAutonomas = null;
        comAutonomas = comAutonomaDao.getAllCacheable();
        return comAutonomas;
    }

    /**
     * {@inheritDoc}
     */
    public List<Localidad> getLocalidades() {
        List<Localidad> localidades = null;
        localidades = localidadDao.getAllCacheable();
        return localidades;
    }

    /**
     * {@inheritDoc}
     */
    public List<Municipio> getMunicipios() {
        List<Municipio> municipios = null;
        municipios = municipioDao.getAllCacheable();
        return municipios;
    }

    /**
     * {@inheritDoc}
     */
    public List<Provincia> getProvincias() {
        List<Provincia> provincias = null;
        provincias = provinciaDao.getAllCacheable();
        return provincias;
    }

    public List<TipoPermiso> getTiposPermiso() {
        List<TipoPermiso> tiposPermisos = null;
        tiposPermisos = tipoPermisoDao.getAllCacheable();
        return tiposPermisos;
    }

    // ======================== Getters && Setters =========================

    public ITipoIdentificacionDAO getTipoIdentificacionDao() {
        return tipoIdentificacionDao;
    }

    public void setTipoIdentificacionDao(
            final ITipoIdentificacionDAO tipoIdentificacionDao) {
        this.tipoIdentificacionDao = tipoIdentificacionDao;
    }

    public ITratamientoDAO getTratamientoDao() {
        return tratamientoDao;
    }

    public void setTratamientoDao(final ITratamientoDAO tratamientoDao) {
        this.tratamientoDao = tratamientoDao;
    }

    public ITipoCodigoDAO getTipoCodigoDao() {
        return tipoCodigoDao;
    }

    public void setTipoCodigoDao(final ITipoCodigoDAO tipoCodigoDao) {
        this.tipoCodigoDao = tipoCodigoDao;
    }

    public IRolDAO getRolDao() {
        return rolDao;
    }

    public void setRolDao(final IRolDAO rolDao) {
        this.rolDao = rolDao;
    }

    public ITipoBibliotecaDAO getTipoBibliotecaDao() {
        return tipoBibliotecaDao;
    }

    public void setTipoBibliotecaDao(final ITipoBibliotecaDAO tipoBibliotecaDao) {
        this.tipoBibliotecaDao = tipoBibliotecaDao;
    }

    public IPaisDAO getPaisDao() {
        return paisDao;
    }

    public void setPaisDao(IPaisDAO paisDao) {
        this.paisDao = paisDao;
    }

    public IComunidadAutonomaDAO getComAutonomaDao() {
        return comAutonomaDao;
    }

    public void setComAutonomaDao(IComunidadAutonomaDAO comAutonomaDao) {
        this.comAutonomaDao = comAutonomaDao;
    }

    public IProvinciaDAO getProvinciaDao() {
        return provinciaDao;
    }

    public void setProvinciaDao(IProvinciaDAO provinciaDao) {
        this.provinciaDao = provinciaDao;
    }

    public IMunicipioDAO getMunicipioDao() {
        return municipioDao;
    }

    public void setMunicipioDao(IMunicipioDAO municipioDao) {
        this.municipioDao = municipioDao;
    }

    public ILocalidadDAO getLocalidadDao() {
        return localidadDao;
    }

    public void setLocalidadDao(ILocalidadDAO localidadDao) {
        this.localidadDao = localidadDao;
    }

    public ITipoViaDAO getTipoViaDao() {
        return tipoViaDao;
    }

    public void setTipoViaDao(ITipoViaDAO tipoViaDao) {
        this.tipoViaDao = tipoViaDao;
    }

    /**
     * @return the tipoPermisoDao
     */
    public ITipoPermisoDAO getTipoPermisoDao() {
        return tipoPermisoDao;
    }

    /**
     * @param tipoPermisoDao
     *            the tipoPermisoDao to set
     */
    public void setTipoPermisoDao(ITipoPermisoDAO tipoPermisoDao) {
        this.tipoPermisoDao = tipoPermisoDao;
    }

}