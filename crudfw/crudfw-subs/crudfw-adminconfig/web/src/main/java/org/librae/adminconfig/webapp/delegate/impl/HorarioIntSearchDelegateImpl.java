package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Horario;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.service.IHorarioIntManager;
import org.librae.adminconfig.service.IHorarioManager;
import org.librae.adminconfig.webapp.delegate.IHorarioIntSearchDelegate;
import org.librae.adminconfig.webapp.form.HorarioForm;

/**
 * Implementacion del interfaz IHorarioIntSearchDelegate.
 *
 * @author aropero
 */
public class HorarioIntSearchDelegateImpl implements IHorarioIntSearchDelegate,
        Serializable {

    /**
     * Serial Version UID
     */
    private static final long  serialVersionUID = 2782127571601184032L;

    /**
     * Atributo para las trazas
     */
    protected final Log        log              = LogFactory
                                                        .getLog(HorarioIntSearchDelegateImpl.class);

    /**
     * Manager de HorarioInt.
     */
    private IHorarioIntManager horarioIntManager;

    /**
     * Manager de Horario
     */
    private IHorarioManager    horarioManager;

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.HashMap)
     */
    public List<HorarioInt> buscar(final Map<String, Object> criterios) {
        return horarioIntManager.buscar(criterios);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IHorarioIntSearchDelegate#prepararDatosVista(org.librae.adminconfig.webapp.form.HorarioForm,
     *      java.lang.String)
     */
    public HorarioForm prepararDatosVista(final HorarioForm form,
            final String biblioteca) {
        Horario horario = null;
        if (form.getIdHorario() != null) {
            horario = horarioManager.get(form.getIdHorario());
            form.fromEntity(horario);
            form.setBiblioteca(biblioteca);
        }
        return form;
    }

    // ================== Getters && Setters =====================
    public IHorarioManager getHorarioManager() {
        return horarioManager;
    }

    public void setHorarioManager(final IHorarioManager horarioManager) {
        this.horarioManager = horarioManager;
    }

    public IHorarioIntManager getHorarioIntManager() {
        return horarioIntManager;
    }

    public void setHorarioIntManager(final IHorarioIntManager horarioIntManager) {
        this.horarioIntManager = horarioIntManager;
    }
}
