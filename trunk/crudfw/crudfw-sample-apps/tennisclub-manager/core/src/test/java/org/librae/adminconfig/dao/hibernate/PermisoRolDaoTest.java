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

package org.librae.adminconfig.dao.hibernate;

import org.librae.adminconfig.dao.IPermisoRolDAO;
import org.librae.common.dao.BaseDAOTestCase;
import org.librae.common.exception.LibraeException;

public class PermisoRolDaoTest extends BaseDAOTestCase {
    private IPermisoRolDAO dao;

    public void setPermisoRolDAO(final IPermisoRolDAO dao) {
        this.dao = dao;
    }

    // public void testAddAndRemovePermisoRol() throws Exception {
    // PermisoRol permisoRol = new PermisoRol();

    // enter all required fields

    // Permiso perm = new Permiso("PERM_PRU", "Permiso de Prueba");
    // Rol rol = new Rol("rol_codigo_1", "Rol 01");
    //
    // PermisoRol permisoRol = new PermisoRol(perm, rol);
    //
    // log.debug("adding permisoRol...");
    // permisoRol = dao.save(permisoRol);
    // //
    // permisoRol = dao.get(permisoRol.getId());
    //
    // assertNotNull(permisoRol.getId());
    //
    // log.debug("removing permisoRol...");
    //
    // dao.remove(permisoRol.getId());
    // //
    // try {
    // dao.get(permisoRol.getId());
    // fail("PermisoRol found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }

    /**
     * Test del metodo "void borrarRoles(Long idRol)". Este borra el Rol
     * correspondiente a la idRol pasada como parametro. Comprobamos que se
     * realiza la llamada correspondiente y no se lanzan excepciones
     * inesperadas.
     */
    public void testBorrarRoles() {
        final Long idRol = new Long(-1);

        this.log.debug("Realizamos la llamada al metodo a testear...");

        try {
            this.dao.borrarRoles(idRol);
        } catch (final LibraeException dae) {
            this.log.debug("No Expected exception: " + dae.getMessage());
        }
    }

}