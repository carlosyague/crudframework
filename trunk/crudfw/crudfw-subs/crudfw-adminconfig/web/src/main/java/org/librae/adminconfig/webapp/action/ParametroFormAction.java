package org.librae.adminconfig.webapp.action;

import java.io.Serializable;

import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.util.WindowMessages;
import org.librae.adminconfig.model.Parametro;
import org.librae.adminconfig.webapp.delegate.IParametroDelegate;
import org.librae.adminconfig.webapp.form.ParametroForm;

/**
 * Action-JSF asociado a los formularios de la entidad Parametro.
 */
public class ParametroFormAction extends BasePage implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long  serialVersionUID = 9133055136576269076L;

    private Long               idParametro;

    private String             idFormulario;

    private IParametroDelegate delegate;

    private ParametroForm      form             = new ParametroForm();

    /**
     * Método invocado cuando se entra en la pantalla de parametros. Se
     * encargará de obtener de session el posible objeto ParametroForm para
     * inicializar los datos
     * 
     * @return cadena vacia
     */
    public String getInit() {
        final ParametroForm formularioSession = (ParametroForm) getSessionManagerParam(getIdFormulario());
        if ((formularioSession != null)
                && (formularioSession.getIdParametro() != null)) {
            final Parametro transformJob = getDelegate().getParametro(
                    formularioSession.getIdParametro());
            formularioSession.fromEntity(transformJob);
            form = formularioSession;
            setSessionManagerParam(getIdFormulario(), form);

            if ((form != null) && (form.getCodigo() != null)) {
                setSessionManagerParam(Constants.CONTEXTO_CODIGO_PARAMETRO,
                        form.getCodigo());
            }
        }
        return null;
    }

    /**
     * Limpia el formulario.
     * 
     * @return
     */
    public String getLimpiarForm() {
        removeSessionManagerParam(getIdFormulario());
        return null;
    }

    /**
     * Método invocado cuando se entra en el formulario de edición de una
     * Parametro. Se encargará de preparar los datos para su correcta
     * visualización invocando a su método delegado prepararDatosVista.
     * 
     * @return String con el identificador de navigation rule (edit).
     */
    public String edit() {
        String page = "form";
        try {
            form.setIdParametro(idParametro);
            form.setReadMode(true);
            form.setCreacion(false);
            setSessionManagerParam(getIdFormulario(), form);
        } catch (final LibraeException e) {
            log.info("No se puede editar la parametro: ", e);
            page = "list";
        } catch (final Exception e) {
            log.error("No se puede editar la parametro: ", e);
            page = "list";
        }
        return page;
    }

    /**
     * Método invocado al pulsar en el botón guardar. Guardará en BD los datos
     * de esa pestaña invocando a su método delegate. Vuelve al listado.
     * 
     * @return String con el identificador de navigation rule (save)
     */
    public String saveAceptar() {
        String page = saveGuardar();
        if (!"error".equals(page)) {
            page = "list";
        }
        return page;
    }

    /**
     * Método invocado al pulsar en el botón guardar. Guardará en BD los datos
     * de esa pestaña invocando a su método delegate. Se queda en la misma
     * página.
     * 
     * @return String con el identificador de navigation rule (guardar)
     */
    public String saveGuardar() {
        String page = "";
        try {
            final Parametro parametro = getDelegate().guardarParametro(form);
            form.setReadMode(true);
            form.setCreacion(false);
            form.setIdParametro(parametro.getId());
            form.fromEntity(parametro);
            setSessionManagerParam(getIdFormulario(), form);
            setMensajeExito("MENSAJE_EXITO_PARAMETRO",
                    MensajesError.PROPERTI_ADMINCONFIG);
            if (form.getCodigo() != null) {
                setSessionManagerParam(Constants.CONTEXTO_CODIGO_PARAMETRO,
                        form.getCodigo());
            }
        } catch (final LibraeException e) {
            log.info("No se puede eliminar la parametro: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            page = "error";
        } catch (final Exception e) {
            log.error("Error al eliminar la parametro: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GUARDAR_PARAMETRO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            page = "error";
        }
        return page;
    }

    /**
     * Método que devuelve el formulario en forma editable. Responde al clic en
     * el boton Editar en la pantalla de Detalle de Transform o Job.
     * 
     * @return cadena vacia
     */
    public void editable() {
        final ParametroForm transformJobForm = (ParametroForm) getSessionManagerParam(getIdFormulario());
        transformJobForm.setReadMode(false);
        setSessionManagerParam(getIdFormulario(), transformJobForm);
        setForm(transformJobForm);
    }

    /**
     * Método invocado al pulsar en el botón eliminar.
     * 
     * @return String con el identificador de navigation rule (delete)
     */
    public String delete() {
        String page = "list";
        try {
            final ParametroForm transformJobForm = (ParametroForm) getSessionManagerParam(getIdFormulario());
            getDelegate().eliminar(transformJobForm.getIdParametro());
        } catch (final LibraeException e) {
            log.info("No se puede eliminar la parametro: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            page = "";
        } catch (final Exception e) {
            log.error("Error al eliminar la parametro: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_ELIMINAR_PARAMETRO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            page = "";
        }
        return page;
    }

    /**
     * Método para pintar el enlace al fichero.
     * 
     * @return true si existe fichero, false en caso contrario.
     */
    public Boolean getExisteFichero() {
        Parametro parametro = null;
        if (form.getIdParametro() != null) {
            parametro = getDelegate().getParametro(form.getIdParametro());
        }
        return parametro != null && parametro.getValorArchivo() != null
                && parametro.getNombreArchivo() != null;
    }

    // Getter & setters

    public String getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(String idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    /**
     * @return the delegate
     */
    public IParametroDelegate getDelegate() {
        return delegate;
    }

    /**
     * @param delegate
     *            the delegate to set
     */
    public void setDelegate(IParametroDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * @return the form
     */
    public ParametroForm getForm() {
        return form;
    }

    /**
     * @param form
     *            the form to set
     */
    public void setForm(ParametroForm form) {
        this.form = form;
    }

}