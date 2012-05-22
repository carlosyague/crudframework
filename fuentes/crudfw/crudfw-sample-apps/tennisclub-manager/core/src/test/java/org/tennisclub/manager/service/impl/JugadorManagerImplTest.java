
package org.tennisclub.manager.service.impl;

import org.jmock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ObjectRetrievalFailureException;

import org.tennisclub.manager.dao.IJugadorDAO;
import org.tennisclub.manager.model.Jugador;
import es.uma.crudframework.service.impl.BaseManagerMockTestCase;

public class JugadorManagerImplTest extends BaseManagerMockTestCase {

    private final Long          ID      = -112L;

    private Jugador            bean    = null;
    private Mock                daoMock = null;
    private JugadorManagerImpl manager = null;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        daoMock = new Mock(IJugadorDAO.class);
        manager = new JugadorManagerImpl((IJugadorDAO) daoMock.proxy());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        manager = null;
        daoMock = null;
    }

    @Test
    public void testGetJugador() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("get").will(returnValue(new Jugador()));

        bean = manager.get(ID);

        assertTrue(bean != null);
        daoMock.verify();
    }

    @Test
    public void testSaveJugador() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("save").with(same(bean)).isVoid();

        manager.save(bean);
        daoMock.verify();
    }

    @Test
    public void testAddAndRemoveJugador() throws Exception {
        bean = new Jugador();

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

        Exception ex = new ObjectRetrievalFailureException(Jugador.class,
        		bean.getId());

        daoMock.expects(once()).method("remove").isVoid();

        daoMock.expects(once()).method("get").will(throwException(ex));
        manager.remove(ID);

        try {
            manager.get(ID);
            fail("Jugador con identificador '" + ID + "' encontrado.");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        daoMock.verify();
    }
}