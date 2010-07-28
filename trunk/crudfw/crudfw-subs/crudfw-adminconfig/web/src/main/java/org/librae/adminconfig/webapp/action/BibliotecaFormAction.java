package org.librae.adminconfig.webapp.action;

import java.io.IOException;
import java.io.Serializable;
import java.sql.BatchUpdateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.bean.IntervaloHorario;
import org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate;
import org.librae.adminconfig.webapp.form.BibliotecaForm;
import org.librae.adminconfig.webapp.form.CalendarioForm;
import org.librae.adminconfig.webapp.form.DireccionForm;
import org.librae.adminconfig.webapp.form.GestionCodigoCriteriaForm;
import org.librae.adminconfig.webapp.form.HorarioForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.action.ConvertUtil;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.tabs.AbstractTab;
import org.librae.common.webapp.util.WindowMessages;
import org.springframework.dao.TransientDataAccessResourceException;

/**
 * Action-JSF asociado a los formularios de la entidad Biblioteca
 * 
 * @author aropero
 */
public class BibliotecaFormAction extends BasePage implements Serializable {
    /**
     * Serial Version UID
     */
    private static final long   serialVersionUID = 1L;

    private IBibliotecaDelegate bibliotecaDelegate;
    private BibliotecaForm      form             = new BibliotecaForm();
    private Boolean             defaultForm      = Boolean.TRUE;
    private List<SelectItem>    tiposBiblioteca;
    private Long                idBiblioteca;
    private Boolean             isPrincipal      = Boolean.FALSE;
    private Long                nodoClicked      = null;

    /**
     * Método invocado cuando se entra en la pantalla de Biblioteca. Se
     * encargará de obtener de session el posible objeto BibliotecaForm para
     * inicializar los datos
     * 
     * @return cadena vacia
     */
    public String getInit() {
        Long idTipoBiblioteca = null;
        final SessionManager sesion = getSessionManager();
        final GestionCodigoCriteriaForm formularioCodigos = new GestionCodigoCriteriaForm();
        if (sesion != null) {
            final BibliotecaForm bibliotecaForm = (BibliotecaForm) sesion
                    .getAttribute(Constants.BIBLIOTECA_SESSION_NAME);
            if (bibliotecaForm == null) {
                idTipoBiblioteca = form.getIdTipo();
            } else {
                form = bibliotecaForm;
                final CalendarioForm calendarioForm = (CalendarioForm) sesion
                        .getAttribute(Constants.CALENDARIO_SESSION_NAME);
                final HorarioForm horarioForm = (HorarioForm) sesion
                        .getAttribute(Constants.HORARIO_SESSION_NAME);
                final DireccionForm direccionForm = (DireccionForm) sesion
                        .getAttribute(Constants.DIRECCION_SESSION_NAME);
                /*
                 * Actualiza el calendario en el formulario de biblioteca
                 */
                if (calendarioForm != null) {
                    form.setFechaActualizacion(calendarioForm
                            .getUltimaActualizacion());
                }
                /*
                 * Actualiza la direccion en el formulario de biblioteca
                 */
                if (direccionForm != null) {
                    form.setDomicilio(direccionForm.getDireccion());
                }
                idTipoBiblioteca = form.getIdTipo();
            }
            if (idTipoBiblioteca == null) {
                final TipoBiblioteca tipoBiblioteca = bibliotecaDelegate
                        .getTipoBibliotecaByCodigo(Constants.TIPO_BIBLIOTECA_GRUPO);
                idTipoBiblioteca = tipoBiblioteca.getId();
            }
            if ((form != null) && (form.getCodigo() != null)) {
                sesion.setAttribute(Constants.CONTEXTO_CODIGO_BIBLIOTECA, form
                        .getCodigo());
            }
            formularioCodigos.setIdTipo(idTipoBiblioteca);
            buscarCodigos(formularioCodigos, "codigosBibliotecas");
        }
        return "";
    }

