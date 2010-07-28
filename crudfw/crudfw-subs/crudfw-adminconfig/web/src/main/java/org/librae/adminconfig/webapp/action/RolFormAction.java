package org.librae.adminconfig.webapp.action;

import java.io.Serializable;

import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate;
import org.librae.adminconfig.webapp.form.RolForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.WindowMessages;
import org.springframework.dao.DataAccessException;

/**
 * Action-JSF asociado a los formularios de la entidad Rol.
 *
 * @author jcisneros
 */
public class RolFormAction extends BasePage implements Serializable {

    /**
     *
     */
    private static final long     serialVersionUID = 1L;
    private IGestionRolesDelegate delegate;
    private RolForm               rolForm          = new RolForm();
    private Long                  idRol;

    /**
     * Método invocado cuando se entra en la pantalla de Rol. Se encargará de
     * obtener de session el posible objeto RolForm para inicializar los datos
     *
     * @return cadena vacia
     */
    public String getInit() {
        SessionManager sesion = getSessionManager();
        if (sesion != null) {
            final RolForm rolForm = (RolForm) sesion.getAttribute(Constants.ROL_SESSION_NAME);
            if (rolForm != null) {
                this.rolForm = rolForm;
            }
            sesion.removeAttribute(BasePage.PREFIJO + "permisosAsignados");
            sesion.removeAttribute(BasePage.PREFIJOALL + "permisosAsignados");
            if ((rolForm != null) && (rolForm.getCodigo() != null)) {
                sesion.setAttribute(Constants.CONTEXTO_CODIGO_ROL, rolForm
                        .getCodigo());
            }
        }
        return "";
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
            rolForm.setIdRol(idRol);
            delegate.prepararDatosVista(rolForm);
            rolForm.setReadMode(true);
            rolForm.setCreacion(false);

            /* Metemos el formulario de rol en session */
            setSessionManagerParam(Constants.ROL_SESSION_NAME, rolForm);
        } catch (final LibraeException e) {
            log.info("No se puede entrar a editar un rol...", e);
            page = "list";
        } catch (final Exception e) {
            log.error("No se puede entrar a editar un rol...", e);
            page = "list";
        }
        return page;
    }

    /**
     * Método invocado al pulsar en el botón guardar en la edición/creación Rol.
     * Guardará en BD los datos de esa página invocando a su método delegate.
     * Vuelve al listado.
     *
     * @return String con el identificador de navigation rule (save)
     */
    public String saveAceptar() {
        final String page = "save";
        try {
            delegate.guardarRol(rolForm);
            if (rolForm.getCodigo() != null) {
                setSessionManagerParam(Constants.CONTEXTO_CODIGO_ROL, rolForm
                        .getCodigo());
            }
        } catch (final LibraeException e) {
            log.info("No se puede guardar un Rol: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("Error al guardar un Rol: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GUARDAR_ROL",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }
        rolForm.setReadMode(true);
        return page;
    }

    /**
     * Método invocado al pulsar en el botón guardar en la edición/creación Rol.
     * Guardará en BD los datos de esa página invocando a su método delegate. Se
     * queda en la misma página.
     *
     * @return String con el identificador de navigation rule (guardar)
     */
    public String saveGuardar() {
        final String page = "guardar";
        Rol rol = null;
        try {
            rol = delegate.guardarRol(rolForm);
            setMensajeExito("MENSAJE_EXITO_ROL",
                    MensajesError.PROPERTI_ADMINCONFIG);
        } catch (final LibraeException e) {
            log.info("No se puede guardar un Rol: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("Error al guardar un rol: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GUARDAR_ROL",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }
        rolForm.setReadMode(true);
        rolForm.setCreacion(false);
        rolForm.setIdRol(rol.getId());

        /* Metemos el formulario de Rol en session para la vuelta */
        setSessionManagerParam(Constants.ROL_SESSION_NAME, rolForm);

        return page;
    }

    /**
     * Método invocado para borrar el Rol que estamos editando.
     *
     * @return String con el identificador de navigation rule (eliminar)
     */
    public String eliminar() {
        try {
        	rolForm =  (RolForm) getSessionManagerParam(Constants.ROL_SESSION_NAME);
            delegate.eliminar(rolForm.getIdRol());
            rolForm.setReadMode(true);
        } catch (final LibraeException e) {
            log.info("No se puede eliminar el rol: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final DataAccessException dae) {
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException(dae,
                            MensajesError.PROPERTI_ADMINCONFIG));
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("No se puede eliminar el rol: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_ELIMINAR_ROL",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }

        return "list";
    }

    /**
     * Método que devuelve el formulario en forma editable. Responde al clic en
     * el boton Editar en la pantalla de Detalle de Rol.
     *
     * @return cadena vacia
     */
    public String editable() {
        rolForm.setReadMode(false);

        /* Metemos el formulario de Rol en session */
        setSessionManagerParam(Constants.ROL_SESSION_NAME, rolForm);

        return "";
    }

    /**
     * Cancela el crear un rol.
     *
     * @return vista de roles.
     */
    public String cancel() {
        return "list";
    }

    public void insertarFavorito() {
        try {
            final Object usuario = getSessionManagerParam(Constants.USUARIO_SESSION_PARAM);
            getDelegate().insertarFavorito(usuario, getUrl(),
                    rolForm.getIdRol(), rolForm.getCodigo());
            setSessionManagerParam(Constants.MENU_FAVORITO_LIBRAE, null);
        } catch (final Exception e) {
            log.error(e);
        }
    }

    // Getter && Setters

    public RolForm getRolForm() {
        return rolForm;
    }

    public void setRolForm(final RolForm rolForm) {
        this.rolForm = rolForm;
    }

    public IGestionRolesDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final IGestionRolesDelegate delegate) {
        this.delegate = delegate;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(final Long idRol) {
        this.idRol = idRol;
    }
}