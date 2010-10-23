
package org.tennisclub.manager.service.impl;

import org.jmock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ObjectRetrievalFailureException;

import org.tennisclub.manager.dao.IContacto4DAO;
import org.tennisclub.manager.model.Contacto4;
import es.uma.crudframework.service.impl.BaseManagerMockTestCase;

public class Contacto4ManagerImplTest extends BaseManagerMockTestCase {

    private final Long          ID      = -112L;

    private Contacto4            bean    = null;
    private Mock                daoMock = null;
    private Contacto4ManagerImpl manager = null;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        daoMock = new Mock(IContacto4DAO.class);
        manager = new Contacto4ManagerImpl((IContacto4DAO) daoMock.proxy());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        manager = null;
        daoMock = null;
    }

    @Test
    public void testGetContacto4() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("get").will(returnValue(new Contacto4()));

        bean = manager.get(ID);

        assertTrue(bean != null);
        daoMock.verify();
    }

    @Test
    public void testSaveContacto4() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("save").with(same(bean)).isVoid();

        manager.save(bean);
        daoMock.verify();
    }

    @Test
    public void testAddAndRemoveContacto4() throws Exception {
        bean = new Contacto4();

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

        Exception ex = new ObjectRetrievalFailureException(Contacto4.class,
        		bean.getId());

        daoMock.expects(once()).method("remove").isVoid();

        daoMock.expects(once()).method("get").will(throwException(ex));
        manager.remove(ID);

        try {
            manager.get(ID);
            fail("Contacto4 con identificador '" + ID + "' encontrado.");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        daoMock.verify();
    }
}