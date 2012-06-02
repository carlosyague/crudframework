
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IJugadorManager;

/**
 * Action-JSF asociado a los listados de la entidad Jugador
 *
 * @author Carlos Yagüe
 */
public class JugadorListAction extends BasePage implements Serializable {
    private IJugadorManager jugadorManager;

    public IJugadorManager getJugadorManager() {
        return this.jugadorManager;
    }

    public void setJugadorManager(IJugadorManager jugadorManager) {
        this.jugadorManager = jugadorManager;
    }

    public JugadorListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getJugadors() {
        return sort(jugadorManager.getAll());
    }
}

