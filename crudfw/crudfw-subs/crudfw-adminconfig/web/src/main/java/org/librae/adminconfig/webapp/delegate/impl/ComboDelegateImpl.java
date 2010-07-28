package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;

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
import org.librae.adminconfig.webapp.delegate.IComboDelegate;
import org.librae.catalogacion.model.EjemplarTipo;
import org.librae.circulacion.model.PoliticaPrestamoDom;
import org.librae.lectores.model.TipoLector;

/**
 * Implementacion del interfaz IUsuarioDelegate.
 * 
 * @author jcisneros
 */
public class ComboDelegateImpl implements IComboDelegate, Serializable {

    /**
     * Numero de serializacon.
     */
    private static final long serialVersionUID = 2782227571601184032L;

    /**
     * Manager de combos.
     */
    private IComboManager     comboManager;

    public List<TipoIdentificacion> getTiposIdentificacion() {
        return comboManager.getTiposIdentificacion();
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getTratamientos()
     */
    public List<Tratamiento> getTratamientos() {
        List<Tratamiento> tratamientos = null;
        tratamientos = comboManager.getTratamientos();
        return tratamientos;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getTiposCodigo()
     */
    public List<TipoCodigo> getTiposCodigo() {
        List<TipoCodigo> tiposCodigo = null;
        tiposCodigo = comboManager.getTiposCodigo();
        return tiposCodigo;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getRoles()
     */
    public List<Rol> getRoles() {
        List<Rol> roles = null;
        roles = comboManager.getRoles();
        return roles;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getTiposBiblioteca()
     */
    public List<TipoBiblioteca> getTiposBiblioteca() {
        List<TipoBiblioteca> tiposBiblioteca = null;
        tiposBiblioteca = comboManager.getTiposBiblioteca();
        return tiposBiblioteca;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getPolicas()
     */
    public List<PoliticaPrestamoDom> getPolicas() {
        List<PoliticaPrestamoDom> politicas = null;
        politicas = comboManager.getPoliticas();
        return politicas;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getTiposEjemplar()
     */
    public List<EjemplarTipo> getTiposEjemplar() {
        List<EjemplarTipo> tiposEjemplar = null;
        tiposEjemplar = comboManager.getTiposEjemplar();
        return tiposEjemplar;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getTiposLector()
     */
    public List<TipoLector> getTiposLector() {
        List<TipoLector> tiposLector = null;
        tiposLector = comboManager.getTiposLector();
        return tiposLector;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getTiposVia()
     */
    public List<TipoVia> getTiposVia() {
        List<TipoVia> tiposVia = null;
        tiposVia = comboManager.getTiposVia();
        return tiposVia;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getPaises()
     */
    public List<Pais> getPaises() {
        List<Pais> paises = null;
        paises = comboManager.getPaises();
        return paises;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getComAutonomas()
     */
    public List<ComunidadAutonoma> getComAutonomas() {
        List<ComunidadAutonoma> comAutonomas = null;
        comAutonomas = comboManager.getComAutonomas();
        return comAutonomas;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getProvincias()
     */
    public List<Provincia> getProvincias() {
        List<Provincia> provincias = null;
        provincias = comboManager.getProvincias();
        return provincias;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getMunicipios()
     */
    public List<Municipio> getMunicipios() {
        List<Municipio> municipios = null;
        municipios = comboManager.getMunicipios();
        return municipios;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getLocalidades()
     */
    public List<Localidad> getLocalidades() {
        List<Localidad> localidades = null;
        localidades = comboManager.getLocalidades();
        return localidades;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getComAutonomas(java.lang.Long)
     */
    public List<ComunidadAutonoma> getComAutonomas(Long idPais) {
        List<ComunidadAutonoma> comAutonomas = null;
        comAutonomas = comboManager.getComAutonomas(idPais);
        return comAutonomas;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getProvincias(java.lang.Long)
     */
    public List<Provincia> getProvincias(Long idComAutonoma) {
        List<Provincia> provincias = null;
        provincias = comboManager.getProvincias(idComAutonoma);
        return provincias;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getMunicipios(java.lang.Long)
     */
    public List<Municipio> getMunicipios(Long idProvincia) {
        List<Municipio> municipios = null;
        municipios = comboManager.getMunicipios(idProvincia);
        return municipios;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IComboDelegate#getLocalidades(java.lang.Long)
     */
    public List<Localidad> getLocalidades(Long idMunicipio) {
        List<Localidad> localidades = null;
        localidades = comboManager.getLocalidades(idMunicipio);
        return localidades;
    }

    public List<TipoPermiso> getTiposPermiso() {
        return comboManager.getTiposPermiso();
    }

    // Getters && Setters

    public IComboManager getComboManager() {
        return comboManager;
    }

    public void setComboManager(final IComboManager comboManager) {
        this.comboManager = comboManager;
    }

}
