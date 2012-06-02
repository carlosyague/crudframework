package org.tennisclub.manager.webapp.action;

import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.tennisclub.manager.service.IJugadorManager;
import org.tennisclub.manager.model.Jugador;
import org.junit.Test;

public class JugadorListTest extends BasePageTestCase {
    private JugadorListAction bean;
    private IJugadorManager jugadorManager;

    public void setJugadorManager(IJugadorManager jugadorManager) {
        this.jugadorManager = jugadorManager;
    }

    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() {
        super.onSetUp();
        bean = new JugadorListAction();
        bean.setJugadorManager(jugadorManager);

        // add a test jugador to the database
        Jugador jugador = new Jugador();

        // enter all required fields

        jugadorManager.save(jugador);
    }

    @Override
    protected void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testSearch() throws Exception {
        assertTrue(bean.getJugadors().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}