    /**
     * Método encargado de obtener el listado de entidades según unos criterios
     * de busqueda establecidos por el usuario.
     * 
     * @return pagina de vuelta rule "search"
     */
    public String buscarCodigos(ISearchForm formulario, String idListadoCodigos) {
        String page = "search";
        try {
            Map<String, Object> filtros = new HashMap<String, Object>();
            if (formulario != null) {
                filtros = formulario.toMap();
                filtros.put(Constants.SORTCOLUMN, getSortColumn());
                filtros.put(Constants.ASCENDING, isAscending());
                filtros.put(Constants.PAGESIZE, -1);
                filtros.put(Constants.CURRENTPAGE, 1);
            }
            setSessionManagerParam(idListadoCodigos, filtros);
        } catch (final Exception e) {
            log.error("Error al buscar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_BUSCAR");
            setWindowMessages(wm);
            page = "search";
        }
        return page;
    }

    public String getInitSoloLectura() {
        edit();
        return null;
    }

    /**
     * Método invocado cuando se entra en el formulario de edición de una
     * biblioteca. Se encargará de preparar los datos para su correcta
     * visualización invocando a su método delegado prepararDatosVista
     * 
     * @return String con el identificador de navigation rule (edit)
     */
    public String edit() {
        String page = "edit";
        try {
            form.setIdBiblioteca(idBiblioteca);
            CalendarioForm calendarioForm = new CalendarioForm();
            final String valores = bibliotecaDelegate.prepararDatosVista(form, calendarioForm);
            setSessionManagerParam(Constants.CALENDARIO_SESSION_NAME, calendarioForm);
            form.setIdValoresSeleccionados(valores);
            setReadMode();
            form.setCreacion(false);
            final Biblioteca biblioteca = bibliotecaDelegate
                    .obtenerBiblioteca(idBiblioteca);
            if (biblioteca.getPadre() != null) {
                setSessionManagerParam("nodo_seleccionado", biblioteca
                        .getPadre().getId());
            }
            if (biblioteca.getPadre() == null) {
                setIsPrincipal(Boolean.TRUE);
            }
            setSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME, form);
        } catch (final LibraeException e) {
            log.info("No se puede entrar a editar una biblioteca...", e);
            page = "list";
        } catch (final Exception e) {
            log.error("No se puede entrar a editar una biblioteca...", e);
            page = "list";
        }

        /** Eliminamos el horario que pudiese quedar en session. */
        removeSessionManagerParam(Constants.HORARIO_SESSION_NAME);
        removeSessionManagerParam(Constants.HORARIOINT_SESSION_NAME);

