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

package org.librae.adminconfig.dao;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Calendario;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad Biblioteca
 * 
 * @author aropero
 */
public interface IBibliotecaDAO extends IGenericSearchDAO<Biblioteca, Long>,
        GenericDAO<Biblioteca, Long> {

    /**
     * Busca una lista de bibliotecas que cumplan las mismas características que
     * la tiene como entrada.
     * 
     * @param biblioteca
     *            biblioteca con unas determinadas caracteristicas.
     * @return lista de bibliotecas
     */
    List<Biblioteca> buscar(Map<String, Object> biblioteca);

    /**
     * Obtiene una lista de bibliotecas del tipo determinado.
     * 
     * @param tipo
     * @param idBibliotecaPadre
     * @return lista de bibliotecas
     */
    List<Biblioteca> getBibliotecaByTipoConPadre(String tipo,
            Long idBibliotecaPadre);

    /**
     * Obtiene una lista de bibliotecas del tipo determinado para el usuario.
     * 
     * @param tipo
     * @param idUsuario
     * @return lista de bibliotecas
     */
    List<Biblioteca> getBibliotecaByTipoConUsuario(String tipo, Long idUsuario);

    /**
     * Obtiene una lista de bibliotecas mediante un Usuario.
     * 
     * @param idUsuario
     *            , identificador del Usuario.
     * @return lista de bibliotecas
     */
    List<Biblioteca> getBibliotecasByUsuario(Long idUsuario);

    /**
     * Obtiene la biblioteca por su codigo
     * 
     * @param codigo
     *            de la biblioteca.
     * @return
     */
    Biblioteca getBibliotecaByCodigo(final String codigo);

    /**
     * Método que actualiza los días laborables según el Calendario que posee la
     * Biblioteca (modo edición)
     * 
     * @param idBiblioteca
     *            , ID de la biblioteca.
     * @return String, con los días de la semana.
     */
    String actLabSegunCalendario(Long idBiblioteca);

    /**
     * @param criterios
     * @return
     */
    List<Biblioteca> buscarPorString(final Map<String, Object> criterios);

    /**
     * @param idUsuario
     * @param permiso
     * @return
     */
    List<Biblioteca> getBibliotecasByUsuarioPermiso(Long idUsuario,
            String permiso);

    /**
     * @param idCalendario
     * @return
     */
    Calendario getCalendario(Long idCalendario);

    /**
     * @return
     */
    List<Biblioteca> getBibliotecas();

    /**
     * Obtiene la biblioteca por su descripcion
     * 
     * @param codigo
     *            de la biblioteca.
     * @return
     */
    Biblioteca getBibliotecaByDescripcion(final String codigo);

    Long getIdBibliotecaByDescripcion(String nodoDescripcionClicked);

    Long getIdBibliotecaByCodigo(String nodoDescripcionClicked);

}
