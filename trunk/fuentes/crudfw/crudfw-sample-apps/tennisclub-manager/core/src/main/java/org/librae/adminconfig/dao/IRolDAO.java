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

import org.librae.adminconfig.model.Rol;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad Rol
 * 
 * @author asantamaria
 * @author jcisneros
 */
public interface IRolDAO extends IGenericSearchDAO<Rol, Long>,
        GenericDAO<Rol, Long> {

    /**
     * Rescata un Rol de la BBDD a través de su codigo
     * 
     * @param codigo
     *            codigo del Rol
     * @return lista de roles
     */
    public Rol getRolByCodigo(String codigo);

    /**
     * Obtiene los roles sin asignar para el usuario y la biblioteca.
     * 
     * @param idUsuario
     * @param idBiblioteca
     * @param codigoRol
     * @return lista de roles.
     */
    public List<Rol> obtenerRolesSinAsignar(Long idUsuario, Long idBiblioteca,
            String codigoRol);

    /**
     * Método que obtiene un listado de roles, mediante un listado de sus
     * correspondientes ids.
     * 
     * @param listadoId
     *            ,listado de ids
     * @return listado de roles.
     */
    public List<Rol> obtenerRolesById(List<String> listadoId);

    /**
     * @param idUsuario
     * @param idBiblioteca
     * @return
     */
    public List<Rol> getRoles(Long idUsuario, Long idBiblioteca);

}