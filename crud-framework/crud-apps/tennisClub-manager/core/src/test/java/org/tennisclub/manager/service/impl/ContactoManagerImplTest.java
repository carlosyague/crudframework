
package org.tennisclub.manager.service.impl;

import org.jmock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ObjectRetrievalFailureException;

import org.tennisclub.manager.dao.IContactoDAO;
import org.tennisclub.manager.model.Contacto;
import es.uma.crudframework.service.impl.BaseManagerMockTestCase;

public class ContactoManagerImplTest extends BaseManagerMockTestCase {

    private final Long          ID      = -112L;

    private Contacto            bean    = null;
    private Mock                daoMock = null;
    private ContactoManagerImpl manager = null;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        daoMock = new Mock(IContactoDAO.class);
        manager = new ContactoManagerImpl((IContactoDAO) daoMock.proxy());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        manager = null;
        daoMock = null;
    }

    @Test
    public void testGetContacto() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("get").will(returnValue(new Contacto()));

        bean = manager.get(ID);

        assertTrue(bean != null);
        daoMock.verify();
    }

    @Test
    public void testSaveContacto() throws Exception {
        // set expected behavior on dao
        daoMock.expects(once()).method("save").with(same(bean)).isVoid();

        manager.save(bean);
        daoMock.verify();
    }

    @Test
    public void testAddAndRemoveContacto() throws Exception {
        bean = new Contacto();

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

        Exception ex = new ObjectRetrievalFailureException(Contacto.class,
        		bean.getId());

        daoMock.expects(once()).method("remove").isVoid();

        daoMock.expects(once()).method("get").will(throwException(ex));
        manager.remove(ID);

        try {
            manager.get(ID);
            fail("Contacto con identificador '" + ID + "' encontrado.");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        daoMock.verify();
    }
}