// package org.librae.adminconfig.dao.hibernate;
//
// import junit.framework.Assert;
//
// import org.junit.Test;
// import org.librae.adminconfig.dao.ITipoCodigoDAO;
// import org.librae.adminconfig.model.TipoCodigo;
// import org.librae.common.dao.BaseDAOTestCase;
// import org.librae.common.exception.LibraeException;
//
// public class TipoCodigoDaoTest extends BaseDAOTestCase {
// private ITipoCodigoDAO dao;
//
// public void setTipoCodigoDAO(final ITipoCodigoDAO dao) {
// this.dao = dao;
// }
//
// @Test
// public void testAddAndRemoveTipoCodigo() throws Exception {
// TipoCodigo tipoCodigo = new TipoCodigo("TC55", "Tipo  Código 55");
//
// // enter all required fields
//
// this.log.debug("adding tipoCodigo...");
// tipoCodigo = this.dao.save(tipoCodigo);
// //
// tipoCodigo = this.dao.get(tipoCodigo.getId());
//
// Assert.assertNotNull(tipoCodigo.getId());
//
// this.log.debug("removing tipoCodigo...");
//
// this.dao.remove(tipoCodigo.getId());
// //
// try {
// this.dao.get(tipoCodigo.getId());
// Assert.fail("TipoCodigo found in database");
// } catch (final LibraeException dae) {
// this.log.debug("Expected exception: " + dae.getMessage());
// Assert.assertNotNull(dae);
// }
// }
//
// }