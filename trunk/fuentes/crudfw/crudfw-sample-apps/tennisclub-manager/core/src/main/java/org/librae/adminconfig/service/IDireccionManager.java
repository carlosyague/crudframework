/*
 * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
 * explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
 * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los t�rminos
 * de la Licencia EUPL European Public License publicada por el organismo IDABC
 * de la Comisi�n Europea, en su versi�n 1.0. o posteriores. �ste programa se
 * distribuye de buena fe, pero SIN NINGUNA GARANT�A, incluso sin las presuntas
 * garant�as impl�citas de USABILIDAD o ADECUACI�N A PROP�SITO CONCRETO. Para
 * mas informaci�n consulte la Licencia EUPL European Public License. Usted
 * recibe una copia de la Licencia EUPL European Public License junto con este
 * programa, si por alg�n motivo no le es posible visualizarla, puede
 * consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */

package org.librae.adminconfig.service;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.ComunidadAutonoma;
import org.librae.adminconfig.model.Direccion;
import org.librae.adminconfig.model.Localidad;
import org.librae.adminconfig.model.Municipio;
import org.librae.adminconfig.model.Pais;
import org.librae.adminconfig.model.Provincia;
import org.librae.common.service.GenericManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager <br/>
 * DAO: IDireccionDAO <br/>
 * Entidad: Direccion
 *
 * @author aropero
 */
public interface IDireccionManager extends GenericManager<Direccion, Long> {

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
    @Transactional(readOnly = false)
    Biblioteca addDireccion(Biblioteca bibliotecaConsorcio, Long idPais,
            Long idComAutonoma, Long idProvincia, Long idMunicipio,
            Long idLocalidad, Long idTipoVia, String codigoPostal,
            String edificio, String direccion, Long numero, String piso,
            String aclarador);

    /**
     * Metodo que devuelve un pais obtenido a traves de su ID pasada como parametro
     *
     * @param idPais
     * @return
     */
    Pais getPais(Long idPais);

    /**
     * Metodo que devuelve una comunidad autonoma obtenido a traves de su ID pasada
     * como parametro
     *
     * @param idComAutonoma
     * @return
     */
    ComunidadAutonoma getComAutonoma(Long idComAutonoma);

    Provincia getProvincia(Long idProvincia);

    Municipio getMunicipio(Long idMunicipio);

    Localidad getLocalidad(Long idLocalidad);

}