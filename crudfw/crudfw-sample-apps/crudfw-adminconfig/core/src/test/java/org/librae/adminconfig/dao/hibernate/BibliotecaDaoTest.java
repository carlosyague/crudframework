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
// package org.librae.adminconfig.dao.hibernate;
//
// import java.util.List;
//
// import junit.framework.Assert;
//
// import org.librae.adminconfig.dao.IBibliotecaDAO;
// import org.librae.adminconfig.model.Biblioteca;
// import org.librae.adminconfig.model.Direccion;
// import org.librae.common.dao.BaseDAOTestCase;
// import org.librae.common.exception.LibraeException;
//
// public class BibliotecaDaoTest extends BaseDAOTestCase {
// private IBibliotecaDAO dao;
//
// public void setBibliotecaDAO(final IBibliotecaDAO dao) {
// this.dao = dao;
// }
//
// public void testAddAndRemoveBiblioteca() throws Exception {
// Biblioteca biblioteca = new Biblioteca("BIB01", "Biblioteca 01");
//
// // enter all required fields
//
// final Direccion direccion = new Direccion("Larios", 3L);
// direccion
// .setAclarador(
// "Esto es para probar. No me conformo con el test autogenerado por el plugin, que si bien está muy bien como punto de partida, no es suficiente"
// );
//
// biblioteca.setDireccion(direccion);
//
// final Biblioteca padre = new Biblioteca("PADRE", "La Biblioteca padre");
//
// biblioteca.setPadre(padre);
//
// this.log.debug("adding biblioteca...");
// biblioteca = this.dao.save(biblioteca);
// //
// biblioteca = this.dao.get(biblioteca.getId());
//
// Assert.assertNotNull(biblioteca.getId());
//
// this.log.debug("removing biblioteca...");
//
// this.dao.remove(biblioteca.getId());
// //
// try {
// this.dao.get(biblioteca.getId());
// Assert.fail("Biblioteca found in database");
// } catch (final LibraeException dae) {
// this.log.debug("Expected exception: " + dae.getMessage());
// Assert.assertNotNull(dae);
// }
// }
//
// /**
// * test para el metodo "testGetBibliotecaByTipoConPadre()".
// */
// public void testGetBibliotecaByTipoConPadre() throws Exception {
//
// this.log.debug("Entrada al metodo: testGetBibliotecaByTipoConPadre");
// /**
// * En el sample-data existe un tipo de biblioteca(Malaga) con
// * idBibliotecaPadre (id=-1)
// */
//
// final String tipo = "Malaga";
// final Long idBibliotecaPadre = new Long(-1);
//
// List<Biblioteca> listBibliotecaTipoPadre = null;
// listBibliotecaTipoPadre = this.dao.getBibliotecaByTipoConPadre(tipo,
// idBibliotecaPadre);
//
// Assert
// .assertNotNull(
// "Debe existir al menos una biblioteca con ese tipo e identificador ...",
// listBibliotecaTipoPadre.size());
//
// this.log.debug("Salida al metodo: testGetBibliotecaByTipoConPadre");
// }
//
// /**
// * test para el metodo "getBibliotecaByTipoConUsuario()".
// */
// public void testGetBibliotecaByTipoConUsuario() throws Exception {
//
// this.log.debug("Entrada al metodo: getBibliotecaByTipoConUsuario");
// /**
// * En el sample-data existe un tipo de biblioteca(Malaga) con
// * idBibliotecaPadre (id=-1)
// */
//
// final String tipo = "Malaga";
// final Long idUsuario = new Long(-1);
//
// List<Biblioteca> listBibliotecaTipoConUsuario = null;
// listBibliotecaTipoConUsuario = this.dao.getBibliotecaByTipoConUsuario(
// tipo, idUsuario);
//
// Assert
// .assertNotNull(
// "Debe existir al menos una biblioteca con ese tipo e identificador ...",
// listBibliotecaTipoConUsuario.size());
//
// this.log.debug("Salida al metodo: getBibliotecaByTipoConUsuario");
// }
//
// /**
// * test para el metodo "testGetBibliotecasByUsuario()".
// */
// public void testGetBibliotecasByUsuario() throws Exception {
//
// this.log.debug("Entrada al metodo: testGetBibliotecasByUsuario");
// /**
// * En el sample-data existe un idUsuario(id=-1) que cumple el método
// */
//
// final Long idUsuario = new Long(-1);
//
// List<Biblioteca> listBibliotecaByUsuario = null;
// listBibliotecaByUsuario = this.dao.getBibliotecasByUsuario(idUsuario);
// Assert
// .assertNotNull(
// "Debe existir al menos una biblioteca con ese identificador de usuario...",
// listBibliotecaByUsuario.size());
//
// this.log.debug("Salida al metodo: testGetBibliotecasByUsuario");
// }
//
// }