package org.librae.adminconfig.webapp.ws.service;

import org.librae.adminconfig.model.Horario;

/**
 * Interfaz del Web Service para servir los horarios de las bibliotecas.
 * 
 * @author asantamaria
 */
public interface IHorarioService {

    /**
     * Método que a partir del código de una biblioteca, devuelve su horario
     * 
     * @param codBiblioteca
     * @return
     */
    public Horario getHorarioByCod(String codBiblioteca);
}
