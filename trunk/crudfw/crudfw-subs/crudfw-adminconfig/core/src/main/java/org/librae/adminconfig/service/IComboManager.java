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
import org.librae.catalogacion.model.EjemplarTipo;
import org.librae.circulacion.model.CodigoNcipServicio;
import org.librae.circulacion.model.EstadoReserva;
import org.librae.circulacion.model.PoliticaPrestamoDom;
import org.librae.circulacion.model.PoliticaReserva; // import
// org.librae.circulacion.model.pib.ActividadPIB;
import org.librae.circulacion.model.pib.AlcancePeticionPIB;
import org.librae.circulacion.model.pib.EstadoPeticionPIB;
import org.librae.circulacion.model.pib.TipoEventoPIB;
import org.librae.circulacion.model.pib.TipoPeticionPIB;
import org.librae.circulacion.model.pib.TipoResolucionPIB;
import org.librae.importexport.model.TipoTransformJob;
import org.librae.lectores.model.TipoLector;

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
     * Obtiene las politicas de prestamo
     * 
     * @return lista politicas de prestamo.
     */
    List<PoliticaPrestamoDom> getPoliticas();

    /**
     * Obtiene las politicas de reserva.
     * 
     * @return lista politicas de reserva.
     */
    List<PoliticaReserva> getPoliticasReserva();

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
     * Obtiene los tipos de lector de un lector.
     * 
     * @param idLector
     * @return
     */
    List<TipoLector> getTiposLector(Long idLector);

    /**
     * Obtiene los estados de una reserva.
     * 
     * @param idLector
     * @return
     */
    List<EstadoReserva> getEstadosReserva();

    /**
     * Obtiene los tipos de transformaciones o jobs.
     * 
     * @return
     */
    List<TipoTransformJob> getTiposTransformJob();

    /**
     * Obtiene la lista de tipos de peticion de PIB.
     * 
     * @return
     */
    List<TipoPeticionPIB> getTiposPeticionPIB();

    /**
     * Obtiene la lista de estados de peticion de PIB.
     * 
     * @return
     */
    List<EstadoPeticionPIB> getEstadosPeticionPIB();

    /**
     * Obtiene la lista de tipos de resolucion de PIB.
     * 
     * @return
     */
    List<TipoResolucionPIB> getTiposResolucionPIB();

    /**
     * Obtiene la lista de tipos de evento de PIB.
     * 
     * @return
     */
    List<TipoEventoPIB> getTiposEventoPIB();

    /**
     * Obtiene la lista de alcances de una petición de PIB.
     * 
     * @return
     */
    List<AlcancePeticionPIB> getAlcancesPeticionPIB();

    /**
     * Obtener los tipos de permisos.
     * 
     * @return lista de permisos.
     */
    List<TipoPermiso> getTiposPermiso();

    /**
     * Obtener los codigos de servicos ncip.
     * 
     * @return lista de codigos.
     */
    List<CodigoNcipServicio> getCodigosNcipServicios();

}
