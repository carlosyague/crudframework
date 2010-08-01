/*
 * // Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
 * // explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
 * // libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los
 * t�rminos // de la Licencia EUPL European Public License publicada por el
 * organismo IDABC // de la Comisi�n Europea, en su versi�n 1.0. o posteriores.
 * �ste programa se // distribuye de buena fe, pero SIN NINGUNA GARANT�A,
 * incluso sin las presuntas // garant�as impl�citas de USABILIDAD o ADECUACI�N
 * A PROP�SITO CONCRETO. Para // mas informaci�n consulte la Licencia EUPL
 * European Public License. Usted // recibe una copia de la Licencia EUPL
 * European Public License junto con este // programa, si por alg�n motivo no le
 * es posible visualizarla, puede // consultarla en la siguiente URL: //
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a //
 * copy of the EUPL European Public License along with this program. If not, see
 * // http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
 * // copie de la EUPL European Public License avec ce programme. Si non, voir
 * // http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * // EUPL European Public License zusammen mit diesem Programm. Wenn nicht,
 * finden // Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919 //
 */
//
package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.librae.adminconfig.dao.IUsuarioDAO;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Tratamiento;
import org.librae.adminconfig.model.Usuario;
import org.librae.common.dao.BaseDAOTestCase;
import org.librae.common.exception.LibraeException;

public class UsuarioDaoTest extends BaseDAOTestCase {
    private IUsuarioDAO dao;

    public void setUsuarioDAO(final IUsuarioDAO dao) {
        this.dao = dao;
    }

    public void disabled_testAddAndRemoveUsuario() throws Exception {
        Usuario usuario = new Usuario("usuario");

        // enter all required fields

        final TipoIdentificacion tipoIdentificacion = new TipoIdentificacion(
                "TI01", "Tipo de Identificación de prueba 01");
        final Tratamiento tratamiento = new Tratamiento("TR01",
                "Tratamiento de prueba 01", Tratamiento.SEXO_HOMBRE);

        usuario.setTipoIdentificacion(tipoIdentificacion);
        usuario.setTratamiento(tratamiento);

        log.debug("adding usuario...");
        usuario = dao.save(usuario);
        //
        usuario = dao.get(usuario.getId());

        Assert.assertNotNull(usuario.getId());

        log.debug("removing usuario...");

        dao.remove(usuario.getId());
        //
        try {
            dao.get(usuario.getId());
            Assert.fail("Usuario found in database");
        } catch (final LibraeException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            Assert.assertNotNull(dae);
        }
    }

    /**
     * test para el metodo "getUsuarioByUsuario()". Comprueba que se obtiene el
     * usuario pasándole su identificador como parámetro.
     */
    public void disabled_testGetUsuarioByUsuario() throws Exception {
        log.debug("Entrada al metodo: getUsuarioByUsuario");
        /**
         * En el sample-data existe un usuario(t_usuario=librae) y esta
         * relacionada con un pais.
         */
        final String user = "librae";
        final Usuario usuario = dao.getUsuarioByUsuario(user);
        Assert.assertNotNull(
                "Debe existir un usuario con ese número de identificación ...",
                usuario.getId());

        log.debug("Salida al metodo: getUsuarioByUsuario");
    }

    public void testGetUsuariosBySucIdXaPrestSeg() throws Exception {
        // log
        // .debug("*********************************************************************************************");
        // log.debug("Entrada al metodo: getUsuariosBySucIdXaPrestSeg");
        // /**
        // * En el sample-data existe un usuario de la sucursale con id=-100
        // */
        // final String sucursalId = "-100";
        // final List<Usuario> usuarios = dao
        // .getUsuariosBySucIdXaPrestSeg(sucursalId);
        //
        // for (final Usuario usuario : usuarios) {
        // log.debug("usuario.nombre: " + usuario.getNombre());
        // }
        //
        // Assert.assertNotNull(
        // "Debe existir un usuario con ese para esa sucursal ...",
        // usuarios.get(0));
        //
        // log.debug("Salida al metodo: getUsuariosBySucIdXaPrestSeg");
    }
}