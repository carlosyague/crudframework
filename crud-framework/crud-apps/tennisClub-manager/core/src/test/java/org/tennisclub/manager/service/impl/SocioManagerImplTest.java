
package org.tennisclub.manager.service.impl;

import org.jmock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ObjectRetrievalFailureException;

import org.tennisclub.manager.dao.ISocioDAO;
import org.tennisclub.manager.model.Socio;
import es.uma.crudframework.service.impl.BaseManagerMockTestCase;

public class SocioManagerImplTest extends BaseManagerMockTestCase {

    private final Long          ID      = -112L;

    private Socio            bean    = null;
    private Mock                daoMock = null;
    private SocioManagerImpl manager = null;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        daoMock = new Mock(ISocioDAO.class);
        manager = new SocioManagerImpl((ISocioDAO) daoMock.proxy());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        manager = null;
        daoMock = null;
    }

    @Test
    public void testGetSocio() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("get").will(returnValue(new Socio()));

        bean = manager.get(ID);

        assertTrue(bean != null);
        daoMock.verify();
    }

    @Test
    public void testSaveSocio() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("save").with(same(bean)).isVoid();

        manager.save(bean);
        daoMock.verify();
    }

    @Test
    public void testAddAndRemoveSocio() throws Exception {
        bean = new Socio();

        // set required fields

        // set expected behavior on dao
        daoMock.expects(once()).method("save").with(same(bean)).isVoid();

        manager.save(bean);
        daoMock.verify();

        // reset expectations
        daoMock.reset();

        daoMock.expects(once()).method("remove").with(eq(ID));

        manager.remove(ID);
        daoMock.verify();

        // reset expectations
        daoMock.reset();

        // remove

        Exception ex = new ObjectRetrievalFailureException(Socio.class,
        		bean.getId());

        daoMock.expects(once()).method("remove").isVoid();

        daoMock.expects(once()).method("get").will(throwException(ex));
        manager.remove(ID);

        try {
            manager.get(ID);
            fail("Socio con identificador '" + ID + "' encontrado.");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        daoMock.verify();
    }
}