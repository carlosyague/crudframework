package org.librae.adminconfig.webapp.delegate;

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
import org.librae.catalogacion.model.EjemplarTipo;
import org.librae.circulacion.model.PoliticaPrestamoDom;
import org.librae.lectores.model.TipoLector;

/**
 * Interfaz para la gestion de combos.
 * 
 * @author jcisneros
 */
public interface IComboDelegate {

    /**
     * Lista con los tipos de identificacion.
     * 
     * @return tipos de identificacion.
     */
    List<TipoIdentificacion> getTiposIdentificacion();

    /**
     * Obtiene la lista de roles.
     * 
     * @return lista de roles
     */
    List<Rol> getRoles();

    /**
     * Obtiene los tipos de codigos.
     * 
     * @return lista de tipos de codigos.
     */
    List<TipoCodigo> getTiposCodigo();

    /**
     * Obtiene los Tratamientos
     * 
     * @return lista de tratamientos.
     */
    List<Tratamiento> getTratamientos();

    /**
     * Obtiene los tipos de biblioteca.
     * 
     * @return lista de tipos de biblioteca.
     */
    List<TipoBiblioteca> getTiposBiblioteca();

    /**
     * Obtiene los tipos de ejemplar.
     * 
     * @return lista tipos de ejemplar.
     */
    List<EjemplarTipo> getTiposEjemplar();

    /**
     * Obtiene los tipos de lector.
     * 
     * @return lista tipos de lector.
     */
    List<TipoLector> getTiposLector();

    /**
     * Obtiene las politicas.
     * 
     * @return lista politicas.
     */
    List<PoliticaPrestamoDom> getPolicas();

    /**
     * Obtiene los países.
     * 
     * @return lista de países.
     */
    List<Pais> getPaises();

    /**
     * Obtiene las comunidades autonomas.
     * 
     * @return lista de comunidades autonomas.
     */
    List<ComunidadAutonoma> getComAutonomas();

    /**
     * Obtiene las provincias.
     * 
     * @return lista de provincias
     */
    List<Provincia> getProvincias();

    /**
     * Obtiene los municipios.
     * 
     * @return lista de municipios.
     */
    List<Municipio> getMunicipios();

    /**
     * Obtiene las localidades.
     * 
     * @return lista de localidades.
     */
    List<Localidad> getLocalidades();

    /**
     * Obtiene las comunidades autonomas.
     * 
     * @param idPais
     *            , ID del país por el que buscamos las comunidades autonomas.
     * @return lista de comunidades autonomas.
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
     * Obtener los tipos de permisos.
     * 
     * @return lista de permisos.
     */
    List<TipoPermiso> getTiposPermiso();

}
