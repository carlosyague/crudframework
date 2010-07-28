package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.adminconfig.service.IHorarioIntManager;
import org.librae.adminconfig.webapp.bean.IntervaloHorario;
import org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate;
import org.librae.adminconfig.webapp.form.HorarioForm;
import org.librae.adminconfig.webapp.form.HorarioIntForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.util.DateUtil;

/**
 * Delegado para la gestión de intervalos de horarios.
 *
 * @author aropero
 */
public class HorarioIntDelegateImpl implements IHorarioIntDelegate,
        Serializable {

    /**
     * Serial Version UID
     */
    private static final long  serialVersionUID = 1L;

    /** Manager de HorarioInt */
    private IHorarioIntManager horarioIntManager;

    /** Manager de Biblioteca */
    private IBibliotecaManager bibliotecaManager;

    /**
     * Atributo para las trazas
     */
    protected final Log        log              = LogFactory
                                                        .getLog(HorarioIntDelegateImpl.class);

    /**
     * @see org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate#prepararDatosVista(org.librae.adminconfig.webapp.form.HorarioIntForm,
     *      java.lang.String)
     */
    public List<IntervaloHorario> prepararDatosVista(final HorarioIntForm form, String key,
            HorarioForm horarioForm, List<HorarioInt> intervalos) {
        HorarioInt horarioInt = null;
        /** Vamos a editar un HorarioInt que aún no se encuentra en BBDD */
        if (intervalos==null) {
        	intervalos = horarioIntManager.getIntervalos(horarioForm.getIdHorario()); 
        } else {
        	horarioInt = intervalos.get(
        			Integer.valueOf(key).intValue());
        }
        form.fromEntity(horarioInt);
        return form.getIntervalos();
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate#validarIntervalo(org.librae.adminconfig.webapp.form.HorarioIntForm)
     */
    public void validarIntervalo(final HorarioIntForm form) {
        if (form.getIntervalos().size() == 5) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ADM_HORARIOS_INTERVALO_MAXIMO_SUPERADO");
        }
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate#modificarIntervalos(org.librae.adminconfig.webapp.form.HorarioIntForm,
     *      java.lang.String, java.util.List)
     */
    public List modificarIntervalos(
            final HorarioIntForm formHorInt, String key,
            final List intervHor) {

        HorarioInt horarioInt = null;
        List listaModificada = intervHor;
        if (listaModificada==null) {
        	listaModificada = new ArrayList();
        }
        horarioInt = formHorInt.toEntity();
        if ((key == null) || (listaModificada.isEmpty())) {
            /** Creamos */
            listaModificada.add(horarioInt);
        } else {
            /** Modificamos objeto de Session */
            listaModificada.set(Integer.valueOf(key).intValue(), horarioInt);
        }

        return listaModificada;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate#actualizarListIntervalos(org.librae.adminconfig.webapp.form.HorarioIntForm)
     */
    public HorarioIntForm actualizarListIntervalos(
            HorarioIntForm horarioIntForm, HorarioIntForm form) {
        // Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff
        final Timestamp horaInicio = Timestamp.valueOf("2000-02-02 "
                + form.getHoraI() + ":" + form.getMinutoI() + ":00");
        final Timestamp horaFin = Timestamp.valueOf("2000-02-02 "
                + form.getHoraF() + ":" + form.getMinutoF() + ":00");

        final IntervaloHorario intervaloH = new IntervaloHorario(horaInicio,
                horaFin);

        /** Validamos el nuevo intervalo */
        esHoraCorrecta(intervaloH, horarioIntForm.getIntervalos());

        /**
         * Actualizamos el formulario de session con el nuevo intervalo
         */
        horarioIntForm.setHoraI(form.getHoraI());
        horarioIntForm.setHoraF(form.getHoraF());
        horarioIntForm.setMinutoI(form.getMinutoI());
        horarioIntForm.setMinutoF(form.getMinutoF());

        if (form.getIntervalos()==null) {
        	form.setIntervalos(new ArrayList());
        }
        form.getIntervalos().add(intervaloH);
        horarioIntForm.setIntervalos(form.getIntervalos());
        form = horarioIntForm;
        return form;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate#actLabSegunCalendario(org.librae.adminconfig.webapp.form.HorarioIntForm)
     */
    public String actLabSegunCalendario(Long idBilioteca) {

        final String diasSemana = bibliotecaManager
                .actLabSegunCalendario(idBilioteca);

        return diasSemana;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate#validarFormulario(org.librae.adminconfig.webapp.form.HorarioIntForm)
     */
    public void validarFormulario(HorarioIntForm form, List<IntervaloHorario> intervalos) {
        /* Se ha seleccionado algún día de la semana */
        if (!form.algunaSeleccionDia()) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ADM_HORARIOS_SEMANA_INVALIDA");
        }
        /* Las fechas estan correctamente creadas */
        if ((form.getComienzo() != null) && (form.getFin() != null)) {
            if ((DateUtil.compararFechas(form.getComienzo(), form.getFin()) == 1)
                    || (DateUtil.compararFechas(form.getComienzo(), form
                            .getFin()) == 0)) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG,
                        "ADM_HORARIOS_FECHA_INVALIDA");
            }
        } else if ((form.getComienzo() == null) && (form.getFin() != null)) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ADM_HORARIOS_FECHA_INVALIDA");
        }
        /* Se ha creado al menos un intervalo horario */
        if ((intervalos == null) || (intervalos.isEmpty())) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ADM_HORARIOS_INTERVALO_INVALIDO");
        }
    }

    /**
     * Método que comprueba si intervalo horario a añadir está correctamente
     * creado y que además no se solapa con ningún intervalo creado previamente.
     * En caso contrario, lanzamos excepción.
     *
     * @param intervaloAComparar
     *            , intervalo horario a validar
     * @param intervalos
     *            , lista de intervalos horarios a validar.
     */
    private void esHoraCorrecta(IntervaloHorario intervaloAComparar,
            List<IntervaloHorario> intervalos) {

        final Timestamp horaInicio = intervaloAComparar.getHoraInicio();
        final Timestamp horaFin = intervaloAComparar.getHoraFin();
        /* Validamos el intervalo */
        if ((horaInicio.getTime() > horaFin.getTime())
                || (horaInicio.getTime() == horaFin.getTime())) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ADM_HORARIOS_INTERVALO_ERRONEO");
        }
        /* Comprobamos que no se solape con alguno ya creado */

        /* Conversión de TimeStamp a Date */
        final Date utilDateInicio = new Date(horaInicio.getTime());

        for (int i = 0; i < intervalos.size(); i++) {
            final IntervaloHorario intervaloAct = intervalos.get(i);

            /* Conversión de TimeStamp a Date */
            final Date utilDateFin = new Date(intervaloAct.getHoraFin()
                    .getTime());

            if (utilDateInicio.before(utilDateFin)) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG,
                        "ADM_HORARIOS_INTERVALO_SOLAPADO");
            }
        }
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate#eliminarIntervalo(org.librae.adminconfig.webapp.form.HorarioIntForm,
     *      java.lang.String, java.util.List)
     */
    public List eliminarIntervalo(HorarioIntForm formHorInt,
            String key, List intervHor) {

        return horarioIntManager.eliminarIntervalo(
                formHorInt.getIdHorarioInt(), intervHor, key);
    }

    // ===================== getter & setter =======================
    public IHorarioIntManager getHorarioIntManager() {
        return horarioIntManager;
    }

    public void setHorarioIntManager(final IHorarioIntManager horarioIntManager) {
        this.horarioIntManager = horarioIntManager;
    }

    public IBibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    public void setBibliotecaManager(IBibliotecaManager bibliotecaManager) {
        this.bibliotecaManager = bibliotecaManager;
    }
}
