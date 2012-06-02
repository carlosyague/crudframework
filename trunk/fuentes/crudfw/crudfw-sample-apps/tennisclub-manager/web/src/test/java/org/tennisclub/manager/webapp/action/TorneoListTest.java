package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.ITorneoManager;
import org.tennisclub.manager.model.Torneo;
import org.junit.Test;

public class TorneoListTest extends BasePageTestCase {
    private TorneoListAction bean;
    private ITorneoManager torneoManager;

    public void setTorneoManager(ITorneoManager torneoManager) {
        this.torneoManager = torneoManager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() {
        super.onSetUp();
        bean = new TorneoListAction();
        bean.setTorneoManager(torneoManager);

        // add a test torneo to the database
        Torneo torneo = new Torneo();

        // enter all required fields

        torneoManager.save(torneo);
    }

    @Override
    protected void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getTorneoes().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}