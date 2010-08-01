package org.librae.adminconfig.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.librae.catalogacion.model.EjemplarTipo;
import org.librae.circulacion.model.CodigoNcipServicio;
import org.librae.circulacion.model.EstadoReserva;
import org.librae.circulacion.model.PoliticaPrestamoDom;
import org.librae.circulacion.model.PoliticaReserva;
import org.librae.circulacion.model.pib.AlcancePeticionPIB;
import org.librae.circulacion.model.pib.EstadoPeticionPIB;
import org.librae.circulacion.model.pib.TipoEventoPIB;
import org.librae.circulacion.model.pib.TipoPeticionPIB;
import org.librae.circulacion.model.pib.TipoResolucionPIB;
import org.librae.common.dao.GenericDAO;
import org.librae.importexport.model.TipoTransformJob;
import org.librae.lectores.model.Lector;
import org.librae.lectores.model.LectorTipo;
import org.librae.lectores.model.TipoLector;

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
    GenericDAO<Lector, Long>              lectorDao              = null;
    IComunidadAutonomaDAO                 comAutonomaDao         = null;
    IProvinciaDAO                         provinciaDao           = null;
    IMunicipioDAO                         municipioDao           = null;
    ILocalidadDAO                         localidadDao           = null;
    ITipoViaDAO                           tipoViaDao             = null;
    GenericDAO<PoliticaPrestamoDom, Long> politicaPrestamoDao    = null;
    GenericDAO<PoliticaReserva, Long>     politicaReservaDao     = null;
    GenericDAO<TipoLector, Long>          tipoLectorDao          = null;
    GenericDAO<EjemplarTipo, Long>        tipoEjemplarDao        = null;
    GenericDAO<EstadoReserva, Long>       estadoReservaDao       = null;
    GenericDAO<TipoTransformJob, Long>    tipoTransformJobDao    = null;
    GenericDAO<TipoPeticionPIB, Long>     tipoPeticionPIBDao     = null;
    GenericDAO<EstadoPeticionPIB, Long>   estadoPeticionPIBDao   = null;
    GenericDAO<TipoResolucionPIB, Long>   tipoResolucionPIBDao   = null;
    GenericDAO<AlcancePeticionPIB, Long>  alcancePeticionPIBDao  = null;
    GenericDAO<TipoEventoPIB, Long>       tipoEventoPIBDao       = null;
    ITipoPermisoDAO                       tipoPermisoDao         = null;
    GenericDAO<CodigoNcipServicio, Long>  codigoNcipServiciosDao = null;

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
    public List<PoliticaPrestamoDom> getPoliticas() {
        List<PoliticaPrestamoDom> politicas = null;
        politicas = politicaPrestamoDao.getAll();
        return politicas;
    }

    /**
     * {@inheritDoc}
     */
    public List<PoliticaReserva> getPoliticasReserva() {
        List<PoliticaReserva> politicasReserva = null;
        politicasReserva = politicaReservaDao.getAll();
        return politicasReserva;
    }

    /**
     * {@inheritDoc}
     */
    public List<EjemplarTipo> getTiposEjemplar() {
        List<EjemplarTipo> tiposEjemplar = null;
        tiposEjemplar = tipoEjemplarDao.getAllCacheable();
        return tiposEjemplar;
    }

    /**
     * {@inheritDoc}
     */
    public List<TipoLector> getTiposLector() {
        List<TipoLector> tiposLector = null;
        tiposLector = tipoLectorDao.getAllCacheable();
        return tiposLector;
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

    /**
     * {@inheritDoc}
     */
    public List<TipoLector> getTiposLector(Long idLector) {
        final List<TipoLector> tiposLector = new ArrayList<TipoLector>();
        Lector lector = null;
        lector = lectorDao.get(idLector);
        for (final LectorTipo lectorTipo : lector.getLectorTipos()) {
            if (!lectorTipo.getTipoLector().getBorrado()) {
                tiposLector.add(lectorTipo.getTipoLector());
            }
        }
        return tiposLector;
    }

    /**
     * {@inheritDoc}
     */
    public List<EstadoReserva> getEstadosReserva() {
        List<EstadoReserva> estadoReserva = null;
        estadoReserva = estadoReservaDao.getAllCacheable();
        return estadoReserva;
    }

    /**
     * {@inheritDoc}
     */
    public List<TipoTransformJob> getTiposTransformJob() {
        List<TipoTransformJob> tipoTransformJob = null;
        tipoTransformJob = tipoTransformJobDao.getAllCacheable();
        return tipoTransformJob;
    }

    /**
     * {@inheritDoc}
     */
    public List<TipoPeticionPIB> getTiposPeticionPIB() {
        List<TipoPeticionPIB> tipoPeticionPIB = null;
        tipoPeticionPIB = tipoPeticionPIBDao.getAllCacheable();
        return tipoPeticionPIB;
    }

    /**
     * {@inheritDoc}
     */
    public List<EstadoPeticionPIB> getEstadosPeticionPIB() {
        List<EstadoPeticionPIB> estadoPeticionPIB = null;
        estadoPeticionPIB = estadoPeticionPIBDao.getAllCacheable();
        return estadoPeticionPIB;
    }

    /**
     * {@inheritDoc}
     */
    public List<TipoResolucionPIB> getTiposResolucionPIB() {
        List<TipoResolucionPIB> tipoResolucionPIB = null;
        tipoResolucionPIB = tipoResolucionPIBDao.getAllCacheable();
        return tipoResolucionPIB;
    }

    /**
     * {@inheritDoc}
     */
    public List<TipoEventoPIB> getTiposEventoPIB() {
        List<TipoEventoPIB> tipoEventoPIB = null;
        tipoEventoPIB = tipoEventoPIBDao.getAllCacheable();
        return tipoEventoPIB;
    }

    /**
     * {@inheritDoc}
     */
    public List<AlcancePeticionPIB> getAlcancesPeticionPIB() {
        List<AlcancePeticionPIB> alcancePeticionPIB = null;
        alcancePeticionPIB = alcancePeticionPIBDao.getAll();
        return alcancePeticionPIB;
    }

    public List<TipoPermiso> getTiposPermiso() {
        List<TipoPermiso> tiposPermisos = null;
        tiposPermisos = tipoPermisoDao.getAllCacheable();
        return tiposPermisos;
    }

    /**
     * @return the codigoNcipServiciosDao
     */
    public List<CodigoNcipServicio> getCodigosNcipServicios() {
        List<CodigoNcipServicio> codigosNcipServicio = null;
        codigosNcipServicio = codigoNcipServiciosDao.getAllCacheable();
        return codigosNcipServicio;
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

    public GenericDAO<PoliticaPrestamoDom, Long> getPoliticaPrestamoDao() {
        return politicaPrestamoDao;
    }

    public void setPoliticaPrestamoDao(
            final GenericDAO<PoliticaPrestamoDom, Long> politicaPrestamoDao) {
        this.politicaPrestamoDao = politicaPrestamoDao;
    }

    public GenericDAO<TipoLector, Long> getTipoLectorDao() {
        return tipoLectorDao;
    }

    public void setTipoLectorDao(
            final GenericDAO<TipoLector, Long> tipoLectorDao) {
        this.tipoLectorDao = tipoLectorDao;
    }

    public GenericDAO<EjemplarTipo, Long> getTipoEjemplarDao() {
        return tipoEjemplarDao;
    }

    public void setTipoEjemplarDao(
            final GenericDAO<EjemplarTipo, Long> tipoEjemplarDao) {
        this.tipoEjemplarDao = tipoEjemplarDao;
    }

    public GenericDAO<PoliticaReserva, Long> getPoliticaReservaDao() {
        return politicaReservaDao;
    }

    public void setPoliticaReservaDao(
            GenericDAO<PoliticaReserva, Long> politicaReservaDao) {
        this.politicaReservaDao = politicaReservaDao;
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

    public GenericDAO<Lector, Long> getLectorDao() {
        return lectorDao;
    }

    public void setLectorDao(GenericDAO<Lector, Long> lectorDao) {
        this.lectorDao = lectorDao;
    }

    public void setEstadoReservaDao(
            GenericDAO<EstadoReserva, Long> estadoReservaDao) {
        this.estadoReservaDao = estadoReservaDao;
    }

    public GenericDAO<TipoTransformJob, Long> getTipoTransformJobDao() {
        return tipoTransformJobDao;
    }

    public void setTipoTransformJobDao(
            GenericDAO<TipoTransformJob, Long> tipoTransformJobDao) {
        this.tipoTransformJobDao = tipoTransformJobDao;
    }

    public GenericDAO<EstadoReserva, Long> getEstadoReservaDao() {
        return estadoReservaDao;
    }

    public GenericDAO<TipoPeticionPIB, Long> getTipoPeticionPIBDao() {
        return tipoPeticionPIBDao;
    }

    public void setTipoPeticionPIBDao(
            GenericDAO<TipoPeticionPIB, Long> tipoPeticionPIBDao) {
        this.tipoPeticionPIBDao = tipoPeticionPIBDao;
    }

    /**
     * @return the estadoPeticionPIBDao
     */
    public GenericDAO<EstadoPeticionPIB, Long> getEstadoPeticionPIBDao() {
        return estadoPeticionPIBDao;
    }

    /**
     * @param estadoPeticionPIBDao
     *            the estadoPeticionPIBDao to set
     */
    public void setEstadoPeticionPIBDao(
            GenericDAO<EstadoPeticionPIB, Long> estadoPeticionPIBDao) {
        this.estadoPeticionPIBDao = estadoPeticionPIBDao;
    }

    /**
     * @return the tipoResolucionPIBDao
     */
    public GenericDAO<TipoResolucionPIB, Long> getTipoResolucionPIBDao() {
        return tipoResolucionPIBDao;
    }

    /**
     * @param tipoResolucionPIBDao
     *            the tipoResolucionPIBDao to set
     */
    public void setTipoResolucionPIBDao(
            GenericDAO<TipoResolucionPIB, Long> tipoResolucionPIBDao) {
        this.tipoResolucionPIBDao = tipoResolucionPIBDao;
    }

    /**
     * @return the tipoEventoPIBDao
     */
    public GenericDAO<TipoEventoPIB, Long> getTipoEventoPIBDao() {
        return tipoEventoPIBDao;
    }

    /**
     * @param tipoEventoPIBDao
     *            the tipoEventoPIBDao to set
     */
    public void setTipoEventoPIBDao(
            GenericDAO<TipoEventoPIB, Long> tipoEventoPIBDao) {
        this.tipoEventoPIBDao = tipoEventoPIBDao;
    }

    /**
     * @return the alcancePeticionPIBDao
     */
    public GenericDAO<AlcancePeticionPIB, Long> getAlcancePeticionPIBDao() {
        return alcancePeticionPIBDao;
    }

    /**
     * @param alcancePeticionPIBDao
     *            the alcancePeticionPIBDao to set
     */
    public void setAlcancePeticionPIBDao(
            GenericDAO<AlcancePeticionPIB, Long> alcancePeticionPIBDao) {
        this.alcancePeticionPIBDao = alcancePeticionPIBDao;
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

    /**
     * @param codigoNcipServiciosDao
     *            the codigoNcipServiciosDao to set
     */
    public void setCodigoNcipServiciosDao(
            GenericDAO<CodigoNcipServicio, Long> codigoNcipServiciosDao) {
        this.codigoNcipServiciosDao = codigoNcipServiciosDao;
    }

    /**
     * @return the codigoNcipServiciosDao
     */
    public GenericDAO<CodigoNcipServicio, Long> getCodigoNcipServiciosDao() {
        return codigoNcipServiciosDao;
    }

}