// // /*
// // * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos
// de
// // * explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es
// software
// // * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los
// // t�rminos
// // * de la Licencia EUPL European Public License publicada por el organismo
// // IDABC
// // * de la Comisi�n Europea, en su versi�n 1.0. o posteriores. �ste programa
// se
// // * distribuye de buena fe, pero SIN NINGUNA GARANT�A, incluso sin las
// // presuntas
// // * garant�as impl�citas de USABILIDAD o ADECUACI�N A PROP�SITO CONCRETO.
// Para
// // * mas informaci�n consulte la Licencia EUPL European Public License. Usted
// // * recibe una copia de la Licencia EUPL European Public License junto con
// este
// // * programa, si por alg�n motivo no le es posible visualizarla, puede
// // * consultarla en la siguiente URL:
// // * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received
// a
// // * copy of the EUPL European Public License along with this program. If
// not,
// // see
// // * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu
// une
// // * copie de la EUPL European Public License avec ce programme. Si non, voir
// // * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie
// der
// // * EUPL European Public License zusammen mit diesem Programm. Wenn nicht,
// // finden
// // * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
// // */
// //
// package org.librae.adminconfig.dao.hibernate;
//
// import java.util.List;
//
// import junit.framework.Assert;
//
// import org.librae.adminconfig.dao.IRolDAO;
// import org.librae.adminconfig.model.Rol;
// import org.librae.common.dao.BaseDAOTestCase;
// import org.librae.common.exception.LibraeException;
//
// public class RolDaoTest extends BaseDAOTestCase {
//
// private IRolDAO dao;
//
// public void setRolDAO(final IRolDAO dao) {
// this.dao = dao;
// }
//
// public void testAddAndRemoveRol() throws Exception {
// Rol rol = new Rol("rol_codigo_2", "Rol 2");
//
// // enter all required fields
//
// log.debug("adding rol...");
// rol = dao.save(rol);
// //
// rol = dao.get(rol.getId());
//
// Assert.assertNotNull(rol.getId());
//
// log.debug("removing rol...");
//
// dao.remove(rol.getId());
// //
// try {
// dao.get(rol.getId());
// Assert.fail("Rol found in database");
// } catch (final LibraeException dae) {
// log.debug("Expected exception: " + dae.getMessage());
// Assert.assertNotNull(dae);
// }
// }
//
// /**
// * test para el metodo "getRolByCodigo()". Obtiene el rol correspondiente al
// * Código que se le pasa como parámetro
// */
// public void testGetRolByCodigo() throws Exception {
// log.debug("Entrada al metodo: getRolByCodigo");
// /*
// * En el sample-data existe un código (con id = -1) que se corresponde
// * con el rol (id = -2)
// */
// final Long idCodigo = new Long(-1);
// log.debug("Recogeremos el rol relacionado con el código de id ... "
// + idCodigo);
//
// Assert.assertNotNull("Debe existir un rol para el codigo ... ",
// idCodigo);
//
// log.debug("Salida del metodo: getRolByCodigo");
//
// }
//
// /**
// * test para el metodo "obtenerRolesSinAsignar()". Obtenemos los roles que
// * no han sido asignados al idUsuario e idBiblioteca que se pasa como
// * parámetro.
// */
// public void obtenerRolesSinAsignar() throws Exception {
// log.debug("Entrada al metodo: obtenerRolesSinAsignar");
// /*
// * En el sample-data existe un idUsuario (con id = -1) con idBiblioteca
// * (id = -1)
// */
// final Long idUsuario = new Long(-1);
// final Long idBiblioteca = new Long(-1);
// final String codigoRol = "Todos";
//
// List<Rol> roles = null;
// roles = dao.obtenerRolesSinAsignar(idUsuario, idBiblioteca, codigoRol);
// Assert.assertNotNull(
// "Debe existir al menos un rol para ese usuario y biblioteca",
// roles.size());
//
// log.debug("Salida al metodo: obtenerRolesSinAsignar");
// }
//
// }