package org.librae.common.webapp.action;

import java.net.URL;
import java.net.URLClassLoader;

import javax.faces.FactoryFinder;
import javax.faces.application.ApplicationFactory;
import javax.faces.component.UIViewRoot;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.render.RenderKitFactory;

import junit.framework.TestCase;

import mock.MockHttpServletRequest;
import mock.MockServletContext;
import mock.MockHttpServletResponse;

import org.apache.shale.test.mock.MockApplication;
import org.apache.shale.test.mock.MockExternalContext;
import org.apache.shale.test.mock.MockFacesContext;
import org.apache.shale.test.mock.MockFacesContextFactory;
import org.apache.shale.test.mock.MockHttpSession;
import org.apache.shale.test.mock.MockLifecycle;
import org.apache.shale.test.mock.MockLifecycleFactory;
import org.apache.shale.test.mock.MockRenderKit;
import org.apache.shale.test.mock.MockServletConfig;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;


public abstract class BaseIntegrationTest extends AbstractTransactionalDataSourceSpringContextTests {


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Construct a new instance of this test case.</p>
     *
     * @param name Name of this test case
     */
    public BaseIntegrationTest(String name) {
        super(name);
    }


    // ---------------------------------------------------- Overall Test Methods


    /**
     * <p>Set up instance variables required by this test case.</p>
     */
//    protected void setUp() throws Exception {
//
//        // Set up a new thread context class loader
//        threadContextClassLoader = Thread.currentThread().getContextClassLoader();
//        Thread.currentThread().setContextClassLoader(new URLClassLoader(new URL[0],
//                this.getClass().getClassLoader()));
//
//        // Set up Servlet API Objects
//        session = new MockHttpSession();
//        servletContext = new MockServletContext(session);
//        config = new MockServletConfig(servletContext);
//        session.setServletContext(servletContext);
//        request = new MockHttpServletRequest(session);
//        request.setServletContext(servletContext);
//        response = new MockHttpServletResponse();
//
//        // Set up JSF API Objects
//        FactoryFinder.releaseFactories();
//        FactoryFinder.setFactory(FactoryFinder.APPLICATION_FACTORY,
//        "org.apache.shale.test.mock.MockApplicationFactory");
//        FactoryFinder.setFactory(FactoryFinder.FACES_CONTEXT_FACTORY,
//        "org.apache.shale.test.mock.MockFacesContextFactory");
//        FactoryFinder.setFactory(FactoryFinder.LIFECYCLE_FACTORY,
//        "org.apache.shale.test.mock.MockLifecycleFactory");
//        FactoryFinder.setFactory(FactoryFinder.RENDER_KIT_FACTORY,
//        "org.apache.shale.test.mock.MockRenderKitFactory");
//
//        externalContext =
//            new MockExternalContext(servletContext, request, response);
//        lifecycleFactory = (MockLifecycleFactory)
//        FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
//        lifecycle = (MockLifecycle)
//        lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
//        facesContextFactory = (MockFacesContextFactory)
//        FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
//        facesContext = (MockFacesContext)
//        facesContextFactory.getFacesContext(servletContext,
//                request,
//                response,
//                lifecycle);
//        externalContext = (MockExternalContext) facesContext.getExternalContext();
//        UIViewRoot root = new UIViewRoot();
//        root.setViewId("/viewId");
//        root.setRenderKitId(RenderKitFactory.HTML_BASIC_RENDER_KIT);
//        facesContext.setViewRoot(root);
//        ApplicationFactory applicationFactory = (ApplicationFactory)
//          FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
//        application = (MockApplication) applicationFactory.getApplication();
//        facesContext.setApplication(application);
//        RenderKitFactory renderKitFactory = (RenderKitFactory)
//        FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
//        renderKit = new MockRenderKit();
//        renderKitFactory.addRenderKit(RenderKitFactory.HTML_BASIC_RENDER_KIT, renderKit);
//
//    }


//    /**
//     * <p>Tear down instance variables required by this test case.</p>
//     */
//    protected void tearDown() throws Exception {
//
//        application = null;
//        config = null;
//        externalContext = null;
//        facesContext.release();
//        facesContext = null;
//        lifecycle = null;
//        lifecycleFactory = null;
//        renderKit = null;
//        request = null;
//        response = null;
//        servletContext = null;
//        session = null;
//        FactoryFinder.releaseFactories();
//
//        Thread.currentThread().setContextClassLoader(threadContextClassLoader);
//        threadContextClassLoader = null;
//
//    }


    // ------------------------------------------------------ Instance Variables


    // Mock object instances for our tests
    protected MockApplication         application = null;
    protected MockServletConfig       config = null;
    protected MockExternalContext     externalContext = null;
    protected MockFacesContext        facesContext = null;
    protected MockFacesContextFactory facesContextFactory = null;
    protected MockLifecycle           lifecycle = null;
    protected MockLifecycleFactory    lifecycleFactory = null;
    protected MockRenderKit           renderKit = null;
    protected MockHttpServletRequest  request = null;
    protected MockHttpServletResponse response = null;
    protected MockServletContext      servletContext = null;
    protected MockHttpSession         session = null;

    // Thread context class loader saved and restored after each test
    private ClassLoader threadContextClassLoader = null;

}
