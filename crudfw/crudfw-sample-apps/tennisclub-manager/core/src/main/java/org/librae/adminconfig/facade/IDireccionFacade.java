package org.librae.adminconfig.facade;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.ComunidadAutonoma;
import org.librae.adminconfig.model.Direccion;
import org.librae.adminconfig.model.Localidad;
import org.librae.adminconfig.model.Municipio;
import org.librae.adminconfig.model.Pais;
import org.librae.adminconfig.model.Provincia;
import org.librae.adminconfig.model.TipoVia;

/**
 * Fachada para la obtencion de las direcciones
 * 
 * @author jcisneros
 */
public interface IDireccionFacade {

    /**
     * Añadimos la Direccion a la biblioteca consorciada a guardar en BBDD.
     * 
     * @param bibliotecaConsorcio
     *            , biblioteca consorciada a guardar en BBDD.
     * @param idConsorcio
     *            , ID del consorcio para la biblioteca.
     * @param serviciosNcip
     *            , listado de serviciosNCIP
     * @param idPais
     *            , ID del pais para la direccion
     * @param idComunidad
     *            , ID de la comunidad autonoma para la direccion
     * @param idProvincia
     *            , ID de la provincia para la direccion
     * @param idMunicipio
     *            , ID del municipio para la direccion
     * @param idLocalidad
     *            , ID de la localidad para la direccion
     * @param idTipoVia
     *            , ID del tipo de via para la direccion
     * @param codigoPostal
     * @param edificio
     * @param direccion
     * @param numero
     * @param piso
     * @param aclarador
     */
    Biblioteca addDireccion(Biblioteca bibliotecaConsorcio, Long idPais,
            Long idComAutonoma, Long idProvincia, Long idMunicipio,
            Long idLocalidad, Long idTipoVia, String codigoPostal,
            String edificio, String direccion, Long numero, String piso,
            String aclarador);

    /**
     * Completa la direccion.
     * 
     * @param idPais
     *            , ID del pais para la direccion
     * @param idComunidad
     *            , ID de la comunidad autonoma para la direccion
     * @param idProvincia
     *            , ID de la provincia para la direccion
     * @param idMunicipio
     *            , ID del municipio para la direccion
     * @param idLocalidad
     *            , ID de la localidad para la direccion
     * @param idTipoVia
     *            , ID del tipo de via para la direccion
     * @return
     */
    Direccion completarDireccion(Direccion direccion, Long idPais,
            Long idComunidad, Long idLocalidad, Long idMunicipio,
            Long idProvincia, Long idTipoVia);

    /**
     * Obtiene el pais a partir de su identificador.
     * 
     * @param idPais
     * @return
     */
    Pais getPais(Long idPais);

    /**
     * Obtiene el comunidadAutonoma a partir de su identificador.
     * 
     * @param idComunidadAutonoma
     * @return
     */
    ComunidadAutonoma getComunidadAutonoma(Long idComunidadAutonoma);

    /**
     * Obtiene el idProvincia a partir de su identificador.
     * 
     * @param provincia
     * @return
     */
    Provincia getProvincia(Long idProvincia);

    /**
     * Obtiene el idLocalidad a partir de su identificador.
     * 
     * @param localidad
     * @return
     */
    Localidad getLocalidad(Long idLocalidad);

    /**
     * Obtiene el idMunicipio a partir de su identificador.
     * 
     * @param idMunicipio
     * @return
     */
    Municipio getMunicipio(Long idMunicipio);

    /**
     * Obtiene el idMunicipio a partir de su identificador.
     * 
     * @param idMunicipio
     * @return
     */
    TipoVia getTipoVia(Long idTipoVia);

}
