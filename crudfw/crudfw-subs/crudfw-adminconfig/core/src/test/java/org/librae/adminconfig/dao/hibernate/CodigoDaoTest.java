// package org.librae.adminconfig.dao.hibernate;
//
// import junit.framework.Assert;
//
// import org.junit.Test;
// import org.librae.adminconfig.dao.ICodigoDAO;
// import org.librae.adminconfig.model.Codigo;
// import org.librae.adminconfig.model.TipoCodigo;
// import org.librae.common.dao.BaseDAOTestCase;
// import org.librae.common.exception.LibraeException;
//
// public class CodigoDaoTest extends BaseDAOTestCase {
// @SuppressWarnings("unused")
// private ICodigoDAO dao;
//
// public void setCodigoDAO(final ICodigoDAO dao) {
// this.dao = dao;
// }
//
// @Test
// public void testAddAndRemoveCodigo() throws Exception {
// Codigo codigo = new Codigo("CB55"); // enter all required fields
// final TipoCodigo tipoCodigo = new TipoCodigo("-4", "probando");
// codigo.setTipoCodigo(tipoCodigo);
// this.log.debug("adding codigo...");
// codigo = this.dao.save(codigo);
// codigo = this.dao.get(codigo.getId());
// Assert.assertNotNull(codigo.getId());
// this.log.debug("removing codigo...");
// this.dao.remove(codigo.getId());
// try {
// this.dao.get(codigo.getId());
// Assert.fail("Codigo found in database");
// } catch (final LibraeException dae) {
// this.log.debug("Expected exception: " + dae.getMessage());
// Assert.assertNotNull(dae);
// }
// }
//
// @Test
// public void testVacio() {
// Assert.assertTrue(true);
// }
// }