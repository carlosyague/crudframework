
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Jugador;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.IJugadorManager;

/**
 * Action-JSF asociado a los formularios de la entidad Jugador
 *
 * @author Carlos Yagüe
 */
public class JugadorFormAction extends BasePage implements Serializable {
    private IJugadorManager jugadorManager;
    private Jugador jugador = new Jugador();
    private Long id;

    // getter & setter del manager

    public IJugadorManager getJugadorManager() {
        return this.jugadorManager;
    }

    public void setJugadorManager(IJugadorManager jugadorManager) {
        this.jugadorManager = jugadorManager;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        jugadorManager.remove(jugador.getId());
        addMessage("jugador.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            jugador = jugadorManager.get(id);
        } else {
            jugador = new Jugador();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (jugador.getId() == null || jugador.getId() == 0);
        jugadorManager.save(jugador);

        String key = (isNew) ? "jugador.added" : "jugador.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}