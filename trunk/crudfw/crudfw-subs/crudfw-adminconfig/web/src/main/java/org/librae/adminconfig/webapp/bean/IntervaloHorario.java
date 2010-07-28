package org.librae.adminconfig.webapp.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Bean para el dataTable de la pagina de Detalle Intervalo (Horario).
 * 
 * @author aropero
 */
public class IntervaloHorario implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    private Timestamp         horaInicio       = null;

    private Timestamp         horaFin          = null;

    public IntervaloHorario(final Timestamp horaInicio, final Timestamp horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // =============== Getters && Setters ===============
    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(final Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Timestamp getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(final Timestamp horaFin) {
        this.horaFin = horaFin;
    }
}
