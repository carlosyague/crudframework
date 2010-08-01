// package org.librae.adminconfig.dao.hibernate;
//
// import junit.framework.Assert;
//
// import org.junit.Test;
// import org.librae.adminconfig.dao.ITipoBibliotecaDAO;
// import org.librae.adminconfig.model.TipoBiblioteca;
// import org.librae.common.dao.BaseDAOTestCase;
// import org.librae.common.exception.LibraeException;
//
// public class TipoBibliotecaDaoTest extends BaseDAOTestCase {
// private ITipoBibliotecaDAO dao;
//
// public void setTipoBibliotecaDAO(final ITipoBibliotecaDAO dao) {
// this.dao = dao;
// }
//
// @Test
// public void testAddAndRemoveTipoBiblioteca() throws Exception {
// TipoBiblioteca tipoBiblioteca = new TipoBiblioteca("TB03",
// "Tipo Biblioteca 03");
//
// // enter all required fields
//
// this.log.debug("adding tipoBiblioteca...");
// tipoBiblioteca = this.dao.save(tipoBiblioteca);
//
// tipoBiblioteca = this.dao.get(tipoBiblioteca.getId());
//
// Assert.assertNotNull(tipoBiblioteca.getId());
//
// this.log.debug("removing tipoBiblioteca...");
//
// this.dao.remove(tipoBiblioteca.getId());
//
// try {
// this.dao.get(tipoBiblioteca.getId());
// Assert.fail("TipoBiblioteca found in database");
// } catch (final LibraeException dae) {
// this.log.debug("Expected exception: " + dae.getMessage());
// Assert.assertNotNull(dae);
// }
// }
//
// /**
// * test para el metodo "getTipoBibliotecaByCodigo()".
// */
// public void testGetTipoBibliotecaByCodigo() throws Exception {
// this.log.debug("Entrada al metodo: testGetTipoBibliotecaByCodigo");
// /**
// * En el sample-data existe un código (con id=-1) que me devuelve un
// * TipoBibliteca
// */
// final String codigo = "B";
// final TipoBiblioteca tipoBiblioteca = this.dao
// .getTipoBibliotecaByCodigo(codigo);
// Assert.assertNotNull("Debe existir un tipo biblioteca con ese codigo",
// tipoBiblioteca);
//
// this.log.debug("Salida del metodo: testGetTipoBibliotecaByCodigo");
// }
// }