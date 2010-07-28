package org.librae.adminconfig.webapp.action;

import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.FactoryFinder;
import javax.faces.application.ApplicationFactory;
import javax.faces.component.UIViewRoot;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.render.RenderKitFactory;

import junit.framework.Assert;
import mock.MockHttpServletRequest;
import mock.MockHttpServletResponse;
import mock.MockServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shale.test.mock.MockApplication;
import org.apache.shale.test.mock.MockExternalContext;
import org.apache.shale.test.mock.MockFacesContext;
import org.apache.shale.test.mock.MockFacesContextFactory;
import org.apache.shale.test.mock.MockHttpSession;
import org.apache.shale.test.mock.MockLifecycle;
import org.apache.shale.test.mock.MockLifecycleFactory;
import org.apache.shale.test.mock.MockRenderKit;
import org.apache.shale.test.mock.MockServletConfig;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate;
import org.librae.common.webapp.session.SessionManager;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Profiling para el Action de la busqueda de bibliotecas.
 * 
 * @author jcisneros
 */
public class BibliotecaListActionProfilingTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	// Mock object instances for our tests
	protected MockApplication application = null;
	protected MockServletConfig config = null;
	protected MockExternalContext externalContext = null;
	protected MockFacesContext facesContext = null;
	protected MockFacesContextFactory facesContextFactory = null;
	protected MockLifecycle lifecycle = null;
	protected MockLifecycleFactory lifecycleFactory = null;
	protected MockRenderKit renderKit = null;
	protected MockHttpServletRequest request = null;
	protected MockHttpServletResponse response = null;
	protected MockServletContext servletContext = null;
	protected MockHttpSession session = null;

	// Thread context class loader saved and restored after each test
	private ClassLoader threadContextClassLoader = null;

	public BibliotecaListActionProfilingTest(String name) {
		super(name);
	}

	protected final Log log = LogFactory
			.getLog(BibliotecaListActionProfilingTest.class);

	private BibliotecaListAction bibliotecaListAction;
	private IBibliotecaDelegate bibliotecaDelegate;

	protected String[] getConfigLocations() {
		return new String[] {
				"classpath:/spring/applicationContext-resources.xml",
				"classpath:/spring/applicationContext-testing.xml" };
	}

	protected void onSetUp() {
		bibliotecaListAction = new BibliotecaListAction();
		bibliotecaListAction.setDelegate(bibliotecaDelegate);
	}

	public void testGetAll() throws Exception {
		// Set up a new thread context class loader
//		threadContextClassLoader = Thread.currentThread()
//				.getContextClassLoader();
//		Thread.currentThread()
//				.setContextClassLoader(
//						new URLClassLoader(new URL[0], this.getClass()
//								.getClassLoader()));
//
//		// Set up Servlet API Objects
//		session = new MockHttpSession();
//		servletContext = new MockServletContext(session);
//		config = new MockServletConfig(servletContext);
//		session.setServletContext(servletContext);
//		request = new MockHttpServletRequest(session);
//		request.setServletContext(servletContext);
//		response = new MockHttpServletResponse();
//
//		// Set up JSF API Objects
//		FactoryFinder.releaseFactories();
//		FactoryFinder.setFactory(FactoryFinder.APPLICATION_FACTORY,
//				"org.apache.shale.test.mock.MockApplicationFactory");
//		FactoryFinder.setFactory(FactoryFinder.FACES_CONTEXT_FACTORY,
//				"org.apache.shale.test.mock.MockFacesContextFactory");
//		FactoryFinder.setFactory(FactoryFinder.LIFECYCLE_FACTORY,
//				"org.apache.shale.test.mock.MockLifecycleFactory");
//		FactoryFinder.setFactory(FactoryFinder.RENDER_KIT_FACTORY,
//				"org.apache.shale.test.mock.MockRenderKitFactory");
//
//		externalContext = new MockExternalContext(servletContext, request,
//				response);
//		lifecycleFactory = (MockLifecycleFactory) FactoryFinder
//				.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
//		lifecycle = (MockLifecycle) lifecycleFactory
//				.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
//		facesContextFactory = (MockFacesContextFactory) FactoryFinder
//				.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
//		facesContext = (MockFacesContext) facesContextFactory.getFacesContext(
//				servletContext, request, response, lifecycle);
//		externalContext = (MockExternalContext) facesContext
//				.getExternalContext();
//		UIViewRoot root = new UIViewRoot();
//		root.setViewId("/viewId");
//		root.setRenderKitId(RenderKitFactory.HTML_BASIC_RENDER_KIT);
//		facesContext.setViewRoot(root);
//		ApplicationFactory applicationFactory = (ApplicationFactory) FactoryFinder
//				.getFactory(FactoryFinder.APPLICATION_FACTORY);
//		application = (MockApplication) applicationFactory.getApplication();
//		facesContext.setApplication(application);
//		RenderKitFactory renderKitFactory = (RenderKitFactory) FactoryFinder
//				.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
//		renderKit = new MockRenderKit();
//		renderKitFactory.addRenderKit(RenderKitFactory.HTML_BASIC_RENDER_KIT,
//				renderKit);
//
//		bibliotecaListAction = new BibliotecaListAction();
//		bibliotecaListAction.setDelegate(bibliotecaDelegate);
//		bibliotecaListAction.setIdListado("bibliotecas");
//		bibliotecaListAction
//				.setNameForm("org.librae.adminconfig.webapp.form.BibliotecaCriteriaForm");
//		BibliotecaListAction bibliotecaAction = (BibliotecaListAction) facesContext
//				.getApplication().getVariableResolver().resolveVariable(
//						facesContext, "bibliotecaListAction");
//		System.out.println("acesContext.getApplication()..."
//				+ facesContext.getApplication());
//		SessionManager.init(request, response, servletContext);
//		showTime();
//		System.out.println("bibliotecaListAction..." + bibliotecaListAction);
//		System.out.println("bibliotecaDelegate..." + bibliotecaDelegate);
//		bibliotecaListAction.buscar();
//		showTime();
//		List<Biblioteca> list = this.bibliotecaListAction.getListado();
//		showTime();
//		List<Biblioteca> list2 = this.bibliotecaListAction.getListado();
//		showTime();
//		List<Biblioteca> list3 = this.bibliotecaListAction.getListado();
//		showTime();
//
//		Assert.assertNotNull(list);
//		Assert.assertNotNull(list2);
//		Assert.assertNotNull(list3);
//
//		log.debug("Tamaño: " + list.size());
	}

	private void showTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,S");
		Date time = new Date();
		log.debug("TIME: " + sdf.format(time));
	}

	// Getters && Setters

	public IBibliotecaDelegate getBibliotecaDelegate() {
		return bibliotecaDelegate;
	}

	public void setBibliotecaDelegate(IBibliotecaDelegate bibliotecaDelegate) {
		this.bibliotecaDelegate = bibliotecaDelegate;
	}

}
