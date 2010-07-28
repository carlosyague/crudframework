package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Calendario;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.webapp.bean.IntervaloHorario;
import org.librae.adminconfig.webapp.delegate.IHorarioIntDelegate;
import org.librae.adminconfig.webapp.form.CalendarioForm;
import org.librae.adminconfig.webapp.form.HorarioForm;
import org.librae.adminconfig.webapp.form.HorarioIntForm;
import org.librae.common.Constants;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.DateUtil;
import org.librae.common.webapp.action.ConvertUtil;
import org.librae.common.webapp.action.SessionSearchAction;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los formularios de la entidad HorarioInt
 * 
 * @author aropero
 */
public class HorarioIntFormAction extends SessionSearchAction implements
        Serializable {

    /**
     * Serial Version UID
     */
    private static final long   serialVersionUID = -9118791929565943906L;

    /** formulario de datos de HorarioInt */
    private HorarioIntForm      form             = new HorarioIntForm();
    private Long                idHorarioInt;
    private IHorarioIntDelegate horarioIntDelegate;
    private String              key;
    private Integer             pos              = -1;
    private String              keyInt;
    private Integer             posInt           = -1;

    public HorarioIntFormAction() {
        setSortColumn("horaInicio");
    }

    /**
     * Método invocado cuando se entra en la pantalla de Horario. Se encargará
     * de obtener de session el posible objeto HorarioIntForm para inicializar
     * los datos
     * 
     * @return cadena vacia
     */
    public String getInit() {
        log.debug("init");

        if (getSessionManager() != null) {
            final HorarioIntForm horarioIntForm = (HorarioIntForm) getSessionManager()
                    .getAttribute(Constants.HORARIOINT_SESSION_NAME);
            if (horarioIntForm != null) {
                form = horarioIntForm;
            }
        }

        return "";
    }

    /**
     * Método invocado cuando se entra en el formulario de edición de un
     * HorarioInt. Se encargará de preparar los datos para su correcta
     * visualización invocando a su método delegado prepararDatosVista
     * 
     * @return String con el identificador de navigation rule (edit)
     */
    public String edit() {
        final String page = "edit";
        HorarioForm horarioForm = null;

        try {
            form.setIdHorarioInt(idHorarioInt);

            if (getSessionManager() != null) {
                horarioForm = (HorarioForm) getSessionManagerParam(Constants.HORARIO_SESSION_NAME);
                List<HorarioInt> horariosInt = (List<HorarioInt>) getSessionManagerParam("horariosInt");
                setSessionManagerParam(Constants.HORARIO_SESSION_NAME,
                        horarioForm);
                setSessionManagerParam("intervalo_key", key);
                List<IntervaloHorario> intervalos = horarioIntDelegate
                        .prepararDatosVista(form, key, horarioForm, horariosInt);

                // setSessionManagerParam("intervalos",
                // convertirIntervaloHorario(intervalos));
                setSessionManagerParam(Constants.HORARIOINT_SESSION_NAME, form);
                setSessionManagerParam("intervalos", intervalos);
            }

        } catch (final LibraeException e) {
            log.info("No se puede editar el intervalo: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "list";
        } catch (final Exception e) {
            log.error("No se puede editar el nuevo intervalo: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_GENERICO");
            setWindowMessages(wm);
            return "list";
        }
        return page;
    }

    private List<IntervaloHorario> convertirIntervaloHorario(
            List<HorarioInt> intervalos) {
        List<IntervaloHorario> intervalosHorarios = new ArrayList<IntervaloHorario>();
        for (HorarioInt intervalo : intervalos) {
        }
        return intervalosHorarios;
    }

    /**
     * Método invocado al pinchar en guardar. Guarda en session un objeto
     * HorarioIntForm con los datos y la key Constants.HORARIOINT_SESSION_NAME a
     * la espera de que la pantalla llamante lo gestione. Actualiza el listado
     * de intervalos horarios del Horario.
     * 
     * @return String con el identificador de navigation rule (list)
     */
    public String save() {
        HorarioForm horarioForm = null;

        try {
            /* Actualizamos el listado de intervalos horarios en el Horario */
            if (getSessionManager() != null) {
                key = (String) getSessionManager()
                        .getAttribute("intervalo_key");
                if (getSessionManagerParam(Constants.HORARIO_SESSION_NAME) != null) {
                    horarioForm = (HorarioForm) getSessionManagerParam(Constants.HORARIO_SESSION_NAME);
                }
            }

            final List<HorarioInt> horariosInt = (List<HorarioInt>) getSessionManagerParam("horariosInt");
            final List<IntervaloHorario> intervalos = (List<IntervaloHorario>) getSessionManagerParam("intervalos");
            /* Realizamos las validaciones correspondientes */
            horarioIntDelegate.validarFormulario(form, intervalos);
            form.setIntervalos(intervalos);
            final List listadoNuevo = horarioIntDelegate.modificarIntervalos(
                    form, key, horariosInt);

            /* Actualizamos el formulario de Horario en session */
            setSessionManagerParam(Constants.HORARIO_SESSION_NAME, horarioForm);

            setSessionManagerParam("horariosInt", listadoNuevo);

            // Eliminamos de session la posicion del HorarioInt editado
            removeSessionManagerParam("intervalo_key");
            removeSessionManagerParam("intervalos");
        } catch (final LibraeException e) {
            log.info("No se puede guardar un nuevo intervalo: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("No se puede guardar un nuevo intervalo: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_GENERICO");
            setWindowMessages(wm);
            return "";
        }

        return "list";
    }

    public List<IntervaloHorario> getListadoSinPaginacion() {
        return (List<IntervaloHorario>) getSessionManagerParam("intervalos");
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtrosPdf = new LinkedHashMap<String, Object>();
        final HorarioForm horarioForm = (HorarioForm) getSessionManager()
                .getAttribute(Constants.HORARIO_SESSION_NAME);
        String diasLaborables = new String(" ");
        if (horarioForm != null) {
            if (horarioForm.getBiblioteca() != null) {
                filtrosPdf.put(ComboLocaleManager.get("horario.bilioteca"),
                        horarioForm.getBiblioteca());
            }
            if (horarioForm.getFechaActualizacion() != null) {
                final String fecha = DateUtil.convertDateToString(horarioForm
                        .getFechaActualizacion());
                filtrosPdf.put(ComboLocaleManager
                        .get("horario.fechaActualizacion"), fecha);
            }
        }

        if (form != null) {

            if (form.getLunes()) {
                diasLaborables = diasLaborables
                        + ComboLocaleManager.get("lunes") + ", ";
            }
            if (form.getMartes()) {
                diasLaborables = diasLaborables
                        + ComboLocaleManager.get("martes") + ", ";
            }
            if (form.getMiercoles()) {
                diasLaborables = diasLaborables
                        + ComboLocaleManager.get("miercoles") + ", ";
            }
            if (form.getJueves()) {
                diasLaborables = diasLaborables
                        + ComboLocaleManager.get("jueves") + ", ";
            }
            if (form.getViernes()) {
                diasLaborables = diasLaborables
                        + ComboLocaleManager.get("viernes") + ", ";
            }
            if (form.getSabado()) {
                diasLaborables = diasLaborables
                        + ComboLocaleManager.get("sabado") + ", ";
            }
            if (form.getDomingo()) {
                diasLaborables = diasLaborables
                        + ComboLocaleManager.get("domingo") + ", ";
            }

            if (form.getComienzo() != null) {
                final String fecha = DateUtil.convertDateToString(form
                        .getComienzo());
                filtrosPdf.put(ComboLocaleManager.get("intHor.fechaComienzo"),
                        fecha);
            }

            if (form.getFin() != null) {
                final String fecha = DateUtil
                        .convertDateToString(form.getFin());
                filtrosPdf
                        .put(ComboLocaleManager.get("intHor.fechaFin"), fecha);
            }

            if (!diasLaborables.equals(" ")) {
                filtrosPdf.put(ComboLocaleManager.get("intHor.diasLaborables"),
                        diasLaborables
                                .substring(0, diasLaborables.length() - 2));
            }
        }

        return filtrosPdf;
    }

    /**
     * Método de apoyo para pintar el botón de eliminar.
     * 
     * @return Boolean, true si podemos eliminar, false en caso contrario.
     */
    public Boolean getCreacion() {
        return (String) getSessionManager().getAttribute("intervalo_key") != null;
    }

    /**
     * Elimina el HorarioInt que estamos editando de BBDD ó Session
     * 
     * @return String con el identificador de navigation rule (list)
     */
    public String eliminarIntervaloHorario() {
        HorarioForm horarioForm = null;

        try {
            /* Actualizamos el listado de intervalos horarios en el Horario */
            if (getSessionManager() != null) {

                key = (String) getSessionManager()
                        .getAttribute("intervalo_key");
            }

            /* OJO! no poner parametros para que no de exception */
            final List<IntervaloHorario> intervalos = (List<IntervaloHorario>) getSessionManagerParam("intervalos");

            final List listadoNuevo = horarioIntDelegate.eliminarIntervalo(
                    form, key, intervalos);

            /*
             * Eliminamos de session la posicion del HorarioInt que hemos
             * editando.
             */
            getSessionManager().removeAttribute("intervalo_key");

            // this.getSessionManager().setAttribute("eliminar", "eliminar");
            getSessionManager().setAttribute(Constants.LISTADO_HORARIOS_ACT,
                    listadoNuevo);

        } catch (final LibraeException e) {
            log.info("Error al eliminar un Intervalo Horario: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("No se puede eliminar dicho intervalo horario: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_GENERICO");
            setWindowMessages(wm);
            return "";
        }

        return "list";

    }
    
    /**
     * Elimina el HorarioInt que estamos editando de BBDD ó Session
     * 
     * @return String con el identificador de navigation rule (list)
     */
    public String eliminarHorario() {
        try {
            key = (String) getSessionManagerParam("intervalo_key");
            final List<HorarioInt> horarios = (List<HorarioInt>)  getSessionManagerParam(Constants.LISTADO_HORARIOS_ACT);
            horarios.remove(Integer.parseInt(key));
            setSessionManagerParam(Constants.LISTADO_HORARIOS_ACT, horarios);
        } catch (final LibraeException e) {
            log.info("Error al eliminar un Intervalo Horario: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("No se puede eliminar dicho intervalo horario: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_GENERICO");
            setWindowMessages(wm);
            return "";
        }

        return "list";

    }

    /**
     * Método que nos lleva a la pantalla de creación de un nuevo intervalo
     * horario. Comprueba que la creación de un nuevo intervalo no implique
     * excederse del número máximo, de lo contrario, lanza excepción. Guarda en
     * session el formulario actual de horarioInt.
     * 
     * @return String con el identificador de navigation rule (nuevoIntervalo)
     */
    public String nuevoIntervalo() {
        String page = "";

        try {
            horarioIntDelegate.validarIntervalo(form);

        } catch (final LibraeException e) {
            log.info("No se puede crear un nuevo intervalo: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return page;
        } catch (final Exception e) {
            log.error("No se puede crear un nuevo intervalo: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_GENERICO");
            setWindowMessages(wm);
            return page;
        }

        page = "nuevoIntervalo";
        getSessionManager().setAttribute(Constants.HORARIOINT_SESSION_NAME,
                form);

        return page;
    }

    /**
     * Método que vuelve de la pantalla de creación de un nuevo intervalo
     * horario.
     * 
     * @return String con el identificador de navigation rule
     *         (saveNuevoIntervalo)
     */
    public String saveNuevoIntervalo() {

        HorarioIntForm horarioIntForm = null;
        final SessionManager sesion = getSessionManager();
        try {
            /* Recuperamos el formulario de HorarioInt de session */
            if (sesion != null) {
                horarioIntForm = (HorarioIntForm) sesion
                        .getAttribute(Constants.HORARIOINT_SESSION_NAME);
                final List intervalos = (List) sesion
                        .getAttribute("intervalos");
                form.setIntervalos(intervalos);
                form = horarioIntDelegate.actualizarListIntervalos(
                        horarioIntForm, form);
                sesion.setAttribute("intervalos", form.getIntervalos());
                sesion.setAttribute(Constants.HORARIOINT_SESSION_NAME, form);
            }

        } catch (final LibraeException e) {
            log.info("No se puede crear un nuevo intervalo: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("No se puede crear un nuevo intervalo: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_GENERICO");
            setWindowMessages(wm);
            return "";
        }
        return "saveNuevoIntervalo";
    }

    /** Elimina un intervalo horario del listado */
    public String eliminarIntervalo() {
        HorarioIntForm horarioIntForm = null;
        final SessionManager sesion = getSessionManager();
        try {
            if (sesion != null) {
                horarioIntForm = (HorarioIntForm) sesion
                        .getAttribute(Constants.HORARIOINT_SESSION_NAME);
                horarioIntForm.getIntervalos().remove(Integer.valueOf(key).intValue());

                /* Actualizamos el formulario en session */
                sesion.setAttribute(Constants.HORARIOINT_SESSION_NAME, horarioIntForm);
                sesion.setAttribute("intervalos", horarioIntForm.getIntervalos());
            }
        } catch (final LibraeException e) {
            log.info("No se puede eliminar el intervalo: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("No se puede eliminar el intervalo: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ELIMINAR_INTERVALO");
            setWindowMessages(wm);
        }
        return "refresh";
    }

    /**
     * Cancela el crear un nuevo intervalo horario.
     * 
     * @return String con el identificador de navigation rule
     *         (cancelNuevoIntervalo)
     */
    public String cancelNuevoIntervalo() {

        return "cancelNuevoIntervalo";
    }

    /**
     * Actualiza los días laborables según el calendario perteneciente a la
     * Biblioteca. (caso de edición)
     * 
     * @return String con el identificador de navigation rule
     *         (actLabSegunCalendario)
     */
    public String actLabSegunCalendario() {
        String diasSemana = null;

        final CalendarioForm calendarioForm = (CalendarioForm) getSessionManagerParam(Constants.CALENDARIO_SESSION_NAME);

        if (calendarioForm != null) {
            final Calendario calendario = calendarioForm.toEntity();
            diasSemana = calendario.getDiasSemana();
        } else {
            final Biblioteca biblioteca = (Biblioteca) getSessionManagerParam(Constants.BIBLIOTECA_HORARIO_SESSION_NAME);

            if (biblioteca != null) {
                diasSemana = horarioIntDelegate
                        .actLabSegunCalendario(biblioteca.getId());
            }
        }

        if (diasSemana != null) {
            form.actualizaDiasSemana(diasSemana);
        }

        /* Actualizamos el formulario en session */
        getSessionManager().setAttribute(Constants.HORARIOINT_SESSION_NAME,
                form);

        return "actLabSegunCalendario";
    }

    /**
     * Cancela el crear/editar un HorarioInt.
     * 
     * @return String con el identificador de navigation rule (cancel)
     */
    public String cancel() {
        /**
         * Eliminamos de session la posicion del HorarioInt que hemos editando
         */
        removeSessionManagerParam("intervalo_key");
        removeSessionManagerParam("intervalos");

        return "list";
    }

    /**
     * Este método sirve para solucionar los errores de
     * LazyInitializationException.
     * 
     * @param lazyEntities
     * @return List<HorarioInt>, nonLazyEntities
     */
    private List<HorarioInt> invokateLazyHibernateEntity(final List lazyEntities) {
        List nonLazyEntities = new ArrayList<HorarioInt>();

        if (lazyEntities != null) {
            nonLazyEntities = lazyEntities;
        }

        /**
         * no borrar esta vble. sirve para forzar la invocación de valores que
         * vienen a lazy.<br>
         * Con esta invocación se soluciona el error:
         * 
         * <code>failed to lazily initialize a collection of role: org.librae.adminconfig.model.Horario.horariosInt, no session or session was closed</code>
         */
        final int invocacionLazyHibernateEntity = nonLazyEntities.size();

        return nonLazyEntities;
    }

    /**
     * ================ getter & setters ============== <br>
     */

    public Integer getPos() {
        pos = pos + 1;
        return pos;
    }

    public String getKey() {
        return ((key == null) ? (String) getSessionManager().getAttribute(
                "intervalo_key") : key);
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public Long getIdHorarioInt() {
        return idHorarioInt;
    }

    public void setIdHorarioInt(final Long idHorarioInt) {
        this.idHorarioInt = idHorarioInt;
    }

    public HorarioIntForm getForm() {
        return form;
    }

    public IHorarioIntDelegate getHorarioIntDelegate() {
        return horarioIntDelegate;
    }

    public void setHorarioIntDelegate(
            final IHorarioIntDelegate horarioIntDelegate) {
        this.horarioIntDelegate = horarioIntDelegate;
    }

    public void setForm(final HorarioIntForm form) {
        this.form = form;
    }

    public void setPos(final Integer pos) {
        this.pos = pos;
    }

    public String getKeyInt() {
        return ((keyInt == null) ? (String) getSessionManager().getAttribute(
                "intervalo_key_int") : keyInt);
    }

    public void setKeyInt(String keyInt) {
        this.keyInt = keyInt;
    }

    public Integer getPosInt() {
        posInt = posInt + 1;
        return posInt;
    }
}