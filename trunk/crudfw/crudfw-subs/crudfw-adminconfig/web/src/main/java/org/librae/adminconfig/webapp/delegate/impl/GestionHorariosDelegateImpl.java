package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Horario;
import org.librae.adminconfig.service.IHorarioManager;
import org.librae.adminconfig.webapp.delegate.IGestionHorariosDelegate;

/**
 * Implementacion del interfaz IGestionHorariosDelegate.
 * 
 * @author jcisneros
 */
public class GestionHorariosDelegateImpl implements IGestionHorariosDelegate,
        Serializable {

    /**
     * Numero de serializacon.
     */
    private static final long serialVersionUID = 3687777294386658149L;

    /**
     * Manager de usuario.
     */
    private IHorarioManager   horarioManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<Horario> buscar(final Map<String, Object> criterios) {
        return this.horarioManager.buscar(criterios);
    }

    // Getters && Setters

    public IHorarioManager getHorarioManager() {
        return this.horarioManager;
    }

    public void setHorarioManager(final IHorarioManager horarioManager) {
        this.horarioManager = horarioManager;
    }

}
