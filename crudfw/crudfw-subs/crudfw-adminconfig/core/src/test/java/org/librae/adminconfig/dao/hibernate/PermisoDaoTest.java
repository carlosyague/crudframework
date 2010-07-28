// /*
// * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
// * explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
// * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los
// t�rminos
// * de la Licencia EUPL European Public License publicada por el organismo
// IDABC
// * de la Comisi�n Europea, en su versi�n 1.0. o posteriores. �ste programa se
// * distribuye de buena fe, pero SIN NINGUNA GARANT�A, incluso sin las
// presuntas
// * garant�as impl�citas de USABILIDAD o ADECUACI�N A PROP�SITO CONCRETO. Para
// * mas informaci�n consulte la Licencia EUPL European Public License. Usted
// * recibe una copia de la Licencia EUPL European Public License junto con este
// * programa, si por alg�n motivo no le es posible visualizarla, puede
// * consultarla en la siguiente URL:
// * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
// * copy of the EUPL European Public License along with this program. If not,
// see
// * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
// * copie de la EUPL European Public License avec ce programme. Si non, voir
// * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
// * EUPL European Public License zusammen mit diesem Programm. Wenn nicht,
// finden
// * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
// */
//
package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.librae.adminconfig.dao.IPermisoDAO;
import org.librae.common.dao.BaseDAOTestCase;
import org.librae.common.exception.LibraeException;

public class PermisoDaoTest extends BaseDAOTestCase {
    private IPermisoDAO dao;

    public void setPermisoDAO(final IPermisoDAO dao) {
        this.dao = dao;
    }

    // public void testAddAndRemovePermiso() throws Exception {
    // Permiso permiso = new Permiso("P01", "Permiso 01");
    //
    // // enter all required fields
    //
    // log.debug("adding permiso...");
    // permiso = dao.save(permiso);
    // //
    // permiso = dao.get(permiso.getId());
    //
    // assertNotNull(permiso.getId());
    //
    // log.debug("removing permiso...");
    //
    // dao.remove(permiso.getId());
    // //
    // try {
    // dao.get(permiso.getId());
    // fail("Permiso found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    @SuppressWarnings("unchecked")
    public void testObtenerPermisosSinAsignar() throws Exception {
        this.log.debug("testObtenerPermisosSinAsignar...");
        final Long idRol = new Long(2);
        final String codigoPermiso = "PER02";
        final Long idTipoPermiso = new Long(2);
        try {
            final List permisos = this.dao.obtenerPermisosSinAsignar(idRol,
                    codigoPermiso, idTipoPermiso);
            this.log.debug("permisos encontrados..." + permisos);
            Assert.assertNotNull(permisos.size());
        } catch (final LibraeException dae) {
            this.log.debug("Expected exception: " + dae.getMessage());
            Assert.assertNotNull(dae);
        }

    }

}