        return page;
    }

    /**
     * Método invocado al pulsar en el botón guardar de la pestaña Biblioteca o
     * de la pestaña Política de Préstamos. Guardará en BD los datos de esa
     * pestaña invocando a su método delegate. Vuelve al listado.
     * 
     * @return String con el identificador de navigation rule (save)
     */
    public String saveAceptar() {
        final String page = "save";
        try {
            final SessionManager sesion = getSessionManager();
            if (sesion != null) {
            	final List<HorarioInt> horarios = (List<HorarioInt>) sesion.getAttribute(Constants.LISTADO_HORARIOS_ACT);
                final Long nodo = (Long) sesion
                        .getAttribute("nodo_seleccionado");
                // obtenemos el limite de tamaño de upload
                long sizeMax = 1048576;
                final String s = getServletContext().getInitParameter(
                        "uploadMaxFileSize");
                if (s != null) {
                    sizeMax = Long.parseLong(s);
                }

                /* Biblioteca padre (Árbol de bibliotecas) */
                final Biblioteca padre = bibliotecaDelegate.validarForm(form,
                        nodo, sizeMax);

                /* Calendario de la Biblioteca */
                CalendarioForm calendarioForm = (CalendarioForm) sesion
                        .getAttribute(Constants.CALENDARIO_SESSION_NAME);

                /* Horario de la Biblioteca */
                HorarioForm horarioForm = (HorarioForm) sesion
                        .getAttribute(Constants.HORARIO_SESSION_NAME);

                /* Direccion de la Biblioteca */
                DireccionForm direccionForm = (DireccionForm) sesion
                        .getAttribute(Constants.DIRECCION_SESSION_NAME);

                if (form.getFechaActualizacionH() == null) {
                    horarioForm = null;
                    form.setFechaActualizacionH(null);
                }

                if (form.getFechaActualizacion() == null) {
                    calendarioForm = null;
                    form.setFechaActualizacion(null);
                }

                if ((form.getDomicilio() == null)
                        || (form.getDomicilio().equals(""))) {
                    direccionForm = null;
                    form.setDomicilio(null);
                }

                bibliotecaDelegate.guardarBiblioteca(form, padre,
                        calendarioForm, horarioForm, direccionForm, horarios);
                if (form.getCodigo() != null) {
                    sesion.setAttribute(Constants.CONTEXTO_CODIGO_BIBLIOTECA,
                            form.getCodigo());
                }
            }
            /**
             * Borramos el horario de session.
             */
            sesion.removeAttribute(Constants.HORARIO_SESSION_NAME);
            sesion.removeAttribute(Constants.HORARIOINT_SESSION_NAME);

            /** Borramos la direccion de session */
            sesion.removeAttribute(Constants.DIRECCION_SESSION_NAME);
        } catch (final LibraeException e) {
            log.info("No se puede guardar la biblioteca: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("Error al guardar una biblioteca: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GUARDAR_BIBLIOTECA",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }
        setReadMode();
        return page;
    }

    /**
     * Método invocado al pulsar en el botón guardar de la pestaña Biblioteca o
     * de la pestaña Política de Préstamos. Guardará en BD los datos de esa
     * pestaña invocando a su método delegate. Se queda en la misma página.
     * 
     * @return String con el identificador de navigation rule (guardar)
     */
    public String saveGuardar() {
        final String page = "guardar";
        Biblioteca biblioteca = null;
        final SessionManager sesion = getSessionManager();
        if (sesion != null) {
            try {
            	final List<HorarioInt> horarios = (List<HorarioInt>) sesion.getAttribute("horariosInt");
                final Long nodo = (Long) sesion
                        .getAttribute("nodo_seleccionado");

                form = saveUserActiveTab(form);

                // obtenemos el limite de tamaño de upload
                long sizeMax = 1048576;
                final String s = getServletContext().getInitParameter(
                        "uploadMaxFileSize");
                if (s != null) {
                    sizeMax = Long.parseLong(s);
                }

                /* Biblioteca padre (Árbol de bibliotecas) */
                final Biblioteca padre = bibliotecaDelegate.validarForm(form,
                        nodo, sizeMax);

                /* Calendario de la Biblioteca */
                CalendarioForm calendarioForm = (CalendarioForm) sesion
                        .getAttribute(Constants.CALENDARIO_SESSION_NAME);

                /* Horario de la Biblioteca */
                HorarioForm horarioForm = (HorarioForm) sesion
                        .getAttribute(Constants.HORARIO_SESSION_NAME);

                /* Direccion de la Biblioteca */
                DireccionForm direccionForm = (DireccionForm) sesion
                        .getAttribute(Constants.DIRECCION_SESSION_NAME);

                if (form.getFechaActualizacionH() == null) {
                    horarioForm = null;
                    form.setFechaActualizacionH(null);
                }

                if (form.getFechaActualizacion() == null) {
                    calendarioForm = null;
                    form.setFechaActualizacion(null);
                }

                if ((form.getDomicilio() == null)
                        || (form.getDomicilio().equals(""))) {
                    direccionForm = null;
                    form.setDomicilio(null);
                }

                biblioteca = bibliotecaDelegate.guardarBiblioteca(form, padre,
                        calendarioForm, horarioForm, direccionForm, horarios);
                setMensajeExito("MENSAJE_EXITO_BIBLIOTECA",
                        MensajesError.PROPERTI_ADMINCONFIG);
            } catch (final TransientDataAccessResourceException e) {
                log.error("No se puede guardar la Peticion: ", e);
                WindowMessages wm = null;

                final Throwable cause = e.getCause();
                if (cause instanceof BatchUpdateException) {
                    final String message = cause.getMessage();
                    wm = new WindowMessages(ExceptionUtil
                            .crearLibraeException("ERROR_CONFIGURACION"));
                    wm.addErrorMessage(message);
                    form.setUploadedFileLogotipo(null);
                } else {
                    wm = new WindowMessages(ExceptionUtil.crearLibraeException(
                            "ERROR_GUARDAR_BIBLIOTECA",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
                }

                setWindowMessages(wm);
            } catch (final LibraeException e) {
                log.info("No se puede guardar la biblioteca: ", e);
                final WindowMessages wm = new WindowMessages(e);
                setWindowMessages(wm);
                return "";
            } catch (final Exception e) {
                log.error("Error al guardar una biblioteca: ", e);
                final WindowMessages wm = new WindowMessages(ExceptionUtil
                        .crearLibraeException("ERROR_GUARDAR_BIBLIOTECA",
                                MensajesError.PROPERTI_ADMINCONFIG, e));
                setWindowMessages(wm);
                return "";
            }
            setReadMode();
            form.setCreacion(false);
            form.setIdBiblioteca(biblioteca.getId());

            /* Metemos el formulario de biblioteca en session para la vuelta */
            sesion.setAttribute(Constants.BIBLIOTECA_SESSION_NAME, form);

            /**
             * Borramos el horario de session.
             */
            sesion.removeAttribute(Constants.HORARIO_SESSION_NAME);
            sesion.removeAttribute(Constants.HORARIOINT_SESSION_NAME);
            sesion.removeAttribute(Constants.LISTADO_HORARIOS_ACT);
        }
        return page;
    }

    /**
     * Método invocado al pulsar en el botón eliminar de la pestaña Biblioteca.
     * 
     * @return String con el identificador de navigation rule (delete)
     */
    public String delete() {
        Usuario usuario = null;
        try {
            if (getSessionManager() != null) {
                usuario = (Usuario) getSessionManagerParam(Constants.USUARIO_SESSION_PARAM);

            }

            bibliotecaDelegate.eliminar(form.getIdBiblioteca(), usuario);

        } catch (final LibraeException e) {
            log.info("No se puede eliminar la biblioteca: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("Error al eliminar una biblioteca: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_ELIMINAR_BIBLIOTECA",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }
        setReadMode();

        /**
         * Borramos el horario de session.
         */
        removeSessionManagerParam(Constants.HORARIO_SESSION_NAME);

        /** Borramos la direccion de session */
        removeSessionManagerParam(Constants.DIRECCION_SESSION_NAME);

        return "delete";
    }

    /**
     * Cancela el crear/editar una Biblioteca.
     * 
     * @return String con el identificador de navigation rule (cancel)
     */
    public String cancel() {
        setReadMode();
        final SessionManager sesion = getSessionManager();
        if (sesion != null) {
            /**
             * Borramos la biblioteca de session.
             */
            sesion.removeAttribute(Constants.BIBLIOTECA_SESSION_NAME);

            /**
             * Borramos el horario de session.
             */
            sesion.removeAttribute(Constants.HORARIO_SESSION_NAME);
            sesion.removeAttribute(Constants.HORARIOINT_SESSION_NAME);
            sesion.removeAttribute(Constants.LISTADO_HORARIOS_ACT);

            /** Borramos la direccion de session */
            sesion.removeAttribute(Constants.DIRECCION_SESSION_NAME);
        }
        return "cancel";
    }

    /**
     * Cancela el crear/editar una Biblioteca.
     * 
     * @return String con el identificador de navigation rule (cancel)
     * @throws IOException
     */
    public String cancelSoloLectura() throws IOException {
        try {
            final SessionManager sesion = getSessionManager();
            if (sesion != null) {
                setReadMode();
                sesion.removeAttribute(Constants.BIBLIOTECA_SESSION_NAME);
                sesion.removeAttribute(Constants.HORARIO_SESSION_NAME);
                sesion.removeAttribute(Constants.HORARIOINT_SESSION_NAME);
                sesion.removeAttribute(Constants.LISTADO_HORARIOS_ACT);
                sesion.removeAttribute(Constants.DIRECCION_SESSION_NAME);
                final StringBuilder urlBack = new StringBuilder();
                urlBack.append(getConfSubsistema());
                final String urlBackSession = (String) sesion
                        .getAttribute(Constants.URL_BACK_MOSTRAR_BIBLIOTECA);
                urlBack.append(urlBackSession);
                getFacesContext().getCurrentInstance().getExternalContext()
                        .redirect(urlBack.toString());
            }
        } catch (final Exception e) {
            log.error(
                    "Se ha producido un error al volver al otro subsistema...",
                    e);
        }
        return null;
    }

    /**
     * Método encargado de rellenar el combo de tipos de biblioteca.
     * 
     * @return listado de tipos de bibliotecas.
     */
    public List<SelectItem> getTiposBiblioteca() {
        if (tiposBiblioteca == null) {
            tiposBiblioteca = ConvertUtil.convertListItem(bibliotecaDelegate
                    .obtenerTiposBiblioteca(), "id", "nombre");
        }
        return tiposBiblioteca;
    }

    /**
     * Método encargado de redirigirnos a la edición del calendario.
     * 
     * @return String con el identificador de navigation rule (calendario)
     */
    public String editCalendario() {
        CalendarioForm formCalendario = (CalendarioForm) getSessionManagerParam(Constants.CALENDARIO_SESSION_NAME);

        if ((formCalendario == null) && (idBiblioteca != null)
                && (!(new Long(0)).equals(idBiblioteca))) {
            formCalendario = new CalendarioForm();
            final Biblioteca biblioteca = bibliotecaDelegate
                    .obtenerBiblioteca(idBiblioteca);
            formCalendario.fromEntity(biblioteca.getCalendario());
        } else if (formCalendario == null) {
            formCalendario = new CalendarioForm();
        }
        /* Establecemos la url de la vuelta */
        formCalendario.setNavigationRuleBack("biblioteca");

        if (form.getReadMode().equals(true)) {
            formCalendario.setReadMode(true);
        } else {
            formCalendario.setReadMode(false);
        }

        /* Metemos el formulario de biblioteca en session para la vuelta */
        setSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME, form);

        /* Metemos el formulario de calendario en session */
        setSessionManagerParam(Constants.CALENDARIO_SESSION_NAME,
                formCalendario);

        return "calendario";
    }

    /**
     * Método encargado de redirigirnos a la edición de un horario.
     * 
     * @return String con el identificador de navigation rule (horario)
     */
    public String editHorario() {
        Biblioteca biblioteca = null;
        HorarioForm formHorario = (HorarioForm) getSessionManagerParam(Constants.HORARIO_SESSION_NAME);

        if (formHorario == null) {
            formHorario = new HorarioForm();
        }

        /* Establecemos la url de la vuelta */
        formHorario.setNavigationRuleBack("biblioteca");

        if (form.getReadMode().equals(true)) {
            formHorario.setReadMode(true);
        } else {
            formHorario.setReadMode(false);
        }

        /* Metemos el formulario de biblioteca en session para la vuelta */
        setSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME, form);

        /* Metemos la biblioteca en session para la busqueda de los horarios */
        if (form.getIdBiblioteca() != null) {
            biblioteca = bibliotecaDelegate.obtenerBiblioteca(form
                    .getIdBiblioteca());
        }
        setSessionManagerParam(Constants.BIBLIOTECA_HORARIO_SESSION_NAME,
                biblioteca);

        /* Metemos el formulario de horario en session */
        setSessionManagerParam(Constants.HORARIO_SESSION_NAME, formHorario);

        return "horario";
    }

    /**
     * Método encargado de redirigirnos a la edición de la direccion.
     * 
     * @return String con el identificador de navigation rule (direccion)
     */
    public String editDireccion() {

        DireccionForm formDireccion = (DireccionForm) getSessionManagerParam(Constants.DIRECCION_SESSION_NAME);

        if (formDireccion == null) {
            formDireccion = new DireccionForm();
            if (form.getIdBiblioteca() != null) {
                /** Obtenemos la direccion a partir de la biblioteca */
                final Biblioteca biblioteca = bibliotecaDelegate
                        .obtenerBiblioteca(form.getIdBiblioteca());
                formDireccion.fromEntity(biblioteca.getDireccion());
            }
        }

        /** Establecemos la url de la vuelta */
        formDireccion.setNavigationRuleBack("biblioteca");

        if (form.getReadMode().equals(true)) {
            formDireccion.setReadMode(true);
        } else {
            formDireccion.setReadMode(false);
        }

        /** Metemos el formulario de biblioteca en session para la vuelta */
        setSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME, form);

        /** Metemos el formulario de direccion en session */
        setSessionManagerParam(Constants.DIRECCION_SESSION_NAME, formDireccion);

        return "direccion";
    }

    /**
     * Método que devuelve el formulario en forma editable. Responde al clic en
     * el boton Editar en la pantalla de Detalle de Biblioteca.
     * 
     * @return cadena vacia
     */
    public String editable() {
        final String page = "editable";
        form = (BibliotecaForm) getSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME);
        setEditMode();
        form = saveUserActiveTab(form);
        /* Metemos el formulario de biblioteca en session para la vuelta */
        setSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME, form);
        defaultForm = Boolean.TRUE;
        return page;
    }

    /**
     * Método para pintar el enlace al logotipo.
     * 
     * @return true si existe logotipo, false en caso contrario.
     */
    public Boolean getExisteLogotipo() {
        Biblioteca b = null;

        if (form.getIdBiblioteca() != null) {
            b = bibliotecaDelegate.obtenerBiblioteca(form.getIdBiblioteca());
        }

        return b != null && b.getLogotipo() != null
                && b.getLogotipoNombreFichero() != null;
    }

    /**
     * Guarda en session el nodo seleccionado.
     */
    public void nodoClick() {
        setSessionManagerParam("nodo_seleccionado", getNodoClicked());
    }

    /**
     * Método que activa el modo de lectura del formulario.
     */
    private void setReadMode() {
        form.setReadMode(true);
    }

    /**
     * Método activa el modo de edición del formulario.
     */
    private void setEditMode() {
        form.setReadMode(false);
    }

    /**
     * Selecciona el id del nodo del arbol.
     * 
     * @return
     */
    public Long getNodoClicked() {
        if (nodoClicked == null) {
            nodoClicked = (Long) getSessionManagerParam("nodo_seleccionado");
        }
        return nodoClicked;
    }

    /**
     * Establecemos como pestaña activa la última que fué pulsada por el usuario
     * 
     * @param form
     * @return
     */
    private BibliotecaForm saveUserActiveTab(final BibliotecaForm form) {
        final BibliotecaForm result = form;
        AbstractTab activeSubTabByUser = null;
        if (form != null && form.getTabGroup() != null) {
            activeSubTabByUser = form.getTabGroup().getActiveTabByUser();
            if (activeSubTabByUser != null) {
                if (form.getTabBiblioteca() != null
                        && activeSubTabByUser.equals(form.getTabBiblioteca())) {
                    result.getTabBiblioteca().activate();
                }
                if (form.getTabPolPrest() != null
                        && activeSubTabByUser.equals(form.getTabPolPrest())) {
                    result.getTabPolPrest().activate();
                }
                if (form.getTabCodigos() != null
                        && activeSubTabByUser.equals(form.getTabCodigos())) {
                    result.getTabCodigos().activate();
                }
                if (form.getTabParametros() != null
                        && activeSubTabByUser.equals(form.getTabParametros())) {
                    result.getTabParametros().activate();
                }
            }
        }
        return result;
    }

    /**
     * Método invocado cuando se entra en la pantalla de Biblioteca. Se
     * encargará de obtener de session el posible objeto BibliotecaForm para
     * inicializar los datos
     * 
     * @return cadena vacia
     */
    public String getLimpiarForm() {
        final SessionManager sesion = getSessionManager();
        if (sesion != null) {
            sesion.removeAttribute(Constants.BIBLIOTECA_SESSION_NAME);
            sesion.removeAttribute(Constants.DIRECCION_SESSION_NAME);
        }
        return "";
    }

    // ====== getters & setters ======
    public void setBibliotecaDelegate(
            final IBibliotecaDelegate bibliotecaDelegate) {
        this.bibliotecaDelegate = bibliotecaDelegate;
    }

    public IBibliotecaDelegate getBibliotecaDelegate() {
        return bibliotecaDelegate;
    }

    public BibliotecaForm getForm() {
        if (defaultForm) {
            final SessionManager sesion = getSessionManager();
            if (sesion != null) {
                final BibliotecaForm bibliotecaForm = (BibliotecaForm) sesion
                        .getAttribute(Constants.BIBLIOTECA_SESSION_NAME);
                if (bibliotecaForm != null) {
                    form = bibliotecaForm;
                }
            }
            defaultForm = Boolean.FALSE;
        }
        return form;
    }

    public void setForm(final BibliotecaForm form) {
        this.form = form;
    }

    public void setTiposBiblioteca(final List<SelectItem> tiposBiblioteca) {
        this.tiposBiblioteca = tiposBiblioteca;
    }

    public Long getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(final Long idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public void setNodoClicked(final Long nodoClicked) {
        this.nodoClicked = nodoClicked;
        setSessionManagerParam("nodo_seleccionado", nodoClicked);
    }

    public Boolean getIsPrincipal() {
        final Boolean principal = (Boolean) getSessionManagerParam("isPrincipal");
        return (principal == null) ? Boolean.FALSE : principal;
    }

    public void setIsPrincipal(Boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
        if (isPrincipal.equals(Boolean.TRUE)) {
            setSessionManagerParam("isPrincipal", Boolean.TRUE);
        }
    }

}