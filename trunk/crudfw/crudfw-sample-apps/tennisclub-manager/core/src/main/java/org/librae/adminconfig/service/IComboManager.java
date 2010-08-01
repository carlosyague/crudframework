package org.librae.adminconfig.service;

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

/**
 * Interfaz para los combos.
 * 
 * @author jcisneros
 */
public interface IComboManager {

    /**
     * Obtiene una lista de tipos de identificacion.
     * 
     * @return Lista de los tipos de financiación.
     */
    List<TipoIdentificacion> getTiposIdentificacion();

    /**
     * Obtiene la lista de tratamientos que puede tener el usuario.
     * 
     * @return lista de tratamientos.
     */
    List<Tratamiento> getTratamientos();

    /**
     * Obtiene los tipos de codigos.
     * 
     * @return lista de tipos de codigos.
     */
    List<TipoCodigo> getTiposCodigo();

    /**
     * Obtiene los roles.
     * 
     * @return lista roles
     */
    List<Rol> getRoles();

    /**
     * Obtiene los tipos de bibliotecas.
     * 
     * @return lista tipos de bibliotecas.
     */
    List<TipoBiblioteca> getTiposBiblioteca();

    /**
     * Obtiene los países.
     * 
     * @return lista de países.
     */
    List<Pais> getPaises();

    /**
     * Obtiene las comunidades autónomas.
     * 
     * @param idPais
     *            , ID del pais por el que buscamos las comunidades autónomas.
     * @return lista de comunidades autónomas.
     */
    List<ComunidadAutonoma> getComAutonomas(Long idPais);

    /**
     * Obtiene las provincias.
     * 
     * @param idComAutonoma
     *            , ID de la comunidad autonoma por la que buscamos las
     *            provincias
     * @return lista de provincias
     */
    List<Provincia> getProvincias(Long idComAutonoma);

    /**
     * Obtiene los municipios.
     * 
     * @param idProvincia
     *            , ID de la provincia por la que buscamos los municipios.
     * @return lista de municipios.
     */
    List<Municipio> getMunicipios(Long idProvincia);

    /**
     * Obtiene las localidades.
     * 
     * @param idMunicipio
     *            , ID del municipio por el que buscamos las localidades.
     * @return lista de localidades.
     */
    List<Localidad> getLocalidades(Long idMunicipio);

    /**
     * Obtiene los tipos de via.
     * 
     * @return lista tipos de via.
     */
    List<TipoVia> getTiposVia();

    /**
     * Obtiene las localidades.
     * 
     * @return lista de localidades.
     */
    List<Localidad> getLocalidades();

    /**
     * Obtiene los municipios.
     * 
     * @return lista de municipios.
     */
    List<Municipio> getMunicipios();

    /**
     * Obtiene las provincias.
     * 
     * @return lista de provincias
     */
    List<Provincia> getProvincias();

    /**
     * Obtiene las comunidades autonomas.
     * 
     * @return lista de comunidades autonomas.
     */
    List<ComunidadAutonoma> getComAutonomas();

    /**
     * Obtener los tipos de permisos.
     * 
     * @return lista de permisos.
     */
    List<TipoPermiso> getTiposPermiso();

}
