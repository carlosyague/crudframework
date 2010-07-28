package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.List;

import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.webapp.delegate.IUsuarioDelegate;
import org.librae.adminconfig.webapp.form.UsuarioForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.util.Propiedades;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.ValidationsUtil;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los formularios de la entidad Usuario
 *
 * @author jcisneros
 * @author aropero
 */
public class UsuarioFormAction extends BasePage implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1609850678909357049L;
    private IUsuarioDelegate  delegate;
    private UsuarioForm       usuarioForm      = new UsuarioForm();
    private Long              idUsuario        = null;
    private Boolean           orbeActivo       = Boolean.FALSE;

    /**
     * Método invocado cuando se entra en la pantalla de creación/edición de un
     * Usuario. Se encargará de obtener de session el posible objeto UsuarioForm
     * para inicializar los datos
     *
     * @return cadena vacia
     */
    public String getInit() {
        final UsuarioForm form = (UsuarioForm) getSessionManagerParam(Constants.USUARIO_SESSION_NAME);
        if (form != null) {
            usuarioForm = form;
        }
        if ((usuarioForm != null) && (usuarioForm.getUsuario() != null)) {
            setSessionManagerParam(Constants.CONTEXTO_CODIGO_USUARIO,
                    usuarioForm.getUsuario());
        }

        final Propiedades properties = Propiedades.getInstance();

        final String orbeActivoStr = properties
                .getPropiedad(Constants.ORBE_ACTIVO);

        if (orbeActivoStr != null && orbeActivoStr.equals("true")) {
            orbeActivo = Boolean.TRUE;
        }

        return "";
    }

    /**
     * Método invocado cuando se entra en el formulario de edición de un
     * Usuario. Se encargará de preparar los datos para su correcta
     * visualización invocando a su método delegado prepararDatosVista
     *
     * @return String con el identificador de navigation rule (edit)
     */

    public String edit() {
        String pagina = "edit";
        try {
            usuarioForm.setIdUsuario(idUsuario);
            delegate.prepararDatosVista(usuarioForm);
            usuarioForm.setReadMode(true);
            usuarioForm.setCreacion(false);
            SessionManager session = getSessionManager();
            if (session!=null) {
            	session.setAttribute("rolesAsignados", delegate
                    .buscarRoles(idUsuario));
            	session.setAttribute(Constants.USUARIO_SESSION_NAME, usuarioForm);
            }
        } catch (final LibraeException e) {
            log.info("No se puede editar el usuario: ", e);
            pagina = "list";
        } catch (final Exception e) {
            log.error("No se puede editar el usuario: ", e);
            pagina = "list";
        }
        return pagina;
    }

    /**
     * Método invocado al pulsar en el botón guardar de la pestaña Política de
     * Préstamos. Guardará en BD los datos de esa pestaña invocando a su método
     * delegate. Vuelve al listado.
     *
     * @return String con el identificador de navigation rule (save)
     */
    public String saveAceptar() {
        try {
            final Propiedades properties = Propiedades.getInstance();
            final String orbeActivoStr = properties
                    .getPropiedad(Constants.ORBE_ACTIVO);

            if (orbeActivoStr != null && orbeActivoStr.equals("true")) {
                orbeActivo = Boolean.TRUE;
            }

            validarTipoIdentificacion();
            final List<UsuarioBibliotecaRol> rolesAsignados = (List<UsuarioBibliotecaRol>) getSessionManagerParam("rolesAsignados");
            delegate.guardarUsuario(usuarioForm, rolesAsignados, orbeActivo);
            if (usuarioForm.getUsuario() != null) {
                setSessionManagerParam(Constants.CONTEXTO_CODIGO_USUARIO,
                        usuarioForm.getUsuario());
            }
            usuarioForm.setReadMode(true);
        } catch (final LibraeException e) {
            log.info("No se puede guardar el Usuario: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("No se puede guardar el Usuario: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GUARDAR_USUARIO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }
        return "list";
    }

    /**
     * Método invocado al pulsar en el botón guardar de la pestaña Usuario.
     * Guardará en BD los datos de esa pestaña invocando a su método delegate.
     * Se queda en la misma página.
     *
     * @return String con el identificador de navigation rule (guardar)
     */
    public String saveGuardar() {
        final String page = "guardar";
        Usuario usuario = null;
        try {
            final Propiedades properties = Propiedades.getInstance();
            final String orbeActivoStr = properties
                    .getPropiedad(Constants.ORBE_ACTIVO);

            if (orbeActivoStr != null && orbeActivoStr.equals("true")) {
                orbeActivo = Boolean.TRUE;
            }

            validarTipoIdentificacion();
            final List<UsuarioBibliotecaRol> rolesAsignados = (List<UsuarioBibliotecaRol>) getSessionManagerParam("rolesAsignados");
            usuario = delegate.guardarUsuario(usuarioForm, rolesAsignados,
                    orbeActivo);
            setMensajeExito("MENSAJE_EXITO_USUARIO",
                    MensajesError.PROPERTI_ADMINCONFIG);
        } catch (final LibraeException e) {
            log.info("No se puede guardar el Usuario: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("Error al guardar el Usuario: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GUARDAR_USUARIO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }
        usuarioForm.setReadMode(true);
        usuarioForm.setCreacion(false);
        usuarioForm.setIdUsuario(usuario.getId());

        /*
         * Metemos el formulario de usuario en session para la vuelta
         */
        setSessionManagerParam(Constants.USUARIO_SESSION_NAME, usuarioForm);

        return page;
    }

    /**
     * Método invocado al pulsar en el botón eliminar de la pestaña de Usuario.
     *
     * @return String con el identificador de navigation rule (list)
     */
    public String desactivar() {
        try {
            delegate.eliminar(usuarioForm.getIdUsuario());
            delegate.prepararDatosVista(usuarioForm);
        } catch (final LibraeException e) {
            log.info("No se puede eliminar el Usuario: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("Error al eliminar el Usuario: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_DESACTIVAR_USUARIO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }

        usuarioForm.setReadMode(true);
        return "list";
    }

    /**
     * Método invocado al pulsar en el botón activar de la pestaña de Usuario.
     *
     * @return String con el identificador de navigation rule (list)
     */
    public String activar() {
        try {
            delegate.activar(usuarioForm.getIdUsuario());
            delegate.prepararDatosVista(usuarioForm);
        } catch (final LibraeException e) {
            log.info("No se puede eliminar el Usuario: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("Error al eliminar el Usuario: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_ACTIVAR_USUARIO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }

        usuarioForm.setReadMode(true);
        return "list";
    }

    /**
     * Método que devuelve el formulario en forma editable. Responde al clic en
     * el boton Editar en la pantalla de Detalle de Usuario.
     *
     * @return cadena vacia
     */
    public String editable() {
        usuarioForm.setReadMode(false);

        /*
         * Metemos el formulario de Usuario en session para la vuelta
         */
        setSessionManagerParam(Constants.USUARIO_SESSION_NAME, usuarioForm);

        return "";
    }

    /**
     * Cancela el crear/editar un Usuario.
     *
     * @return String con el identificador de navigation rule (list)
     */
    public String cancel() {
        usuarioForm.setReadMode(true);

        return "list";
    }

    /**
     * Método que valida el formato y la validez del tipo de identificación y
     * número asociado del lector.
     */
    private void validarTipoIdentificacion() {
        if (!orbeActivo) {
            final TipoIdentificacion tipoIdentificacion = delegate
                    .getTipoIdentificacion(usuarioForm.getTipoIdentificacion());
            ValidationsUtil.validarIdentificador(
                    tipoIdentificacion.getCodigo(), usuarioForm
                            .getNumeroIdentificacion());
        }
    }

    /**
     * Muestra el boton alta o baja de usuario.
     *
     * @return
     */
    public Boolean getIsBaja() {
        return usuarioForm.getActivo();
    }

    /**
     * @return the isAlta
     */
    public Boolean getIsAlta() {
        return !usuarioForm.getActivo();
    }

    // ==================== Getters && Setters ===================

    public Long getIdUsuario() {
        return idUsuario;
    }

    public IUsuarioDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final IUsuarioDelegate delegate) {
        this.delegate = delegate;
    }

    public UsuarioForm getUsuarioForm() {
        return usuarioForm;
    }

    public void setUsuarioForm(final UsuarioForm usuarioForm) {
        this.usuarioForm = usuarioForm;
    }

    public void setIdUsuario(final Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the orbeActivo
     */
    public Boolean getOrbeActivo() {
        return orbeActivo;
    }

    /**
     * @return the orbeActivo
     */
    public void setOrbeActivo(Boolean orbeActivo) {
        this.orbeActivo = orbeActivo;
    }
}
