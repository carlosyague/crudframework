
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.ITorneoManager;
import org.tennisclub.manager.model.Torneo;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class TorneoFormTest extends BasePageTestCase {
    private TorneoFormAction bean;
    private ITorneoManager torneoManager;

    public void setTorneoManager(ITorneoManager torneoManager) {
        this.torneoManager = torneoManager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new TorneoFormAction();
        bean.setTorneoManager(torneoManager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Torneo torneo = new Torneo();

        // enter all required fields
        bean.setTorneo(torneo);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getTorneo());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getTorneo());
        Torneo torneo = bean.getTorneo();

        // update required fields
        bean.setTorneo(torneo);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Torneo torneo = new Torneo();
        torneo.setId(-2L);
        bean.setTorneo(torneo);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}