package org.librae.adminconfig.webapp.ws.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Horario;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.adminconfig.service.IHorarioManager;
import org.librae.adminconfig.webapp.ws.service.IHorarioService;
import org.librae.common.exception.LibraeException;

/**
 * Clase del Web Service para servir los horarios de las bibliotecas.
 * 
 * @author asantamaria
 */
public class HorarioServiceImpl implements IHorarioService {

    private IHorarioManager    horarioManager;
    private IBibliotecaManager bibliotecaManager;

    /*
     * (non-Javadoc)
     * @see
     * org.librae.adminconfig.webapp.ws.IHorarioWS#getHorarioByBibliotecaId(
     * java.lang.String)
     */
    public Horario getHorarioByCod(String codBiblioteca) {
        List<Horario> horarios = null;
        Horario horario = null;

        final Map<String, Object> criterios = new HashMap<String, Object>();
        final Biblioteca biblioteca = bibliotecaManager
                .getBibliotecaByCodigo(codBiblioteca);

        if (biblioteca == null) {
            throw new LibraeException("01", "Codigo de biblioteca incorrecto");
        }

        criterios.put("idBiblioteca", biblioteca.getId());

        horarios = horarioManager.buscar(criterios);

        if (horarios.size() == 1) {
            horario = horarios.get(0);
        }

        return horario;
    }

    /**
     * @return the horarioManager
     */
    public IHorarioManager getHorarioManager() {
        return horarioManager;
    }

    /**
     * @param horarioManager
     *            the horarioManager to set
     */
    public void setHorarioManager(IHorarioManager horarioManager) {
        this.horarioManager = horarioManager;
    }

    /**
     * @return the bibliotecaManager
     */
    public IBibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    /**
     * @param bibliotecaManager
     *            the bibliotecaManager to set
     */
    public void setBibliotecaManager(IBibliotecaManager bibliotecaManager) {
        this.bibliotecaManager = bibliotecaManager;
    }
}
