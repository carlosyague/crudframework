
package org.tennisclub.manager.webapp.action;

import org.tennisclub.manager.service.IJugadorManager;
import org.tennisclub.manager.model.Jugador;
import es.uma.crudframework.exception.CrudException;
import es.uma.crudframework.webapp.action.BasePageTestCase;
import org.junit.Test;

public class JugadorFormTest extends BasePageTestCase {
    private JugadorFormAction bean;
    private IJugadorManager jugadorManager;

    public void setJugadorManager(IJugadorManager jugadorManager) {
        this.jugadorManager = jugadorManager;
    }

    @Override
    protected void onSetUp() throws CrudException {
        super.onSetUp();
        bean = new JugadorFormAction();
        bean.setJugadorManager(jugadorManager);
    }

    @Override
    protected void onTearDown() throws CrudException {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testAdd() throws CrudException {
        Jugador jugador = new Jugador();

        // enter all required fields
        bean.setJugador(jugador);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testEdit() throws CrudException {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getJugador());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getJugador());
        Jugador jugador = bean.getJugador();

        // update required fields
        bean.setJugador(jugador);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws CrudException {
        log.debug("testing remove...");
        Jugador jugador = new Jugador();
        jugador.setId(-2L);
        bean.setJugador(jugador);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}