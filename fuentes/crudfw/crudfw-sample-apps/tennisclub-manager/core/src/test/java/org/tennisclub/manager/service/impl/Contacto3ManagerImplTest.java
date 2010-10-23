
package org.tennisclub.manager.service.impl;

import org.jmock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ObjectRetrievalFailureException;

import org.tennisclub.manager.dao.IContacto3DAO;
import org.tennisclub.manager.model.Contacto3;
import es.uma.crudframework.service.impl.BaseManagerMockTestCase;

public class Contacto3ManagerImplTest extends BaseManagerMockTestCase {

    private final Long          ID      = -112L;

    private Contacto3            bean    = null;
    private Mock                daoMock = null;
    private Contacto3ManagerImpl manager = null;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        daoMock = new Mock(IContacto3DAO.class);
        manager = new Contacto3ManagerImpl((IContacto3DAO) daoMock.proxy());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        manager = null;
        daoMock = null;
    }

    @Test
    public void testGetContacto3() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("get").will(returnValue(new Contacto3()));

        bean = manager.get(ID);

        assertTrue(bean != null);
        daoMock.verify();
    }

    @Test
    public void testSaveContacto3() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("save").with(same(bean)).isVoid();

        manager.save(bean);
        daoMock.verify();
    }

    @Test
    public void testAddAndRemoveContacto3() throws Exception {
        bean = new Contacto3();

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

        Exception ex = new ObjectRetrievalFailureException(Contacto3.class,
        		bean.getId());

        daoMock.expects(once()).method("remove").isVoid();

        daoMock.expects(once()).method("get").will(throwException(ex));
        manager.remove(ID);

        try {
            manager.get(ID);
            fail("Contacto3 con identificador '" + ID + "' encontrado.");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        daoMock.verify();
    }
}