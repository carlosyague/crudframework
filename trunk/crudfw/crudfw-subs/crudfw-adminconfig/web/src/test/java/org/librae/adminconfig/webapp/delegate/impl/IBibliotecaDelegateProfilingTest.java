package org.librae.adminconfig.webapp.delegate.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Profiling para el Delegate de la busqueda de bibliotecas.
 * 
 * @author jcisneros
 */
public class IBibliotecaDelegateProfilingTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	protected final Log log = LogFactory
			.getLog(IBibliotecaDelegateProfilingTest.class);

	private IBibliotecaDelegate bibliotecaDelegate;

	public void setBibliotecaDelegate(final IBibliotecaDelegate bibliotecaDelegate) {
		this.bibliotecaDelegate = bibliotecaDelegate;
	}

	@Override
	protected String[] getConfigLocations() {
		setAutowireMode(AbstractDependencyInjectionSpringContextTests.AUTOWIRE_BY_NAME);
		return new String[] {
				"classpath:/spring/applicationContext-resources.xml",
				"classpath:/spring/applicationContext-testing.xml" };
	}

	public void testBuscar() throws Exception {
//		showTime();
//		Map<String, Object> criterios = new HashMap<String, Object>();
//		showTime();
//		List<Biblioteca> list = this.bibliotecaDelegate.buscar(criterios);
//		showTime();
//		List<Biblioteca> list2 = this.bibliotecaDelegate.buscar(criterios);
//		showTime();
//		List<Biblioteca> list3 = this.bibliotecaDelegate.buscar(criterios);
//		showTime();
//
//		Assert.assertNotNull(list);
//		Assert.assertNotNull(list2);
//		Assert.assertNotNull(list3);
//		log.debug("Tamaño: " + list.size());

	}

	public void testGetAll() throws Exception {
//		showTime();
//		List<Biblioteca> list = this.bibliotecaDelegate.obtenerBibliotecas();
//		showTime();
//		List<Biblioteca> list2 = this.bibliotecaDelegate.obtenerBibliotecas();
//		showTime();
//		List<Biblioteca> list3 = this.bibliotecaDelegate.obtenerBibliotecas();
//		showTime();
//
//		Assert.assertNotNull(list);
//		Assert.assertNotNull(list2);
//		Assert.assertNotNull(list3);
//		log.debug("Tamaño: " + list.size());

	}
	
	private void showTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,S");
		Date time = new Date();
		log.error("-------------------------------------------------------- TIME..." + sdf.format(time));
	}

	public IBibliotecaDelegate getBibliotecaDelegate() {
		return bibliotecaDelegate;
	}

}
